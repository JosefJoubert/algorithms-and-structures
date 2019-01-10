import java.util.ArrayList;

// This interface defines a sorting algorithm with a ``sort'' method, which returns a sorted version of the given ArrayList object.

public interface Sorter<T extends Comparable<? super T>> {
	abstract ArrayList<T> sort(ArrayList<T> list);
}