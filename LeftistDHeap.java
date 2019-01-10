import java.lang.reflect.Array;


/*
Complete your details...
Name and Surname: JH Joubert
Student/staff Number: 12048918
*/


/*You must implement this class to be a Max leftist d-heap, with the largest element in the root, for any d.  That is, every node should contain an element which is larger
or equal to that of any of its descendants*/
public class LeftistDHeap<T extends Comparable<? super T>>
{
	/*
	1. You must design and code your own "merge" procedure/method...
	2. You must create your own node class.
	3. You may add any additional methods or fields that you may require BUT do
	 not remove or change anything for the constructor and methods you where given.
	*/
	
	public Node root;
	int d,elements;
	
	public class Node {
		int npl;
		T element;
		Node[] children;
		public Node(int c, T elem) {
			element = elem;
			
			this.children = (Node[]) new LeftistDHeap.Node[c];
		}	
	}
	
	public class Queue
	{
	    protected QueueNode front;
	    protected int size;
	    
	    public class QueueNode
	    {
	        
	        public QueueNode next;
	        public Node contains;
	        
	        
	        public QueueNode(Node node) {
	        	
	        	next = null;
	        	contains = node;
	        }
	    }
	    
	    public Queue()
	    {
	        
	    	front = null;
	    	size = 0;
	    }
	   
	    
	    public void enqueue(Node node)
	    {
	    	elements++;
	    	if (isEmpty()) {
	    		front = new QueueNode(node);
	    		front.contains = node;
	    		return;
	    	}
	    	QueueNode temp = front;
	    	
	    	while (temp.next != null) {
	    		temp = temp.next;
	    	}
	    	temp.next = new QueueNode(node);
	    	temp.next.contains = node;
	    	
	    	
	    }
	    
	    public Node dequeue()
	    {
	        
	         if (isEmpty()) return null;
	         elements--;
	         QueueNode temp = front;
	         front = front.next;
	         return temp.contains;
	         
	         
	    }
	    
	    
	    public boolean isEmpty()
	    {
	        if (front == null) return true;
	        return false;
	    }
	}
	
	public void restoreLeft(Node node) {
		if (node == null) return;
		for (int a=0;a < d;a++) {
			for (int b=a+1;b<d+1;b++) {
				if (getNpl(node.children[a]) < getNpl(node.children[b])) {
					if (node.children[a] != null) {
						Node temp2 = node.children[a];
						node.children[a] = node.children[b];
						node.children[b] = temp2;
					}
					else {
						node.children[a] = node.children[b];
						node.children[b] = null;
					}
				}
			}
		}
	}
	
	
	public int getNpl(Node node) {
		if (node == null) return -1;
		else return node.npl;
	}
	
	public void setNpl(Node node) {
		if(node== null) return;
		setNpl(node.children[0]);
		int temp = getNpl(node.children[0]);
		
		for (int a=d; a > 0;a--) {
			setNpl(node.children[a]);
			if (getNpl(node.children[a]) < temp) temp = getNpl(node.children[a]);
		}
		node.npl = temp + 1;
		restoreLeft(node);
	}
	
	public Node merge(Node[] nodes) {
		int temp2 = 0;
		if (nodes[0] == null) return null;
		for (int a = 1;a<d+1;a++) {
			if (nodes[a] == null) continue;
			if (nodes[a].element.compareTo(nodes[temp2].element) > 0) temp2 = a;
		}
		for (int a = 0;a<d+1;a++) {
			if (temp2 == a) continue;
			nodes[temp2].children[d] =  merge2(nodes[a],nodes[temp2].children[d]);
			setNpl(nodes[temp2]);
		}
		return nodes[temp2];
	}
	
	public Node merge2(Node node1, Node node2) {
		if (node1 == null) return node2;
		if (node2 == null) return node1;
		if(node1.element.compareTo(node2.element) > 0) {
			node1.children[d] = merge2(node1.children[d],node2); 
			return node1; 
		}
		node2.children[d] = merge2(node2.children[d],node1);
		return node2;
	}
	
