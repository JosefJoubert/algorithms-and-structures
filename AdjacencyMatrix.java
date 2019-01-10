public class AdjacencyMatrix implements Graph{
	
	/** See the specification for more information on the class variables**/
	double[][] matrix;
	Character[] header;
	/**
    * Given a vertex's name or key, return the vertex's relevant column/row in the matrix variable.
	* For example, consider the adjacency matrix representation of a undirected graph:
	*		a		b		c
	*	a	Inf		1		1
    *	b	1		Inf		Inf
	*	c	1		1		Inf
	* 
	*	Then getVertexIndex('a') will return 0, since that is the index of the row and column relevant to the vertex 'a' in the graph.
	*	getVertexIndex('b') will return 1 and getVertexIndex('c') will return 2.
    * @param  vertex  The name/key of a vertex
    * @return         The index of the vertex's column/row in the matrix. Since the row and column headers of the matrix are the same
    */
	private int getVertexIndex(Character vertex) {
		if (header == null) {
			return -1;
		}
		for (int k = 0; k < header.length; k++) {
			if (header[k].equals(vertex)) {
				return k;
			}
		}
		return -1;
	}
	
	
	/**
    * Returns the names of all the vertices in the graph as an array of characters.
    *
    * @return         The names of all the vertices in the graph as an array of characters.
    */
	public Character[] getVertices() {
		if (header == null) return new Character[0];
		Character[] hCopy = new Character[header.length];
		for (int k = 0; k < header.length; k++) {
			hCopy[k] = header[k];
		}
		return hCopy;
	}

	/**
    * Adds a vertex with the given name to the graph
    *
	* In order to add the vertex to the graph successfully, the header array will have to be grown to accommodate the new vertex name. 
	* The new vertex can simply be added to the end; there is no need to keep the node list alphabetized for this practical.
	*
	* The matrix variable will have to grow an additional row and an additional column. All of the values in the new row and column should be initialized to positive infinity, since there is initially no edge between the new vertex and any of the existing vertices.
	*
    * @param  vName   The name of the vertex to be added to the graph
    */
        @Override
	public void addVertex(Character vName) {
		
		if (header == null) {
			matrix = new double[1][1];
			header = new Character[1];
			header[0] = vName;
			matrix[0][0] = Double.POSITIVE_INFINITY;
		} else {
			Character[] hNew = new Character[header.length + 1];
			for (int k = 0; k < header.length; k++) {
				hNew[k] = header[k];
			}
			hNew[header.length] = vName;
			
			double[][] mNew = new double[hNew.length][hNew.length];
			for (int r = 0; r < header.length; r++) {
				for (int c = 0; c < header.length; c++) {
					mNew[r][c] = matrix[r][c];
				}
				mNew[r][header.length] = Double.POSITIVE_INFINITY;
				mNew[header.length][r] = Double.POSITIVE_INFINITY;
			}
			mNew[header.length][header.length] = Double.POSITIVE_INFINITY;
			
			header = hNew;
			matrix = mNew;
		}
	}
	
	
	/**
    * Adds an undirected edge to the graph. The edge is from the vertex vFrom to the vertex vTo.
	*
	* See the illustration in the prac specification.
    *
    * @param  vFrom   The name of the vertex at which the edge starts
    * @param  vTo   	The name of the vertex at which the edge ends
    */
	public void addEdge(Character vFrom, Character vTo) {
		int fromIndex = getVertexIndex(vFrom);
		int toIndex = getVertexIndex(vTo);
		
		if (fromIndex == -1 || toIndex == -1) {
			System.out.println("Error - vertices for edge not found");
			return;
		}
		
		matrix[fromIndex][toIndex] = 1;
                matrix[toIndex][fromIndex] = 1;
	}
	/**
    * Checks whether there is a direct edge from vertex vFrom to vertex vTo. 
	* If so, the two vertices are neighbours and the function returns true.
	* Otherwise it returns false. 
    *
    * @param  vFrom   The name of the start vertex
    * @param  vTo     The name of the end vertex
    * @return         true if there is an edge from vertex vFrom to vertex vTo; false otherwise
    */
	public boolean areNeighbours(Character vFrom, Character vTo) {
		int indexFrom = getVertexIndex(vFrom);
		int indexTo = getVertexIndex(vTo);
		if (indexFrom == -1 || indexTo == -1) {
			return false;
		}
		return !Double.isInfinite(matrix[indexFrom][indexTo]);
	}
	
	/**
    * Returns a list of all vertex vName's neighbours. A vertex u is a neighbour of vName if there is an edge from vName to u.
    *
    * @param  vName   The name of the start vertex
    * @return         The names of all vName's neighbours as an array of characters. If vName has no neighbours, the array should be empty.
    */
	public Character[] getNeighbours(Character vName) {
		int r = getVertexIndex(vName);
		int count = 0;
		for (int c = 0; c < header.length; c++) {
			if (!Double.isInfinite(matrix[r][c])) {
				count++;
			}
		}
		Character[] result = new Character[count];
		count = 0;
		for (int c = 0; c < header.length; c++) {
			if (!Double.isInfinite(matrix[r][c])) {
				result[count] = header[c];
				count++;
			}
		}
		return result;
	}	
}