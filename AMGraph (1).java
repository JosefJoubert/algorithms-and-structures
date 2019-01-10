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

		// add your implementation here

	}

	// task 2:
	// implement this function
	// see the spec for more instructions
	public Traversal depthFirstSearch() {
		
		// replace with your implementation
		return null;
	}

	// task 3:
	// implement this function
	// see the spec for more instructions
	public Traversal breadthFirstSearch() {

		// replace with your implementation
		return null;
	}
}