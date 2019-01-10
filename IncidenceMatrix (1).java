public class IncidenceMatrix implements Graph{
	
	/** See the specification for more information on the class variables**/
	double[][] matrix = new double[0][0];
	Character[] vertices = new Character[0];
	String[] edges = new String[0];
	
	
	/** 
	**	Helper Functions
	**
	**/
	
	/**
    * Given a vertex's name/key, return the vertex's relevant row in the matrix variable.
	* For example, consider the weighted incidence matrix representation of an undirected, weighted, simple graph:
	*		ab		bc		
	*	a	5		Inf		
    *	b	5		-20		
	*	c	Inf		-20		
	* 
	*	Then getVertexRow('a') will return 0, since that is the index of the row relevant to the vertex 'a' in the graph.
	*	getVertexRow('b') will return 1 and getVertexRow('c') will return 2.
	* If the requested vertex is not in the graph, the method will return -1
    * @param  vertex  The name/key of a vertex
    * @return         The index of the vertex's row in the matrix.
    */
	private int getVertexRow(Character vertex) {
		for (int k = 0; k < vertices.length; k++) {
			if (vertices[k].equals(vertex)) {
				return k;
			}
		}
		return -1;
	}
	
	/**
    * Given a edges's incident vertices, return the edge's relevant column in the matrix variable.
	* For example, consider the weighted incidence matrix representation of an undirected, weighted, simple graph:
	*		ab		bc		
	*	a	5		Inf		
    *	b	5		-20		
	*	c	Inf		-20		
	* 
	*	Then getEdgeColumn('ab') will return 0, since that is the index of the column relevant to the edge incident on 'a' and b' in the graph.
	*	getEdgeColumn('bc') will return 1 and getEdgeColumn('cb') will return -1.
    * @param  edge  The name of the edge, i.e. its incident vertices.
    * @return         The index of the eddge's column in the matrix.
    */
	private int getEdgeColumn(String edge) {
		for (int k = 0; k < edges.length; k++) {
			if (edges[k].equals(edge)) {
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
		Character[] hCopy = new Character[vertices.length];
		for (int k = 0; k < vertices.length; k++) {
			hCopy[k] = vertices[k];
		}
		return hCopy;
	}
	
	
	/**
    * Prints out the graph.
    */
	public void printGraph() {
		String line = " ";
		for (int k = 0; k < edges.length; k++) {
			line += "\t" + edges[k];
		}
		line += "\n";
		for (int r = 0; r < vertices.length; r++) {
			line += vertices[r];
			for (int c = 0; c < edges.length; c++) {
				line += "\t" + (Double.isInfinite(matrix[r][c]) ? "Inf" : matrix[r][c]);
			}
			line += "\n";
		}
		System.out.println(line);
	}
	
	
	/** 
	**	To Implement:
	**
	**/
	
	/**
    * Adds a vertex with the given name to the graph
    *
	* In order to add the vertex to the graph successfully, the vertex array array will have to be grown to accommodate the new vertex name. 
	* The new vertex can simply be added to the end; there is no need to keep the node list alphabetized for this practical. 
	*
	* The matrix variable will have to grow an additional row. All of the values in the new row should be initialized to positive infinity, since none of th existing edges are between the new vertex and an existing vertex. See the illustration in the prac specification.
	*
    * @param  vName   The name of the vertex to be added to the graph
    */
	public void addVertex(Character vName) {
		Character[] newvertices = new Character[vertices.length+1];
		for (int a =0;a<vertices.length;a++){
			newvertices[a] = vertices[a];
		}
		newvertices[vertices.length] = vName;
		vertices = newvertices;
		
		int a = matrix.length;
		if (a == 0){
			return;
		}
		else{
			double[][] newmatrix = new double[a+1][matrix[0].length];
			for (int b = 0;b<a;b++){
				for (int x = 0;x<matrix[0].length;x++){
					newmatrix[b][x] = matrix[b][x];
				}
			}
			matrix = newmatrix;
			for(int b = 0;b<matrix[0].length;b++){
				matrix[a][b] = Double.POSITIVE_INFINITY;
			}
		}
		
	}
	
	
	/**
    * Adds an edge between v1 and v2 with the given weight to the graph. Remember that the graph is undirected.
	*
	* In order to add a new edge, the edges variable will have to be grown to accomodate the new edge (as with the vertices list, there is no need to alphabetize it for this practical).
	* The matrix variable will also have to be grown with an additional column.
	* You should be able to figure out what the values in the new column should be
	*
	* See the illustration in the prac specification.
    *
    * @param  v1   The name of the first vertex
    * @param  v2   	The name of the second vertex 
    * @param  weight   The weight of the new edge
    */
	public void addEdge(Character v1, Character v2, double weight) {
		if (v1.equals(v2)) return;
		String newstring = Character.toString(v1) + Character.toString(v2);
		String[] newedges = new String[edges.length+1];
		for (int a = 0;a<edges.length;a++){
			newedges[a] = edges[a];
		}
		int pos = 0;
		newedges[edges.length] = newstring;
		edges = newedges;
		if (matrix.length == 0){
			matrix = new double[vertices.length][1];
			for(int a = 0;a<vertices.length;a++){
				matrix[a][0] = Double.POSITIVE_INFINITY;
			}	
		}
		else{
			double[][] newmatrix = new double[matrix.length][matrix[0].length+1];
			for(int a = 0;a<matrix.length;a++){
				for(int b = 0;b<matrix[0].length;b++){
					newmatrix[a][b] = matrix[a][b];
				}
			}
			for(int a =0;a<matrix.length;a++){
				newmatrix[a][matrix[0].length] = Double.POSITIVE_INFINITY;
			}
			matrix = newmatrix;
			pos = matrix[0].length-1;
		}
		int pos1=0,pos2=0;
		for (int a = 0;a<vertices.length;a++){
			if (vertices[a].equals(v1)) pos1 = a;
			if (vertices[a].equals(v2)) pos2 = a;
		}
		matrix[pos1][pos] = weight;
		matrix[pos2][pos] = weight;
		
	}
	
	
	/**
    * Removes the vertex with the given name from the graph. 
	* If removal is successful, the function returns true. 
	* Otherwise (eg. if the specified vertex is not in the graph), it returns false.
    *
	* In order to remove the vertex from the graph successfully, the vertices array will have to be shrunk. All the vertices after vName will have to be moved up.
	*
	* The matrix variable will have to shrunk in order to remove the row corresponding to vName. Additionally, any edges that are incident on vName should also be removed from the edges array and from the matrix variable.
	*
	* See the illustration in the prac specification.
	*
    * @param  vName   The name of the vertex to be removed from the graph
    * @return         true if removal was successful, false otherwise
    */
	public boolean removeVertex(Character vName) {
		int pos = -1;
		for (int a =0;a<vertices.length;a++){
			if (vertices[a].equals(vName)) {
				pos = a;
				break;
			}
		}
		if (pos == -1) return false;
		for (int a = 0;a<edges.length;a++){
			if (edges[a].charAt(0) == vName || edges[a].charAt(1) == vName){
				removeEdge(a--);
			}
		}
		Character[] newvertices = new Character[vertices.length-1];
		double[][] newmatrix = new double[matrix.length-1][matrix[0].length];
		for (int a = 0; a<pos;a++){
			newvertices[a] = vertices[a];
			newmatrix[a] = matrix[a];
		}
		for (int a = pos+1; a<vertices.length;a++){
			newvertices[a-1] = vertices[a];
			newmatrix[a-1] = matrix[a];
		}
		vertices = newvertices;
		matrix = newmatrix;
		
		return true;
	}
	
	/**
    * Removes the edge that goes between vertex v1 and vertex v2 from the graph. 
	* If removal is successful, the function returns true. 
	* Otherwise (eg. if the specified vertex is not in the graph), it returns false.
	*
	* Both the edges array and the matrix variable will have to be shrunk to achieve this
	*
	* See the illustration in the prac specification.
    *
    * @param  v1   The name of first vertex 
    * @param  v2     The name of second vertex 
    * @return         true if removal was successful, false otherwise
    */
	
	private void removeEdge(int pos){
		String[] newedges = new String[edges.length-1];
		double[][] newmatrix = new double[matrix.length][matrix[0].length-1];
		for (int a = 0;a<pos;a++){
			newedges[a] = edges[a];
			//newmatrix[a] = matrix[a];
		}
		for (int a = pos+1;a<edges.length;a++){
			newedges[a-1] = edges[a];
			//newmatrix[a-1] = matrix[a];
		}
		for (int a=0;a<vertices.length;a++){
			for (int b = 0;b<pos;b++){
				newmatrix[a][b] = matrix[a][b];
			}
		}
		for (int a=0;a<vertices.length;a++){
			for(int b =pos+1;b<edges.length;b++){
				newmatrix[a][b-1] = matrix[a][b];
			}
		}
		
		
		edges = newedges;
		matrix = newmatrix;
	}
	
	public boolean removeEdge(Character v1, Character v2) {
		String str1 = Character.toString(v1) + Character.toString(v2);
		String str2 = Character.toString(v2) + Character.toString(v1);
		int pos = -1;
		for (int a = 0;a<edges.length;a++){
			if (edges[a].equals(str1) || edges[a].equals(str2)){
				pos = a;
				break;
			}
		}
		if (pos == -1) return false;
		removeEdge(pos);
		return true;
		
		
	}
	
	/**
    * Checks whether there is an edge between vertex v1 and vertex v2. 
	* If so, the two vertices are neighbours and the function returns true.
	* Otherwise it returns false. 
    *
    * @param  v1   The name of the start vertex
    * @param  v2     The name of the end vertex
    * @return         true if there is an edge between vertex v1 and vertex v2; false otherwise
    */
	public boolean areNeighbours(Character v1, Character v2) {
		String str1 = Character.toString(v1) + Character.toString(v2);
		String str2 = Character.toString(v2) + Character.toString(v1);
		for (int a = 0;a<edges.length;a++){
			if (edges[a].equals(str1)) return true;
			if (edges[a].equals(str2)) return true;
		}
		
		return false;
	}
	
	/**
    * Returns a list of all vertex vName's neighbours. A vertex u is a neighbour of vName if there is an edge between vName and u.
	* The list should not contain any repetitions; only count the first time that a vertex is the neighbour of vName.
    *
    * @param  vName   The name of the start vertex
    * @return         The names of all vName's neighbours as an array of characters. If vName has no neighbours, the array should be empty.
    */
	public Character[] getNeighbours(Character vName) {
		int count =0;
		
		for (int a = 0;a<vertices.length;a++) if(areNeighbours(vName,vertices[a])) count++;
		Character[] arr = new Character[count];
		int b = 0;
		for (int a = 0;a<vertices.length;a++) if(areNeighbours(vName,vertices[a])) arr[b++] = vertices[a];
		return arr;
	}
	
	/**
    * Returns the weight of the specified edge (i.e. the edge between v1 and v2)
    *
    * @param  v1   The name of the start vertex
    * @param  v2     The name of the end vertex
    * @return         The weight of the edge between vertex v1 and vertex v2.
    */
	public double getEdgeWeight(Character v1, Character v2) {
		int pos1 = getEdgeColumn(Character.toString(v1)+ Character.toString(v2));
		if (pos1 == -1) return -1;
		int pos2 = getVertexRow(v1);
		if (pos2 == -1) return -1;
		
		return matrix[pos2][pos1];
		
		
	}
		
}