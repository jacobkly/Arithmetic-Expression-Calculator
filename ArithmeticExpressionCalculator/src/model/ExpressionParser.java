/*
 * ExpressionParser - Arithmetic Calculator Program
 */

package model;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * This class implements the shunting yard algorithm by Edsger Dijkstra to parse arithmetic
 * expressions. It takes in a String in infix notation and outputs a String in RPN notation.
 * This implementation only parses single digit integers and the following five operators:
 * -, +, /, *, ^.
 *
 * @author Jacob Klymenko
 * @version 1.2
 */
public final class ExpressionParser {

	/**
	 * A boolean variable keeping track if the input expression is valid. Specifically, if
	 * it contains misplaced parentheses.
	 */
	private static boolean myIsValid = true;

	/** A private constructor to inhibit external instantiation. */
	private ExpressionParser() {
		// do nothing
	}

	/**
	 * This implementation of the shunting yard algorithm, originally created by Edsger
	 * Dijkstra, parses infix notation arithmetic expressions, and converts it to a post-fix
	 * notation string (or RPN).
	 *
	 * @param theInfixExpression the infix notation expression being converted into post-fix
	 * @return the RPN expression of the parameter infix notation arithmetic expression
	 */
	public static String shuntingYard(final String theInfixExpression) {
		String outputString = "";
		Deque<Character> operatorStack = new ArrayDeque<Character>();
		myIsValid = true; // resets the boolean, unless updated further down

		for (int i = 0; i < theInfixExpression.length(); i++) {
			char c = theInfixExpression.charAt(i);

			if (Character.isDigit(c)) {
				outputString += c;
			} else if (c == '(') {
				operatorStack.push(c);
			} else if (c == ')') {
				// adds the operator to the string after it's two surrounding operands
				try {
					while (operatorStack.peek() != '(' && !operatorStack.isEmpty()) {
						outputString += operatorStack.pop();
					}
					operatorStack.pop(); // pops the corresponding opening parenthesis
				} catch (Exception error) { // catches misplaced closing parenthesis
					setIsValid(false);
					return "";
				}
			} else { // if this statement is reached, it handles the operators on top of stack
				while (!operatorStack.isEmpty() &&
				    (getPrecedence(c) <= getPrecedence(operatorStack.peek())) &&
				    isLeftAssociative(c)) {
					outputString += operatorStack.pop();
				}
				operatorStack.push(c);
			}
		}
		// deals with the last elems in the stack; whether they are operators or parentheses
		while (!operatorStack.isEmpty()) {
			if (operatorStack.peek() == '(') {
				setIsValid(false);
				return ""; // a return statement is needed to stop the method
			} else {
				setIsValid(true);
				outputString += operatorStack.pop();
			}
		}
		// System.out.println("\n" + outputString);
		return outputString;
	}

	/**
	 * Sets the class boolean myIsValid to either true or false.
	 *
	 * @param theBoolean the boolean value
	 */
	private static void setIsValid(final boolean theBoolean) {
		myIsValid = theBoolean;
	}

	/**
	 * Returns the value of class boolean myIsValid.
	 *
	 * @return the value of class boolean myIsValid
	 */
	public static boolean getIsValid() {
		return myIsValid;
	}

	/**
	 * Returns the precedence of the operator, displayed as a Character, as an integer. Meaning
	 * the order in which operators are evaluated in an expression. In this implementation, the
	 * greater the number, the higher the precedence of the operator, and vice versa.
	 *
	 * @param theChar the operator
	 * @return the precedence of the operator as an integer
	 */
	private static int getPrecedence(final Character theChar) {
		int precedence = -1;

		switch (theChar) {
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

	/**
	 * Returns true if the operator, displayed as a Character, is left associate. Meaning the
	 * operators of the same precedence are evaluated in the order from left to right.
	 * Otherwise return false.
	 *
	 * @param theChar the operator
	 * @return true if the operator is left associative; otherwise false
	 */
	private static boolean isLeftAssociative(final Character theChar) {
		boolean result;

		if (theChar == '-' || theChar == '+' ||
		    theChar == '/' || theChar == '*') {
			result = true;
		} else {
			result = false;
		}

		return result;
	}
}
