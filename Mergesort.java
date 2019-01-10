
import java.util.*;

public class Mergesort<T extends Comparable<? super T>> {

    public Queue<String> queue = new LinkedList<String>();// To collect output from swap method
    private Comparable[] temp;

    //DO NOT MODIFY
    public Mergesort() {
    }

    /**
     * TODO:Merge method.
     */
    private void merge(T[] data, int first, int last) {
        //IMPLEMENT HERE
    	int i1 = 0;
    	int mid = ((first+last)/2);
    	int i3 = mid+1;
    	int i2 = first;
    	T[] arr = (T[])new Comparable[last - first + 1];
    	while(i2 != mid+1 && i3 != last+1){
    		if(data[i2].compareTo(data[i3]) < 0) arr[i1++] = data[i2++];
    		else arr[i1++] = data[i3++];
    	}
    	while (i3 != last+1) arr[i1++] = data[i3++];
    	while(i2 != mid+1) arr[i1++] = data[i2++];
    	i1 = 0;
    	for (int a = first;a!=last+1;a++){
    		data[a] = arr[i1++];
    	}

        //DO NOT MODIFY. Implement merge method above this code.
        collectOutput(data); //store output for printing out array at each merge step
    }

    //DO NOT MODIFY
    /**
     * Enqueues the array step by step output.
     */
    private void collectOutput(T[] data) {
        queue.add(Arrays.toString(data)); //store output for printing out array at each merge step
    }

    /**
     * TODO:Mergesort method.
     */
    
    private void mergeSort(T[] data,int first, int last){
    	if (first < last){
    		int mid = (first+last)/2;
    		mergeSort(data,first,mid);
    		mergeSort(data,mid+1,last);
    		merge(data,first,last);
    	}
    }
    
    public void mergeSort(T[] data) {
    	//if (data == null) return;
        mergeSort(data,0,data.length-1);
    }

    //DO NOT MODIFY
    /**
     * Prints out the output list from the queue.
     */
    @Override
    public String toString() {
        ArrayList<String> list = new ArrayList<String>(queue);
        String out = "";
        for (int i = 0; i < list.size(); i++) {
            out += list.get(i) + "\n";
        }
        return out;
    }

    //DO NOT MODIFY
    /**
     * Returns the final array sorted
     */
    public String sortedArray() {
        ArrayList<String> list = new ArrayList<String>(queue);
        if (!list.isEmpty()) {
            return list.get(list.size() - 1);
        } else {
            return null;
        }
    }
}
