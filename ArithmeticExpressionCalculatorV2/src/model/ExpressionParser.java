/*
 * CalculatorMain - Arithmetic Expression Calculator V2
 */

package model;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;
import structures.AETNode;

/**
 * This class implements the shunting yard algorithm by Edsger Dijkstra to parse arithmetic
 * expressions. It also contains useful methods to parse the user inputed expressions.
 *
 * @author Jacob Klymenko
 * @version 2.1
 */
public class ExpressionParser {

	/**
	 * A boolean variable keeping track if the input expression is valid. Specifically, if
	 * it contains misplaced parentheses.
	 */
	private static boolean myIsValid = true;

	/**
	 * A map containing all the valid mathematical functions this calculator accepts. The
	 * corresponding integer values to the string keys represent the following for each
	 * function:
	 *		0 -> no base value
	 * 		1 -> has base value
	 */
	private static Map<String, Integer> myValidFunctions = new HashMap<String, Integer>();

	/** A private constructor to inhibit external instantiation. */
	private ExpressionParser() {
		// do nothing
	}

	/**
	 * Sets all the valid mathematical functions this calculator accepts from the user.
	 */
	public static void setValidFunctions() {
		myValidFunctions.put("sin", 0);
		myValidFunctions.put("cos", 0);
		myValidFunctions.put("tan", 0);
		myValidFunctions.put("sec", 0);
		myValidFunctions.put("csc", 0);
		myValidFunctions.put("cot", 0);
		myValidFunctions.put("log", 1);
		myValidFunctions.put("ln", 1);
	}

