
public class TreapNode<T extends Comparable<T>, R extends Comparable<R>> {

    protected T el;
    protected R key;
    protected TreapNode<T,R> left, right, parent;
    

    public TreapNode() {
        left = right = parent = null;
    }

    public TreapNode(T el) {
        this(el, null,null, null, null);
    }

    public TreapNode(T el, R key){
            this(el, key,null, null, null);
    }
    
    public TreapNode(T _el, R _key,TreapNode<T,R> lt, TreapNode<T,R> rt, TreapNode<T,R> pr) {
        el = _el;
        key = _key;
        left = lt;
        right = rt;
        parent = pr;
    }

    public void setKey(R _key){
        key = _key;
    }
    public void setParent(TreapNode<T,R> _parent){
        parent = _parent;
    }
    
    @Override
    public String toString(){
        if(parent!=null)
            if(parent.right!=null)
                if(parent.right.el.compareTo(el)==0)
                    return "{"+ el+","+key+"("+parent.el+"-r)}";
                else
                    return "{"+ el+","+key+"("+parent.el+"-l)}";
            else if(parent.left!=null)
                if(parent.left.el.compareTo(el)==0)
                    return "{"+ el+","+key+"("+parent.el+"-l)}";
                else
                    return "{"+ el+","+key+"("+parent.el+"-r)}";
            else  return "{"+ el+","+key+"(0)}";
        else
            return "{"+ el+","+key+"(0)}";
    }
}

