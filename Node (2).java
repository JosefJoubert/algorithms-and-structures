public class Node<T> {
	public T elem = null;
	public Node<T> left = null;
	public Node<T> right = null;
	
	public Node(T _elem) {
		elem = _elem;
	}
	
	public String toString() {
		String out = elem.toString();
		out += " [L: "+ (left == null ? "null" : left.elem) + "] ";
		out += " [R: "+ (right == null ? "null" : right.elem) + "] ";
		return out;
	}
}