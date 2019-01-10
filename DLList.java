public class DLList<T extends Comparable<T>> {
	
	protected DLNode head = null;
	protected DLNode tail = null;
	
	/*
	This method should create a new node with storing "value" and add it to the end of the list.
	*/
	public void addToHead(T value) {
		DLNode temp = new DLNode(value);
		if (isEmpty()){
			this.head = temp;
			this.tail = temp;
			return;
		}
		temp.next = this.head;
		this.head.prev = temp;
		this.head = temp;
	}
	
	/*
	Inserts an element to the tail of the list.
	*/
	public void addToTail(T value) {
		DLNode temp = new DLNode(value);
		if (isEmpty()){
			this.head = temp;
			this.tail = temp;
			return;
		}
		this.tail.next = temp;
		temp.prev = this.tail;
		this.tail = temp;
	}

	
	public void removeHead() throws EmptyListException {
		if (isEmpty()) 	throw new EmptyListException();
		if (head == tail) {
			head = null;
			tail = null;
			return;
		}
		this.head = this.head.next;
		this.head.prev = null;
		
		
	}
	
	public void removeTail() throws EmptyListException  {
		if (isEmpty()) throw new EmptyListException();
		if (head == tail) {
			head = null;
			tail = null;
			return;
		}
		this.tail = this.tail.prev;
		this.tail.next = null;
	}
	
	public void remove(T value) throws EmptyListException  {
		if (isEmpty()) throw new EmptyListException();
		DLNode node = this.head;
		while (node != null) {
			if (node.value == value) {
				node.prev.next = node.next;
				node.next.prev = node.prev;
				return;
			}
			node = node.next;
		}
	}
	
	public boolean isEmpty() {
		if(this.head == null) return true;
		return false;
	}
	
	public int getCount() {
		if (isEmpty()) return 0;
		DLNode node = this.head;
		int x = 0;
		while (node != null) {
			x++;
			node = node.next;
		}
		return x;
	}
	
	public int getPosition(T value) {
		int x = 0;
		DLNode node = head;
		while (node != null) {
			if (node.value.equals(value)) {
				return x;
			}
			x++;
			node = node.next;
		}
		return -1;
	}
	
	public void printList(boolean verbose) {
		DLNode iter = head;
		String out = "";
		if (verbose) {
			out += " H ="+ (head == null ? " null"  : head.getString()) + "\n";
			out += " T =" +(tail == null ? " null"  : tail.getString()) + "\n";
		}		
		 else {
			 out += "[";
		 }
		while (iter != null) {
			if (verbose) {
				out += iter.getStringVerbose() + (iter == tail ? "" : "\n");
			} else {
				out += iter.getString();
			}
			iter = iter.next;
		}
		if (!verbose) {
			out += "]";	
		}
		System.out.println(out);
	}
	
	protected class DLNode {
		T value;
		DLNode next = null; 
		DLNode prev = null;
		
		DLNode(T _value) {
			value = _value;
		}
		
		String getStringVerbose() {
			return " ("+value+", next = "+(next == null ? "null" : next.value)+", prev = "+(prev == null ? "null" : prev.value)+") ";
		}
		
		String getString() {
			return " ("+value+") ";
		}
	}
}