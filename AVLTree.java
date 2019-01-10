// Name:JH Joubert
// Student number: 12048918

import java.util.ArrayList;

public class AVLTree<T extends Comparable<T>> {

	protected class AVLNode<T> {
	
		public T elem = null;
		public AVLNode<T> left = null;
		public AVLNode<T> right = null;
		public AVLNode<T> parent = null;
		public int balance = 0;
		
		public AVLNode(T _elem, AVLNode<T> _parent) {
			elem = _elem;
			parent = _parent;
		}
		
		public String toString() {
			String out = elem.toString() + "  ";
			out += "[L: "+ (left == null ? "null" : left.elem.toString()) + "] ";
			out += "[R: "+ (right == null ? "null" : right.elem.toString()) + "] ";
			out += "[P: "+ (parent == null ? "null" : parent.elem.toString()) + "] ";
			out += "[B: "+ balance + "] ";
			return out;
		}
	}

	protected AVLNode<T> root = null;
	
	/**
	 * Prints out all the elements in the tree
	 * @param verbose
	 *			If false, the method simply prints out the element of each node in the tree
	 *			If true, then the output provides additional detail about each of the nodes.
	 */
	public void printTree(Boolean verbose) {
		inorder(root, verbose);
	}
	
	protected void inorder(AVLNode<T> node, Boolean verbose) {
		if (node != null) {
			inorder(node.left, verbose);
			if (verbose) {
				System.out.println(node.toString());
			} else {
				System.out.println(node.elem.toString());
			}
			inorder(node.right, verbose);
		}
	}
	
	////// You may not change any code above this line //////

	////// Implement the functions below this line //////
	
	/**
	 * Inserts an element into the AVL tree, making sure to balance if necessary.
	 * Should not allow duplicate elements to be inserted into the tree.
	 * This method should make use of the rotateLeft, rotateRight and updateBalanceFactors functions.
	 * @param _elem
	 *			The element to insert into the tree
	 */
	public void insert(T _elem) {
	
	}
	
	/**
	 * Updates the balance factors of the nodes in the AVL tree (upon inserting node Q).
	 * @param Q
	 *			The node just inserted
	 * @param P
	 *			The parent of the node just inserted
	 * @return 
	 *			The root of the subtree to be rebalanced
	 */
	protected AVLNode<T> updateBalanceFactors(AVLNode<T> Q, AVLNode<T> P) {
		return null;
	}
	
	/**
	 * Left rotates a node N about its parent P.
	 * @param P
	 *			The parent of the node being rotated
	 * @param Q
	 *			The node being rotated about its parent
	 */
	protected void rotateLeft(AVLNode<T> P, AVLNode<T> Q) {
	
	}
	
	/**
	 * Right rotates a node N about its parent P.
	 * @param P
	 *			The parent of the node being rotated
	 * @param Q
	 *			The node being rotated about its parent
	 */
	protected void rotateRight(AVLNode<T> P, AVLNode<T> Q) {
	
	}
	
}