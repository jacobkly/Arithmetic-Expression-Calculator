/*
 * CalculatorMain - Arithmetic Expression Calculator V2
 */

package model;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import structures.AETNode;

/**
 * This class evaluates an arithmetic expression in RPN, represented in a list, and returns
 * the resulting value.
 *
 * @author Jacob Klymenko
 * @version 2.1
 */
public class Evaluator {

	/** The positive tolerance of a value. Meant for rounding to zero purposes. */
	private final static double POS_TOLERANCE = 0.00000001;

	/** The negative tolerance of a value. Meant for rounding to zero purposes. */
	private final static double NEG_TOLERANCE = -0.00000001;

	/** A private constructor to inhibit external instantiation. */
	private Evaluator() {
		// do nothing
	}

	/**
	 * Evaluates the arithmetic expression in RPN, represented in a list, by using a stack.
	 * Operands are continuously pushed onto the stack from the list. Once an operator is
	 * seen, two operands are popped and the operator is applied on them and the result is
	 * pushed back onto the stack. The final value is the last element in the stack.
	 *
	 * @param theRPN the arithmetic expression in RPN represented in a list
	 * @return the value of the arithmetic expression
	 */
	public static double evaluateRPN(final ArrayList<String> theRPN) {
		Deque<Double> stack = new ArrayDeque<Double>();
		for (int i = 0; i < theRPN.size(); i++) {
			String s = theRPN.get(i);
			if (s.matches("-?\\d+(\\.\\d+)?")) { // identifies if the string is a number
				stack.push(Double.parseDouble(s));
			} else if (isOperator(s.charAt(0))) {
				double value2 = stack.pop();
				double value1 = stack.pop();
				stack.push(applyOperator(s, value1, value2));
			} else { // if reached, the function is handled
				double value = stack.pop();
				stack.push(applyFunction(s, value));
			}
		}
		return stack.peek();
	}

	/**
	 * Evaluates the arithmetic expression tree (AET) with recursion.
	 *
	 * @param theTree the arithmetic expression tree (AET) to evaluate
	 * @return the value of the arithmetic expression tree (AET)
	 */
	public static double evaluateAET(final AETNode theTree) {
		switch (theTree.getValue()) {
			case "-":
				return evaluateAET(theTree.getLeftAETNode()) -
				    evaluateAET(theTree.getRightAETNode());
			case "+":
				return evaluateAET(theTree.getLeftAETNode()) +
				    evaluateAET(theTree.getRightAETNode());
			case "/":
				return evaluateAET(theTree.getLeftAETNode()) /
				    evaluateAET(theTree.getRightAETNode());
			case "*":
				return evaluateAET(theTree.getLeftAETNode()) *
				    evaluateAET(theTree.getRightAETNode());
			case "^":
				return Math.pow(evaluateAET(theTree.getLeftAETNode()),
				    evaluateAET(theTree.getRightAETNode()));
			default:
				return Double.valueOf(theTree.getValue());
		}
	}

	/**
	 * Returns true if the character is an operator; otherwise false.
	 *
	 * @param theChar the character being examined
	 * @return true if the character is an operator; otherwise false
	 */
	private static boolean isOperator(final Character theChar) {
		boolean result = false;
		if (theChar == '-' || theChar == '+' || theChar == '/' ||
		    theChar == '*' || theChar == '^') {
			result = true;
		}
		return result;
	}

	/**
	 * Returns a double value after applying the operator on the two operands, in the
	 * designated order of the caller method.
	 *
	 * @param theOperator the operator being applied on the two operands
	 * @param theValue1 the operand to the left of an operator in an infix notation equation
	 * @param theValue2 the operand to the right of an operator in an infix notation equation
	 * @return a double value after applying the operator on the two operands
	 */
	private static double applyOperator(final String theOperator, final double theValue1,
	    final double theValue2) {
		double result = 0;

		switch (theOperator) {
			case "-":
				result = theValue1 - theValue2;
				break;
			case "+":
				result = theValue1 + theValue2;
				break;
			case "/":
				result = theValue1 / theValue2;
				break;
			case "*":
				result = theValue1 * theValue2;
				break;
			case "^":
				result = Math.pow(theValue1, theValue2);
				break;
		}
		return result;
	}

	/**
	 * Evaluates a logarithmic or trigonometric function. For logarithmic functions (excluding
	 * natural logarithms), it takes into consideration the potential base values. For
	 * trigonometric functions, the values are strictly in radians.
	 *
	 * @param theFunction the function being evaluated
	 * @param theValue the value within the function argument
	 * @return the value of the function
	 */
	private static double applyFunction(final String theFunction, final double theValue) {
		double result = 0.0;
		// prob not the best to leave if statement w/o the condition 'theFunction == "ln"' or
		// 'theFunction.substring(0, 2) == "ln"', but that doesn't work here for some reason
		if (theFunction.length() == 2) {
			result = Math.log(theValue);
		} else {
			switch (theFunction.substring(0, 3)) {
				case "log":
					if (theFunction.contains("_")) {
						// base value is from the underscore till the end of the string
						final String baseString = theFunction.substring(4);
						final double baseValue = Double.parseDouble(baseString);
						result = Math.log(theValue) / Math.log(baseValue);
					} else {
						result = Math.log(theValue) / Math.log(10);
					}
					break;
				case "sin":
					result = Math.sin(theValue);
					break;
				case "cos":
					result = Math.cos(theValue);
					break;
				case "tan":
					result = Math.tan(theValue);
					break;
				case "sec":
					result = 1 / Math.cos(theValue);
					break;
				case "csc":
					result = 1 / Math.sin(theValue);
					break;
				case "cot":
					result = 1 / Math.tan(theValue);
					break;
			}
		}
		if (result < POS_TOLERANCE && result > NEG_TOLERANCE) {
			result = 0;
		}
		return result;
	}
}
