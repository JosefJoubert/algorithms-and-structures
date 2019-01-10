
/*
Complete your details...
Name and Surname: JH Joubert
Student/staff Number: 12048918
*/

/*You must complete this class to create a fully functional B*-Tree containing
Integer objects.  Additional instructions are provided in comments throughout the class*/
public class BStarTree
{
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
		int m;
		Integer[] values; 
		Node[] ptrs;
		Node(Integer k,int m) {
			this.m = m;
			values = new Integer[m-1];
			ptrs = new Node[m];
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
			if (keys < ((2*m-1))/3) return true;
			return false;
		}
		
	}

	
	
	public BStarTree(int m)
	{
		if (m % 2 == 0) {
			System.out.println("Invalid tree");
			System.exit(0);
		}
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
		//If root is null
		if (root == null) {
			root = new Node(element,2*((2*m-1)/3) + 2);
			return true;
		}
		//If root is leaf
		if (root.leaf) {
			for (int i = 0; i < root.keys; i++){
				if (root.values[i].equals(element)) return false;
			}
			if (root.keys == root.m-1) {
				rootsplit(element);
				return true;
			}
			else {
				for (int i=0;i<root.keys;i++){
					if (root.values[i].compareTo(element) > 0) {
						root.insert(element, i);
						return true;
					}
				}
				root.insert(element, root.keys);
			}
			return true;
		}
		//If root is not leaf
		Node leaf = findNode(element,root);
		if (leaf.keys == leaf.m-1){ //leaf is full
			Node parent = findParent(leaf);
			int parentpos = findParentPos(leaf,parent);
			if (parentpos != 0) {
				if (parent.ptrs[parentpos-1] != null){	
					if (parent.ptrs[parentpos-1].keys != parent.ptrs[parentpos-1].m-1){
						share(parent.ptrs[parentpos-1],leaf,parent,parentpos-1,element);
						return true;
					}
				}
			}
			if (parentpos != parent.m-1) {
				if (parent.ptrs[parentpos+1] != null){
					if (parent.ptrs[parentpos+1].keys != parent.ptrs[parentpos+1].m-1){
						share(leaf,parent.ptrs[parentpos+1],parent,parentpos,element);
						return true;
					}
				}
			}
			nodesplit(leaf,parent,parentpos,element);
			return true;
		}
		for (int a = 0;a<leaf.keys;a++){
			if (leaf.values[a].compareTo(element) > 0){
				leaf.insert(element, a);
				return true;
			}
		}
		leaf.insert(element, leaf.keys);
		return true;
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
		int a;
		for (a = 0;a<node.keys+1;a++){
			if (a == node.keys) return false;
			if (node.values[a].equals(element)) break; 
		}
		if (node.leaf){
			for (int b = a+1;b<node.keys;b++){
				node.values[b-1] = node.values[b];
			}
			node.values[node.keys-1] = null;
			if (root == node) {
				root.keyTally();
				return true;
			}
			if(node.underflow()){ 
				if (node.keys == 0){
					Node parent = findParent(node);
					int pos = findParentPos(node,parent);
					parent.ptrs[pos] = null;
					return true;
				}
				merge(node);
			}
		}
		else {
			Node temp = node.ptrs[a];
			while(!temp.leaf){
				temp = temp.ptrs[temp.keys];
			}
			node.values[a] = temp.values[temp.keys-1];
			temp.values[temp.keys-1] = null;
			if (temp.underflow()) {
				if (temp.keys == 0){
					Node parent = findParent(temp);
					int pos = findParentPos(temp,parent);
					parent.ptrs[pos] = null;
					return true;
				}
				merge(temp);
			}
		}
		return true;
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
	}
	
	public int height()
	{
		return  height2(root);
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
	
	private void merge(Node node){
		Node parent = findParent(node);
		int pos = findParentPos(node,parent);
		if (pos != 0){
			if (parent.ptrs[pos-1] != null){
				if (parent.ptrs[pos-1].keys > node.keys+1){
					share2(parent.ptrs[pos-1],node,parent,pos);
					return;
				}
			}
		}
		if (pos != parent.m-1){
			if (parent.ptrs[pos+1] != null){
				if (parent.ptrs[pos+1].keys > node.keys+1){
					share2(node,parent.ptrs[pos+1],parent,pos);
					return;
				}
			}
		}
		Node left,right;
		if(pos == 0){
			left = node;
			node = parent.ptrs[pos+1];
			right = parent.ptrs[pos+2];
			
		}
		else if(pos == parent.m-1 || pos == parent.keys){
			right = node;
			node = parent.ptrs[pos-1];
			left = parent.ptrs[pos - 2];
			pos=pos-2;
		}
		else{
			left = parent.ptrs[pos-1];
			right = parent.ptrs[pos+1];
			pos--;
		}
		if(left == null) return;
		if(node == null) return;
		if(right == null) return;
		Integer arr[] = new Integer[left.keys+node.keys+right.keys+2];
		int b=0;
		for (int a=0;a<left.keys;a++){
			arr[b++] = left.values[a];
		}
		arr[b++] = parent.values[pos];
		for (int a=0;a<node.keys;a++){
			arr[b++] = node.values[a];
		}
		arr[b++] = parent.values[pos+1];
		for (int a=0;a<right.keys;a++){
			arr[b++] = right.values[a];
		}
		int mid = arr.length/2;
		b=0;
		for (int a = 0;a<mid;a++){
			left.values[a] = arr[b++];
		}
		parent.values[pos] = arr[b++];
		parent.values[pos+1] = null;
		mid = arr.length-b;
		for (int a=0;a<mid;a++){
			node.values[a] = arr[b++];
		}
		parent.ptrs[pos+2] = null;
		
		
	}
	
	private Node find(Node node, Integer element) {
		if (node == null) return null;
		for (int a = 0;a < node.keys;a++) {
			if (node.values[a].equals(element)) return node;
			if (node.values[a] > element) return find(node.ptrs[a],element);
		}
		return find(node.ptrs[node.keys],element);
	}
	
	private void rootsplitagain(Node node,Integer element){
		Node newroot = new Node(null,root.m);
		Node left = new Node(null,m);
		Node right = new Node(null,m);
		left.leaf = false;
		right.leaf = false;
		newroot.leaf = false;
		Integer arr[] = new Integer[root.keys + 1];
		int b = 0;
		for (int a = 0;a<root.keys/2;a++){
			if (element.compareTo(root.values[a]) < 0){
				arr[b++] = element;
				element = Integer.MAX_VALUE;
			}
			arr[b++] = root.values[a];
		}
		for (int a = root.keys/2;a<root.keys;a++){
			if(element.compareTo(root.values[a]) < 0){
				arr[b++] = element;
				element = Integer.MAX_VALUE;
			}
			arr[b++] = root.values[a];
		}
		if (element != Integer.MAX_VALUE) arr[b] = element;
		b = 0;
		for (int a = 0;a<root.keys/2;a++){
			left.values[a] = arr[b++];
		}
		newroot.values[0] = arr[b++];
		for (int a = 0;a<root.keys/2+1;a++){
			right.values[a] = arr[b++];
		}
		newroot.ptrs[0] = left;
		newroot.ptrs[1] = right;
		left.keyTally();
		right.keyTally();
		node.keyTally();
		b=0;
		for (int a = 0;a<left.keys+1;a++){
			if(node != null && node.values[0].compareTo(root.ptrs[b].values[0]) < 0){
				left.ptrs[a++] = node;
				node = null;
			}
			left.ptrs[a] = root.ptrs[b++];
		}
		//int mid = root.m - b;
		for (int a = 0;b<root.m;a++){
			if(node != null && node.values[0].compareTo(root.ptrs[b].values[0]) < 0){
				right.ptrs[a++] = node;
				node = null;
			}
			right.ptrs[a] = root.ptrs[b++];
		}
		if (node != null){
			right.ptrs[right.keys] = node;
		}
		root = newroot;
		root.keyTally();
		
	}
	
	private void splitagain(Node left,Node unassigned,Integer element){
		if (left == root) {
			rootsplitagain(unassigned,element);
			return;
		}		
	}
	
	private void nodesplit(Node left,Node parent,int pos,Integer element){
		if (pos == parent.m-1){
			pos--;
			left = parent.ptrs[pos];
		}
		Node right = parent.ptrs[pos+1];
		if (right == null) {
			right = left;
			left = parent.ptrs[pos-1];
			pos--;
		}
		Node newnode = new Node(null,m);
		Integer[] arr = new Integer[left.keys+right.keys+2];
		int b = 0;
		for (int a = 0;a<m-1;a++){
			if (element.compareTo(left.values[a]) < 0) {
				arr[b++] = element;
				element = Integer.MAX_VALUE;
			}
			arr[b++] = left.values[a];
		}
		arr[b++] = parent.values[pos];
		for (int a=0;a<m-1;a++){
			if (element.compareTo(right.values[a]) < 0) {
				arr[b++] = element;
				element = Integer.MAX_VALUE;
			}
			arr[b++] = right.values[a];
		}
		for (int a = 0;a<m-1;a++){
			left.values[a] = null;
			right.values[a] = null;
		}
		int seperator = (2*m-1)/3;
		b = 0;
		for (int a=0;a<seperator;a++){
			left.values[a] = arr[b++];
		}
		parent.values[pos] = arr[b++];
		//seperator = 2*seperator+1;
		for (int a = 0;a<seperator;a++){
			right.values[a] = arr[b++];
		}
		
		if (parent.keys != parent.m-1){
			parent.insert(arr[b++], pos+1);
			seperator = arr.length-b;
			for (int a = 0;a<seperator;a++){
				newnode.values[a] = arr[b++];
			}
			parent.ptrs[pos+2] = newnode;
			parent.keyTally();
			
		}
		else{
			element = arr[b++];
			seperator = arr.length-b;
			for (int a = 0;a<seperator;a++){
				newnode.values[a] = arr[b++];
			}
			splitagain(parent,newnode,element);
		}
		left.keyTally();
		right.keyTally();
		newnode.keyTally();
		
		
		
	}
	
	private void share2(Node left, Node right, Node parent, int pos){
		Integer arr[] = new Integer[left.keys + right.keys + 1];
		int b =0;
		for (int a = 0;a<left.keys;a++){
			arr[b++] = left.values[a];
		}
		arr[b++] = parent.values[pos];
		int mid = (left.keys + right.keys + 1)/2;
		for (int a = 0;a<right.keys;a++){
			arr[b++] = right.values[a];
		}
		for (int a = 0;a < left.m-1;a++) {
			left.values[a] = null;
			right.values[a] = null;
		}
		b=0;
		for (int a = 0; a<mid;a++){
			left.values[a] = arr[b++];
		}
		mid = arr.length-b-1;
		parent.values[pos] = arr[b++];
		for (int a = 0; a<mid;a++){
			right.values[a] = arr[b++];
		}
		left.keyTally();
		right.keyTally();
	}
	
	private void share(Node left, Node right, Node parent, int pos,Integer element){
		Integer arr[] = new Integer[left.keys + right.keys + 2];
		int b =0;
		for (int a = 0;a<left.keys;a++){
			if (element.compareTo(left.values[a]) < 0){
				arr[b++] = element;
				element = Integer.MAX_VALUE;
			}
			arr[b++] = left.values[a];
		}
		arr[b++] = parent.values[pos];
		int mid = (left.keys + right.keys + 1)/2;
		for (int a = 0;a<right.keys;a++){
			if (element.compareTo(right.values[a]) < 0){
				arr[b++] = element;
				element = Integer.MAX_VALUE;
			}
			arr[b++] = right.values[a];
		}
		if (element != Integer.MAX_VALUE) arr[b] = element;
		for (int a = 0;a < left.m-1;a++) {
			left.values[a] = null;
			right.values[a] = null;
		}
		b=0;
		parent.values[pos] = arr[mid];
		for (int a = 0; a<mid;a++){
			left.values[a] = arr[b++];
		}
		mid = arr.length-b-1;
		b++;
		for (int a = 0; a<mid;a++){
			right.values[a] = arr[b++];
		}
		left.keyTally();
		right.keyTally();
	}
	
	private Node findNode(Integer element, Node node) {
		if (node.leaf) return node;
		for (int i=0;i<node.m-1;i++) {
			if (node.values[i] == null) {
				return findNode(element,node.ptrs[i]);
			}
			if (node.values[i].compareTo(element) > 0) {
				return findNode(element,node.ptrs[i]);
			}
		}
		return findNode(element,node.ptrs[node.m-1]);
	}
	
	private void rootsplit(Integer element){
		Node node, node1,node2;
		int a = 0;
		Integer[] arr = new Integer[root.m];
		for (int i = 0; i < root.m-1;i++){
			if (element.compareTo(root.values[a]) < 0) {
				arr[a++] = element;
				while (a<root.m){
					arr[a++] = root.values[i++];
				}
				break;
			}
			arr[a++] = root.values[i];
		}
		if (element.compareTo(root.values[root.m-2])> 0) {
			arr[root.m-1] = element;
		}
		int mid = (root.m)/2;
		node = new Node(arr[mid],root.m);
		node.leaf = false;
		node1 = new Node(arr[0],m);
		for (int i = 1;i<mid;i++){
			node1.values[i] = arr[i];
		}
		node2 = new Node(arr[mid+1],m);
		for (int i = 0;i<mid-1;i++){
			node2.values[i] = arr[mid+1+i];
		}
		node.keyTally();
		node1.keyTally();
		node2.keyTally();
		node.ptrs[0] = node1;
		node.ptrs[1] = node2;
		root = node;
	}
	
	private int findParentPos(Node node, Node parent) {
		for(int i = 0; i<parent.ptrs.length;i++) {
			if(parent.ptrs[i] == node) {
				return  i;
			}
		}
		return parent.ptrs.length;
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
			for (int i=0;i < temp.m-1;i++) {
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
				if (temp.ptrs[temp.m-1] == node) return temp;
				temp = temp.ptrs[temp.m-1];
			}
		}
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
}
