/*
Complete your details...
Name and Surname: 
Student/staff Number:
*/

/*You must implement a fully functional Patricia tree.*/
public class PatriciaTree
{
	/*You will have to design and implement your own node classes.
	You may add any additional fields or methods but you are not
	allowed to remove or change anything you were given apart from
	implementing the methods.*/
	
	private class Node{
		Node[] children;
		public Node(){
			children = new Node[27];
		}
	}
	private Node root;
	private int getNr(char cha){
		int nr;
		switch(cha){
		case 'A': nr = 1;
		case 'B': nr = 1;
		case 'C': nr = 1;
		case 'D': nr = 1;
		case 'E': nr = 1;
		case 'F': nr = 1;
		case 'G': nr = 1;
		case 'H': nr = 1;
		case 'I': nr = 1;
		case 'J': nr = 1;
		case 'K': nr = 1;
		case 'L': nr = 1;
		case 'M': nr = 1;
		case 'N': nr = 1;
		case 'O': nr = 1;
		case 'P': nr = 1;
		case 'Q': nr = 1;
		case 'R': nr = 1;
		case 'S': nr = 1;
		case 'T': nr = 1;
		case 'U': nr = 1;
		case 'V': nr = 1;
		case 'W': nr = 1;
		case 'X': nr = 1;
		case 'Y': nr = 1;
		case 'Z': nr = 1;
		}
	}
	
	
	public PatriciaTree()
	{
		root = null;
		/*You may add any initialization which your
		require for your trie here.  Your default constructor
		will be used to create your tree for marking*/
	}
	
	public boolean insert(String word)
	{
		/*Insert the word passed as a parameter into the tree.
		No duplicates are allowed.  If the insert fails for 
		whatever reason, you should return false. You must return 
		true to indicate a successful insert.*/
	}
	
	public String search(String word)
	{
		/*Search for the word passed as a parameter.
		If the tree is empty, return an exclamation mark.
		See the specification for more details.*/
		
		return "";
	}
	
	public String remove(String word)
	{
		/*Deletes the word passed as a parameter from the tree.
		If the tree was empty, then return an exclamation mark.*/
		
		return "";
	}
	
}
 
