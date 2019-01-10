public interface Graph{
	public void addVertex(Character vertex);
	public void addEdge(Character vFrom, Character vTo);
	
	public boolean areNeighbours(Character vFrom, Character vTo);
	public Character[] getNeighbours(Character vertex);
	
	public Character[] getVertices();
}