import java.util.ArrayList;

public class AMGraph {

	// number of vertices
	protected int V;
	
	// the adjacency matrix
	protected boolean [][] adjacencyMatrix;

	// this constructor initialises the graph with the given number of vertices
	// and no edges.
	public AMGraph(int numberOfVertices) {
		V = numberOfVertices;
		adjacencyMatrix = new boolean[V][V];
		for (int i = 0; i < V; i++)
			for (int j = 0; j < V; j++)
				adjacencyMatrix[i][j] = false;
	}

	// prints out the adjacency matrix
	// you can use this to test your implementations
	public void printRepresentation() {
		for (int i = 0; i < V; i++) {
			for (int j = 0; j < V; j++) {
				System.out.print( adjacencyMatrix[i][j] ? "1" : "0" );
			}
			System.out.println();
		}
	}

	// task 1:
	// implement this function
	// see the spec for more instructions
	public void addEdge(int v1, int v2) {
		if (v1 > V-1 || v2 > V-1) return;
		adjacencyMatrix[v1][v2] = true;
		adjacencyMatrix[v2][v1] = true;
		// add your implementation here

	}

	// task 2:
	// implement this function
	// see the spec for more instructions
	public Traversal depthFirstSearch() {
		Traversal obj = new Traversal(V);
		while (true) {
			for (int a=0;a<obj.num.length;a++) {
				if (obj.num[a] == 0) DFS(a,obj); 
			}
			if (obj.num[obj.num.length-1] != 0) break;
		}
		//obj.printEdges();
		return obj;
		// replace with your implementation
	}
	
	private void DFS(int vee,Traversal obj) {
		obj.num[vee] = obj.i++;
		for (int a=0;a<V;a++) {
			if (a == vee) continue;
			if (obj.num[a] == 0) {
				if (adjacencyMatrix[vee][a]) {
					obj.edges.add(Integer.toString(vee) + Integer.toString(a));
					DFS(a,obj);
				}
			}
		}
	}

	// task 3:
	// implement this function
	// see the spec for more instructions
	public Traversal breadthFirstSearch() {
		Traversal obj = new Traversal(V);
		ArrayList<Integer> queue = new ArrayList<Integer>();
		int a=0;
		while(true) {
			obj.num[a] = obj.i++;
			queue.add(a);
			while (!queue.isEmpty()) {
				a = queue.remove(queue.size()-1);
				for (int b=0;b<V;b++) {
					if(adjacencyMatrix[a][b]) {
						if (obj.num[b] == 0) {
							obj.num[b] = obj.i++;
							queue.add(b);
							obj.edges.add(Integer.toString(a)+Integer.toString(b));
						}
					}
				}
			}
			if(obj.num[obj.num.length-1] != 0) break;
			a++;
		}
		return obj;
	}
}