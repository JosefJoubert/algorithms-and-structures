// Name: JH Joubert
// Student number: 12048918
public class BinaryTree
{
    protected BinaryTreeNode root;
    protected Queue operators;
    protected Queue operands;
    public void insertData(char c)
    {
       if(root==null) root = new BinaryTreeNode(c);
       else root.add(c);
    }
    public void print()
    {
        visitationStrategy(root);
        System.out.println("");
    }
    protected void visit(BinaryTreeNode n)
    {
        System.out.print(n.el);
    }
    public BinaryTreeNode getRoot()
    {
        return root;
    }
    
    ////// You may not change any code above this line //////
    
    ////// Implement the functions below this line //////
    /**
    * Constructor for the binary tree
    *
    */  
    public BinaryTree()
    {
        // provide your implementation here
    	root = null;
    	operators = new Queue();
    	operands = new Queue();
    }
    /**
    * Populates the binary tree with the given expression
    * @param expr
    *           The expression to be converted to a tree.
    */  
    public void addExpression(String expr)
    {
        // provide your implementation here
    	int a = expr.length();
    	int b = 0;
    	while (a > b) {
    		if (Character.isDigit(expr.charAt(b))) {
    			operands.enqueue(expr.charAt(b));
    		}
    		else operators.enqueue(expr.charAt(b));
    		b++;
    	}
    	
    	while (!operators.isEmpty()) {
    		insertData(operators.dequeue().data);
    	}
    	
    	while (!operands.isEmpty()) {
    		insertData(operands.dequeue().data);
    	}
    }
    /**
    * Visits the nodes in the binary tree using depth first search
    * @param n
    *           The node to be visited.
    */ 
    public void visitationStrategy(BinaryTreeNode n)
    {
        // provide your implementation here
    	System.out.println(dfs(n));
    }
    
    public String dfs(BinaryTreeNode n) {
    	String temp = "";
    	if (n.left != null) temp = temp + dfs(n.left);
    	temp = temp + n.el;
    	if (n.right != null) temp= temp + dfs(n.right);
    	return temp;
    }

}
