// Name: JH Joubert
// Student number: 12048918
public class BinaryTreeNode 
{
        protected char el;
        protected BinaryTreeNode left, right;
        public BinaryTreeNode()
        {
            left = right = null;
            
        }
        public BinaryTreeNode(char elem)
        {
            el = elem;
            left  = null;
            right = null;
        }
        ////// You may not change any code above this line //////
    
        ////// Implement the functions below this line //////
        /**
        * Adds a child node with the given key 
        * @param val
        *           The key for the node to be inserted.
        */ 
        public void add(char val)
        {
              // provide your implementation here
        	if (Character.isDigit(val)) {
        		if (right == null) {
        			right = new BinaryTreeNode(val);
        			return;
        		}

        		if (left == null) {
        			left = new BinaryTreeNode(val);
        			return;
        		}
        		
        		
        		left.add(val);
        		return;
        	}
        	
       		BinaryTreeNode temp = this;
       		while (temp.left != null) {
       			temp = temp.left;
       		}
       		temp.left = new BinaryTreeNode(val);
        	
        	
        }
    
}
