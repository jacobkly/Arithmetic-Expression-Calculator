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
 * @version 2.2
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
	 * respective value. Both methods of parsing and evaluation are shown to the user.
	 *
	 * @param theConsole a Scanner used to gather user input
	 */
	private static void start(final Scanner theConsole) {
		final String prompt = "\nenter an expression (or \"q\" to quit): ";
		System.out.print(prompt);
		myUserInput = theConsole.nextLine();
		// handles and evaluates the user input using a list and tree.
		final ArrayList<String> rpn = getRPN(theConsole, prompt);
		final AETNode tree = getTree(theConsole, prompt);
		if (myUserQuitOption) {
			return;
		} else {
			try {
				final double listOutput = Evaluator.evaluateRPN(rpn);
				displayResult("List", myUserInput, listOutput);
				final double treeOutput = Evaluator.evaluateAET(tree);
				displayResult("Tree", myUserInput, treeOutput);
			} catch (final Exception error) {
				System.out.println("Evaluation error has occured!");
			}
		}
	}

	/**
	 * Repeatedly waits for an expression in infix notation entered by the user. It first runs
	 * the input through and separates substrings surrounded by space characters to then
	 * correctly feed the user input into the shunting yard algorithm.
	 *
	 * The method also handles any misplaced parentheses by asking the user to input once more.
	 *
	 * @param theConsole a Scanner used to gather user input
	 * @param thePrompt the prompt to display to the user
	 * @return the expression in RPN of the user inputed infix notation expression
	 */
	private static ArrayList<String> getRPN(final Scanner theConsole, final String thePrompt) {
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

	/**
	 * Repeatedly waits for an expression in infix notation to be entered by the user. It
	 * first runs the input through and seperates substring surrounded by space characters to
	 * then correctly feed the user input into the shunting yard algorithm.
	 *
	 * The method also handles any misplaced parentheses by asking the user to input once more.
	 *
	 * @param theConsole a Scanner used to gather user input
	 * @param thePrompt the prompt to display to the user
	 * @return the expression in an AETNode of the user inputed infix notation expression
	 */
	private static AETNode getTree(final Scanner theConsole, final String thePrompt) {
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

	/**
	 * Return true if the user input is an upper or lower case 'q'. Otherwise return false.
	 *
	 * @param theUserInput the user's input
	 * @return true if the user input is an upper or lower case 'q'; otherwise false
	 */
	private static boolean isUserQuitting(final String theUserInput) {
		if (myUserInput.equals("Q") || myUserInput.equals("q")) {
			myUserQuitOption = true;
			return true;
		} else {
			myUserQuitOption = false;
			return false;
		}
	}

	/**
	 * Displays the result of the list and tree evaluation methods for the user inputed
	 * expression. Outputs both the list and tree evaluation lines sequentially.
	 *
	 * @param theEvalMethod the method of evaluation for the user expression
	 * @param theUserInput the user inputed expression
	 * @param theResult the result of the method of evaluation
	 */
	private static void displayResult(final String theEvalMethod, final String theUserInput,
	    final double theResult) {
		if (theEvalMethod == "List") {
			System.out.print("\n");
		}
		System.out.println(theEvalMethod + " Evaluation: " + theUserInput + " = " + theResult);
	}
}
