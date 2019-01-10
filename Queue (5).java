public class Queue {
	class Node {
		Vertex vertex;
		Node next = null;
		Node(Vertex v) {
			vertex = v;
		}
	}
	
	Node head = null;
	
	
	/**
    * Creates a new vertex with the given name and cost and inserts it at the end of the list.
	*
    * @param  vert    The vertex to be inserted into the list
    */
	public void insertVertex(Character vertex, double cost) {
		insertVertex(new Vertex(vertex, cost, null));
	}
	
	/**
    * Inserts the given vertex at the end of the list.
	*
    * @param  vert    The vertex to be inserted into the list
    */
	public void insertVertex(Vertex vert) {
		if (head == null) {
			head = new Node(vert);
		} else {
			Node temp = head;
			while (temp.next != null) {
				temp = temp.next;
			}
			temp.next = new Node(vert);
		}
	}
	
	/**
    * Removes the given vertex from the list
	*
    * @param  vert    The name of the vertex to be removed from the list
    */
	public Vertex removeVertex(Character vertex) {
		
		Node temp = head;
		Node prev = null;
		while (temp != null) {
			if (temp.vertex.key.equals(vertex)) {
				if (temp == head) {
					head = head.next;
				} else {
					prev.next = temp.next;
				}
				temp.next = null;
				return temp.vertex;
			}
			prev = temp;
			temp = temp.next;
		}
		return null;
	}
	
	
	
	/**
    * Checks whether a vertex with the given name is already in the list.
	*
    * @param  v    The name of the vertex to be searched for
	* @return	   Returns true if a vertex with name 'v' occurs in the list and false otherwise.
    */
	public boolean contains(Character v) {
		Node t = head;
		while (t != null) {
			if (t.vertex.key.compareTo(v) == 0) {
				return true;
			}
			t = t.next;
		}
		return false;
	}
	
	
	/**
    * Checks if the queue is empty or not
	*
	* @return	   Returns true if the queue is empty and false otherwise
    */
	public boolean isEmpty() {
		return head == null;
	}
	
	/**
    * Returns the first vertex in the queue.
	*
	* @return	   Returns the head of the queue
    */
	public Vertex peek() {
		return head.vertex;
	}
	
	
	
	/**
    * Returns the vertex with name 'v'
	*
    * @param  v    The name of the vertex to be searched for
	* @return	   Returns vertex with name 'v'
    */
	public Vertex getVertex(Character v) {
		Node t = head;
		//System.out.println("Looking for "+v);
		while (t != null) {
			if (t.vertex.key.equals(v)) {
				//System.out.println("Found "+t.vertex.key+" = "+v);
				return t.vertex;
			}
			//System.out.println("found "+t.vertex.key+" != "+v);
			t = t.next;
		}
		return null;
	}
	
	
	/**
    * Prints the contents of the VertexList.
    */
	public void printList() {
		Node tmp = head;
		while (tmp != null) {
			System.out.println(tmp.vertex.key + " (pred = "+tmp.vertex.pred + ", cost = "+tmp.vertex.cost+")");
			tmp = tmp.next;
		}
	}
}