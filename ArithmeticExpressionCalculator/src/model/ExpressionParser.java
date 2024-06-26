/*
 * ExpressionParser - Arithmetic Calculator Program
 */

package model;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

/**
 * This class implements the shunting yard algorithm by Edsger Dijkstra to parse arithmetic
 * expressions. It takes in a String in infix notation and outputs a Queue in RPN notation.
 *
 * @author Jacob Klymenko
 * @version 1.1
 */
public final class ExpressionParser {

	/** A boolean variable keeping track if the input expression is valid. */
	private static boolean myIsValid;

	/** A private constructor to inhibit external instantiation. */
	private ExpressionParser() {
		// do nothing
	}

	public static Queue<Character> shuntingYard(final String theInfixExpression) {
		String infixExpression = theInfixExpression.replaceAll("\\s", "");

		Queue<Character> outputQueue = new LinkedList<Character>();
		Deque<Character> operatorStack = new ArrayDeque<Character>();

		for (int i = 0; i < infixExpression.length(); i++) {
			char c = infixExpression.charAt(i);

			if (Character.isDigit(c)) {
				outputQueue.add(c);
			} else if (c == '(') {
				operatorStack.push(c);
			} else if (c == ')') {
				while (operatorStack.peek() != '(' && !operatorStack.isEmpty()) {
					outputQueue.add(operatorStack.pop());
				}
			} else { // if this statement is reached, it handles the operators on top of stack
				while (!operatorStack.isEmpty() &&
				    (getPrecedence(c) <= getPrecedence(operatorStack.peek())) &&
				    isLeftAssociative(c)) {
					outputQueue.add(operatorStack.pop());
				}
				operatorStack.add(c);
			}
		}

		while (!operatorStack.isEmpty()) {
			if (operatorStack.peek() == '(') {
				setIsValid(false);
			} else {
				setIsValid(true);
				outputQueue.add(operatorStack.pop());
			}

		}

		return outputQueue;
	}

	private static void setIsValid(final boolean theBoolean) {
		myIsValid = theBoolean;
	}

	public static boolean getIsValid() {
		return myIsValid;
	}

	private static int getPrecedence(final Character theCharacter) {
		int precedence = 1;

		switch (theCharacter) {
			case '-':
				precedence = 2;
				break;
			case '+':
				precedence = 2;
				break;
			case '/':
				precedence = 3;
				break;
			case '*':
				precedence = 3;
				break;
			case '^':
				precedence = 4;
				break;
		}

		return precedence;
	}

	private static boolean isLeftAssociative(final Character theCharacter) {
		boolean result;

		if (theCharacter == '-' || theCharacter == '+' ||
		    theCharacter == '/' || theCharacter == '*') {
			result = true;
		} else {
			result = false;
		}

		return result;
	}
}