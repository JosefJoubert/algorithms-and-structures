public class Queue {

	public Queue(int _max) {
		elems = new Node[_max];
		max = _max;
	}
	
	Node[] elems;
	int begin = 0;
	int end = 0;
	int max;
	
	public void enq(Node e) {
		elems[end] = e;
    
		if ((end+1)%max == begin) {
		  Node[] temp = new Node[max*2];
		  for (int k = 0; k < max; k++) {
			temp[k] = elems[(begin + k)%max];
		  }
		  elems = temp;
		  end = max;
		  begin = 0;
		  max = max*2;
		} else {
		  end = (end+1)%max;
		}
	}
	
	public Node deq() {
		if (begin == end) {
			throw new RuntimeException("Cannot dequeue - Queue empty");
		}
		Node result = elems[begin];
		begin = (begin+1)%max;
		return result;
	}
	
	public boolean isEmpty() {
		return begin == end;
	}
  
	public void print() {
		if (this.isEmpty()) {
		  System.out.println( "[  ]");
		  return;
		}
		String t = "[ "+elems[begin].key;
		int p = (begin+1)% max;
		while (p != end) {
		  t += ", "+elems[p%max].key;
		  p = (p+1)% max;
		}
		t += " ]";
		System.out.println(t);
	}
}