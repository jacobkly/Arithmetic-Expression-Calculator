/*
 * ExpressionParserTest - Arithmetic Calculator Program
 */

package tests;

import model.ExpressionParser;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Tests of the ExpressionParser class.
 *
 * @author Jacob Klymenko
 * @version 1.1
 */
public class ExpressionParserTest {

	/** The first infix notation expression to be tested. */
	private final String myInfixExp1 = "(9/(5-2))^3+((8*3)-(9/(3^2+1)))";

	/** The second infix notation expression to be tested. */
	private final String myInfixExp2 = "((7/(3+2))^2)*((8-3^2)+(6/2))";

	/** The third infix notation expression to be tested. */
	private final String myInfixExp3 = "((9/3)+(8*(7-(4+1)))^2)*2";

	/** The fourth infix notation expression to be tested. */
	private final String myInfixExp4 =
	    "(((3^3+2)*(5^2-(8/4))^2)/(7+((6+4*(2^2-3))/(5-3*1))^2))";

	/** The first valid infix notation expression to be tested. */
	private final String myValidExpression1 = "((((((((((9+1)(9+1))))))))))";

	/** The second valid infix notation expression to be tested. */
	private final String myValidExpression2 = "((((5+4)+3)+2)+1)";

	/** The first invalid infix notation expression to be tested. */
	private final String myInvalidExpression1 = "(((3+2)+1)";

	/** The second invalid infix notation expression to be tested. */
	private final String myInvalidExpression2 = "(1+(2+3)))";

	/**
	 * Test method for {@link model.ExpressionParser#shuntingYard(java.lang.String)} with the
	 * first infix notation expression being parsed.
	 */
	@Test
	public void testShuntingYardFirst() {
		// the converted infix notation expression into post-fix notation
		final String postfixExp1 = "952-/3^83*932^1+/-+";
		assertEquals(postfixExp1, ExpressionParser.shuntingYard(myInfixExp1));
	}

	/**
	 * Test method for {@link model.ExpressionParser#shuntingYard(java.lang.String)} with the
	 * first infix notation expression being parsed.
	 */
	@Test
	public void testShuntingYardSecond() {
		// the converted infix notation expression into post-fix notation
		final String postfixExp2 = "732+/2^832^-62/+*";
		assertEquals(postfixExp2, ExpressionParser.shuntingYard(myInfixExp2));
	}

	/**
	 * Test method for {@link model.ExpressionParser#shuntingYard(java.lang.String)} with the
	 * second infix notation expression being parsed.
	 */
	@Test
	public void testShuntingYardThird() {
		// the converted infix notation expression into post-fix notation
		final String postfixExp3 = "93/8741+-*2^+2*";
		assertEquals(postfixExp3, ExpressionParser.shuntingYard(myInfixExp3));
	}

	/**
	 * Test method for {@link model.ExpressionParser#shuntingYard(java.lang.String)} with the
	 * fourth infix notation expression being parsed.
	 */
	@Test
	public void testShuntingYardFourth() {
		// the converted infix notation expression into post-fix notation
		final String postfixExp4 = "33^2+52^84/-2^*76422^3-*+531*-/2^+/";
		assertEquals(postfixExp4, ExpressionParser.shuntingYard(myInfixExp4));
	}

	/**
	 * Test method for {@link model.ExpressionParser#getIsValid()} with the first valid infix
	 * notation expression being tested.
	 */
	@Test
	public void testGetIsValidFirstTrue() {
		// To correctly test the getIsValud() method, we must pass through an expression. Even
		// if the post-fix notation expression is not used any further.
		@SuppressWarnings("unused")
		final String test = ExpressionParser.shuntingYard(myValidExpression1);
		assertTrue(ExpressionParser.getIsValid());
	}

	/**
	 * Test method for {@link model.ExpressionParser#getIsValid()} with the second valid infix
	 * notation expression being tested.
	 */
	@Test
	public void testGetIsValidSecondTrue() {
		// To correctly test the getIsValud() method, we must pass through an expression. Even
		// if the post-fix notation expression is not used any further.
		@SuppressWarnings("unused")
		final String test = ExpressionParser.shuntingYard(myValidExpression2);
		assertTrue(ExpressionParser.getIsValid());
	}

	/**
	 * Test method for {@link model.ExpressionParser#getIsValid()} with the first invalid infix
	 * notation expression being tested.
	 */
	@Test
	public void testGetIsValidFirstFalse() {
		// To correctly test the getIsValud() method, we must pass through an expression. Even
		// if the post-fix notation expression is not used any further.
		@SuppressWarnings("unused")
		final String test = ExpressionParser.shuntingYard(myInvalidExpression1);
		assertFalse(ExpressionParser.getIsValid());
	}

	/**
	* Test method for {@link model.ExpressionParser#getIsValid()} with the second invalid
	* infix notation expression being tested.
	*/
	@Test
	public void testGetIsValidSecondFalse() {
		// To correctly test the getIsValud() method, we must pass through an expression. Even
		// if the post-fix notation expression is not used any further.
		@SuppressWarnings("unused")
		final String test = ExpressionParser.shuntingYard(myInvalidExpression2);
		assertFalse(ExpressionParser.getIsValid());
	}

}
