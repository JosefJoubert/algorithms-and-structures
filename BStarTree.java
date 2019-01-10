


/*
Complete your details...
Name and Surname: JH Joubert
Student/staff Number: 12048918
*/

/*You must complete this class to create a fully functional B*-Tree containing
Integer objects.  Additional instructions are provided in comments throughout the class*/
public class BStarTree
{
	/*
	1. You may not modify the public interface of this class.  You may however
	add any additional methods and/or field which you may require to aid you 
	in the completion of this assignment.
	
	2. You will have to design and implement a Node class.  The BStarTree should
	house Integer objects.
	
	3. You will notice that there are some overloaded methods, some of which work for Integer objects and some with primitive type int.  You have to find a way to implement the methods to work with both types.
	*/
	int m;
	Node root;
	

	protected class Queue {
		protected class QueueNode{
			QueueNode next;
			Node value;
			QueueNode(Node element) {
				value = element;
				next = null;
			}
		}
	
		QueueNode root;
		Queue() {
			root = null;
		}
	
		public void enqueue(Node element) {
			if (root == null) {
				root = new QueueNode(element);
				return;
			}
			QueueNode temp = root;
			while (temp.next != null) {
				temp = temp.next;
			}
			temp.next = new QueueNode(element);
		}
	
		public Node dequeue() {
			Node temp = root.value;
			root = root.next;
			return temp;
		}
	
		public void clear() {
			root = null;
		}
	
		public boolean isEmpty() {
			if (root == null) return true;
			return false;
		}
	}
	
	protected class Node {
		boolean leaf = true;
		int keys;
		Integer[] values = new Integer[m-1];
		Node[] ptrs = new Node[m];
		Node(Integer k) {
			values[0] = k;
			for (int i = 0; i<m; i++) {
				ptrs[i] = null;
			}
			if (k == null) keys = 0;
			else keys = 1;
		}
		public boolean isFull() {
			if (keys == m-1) return true;
			return false;
		}
		public void insert(Integer element,int pos) {
			for (int i = m-2;i != pos;i--) {
				values[i] = values[i-1];
				ptrs[i+1] = ptrs[i];
			}
			values[pos] = element;
			keys++;
		}
		public void keyTally(){
			keys = 0;
			for (int i = 0;i<m-1;i++) {
				if (values[i] != null) keys++;
			}
			if (ptrs[0] != null) leaf = false;
		}
		public String toString(){
			String toS = "";
			for (int a=0;a<m-1;a++) {
				toS = toS + "[";
				if (values[a] != null) toS = toS + values[a];  
				toS = toS + "]";
			}
			return toS;
		}
		public boolean underflow() {
			keyTally();
			if (keys < ((m-1)*2)/3) return true;
			return false;
		}
		
	}
	
	
	public BStarTree(int m)
	{
		this.m = m;
		root = null;
		/*
		The constructor.  You must create a BStarTree object of order m,
		where m is passed as a parameter to the constructor.
		*/
	}
	
	public boolean insertElement(int element)
	{
		/*
		The int element passed as a parameter should be inserted in
		your B*-Tree.  The method should return true after a 
		successful insert, and false otherwise.
		*/
		return insertElement(new Integer(element));
		
	}
	
	public boolean insertElement(Integer element)
	{
		/*
		The Integer object passed as a parameter should be inserted in
		your B*-Tree.  The method should return true after a 
		successful insert, and false otherwise.
		*/
		if (root == null) {
			root = new Node(element);
			return true;
		}
		
		Node temp = root;
		int a =0;
		while(true) {
			if (temp == null) break;
			for(a=0;a<temp.keys;a++) {	
				if (temp.values[a].equals(element)) return false;
				if (temp.values[a] > element) {
					temp = temp.ptrs[a];
					break;
				}
			}
			if (temp != null) if (a == temp.keys)	temp = temp.ptrs[temp.keys];
		}
		
		temp = findNode(element,root);
		if(!temp.isFull()) {
			int pos = findPos(element,temp);
			if (pos != -1) temp.insert(element, pos);
			else return false;
			return true;	
		}
		
		if (temp == root) {
			rootsplit(element,null,null);
			return true;
		}
		
		Node left = findLeft(temp);
		if (left != null) {
			if (!left.isFull()) {
				merge(left,temp,element);
				return true;
			}
		}
		Node right = findRight(temp);
		if (right != null) {
			if (!right.isFull()) {
				merge(temp,right,element);
				return true;
			}
		}
		
		if (right != null) {
			split(temp,right,element,null);
			return true;
		}
		if (left != null) {
			split(left,temp,element,null);
			return true;
		}
		
		
		System.out.println(element);
		System.out.println(": Error");
		return false;
	}
	
