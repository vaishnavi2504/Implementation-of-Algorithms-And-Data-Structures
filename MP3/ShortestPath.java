
/**
 * @author : Vaishnavi Bhat
 * Dijkstra ShortestPaths Implementation
 * 
 */
import java.util.Comparator;
import java.util.Scanner;
import java.lang.Comparable;
import java.io.FileNotFoundException;
import java.io.File;

public class ShortestPath {
    static final int Infinity = Integer.MAX_VALUE;

    static void DijkstraShortestPaths(Graph g, Vertex src)
    {
        // Code for Dijkstra's algorithm needs to be written
	// Shortest paths will be stored in fields d,p in the Vertex class
    	
    	int vsize=g.v.size();
    	Vertex[] q=new Vertex[vsize];
    	int i=0;
    	for(Vertex u:g.v){
    		if(u!=null){
    			u.seen=false;
    			u.d=Infinity;
        		u.p=null;
        		q[i++]=u;
    		}    		
    	}
    	src.seen=false;
    	src.d=0;
    	IndexedHeap12 heap = new IndexedHeap12(q, new Comparator<Vertex>() {
            @Override
            public int compare(Vertex o1, Vertex o2) {
                return o2.d - o1.d;
                
            }
        });
    	while(!heap.isEmpty()){
    		Vertex u=(Vertex)heap.remove();
    		u.seen=true;
    		for(Edge e:u.adj){
    			Vertex x=(Vertex)e.otherEnd(u);
    			if(!x.seen&&u.d+e.weight<x.d){
    				x.d=u.d+e.weight;
    				heap.decreaseKey(x);
    			}
    		}
    	}
    	
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
		int src = in.nextInt();
		int target = in.nextInt();
	        Vertex s = g.getVertex(src);
		Vertex t = g.getVertex(target);
		
			
    }
}
