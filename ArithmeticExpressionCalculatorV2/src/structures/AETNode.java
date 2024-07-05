/*
 *
 */

package structures;

/**
 * AET --> arithmetic expression tree
 *
 *
 *
 */
public class AETNode {

	private final String myValue;

	private final AETNode myLeftAETNode;

	private final AETNode myRightAETNode;

	public AETNode(final String theValue, final AETNode theLeftAETNode,
	    final AETNode theRightAETNode) {

		this.myValue = theValue;
		this.myLeftAETNode = theLeftAETNode;
		this.myRightAETNode = theRightAETNode;
	}

	public String getValue() {
		return myValue;
	}

	public AETNode getLeftAETNode() {
		return myLeftAETNode;
	}

	public AETNode getRightAETNode() {
		return myRightAETNode;
	}
}
