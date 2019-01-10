
public class Printer<T extends Comparable<? super T>> extends MaxHeap {

    public Printer(Comparable[] elements) {
        super(elements);
    }

    
    
    
    public void printTree(int i,Node node ,int size,Node root)
        {
            if(i >= size)
            {
                return;
            }
            if(i == 0)
            {
                
                root.displayInfo();
                printTree( i*2 +1,root.left,size,root);
                printTree( i*2 +2,root.right,size,root);
            }
            else
            {
               
                node.displayInfo();
                printTree( i*2 +1,node.left,size,root);
                printTree( i*2 +2,node.right,size,root);
                
            }
            
            return;
        
        }
    
}
