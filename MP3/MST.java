/**
 * @author : Vaishnavi Bhat
 *  Implementation of Prim1, Prim2 for calculating MST
 * 
 */

import java.util.*;
import java.util.Comparator;
import java.lang.Comparable;
import java.io.FileNotFoundException;
import java.io.File;

public class MST implements Comparator<Integer> {
    static final int Infinity = Integer.MAX_VALUE;
    
    public int compare(Integer s1, Integer s2) {
        return s1.compareTo(s2);
    }    
    
    //Implementation of Prim1 to calculate MST
    public static int MSTUsingPrims(Graph g, Vertex src) {
        int wmst = 0;
        for(Vertex u:g.v){
    		if(u!=null){
    			u.seen=false;
        		u.p=null;
    		}    		
    	}
        src.seen = true;
        src.d = 0;        
        int size = src.adj.size();
        Edge[] edges = new Edge[size + 1];
        for (int i = 1; i <= size; i++) {
            edges[i] = src.adj.get(i - 1);
        }
        BinaryHeap1<Edge> heapEdges = new BinaryHeap1<>(edges, new Comparator<Edge>() {
            @Override
            public int compare(Edge e1, Edge e2) {
                return e2.weight - e1.weight;
            }
        });

        while (!heapEdges.isEmpty()) {
            Edge e = heapEdges.remove();
            if (e != null) {               
            	Vertex u;
                if(e.from.seen)u=e.to;
                else u=e.from;
                if (!u.seen) {                	
                    u.seen = true;
                    u.p = e.otherEnd(u);
                    wmst = wmst + e.weight;
                    for (Edge ed : u.adj) {
                        if (!ed.otherEnd(u).seen)
                            heapEdges.add(ed);
                    }
                }
            }
        }
        
        return wmst;
    }
    
    
    //Implementation of Prim2 to calculate MST
    static int PrimMST(Graph g, Vertex s){    	
    	for(Vertex u:g.v){
    		if(u!=null){
    			u.seen=false;
        		u.p=null;
    		}    		
    	}    	
    	int vsize=g.v.size();
    	Vertex[] q=new Vertex[vsize];
    	int i=0;
    	int mst=0;    	
    	for(Vertex u:g.v){
    		if(u!=null){
	    		u.d=Infinity;
	    		q[i++]=u;
    		}
    	}
    	s.seen=true;
    	s.d=0;
    	
    	IndexedHeap12 heap = new IndexedHeap12(q, new Comparator<Vertex>() {
            @Override
            public int compare(Vertex o1, Vertex o2) {
                return o2.d - o1.d;
                
            }
        });
    	
    	while(!heap.isEmpty()){
    		Vertex u=(Vertex)heap.remove();
    		if(u!=null){
    			u.seen=true;
        		mst+=u.d;    		
        		for(Edge e:u.adj){
        			Vertex x=e.otherEnd(u);        			
        			if(!x.seen&&x.d>e.weight){
        				x.d=e.weight;    				
        				x.p=u;   
        				heap.percolateUp(x.getIndex());
        			}
        		}
    		}
    		
    	}
    	  return mst;
    }

    public static void main(String[] args) throws FileNotFoundException {
	Scanner in;

        if (args.length > 0) {
            File inputFile = new File(args[0]);
            in = new Scanner(inputFile);
        } else {
            in = new Scanner(System.in);
        }

        Graph g = Graph.readGraph(in);
        Vertex s = g.getVertex(1);
        System.out.println(PrimMST(g, s));
    }
}