	/**
	 * Breaks up the user inputed expression string into a list by separating each block of
	 * substring that is surrounded by space characters into its corresponding indices. The
	 * resulting list is still in infix notation of the original user entered expression.
	 *
	 * @param theUserInput the user inputed arithmetic expression string
	 * @return a list with the user expression separated into according indices
	 */
	public static ArrayList<String> stringToList(final String theUserInput) {
		ArrayList<String> result = new ArrayList<>();
		Map<Integer, String> map = new HashMap<Integer, String>();
		// keeps track of all the space characters from the input string in a map
		for (int i = 0; i < theUserInput.length(); i++) {
			if (theUserInput.charAt(i) == ' ') {
				map.put(i, " ");
			}
		}
		// adds each substring block in between space characters in the list
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

	/**
	 * This implementation of the shunting yard algorithm, parses a list containing the user
	 * expression in infix notation, and converts it to RPN within another list.
	 *
	 * @param theInfixList the infix notation expression displayed as a list
	 * @return the user expression in RPN displayed as a list
	 */
	public static ArrayList<String> shuntingYardRPN(final ArrayList<String> theInfixList) {
		ArrayList<String> output = new ArrayList<String>();
		Deque<String> operatorStack = new ArrayDeque<String>();
		myIsValid = true; // resets the boolean value, unless updated again further down

		for (String s : theInfixList) {
			// identifies if the string is a number, pi, or Euler's number
			if (s.matches("-?\\d+(\\.\\d+)?") || s.equals("pi") || s.equals("e")) {
				output.add(s);
			} else if (isFunction(s)) {
				operatorStack.push(s);
			} else if (s.equals("(")) {
				operatorStack.push(s);
			} else if (s.equals(")")) {
				try {
					while (!operatorStack.peek().equals("(") && !operatorStack.isEmpty() &&
					    !isFunction(operatorStack.peek())) {
						output.add(operatorStack.pop());
					}
					if (operatorStack.peek().equals("(")) {
						operatorStack.pop();
					}
					// gotta check for null b/c previous if statement might empty out the stack
					// when dealing with the last closing parenthesis
					if (operatorStack.equals(null) && isFunction(operatorStack.peek())) {
						output.add(operatorStack.pop());
					}
				} catch (final Exception error) {
					setIsValid(false);
					break;
				}
			} else {
				while (!operatorStack.isEmpty() &&
				    (getPrecedence(s) <= getPrecedence(operatorStack.peek())) &&
				    isLeftAssociative(s)) {
					output.add(operatorStack.pop());
				}
				operatorStack.push(s);
			}
		}
		while (!operatorStack.isEmpty()) {
			if (operatorStack.peek().equals("(")) {
				setIsValid(false);
				break;
			} else {
				// if (isFunction(operatorStack.peek())) {
				// output.add(operatorStack.pop());
				// } else {
				// output.add(operatorStack.pop());
				// }
				output.add(operatorStack.pop());
			}
		}
		System.out.println("\n" + output);
		return output;
	}

	public static AETNode shuntingYardTree(final ArrayList<String> theInfixList) {
		Deque<AETNode> operandStack = new ArrayDeque<AETNode>();
		Deque<String> operatorStack = new ArrayDeque<String>();
		myIsValid = true;

		for (String s : theInfixList) {
			if (s.matches("-?\\d+(\\.\\d+)?") || s.equals("pi") || s.equals("e")) {
				operandStack.push(new AETNode(s, null, null));
			} else if (isFunction(s)) {
				operatorStack.push(s);
			} else if (s.charAt(0) == '(') {
				operatorStack.push(s);
			} else if (s.charAt(0) == ')') {
				try {
					while (!operatorStack.peek().equals("(") && !operatorStack.isEmpty() &&
					    !isFunction(operatorStack.peek())) {
						addNode(operandStack, operatorStack.pop());
					}
					if (operatorStack.peek().equals("(")) {
						operatorStack.pop();
					}
					if (operatorStack.peek() != null &&
					    isFunction(operatorStack.peek())) {
						addNode(operandStack, operatorStack.pop());
					}
				} catch (final Exception error) {
					setIsValid(false);
					break;
				}
			} else {
				while (!operatorStack.isEmpty() &&
				    (getPrecedence(s) <= getPrecedence(operatorStack.peek())) &&
				    isLeftAssociative(s)) {
					// operandStack.push(new AETNode(operatorStack.pop(), null, null));
					addNode(operandStack, operatorStack.pop());
				}
				// System.out.println(3);
				operatorStack.push(s);
			}
		}
		// System.out.println(operatorStack);
		while (!operatorStack.isEmpty()) {
			String top = operatorStack.peek();
			if (top.equals("(")) {
				setIsValid(false);
				break;
			} else if (getPrecedence(top) > 0 || isFunction(top)) {
				// System.out.println(2);
				addNode(operandStack, operatorStack.pop());
			}
		}
		// System.out.println(operandStack.peek().toString());
		return operandStack.peek();
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
	 * Deciphers if the string parameter is a function. Returns true if the string is a
	 * function, otherwise false.
	 *
	 * @param theString the string being examined as a function
	 * @return true if the string is a function; otherwise false
	 */
	private static boolean isFunction(final String theString) {
		boolean result = false;
		final boolean length = theString.length() > 1;
		if (length && myValidFunctions.containsKey(theString.substring(0, 2))) {
			result = true;
		} else if (length && myValidFunctions.containsKey(theString.substring(0, 3))) {
			result = true;
		}
		return result;
	}

	/**
	 * Returns the precedence of the operator as an integer. Precedence; meaning the order in
	 * which operators are evaluated in an expression. The greater the integer, the higher the
	 * precedence of the operator. If the string parameter is not an operator, return -1.
	 *
	 * @param theOperator the operator
	 * @return the precedence of the operator as an integer
	 */
	private static int getPrecedence(final String theOperator) {
		int precedence = -1;
		switch (theOperator) {
			case "-":
				precedence = 2;
				break;
			case "+":
				precedence = 2;
				break;
			case "/":
				precedence = 3;
				break;
			case "*":
				precedence = 3;
				break;
			case "^":
				precedence = 4;
				break;
		}
		return precedence;
	}

	/**
	 * Returns true if the operator is left associative; otherwise false. Left associative
	 * operators; meaning the operators of the same precedence are evaluated in the order from
	 * left to right.
	 *
	 * @param theOperator the operator
	 * @return true if the operator is left associative; otherwise false
	 */
	private static boolean isLeftAssociative(final String theOperator) {
		boolean result;
		if (theOperator == "-" || theOperator == "+" ||
		    theOperator == "/" || theOperator == "*") {
			result = true;
		} else {
			result = false;
		}
		return result;
	}

	private static void addNode(final Deque<AETNode> theOperandStack,
	    final String theOpOrFunc) {
		if (isFunction(theOpOrFunc)) {
			final AETNode leftAETNode = theOperandStack.pop();
			theOperandStack.push(new AETNode(theOpOrFunc, leftAETNode, null));
		} else { // else theOpOrFunc is an operator
			// System.out.println(1);
			final AETNode rightAETNode = theOperandStack.pop();
			final AETNode leftAETNode = theOperandStack.pop();
			theOperandStack.push(new AETNode(theOpOrFunc, leftAETNode, rightAETNode));
		}
	}
}
