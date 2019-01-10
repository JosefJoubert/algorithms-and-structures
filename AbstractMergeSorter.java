import java.util.ArrayList;

// This class defines the outline of a merge sort algorithm.
// MergeSorter is a concrete sub-class of this class which will provide implementation for all abstract methods of this class.
// Do not modify this class.

public abstract class AbstractMergeSorter<T extends Comparable<? super T>> implements Sorter<T>{
	public ArrayList<T> sort(ArrayList<T> list) {
		System.out.println("sort: " + toString(list));
		if (list.size() > 1) {
			ArrayList<T> left = sort(leftHalfOf(list));
			ArrayList<T> right = sort(rightHalfOf(list));
			list = merge(left, right);
			System.out.println("merged: " + toString(list));
		}
		return list;
	}

	String toString(ArrayList<T> list) {
		String s = "[";
		for (T i : list.subList(0, list.size()-1))
			s += i.toString() + ",";
		if (list.size() > 0)
			s += list.get(list.size()-1);
		return s + "]";
	}

	abstract ArrayList<T> leftHalfOf(ArrayList<T> list); 
	abstract ArrayList<T> rightHalfOf(ArrayList<T> list);
	abstract ArrayList<T> merge(ArrayList<T> left, ArrayList<T> right);
}