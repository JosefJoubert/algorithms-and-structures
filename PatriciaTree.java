/*
Complete your details...
Name and Surname: JH Joubert
Student/staff Number: 12048918
*/

/*You must implement a fully functional Patricia tree.*/
public class PatriciaTree
{
	/*You will have to design and implement your own node classes.
	You may add any additional fields or methods but you are not
	allowed to remove or change anything you were given apart from
	implementing the methods.*/
	protected class PNode {
		String[] values;
		int length[];
		int keys;
		PNode[] ptrs;
		Boolean leaf;
		
		public PNode() {
			values = new String[27];
			keys = 0;
			length = new int[27];
			ptrs = new PNode[27];
			leaf = true;
		}
		
		public PNode(String value) {
			values = new String[27];
			values[0] = value;
			length = new int[27];
			ptrs = new PNode[27];
			leaf = true;
		}
		
		public String toString() {
			String str = "";
			for (int a=0;a<27;a++) {
				if (values[a] != null) str = str + "[" + values[a] + "]";
			}
			return str;
		}
	}
	
	PNode root;
	
	public PatriciaTree()
	{
		/*You may add any initialization which your
		require for your trie here.  Your default constructor
		will be used to create your tree for marking*/
		
		root = null;
	}
	
	public boolean insert(String word)
	{
		/*Insert the word passed as a parameter into the tree.
		No duplicates are allowed.  If the insert fails for 
		whatever reason, you should return false. You must return 
		true to indicate a successful insert.*/
		word = word.toUpperCase();
		
		
		for (int a=0;a<word.length();a++) {
			if (!Character.isLetter(word.charAt(a))) return false;
		}
		if (search2(word) != null) return false;
		int pos = findPos(word.charAt(0));
		if (pos == -1) return false;
		if (root == null) {
			root = new PNode();
			root.values[pos] = Character.toString(word.charAt(0));
			root.ptrs[pos] = new PNode();
			root.leaf = false;
			root.ptrs[pos].values[0] = word;
			return true;
		}
		
		if (root.values[pos] == null) {
			root.values[pos] = Character.toString(word.charAt(0));
			root.ptrs[pos] = new PNode(word);
			return true;
		}
		
		PNode parent = root;
		PNode temp = root.ptrs[pos];
		int a=1;
		while (true) {
			int pos2 = findPos(word.charAt(a));
			if (parent.length[pos] != 0) {
				PNode temp2 = parent.ptrs[pos];
				while (!temp2.leaf) {
					for (int c=1;c<27;c++) {
						if (temp2.ptrs[c] != null) {
							temp2 = temp2.ptrs[c];
							break;
						}
					}
				}
				if (temp2.values[0].equals(word)) return false;
				int c=0;
				PNode temp3 = parent.ptrs[pos];
				while (word.charAt(a+c) == temp2.values[0].charAt(a+c) && a+c < parent.length[pos]) {
					
					if (a+c+1 == word.length()) {
						parent.ptrs[pos] = new PNode();
						parent.ptrs[pos].leaf = false;
						parent.ptrs[pos].values[0] = "#";
						parent.ptrs[pos].ptrs[0] = new PNode(word);
						parent.ptrs[pos].ptrs[findPos(temp2.values[0].charAt(a+c+1))] = temp3;
						parent.ptrs[pos].values[findPos(temp2.values[0].charAt(a+c+1))] = Character.toString(temp2.values[0].charAt(a+c+1));
						parent.ptrs[pos].length[findPos(temp2.values[0].charAt(a+c+1))] = parent.length[pos];
						parent.length[pos] = c+a+1;
						return true;
					}
					if (c+a+1 == parent.length[pos]) {
						pos2 = findPos(word.charAt(c+a+1));
						break;
					}
					pos2 = findPos(word.charAt(a+c+1));
					c++;
				}
				if (c+a+1 != parent.length[pos]) {
					parent.ptrs[pos] = new PNode();
				//parent.values[pos] = Character.toString(word.charAt(a));
					parent.ptrs[pos].leaf = false;
					parent.ptrs[pos].ptrs[findPos(word.charAt(a+c))] = new PNode(word);
					parent.ptrs[pos].values[findPos(word.charAt(a+c))] = Character.toString(word.charAt(a+c));
					parent.ptrs[pos].ptrs[findPos(temp2.values[0].charAt(a+c))] = temp3;
					parent.ptrs[pos].values[findPos(temp2.values[0].charAt(a+c))] = Character.toString(temp2.values[0].charAt(a+c));
					parent.ptrs[pos].length[findPos(temp2.values[0].charAt(a+c))] = parent.length[pos];
					parent.length[pos] = c+1;
					return true;
				}
				
				
			}
				
			if (a >= word.length()) {
				if (temp.ptrs[pos2] == null) {
					temp.ptrs[pos2] = new PNode();
					temp.ptrs[pos2].values[0] = word;
					return true;
				}
				if (temp.ptrs[pos2].values[0].equals(word)) return false;
				temp = temp.ptrs[pos2];
				if (temp.leaf) {
					temp.leaf = false;
					if (temp.values[0].length() != a) {
						temp.ptrs[findPos(temp.values[0].charAt(a))] = new PNode(temp.values[0]);
						temp.values[0] = null;
					}
				}
				temp.ptrs[0] = new PNode(word);
				return true;
			}
			
			if (temp.leaf) {
				if (temp.values[0].equals(word)) return false;
				temp.leaf = false;
				int b=0;
				while (temp.values[0].charAt(a+b) == word.charAt(a+b++)) {
					if (a+b == word.length()) {
						temp.ptrs[0] = new PNode(word);
						temp.ptrs[findPos(temp.values[0].charAt(a+b))] = new PNode(temp.values[0]);
						temp.values[findPos(temp.values[0].charAt(a+b))] = Character.toString(temp.values[0].charAt(a+b));
						temp.values[0] = "#";
						int pos3 = -1;
						while(true) if (parent.ptrs[++pos3] == temp) break;
						parent.length[pos3] = b+1;
						return true;
					}
					if (a+b == temp.values[0].length()) {
						temp.ptrs[0] = new PNode(temp.values[0]);
						temp.values[0] = "#";
						temp.ptrs[findPos(word.charAt(a+b))] = new PNode(word);
						temp.values[findPos(word.charAt(a+b))] = Character.toString(word.charAt(a+b));
						int pos3=-1;
						while(true) if (parent.ptrs[++pos3] == temp) break;
						parent.length[pos3] = b+2;
						return true;
					}
					
				}
				b--;
				temp.ptrs[findPos(temp.values[0].charAt(a+b))] = new PNode(temp.values[0]);
				temp.values[findPos(temp.values[0].charAt(a+b))] = Character.toString(temp.values[0].charAt(a+b));
				temp.values[0] = null;
				parent.length[pos] = b+a;
				temp.ptrs[findPos(word.charAt(a+b))] = new PNode(word);
				temp.values[findPos(word.charAt(a+b))] = Character.toString(word.charAt(a+b));
				return true;
			}
			
			if (temp.ptrs[pos2] == null) {
				temp.ptrs[pos2] = new PNode(word);
				temp.values[pos2] = Character.toString(word.charAt(parent.length[pos]));
				return true;
			}
			if (parent.length[pos] != 0) a = parent.length[pos];
			parent = temp;
			temp = temp.ptrs[pos2];
			pos =-1;
			//a++;
			while (true) if (parent.ptrs[++pos] == temp) break; 
			//parent = temp;
		}
		
		
	}
	
