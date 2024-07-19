/*
 * AETNodeTest - Arithmetic Expression Calculator V2
 */

package tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import structures.AETNode;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * This class tests the AETNode class in the structures package.
 *
 * @author Jacob Klymenko
 * @version 2.0
 */
class AETNodeTest {

	/** An AET node to test. */
	private AETNode myAETNode;

	/**
	 * Creates the myAETNode to hold the following math expression to be further tested:
	 * "containing "3.7 * log_10 ( 8.2 )".
	 */
	@BeforeEach
	void setUp() {
		final AETNode node4 = new AETNode("8.2", null, null);
		final AETNode node3 = new AETNode("log_10", node4, null);
		final AETNode node2 = new AETNode("3.7", null, null);
		myAETNode = new AETNode("*", node2, node3);
	}

	/**
	 * Test method for {@link structures.AETNode#AETNode(java.lang.String, structures.AETNode, structures.AETNode)}.
	 */
	@Test
	void testAETNode() {
		assertNotNull(myAETNode);
		assertEquals("*", myAETNode.getValue());
		assertNotNull(myAETNode.getLeftAETNode());
		assertNotNull(myAETNode.getRightAETNode());
	}

	/**
	 * Test method for {@link structures.AETNode#getValue()}.
	 */
	@Test
	void testGetValue() {
		final AETNode leftRootNode = myAETNode.getLeftAETNode();
		assertEquals("3.7", leftRootNode.getValue());
		final AETNode rightRootNode = myAETNode.getRightAETNode();
		assertEquals("log_10", rightRootNode.getValue());
		assertEquals("8.2", rightRootNode.getLeftAETNode().getValue());
	}

	/**
	 * Testing the left subtree nodes of myAETNode.
	 */
	@Test
	void testLeftAETNodeSubtree() {
		final AETNode leftRootNode = myAETNode.getLeftAETNode();
		assertNotNull(leftRootNode);
		assertNull(leftRootNode.getLeftAETNode());
		assertNull(leftRootNode.getRightAETNode());
	}

	/**
	 * Testing the right subtree nodes of myAETNode.
	 */
	@Test
	void testRightAETNodeSubtree() {
		final AETNode rightRootNode = myAETNode.getRightAETNode();
		assertNotNull(rightRootNode);
		assertNotNull(rightRootNode.getLeftAETNode());
		assertNull(rightRootNode.getRightAETNode());
		assertNull(rightRootNode.getLeftAETNode().getLeftAETNode());
		assertNull(rightRootNode.getLeftAETNode().getRightAETNode());
	}

	/**
	 * Test method for {@link structures.AETNode#toString()}.
	 */
	@Test
	void testToString() {
		final String preOrderString = "* 3.7 null null log_10 8.2 null null null";
		assertEquals(preOrderString, myAETNode.toString());
	}

}
