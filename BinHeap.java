// Name: JH Joubert
// Student number: 12048918

// Provide implementation for the functions where indicated, following the specification for this practical

/**
 * The class definition for a Binary Heap
 */
public class BinHeap<T extends Comparable<? super T>> {

	// The elements of the heap are stored in this list; do not modify!
	List<T> list = new List<T>();


	// TASK 1
	// Implement the following functions:

	/**
	 * Implement this function:
	 *
	 * Given an index of an element in the list,
	 * this function returns the index in the list of that element's parent in the heap.
	 * If the element has no parent (it is the root of the heap), -1 is returned.
	 * If the given index is out of bounds, an IndexOutOfBoundsException is thrown.
	 */
	protected int indexOfParent(int index) throws IndexOutOfBoundsException {
		if(index > list.size()  || index < 0) {
			throw new IndexOutOfBoundsException();
		}
		if (index == 0) return -1;
		return (index-1)/2;
	}

	/**
	 * Implement this function:
	 *
	 * Given an index of an element,
	 * this functions swaps the element up in the heap if necessary,
	 * and is recursively called with the element's new index if necessary to restore the heap property of the heap.
	 * If the index is out of bounds, an IndexOutOfBoundsException is thrown.
	 */
	protected void bubbleUp(int index) throws IndexOutOfBoundsException {
		if(index > list.size()  || index < 0) {
			throw new IndexOutOfBoundsException();
		}
		
		int a = indexOfParent(index);
		if(index == 0 || list.get(index).compareTo(list.get(a)) < 0) return;
		list.swap(index, a);
		bubbleUp(a);
	}

	/**
	 * Implement this function:
	 *
	 * Inserts the given element into the heap.
	 * Insertion is done as follows:
	 * 1. The element is added to the back of the list
	 * 2. The element is bubbled up until the heap property of the heap is restored.
	 */
	public void insert(T el) {
		list.addToTail(el);
		bubbleUp(list.size() -1);
		
	}


	// TASK 2
	// Implement the following functions:

	/**
	 * Implement this function:
	 *
	 * Given the index of an element in the list,
	 * this function returns the index in the list of that element's left child in the heap.
	 * If the element has no child (it is a leaf node), -1 is returned.
	 * If the given index is out of bounds, an IndexOutOfBoundsException is thrown.
	 */
	protected int indexOfLeftChild(int index) throws IndexOutOfBoundsException {
		if(index > list.size()  || index < 0) {
			throw new IndexOutOfBoundsException();
		}
		
		return (index*2)+1;
	}

	/**
	 * Implement this function:
	 *
	 * Given the index of an element in the list,
	 * this function returns the index in the list of that element's right child in the heap.
	 * If the element has no child (it is a leaf node), a NotFoundException is thrown/
	 * If the given index is out of bounds, an IndexOutOfBoundsException is thrown.
	 */
	protected int indexOfRightChild(int index) throws IndexOutOfBoundsException {
		if(index > list.size()  || index < 0) {
			throw new IndexOutOfBoundsException();
		}
		
		return (index*2)+2;
	}

	/**
	 * Implement this function:
	 *
	 * Given an index of an element,
	 * this functions swaps the element down in the heap with its largest child if necessary, (if both are equal, swap with left child)
	 * and is recursively called with the element's new index if necessary to restore the heap property of the heap.
	 * If the index is out of bounds, an IndexOutOfBoundsException is thrown.
	 */
	protected void bubbleDown(int index) throws IndexOutOfBoundsException {
		if(index > list.size()  || index < 0) {
			throw new IndexOutOfBoundsException();
		}
		
		try {
			int a, right = indexOfRightChild(index), left = indexOfLeftChild(index); 
			if (list.get(right).compareTo(list.get(left)) > 0) a = right;
			else a = left;
			list.swap(index, a);
			bubbleDown(a);
			
		}
		
		finally {return;}
		
		
	}

	/**
	 * Implement this function:
	 *
	 * Removes the first occurrence of the given element from the heap.
	 * (Occurrences are ordered by the list)
	 * Removal is performed as follows:
	 * 1. The element is swapped with the last element in the heap
	 * 2. The element is removed from the end of the heap
	 * 3. The old last element, which is now in the removed element's position,
	 *    is bubbled down the heap until the heap property is restored.
	 * If the element is at the end of the list, it can simply be removed.
	 * If the element is not in the list, a NotFoundException is thrown
	 */
	public void remove(T el) {
		int a = 0;
		try {
			a = list.indexOf(el);
		}
		catch (NotFoundException e) {
			System.out.println("Not Found!");
		}
		if(a == list.size()-1) {
			list.removeFromTail();
			return;
		}
		list.swap(a, list.size()-1);
		list.removeFromTail();
		bubbleDown(a);
		
	}

	// TOSTRING
	// You may modify the implementation of toString to aid you in debugging

	/**
	 * This functions prints the contents of the inner list of the heap on a single line.
	 */
	@Override
	public String toString() {
		// replace with your implementation if you wish:
		return list.toString();
	}
};