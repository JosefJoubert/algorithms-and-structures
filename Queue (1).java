
public class Queue {
	
	
	
	private Node root = null;
	private int length;
	public Queue(int l){
		length = l;
	}
	
	
	public void clear(){
		root = null;
	}
	
	public void enqueue(String name, int val,Node prev, String first,int visited){
		if (root == null) {
			root = new Node(name,length,prev,first,visited);
			root.value = val;
			return;
		}
		Node temp = root;
		while (temp.next != null) temp = temp.next;
		temp.next = new Node(name,length,prev,first,visited);
		temp.next.value = val;
	}
	
	public boolean contains(String name){
		Node temp = root;
		while(temp != null){
			if (temp.equals(name)) return true;
		}
		return false;
	}
	
	public Node returnlowest(){
		if (root == null) return null;
		//int low = root.value;
		Node temp = root.next;
		Node lowest = root;
		while (temp != null){
			if(temp.value < lowest.value) {
				//low = temp.value;
				lowest = temp;
			}
			temp = temp.next;
		}
		return lowest;
	}

	
	public void remove(Node remove){
		if (root == null) return;
		if (root == remove){
			root = root.next;
			return;
		}
		Node temp = root;
		while(temp != null){
			if(temp.next == remove){
				temp.next = remove.next;
				return;
			}
			temp = temp.next;
		}
	}
	
	
}
