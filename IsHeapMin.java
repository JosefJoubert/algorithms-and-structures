
public class IsHeapMin<T extends Comparable<? super T>> {
    
    private T[] heapArray;
    
    public IsHeapMin(T[] data)
    {
        heapArray = data;
    }
    
    public boolean Test(int index)
    {
    	if (index*2 > getSize()) return true;
       if (heapArray[index].compareTo(heapArray[index*2]) > 0) return false;
       if (heapArray[index].compareTo(heapArray[index*2+1]) > 0) return false;
       return true;
    }
    
    public int getSize()
    {
       return heapArray.length;
    }
}
