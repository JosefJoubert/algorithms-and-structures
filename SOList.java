//Self-Organizing List
public class SOList<T extends Comparable<T>> extends DLList<T> {
	
	public void access(T value) {
		if (this.isEmpty()) {
			this.addToTail(value);
			return;
		}
		if (head.value.equals(value)) return;
		DLNode node = this.head.next;
		while (node != null) {
			if (node.value.equals(value)) {
				DLNode node2= node.prev;
				if (node.next != null) {
					node.next.prev = node2;					
				}
				node2.next = node.next;
				node.next = node2;
				node.prev = node2.prev;
				if (node2.prev != null) {
					node2.prev.next = node;
				}
				node2.prev = node;
				if (this.tail == node) this.tail = node2;
				if (this.head == node2) this.head = node;
				return;
			}
			node = node.next;
		}
		this.addToTail(value);
	}
}