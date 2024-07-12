/*
 * CalculatorMain - Arithmetic Expression Calculator V2
 */

package view;

import java.util.ArrayList;
import java.util.Scanner;
import model.Evaluator;
import model.ExpressionParser;
import structures.AETNode;

/**
 * A console-based program to perform arithmetic expression calculations. It performs the
 * calculations according to the PEMDAS (or BEMDAS or BODMAS) rule. This program allows both
 * integer and decimal numbers, along with simple logarithmic and trigonometric functions, and
 * the following five operators: -, +, /, *, ^.
 *
 * To see how to correctly use this calculator, please read the GitHub README.md when
 * instructions for calculator V2 are out.
 *
 * @author Jacob Klymenko
 * @version 2.1
 */
public final class CalculatorMain {

	/** The user inputed infix notation arithmetic expression. */
	private static String myUserInput = "";

	private static boolean myUserQuitOption = false;

	/** A private constructor to inhibit external instantiation. */
	private CalculatorMain() {
		// do nothing
	}

	/**
	 * The start point for this program.
	 *
	 * @param theArgs command line arguments - ignored
	 */
	public static void main(final String[] theArgs) {
		System.out.println("there was a beginning...");
		ExpressionParser.setValidFunctions();
		try (Scanner console = new Scanner(System.in)) {
			for (;;) {
				start(console);
				if (myUserQuitOption) {
					break;
				}
			}
		}
		System.out.println("\nand the end...");
	}

	/**
	 * Starts the first round of asking for and evaluating an arithmetic expression into its
	 * respective value.
	 *
	 * @param theConsole a Scanner used to gather user input
	 */
	private static void start(final Scanner theConsole) {
		final String prompt = "\nenter an expression (or \"Q\" to quit): ";

		// final ArrayList<String> rpn = getRPN(theConsole, prompt);
		// try {
		// final double output = Evaluator.evaluateRPN(rpn);
		// // Reports the user entered expression and resulting value.
		// System.out.println("\n" + myUserInput + " = " + output);
		// } catch (final Exception error) {
		// System.out.println("Evaluation error has occured!");
		// }

		final AETNode tree = getTree(theConsole, prompt);

		if (myUserQuitOption) {
			return;
		} else {
			try {
				final double output = Evaluator.evaluateAET(tree);
				System.out.println("\n" + myUserInput + " = " + output);
			} catch (final Exception error) {
				System.out.println("evaluation error has occured!");
			}
		}
	}

	/**
	 * Repeatedly waits for an expression in infix notation entered by the user. It first runs
	 * the input through and separates substrings surrounded by space characters. This is to
	 * correctly feed the user input into the shunting yard parsing algorithm.
	 *
	 * To handled potential misplaced parentheses, it evaluates the first input, and if the
	 * expression contains misplaced parentheses, the user is prompted once more. Otherwise,
	 * it returns the expression in RPN of the user inputed infix notation expression.
	 *
	 * @param theConsole a Scanner used to gather user input
	 * @param thePrompt the prompt to display to the user
	 * @return the expression in RPN of the user inputed infix notation expression
	 */
	private static ArrayList<String> getRPN(final Scanner theConsole, final String thePrompt) {
		System.out.print(thePrompt);
		myUserInput = theConsole.nextLine();

		if (isUserQuitting(myUserInput)) {
			return new ArrayList<String>();
		}

		ArrayList<String> list = ExpressionParser.stringToList(myUserInput);
		ArrayList<String> rpn = ExpressionParser.shuntingYardRPN(list);
		while (!ExpressionParser.getIsValid()) {
			System.out.println("Not a valid arithmetic expression. \nYour input may " +
			    "contain misplaced parentheses. \n\nPlease try again.");
			System.out.print(thePrompt);
			myUserInput = theConsole.nextLine();
			if (isUserQuitting(myUserInput)) {
				return new ArrayList<String>();
			}
			list = ExpressionParser.stringToList(myUserInput);
			rpn = ExpressionParser.shuntingYardRPN(list);
		}
		return rpn;
	}

	private static AETNode getTree(final Scanner theConsole, final String thePrompt) {
		System.out.print(thePrompt);
		myUserInput = theConsole.nextLine();

		if (isUserQuitting(myUserInput)) {
			AETNode quit = null;
			return quit;
		}

		ArrayList<String> list = ExpressionParser.stringToList(myUserInput);
		AETNode tree = ExpressionParser.shuntingYardTree(list);
		while (!ExpressionParser.getIsValid()) {
			System.out.println("Not a valid arithmetic expression. \nYour input may " +
			    "contain misplaced parentheses. \n\nPlease try again.");
			System.out.print(thePrompt);
			myUserInput = theConsole.nextLine();
			if (isUserQuitting(myUserInput)) {
				AETNode quit = null;
				return quit;
			}
			list = ExpressionParser.stringToList(myUserInput);
			tree = ExpressionParser.shuntingYardTree(list);
		}
		return tree;
	}

	private static boolean isUserQuitting(final String theUserInput) {
		if (myUserInput.equals("Q") || myUserInput.equals("q")) {
			myUserQuitOption = true;
			return true;
		} else {
			myUserQuitOption = false;
			return false;
		}
	}
}
