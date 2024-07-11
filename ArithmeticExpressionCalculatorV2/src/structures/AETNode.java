/*
 * CalculatorMain - Arithmetic Expression Calculator V2
 */

package structures;

/**
  * An arithmetic expression tree (AET) node class.
  *
  * Created for the future purpose of aiding in the differentiation of expressions.
  *
  * @author Jacob Klymenko
  * @version 1.0
  */
public class AETNode {

	/** The value of the node being examined. */
	private final String myValue;

	/** The left child of the node being examined. */
	private final AETNode myLeftAETNode;

	/** The right child of the node being examined. */
	private final AETNode myRightAETNode;

	/**
	 * Constructs a new AET node. Leaf nodes have null left and right children. As this is for
	 * the purpose of mathematical expressions, each node either has two children nodes or is
	 * a leaf node.
	 *
	 * @param theValue the value of the node
	 * @param theLeftAETNode the left child of the node
	 * @param theRightAETNode the right child of the node
	 */
	public AETNode(final String theValue, final AETNode theLeftAETNode,
	    final AETNode theRightAETNode) {

		myValue = theValue;
		myLeftAETNode = theLeftAETNode;
		myRightAETNode = theRightAETNode;
	}

	/**
	 * Returns the value of the node being examined.
	 *
	 * @return the value of the node being examined
	 */
	public String getValue() {
		return myValue;
	}

	/**
	 * Returns the left child of the node being examined.
	 *
	 * @return the left child of the node being examined
	 */
	public AETNode getLeftAETNode() {
		return myLeftAETNode;
	}

	/**
	 * Returns the right child of the node being examined
	 *
	 * @return the right child of the node being examined
	 */
	public AETNode getRightAETNode() {
		return myRightAETNode;
	}

	@Override
	public String toString() {

		return "";
	}
}
