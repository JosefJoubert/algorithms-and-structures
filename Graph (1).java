public interface Graph{
	public void addVertex(Character vertex);
	public void addEdge(Character vFrom, Character vTo, double weight);
	
	public boolean removeVertex(Character vertex);
	public boolean removeEdge(Character vFrom, Character vTo);
	
	public boolean areNeighbours(Character vFrom, Character vTo);
	public Character[] getNeighbours(Character vertex);
	public double getEdgeWeight(Character from, Character to);
	
	public Character[] getVertices();
	
	public void printGraph();
	
}