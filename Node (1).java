public class Node {
	String name;
	int value;
	Node next;
	Node pred;
	String first;
	Node[] neighbours;
	String[] path;
	int visited;
	public Node(String n,int l, Node prev, String firs, int vis){
		name = n;
		neighbours = new Node[l];
		path = new String[l];
		pred = prev;
		first = firs;
		visited = vis;
	}
}
