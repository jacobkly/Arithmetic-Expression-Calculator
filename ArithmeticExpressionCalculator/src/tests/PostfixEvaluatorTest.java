/*
 * PostfixEvaluatorTest - Arithmetic Calculator Program
 */

package tests;

import model.PostfixEvaluator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tests of the PostfixEvaluator class.
 *
 * @author Jacob Klymenko
 * @version 1.1
 */
public class PostfixEvaluatorTest {

	/** A tolerance value used when comparing equality of doubles. */
	private static final double TOLERANCE = 0.000001;

	/** The first post-fix notation expression test. */
	private final String expression1 = "23^4*63/+2^521+*-";

	/** The second post-fix notation expression test. */
	private final String expression2 = "2532^62/-*2^+823^--";

	/** The third post-fix notation expression test. */
	private final String expression3 = "52^3+74-2^*23^1+62-*/";

	/** The fourth post-fix notation expression test. */
	private final String expression4 = "45*3-322^+/673-*+";

	/**
	 * Test method for {@link model.PostfixEvaluator#evaluate(String)} with the first
	 * post-fix notation expression.
	 */
	@Test
	public void testEvaluateFirst() {
		final double result1 = 1141.0;
		assertEquals(result1, PostfixEvaluator.evaluate(expression1), TOLERANCE);
	}

	/**
	 * Test method for {@link model.PostfixEvaluator#evaluate(String)} with the second
	 * post-fix notation expression.
	 */
	@Test
	public void testEvaluateSecond() {
		final double result2 = 902.0;
		assertEquals(result2, PostfixEvaluator.evaluate(expression2), TOLERANCE);
	}

	/**
	 * Test method for {@link model.PostfixEvaluator#evaluate(String)} with the third
	 * post-fix notation expression.
	 */
	@Test
	public void testEvaluateThird() {
		final double result3 = 7.0;
		assertEquals(result3, PostfixEvaluator.evaluate(expression3), TOLERANCE);
	}

	/**
	 * Test method for {@link model.PostfixEvaluator#evaluate(String)} with the fourth
	 * post-fix notation expression.
	 */
	@Test
	public void testEvaluateFourth() {
		final double result4 = 26.428571428571427;
		assertEquals(result4, PostfixEvaluator.evaluate(expression4), TOLERANCE);
	}

}
