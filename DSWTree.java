// Name: JH Joubert
// Student number: 12048918


public class DSWTree<T extends Comparable<T>> {

	protected class DSWNode<T> {
	
		public T elem = null;
		public DSWNode<T> left = null;
		public DSWNode<T> right = null;
		
		public DSWNode(T _elem) {
			elem = _elem;
		}
		
		public String toString() {
			String out = elem.toString() + "  ";
			out += "[L: "+ (left == null ? "null" : left.elem.toString()) + "] ";
			out += "[R: "+ (right == null ? "null" : right.elem.toString()) + "] ";
			return out;
		}
	}

	protected DSWNode<T> root = null;
	
	/**
	 * Prints out all the elements in the tree
	 * @param verbose
	 *			If false, the method simply prints out the element of each node in the tree
	 *			If true, then the output provides additional detail about each of the nodes.
	 */
	public void printTree(Boolean verbose) {
		String result;
		result = preorder(root, verbose);
		System.out.println(result);
	}
	
	protected String preorder(DSWNode<T> node, Boolean verbose) {
		if (node != null) {
			String result = "";
			if (verbose) {
				result += node.toString()+"\n";
			} else {
				result += node.elem.toString() + " ";
			}
			result += preorder(node.left, verbose);
			result += preorder(node.right, verbose);
			return result;
		}
		return "";
	}
	
	////// You may not change any code above this line //////

	////// Implement the functions below this line //////
	
	/**
	 * Inserts an element into the tree (as you would into a normal binary search tree)
	 * Should not allow duplicate elements to be inserted into the tree.
	 * @param _elem
	 *			The element to insert into the tree
	 */
	public void insert(T elem) {
		if (root == null){
			root = new DSWNode<T>(elem);
			return;
		}
		DSWNode<T> temp = root;
		while(true){
			if (temp.elem.equals(elem)) return;
			if (temp.elem.compareTo(elem) > 0) {
				if (temp.left == null){
					temp.left = new DSWNode<T>(elem);
					return;
				}
				temp = temp.left;
			}
			else {
				if (temp.right == null){
					temp.right = new DSWNode<T>(elem);
					return;
				}
				temp = temp.right;
			}
		}
	}
	
	/**
	 * Converts the current tree into one that is balanced
	 */
	public void balanceTree() {
		createBackBone();
		printTree(true);
		createPerfectTree();
	}
	
	/**
	 * Converts the tree into a 'backbone'
	 */
	protected void createBackBone() {
		DSWNode<T> temp = root;
		while(temp != null) {
			if (temp.left != null) {
				DSWNode<T> P = findParent(temp);
				rotateRight(findParent(P),P,temp);
				temp = temp.left;
			}
			else temp = temp.right;
		}
	}
	
	/**
	 * Performs the necessary rotations to create a perfect tree (assuming the tree is already a 'backbone')
	 */
	protected void createPerfectTree() {
		int n = countNodes(root);
		int m = (int)((Math.pow(2,Math.log(n+1)) -1));
		for (int i = 0; i < n-m;i++) {
			rotateRight(null,root,root.right);
		}
		while (m > 1) {
			m = m/2;
			for (int i = 0; i < n-m;i++) {
				rotateRight(null,root,root.right);
			}
		}
	}
	
	
	/**
	 * A recursive algorithm that traverses the tree and calculates how many nodes are in the tree
	 * @param node
	 *			The function will calculate how many nodes are contained in the subtree that starts at node.
	 * @param int
	 *			The number of nodes contained in the subtree with root node
	 */
	protected int countNodes(DSWNode<T> node) {
		if (node == null) return 0;
		return countNodes(node.left) + countNodes(node.right) + 1;
	}
	
	/**
	 * Finds and returns the parent of node Q in the tree.
	 * Should return null if no parent of Q exists or if Q is null.
	 * @param Q
	 *			The child node whose parent will be searched for
	 * @return The parent of node Q
	 */
	protected DSWNode<T> findParent(DSWNode<T> Q) {
		if (Q == null) return null;
		if (root == Q) return null;
		DSWNode<T> temp = root;
		while(true){
			if(temp.elem.compareTo(Q.elem) > 0) {
				if (temp.left == null) return null;
				if (temp.left == Q) return temp;
				temp = temp.left;
			}
			else {
				if (temp.right == null) return null;
				if (temp.right == Q) return temp;
				temp = temp.right;
			}
		}
	}
	
	/**
	 * Left rotates a node N about its parent P.
	 * @param Gr
	 *			The grandparent of the node being rotated
	 * @param P
	 *			The parent of the node being rotated
	 * @param C
	 *			The node being rotated about its parent
	 */
	protected void rotateLeft(DSWNode<T> Gr, DSWNode<T> P, DSWNode<T> C) {
		if (Gr == null) root = C;
		else if (Gr.left == P) Gr.left = C;
		else Gr.right = C;
		P.right = C.left;
		C.left = P;
	}
	
	/**
	 * Right rotates a node N about its parent P.
	 * @param Gr
	 *			The grandparent of the node being rotated
	 * @param P
	 *			The parent of the node being rotated
	 * @param C
	 *			The node being rotated about its parent
	 */
	protected void rotateRight(DSWNode<T> Gr, DSWNode<T> P, DSWNode<T> C) {
		if (Gr == null) root = C;
		else if (Gr.left == P) Gr.left = C;
		else Gr.right = C;
		P.left = C.right;
		C.right = P;
		
	}
	
}