	private void rootsplit(Integer element, Node lost, Node left) {
		Integer[] arr = new Integer[root.values.length+1];
		for (int b=0;b<root.values.length;b++) {
			arr[b] = root.values[b];
		}
		int a = 0;
		while (arr[a] < element) {
			if (arr[++a] == null) break;
			//if (a == arr.length) break;
		}
		
		for (int b=arr.length-1;b>a;b--) {
			arr[b] = arr[b-1];
		}
		arr[a] = element;
		int mid = arr.length/2;
		a = 0;
		Node node2 = new Node(null);
		for (int c=0;c <root.values.length;c++) root.values[c] = null;
		for (int b=0;b<mid-1;b++) {
			if (b==mid) a++;
			root.values[b] = arr[a++];
			node2.values[b] = arr[a+mid];
		}
		root.values[mid-1] = arr[a++];
		if (mid*2 != arr.length) node2.values[mid-1] = arr[mid+mid];
		for (int b=0;b<mid-1;b++) {
			node2.ptrs[b] = root.ptrs[mid+b+1];
			root.ptrs[mid+b+1] = null;
		}
		if (mid*2 != arr.length) {
			node2.ptrs[mid-1] = root.ptrs[mid+mid];
			root.ptrs[mid+mid] = null;
		}

		Node temp = new Node(arr[mid]);
		root.keyTally();
		node2.keyTally();
		temp.ptrs[0] = root;
		temp.ptrs[1] = node2;
		root = temp;
		root.leaf = false;
		
		if (lost != null) {
			if (lost.values[0] > root.values[0]) {
				int pos = findParentPos(left) +1;
				for (a = node2.ptrs.length-1;a > pos;a--) {
					node2.ptrs[a] = node2.ptrs[a-1];
				}
				node2.ptrs[pos] = lost;
			}
			else {
				int pos = findParentPos(left) +1;
				for (a = root.ptrs[0].ptrs.length-1;a > pos;a--) {
					root.ptrs[0].ptrs[a] = node2.ptrs[a-1];
				}
				root.ptrs[0].ptrs[pos] = lost;
			}
		}
		
	}
	
	
	private void split(Node node1,Node node2, Integer element,Node lost) {
		Node node3 = new Node(null);
		Node parent = findParent(node1);
		Integer[] arr = new Integer[node1.values.length+1+node2.values.length+1];
		Node[] arr2 = new Node[arr.length+2];
		for (int a = 0; a <node1.keys;a++) {
			arr[a] = node1.values[a];
			arr2[a] = node1.ptrs[a];
		}
		arr2[node1.keys] = node1.ptrs[node1.keys];
		for (int a = 0;a < node2.keys;a++) {
			arr[a+node1.keys] = node2.values[a];
			arr2[a+node1.keys+1] = node2.ptrs[a];
		}
		arr2[node1.keys+node2.keys+1] = node2.ptrs[node2.keys];
		int a = 0;
		while (arr[a] < element) {
			if (arr[++a] == null) break;
			if (a > arr.length) break; 
		}		
		for (int b=arr.length-1;b>a;b--) {
			arr[b] = arr[b-1];
		}
		arr[a] = element;
		int pos = findParentPos(node1);
		a =0;
		while (arr[a] < parent.values[pos]) {
			if (++a > arr.length) break;
		}
		for (int b=arr.length-1;b>a;b--) {
			arr[b] = arr[b-1];
		}
		arr[a] = parent.values[pos];
		a = 0;
		if (lost != null) {
			while (lost.values[0] > arr2[a].values[0]) {
				if (++a > arr2.length) break;
				if (arr2[a] == null) break;
			}
			for (int b=arr2.length-1;b>a;b--) {
				arr2[b] = arr2[b-1];
			}
			arr2[a] = lost;
		}
		

		int length = arr.length/3;
		int b = 0;
		
		
		for (int c=0;c <node1.values.length;c++) node1.values[c] = null;
		for (int c=0;c <node1.ptrs.length;c++) node1.ptrs[c] = null;
		for (a=0;a<length;a++) {
			node1.values[a] = arr[b++];
			node1.ptrs[a] = arr2[a];
		}
		node1.ptrs[length] = arr2[length];
		Integer first = arr[b++];  
		for (int c=0;c <node2.values.length;c++) node2.values[c] = null;
		for (int c=0;c <node2.ptrs.length;c++) node2.ptrs[c] = null;
		for (a=0;a<length-1;a++) {
			node2.values[a] = arr[b++];
			node2.ptrs[a] = arr2[a+length+1];
		}
		Integer second = arr[b++];
		node2.ptrs[length-1] = arr2[length+length];
		for (a=0;a<length-1;a++) {
			node3.values[a] = arr[b++];
			node3.ptrs[a] = arr2[length + length + a + 1];
		}
		node3.ptrs[length-1] = arr2[length + length + length];
		if (2*(m/2) == m) node3.values[length-1] = arr[arr.length-1];
		node1.keyTally();
		node2.keyTally();
		node3.keyTally();
		parent.values[pos] = first;
		if (!parent.isFull()) {
			parent.insert(second, pos+1);
			parent.ptrs[pos+2] = node3;
			return;
		}
		
		
		if (parent == root) {
			rootsplit(second,node3,node2);
			return;
		}
		
		Node parentRight = findRight(parent);
		if (parentRight != null) {
			if (!parentRight.isFull()) {
				merge(parent,parentRight,second);
				for (a=0;a<parent.keys;a++) {
					if (node3.values[0] < parent.values[a]) break;
				}
				for (b=parent.keys;b>a;b--) {
					parent.ptrs[b] = parent.ptrs[b-1];
				}
				parent.ptrs[a] = node3;
				return;
			}
			split(parent,parentRight,second,node3);
			return;
		}
		Node parentLeft = findLeft(parent);
		if (parentLeft != null) {
			if (!parentLeft.isFull()) {
				merge(parentLeft,parent,second);
				for (a=0;a<parent.keys;a++) {
					if (node3.values[0] < parent.values[a]) break;
				}
				for (b=parent.keys;b>a;b--) {
					parent.ptrs[b] = parent.ptrs[b-1];
				}
				parent.ptrs[a] = node3;
				return;
			}
			split(parentLeft,parent,second,node3);
			return;
		}
		
		
	}
	