	public LeftistDHeap(int e)
	{
		if (e<2) {
			System.out.println("Error! Must have more than two children!");
			System.exit(1);
		}
		d = e-1;
		root = null;
		elements = 0;
	}
		/*
		The parameter d indicates the maximum number of
		children that any node is allowed to have. Nodes may have any number
		of children from 2 upwards.
		*/
	
	
	public void enqueue(T element)
	{
		/*
		Insert the element into the heap.
		*/ 
		if (root == null) {
			root = new Node(d+1,element);
			return;
		}
		Node temp = root;
		while(temp.element.compareTo(element) > 0) {
			if (temp.children[d] == null) {
				temp.children[d] = new Node(d+1,element);
				setNpl(root);
				return;
			}
			temp = temp.children[d];
		}
		
		T temp2 = temp.element;
		temp.element = element;
		temp.children[d] = merge2(temp.children[d],new Node(d+1,temp2));
		
		if (temp == root) {
			
		}
		
		
		
		setNpl(root);
		elements++;
	}
	
	public T dequeue()
	{
		/*
		Remove and return the Max element from the heap.
		If no such element exists, return null. 
		*/
		
		if (root == null) return null;
		elements--;
		T temp = root.element;
		root = merge(root.children);
		return temp;
		
	}
	
	public String breadthFirstSearch()
	{
		/*
		Return a string containing the elements in this heap
		in breadth first order.  The elements should appear in square 
		brackets, be comma separated and have no additional characters or
		spaces.  If the heap is empty, simply return two square brackets with 
		no spaces.
		
		As an example, the string for Heap 2 in figure 1 in the spec should be 
		returned as: [4,5,6,7,11,8,50,22,19] containing no additional white 
		space.
		*/
		
		if (root==null) return null;
		
		Queue queue = new Queue();
		String returnS = "[" + root.element;
		
		queue.enqueue(root);
		
		while(!queue.isEmpty()) {
			Node r = queue.dequeue();
			
			if(r != root)  returnS = returnS + "," + r.element;
			
			for(int a=0; a<d+1;a++) {
				if(r.children[a] == null) continue;
				queue.enqueue(r.children[a]);
			}
			
		}
		
		return returnS + "]";
		
	}
	
	
	public String depthFirstPreOrder()
	{
		/*
		This method must return a string representation
		of the elements (between square brackets and comma separated) in the 
		heap in pre-order.
		In multiway trees, a pre-order traversal "visits" 
		a current node and then processes its children
		from left to right.
		
		The pre-order string for Heap 2 in figure one would be: 
		[4,5,11,8,19,50,6,7,22] containing no additional white space.
		*/
		if (root == null)	return "[]";
		return "[" + dFPre(root) + "]";
	}
	
	public String dFPre(Node node) {
		if (node == null) return "";
		String temp = node.element.toString();
		
		for (int a = 0;a<d+1;a++) {
			if (node.children[a] != null) temp = temp + "," + dFPre(node.children[a]);
			
		}
		return temp;
		
		
		
	}
	
	
	public String depthFirstPostOrder()
	{
		/*
		This method must return a string representation
		of the elements (between square brackets and comma separated) in the 
		heap in post-order.
		In multiway trees, a post-order traversal "visits" 
		a current node's children from left to right before processing the 
		node itself.
		
		See the pre-order method to get an idea of what the output of should 
		look like. 
		*/
		
		if (root == null)	return "[]";
		return "[" + dFPost(root) + "]";
	}
	
	public String dFPost(Node node) {
		if (node == null) return "";
		String temp = node.element.toString();
		
		for (int a = d;a>-1;a--) {
			if (node.children[a] != null) temp = dFPost(node.children[a]) + "," + temp;
			
		}
		return temp;
	}
	
	public void combine(LeftistDHeap<T> other)
	{
		/*
		This method needs to merge two heaps (the heap on which the method
		was called, namely "this" and the heap passed in as a parameter,
		namely "other"). If the arity of the two heaps
		differ, then the overall merged heap's arity should be set to 
		be the larger of the two.  For example, if "this" is of 
		arity 3 and "other" of arity 5, then "this"'s arity should be
		changed to 5. "This" should become the product of the merger.
		*/
		if (other.d > d) {
			
			while(!isEmpty()) {
				other.enqueue(dequeue());
			}
			d = other.d;
			root = other.root;
		}
		
		else {
			while (!other.isEmpty()) {
				enqueue(other.dequeue());
			}
		}
		
		
	}
	
	public boolean isEmpty()
	{
		/*
		Returns true if the heap contains no elements and
		false otherwise.
		*/
		if (root == null) return true;
		return false;
	}
	
}