/*
 * CalculatorMain - Derivate Calculator Project
 */

package view;

import java.util.Scanner;

/**
 * A program to perform basic calculations of arithmetic expressions through console. In more
 * elementary terms, this program performs calculations according to the PEMDAS (or BEDMAS or
 * PE(MD)AS) rule.
 *
 * @author Jacob Klymenko
 * @version 1.1
 */
public final class CalculatorMain {

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
		displayIntro();
		try (Scanner console = new Scanner(System.in)) {
			for (;;) { // Loops until the user decides to quit.
				start(console);
				if (!startAgain(console)) {
					break;
				}
			}
		}
		displayCon();
	}

	/**
	 * Displays an introduction and explanation of the program to the user.
	 */
	private static void displayIntro() {
		System.out.println("This program calculates simple arithmetic expressions!");
		System.out.println("Once you are finished, please follow the exiting instructions.");
	}

	/**
	 * Starts the first round of evaluating an arithmetic expression into its respective value.
	 *
	 * @param theConsole a Scanner used to gather user input
	 */
	private static void start(final Scanner theConsole) {
		final String prompt = "\nPlease enter an arithmetic expression to evaluate: ";
		final String input = getString(theConsole, prompt);
		final int output = 0;
		reportResult(input, output);
	}

	/**
	 * Repeatedly waits for an arithmetic expression to be entered. If the input is not a valid
	 * arithmetic expression, the user is prompted once more.
	 *
	 * @param theConsole a Scanner used to gather user input
	 * @param thePrompt the prompt to display
	 * @return the arithmetic expression string entered by the user
	 */
	private static String getString(final Scanner theConsole, final String thePrompt) {
		System.out.print(thePrompt);

		// Will possibly need a way to check if expression is valid here.
		// That will be the condition inside the while loop.
		while (!theConsole.hasNext()) {
			theConsole.next();
			System.out.println("Not a valid arithmetic expression. Please try again.");
			System.out.print(thePrompt);
		}

		return theConsole.next();
	}

	/**
	 * Reports the user entered expression and resulting value.
	 *
	 * @param theInput the user entered expression
	 * @param theOutput the value of the expression
	 */
	private static void reportResult(final String theInput, final int theOutput) {
		System.out.println("\n" + theInput + " = " + theOutput);
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

			final String again = theConsole.next();
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

	/**
	 * Displays a conclusion message to the user.
	 */
	private static void displayCon() {
		System.out.println("\nThank you for trying this program and have a wonderful day!");
	}

}
