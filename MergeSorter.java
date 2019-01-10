import java.util.ArrayList;

// This class is a concrete sub-class of AbstractMergeSorter.
// The abstract functions of AbstractMergeSorter have concrete implementations here.
// Your task is to implement all functions in this class.

public class MergeSorter<T extends Comparable<? super T>> extends AbstractMergeSorter<T> {
	
	// Returns a (new) ArrayList object containing the elements of the left half of the given list.
	// Take care to divide the given list exactly according to the spec!
	ArrayList<T> leftHalfOf(ArrayList<T> list) {
		ArrayList<T> temp = new ArrayList<T>();
		for (int i = 0; i < (list.size()-1)/2; i++) {
			temp.add(list.get(i));
		}
		return temp;
	}

	// Returns a (new) ArrayList object containing the elements of the right half of the given list.
	// Take care to divide the given list exactly according to the spec!
	ArrayList<T> rightHalfOf(ArrayList<T> list) {
		ArrayList<T> temp = new ArrayList<T>();
		for (int i = (list.size()-1)/2 +1; i < list.size(); i++) {
			temp.add(list.get(i));
		}
		return temp;
	}

	// Merges the elements of the given sorted lists to create a new sorted list.
	ArrayList<T> merge(ArrayList<T> left, ArrayList<T> right) {
		ArrayList<T> temp = new ArrayList<T>();
		int i = 0,j = 0;
		while (true) {
			if (i == left.size() || j == right.size()) break;
			if (left.get(i).compareTo(right.get(j)) < 0) {
				temp.add(left.get(i++));
			}
			else {
				temp.add(right.get(j++));
			}
		}
		if (i == left.size()-1) {
			while (j != right.size()) {
				temp.add(right.get(j++));
			}
		}
		
		return temp;
	}
}