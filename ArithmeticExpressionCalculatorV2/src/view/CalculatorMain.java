/*
 *
 */

package view;

import java.util.ArrayList;
import java.util.Scanner;
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
		reportResult(myUserInput);
	}

	private static ArrayList<String> getList(final Scanner theConsole,
	    final String thePrompt) {
		ArrayList<String> result = new ArrayList<>();

		System.out.print(thePrompt);
		myUserInput = theConsole.nextLine();
		result = ExpressionParser.stringToList(myUserInput);
		// System.out.println(result); // test to see the converted list

		return result;
	}

	private static void reportResult(final String theExpression) {
		System.out.println("\n" + theExpression);
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
