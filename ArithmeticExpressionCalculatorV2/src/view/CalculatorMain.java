/*
 *
 */

package view;

import java.util.ArrayList;
import java.util.Scanner;
import model.Evaluator;
import model.ExpressionParser;

/**
 *
 *
 *
 *
 */
public final class CalculatorMain {

	/** The user inputed infix notation arithmetic expression. */
	private static String myUserInput = "";

	/** A private constructor to inhibit external instantiation. */
	private CalculatorMain() {
		// do nothing
	}

	public static void main(final String[] theArgs) {
		System.out.println("there was a beginning...");
		ExpressionParser.initializeFunctions();
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

	private static void start(final Scanner theConsole) {
		final String prompt = "\nEnter an expression: ";
		// final AETNode tree = ExpressionParser.shuntingYardTree(list);
		// final double output = AETEvaluator.evaluateAET(tree);
		final ArrayList<String> rpn = getRPN(theConsole, prompt);
		try {
			final double output = Evaluator.evaluateRPN(rpn);
			reportResult(myUserInput, output);
		} catch (final Exception error) {
			System.out.println("Evaluation error has occured!");
		}
	}

	// private static ArrayList<String> getList(final Scanner theConsole,
	// final String thePrompt) {
	// ArrayList<String> list = new ArrayList<>();
	//
	// System.out.print(thePrompt);
	// myUserInput = theConsole.nextLine();
	// list = ExpressionParser.stringToList(myUserInput);
	//
	// return list;
	// }

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

	private static void reportResult(final String theExpression, final double theOutput) {
		System.out.println("\n" + theExpression + " = " + theOutput);
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
