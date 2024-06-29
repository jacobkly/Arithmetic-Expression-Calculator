/*
 * CalculatorMain - Arithmetic Calculator Program
 */

package view;

import java.util.Scanner;
import model.ExpressionParser;
import model.PostfixEvaluator;

/**
 * A program to perform basic calculations of arithmetic expressions through console. In more
 * elementary terms, this program performs calculations according to the PEMDAS (or BEDMAS or
 * PE(MD)AS) rule. This program is only able to calculate single digit integers and the
 * following five operators: -, +, /, *, ^.
 *
 * @author Jacob Klymenko
 * @version 1.2
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
	 * Starts the first round of asking for and evaluating an arithmetic expression into its
	 * respective value.
	 *
	 * @param theConsole a Scanner used to gather user input
	 */
	private static void start(final Scanner theConsole) {
		final String prompt = "\nEnter an arithmetic expression to evaluate: ";
		final String rpn = getRPN(theConsole, prompt);
		try {
			final double output = PostfixEvaluator.evaluate(rpn);
			reportResult(output);
		} catch (Exception error) {
			System.out.println("Error has occured! Invalid input...");
		}

	}

	/**
	 * Repeatedly waits for an arithmetic infix expression to be entered by the user. It
	 * evaluates the first input, and if it's not a valid expression (contains misplaced
	 * parentheses), the user is prompted once more. In the end, it returns the post-fix
	 * notation (RPN) of the user inputed infix notation expression.
	 *
	 * @param theConsole a Scanner used to gather user input
	 * @param thePrompt the prompt to display to the user
	 * @return the post-fix notation (RPN) of the user inputed infix notation expression
	 */
	private static String getRPN(final Scanner theConsole, final String thePrompt) {
		System.out.print(thePrompt);
		myUserInput = theConsole.next();
		String rpn = ExpressionParser.shuntingYard(myUserInput);

		while (!ExpressionParser.getIsValid()) {
			System.out.println("Not a valid arithmetic expression. Please try again.");
			System.out.print(thePrompt);
			myUserInput = theConsole.next();
			rpn = ExpressionParser.shuntingYard(myUserInput);
		}

		return rpn;
	}

	/**
	 * Reports the user entered expression and resulting value.
	 *
	 * @param output the value of the user inputed arithmetic expression
	 */
	private static void reportResult(final double output) {
		System.out.println("\n" + myUserInput + " = " + output);
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