	private int findParentPos(Node node) {
		Node parent = findParent(node);
		for(int i = 0; i<parent.ptrs.length;i++) {
			if(parent.ptrs[i] == node) {
				return  i;
			}
		}
		return parent.ptrs.length;
	}
	
	private void merge(Node left, Node right, Integer element) {
		Integer[] both = new Integer[left.keys+right.keys+2];
		Node[] both2 = new Node[left.keys+right.keys+3];
		int a=0,b=0;
		Node parent = findParent(left);
		int pos = findParentPos(left);

		
		if (element > parent.values[pos]) {
			for (int i = 0;i < left.keys;i++) {
				if (left.values[i] > parent.values[pos]) {
					both[a++] = parent.values[pos];
					parent.values[pos] = 0;
				}
				if (left.values[i] > element) {
					both[a++] = element;
					element = 0;
				}
				both[a++] = left.values[i];
				both2[b++] = left.ptrs[i];
			}
			both2[b++] = left.ptrs[left.keys];
		}
		
		else {
			for (int i=0;i<left.keys;i++) {
				if (left.values[i] > element) {
					both[a++] = element;
					element = Integer.MAX_VALUE;
				}
				if (left.values[i] > parent.values[pos]) {
					both[a++] = parent.values[pos];
					parent.values[pos] = Integer.MAX_VALUE;
				}
				both[a++] = left.values[i];
				both2[b++] = left.ptrs[i];
			}
			both2[b++] = left.ptrs[left.keys];
		}
		
		if (element > parent.values[pos]) {
			for (int i = 0;i < right.keys;i++) {
				if (right.values[i] > parent.values[pos]) {
					both[a++] = parent.values[pos];
					parent.values[pos] = Integer.MAX_VALUE;
				}
				if (right.values[i] > element) {
					both[a++] = element;
					element = Integer.MAX_VALUE;
				}
				both[a++] = right.values[i];
				both2[b++] = right.ptrs[i];
			}
			both2[b++] = right.ptrs[right.keys];
		}
		
		else {
			for (int i=0;i<left.keys;i++) {
				if (left.values[i] > element) {
					both[a++] = element;
					element = 0;
				}
				if (left.values[i] > parent.values[pos]) {
					both[a++] = parent.values[pos];
					parent.values[pos] = 0;
				}
				both[a++] = right.values[i];
				both2[b++] = right.ptrs[i];
			}
			both2[b++] = right.ptrs[right.keys];
		}
		if (element != Integer.MAX_VALUE) {
			if (element > both[a-1]) both[a] = element; 
		}
		int mid = both.length/2;
		for (int c=0;c <left.values.length;c++) left.values[c] = null;
		for (int c=0;c <left.ptrs.length;c++) left.ptrs[c] = null;
		for (int c=0;c <right.values.length;c++) right.values[c] = null;
		for (int c=0;c <right.ptrs.length;c++) right.ptrs[c] = null;
		for (int i=0;i < mid;i++) {
			left.values[i] = both[i];
			left.ptrs[i] = both2[i];
		}
		left.ptrs[mid] = both2[mid];
		parent.values[pos] = both[mid];
		for (int i = 0; i < mid-1;i++){
			right.values[i] = both[mid+i+1];
			right.ptrs[i] = both2[mid+i+1];
		}
		if (both.length > mid*2) {
			right.values[mid-1] = both[mid*2];
			right.ptrs[mid-1] = both2[mid+mid];
		}
		//right.ptrs[mid] = both2[mid+mid];
		left.keyTally();
		right.keyTally();
	}
	
