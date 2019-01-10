// Name: JH Joubert
// Student number: 12048918	

public class ThreadedTree<T extends Comparable<T>> {

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
			out += "[Succ: "+hasSuccessor + "] ";
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
	
	/**
	 * Inserts an element into the threaded tree, making sure to adjust predecessor threads correctly.
	 * Does not allow duplicate elements to be inserted into the tree.
	 * @param _elem
	 *			The element to insert into the tree
	 */
	public void insert(T _elem) {
		TTNode<T> newNode = new TTNode<T>(_elem);
		
		if (root == null) {
			root = newNode;
			return;
		}
		
		TTNode<T> p = root;
		TTNode<T> prev = null;
		
		while (p != null) {
			prev = p;
			if (_elem.compareTo(p.elem) < 0) {
				p = p.left;
			} else if (_elem.compareTo(p.elem) == 0) {
				return;
			}else if (!p.hasSuccessor) {
				p = p.right;
			} else {
				break;
			}
		}
		
		if (_elem.compareTo(prev.elem) < 0) {
			prev.left = newNode;
			newNode.hasSuccessor = true;
			newNode.right = prev;
		} else if (prev.hasSuccessor) {
			newNode.hasSuccessor = true;
			prev.hasSuccessor = false;
			newNode.right = prev.right;
			prev.right = newNode;
		} else {
			prev.right = newNode;
		}
	}
	
	////// You may not change any code above this line //////

	////// Implement the functions below this line //////
	
	/**
	 * Removes an element from the threaded tree, making sure to adjust successor threads correctly.
	 * Use the deletion by merging technique as on pg 249
	 * @param _elem
	 *			The element to remove from the tree
	 */
	public void delete(T _elem) {
		// Provide your implementation here
		TTNode<T> temp, node, p = root, prev = null;
		while (p != null) {
			if (_elem.compareTo(p.elem) == 0) {
				break;
			}
			else if (_elem.compareTo(p.elem) < 0) {
				prev = p;
				p = p.left;
				
			} else if (!p.hasSuccessor) {
				prev = p;
				p = p.right;
				
			} else {
				break;
			}
		}
		
		if (p == null) {
			System.out.println("ERROR - Element not found");
			return;
		}
		
		if (!p.elem.equals(_elem)) {
			System.out.println("ERROR - Element not found");
			return;
		}
		
		
		node = p;
		
		if (node.hasSuccessor) {
			if (prev.right == node) {
				prev.right = node.right;
				prev.hasSuccessor = true;
				return;
			}
			prev.left = null;
			return;
		}
		
		if (node.right == null) {
			if (prev.right == node) {
				prev.right = node.left;
				return;
			}
			prev.left = node.left;
			return;
		}
		
		if (node.left == null) {
			if (prev.right == node) {
				prev.right = node.right;
				return;
			}
			prev.left = node.right;
			return;
		}
		
		temp = node.left;
		while (!temp.hasSuccessor) {
			temp = temp.right;
		}
		
		temp.hasSuccessor = false;
		temp.right = node.right;
		node = node.left;
		
		if(p == root) {
			root = node;
			return;
		}
		
		if (prev.left == p) {
			prev.left = node;
			return;
		}
		
		prev.right = node;
		
		
		
		
		
		
	}
}