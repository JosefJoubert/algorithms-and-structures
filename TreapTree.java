
public class TreapTree<T extends Comparable<T>, R extends Comparable<R>> 
{

    protected TreapNode<T, R> root;
    public TreapTree() 
    {
        root = null;
    }

    //TODO:
    /**
     * Inserts a new element or modifies an existing element's key then does
     * rotations accordingly. Read the spec for more details.
     */
    public void insert(T el, R key) {
    	if(root == null) {
    		root = new TreapNode<T,R>(el,key);
    		return;
    	}
    	TreapNode<T,R> temp = find(root,el);
       if (temp != null) {
    	   temp.key = key;
    	   if (temp.parent == null) return;
    	   while (temp.key.compareTo(temp.parent.key) > 0) {
    		   rotate(temp.parent.parent,temp.parent,temp);
    	   		if (temp.parent == null) return;
    	   }
       }
       
       else {
    	   temp = root;
    	   while(true) {
    		   if (temp.el.compareTo(el) < 0) {
    			   if (temp.right == null) {
    				   temp.right = new TreapNode<T,R>(el,key,null,null,temp);
    				   if (temp.parent == null) return;
    				   while (temp.key.compareTo(temp.parent.key) > 0) {
    		    		   rotate(temp.parent.parent,temp.parent,temp);
    		    	   		if (temp.parent == null) return;
    		    	   }
    				   return;
    			   }
    			   else temp = temp.right;
    		   }
    		   if (temp.el.compareTo(el) > 0) {
    			   if (temp.left == null) {
    				   temp.left = new TreapNode<T,R>(el,key,null,null,temp);
    				   if (temp.parent == null) return;
    				   while (temp.key.compareTo(temp.parent.key) > 0) {
    		    		   rotate(temp.parent.parent,temp.parent,temp);
    		    	   		if (temp.parent == null) return;
    		    	   }
    				   return;
    			   }
    			   else temp = temp.left;
    		   }
    		   
    	   }
       }
       
       
    }

    //TODO:
    /**
     * Deletes a node by specifying the element you want to delete.
     * Use the COS 212 text book's strategy to delete nodes.
     */
    public void delete(T el)
    {
       TreapNode<T,R> temp = find(root,el);
       if (temp == null) return;
       while (temp.left != null && temp.right != null) {
    	   if(temp.left.key.compareTo(temp.right.key) > 0) {
    		   rotate(temp.parent,temp,temp.left);
    	   }
    	   else rotate(temp.parent,temp,temp.right);
       }
       if (temp.left == null) {
    	   if (temp != root) {
    		   if (temp.parent.left == temp) {
    			   temp.parent.left = temp.right;
    		   }
    		   else temp.parent.right = temp.right;
    	   }
    	   else root = temp.right;
    	   if (temp.right != null)  temp.right.parent = temp.parent;
    	   return;
       }
       if (temp.left != null) {
    	   if (temp != root) {
    		   if (temp.parent.left == temp) temp.parent.left = temp.left;
    		   else temp.parent.right = temp.left;
    	   }
    	   else root = temp.left;
    	   temp.left.parent = temp.parent;
       }
    }
    //TODO:
    /**
     * Returns the node with the specified element
     */
    public TreapNode<T, R> find(TreapNode<T, R> p,T el) 
    {
    	if (root == null) return null;
    	if (root.el == el) return root;
        TreapNode<T,R> temp = root;
        while (temp != null) {
        	if (temp.el.compareTo(el) > 0) temp = temp.left;
        	else if (temp.el.compareTo(el) < 0) temp = temp.right;
        	else if (temp.el == el) return temp;
        }
        return null;
    }
    
    //DO NOT MODIFY
    public void inorder() {
        inorder(root);
    }

    //DO NOT MODIFY
    public void inorder(TreapNode<T, R> p) {
        if (p != null) {
            inorder(p.left);
            visit(p);
            inorder(p.right);
        }
    }

    //DO NOT MODIFY
    public void visit(TreapNode<T, R> p) {
        System.out.print(p.toString());
    }

    //DO NOT MODIFY
    private void rotate(TreapNode<T, R> gr, TreapNode<T, R> _parent, TreapNode<T, R> child) {
        if (_parent.left == child) {
            rotateRight(gr, _parent, child);
        } else {
            rotateLeft(gr, _parent, child);
        }
    }

    //DO NOT MODIFY
    private void rotateLeft(TreapNode<T, R> gr, TreapNode<T, R> _parent, TreapNode<T, R> child) {
        _parent.right = child.left;
        child.left = _parent;
        finishRotation(gr, child.left, child, child.left.right);
    }

    //DO NOT MODIFY
    private void rotateRight(TreapNode<T, R> gr, TreapNode<T, R> _parent, TreapNode<T, R> child) {
        _parent.left = child.right;
        child.right = _parent;
        finishRotation(gr, child.right, child, child.right.left);
    }

    //DO NOT MODIFY
    private void finishRotation(TreapNode<T, R> grandParent, TreapNode<T, R> _parent, TreapNode<T, R> child, TreapNode<T, R> childDescendant) {

        // if there is a grandparent and you have rotated about the parent make the child now the parent
        if (grandParent != null) {
            if (grandParent.right == child.parent) {
                grandParent.right = child;
            } else {
                grandParent.left = child;
            }
        } else {
            root = child;
        }

        if (childDescendant != null) {
            childDescendant.parent = _parent;
        }
        
            
        _parent.parent = child;
        child.parent = grandParent;
    }
}
