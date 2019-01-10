
import java.lang.reflect.Array;


public class MaxHeap<T extends Comparable<? super T>> {     
    public class Node {
	
		public T elem = null;
		public Node left = null;
		public Node right = null;
        public Node parent = null;
		
		
		public Node(T _elem) {
			elem = _elem;
		}
                
        public void displayInfo()
        {
            System.out.print("Node{Elem:" +elem+ " , Left:"+ (left == null ? "null" : left.elem.toString())+ " , Right:"+ (right == null ? "null" : right.elem.toString())+ " , Parent:"+ (parent == null ? "null" : parent.elem.toString())+ "}");
        }
	}

	protected Node root = null;
    protected T[] Elements;
	protected int size;
        
    public MaxHeap(T[] elements)
	{
		Elements = elements;
        size = elements.length;
	}
       
    public int getSize()
    {
        return size;
    }
        
    public void initialConstruction()
    {	
    	int pos = (getSize())/2 - 1;
    	@SuppressWarnings("unchecked")
    	Node[] arr = (Node[]) Array.newInstance(Node.class, getSize());
    	for (int x=0;x<getSize();x++){
    		arr[x] = new Node(Elements[x]);
    	}
    	
    	if (getSize()%2 != 0){
    		if ((arr[pos].elem.compareTo(arr[pos*2+1].elem) < 0) || (arr[pos].elem.compareTo(arr[pos*2+2].elem) < 0)){
    			Node temp = arr[pos];
    			if (arr[pos*2+1].elem.compareTo(arr[pos*2+2].elem) > 0) {
    				temp = arr[pos];
    				arr[pos] = arr[pos*2+1];
    				arr[pos*2+1] = temp;
    			}
    			else{
    				arr[pos] = arr[pos*2+2];
    				arr[pos*2+2] = temp;
    			}
    		}
    		arr[pos].right = arr[pos*2+2];
    	}
    	else {
    		if (arr[pos].elem.compareTo(arr[pos*2+1].elem) < 0) {
    			Node temp = arr[pos];
    			arr[pos] = arr[pos*2+1];
    			arr[pos*2+1] = temp;
    		}
    	}
    	arr[pos].left = arr[pos*2+1];
    	pos--;
    	for (;pos!=-1;pos--){
    		if ((arr[pos].elem.compareTo(arr[pos*2+1].elem) < 0) || (arr[pos].elem.compareTo(arr[pos*2+2].elem) < 0)){
    			Node temp = arr[pos];
    			if (arr[pos*2+1].elem.compareTo(arr[pos*2+2].elem) > 0) {
    				temp = arr[pos];
    				arr[pos] = arr[pos*2+1];
    				arr[pos*2+1] = temp;
    			}
    			else{
    				arr[pos] = arr[pos*2+2];
    				arr[pos*2+2] = temp;
    			}
    		}
    		arr[pos].left = arr[pos*2+1];
    		arr[pos].right = arr[pos*2+2];
    			
    	}
    	this.root = arr[0];
    	
    }
        
        
        
    public boolean insertNode(T el)
    {
            
            
    }
      
    public T deleteNode()
    {
            
            
    }
        
    public T[] createArray(int size)
    {
       T[] array = (T[]) Array.newInstance(Elements.getClass().getComponentType(), size);
       return array;
    }
    
   
    
        
        
    
    
}
