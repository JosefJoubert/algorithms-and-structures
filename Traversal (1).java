import java.util.ArrayList;

public class Traversal {

	// The below variables are as used in sectino 8.2 of the course textbook.

	// An array of number assigned to each vertex
	public int [] num;

	// An array of strings represented each edge in the order they are visited
	public ArrayList<String> edges;

	// A counter used to assign numbers to vertices
	public int i;

	/**
	 * The constructor:
	 * Given the number of vertices in the graph, this will initialise the num and edges arrays.
	 * @param V: The number of vertices in the graph
	 */
	public Traversal(int V) {
		num = new int[V];
		edges = new ArrayList<String>();
		i = 1;
	}

	/**
	 * Prints out the contents of the num array
	 */
	public void printNum() {
		for (int v = 0; v < num.length; v++)
			System.out.println(v +": " + num[v]);
	}

	/**
	 * Prints out the contents of the edges array
	 */
	public void printEdges() {
		int n = 0;
		for (int e = 0; e < edges.size(); e++)
			System.out.println(e + ": " + edges.get(e));
	}
}