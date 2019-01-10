// Name: JH Joubert
// Student number: 12048918
public class Queue
{
    protected QueueNode front;
    protected int size;
    /**
    * Constructor for the queue
    *
    */ 
    public Queue()
    {
        // provide your implementation here
    	front = null;
    	size = 0;
    }
    /**
    * Creates a new node with the given key and
    * adds it the back of the queue
    * @param el
    *           The key for the node to enqueued
    */
    public void enqueue(char el)
    {
        // provide your implementation here
    	if (isEmpty()) {
    		front = new QueueNode(el);
    		return;
    	}
    	QueueNode temp = front;
    	
    	while (temp.next != null) {
    		temp = temp.next;
    	}
    	temp.next = new QueueNode(el);
    	
    }
    /**
    * Returns the node at the front of the queue,
    * removing it from the queue in the process
	* @return the node at the front of the queue
    */
    public QueueNode dequeue()
    {
         // provide your implementation here
         if (isEmpty()) return null;
         QueueNode temp = front;
         front = front.next;
         return temp;
    }
    /**
    * Indicates whether or not there are nodes in the queue
    * @return true if the queue is empty, false otherwise
	*
    */
    public boolean isEmpty()
    {
        // provide your implementation here
        if (front == null) return true;
        return false;
    }
}
