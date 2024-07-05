/*
 *
 */

package model;

import structures.AETNode;

/**
 *
 *
 *
 *
 */
public class AETEvaluator {

	/** A private constructor to inhibit external instantiation. */
	private AETEvaluator() {
		// do nothing
	}

	public static double evaluateAET(final AETNode theTree) {
		switch (theTree.getValue()) {
			case "-":
				return evaluateAET(theTree.getLeftAETNode()) -
				    evaluateAET(theTree.getRightAETNode());
			case "+":
				return evaluateAET(theTree.getLeftAETNode()) +
				    evaluateAET(theTree.getRightAETNode());
			case "/":
				return evaluateAET(theTree.getLeftAETNode()) /
				    evaluateAET(theTree.getRightAETNode());
			case "*":
				return evaluateAET(theTree.getLeftAETNode()) *
				    evaluateAET(theTree.getRightAETNode());
			case "^":
				return Math.pow(evaluateAET(theTree.getLeftAETNode()),
				    evaluateAET(theTree.getRightAETNode()));
			default:
				return Double.valueOf(theTree.getValue());
		}
	}
}
