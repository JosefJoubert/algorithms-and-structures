
public class PriorityQueue <T extends Comparable<T>> {
	Node<T> elements;
	
	PriorityQueue() {
		elements = null;
	}
	
	PriorityQueue(LinearStructure<T> c) {
		elements = null;
	}
	
	PriorityQueue(PriorityQueue<T> c) {
		elements = c.elements;
	}
	
	public void enqueue(T el) {
		if(elements == null) {
			elements = new Node<T>(el);
			return;
		}
		
		if(elements.element.compareTo(el) < 0) {
			elements = new Node<T>(el,elements);
			return;
		}
		
		Node<T> temp = elements;
		while(temp.next != null) {
			if(temp.next.element.compareTo(el) < 0) {
				temp.next = new Node<T>(el,temp.next);
				return;
			}
			temp = temp.next;
		}
		temp.next = new Node<T>(el,temp.next);
	}
	
	public T dequeue() {
		T temp = elements.element;
		elements = elements.next;
		return temp;
	}
	
	public T next(){
		return elements.element;
	}
	
	public boolean isEmpty() {
		if(elements == null) {
			return true;
		}
		return false;
	}
	
	public void print(){
		if(isEmpty()) {
			System.out.println("Empty structure!");
			return;
		}
		Node<T> temp = elements;
		System.out.println(temp.element);
		temp = temp.next;
		while (temp != null) {
			System.out.println(temp.element);
			temp = temp.next;
		}
	}
}