	private PNode search2(String word) {
		PNode temp = root;
		if (temp == null) return null;
		int pos=findPos(word.charAt(0));
		if (temp.ptrs[pos] == null) return null;
		int a=0;
		while(true) {
			if (temp.ptrs[pos] == null) return null;
			if (temp.ptrs[pos].leaf) break;
			if (temp.length[pos] != 0) a= temp.length[pos];
			else a++;
			temp = temp.ptrs[pos];
			if (a+1 > word.length()) {
				if (temp.ptrs[0] != null) {
					if (temp.ptrs[0].values[0] != null) {
						if (temp.ptrs[0].values[0].equals(word)) return temp;
					}
				}
				return null;
			}
			if (a == word.length()) a--;
			pos = findPos(word.charAt(a));
		}
		if (temp.ptrs[pos].values[0].equals(word)) return temp;
		return null;
	}
	
	private int findPos(char a) {
		if (a=='A') return 1;
		if (a=='B') return 2;
		if (a=='C') return 3;
		if (a=='D') return 4;
		if (a=='E') return 5;
		if (a=='F') return 6;
		if (a=='G') return 7;
		if (a=='H') return 8;
		if (a=='I') return 9;
		if (a=='J') return 10;
		if (a=='K') return 11;
		if (a=='L') return 12;
		if (a=='M') return 13;
		if (a=='N') return 14;
		if (a=='O') return 15;
		if (a=='P') return 16;
		if (a=='Q') return 17;
		if (a=='R') return 18;
		if (a=='S') return 19;
		if (a=='T') return 20;
		if (a=='U') return 21;
		if (a=='V') return 22;
		if (a=='W') return 23;
		if (a=='X') return 24;
		if (a=='Y') return 25;
		if (a=='Z') return 26;
		return -1;
		
	}
	