	private Node findLeft(Node node) {
		Node parent = findParent(node);
		int i;
		if (parent == null) return null;
		for (i=0;i<m;i++) {
			if (parent.ptrs[i] == node) {
				if (i == 0) return null;
				return parent.ptrs[i-1];
			}
		}
		return null;
	}
	
	private Node findRight(Node node) {
		Node parent = findParent(node);
		int i;
		if (parent == null) return null;
		for (i=0;i<m-1;i++) {
			if (parent.ptrs[i] == node) {
				if (i == m-1) return null;
				return parent.ptrs[i+1];
			}
		}
		return null;
	}
	
	private Node findParent(Node node) {
		if (root == node) return null;
		Node temp = root;
		Boolean bool = false;
		while(true) {
			bool = false;
			for (int i=0;i < m-1;i++) {
				if (temp.values[i] == null) {
					if (temp.ptrs[i] == node) return temp;
					temp = temp.ptrs[i];
					bool = true;
					break;
				}
				if (temp.values[i] > node.values[0]) {
					if (temp.ptrs[i] == node) return temp;
					temp = temp.ptrs[i];
					bool = true;
					break;
				}
			}
			if (!bool) {
				if (temp.ptrs[m-1] == node) return temp;
				temp = temp.ptrs[m-1];
			}
		}
	}
	
	private Node findNode(Integer element, Node node) {
		if (node.leaf) return node;
		for (int i=0;i<m-1;i++) {
			if (node.values[i] == null) {
				return findNode(element,node.ptrs[i]);
			}
			if (node.values[i] > element) {
				if (node.ptrs[i] == null) {
					node.ptrs[i] = new Node(null);
					return node.ptrs[i];
				}
				return findNode(element,node.ptrs[i]);
			}
			if (node.ptrs[i+1] == null) {
				node.ptrs[i+1] = new Node(null);
				return findNode(element,node.ptrs[i+1]);
			}
		}
		if (node.ptrs[m-1] == null) {
			node.ptrs[m-1] = new Node(null);
			return node.ptrs[m-1];
		}
		return findNode(element,node.ptrs[m-1]);
	}
	
	private int findPos(Integer element, Node node) {
		for (int i = 0;i<m-1;i++) {
			if (node.values[i] == null) return i;
			if (node.values[i] > element) return i;
		}
		return m-1;
	}
	
