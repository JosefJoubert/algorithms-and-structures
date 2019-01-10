import java.util.ArrayList;
import java.util.Collections;

// This class represents a recursive implementation of the bubble sort 2 algorithm.
// See the spec for implementation instructions.
// Also pay attention to the instructions regarding the given output lines, as given in the comments.

public class RecursiveBubbleSorter<T extends Comparable<? super T>> implements Sorter<T> {
	public ArrayList<T> sort(ArrayList<T> list) {
		System.out.println("sorting: " + toString(list)); // LEAVE THIS AS THE FIRST LINE OF THE FUNCTION
		
		Boolean again = false;
		if (list.size() == 1) return list;
		for (int j = list.size()-1; j>0;j--) {
			if (list.get(j).compareTo(list.get(j-1)) < 0) {
				Collections.swap(list, j, j-1);
				again = true;
			}
		}
		ArrayList<T> temp = new ArrayList<T>();
		if (again) {
			for (int j = 1; j < list.size();j++) {
				temp.add(list.get(j));
			}
			temp = sort(temp);
			for (int j=0;j < temp.size();j++) {
				list.set(j+1, temp.get(j));
			}
		}
		

		System.out.println("sorted: " + toString(list)); // LEAVE THIS AS THE 2nd-TO_LAST LINE OF THE FUNCTION, followed only by the return statement.
		return list; // LEAVE THIS AS THE LAST LINE OF THE FUNCTION. (Assign the sorted list to list before returning.)
	}


	// Do not modify this function
	String toString(ArrayList<T> list) {
		String s = "[";
		for (T i : list.subList(0, list.size()-1))
			s += i.toString() + ",";
		if (list.size() > 0)
			s += list.get(list.size()-1);
		return s + "]";
	}
}