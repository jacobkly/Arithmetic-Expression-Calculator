/*
 * PostfixEvaluator - Arithmetic Calculator Program
 */

package model;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Queue;

/**
 *
 *
 * @author Jacob Klymenko
 * @version 1.1
 */
public final class PostfixEvaluator {

	private PostfixEvaluator() {

	}

	public static double evaluate(final Queue<Character> theQueue) {
		Deque<Double> stack = new ArrayDeque<Double>();

		while (!theQueue.isEmpty()) {
			if (Character.isDigit(theQueue.peek())) {
				char c = theQueue.remove();
				stack.push((double) Integer.parseInt(String.valueOf(c)));
			} else { // else, we expect the char to be an operator
				char operator = theQueue.remove();
				double value2 = stack.pop();
				double value1 = stack.pop();
				stack.push(applyOperator(value1, value2, operator));
			}
		}

		return stack.peek();
	}

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
