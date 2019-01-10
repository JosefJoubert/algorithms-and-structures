// Leave this file alone!
// No changes you make will be taken into consideration during assessment.
// Feel free to take a look at the interface (the functions available to call) and the implementation

/**
 * The class definition for a singly-linked list.
 *
 * Note the generic type of the class.
 * The class can be instantiated for any type T, as long as T extends Comparable.
 * Moreover, Comparable is also a generic class that can be instantiated for any type, as long as that type is a sub-class of T.
 */
public class List <T extends Comparable<? super T>> {

	/**
	 * The class definition for nodes used in this list
	 */
	class Node<T extends Comparable<? super T>> {
		T data;
		Node<T> next;
		public Node(T d) {
			data = d;
			next = null;
		}
	};

	Node<T> head = null;

	/**
	 * Adds the given element at the end of the list
	 */
	public void addToTail(T d) {
		if (head == null)
			head = new Node<T>(d);
		else {
			Node<T> n = head;
			while (n.next != null)
				n = n.next;
			n.next = new Node<T>(d);
		}
	}

	/**
	 * Removes the element that is at the end of the list.
	 * If the list is empty, throws an IndexOutOfBoundsException.
	 */
	public void removeFromTail() throws IndexOutOfBoundsException {
		if (head == null)
			throw new IndexOutOfBoundsException();
		if (head.next == null)
			head = null;
		else {
			Node<T> n = head;
			while (n.next.next != null)
				n = n.next;
			n.next = null;
		}
	}

	/**
	 * Returns the element at the given index.
	 * If the index is invalid, an IndexOutOfBoundsException is thrown.
	 */
	public T get(int index) throws IndexOutOfBoundsException {
		if (head == null || index < 0)
			throw new IndexOutOfBoundsException();
		int i = 0;
		Node<T> n = head;
		while (i < index && n != null) {
			n = n.next;
			i++;
		}
		if (n == null)
			throw new IndexOutOfBoundsException();
		return n.data;
	}

	/**
	 * Returns the index in the list of the first occurrence, if any, of the given element.
	 * If the element is not in the list, a NotFoundException is thrown.
	 */
	public int indexOf(T el) throws NotFoundException {
		Node<T> n = head;
		int index = 0;
		while (n != null) {
			if (n.data.equals(el))
				return index;
			n = n.next;
			index++;
		}
		throw new NotFoundException();
	}



	/**
	 * Returns a count of the number of elements in the list.
	 */
	public int size() {
		int size = 0;
		Node<T> n = head;
		while (n != null) {
			n = n.next;
			size++;
		}
		return size;
	}

	/**
	 * Returns true of the list does not contain any items
	 */
	public boolean isEmpty() {
		return head == null;
	}

	/**
	 * Swaps the element at the given left index with the element at the given right index.
	 * If either of the given indices are invalid, an IndexOutOfBoundsException is thrown.
	 */
	public void swap(int lIndex, int rIndex) throws IndexOutOfBoundsException{
		if (0 > lIndex || lIndex >= size() || 0 > rIndex || rIndex >= size())
			throw new IndexOutOfBoundsException();
		Node<T> l = head, r = head;
		for (int il = 0; il < lIndex;il++)
			l = l.next;
		for (int ir = 0; ir < rIndex; ir++)
			r = r.next;
		T tmpData = l.data;
		l.data = r.data;
		r.data = tmpData;
	}

	/**
	 * Prints the contents of the list.
	 */
	@Override
	public String toString() {
		Node<T> n = head;
		String s = "";
		while (n != null) {
			s += n.data + " ";
			n = n.next;
		}
		return s;
	}
};