	public String search(String word)
	{
		/*Search for the word passed as a parameter.
		If the tree is empty, return an exclamation mark.
		See the specification for more details.*/
		String str = "";
		word = word.toUpperCase();
		PNode temp = root;
		if (temp == null) return "!";
		int pos=findPos(word.charAt(0));
		if (temp.ptrs[pos] == null) return null;
		int a=0;
		str = str + temp.toString();
		while(true) {
			if (temp.ptrs[pos] == null) return str + ",!";
			if (temp.ptrs[pos].leaf) break;
			if (temp.length[pos] != 0) a= temp.length[pos];
			else a++;
			temp = temp.ptrs[pos];
			str = str + "," + temp.toString();
			if (a+1 > word.length()) {
				if (temp.ptrs[0] != null) {
					if (temp.ptrs[0].values[0] != null) {
						if (temp.ptrs[0].values[0].equals(word)) return str + ","+temp.ptrs[0].values[0];
					}
				}
				return str + ",!";
			}
			if (a == word.length()) a--;
			pos = findPos(word.charAt(a));
		}
		if (temp.ptrs[pos].values[0].equals(word)) return str + "," + temp.ptrs[pos].values[0];
		return str + "!";
	}
	
	public String remove(String word)
	{
		/*Deletes the word passed as a parameter from the tree.
		If the tree was empty, then return an exclamation mark.*/
		word = word.toUpperCase();
		PNode temp = search2(word);
		String str = search(word);
		if (temp == null) return str;
		int a=-1;
		while (true) if (temp.ptrs[++a] != null) if(temp.ptrs[a].values[0] != null) if(temp.ptrs[a].values[0].equals(word)) break;
		//a--;
		temp.ptrs[a] = null;
		temp.values[a] = null;
		if (temp == root) return str;
		PNode temp2 = root;
		int c;
		int pos;
		Boolean another = false;
		Boolean another2 = false;
		a=0;
		
		while (true) {
			pos = findPos(word.charAt(a));
			if (temp2.ptrs[pos] == temp) break;
			temp2 = temp2.ptrs[pos];
			a++;
			if (temp2.length[pos] != 0) a = temp2.length[pos] -1;
		}
		for (c=0;c<27;c++) {
			if (temp.ptrs[c] != null) {
				if (!another) {
					a=c;
					another = true;
				}
				else {
					another2 = true;
					break;
				}
			}
		}
		if (another2) return str;
		temp2.ptrs[pos] = temp.ptrs[a];
		temp2.length[pos] = temp.length[a];
		return str;
	}
	
}
 
