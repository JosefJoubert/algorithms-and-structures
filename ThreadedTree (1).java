// Name:
// Student number:

public class ThreadedTree<T extends Comparable<T>> {
	//Node
	protected class TTNode<T> {
		public T elem = null;
		public TTNode<T> left = null;
		public TTNode<T> right = null;
		public Boolean hasSuccessor = false;
		
		public TTNode(T _elem) {
			elem = _elem;
		}
		
		public String toString() {
			String out = elem.toString() + "  ";
			out += "[L: "+ (left == null ? "null" : left.elem.toString()) + "] ";
			out += "[R: "+ (right == null ? "null" : right.elem.toString()) + "] ";
			out += "[Succ: " + hasSuccessor + "] ";
			return out;
		}
	}

	protected TTNode<T> root = null;
	
	/**
	 * Prints out all the elements in the threaded tree by making use of the threads.
	 * @param verbose
	 *			If false, the method simply prints out the element of each node in the tree
	 *			If true, then the output provides additional detail about each of the nodes.
	 */
	public void print(Boolean verbose) {
		TTNode<T> prev, p = root;
		String out = "";
		if (p != null) {
			while(p.left != null) {
				p = p.left;
			}
			while (p != null) {
				if (verbose) {
					out += p.toString() + "\n";
				} else {
					out += p.elem.toString() + " ";
				}
				prev = p;
				p = p.right;
				
				if (p != null && !prev.hasSuccessor) {
					while (p.left != null) {
						p = p.left;
					}
				}
			}
		}
		System.out.println(out);
		System.out.println("--------------------------------");
	}
	
	//Do not change anything above this line!
	
	/**
	 * Insert an element into the threaded tree, making sure to adjust predecessor threads correctly.
	 * Do not allow duplicate elements to be inserted into the tree.
	 * @param _elem
	 *			The element to insert into the tree
	 */
	public void insert(T _elem) {
		//Your Code Here
		if (root == null) {
			root = new TTNode<T>(_elem);
			return;
		}
		TTNode<T> prev = root, p = root;
		while (p != null) {
			prev = p;
			if (p.elem.compareTo(_elem) == 0) return;
			if (p.elem.compareTo(_elem) > 0) p = p.left;
			else {
				if (p.hasSuccessor) break;
				else p = p.right;
			}
		}
		TTNode<T> node = new TTNode<T>(_elem);
		if (prev.elem.compareTo(_elem) > 0){
			prev.left = node;
			node.hasSuccessor = true;
			node.right = prev;
		}
		else {
			if (prev.hasSuccessor){
				node.right = prev.right;
				prev.hasSuccessor = false;
				node.hasSuccessor = true;
			}			
			prev.right = node;
		}
		
	}
	
	/**
	 * Remove an element from the threaded tree, making sure to adjust successor threads correctly.
	 * Use the deletion by merging technique as on pg 249
	 * @param _elem
	 *			The element to remove from the tree
	 */
	public void delete(T _elem) {
		//Your Code Here
		if (root == null) return;
		if (root.elem.equals(_elem)) if (root.left == null && root.right == null) {
			root = null;
			return;
		}
		TTNode<T> node = root,prev=root;
		while (node != null) {
			if (node.elem.equals(_elem)) break;
			prev = node;
			if (node.elem.compareTo(_elem) > 0) node = node.left;
			else if (node.hasSuccessor) return;
			else node = node.right;
			
		}
		if (node == null) return;
		if (prev.right == node) {
			if (node.left == null && node.right == null) {
				prev.right = null;
				return;
			}
			if (node.left == null) {
				prev.right = node.right;
				if (node.hasSuccessor) prev.hasSuccessor = true;
				return;
			}
			if (node.right == null){
				prev.right = node.left;
				return;
			}
			TTNode<T> temp = node.left,tempprev = temp;
			while (temp.right != null && temp.hasSuccessor == false) {
				tempprev = temp;
				temp = temp.right;
			}
			temp.right = node.right;
			temp.hasSuccessor = false;
			prev.right = node.left;
		}
		else {
			if (node.left == null && node.right == null) {
				prev.left = null;
				return;
			}
			if (node.left == null && node.hasSuccessor) {
				prev.left = null;
				return;
			}
			if (node.left == null) {
				prev.left = node.right;
				if (node.hasSuccessor) prev.hasSuccessor = true;
				if (root == node) root = node.right;
				return;
			}
			if (node.right == null){
				prev.left = node.left;
				if (root == node) root = node.left;
				return;
			}
			TTNode<T> temp = node.left;
			while (temp.right != null && temp.hasSuccessor == false) {
				temp = temp.right;
			}
			temp.right = node.right;
			temp.hasSuccessor = false;
			if (node == root) root = node.left;
			else prev.left = node.left;
		}
	}
}