
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Graph {

    //Maximum number of vertices allowed
    protected int V;
    //Number of edges currently in the graph
    protected int E;
    //The adjacency matrix
    protected int[][] matrix;
    //Distance array
    public int[] dist;
    //Pred array
    public int[] pred;
    //Visitation number
    private int i;
    //Cycle detection
    private int[] num;

    public Graph(int V) {
        this.V = V;
        this.E = 0;
        this.matrix = new int[V][V];
    }

    public int getV() {
        return V;
    }

    public int getE() {
        return E;
    }

    public void addEdge(int from, int to, int weight) {
        if (matrix[from][to] == 0) 
        {
            matrix[from][to] = weight;
            E++;
            
        }
    }

    public static Graph buildFromFile(String file) {
        Graph graph = null;

        try {
            FileInputStream fstream = new FileInputStream(file);
            DataInputStream in = new DataInputStream(fstream);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));

            graph = new Graph(new Integer(br.readLine()));

            String strLine;
            while ((strLine = br.readLine()) != null) {
                String[] temp = strLine.split(" ");

                int from = temp[0].charAt(0) - 'a' + 0;
                int to = temp[1].charAt(0) - 'a' + 0;
                int weight = Integer.parseInt(temp[2]);
                graph.addEdge(from, to, weight);
            }
            in.close();
            br.close();
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }

        return graph;
    }

    @Override
    public String toString() {
        String temp = "  ";

        for (int i = 0; i < V; i++) {
            temp += " " + (i < 10 ? " " : "") + (char)(i+97);
        }

        temp += "\n";

        for (int i = 0; i < V; i++) {
            temp += (i < 10 ? " " : "") + (char)(i+97);
            for (int j = 0; j < V; j++) {
                temp += "  " + (matrix[i][j] == 0 ? "-" : matrix[i][j]);
            }
            temp += "\n";
        }

        return temp;
    }
    public boolean route(char from, char to)
    {
        return dijkstra(from, to);
    }
    public boolean dijkstra(char from, char to) {
        return dijkstra(from - 'a' + 0, to - 'a' + 0);
    }

    // ------
    // Task 1
    // ------

    public boolean dijkstra(int from, int to) {
        dist = new int[to-from];
        ArrayList<Integer> check = new ArrayList();
        for(int a=0;a<dist.length;a++) dist[a] = Integer.MAX_VALUE;
        dist[from] = 0;
        for(int a=from;a<to+1;a++) check.add(a);
        while (true) {
        	if(check.isEmpty()) break;
        	int v = check.get(0);
        	for (int a=1;a<check.size();a++) {
        		if (dist[check.get(v)] > dist[check.get(a)]) v = a;
        	}
        	check.remove(v);
        	for (int a=0;a<V;a++) {
        		if (matrix[v][a] != 0) {
        			if (dist[a] > dist[v]+matrix[v][a]) {
        				dist[a] = dist[v] + matrix[v][a];
        				pred[a] = v;
        			}
        		}
        	}
        	
        	
        }
        return false;
    }
    public ArrayList<String> cycles; //we will check this variable during marking

    public void detectCycles() {
        //implement 2/5
     
    }
    
    private ArrayList<Integer> stack;
    public ArrayList<String> scc; //we will check this variable during marking


     public void stronglyConnectedSearch() 
     {
         //implement 3/5
     }

   
    
     // ------
    // Task 2
    // ------

    
    public boolean commands(String command) 
    {
            String one="";
            String two="";
            String three="";
            StringTokenizer st =new StringTokenizer(command);
            if(st.hasMoreTokens()) one= st.nextToken();
            if(st.hasMoreTokens()) two= st.nextToken();
            if(st.hasMoreTokens()) three= st.nextToken();
            
            if(one.equalsIgnoreCase("ping") && two.length()>0 && three.length()>0)
            {
                
                if (route(two.charAt(0), three.charAt(0)) == true) {
                    System.out.println("Server "+three.charAt(0)+" is reachable from server "+two.charAt(0)+" with cost "+dist[((int)(three.charAt(0))-97)]);
                } else {
                    System.out.println("Server "+three.charAt(0)+" not reachable from server "+two.charAt(0));
                }
            }
            else if(one.equalsIgnoreCase("tracert") && two.length()>0 && three.length()>0)
            {
                boolean reach = route(two.charAt(0), three.charAt(0));
                
                //implement 4/5 (implement last)
                //Your code here
                //Display the shortest path
                
                if (reach == true) {
                    System.out.println("Server "+three.charAt(0)+" is reachable from server "+two.charAt(0)+" with cost "+dist[((int)(three.charAt(0))-97)]);
                } else {
                    System.out.println("Server "+three.charAt(0)+" not reachable from server "+two.charAt(0));
                }
            }
            else if(one.equalsIgnoreCase("local") && two.length() > 0 && three.length() > 0 )
            {
                route(two.charAt(0), three.charAt(0));
                printLocal(dist);
            }
            else if(one.equalsIgnoreCase("warn"))
            {
                detectCycles();
                if(cycles.isEmpty()) System.out.println("No warnings"); 
                else System.out.println(cycles); 
            }
            else if(one.equalsIgnoreCase("view"))
            {
                System.out.println(this);
            }
            else if(one.equalsIgnoreCase("ring"))
            {
                stronglyConnectedSearch();
                System.out.println(scc); 
            }
            else if(one.equalsIgnoreCase("exit"))
            {
                   return false;
            }
            else
            {
                  System.out.println("'"+one+"' command contains a syntax error or does not exist"); //[5 2 0, 10, 9, 8, 6 7 3 4 1]
            }
            return true;
    }
    void printLocal(int[] arr) 
    {
        //implement 5/5
    }

}
