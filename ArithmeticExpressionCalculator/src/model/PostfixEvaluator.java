/*
 * PostfixEvaluator - Arithmetic Calculator Program
 */

package model;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * This class evaluates a arithmetic expression in post-fix notation and returns the result.
 *
 * @author Jacob Klymenko
 * @version 1.2
 */
public final class PostfixEvaluator {

	/** A private constructor to inhibit external instantiation. */
	private PostfixEvaluator() {
		// do nothing
	}

	/**
	 * Evaluates the arithmetic expression in post-fix notation by using a Stack. Operands are
	 * continuously pushed onto the Stack from the String. Once an operator is seen, two
	 * operands are popped and the operator is applied on them and the result is pushed back
	 * onto the Stack.
	 *
	 * @param theRPN the arithmetic expression in RPN
	 * @return the value of the arithmetic expression
	 */
	public static double evaluate(final String theRPN) {
		Deque<Double> stack = new ArrayDeque<Double>();

		for (int i = 0; i < theRPN.length(); i++) {
			char c = theRPN.charAt(i);
			if (Character.isDigit(c)) {
				stack.push((double) Integer.parseInt(String.valueOf(c)));
			} else if (isOperator(c)) { // else the character at hand is an operator
				double value2 = stack.pop();
				double value1 = stack.pop();
				stack.push(applyOperator(value1, value2, c));
			}
		}
		return stack.peek();
	}

	/**
	 * Returns true if the Character is an operator; otherwise false.
	 *
	 * @param theChar the Character being handled
	 * @return true if the Character is an operator; otherwise false
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
	 * Returns a double value after applying the operator on the two operands, in the specific
	 * order of the caller method.
	 *
	 * @param theValue1 the left operand in an arithmetic expression
	 * @param theValue2 the right operand in an arithmetic expression
	 * @param theOperator the operator being applied on the two operands
	 * @return a double value after applying the operator on the two operands
	 */
	private static double applyOperator(final double theValue1, final double theValue2,
	    final char theOperator) {
		double result = 0;

		switch (theOperator) {
			case '-':
				result = theValue1 - theValue2;
				break;
			case '+':
				result = theValue1 + theValue2;
				break;
			case '/':
				result = theValue1 / theValue2;
				break;
			case '*':
				result = theValue1 * theValue2;
				break;
			case '^':
				result = Math.pow(theValue1, theValue2);
				break;
		}
		return result;
	}
}
