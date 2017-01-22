
/**
 * @author : Vaishnavi Bhat
 * Indexed Heap Implementation
 * 
 */

import java.util.Comparator;
import java.util.List;

public class IndexedHeap12<T extends Index> extends BinaryHeap1<T> {
	/** Build a priority queue with a given array q */
	
	public IndexedHeap12(List<T> q, Comparator<T> comp) {
		this((T[]) q.toArray(new Object[q.size()]), comp);
    }
	
	IndexedHeap12(T[] q, Comparator<T> comp) {
		super(q, comp);
		int j =0;
        for (T i : q) {
            if(i!=null)	i.putIndex(j++);
        }
	}
	
    /** Create an empty priority queue of given maximum size */
    IndexedHeap12(int n, Comparator<T> comp) {
    	super(n, comp);
    }
    
    /** restore heap order property after the priority of x has decreased */
    void decreaseKey(T x) {
    	percolateUp(x.getIndex());
    }
    
    public void swap(int i, int j) {
        T temp = (T)pq[i];
        pq[i] = pq[j];
        pq[j] = temp;
        //Update the indices post swapping
        ((Vertex)pq[i]).putIndex(i);
        ((Vertex)pq[j]).putIndex(j);
    }

}
