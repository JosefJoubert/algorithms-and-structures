
public class GraphColouring {
    
    int[] verteciesColour;
    AdjacencyMatrix verteciesMatrix;
    
    public GraphColouring(AdjacencyMatrix matrix)
    {
            verteciesMatrix = matrix;
            if(matrix.header != null)
            {
                verteciesColour = new int[matrix.header.length];
                for(int i = 0;i < matrix.header.length;i++)
                {
                    verteciesColour[i] = -1;
                }     
            }
            
    }
    
    private int getVertexIndex(Character vertex) {
		if (verteciesMatrix.header == null) {
			return -1;
		}
		for (int k = 0; k < verteciesMatrix.header.length; k++) {
			if (verteciesMatrix.header[k].equals(vertex)) {
				return k;
			}
		}
		return -1;
	}
    
    public void sequentialColouringAlgorithm()
    {
    	if (verteciesMatrix == null) return;
    	if (verteciesColour == null) return;
       verteciesColour[0] = 0;
       for (int a = 1; a<verteciesColour.length;a++){
    	   Character[] neighbours = verteciesMatrix.getNeighbours(verteciesMatrix.header[a]);
    	   for (int b = 0;b<verteciesColour.length+1;b++){
    		   Boolean found = false;
    		   for (int c=0; c<neighbours.length;c++){
    			   if(verteciesColour[getVertexIndex(neighbours[c])] == b){
    				   found = true;
    				   break;
    			   }
    		   }
    		   if(!found){
    			   verteciesColour[a] = b;
    			   break;
    		   }
    	   }
       }
       
    }
    
    public void printverticesColours()
    {   if(verteciesMatrix.header == null)
        {
            System.out.println("Empty Matrix");
        }
        else
        {
            for(int i =0;i<verteciesColour.length;i++)
            {
                System.out.println("Vertex " + verteciesMatrix.header[i] + " has colour --> " + verteciesColour[i]);
            }
        }
    }
    
}
