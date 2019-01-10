// Name: JH Joubert
// Student number: 12048918

public class SplayTree<T extends Comparable<T>> {

	protected Node root = null;
	
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
	
	protected String preorder(Node<T> node, Boolean verbose) {
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
	* Inserts the given element into the tree, but only if it is not already in the tree.
	* @param elem 
	* 		 	The element to be inserted into the tree
	* @return 
	*			Returns true if the element was successfully inserted into the tree. 
	*			Returns false if elem is already in the tree and no insertion took place.
	*
	*/
	public boolean insert(T elem) {
		if (root == null){
			root = new Node<T>(elem);
			return true;
		}
		Node<T> temp = root;
		while(true){
			if (temp.elem.equals(elem)) return false;
			if (temp.elem.compareTo(elem) > 0) {
				if (temp.left == null){
					temp.left = new Node<T>(elem);
					return true;
				}
				temp = temp.left;
			}
			else {
				if (temp.right == null){
					temp.right = new Node<T>(elem);
					return true;
				}
				temp = temp.right;
			}
		}
	}
	
	/**
	* Checks whether a given element is already in the tree.
	* @param elem 
	* 		 	The element being searched for in the tree
	* @return 
	*			Returns true if the element is already in the tree
	*			Returns false if elem is not in the tree
	*
	*/
	public boolean contains(T elem) {
		Node<T> temp = root;
		while(temp != null){
			if (temp.elem.equals(elem)) return true;
			if (temp.elem.compareTo(elem) > 0) temp = temp.left;
			else temp = temp.right;
		}	
		return false;
	}
	
	/**
	 * Accesses the node containing elem. 
	 * If no such node exists, the node should be inserted into the tree.
	 * If the element is already in the tree, the tree should be splayed so that the accessed node is the new root.
	 * @param elem
	 *			The element being accessed
	 */
	public void access(T elem) {
		if (contains(elem) == false) {
			insert(elem);
			return;
		}
		if (root.elem.equals(elem)) return;
		Node<T> temp = root;
		while(true){
			if (temp.elem.equals(elem)) break;
			if (temp.elem.compareTo(elem) > 0) temp = temp.left;
			else temp = temp.right;
		}
		splay(temp);
	}
	
	/**
	 * Splays the tree using the move-to-root strategy
	 * @param C
	 *			The node that will be the new root
	 */
	protected void splay(Node<T> C) {
		while (root != C) {
			Node<T> p = findParent(C);
			Node<T> Gr = findParent(p);
			if (p.left == C) {
				rotateRight(Gr,p,C);
			}
			else rotateLeft(Gr,p,C);
		}
	}
	
	/**
	 * Finds and returns the parent of node Q in the tree.
	 * Should return null if no parent of Q exists or if Q is null.
	 * @param Q
	 *			The child node whose parent will be searched for
	 * @return The parent of node Q
	 */
	protected Node<T> findParent(Node<T> Q) {
		if (Q == null) return null;
		if (root == Q) return null;
		Node<T> temp = root;
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
	protected void rotateLeft(Node<T> Gr, Node<T> P, Node<T> C) {
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
	protected void rotateRight(Node<T> Gr, Node<T> P, Node<T> C) {
		if (Gr == null) root = C;
		else if (Gr.left == P) Gr.left = C;
		else Gr.right = C;
		P.left = C.right;
		C.right = P;
	}
	
	
}