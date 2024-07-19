/*
 * EvaluatorTest - Arithmetic Expression Calculator V2
 */

package tests;

import java.util.ArrayList;
import model.Evaluator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

/**
 * This class tests the Evaluator class in the model package.
 *
 * @author Jacob Klymenko
 * @version 2.0
 */
public class EvaluatorTest {

	/** A tolerance value used when comparing equality of doubles. */
	private static final double TOLERANCE = 0.000001;

	/** The first post-fix notation expression to be evaluated. */
	private static ArrayList<String> myExp1 = new ArrayList<String>();

	/** The second post-fix notation expression to be evaluated. */
	private static ArrayList<String> myExp2 = new ArrayList<String>();

	/** The third post-fix notation expression to be evaluated. */
	private static ArrayList<String> myExp3 = new ArrayList<String>();

	/** The fourth post-fix notation expression to be evaluated. */
	private static ArrayList<String> myExp4 = new ArrayList<String>();

	/**
	 * Test method for {@link model.Evaluator#evaluateRPN(java.util.ArrayList)} with the first
	 * post-fix notation expression being evaluated.
	 *
	 * The equivalent infix notation expression: "3.7 * log_10 ( 8.2 ) - sin ( pi / 3.5 )"
	 * equals ~2.59927977135.
	 */
	@Test
	public void testEvaluateRPNFirst() {
		final double result = 2.59927977135;
		String[] exp1 = {"3.7", "8.2", "log_10", "*", "pi", "3.5", "/", "sin", "-"};
		for (int i = 0; i < exp1.length; i++) {
			myExp1.add(exp1[i]);
		}
		assertEquals(result, Evaluator.evaluateRPN(myExp1), TOLERANCE);
	}

	/**
	 * Test method for {@link model.Evaluator#evaluateRPN(java.util.ArrayList)} with the
	 * second post-fix notation expression being evaluated.
	 *
	 * The equivalent infix notation expression:
	 * "( ( sin ( 3.3 * pi ) / log_10 ( 5.6 ) ) + 7.4 ) * ( tan ( 1.8 ) - 4.2 ^ 0.5 )"
	 * equals ~-40.0330723589.
	 */
	@Test
	public void testEvaluateRPNSecond() {
		final double result = -40.0330723589;
		String[] exp2 = {"3.3", "pi", "*", "sin", "5.6", "log_10", "/", "7.4", "+", "1.8",
		    "tan", "4.2", "0.5", "^", "-", "*"};
		for (int i = 0; i < exp2.length; i++) {
			myExp2.add(exp2[i]);
		}
		assertEquals(result, Evaluator.evaluateRPN(myExp2), TOLERANCE);
	}

	/**
	 * Test method for {@link model.Evaluator#evaluateRPN(java.util.ArrayList)} with the
	 * third post-fix notation expression being evaluated.
	 *
	 * The equivalent infix notation expression:
	 * "( tan ( 4.5 ) * log_2 ( 15.2 ) - 5.7 / cos ( 1.2 ) ) + ( 8.9 - log_6 ( 7.3 ^ 2.1 ) )"
	 * equals ~9.04599944.
	 */
	@Test
	public void testEvaluateRPNThird() {
		final double result = 9.04599944;
		String[] exp3 = {"4.5", "tan", "15.2", "log_2", "*", "5.7", "1.2", "cos", "/", "-",
		    "8.9", "7.3", "2.1", "^", "log_6", "-", "+"};
		for (int i = 0; i < exp3.length; i++) {
			myExp3.add(exp3[i]);
		}
		assertEquals(result, Evaluator.evaluateRPN(myExp3), TOLERANCE);
	}

	/**
	 * Test method for {@link model.Evaluator#evaluateRPN(java.util.ArrayList)} with the
	 * fourth post-fix notation expression being evaluated.
	 *
	 * The equivalent infix notation expression:
	 * "( cos ( 2.5 ) + log_2 ( 9.1 + 6.7 ^ 1.2 ) ) * ( 5.3 + sin ( 0.75 * pi ) )"
	 * equals ~15.7962477107.
	 */
	@Test
	public void testEvaluateRPNFourth() {
		final double result = 15.7962477107;
		String[] exp4 = {"2.5", "cos", "9.1", "6.7", "1.2", "^", "+", "log_2", "+", "5.3",
		    "0.75", "pi", "*", "sin", "-", "*"};
		for (int i = 0; i < exp4.length; i++) {
			myExp4.add(exp4[i]);
		}
		assertEquals(result, Evaluator.evaluateRPN(myExp4), TOLERANCE);
	}

	/**
	 * Test method for {@link model.Evaluator#evaluateAET(structures.AETNode)}.
	 */
	@Test
	public void testEvaluateAET() {
		fail("Not yet implemented");
	}

}
