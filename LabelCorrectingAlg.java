public class LabelCorrectingAlg {
	
	/**
    * Finds the shortest path from vertex 'start' to every other vertex in the graph.
    *
	* You will require some data structure to serve as the toBeChecked list. You are advised to use the Queue class that you are provided specifically for this purpose.
	* As you can see, you are required to return a Queue. This Queue should contain all the vertices in the graph, (in the same order as they occur in the graph) with correct predecessors and costs.
	* To achieve this, create a result Queue at the beginning of the function and insert all the vertices in the graph into the Queue. 
	* The getVertex method of the Queue can be used to get a reference to the relevant vertex (so that its cost/predecessor can be updated or so that same Vertex object can be put into the toBeChecked list, if needed).
	* The resulting Queue will be enough to determine the shortest path and cost to any vertex in the graph from the 'start' vertex.
	* This is essentially the table of costs and predecessors produced by the example execution in the textbook.
	*
	*
    * @param  graph   The graph on which the shortest path algorithm is applied
    * @param  start   The name of the vertex from which the shortest paths are calculated
	* @return 		  A list of all the vertices, their predecessors and current distances/costs
    */
	public static Queue shortestPaths(Graph graph, Character start) {
		Character[] arr = graph.getVertices();
		Queue toBe = new Queue();
		for (int a = 0;a<arr.length;a++){
			toBe.insertVertex(arr[a], Double.POSITIVE_INFINITY);
		}
		toBe.peek().cost = 0;
		while (!toBe.isEmpty()){
			Vertex v = toBe.peek();
			toBe.removeVertex(v.key);
			Character[] neighbours = graph.getNeighbours(v.key);
			for (int a=0;a<neighbours.length;a++){
				if (toBe.getVertex(neighbours[a]).cost > (v.cost+graph.getEdgeWeight(v.key, neighbours[a]))){
					toBe.getVertex(neighbours[a]).cost = v.cost+graph.getEdgeWeight(v.key, neighbours[a]);
					toBe.getVertex(neighbours[a]).pred = v.key;
				}
			}
		}
		return toBe;
	}
	
	/**
    * The function uses the Queue output from the shortestPaths function above. 
	* Finds the cost of the shortest path to the specified 'target' vertex (this path will start at the vertex given as input to the shortestPaths function)
	*
    * @param  vl   	  The Queue that was produced by the shortestPaths function. This list contains enough information to deduce the shortest path from 'start' to any other node in the graph as well as its cost.
    * @param  target   The name of the vertex to which the shortest path is calculated
	* @return 		  The cost of the shortest path from the first vertex in vl to the target vertex
    */
	public static double getPathCost(Queue vl, Character target) {
		return -1;
	}
	
	
	/**
    * The function uses the Queue output from the shortestPaths function above. 
	* Finds the shortest path to the specified 'target' vertex. 
	* If the the start and target vertices are the same, return an empty array.
	* If there is no path between the two vertices, return an empty array.
	*
    * @param  vl   	  The Queue that was produced by the shortestPaths function. This list contains enough information to deduce the shortest path from 'start' to any other node in the graph as well as its cost.
    * @param  from   	The name of the vertex at which the path starts
    * @param  target   The name of the vertex at which the path ends (i.e. the vertex to which the shortest path is calculated)
	* @return 		  The shortest path from the 'from' vertex to the 'target' vertex as an array of vertex names
    */
	public static Character[] getPath(Queue vl, Character from, Character target) {
		return null;
	}
}