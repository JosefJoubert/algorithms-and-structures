public class Trie {
	
	protected char[] letters;
	protected Node root = null;
  
  /**
   * Initializes the empty trie.
   * @param _letters - The list of characters that the trie should be able to handle
   * (including the end-of-word character, which is stored at the 0th index")
   * It is assumed that _letters is already sorted.
   */
	public Trie(char[] _letters) {
		letters = new char[_letters.length+1];
		letters[0] = '#';
		for (int k = 0; k < _letters.length; k++) {
		  letters[k+1] = _letters[k];
		}
	}
  
  
  /**
   * Converts the given Node to its string representation
   * @param Node - The Node being printed
   */
	public String nodeToString(Node node) {
		if (node.isLeaf) {
				return node.key;
		}
		else {
			String res = "";
			for (int k = 0; k < node.ptrs.length; k++) {
				if (node.ptrs[k] == null) {
					res += "("+character(k)+",0)  ";
				} else {
					res += "("+character(k) + ",1)  ";
				}
			}
			return res;
		}
	}
  
  /**
   * Prints all the nodes in the tree in a breadth-first fashion.
   */
  public void print() {
		Queue queue = new Queue(10);
		
		Node n = root;
		if (n != null) {
			queue.enq(n);
			while (!queue.isEmpty()){
				n = queue.deq();
				System.out.println(nodeToString(n));
				for (int k = 0; k < n.ptrs.length; k++) {
					if (n.ptrs[k] != null)
						queue.enq(n.ptrs[k]);
				}
			}
		}
	}
  
  /**
   * A helper function that finds the index of the given character in the 'letters' array.
   * This can be used to find the index at which a node should be inserted into a ptrs array.
   */
  protected int index(char c) {
		for (int k = 0; k < letters.length; k++) {
			if (letters[k] == c) {
				return k;
			}
		}
		return -1;
	}
  
	/**
   * A helper function that finds the character for a given index
   * This can be used to find the character being represented by an index in a ptrs array
   */
	protected char character(int i) {
		return letters[i];
	}
  
  // Do NOT modify any of the code above this line
  //Implement all of the functions below this line
  
  
   /**
   * Inserts the given word into the trie.
   * @param word the word that is being inserted into the trie
   */
	public void insert(String key) {
		if (root == null) {
			root = new Node(letters.length);
		}
		Node temp = root;
		int a = 0;
		while(true) {
			if (a == key.length()) {
				temp.endOfWord = true;
				temp.ptrs[0] = new Node(key,letters.length);
				return;
			}
			int temp2 = index(key.charAt(a));
			if (temp.ptrs[temp2] == null) {
				temp.ptrs[temp2] = new Node(key,letters.length);
				return;
			}
			if (temp.ptrs[temp2].isLeaf) {
				if (key == temp.ptrs[temp2].key) return;
				String tempkey = temp.ptrs[temp2].key;
				temp.ptrs[temp2] = new Node(letters.length);
				temp = temp.ptrs[temp2];
				while (key.charAt(a) == tempkey.charAt(a++)) {
					if (a == key.length()) {
						temp.ptrs[temp2] = new Node(tempkey,letters.length);
						temp.endOfWord = true;
						temp.ptrs[0] = new Node(key,letters.length);
						return;
					}
					
					else if (a == tempkey.length()) {
						temp.ptrs[temp2] = new Node(key,letters.length);
						temp.endOfWord = true;
						temp.ptrs[0] = new Node(tempkey,letters.length);
						return;
					}
					int itemp = index(key.charAt(a));
					temp.ptrs[itemp] = new Node(letters.length);
					temp = temp.ptrs[itemp];
				}
				temp2 = index(key.charAt(--a));
				
				temp.ptrs[temp2] = new Node(key,letters.length);
				temp.ptrs[index(tempkey.charAt(a))] = new Node(tempkey,letters.length);
				return;
			} 
			else {
				temp = temp.ptrs[temp2];
				a++;
			}
			
		}
		
		
 	}
  
 /**
   * @param word the word that is being searched for in the trie.
   * Returns true if the element is found in the trie.
   * Returns false if the element is not in the trie.
   */
  public boolean elementOf(String key) {
	Node temp = root;
	int a = 0;
    while (true) {
    	if (temp == null) return false;
    	if (temp.isLeaf == true) {
    		if (temp.key == key) return true;
    		return false;
    	}
    	if (a == key.length()) {
    		if (temp.endOfWord == false) return false;
    		if (temp.ptrs[0].key == key) return true;
    		return false;
    	}
    	
    	temp = temp.ptrs[index(key.charAt(a++))];
    }
  }
   /**
   * This method searches the trie for all words that start with the given prefix and prints them, one per line using System.out.println
   * @param prefix - The prefix used to search the trie.
   */
  public void keysWithPrefix(String prefix) {
	  Node temp = root;
	  for (int a=0; a<prefix.length(); a++) {
		  if (temp == null) return;
		  int itemp = index(prefix.charAt(a++));
		  temp = temp.ptrs[itemp];
	  }
	  DFS(temp);
  }
  
  public void DFS(Node node) {
	  if (node == null) return;
	  if (node.isLeaf) System.out.println(node.key);
	  else for (int a=0;a<letters.length;a++) {
		  DFS(node.ptrs[a]);
	  }
}
  
}