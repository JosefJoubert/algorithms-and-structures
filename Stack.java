
public class Stack<T> {
	protected Node<T> head;
	
	public Stack(LinearStructure<T> initializeQueue) {
		head = null;
	}
	
	public Stack(Stack<T> c) {
		head = c.head;
	}
	
	public T pop() {
		T temp = head.element;
		head = head.next;
		return temp;
	}
	
	public T top() {
		
		return head.element;
	}
	
	public void push(T el) {
		head = new Node<T>(el,head.next);
	}
	
	public boolean isEmpty() {
		if (head == null) {
			return true;
		}
		return false;
	}
	
	public int size() {
		Stack<T> temp = new Stack<T>(this);
		int a = 0;
		while (!temp.isEmpty()) {
			a++;
			temp.pop();
		}
		return a;
	}
}
