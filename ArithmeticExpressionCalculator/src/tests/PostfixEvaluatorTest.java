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

	/** The first post-fix notation expression to be tested. */
	private final String myExpression1 = "23^4*63/+2^521+*-";

	/** The second post-fix notation expression to be tested. */
	private final String myExpression2 = "2532^62/-*2^+823^--";

	/** The third post-fix notation expression to be tested. */
	private final String myExpression3 = "52^3+74-2^*23^1+62-*/";

	/** The fourth post-fix notation expression to be tested. */
	private final String myExpression4 = "45*3-322^+/673-*+";

	/**
	 * Test method for {@link model.PostfixEvaluator#evaluate(String)} with the first
	 * post-fix notation expression being evaluated.
	 *
	 * The equivalent infix notation expression: (((2^3*4)+(6/3))^2)-(5*(2+1)) which
	 * equals 1141
	 */
	@Test
	public void testEvaluateFirst() {
		final double result1 = 1141.0;
		assertEquals(result1, PostfixEvaluator.evaluate(myExpression1), TOLERANCE);
	}

	/**
	 * Test method for {@link model.PostfixEvaluator#evaluate(String)} with the second
	 * post-fix notation expression being evaluated.
	 *
	 * The equivalent infix notation expression: 2+((5*(3^2-(6/2)))^2)-(8-2^3) which
	 * equals 902
	 */
	@Test
	public void testEvaluateSecond() {
		final double result2 = 902.0;
		assertEquals(result2, PostfixEvaluator.evaluate(myExpression2), TOLERANCE);
	}

	/**
	 * Test method for {@link model.PostfixEvaluator#evaluate(String)} with the third
	 * post-fix notation expression being evaluated.
	 *
	 * The equivalent infix notation expression: ((5^2+3)*(7-4)^2)/((2^3+1)*(6-2)) which
	 * equals 7
	 */
	@Test
	public void testEvaluateThird() {
		final double result3 = 7.0;
		assertEquals(result3, PostfixEvaluator.evaluate(myExpression3), TOLERANCE);
	}

	/**
	 * Test method for {@link model.PostfixEvaluator#evaluate(String)} with the fourth
	 * post-fix notation expression being evaluated.
	 *
	 * The equivalent infix notation expression: ((4*5)-3)/(3+2^2)+(6*(7-3)) which
	 * equals ~26.428571
	 */
	@Test
	public void testEvaluateFourth() {
		final double result4 = 26.428571428571427;
		assertEquals(result4, PostfixEvaluator.evaluate(myExpression4), TOLERANCE);
	}

}
