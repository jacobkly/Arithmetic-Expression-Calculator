/*
 * CalculatorMain - Arithmetic Expression Calculator V2
 */

package view;

import java.util.ArrayList;
import java.util.Scanner;
import model.Evaluator;
import model.ExpressionParser;

/**
 * A console-based program to perform arithmetic expression calculations. It performs the
 * calculations according to the PEMDAS (or BEMDAS or BODMAS) rule. This program allows both
 * integer and decimal numbers, along with simple logarithmic and trigonometric functions, and
 * the following five operators: -, +, /, *, ^.
 *
 * To see how to correctly use this calculator, please read the GitHub README.md.
 *
 * @author Jacob Klymenko
 * @version 2.0
 */
public final class CalculatorMain {

	/** The user inputed infix notation arithmetic expression. */
	private static String myUserInput = "";

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
				if (!startAgain(console)) {
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
		final String prompt = "\nEnter an expression: ";
		// final AETNode tree = ExpressionParser.shuntingYardTree(list);
		// final double output = AETEvaluator.evaluateAET(tree);
		final ArrayList<String> rpn = getRPN(theConsole, prompt);
		try {
			final double output = Evaluator.evaluateRPN(rpn);
			// Reports the user entered expression and resulting value.
			System.out.println("\n" + myUserInput + " = " + output);
		} catch (final Exception error) {
			System.out.println("Evaluation error has occured!");
			// System.err.println(error);
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
		ArrayList<String> list = ExpressionParser.stringToList(myUserInput);
		ArrayList<String> rpn = ExpressionParser.shuntingYardRPN(list);

		while (!ExpressionParser.getIsValid()) {
			System.out.println("Not a valid arithmetic expression. \nYour input may " +
			    "contain misplaced parentheses. \n\nPlease try again.");
			System.out.print(thePrompt);
			myUserInput = theConsole.nextLine();
			list = ExpressionParser.stringToList(myUserInput);
			rpn = ExpressionParser.shuntingYardRPN(list);
		}

		return rpn;
	}

	/**
	 * Asks if the user wants to evaluate another arithmetic expression and returns true/false
	 * depending on the user's answer.
	 *
	 * @param theConsole a Scanner used to gather user input
	 * @return true if the user wants to evaluate another expression; false otherwise
	 */
	private static boolean startAgain(final Scanner theConsole) {
		boolean response = false;

		while (true) {
			System.out.print("\nDo you want to evaluate another arithmetic expression? Y/N: ");

			final String again = theConsole.nextLine();
			if ("Y".equalsIgnoreCase(again) || "YES".equalsIgnoreCase(again)) {
				response = true;
				break;
			} else if ("N".equalsIgnoreCase(again) || "NO".equalsIgnoreCase(again)) {
				response = false;
				break;
			} else {
				System.out.println("I did not understand your answer.");
			}
		}

		return response;
	}
}
