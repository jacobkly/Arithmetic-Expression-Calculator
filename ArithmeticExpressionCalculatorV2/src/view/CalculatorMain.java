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
		final ArrayList<String> list = getList(theConsole, prompt);
		// final AETNode tree = ExpressionParser.shuntingYardTree(list);
		// final double output = AETEvaluator.evaluateAET(tree);
		final ArrayList<String> rpn = ExpressionParser.shuntingYardRPN(list);
		final double output = Evaluator.evaluateRPN(rpn);
		reportResult(myUserInput, output);
	}

	private static ArrayList<String> getList(final Scanner theConsole,
	    final String thePrompt) {
		ArrayList<String> list = new ArrayList<>();

		System.out.print(thePrompt);
		myUserInput = theConsole.nextLine();
		list = ExpressionParser.stringToList(myUserInput);

		return list;
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
