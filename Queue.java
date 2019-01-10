
public class Queue<T> {
	
	protected Node<T> head;
	
	public T dequeue() {
		T temp = head.element;
		head = head.next;
		return temp;
	}
	
	public void enqueue(T el) {
		Node<T> temp = head;
		while (temp.next != null) temp = temp.next;
		temp.next = new Node<T>(el);
	}
	
	public boolean isEmpty() {
		return head == null;
	}
	
	public Queue(LinearStructure<T> c) {
		head = c.head;
	}
	
	public Queue(Queue<T> c) {
		head = c.head;
	}
}
