public class Vertex {
	Character key; 
	Character pred;
	double cost;
	
	
	/**
    * Creates a new vertex object with the given values.
	*
    * @param  _v    	The name/key of the vertex
    * @param  _cost    	The cost/current distance of the vertex
    * @param  _pred    	The predecessor of the vertex
    */
	public Vertex(Character _v, double _cost, Character _pred) {
		key = _v;
		cost = _cost;
		pred = _pred;
	}
}