

/**
 * Class to represent a vertex of a graph
 * @author rbk
 *
 */

import java.util.List;
import java.util.ArrayList;

public class Vertex {
    int name; // name of the vertex
    boolean seen; // flag to check if the vertex has already been visited
    int distance; // distance of vertex from a source
    List<Edge> adj, revAdj; // adjacency list; use LinkedList or ArrayList

    /**
     * Constructor for the vertex
     * 
     * @param n
     *            : int - name of the vertex
     */
    Vertex(int n) {
		name = n;
		seen = false;
		distance = Integer.MAX_VALUE;
		adj = new ArrayList<Edge>();
		revAdj = new ArrayList<Edge>();   /* only for directed graphs */
    }  
    
    

    @Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + name;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Vertex other = (Vertex) obj;
		if (name != other.name)
			return false;
		return true;
	}


	/**
     * Method to represent a vertex by its name
     */
    public String toString() {
    	return Integer.toString(name);
    }
}
