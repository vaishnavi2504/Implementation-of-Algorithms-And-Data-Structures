
/**

 * Class to represent a graph
 *  @author Vaishnavi Bhat
 *  Date: 11/30/2016
 *
 */

import java.util.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class Graph implements Iterable<Vertex> {
    List<Vertex> v; // vertices of graph
    int size; // number of vertices in the graph
    int count=1;
    int length=0;
    boolean directed;  // true if graph is directed, false otherwise
    List<Vertex> topList=new LinkedList<>();
    List<Vertex> revTopList=new ArrayList<>();
    List<Edge> criticalPath=new ArrayList<>();
    //List of vertices in reverse topological order-From Topological sort(take 2) algorithm
    List<Vertex> revTopList1=new LinkedList<>();
    Vertex source, sink;
    Map<Vertex,Integer> cPath=new HashMap<>();
    Graph(int size) {
		this.size = size;
		this.v = new ArrayList<>(size + 1);
		this.v.add(0, null);  // Vertex at index 0 is not used
		this.directed = false;  // default is undirected graph
		// create an array of Vertex objects
		for (int i = 1; i <= size; i++)
		    this.v.add(i, new Vertex(i));
		source=v.get(v.size()-2);
		sink=v.get(v.size()-1);
		
    }

    /**
     * Find vertex no. n
     * @param n
     *           : int
     */
    Vertex getVertex(int n) {
    	return this.v.get(n);
    }    
    
    //Checks if an edge is a tight edge
    public void detectTightEdges(){    	
    	lcCompute();
    	for(Vertex u:topList){    		
    		for(Edge e:u.adj){
    			Vertex v=e.otherEnd(u);    			
    			if(u.lc+v.d==v.lc&&u.ec==u.lc&&v.ec==v.lc){
    				e.isTight=true;
    			}    			
    		}
    	}    	
    }
    
    //Number of critical paths in the given graph
    public int numOfPaths(){    	    	
    	source.npaths=1;    	
      	for(Vertex u:topList){
    		//add the no. of paths of a vertex's predecessors
    		if(u!=null){    			
    				for(Edge x:u.adj){    					
        				if(x.isTight){
        					Vertex y=x.otherEnd(u);
        					 y.npaths+=u.npaths;            			
        				}        			
            		}
       		}    		
    	}      	
      return sink.npaths;    	
    }	
    
    public void DFS(){
    	for(Vertex u :v){
    		if(u!=null){
    			u.seen=false;
        		u.parent=null;
        		u.cno=0;
    		}    		
    	}
    	int comp=0;    	
    	List<Vertex> src=new ArrayList<>();    	
    	for(Vertex u :v){
    		if(u!=null){
	    		if(!u.seen){
	    			src.add(u);
	    			u.cno=++comp;
	    			DFSVisit(u,revTopList);
	    		}
    		}
    	}
    	revTopList.addAll(src);
    	
    }
    
    //Recursive
    public void DFSVisit(Vertex u,List<Vertex> list){
    	u.seen=true;    	
    	for(Edge e:u.adj){
    		Vertex x=e.otherEnd(u);
    		if(!x.seen){
    			x.cno=u.cno;
    			x.parent=u;    			
    			DFSVisit(x,list);    			
    		}    		
    	}
    	list.add(u);    	
    }
    
    //Topological sort - Take2(From Class notes)
    public List<Vertex>toplogicalSortTake2(){
    	for(Vertex u:v){
    		if(u!=null)u.seen=false;
    	}
    	List<Vertex> src=new ArrayList<>();  
    	for(Vertex u:v){
    		if(u!=null&&!u.seen/*&&u!=source&&u!=sink*/){    			
    			DFSVisit(u,src);
    		}
    	}    
    	return src;
    }   
    
    //Computes earliest completion time for each vertex in the graph
    public void ecCompute(){
    	connection();
    	for(Vertex u:v){
    		if(u!=null)u.ec=u.d;    		
    	}   	
    	topologicalSort();    
    	Vertex y=null;
    	for(Vertex u: topList){
    		if(u!=null){
    			for(Edge e:u.adj){
        			y=e.otherEnd(u);
        		y.ec=Math.max(y.ec, u.ec+y.d);
        		}
    		}     		
    	}    	
    	
    }
    
    //Computes Latest Completion time at each vertex
    public void lcCompute(){  
    	ecCompute();    	
    	List<Vertex> l=toplogicalSortTake2();    	
    	sink.lc=sink.ec;
    	for(Vertex u: v){
    		if(u!=null)u.lc=sink.lc;
    	}
    	for(Vertex u:l){
    		u.slack=u.lc-u.ec;
    		for(Edge e:u.revAdj){
    			Vertex x=e.otherEnd(u);
    			x.lc=Math.min(x.lc, u.lc-u.d);    			
    		}
    		
    	}    	
    }
    
    //Returns the count of nodes whose slack is 0. lc-ec=0
    public void numOfCriticalNodes(){   
    	lcCompute();    	
    	int count=0;
    	for(Vertex u:v){
    		if(u!=null&&u!=source&&u!=sink&&u.ec==u.lc)count++;
    	}
    	System.out.println(count);
    } 
    
    
    public  void topologicalSort(){
    	Queue<Vertex> q=new LinkedList<>();    	
    	int count1=0;
    	q.add(source);
    	for(Vertex u :v){    		
    		if(u!=null && u.inDegree==0&&u!=source&&u!=sink)q.add(u);    		
    	}
    	while(!q.isEmpty()){
    		Vertex u=q.remove();
    		topList.add(u);
    		u.top=++count1;
    		for(Edge e:u.adj){
    			Vertex y=e.otherEnd(u);
    			y.inDegree--;
    			if(y.inDegree==0)q.add(y);
    		}    		
    	}   	
    	topList.add(sink);
    }
    
    
    void addEdge(Vertex from, Vertex to, int weight) {
		Edge e = new Edge(from, to, weight);
		if(this.directed) {
		    from.adj.add(e);
	            to.revAdj.add(e);
		} else {
		    from.adj.add(e);
		    to.adj.add(e);
		}
    }

    //Connects source vertex to all the nodes with indegree 0 and all nodes with out degree 0 to sink
    void connection(){
    	List<Vertex>In=new ArrayList<>();
    	List<Vertex>Out=new ArrayList<>();
    	for(Vertex u:v){
    		if(u!=null){    			
    			if(u.inDegree==0&&u.outDegree!=0){
    				
    				In.add(u);
    			}
    			if(u.outDegree==0&&u.inDegree!=0){
    				
    				Out.add(u);
    			}
    		}
    	}
    	for(Vertex u:In){
    		addEdge(source, u, 1);
    	}
    	for(Vertex u:Out){
    		addEdge(u,sink, 1);
    	}    	
    }
    
    public Iterator<Vertex> iterator() {
		Iterator<Vertex> it = this.v.iterator();
		it.next();  // Index 0 is not used.  Skip it.
		return it;
    }

    // Run BFS from a given source node
    // Precondition: nodes have already been marked unseen
    public void bfs(Vertex src) {
		src.seen = true;
		src.d = 0;
		Queue<Vertex> q = new LinkedList<>();
		q.add(src);
		while(!q.isEmpty()) {
		    Vertex u = q.remove();
		    for(Edge e: u.adj) {
				Vertex v = e.otherEnd(u);
				if(!v.seen) {
				    v.seen = true;
				    v.d = u.d + 1;
				    q.add(v);
				}
		    }
		}
    }
    
    
    // read a directed graph using the Scanner interface
    public static Graph readDirectedGraph(Scanner in) {
	return readGraph(in, true);
    }
    
    // read an undirected graph using the Scanner interface
    public static Graph readGraph(Scanner in) {
	return readGraph(in, false);
    }
    
    public static Graph readGraph(Scanner in, boolean directed) {
		// read the graph related parameters
		int n = in.nextInt(); // number of vertices in the graph
		int m = in.nextInt(); // number of edges in the graph
	
		// create a graph instance
		Graph g = new Graph(n);
		g.directed = directed;
		for (int i = 0; i < m; i++) {
		    int u = in.nextInt();
		    int v = in.nextInt();
		    int w = in.nextInt();
		    g.addEdge(g.getVertex(u), g.getVertex(v), w);
		}
		return g;
    }

}
