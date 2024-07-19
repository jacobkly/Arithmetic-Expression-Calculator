/**
 *
 */
package tests;

import java.util.ArrayList;
import model.ExpressionParser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

/**
 *
 */
class ExpressionParserTest {

	/** The first valid infix notation expression to be tested. */
	private final String myValidExp1 = "3.7 * log_10 ( 8.2 ) - sin ( pi / 3.5 )";

	/** The second valid infix notation expression to be tested. */
	private final String myValidExp2 =
	    "( cos ( 2.5 ) + log_2 ( 9.1 + 6.7 ^ 1.2 ) ) * ( 5.3 + sin ( 0.75 * pi ) )";

	/** The first invalid infix notation expression to be tested. */
	private final String myInvalidExp1 = "( ( ( 3 + 2 ) + 1 )";

	/** The second invalid infix notation expression to be tested. */
	private final String myInvalidExp2 = "( 1 + ( 2 + 3 ) ) )";

	/** A temporary ArrayList to hold expressions in a stringToList() format. */
	private ArrayList<String> myTempList = new ArrayList<String>();

	/**
	 * Clears the myTempList ArrayList before each of the tests.
	 *
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		myTempList.clear();
	}

	/**
	 * Test method for {@link model.ExpressionParser#stringToList(java.lang.String)} on the
	 * first valid infix notation expression.
	 */
	@Test
	void testStringToListFirst() {
		String[] validExp1 = {"3.7", "*", "log_10", "(", "8.2", ")", "-", "sin", "(", "pi",
		    "/", "3.5", ")"};
		for (int i = 0; i < validExp1.length; i++) {
			myTempList.add(validExp1[i]);
		}
		assertEquals(myTempList, ExpressionParser.stringToList(myValidExp1));
	}

	/**
	 * Test method for {@link model.ExpressionParser#stringToList(java.lang.String)} on the
	 * second valid infix notation expression.
	 */
	@Test
	void testStringToListSecond() {
		String[] validExp2 = {"(", "cos", "(", "2.5", ")", "+", "log_2", "(", "9.1", "+", "6.7",
		    "^", "1.2", ")", ")", "*", "(", "5.3", "+", "sin", "(", "0.75", "*", "pi", ")", ")"};
		for (int i = 0; i < validExp2.length; i++) {
			myTempList.add(validExp2[i]);
		}
		assertEquals(myTempList, ExpressionParser.stringToList(myValidExp2));
	}

	// [3.7, 8.2, log_10, *, pi, 3.5, /, sin, -]

	/**
	 * Test method for {@link model.ExpressionParser#shuntingYardRPN(java.util.ArrayList)}
	 * on the first valid infix notation expression.
	 *
	 * The isFunction() does not work correctly when called from this test class. However, it
	 * does work when the calculator program itself is running.
	 */
	@Test
	void testShuntingYardRPNFirst() {
		// ArrayList<String> list = ExpressionParser.stringToList(myValidExp1);
		// String[] rpnExp1 = {"3.7", "8.2", "log_10", "*", "pi", "3.5", "/", "sin", "- "};
		// for (int i = 0; i < rpnExp1.length; i++) {
		// myTempList.add(rpnExp1[i]);
		// }
		// System.out.println(myTempList);
		// System.out.println(ExpressionParser.shuntingYardRPN(list));
		// assertEquals(myTempList, ExpressionParser.shuntingYardRPN(list));
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link model.ExpressionParser#shuntingYardRPN(java.util.ArrayList)}
	 * on the second valid infix notation expression.
	 *
	 * The isFunction() does not work correctly when called from this test class. However, it
	 * does work when the calculator program itself is running.
	 */
	@Test
	void testShuntingYardRPNSecond() {
		// ArrayList<String> list = ExpressionParser.stringToList(myValidExp2);
		// String[] rpnExp2 = {"2.5", "cos", "9.1", "6.7", "1.2", "^", "+", "log_2", "+", "5.3",
		// "0.75", "pi", "*", "sin", "-", "*"};
		// for (int i = 0; i < rpnExp2.length; i++) {
		// myTempList.add(rpnExp2[i]);
		// }
		// assertEquals(myTempList, ExpressionParser.shuntingYardRPN(list));
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link model.ExpressionParser#shuntingYardTree(java.util.ArrayList)}.
	 */
	@Test
	void testShuntingYardTree() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link model.ExpressionParser#getIsValid()} with the first valid infix
	 * notation expression being tested.
	 */
	@Test
	void testGetIsValidFirstTrue() {
		myTempList = ExpressionParser.stringToList(myValidExp1);
		@SuppressWarnings("unused")
		final ArrayList<String> test = ExpressionParser.shuntingYardRPN(myTempList);
		assertTrue(ExpressionParser.getIsValid());
	}

	/**
	 * Test method for {@link model.ExpressionParser#getIsValid()} with the second valid infix
	 * notation expression being tested.
	 */
	@Test
	void testGetIsValidSecondTrue() {
		myTempList = ExpressionParser.stringToList(myValidExp2);
		@SuppressWarnings("unused")
		final ArrayList<String> test = ExpressionParser.shuntingYardRPN(myTempList);
		assertTrue(ExpressionParser.getIsValid());
	}

	/**
	 * Test method for {@link model.ExpressionParser#getIsValid()} with the first invalid
	 * infix notation expression being tested.
	 */
	@Test
	void testGetIsValidFirstFalse() {
		myTempList = ExpressionParser.stringToList(myInvalidExp1);
		@SuppressWarnings("unused")
		final ArrayList<String> test = ExpressionParser.shuntingYardRPN(myTempList);
		assertFalse(ExpressionParser.getIsValid());
	}

	/**
	 * Test method for {@link model.ExpressionParser#getIsValid()} with the second invalid
	 * infix notation expression being tested.
	 */
	@Test
	void testGetIsValidSecondFalse() {
		myTempList = ExpressionParser.stringToList(myInvalidExp2);
		@SuppressWarnings("unused")
		final ArrayList<String> test = ExpressionParser.shuntingYardRPN(myTempList);
		assertFalse(ExpressionParser.getIsValid());
	}

}
