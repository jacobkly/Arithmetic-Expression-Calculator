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

	/**
	 * A boolean variable keeping track if the input expression is valid. Specifically, if
	 * it contains misplaced parentheses.
	 */
	private static boolean myIsValid = true;

	/**
	 * Integer:
	 * 		0 --> no base value
	 * 		1 --> has base value
	 */
	private static Map<String, Integer> myValidFunctions = new HashMap<String, Integer>();

	/** A private constructor to inhibit external instantiation. */
	private ExpressionParser() {
		// do nothing
	}

	public static void initializeFunctions() {
		myValidFunctions.put("sin", 0);
		myValidFunctions.put("cos", 0);
		myValidFunctions.put("tan", 0);
		myValidFunctions.put("sec", 0);
		myValidFunctions.put("csc", 0);
		myValidFunctions.put("cot", 0);
		myValidFunctions.put("log", 1);
		myValidFunctions.put("ln(", 1);
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

	public static ArrayList<String> shuntingYardRPN(final ArrayList<String> theInfixList) {
		ArrayList<String> output = new ArrayList<String>();
		Deque<String> operatorStack = new ArrayDeque<String>();
		myIsValid = true; // resets the boolean value, unless updated again further down

		for (String s : theInfixList) {
			if (isNumber(s)) {
				output.add(s);
			} else if (isFunction(s)) {
				operatorStack.push(s);
			} else if (s.charAt(0) == '(') {
				operatorStack.push(s);
			} else if (s.charAt(0) == ')') {
				try {
					String top = operatorStack.peek();
					while (operatorStack.peek().charAt(0) != '(' && !operatorStack.isEmpty() &&
					    !isFunction(top)) {
						output.add(operatorStack.pop());
					}
					if (operatorStack.peek().charAt(0) == '(') {
						operatorStack.pop();
					}
					if (isFunction(top)) {
						final double functionResult =
						    Evaluator.evaluateFunction(operatorStack.pop());
						output.add(String.valueOf(functionResult));
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
			if (operatorStack.peek().charAt(0) == '(') {
				setIsValid(false);
				break;
			} else {
				if (isFunction(operatorStack.peek())) {
					final double functionResult =
					    Evaluator.evaluateFunction(operatorStack.pop());
					output.add(String.valueOf(functionResult));
				} else {
					output.add(operatorStack.pop());
				}
				setIsValid(true);
			}
		}
		System.out.println("\n" + output);
		return output;
	}

	// public static AETNode shuntingYardTree(final ArrayList<String> theInfixList) {
	// Deque<String> operatorStack = new ArrayDeque<String>();
	// Deque<AETNode> operandStack = new ArrayDeque<AETNode>();
	//
	// for (String s : theInfixList) {
	// char c = s.charAt(0);
	// String popped;
	//
	// if (Character.isDigit(c)) {
	// operandStack.push(new AETNode(s, null, null));
	// } else if (s == "(") {
	// operatorStack.push(s);
	// } else if (s == ")") {
	// while (!operatorStack.isEmpty()) {
	// popped = operatorStack.pop();
	// if (popped == "(") {
	// continue;
	// } else {
	// addNode(operandStack, popped);
	// }
	// }
	// throw new IllegalStateException("Misplaced closing parenthesis. " +
	// "Please try again.");
	// } else {
	// while (!operatorStack.isEmpty() &&
	// (getPrecedence(c) <= getPrecedence(operatorStack.peek().charAt(0))) &&
	// isLeftAssociative(c)) {
	// operandStack.push(new AETNode(operatorStack.pop(), null, null));
	// }
	// operatorStack.push(s);
	// }
	// }
	// while (!operatorStack.isEmpty()) {
	// addNode(operandStack, operatorStack.pop());
	// }
	// return operandStack.pop();
	// }

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

	private static boolean isNumber(final String theString) {
		// matches a number with optional '-' and decimal.
		return theString.matches("-?\\d+(\\.\\d+)?");
	}

	private static boolean isFunction(final String theString) {
		return theString.length() > 1 &&
		    myValidFunctions.containsKey(theString.substring(0, 3));
	}

	private static void addNode(final Deque<AETNode> theStack, final String theOperator) {
		final AETNode rightASTNode = theStack.pop();
		final AETNode leftASTNode = theStack.pop();
		theStack.push(new AETNode(theOperator, leftASTNode, rightASTNode));
	}

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
}