	public boolean deleteElement(int element)
	{
		/*
		The int element passed as a parameter should be deleted from
		your B*-Tree.  The method should return true after a 
		successful delete, and false otherwise.
		*/
		return deleteElement (new Integer(element));	
	}
	
	public boolean deleteElement(Integer element)
	{
		/*
		The Integer object passed as a parameter should be deleted from
		your B*-Tree.  The method should return true after a 
		successful delete, and false otherwise.
		*/
		Node node = find(root,element);
		if (node == null) return false;
		
		if (node.values[1] == null && node.ptrs[0] == null) {
			Node parent = findParent(node);
			int pos = findParentPos(node);
			if (parent.values[pos+1] != null) {
				node.values[0] = parent.values[pos];
				for (int a =pos;a<parent.keys-1;a++) parent.values[a] = parent.values[a+1];
				parent.values[parent.keys-1] = null;
				parent.keyTally();
				return true;
			}
			parent.ptrs[pos] = null;
			if (parent.ptrs[pos+1] != null) {
				parent.ptrs[pos] = parent.ptrs[1];
				parent.ptrs[pos+1] = null;
				Integer temp = parent.values[pos];
				parent.values[pos] = parent.ptrs[pos].values[parent.ptrs[pos].keys-1];
				for (int a=parent.ptrs[pos].keys-1;a>0;a--) {
					parent.ptrs[pos].values[a] = parent.ptrs[pos].values[a-1];
				}
				parent.ptrs[pos].values[0] = temp;
				if (parent.ptrs[0] == null) parent.leaf = true;
			}
			return true;
		}
		
		int pos;
		for (pos=0;pos<node.keys;pos++) {
			if (node.values[pos].equals(element)) break;
		}
		if (node.ptrs[pos] == null) {
			node.values[pos] = null;
			if (pos != node.keys-1) {
				for (int a=pos;a<node.keys-1;a++) {
					node.values[a] = node.values[a+1];
				}
				node.values[node.keys-1] = null;
			}

			if (node.underflow()) merge2(node);
		}
		else {
			Node temp = node.ptrs[pos];
			while(true) {
				if (temp.ptrs[temp.keys] == null) break;
				temp = temp.ptrs[temp.keys];
			}
			node.values[pos] = temp.values[temp.keys-1];
			temp.values[temp.keys-1] = null;
			if (temp.underflow()) merge2(temp);
		}
		
		return true;
	}
	
	
	private void merge2(Node node) {
		if (root == node) return;
		Node left = findLeft(node);
		int test = m/3;
		test = test*2;
		if (2*(m/2) != m) test--;
		
		if (left != null) {
			if (left.keys > test) {
				Integer arr[] = new Integer[node.keys + left.keys+1];
				Node arr2[] = new Node[arr.length+1];
				int b=0;
				for (int a=0;a<left.keys;a++) {
					arr2[b] = left.ptrs[a];
					arr[b++] = left.values[a];
				}
				Node parent = findParent(node);
				int pos = findParentPos(left);
				arr2[b] = left.ptrs[left.keys];
				arr[b++] = parent.values[pos];
				for (int a=0;a<node.keys;a++) {
					arr2[b] = node.ptrs[a];
					arr[b++] = node.values[a];
				}
				arr2[b] = node.ptrs[node.keys];
				for (int c=0;c <left.values.length;c++) left.values[c] = null;
				for (int c=0;c <node.values.length;c++) node.values[c] = null;
				for (int c=0;c <left.ptrs.length;c++) left.ptrs[c] = null;
				for (int c=0;c <node.ptrs.length;c++) node.ptrs[c] = null;
				b=0;
				for (int a=0;a<arr.length/2;a++) {
					left.ptrs[a] = arr2[b];
					left.values[a] = arr[b++];
				}
				left.ptrs[arr.length/2] = arr2[b];
				parent.values[pos] = arr[b++];
				for (int a=0;a<(arr.length/2)-1;a++) {
					node.ptrs[a] = arr2[b];
					node.values[a] = arr[b++];
				}
				node.ptrs[(arr.length/2)-1] = arr2[b];
				if (b != arr.length) {
					node.values[(arr.length/2)-1] = arr[b++];
					node.ptrs[arr.length/2] = arr2[b];
				}
				node.keyTally();
				left.keyTally();
				return;
			}
		}
		Node right = findRight(node);
		if(right != null) {
			if (right.keys > test){
				Integer arr[] = new Integer[node.keys + right.keys+1];
				Node arr2[] = new Node[arr.length+1];
				int b=0;
				for (int a=0;a<node.keys;a++) {
					arr2[b] = node.ptrs[a];
					arr[b++] = node.values[a];
				}
				Node parent = findParent(node);
				int pos = findParentPos(node);
				arr2[b] = node.ptrs[node.keys];
				arr[b++] = parent.values[pos];
				for (int a=0;a<right.keys;a++) {
					arr2[b] = right.ptrs[a];
					arr[b++] = right.values[a];
				}
				arr2[b] = right.ptrs[right.keys];
				for (int c=0;c <right.values.length;c++) right.values[c] = null;
				for (int c=0;c <right.ptrs.length;c++) right.ptrs[c] = null;
				for (int c=0;c <node.values.length;c++) node.values[c] = null;
				for (int c=0;c <node.ptrs.length;c++) node.ptrs[c] = null;
				b=0;
				for(int a=0;a<arr.length/2;a++) {
					node.ptrs[a] = arr2[b];
					node.values[a] = arr[b++];
				}
				node.ptrs[arr.length/2] = arr2[b];
				parent.values[pos] = arr[b++];
				for (int a=0;a<(arr.length/2)-1;a++) {
					right.ptrs[a] = arr2[b];
					right.values[a] = arr[b++];
				}
				right.ptrs[(arr.length/2)-1] = arr2[b];
				if (2*(arr.length/2) != arr.length) {
					right.ptrs[arr.length/2] = arr2[b+1];
					right.values[(arr.length/2)-1] = arr[b];
				}
				node.keyTally();
				right.keyTally();
				return;
			}
		}
		if (left != null && right != null) {
			split2(left,node,right);
			return;
		}
		if (left == null && right == null) {
			if (node.values[0] == null) {
				findParent(node).ptrs[findParentPos(node)] = null;
				return;
			}
		}
		
		if(left == null && findRight(right) != null) {
			Node parent = findParent(node);
			int pos = findParentPos(node);
			node.values[node.keys] = parent.values[pos];
			node.ptrs[node.keys+1] = right.ptrs[0];
			parent.values[pos] = right.values[0];
			for (int a=0;a<right.keys-1;a++) {
				right.ptrs[a] = right.ptrs[a+1];
				right.values[a] = right.values[a+1];
			}
			right.ptrs[right.keys-1] = right.ptrs[right.keys];
			right.values[right.keys-1] = null;
			right.ptrs[right.keys] = null;
			node.keyTally();
			right.keyTally();
			merge2(right);
			return;
		}
		if (right == null && findLeft(left) != null) {
			Node parent = findParent(node);
			int pos = findParentPos(node);
			for(int a=node.keys;a>0;a--) {
				node.ptrs[a+1] = node.ptrs[a];
				node.values[a] = node.values[a-1];
			}
			node.ptrs[1] = node.ptrs[0];
			node.values[0] = parent.values[pos-1];
			node.ptrs[0] = left.ptrs[left.keys];
			parent.values[pos-1] = left.values[left.keys-1];
			left.values[left.keys-1] = null;
			left.ptrs[left.keys] = null;
			node.keyTally();
			left.keyTally();
			merge2(left);
			return;
		}
	}
	
	
	private void split2(Node node1,Node node2,Node node3) {
		Integer arr[] = new Integer[node1.keys + node2.keys + node3.keys +2];
		Node arr2[] = new Node[arr.length+1];
		Node parent = findParent(node1);
		int pos = findParentPos(node1);
		int b=0;
		for (int a=0;a<node1.keys;a++) {
			arr2[b] = node1.ptrs[a];
			arr[b++] = node1.values[a];
		}
		arr2[b] = node1.ptrs[node1.keys];
		arr[b++] = parent.values[pos];
		for (int a=0;a<node2.keys;a++) {
			arr2[b] = node2.ptrs[a];
			arr[b++] = node2.values[a];
		}
		arr2[b] = node2.ptrs[node2.keys];
		arr[b++] = parent.values[pos+1];
		for (int a=0;a<node3.keys;a++) {
			arr2[b] = node3.ptrs[a];
			arr[b++] = node3.values[a];
		}
		arr2[b] = node3.ptrs[node3.keys];
		int length = arr.length/2;
		b=0;
		for (int a=0;a<length-1;a++) {
			node1.ptrs[a] = arr2[b];
			node1.values[a] = arr[b++];
		}
		node1.ptrs[length-1] = arr2[b];
		if (2*(m/2) == m) {
			node1.values[length-1] = arr[b++];
			node1.ptrs[length] = arr2[b];
		}
		parent.values[pos] = arr[b++];
		for (int a=0;a<length-1;a++) {
			node2.ptrs[a] = arr2[b];
			node2.values[a] = arr[b++];
		}
		node2.ptrs[length-1] = arr2[b];
		if (2*(m/2) == m) {
			node2.values[length-1] = arr[b++];
			node2.ptrs[length] = arr2[b];
		}
		if (b+2 == arr.length) {
			node2.values[length-1] = arr[b++];
			node2.values[length] = arr[b++];
			node2.ptrs[length] = arr2[b-1];
			node2.ptrs[length+1] = arr2[b];
		}
		if (b != arr.length) {
			node2.values[length-1] = arr[b++];
			node2.ptrs[length] = arr2[b];
		}
		for (int a=pos+1;a<parent.keys-1;a++) {
			parent.values[a] = parent.values[a+1];
			parent.ptrs[a+1] = parent.ptrs[a+2];
		}
		parent.values[parent.keys-1] = null;
		parent.ptrs[parent.keys] = null;
		parent.keyTally();
		node1.keyTally();
		node2.keyTally();
		if (parent.underflow()) {
			merge2(parent);
		}
	}
	
	
	
