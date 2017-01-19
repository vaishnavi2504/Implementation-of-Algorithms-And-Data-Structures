/**
 * *  @author Vaishnavi Bhat
 *  Date 10/02/2016
 */

import java.lang.Exception;

public class Edge {
    Vertex from; // head vertex
    Vertex to; // tail vertex
    int weight;// weight of edge
    boolean seen;
    boolean seen1;

    /**
     * Constructor for Edge
     * 
     * @param u
     *            : Vertex - Vertex from which edge starts
     * @param v
     *            : Vertex - Vertex on which edge lands
     * @param w
     *            : int - Weight of edge
     */
    Edge(Vertex u, Vertex v, int w) {
		from = u;
		to = v;
		weight = w;
		seen=false;
    }

    /**
     * Method to find the other end end of an edge, given a vertex reference
     * This method is used for undirected graphs
     * 
     * @param u
     *            : Vertex
     * @return
		  : Vertex - other end of edge
     */
    public Vertex otherEnd(Vertex u) {
		assert from == u || to == u;
		// if the vertex u is the head of the arc, then return the tail else return the head
		if (from == u) {
		    return to;
		} else {
		    return from;
		} 
    }

    /**
     * Return the string "(x,y)", where edge goes from x to y
     */
    public String toString() {
    	return "(" + from + "," + to + ")";
    }

    public String stringWithSpaces() {
    	return from + " " + to + " " + weight;
    }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((from == null) ? 0 : from.hashCode());
		result = prime * result + ((to == null) ? 0 : to.hashCode());
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
		Edge other = (Edge) obj;
		if (from == null) {
			if (other.from != null)
				return false;
		} else if (!from.equals(other.from))
			return false;
		if (to == null) {
			if (other.to != null)
				return false;
		} else if (!to.equals(other.to))
			return false;
		return true;
	}
    
    
}

