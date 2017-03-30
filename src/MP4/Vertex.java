package MP4;
/**

 * Class to represent a vertex of a graph
 *
 *
 */

import java.util.List;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;

public class Vertex implements Iterable<Edge> {
    int name; // name of the vertex
    boolean seen; // flag to check if the vertex has already been visited
    int d;  Vertex p;  // fields used in algorithms of Prim and Dijkstra
    List<Edge> adj, revAdj; // adjacency list; use LinkedList or ArrayList
    int inDegree;
    int outDegree;
    boolean visited;
    int top,npaths;
    int du=0,slack=0;
    int ec,lc;
    Vertex parent;
	int cno;

    /**
     * Constructor for the vertex
     * 
     * @param n
     *            : int - name of the vertex
     */
    Vertex(int n) {
		name = n;
		seen = false;
		d = Integer.MAX_VALUE;		
		du=0;
		adj = new ArrayList<Edge>();
		revAdj = new ArrayList<Edge>();   /* only for directed graphs */
		inDegree=0;
		visited=false;
		top=0;
		npaths=0;
		lc=0;
		ec=0;
		slack=0;
		parent=null;
		cno=0;		
    }

    public Iterator<Edge> iterator() { return adj.iterator(); }

    /**
     * Method to represent a vertex by its name
     */
    public String toString() {
    	return Integer.toString(name);
    }
}