	public String search(int element)
	{
		/*
		A String should be returned representing the search path
		for the element sent in as a parameter.  Refer to the
		specification for more details.
		*/
		return search(new Integer(element));
	}
	
	public String search(Integer element)
	{
		/*
		A String should be returned representing the search path
		for the element sent in as a parameter.  Refer to the
		specification for more details.
		*/
		if (root == null) return "*NULL*";
		String str = "";
		Node temp = root;
		int a =0;
		while(true) {
			if (temp == null) return str + "*NULL*";
			str = str + temp.toString() + ",";
			for(a=0;a<temp.keys+1;a++) {
				if (a == temp.keys) {
					temp = temp.ptrs[temp.keys];
					break;
				}
				if (temp.values[a].equals(element)) return str.substring(0, str.length()-1);
				if (temp.values[a] > element) {
					temp = temp.ptrs[a];
					break;
				}
			}
		}
		
		
		
		//return str;
	}
	
	public int height()
	{
		/*
		This method should return the height of the tree.
		*/
		return  height2(root);
	}
	
	private int height2(Node node) {
		if (node == null) return 0;
		int h = 0;
		for (int a = 0;a < node.keys; a++) {
			int n = height2(node.ptrs[a]) + 1; 
			if (n > h) h = n;
		}
		return h;
	}
	
