
public class LinearStructure<T> {
	protected Node<T> head;
	
	public boolean isEmpty() {
		return head == null;
	}
	
	public LinearStructure() {
		head = null;
	}
	
	public void insert(int index, T element) {
		if(isEmpty()) {
			head = new Node<T>(element);
		}
		
		if(index == 0) {
			Node<T> temp = new Node<T>(element,head);
			head = temp;
			return;
		}
		
		Node<T> temp = head;
		int a = index;
		while (temp.next != null  &&  a > 1) {
			temp = temp.next;
			a--;
		}
		Node<T> temp2 = new Node<T>(element,temp.next);
		temp.next = temp2;
		
	}
	
	public T remove(int index) {
		if (index == 0) {
			T temp = head.element;
			head = head.next;
			return temp;
		}
		
		if(head.next == null){
			T temp = head.element;
			head = null;
			return temp;
		}
		
		Node<T> temp = head;
		int a = 1;
		while (temp.next.next != null && a < index) {
			temp = temp.next;
			a++;
		}
		
		T temp2 = temp.next.element;
		temp.next = temp.next.next;
		return temp2;
	}
	
	public void clear(){
		head = null;
	}
	
	public void print(){
		if(isEmpty()) {
			System.out.println("Empty structure!");
			return;
		}
		Node<T> temp = head;
		System.out.println(temp.element);
		temp = temp.next;
		while (temp != null) {
			System.out.println(temp.element);
			temp = temp.next;
		}
	}
	
}
