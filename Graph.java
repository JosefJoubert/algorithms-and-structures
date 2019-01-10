/*
Complete your details...
Name and Surname: 
Student/staff Number:
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
//import java.io.RandomAccessFile;


/*You must complete this class to calculate the shortest paths for the travelling
 salesman problem.  Additional instructions are provided in comments throughout the
 class*/
public class Graph
{
	/*
	1. You may not change the signatures of any of the given methods.  You may 
	however add any additional methods and/or fields which you may require to aid 
	you in the completion of this assignment.
	
	2. You will have to design and implement a your own Graph class in terms of 
	graph representation.
	
	3. You may add any additional classes and/or methods to your assignment.
	*/
	
	
	
	String[] nodes = new String[0];
	int[][] weights;
	Node best;
	
	public Graph(String f)
	{
		/*
		The constructor receives the name of the file from which a graph
		is read and constructed.
		*/
		if (f == null) return;
		BufferedReader br = null;
		
		try {

			String sCurrentLine;

			br = new BufferedReader(new FileReader(f));

			while ((sCurrentLine = br.readLine()) != null) {
				if (sCurrentLine.equals("")) {
					sCurrentLine = br.readLine();
					if (sCurrentLine == null) break;
				}
				sCurrentLine = sCurrentLine.replace(" ", "");
				sCurrentLine.replaceAll("\\s+","");
				int pos = sCurrentLine.indexOf(',');
				String firstnode = sCurrentLine.substring(0, pos);
				int pos2 = sCurrentLine.indexOf(',',pos+1);
				String secondnode = sCurrentLine.substring(pos+1,pos2);
				int b = 2;
				for (int a = 0;a<nodes.length;a++){
					if (nodes[a].equals(firstnode)){
						b--;
						firstnode = "!";
					}
					if (nodes[a].equals(secondnode)){
						b--;
						secondnode = "!";
					}
				}
				if (b != 0){
					String[] tempnodes = new String[nodes.length+b];
					for (int a=0;a<nodes.length;a++){
						tempnodes[a] = nodes[a];
					}
					if(firstnode != "!"){
						tempnodes[tempnodes.length-b--] = firstnode;
					}
					if(secondnode != "!"){
						tempnodes[tempnodes.length-b] = secondnode;
					}
					nodes = tempnodes;
				}	
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		nodes = sort(nodes);
		weights = new int[nodes.length][nodes.length];
		for (int a = 0;a<nodes.length;a++){
			for (int b = 0;b<nodes.length;b++){
				weights[a][b] = Integer.MAX_VALUE;
			}
		}
		try {

			String sCurrentLine;

			br = new BufferedReader(new FileReader(f));

			while ((sCurrentLine = br.readLine()) != null) {
				if (sCurrentLine.equals("")) {
					sCurrentLine = br.readLine();
					if (sCurrentLine == null) break;
				}
				sCurrentLine = sCurrentLine.replace(" ", "");
				int pos = sCurrentLine.indexOf(',');
				String firstnode = sCurrentLine.substring(0, pos);
				int pos2 = sCurrentLine.indexOf(',',pos+1);
				String secondnode = sCurrentLine.substring(pos+1,pos2);
				int node1 = findPos(firstnode);
				int node2 = findPos(secondnode);
				int weight = Integer.parseInt(sCurrentLine.substring(pos2+1,sCurrentLine.length()));
				if (weights[node1][node2] > weight)	weights[node1][node2] = weight;
				if (weights[node2][node1] > weight)	weights[node2][node1] = weight;
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		
		
		
	}
	
	private Node getBest(){
		Queue tobe = new Queue(nodes.length);
		//for (int a = 0;a<nodes.length;a++) {
		//	tobe.enqueue(nodes[a], 0, null,nodes[a],1);
		//}
		tobe.enqueue(nodes[0], 0, null, nodes[0], 1);
		while(true){
			Node node = tobe.returnlowest();
			if (node == null) return null;
			if (node.visited == -1) {
				return node;
			}
			tobe.remove(node);
			int[] neighbours = getNeighbours(findPos(node.name));
			for (int a = 0;a<neighbours.length;a++){
				Node pred = node.pred;
				Boolean found = false;
				while (pred != null){
					if(pred.name.equals(nodes[neighbours[a]])){
						found = true;
						break;
					}
					pred = pred.pred;
				}
				if (!found){
					//node.neighbours[neighbours[a]] = new Node(nodes[neighbours[a]],weights[findPos(node.name)][findPos(nodes[neighbours[a]])],node,node.first,node.visited+1);
					tobe.enqueue(nodes[neighbours[a]], node.value + weights[findPos(node.name)][findPos(nodes[neighbours[a]])], node, node.first, node.visited+1);
				}
			}
			
			if (node.visited == nodes.length){
				for(int a = 0;a<neighbours.length;a++){
					if (nodes[neighbours[a]].equals(node.first)){
						tobe.enqueue(node.first,node.value + weights[findPos(node.name)][findPos(node.first)],node,node.first,-1);
					}
				}
			}
		}
		
		
	}
	
	public String getPath()
	{
		/*
		This method should return the path detected by your algorithm.  
		The string should be the vertex labels, with the starting vertex
		listed first and separated by commas containing no additional white 
		space.
		*/
		
		if (best == null) best = getBest();
		if (best == null) return "";
		Node temp = best;
		String[] path = new String[nodes.length+1];
		int a=nodes.length;
		while(temp!=null){
			path[a--] = temp.name;
			temp = temp.pred;
		}
		String paths = "";
		for (a=0;a<path.length-1;a++){
			paths = paths + path[a] + ",";
		}
		paths = paths + path[a];
		return paths;
	}
	
	public int getCost()
	{
		/*
		This method should return the cost of the route determined by your
		algorithm.
		*/
		if (best == null) best = getBest();
		if (best == null) return 0;
		return best.value;
	}
	
	public boolean changeWeight(String v, String u, int newWeight)
	{
		/*
		This method changes the weight of the edge between the vertices labelled
		v and u to newWeight.  If there are multiple edges between these two 
		vertices,then the edge with the lowest weight should be changed.  The 
		method should return true if the weight was successfully changed, and 
		false otherwise.
		*/
		int pos1 = findPos(v);
		int pos2 = findPos(u);
		if(pos1 == -1) return false;
		if(pos2 == -1) return false;
		weights[pos1][pos2] = newWeight;
		weights[pos2][pos1] = newWeight;
		
		return true;
	}
	
	public boolean changeLabel(String v, String newLabel)
	{
		int pos = findPos(v);
		if (pos == -1) return false;
		nodes[pos] = newLabel;
		return true;
	}
	
	public boolean reconstructGraph(String fileName)
	{
		/*
		This method should discard the current graph and construct a new
		graph contained in the file named "fileName".
		*/
		this.nodes = new String[0];
		BufferedReader br = null;
		try {

			String sCurrentLine;

			br = new BufferedReader(new FileReader(fileName));

			while ((sCurrentLine = br.readLine()) != null) {
				if (sCurrentLine.equals("")) sCurrentLine = br.readLine();
				sCurrentLine.replaceAll("\\s+","");
				int pos = sCurrentLine.indexOf(',');
				String firstnode = sCurrentLine.substring(0, pos);
				int pos2 = sCurrentLine.indexOf(',',pos+1);
				String secondnode = sCurrentLine.substring(pos+1,pos2);
				int b = 2;
				for (int a = 0;a<nodes.length;a++){
					if (nodes[a].equals(firstnode)){
						b--;
						firstnode = "!";
					}
					if (nodes[a].equals(secondnode)){
						b--;
						secondnode = "!";
					}
				}
				if (b != 0){
					String[] tempnodes = new String[nodes.length+b];
					for (int a=0;a<nodes.length;a++){
						tempnodes[a] = nodes[a];
					}
					if(firstnode != "!"){
						tempnodes[tempnodes.length-b--] = firstnode;
					}
					if(secondnode != "!"){
						tempnodes[tempnodes.length-b] = secondnode;
					}
					nodes = tempnodes;
				}	
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		weights = new int[nodes.length][nodes.length];
		for (int a = 0;a<nodes.length;a++){
			for (int b = 0;b<nodes.length;b++){
				weights[a][b] = Integer.MAX_VALUE;
			}
		}
		try {

			String sCurrentLine;

			br = new BufferedReader(new FileReader(fileName));

			while ((sCurrentLine = br.readLine()) != null) {
				if (sCurrentLine.equals("")) sCurrentLine = br.readLine();
				int pos = sCurrentLine.indexOf(',');
				String firstnode = sCurrentLine.substring(0, pos);
				int pos2 = sCurrentLine.indexOf(',',pos+1);
				String secondnode = sCurrentLine.substring(pos+1,pos2);
				int node1 = findPos(firstnode);
				int node2 = findPos(secondnode);
				int weight = Integer.parseInt(sCurrentLine.substring(pos2+1,sCurrentLine.length()));
				weights[node1][node2] = weight;
				weights[node2][node1] = weight;
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return true;
	}
	public int[] getNeighbours(int i){
		int count = 0;
		for (int a = 0;a<weights[0].length;a++){
			if (weights[i][a] != Integer.MAX_VALUE) count++;
		}
		int[] neigh = new int[count];
		count = 0;
		for (int a = 0;a<weights[0].length;a++){
			if (weights[i][a] != Integer.MAX_VALUE) neigh[count++] = a;
		}
		return neigh;
		
	}
	
	private String[] sort(String[] nodess){
		
		String[] newnodes = new String[nodess.length];
		int pos = -1;
		String low = nodess[0].trim();
		for (int a = 0;a<nodess.length;a++){
			while(true) if (nodess[++pos] != null) break;
			low = nodess[pos].trim();
			for (int b=0;b<nodess.length;b++){
				if (nodess[b] == null) continue;
				if (nodess[b].trim().compareTo(low) < 0) {
					low = nodess[b];
					pos = b;
				}
			}
			newnodes[a] = low;
			nodess[pos] = null;
			pos = -1;
		}
		return newnodes;
	}
	
	private int findPos(String node){
		for (int a = 0;a<nodes.length;a++){
			if (nodes[a].equals(node)) return a;
		}
		return -1;
	}
	
}
