
/**
 * Class to represent a graph
 *  @author Vaishnavi Bhat
 *  Date 10/02/2016
 *
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;


public class Graph implements Iterable<Vertex> {
	List<Vertex> v; // vertices of graph
	int size; // number of vertices in the graph
	boolean directed; // true if graph is directed, false otherwise
	

	/**
	 * Constructor for Graph
	 * 
	 * @param size
	 *            : int - number of vertices
	 */
	Graph(int size) {
		this.size = size;
		this.v = new ArrayList<>(size + 1);
		this.v.add(0, null); // Vertex at index 0 is not used
		this.directed = false; // default is undirected graph
		// create an array of Vertex objects
		for (int i = 1; i <= size; i++)
			this.v.add(i, new Vertex(i));
	}

	/**
	 * Find vertex no. n
	 * 
	 * @param n
	 *            : int
	 */
	Vertex getVertex(int n) {
		// Returns arraylist corresponding to vertex n Vertex(n)
		return this.v.get(n);
	}

	/**
	 * Method to add an edge to the graph
	 * 
	 * @param a
	 *            : int - one end of edge
	 * @param b
	 *            : int - other end of edge
	 * @param weight
	 *            : int - the weight of the edge
	 */
	void addEdge(Vertex from, Vertex to, int weight) {
		Edge e = new Edge(from, to, weight);
		if (this.directed) {
			from.adj.add(e);
			to.revAdj.add(e);
		} else {
			from.adj.add(e);
			to.adj.add(e);
		}
	}

	/**
	 * Method to create iterator for vertices of graph
	 */
	public Iterator<Vertex> iterator() {
		Iterator<Vertex> it = this.v.iterator();
		it.next(); // Index 0 is not used. Skip it.
		return it;
	}

	
	//Verifies if a given path is a valid Euler tour
	public boolean verifyTour(Graph g, CircularList<Vertex> tour) {
		
		HashSet<Edge> set1 = new HashSet<Edge>();
		int w=1;
		// Create a hashset with edges as keys and have value as 1 for each edge
		for (Vertex x : g.v) {
			if (x != null) {
				for (Edge l : x.adj) {
					set1.add(l);

				}
			}
		}		
		Iterator<Vertex>it=tour.iterator();
		Vertex u=it.next();
		Vertex v;
		while(it.hasNext()){
			v=it.next();
			Edge a1 = new Edge(u, v, 1);
			Edge a2 = new Edge(v, u, 1);
			
			set1.remove(a1);
			set1.remove(a2);
			
			u = v;
		}
		if(set1.size()==0)
			return true;
		return false;
	}

	// Returns false if a vertex has no unvisited edges
	public boolean checkUnvisit(Vertex s) {
		if(s!=null){
			for (Edge e : s.adj) {
				if (!e.seen) {
					return true;
				}
			}	
			return false;
		}
		return false;
	}

	//Stiches sub tours into a single Euler tour 
	public CircularList<Vertex> stitchTours(List<CircularList<Vertex>> lts){		
		//Fetches first list		
		Iterator<CircularList<Vertex>> it=lts.iterator();
		CircularList<Vertex> parent = it.next();
		while(it.hasNext()){
			parent.merge1(it.next());
		}		
		return parent;
	}
	
	
	//Breaks the given graph into a list of sub tours
	public List<CircularList<Vertex>> breakGraphIntoTours() {			
		List<CircularList<Vertex>> lcl = new ArrayList<>();	
		int res=0;
		//Populate queue with vertices
		Queue<Vertex> q = new LinkedList<Vertex>();	
		for(Vertex z:v)q.add(z);
		
		while(!q.isEmpty()){
			Vertex src=q.peek();
			if (src!=null&&checkUnvisit(src)) {
				Vertex u = src;
				Vertex strt = u;
				CircularList<Vertex> c1 = new CircularList<Vertex>();
				c1.add(strt);
				Vertex v = null;
				do {
					// Just to initialize
					Edge e1 = u.adj.get(1);
					for (Edge e : u.adj) {
						if(e.seen);						
						else{
							v = e.otherEnd(u);
							e1 = e;
							break;
						}
					}
	
					if (!(e1.seen) && !e1.equals(null)) {
						e1.seen = true;
						c1.add(v);
	
					}
					u = v;
				} while (!strt.equals(v));
				
				
				lcl.add(c1);
			}
			if(!checkUnvisit(src))q.remove();
		}		
		return lcl;
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
		in.close();
		return g;
	}

}
