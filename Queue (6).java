
public class Queue<T> {
	protected class QueueNode{
		QueueNode next;
		T value;
		QueueNode(T element) {
			value = element;
			next = null;
		}
	}
	
	QueueNode root;
	Queue() {
		root = null;
	}
	
	public void enqueue(T element) {
		if (root == null) {
			root = new QueueNode(element);
			return;
		}
		QueueNode temp = root;
		while (temp.next != null) {
			temp = temp.next;
		}
		temp.next = new QueueNode(element);
	}
	
	public T dequeue() {
		T temp = root.value;
		root = root.next;
		return temp;
	}
	
	public void clear() {
		root = null;
	}
	
	public boolean isEmpty() {
		if (root == null) return true;
		return false;
	}
}
