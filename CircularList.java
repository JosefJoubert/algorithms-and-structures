
public class CircularList<T> {
	protected Node<T> head;
	public CircularList() {
		head = null;
	}
	
	public boolean isEmpty() {
		return head == null;
	}
	
	public void insert(T el) {
		if (head == null) {
			head = new Node<T>(el);
			return;
		}
		Node<T> temp = head;
		while (temp.next != head) {
			temp = temp.next;
		}
		temp.next = new Node<T>(el,head);
	}
	
	public int size(){
		int itemp=0;
		if (isEmpty()) return 0;
		itemp++;
		Node<T> temp = head;
		while(temp.next != head){
			temp = temp.next;
			itemp++;
		}
		return itemp;
	}
	
	public void insert(int index, T el) {
		
		if(isEmpty()){
			head = new Node<T>(el);	
			return;
		}
		
		Node<T> temp;
		
		if (index==0) {
			temp = new Node<T>(el);
			temp.next = head;
			head = temp;
			return;
		}
		
		int a = size();
		
		if(index == a) {
			temp = head;
			while(temp.next != head) {
				temp = temp.next;
			}
			temp.next = new Node<T>(el,head);
			return;
		}
		
		if (index > a || index < 0) {
			temp = new Node<T>(el);
			temp.next = head;
			head = temp;
			return;
		}
		
		a=index;
		temp = head;
		while(temp.next != head && a > 0){
			temp = temp.next;
			a--;
		}
		
		Node<T> temp2;
		temp2 = new Node<T>(el);
		temp2.next = temp.next;
		temp.next = temp2;
		return;
		
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
		while (temp.next.next != head && a < index) {
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
	
	public Node<T> getLeader(){
		return head;
	}
	
	public void print(){
		if(isEmpty()) {
			System.out.println("Empty structure!");
			return;
		}
		Node<T> temp = head;
		System.out.println(temp.element);
		temp = temp.next;
		while (temp != head) {
			System.out.println(temp.element);
			temp = temp.next;
		}
	}
}
