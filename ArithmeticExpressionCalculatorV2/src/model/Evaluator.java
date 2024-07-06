/*
 *
 */

package model;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import structures.AETNode;

/**
 *
 *
 *
 *
 */
public class Evaluator {

	/** A private constructor to inhibit external instantiation. */
	private Evaluator() {
		// do nothing
	}

	public static double evaluateRPN(final ArrayList<String> theRPN) {
		Deque<Double> stack = new ArrayDeque<Double>();
		for (int i = 0; i < theRPN.size(); i++) {
			String s = theRPN.get(i);
			if (isNumber(s)) {
				stack.push(Double.parseDouble(s));
			} else if (isOperator(s.charAt(0))) {
				double value2 = stack.pop();
				double value1 = stack.pop();
				stack.push(applyOperator(value1, value2, s));
			}
		}
		return stack.peek();
	}

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

	public static double evaluateFunction(final String theFunction) {
		double result = 0.0;
		final int openBracket = theFunction.indexOf("(");
		final int closedBracket = theFunction.indexOf(")");
		final String insideBracketValue =
		    theFunction.substring(openBracket + 1, closedBracket);
		final double insideValue = Double.parseDouble(insideBracketValue);

		if (theFunction.substring(0, 2) == "ln") {
			result = Math.log(insideValue);
		} else {
			switch (theFunction.substring(0, 3)) {
				case "log":
					if (theFunction.contains("_")) {
						int underscore = theFunction.indexOf('_');
						String baseString = theFunction.substring(underscore + 1, openBracket);
						double baseValue = Double.parseDouble(baseString);
						result = Math.log(insideValue) / Math.log(baseValue);
					} else {
						result = Math.log(insideValue) / Math.log(10);
					}
				case "sin":

				case "cos":

				case "tan":

				case "sec":

				case "csc":

				case "cot":
			}
		}
		return result;
	}

	private static boolean isNumber(final String theString) {
		// matches a number with optional '-' and decimal.
		return theString.matches("-?\\d+(\\.\\d+)?");
	}

	private static boolean isOperator(final Character theChar) {
		boolean result = false;
		if (theChar == '-' || theChar == '+' || theChar == '/' ||
		    theChar == '*' || theChar == '^') {
			result = true;
		}
		return result;
	}

	private static double applyOperator(final double theValue1, final double theValue2,
	    final String theOperator) {
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
}