	public int fullness()
	{
		/*
		This method should return as a percentage the fullness of the tree.
		The percentage is out of 100 and if, for example, 70 is returned,
		it means that the tree is 70% full.
		A tree containing no keys is 0% full.
		*/
		
		return 0;
	}
	
	public String breadthFirst()
	{
		/*
		This method returns a String containing the nodes in breath-first
		order.  You should not include null nodes in the string.  The string
		for an empty tree is simply "".
		*/
		String toS = "";
		Node temp = root;
		Queue queue = new Queue();
		if (temp != null) {
			queue.enqueue(temp);
			while (!queue.isEmpty()) {
				temp = queue.dequeue();
				toS = toS + temp.toString() + ",";
				if (!temp.leaf) {
					for (int a =0;a<temp.keys+1;a++) {
						queue.enqueue(temp.ptrs[a]);
					}
				}
			}
			toS = toS.substring(0, toS.length()-1);
			return toS;				
		}
		return null;
	}
	
	private Node find(Node node, Integer element) {
		if (node == null) return null;
		for (int a = 0;a < node.keys;a++) {
			if (node.values[a].equals(element)) return node;
			if (node.values[a] > element) return find(node.ptrs[a],element);
		}
		return find(node.ptrs[node.keys],element);
	}
	
}
