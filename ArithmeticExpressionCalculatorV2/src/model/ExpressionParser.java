/*
 *
 */

package model;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;
import structures.AETNode;

/**
 *
 *
 *
 *
 */
public class ExpressionParser {

	/** A private constructor to inhibit external instantiation. */
	private ExpressionParser() {
		// do nothing
	}

	public static ArrayList<String> stringToList(final String theUserInput) {
		ArrayList<String> result = new ArrayList<>();
		Map<Integer, String> map = new HashMap<Integer, String>();

		// keeps track of all the space characters from the input string in a map
		for (int i = 0; i < theUserInput.length(); i++) {
			if (theUserInput.charAt(i) == ' ') {
				map.put(i, " ");
			}
		}

		// adds everything in between the space characters separately in the list
		int track = 0;
		for (int j = 0; j < theUserInput.length(); j++) {
			if (map.containsKey(j)) {
				result.add(theUserInput.substring(track, j));
				track = j + 1;
			}
		}
		result.add(theUserInput.substring(track)); // adds the last part of the input

		return result;
	}

	public static AETNode shuntingYard(final ArrayList<String> theInfixList) {
		Deque<String> operatorStack = new ArrayDeque<String>();
		Deque<AETNode> operandStack = new ArrayDeque<AETNode>();

		for (String s : theInfixList) {
			char c = s.charAt(0);
			String popped;

			if (Character.isDigit(c)) {
				operandStack.push(new AETNode(s, null, null));
			} else if (s == "(") {
				operatorStack.push(s);
			} else if (s == ")") {
				while (!operatorStack.isEmpty()) {
					popped = operatorStack.pop();
					if (popped == "(") {
						continue;
					} else {
						addNode(operandStack, popped);
					}
				}
				throw new IllegalStateException("Misplaced closing parenthesis. " +
				    "Please try again.");
			} else {
				while (!operatorStack.isEmpty() &&
				    (getPrecedence(c) <= getPrecedence(operatorStack.peek().charAt(0))) &&
				    isLeftAssociative(c)) {
					operandStack.push(new AETNode(operatorStack.pop(), null, null));
				}
				operatorStack.push(s);
			}
		}
		while (!operatorStack.isEmpty()) {
			addNode(operandStack, operatorStack.pop());
		}
		return operandStack.pop();
	}

	private static void addNode(final Deque<AETNode> theStack, final String theOperator) {
		final AETNode rightASTNode = theStack.pop();
		final AETNode leftASTNode = theStack.pop();
		theStack.push(new AETNode(theOperator, leftASTNode, rightASTNode));
	}

	/**
	 * Returns the precedence of the operator, displayed as a Character, as an integer. Meaning
	 * the order in which operators are evaluated in an AETression. In this implementation, the
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
