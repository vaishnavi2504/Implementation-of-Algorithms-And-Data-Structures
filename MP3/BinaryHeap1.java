
/**
 * @author : Vaishnavi Bhat
 * Binary Heap Implementation
 * 
 */

import java.util.Comparator;

public class BinaryHeap1<T> implements PQ<T> {

	Object[] pq;
	Comparator<T> c;
	private int size;

	
	BinaryHeap1(T[] q, Comparator<T> comp) {
		pq = q;
        c = comp;
        buildHeap();
	}

	
	public BinaryHeap1(int n, Comparator<T> comp) {
		pq = (T[]) new Object[n];
        c = comp;
	}

	
	public void insert(T x) {
		add(x);
	}

	
	public T deleteMin() {
		if (size == 0)
			return null;
		return remove();
	}

	
	//Since it's a min heap. root of the heap is returned
	public T min() {
		return peek();
	}

	

	public void add(T x) {
		if (size == pq.length - 1) {
            resize();
        }
        size++;
        pq[size] = x;
        percolateUp(size);
	}

	// resize operation 
	public void resize() {
		Object[] newPriorityQueue = pq;
		pq = (Object[]) new Object[2 * size];
		for (int i = 0; i <= size; i++)
			pq[i] = newPriorityQueue[i];
	}
	
	// Remove the minimum  element 
	public T remove() {
		T value = peek();
		pq[1] = pq[size--];
		percolateDown(1);
		return value;
	}
	
	public void setIndex(int i, int j) {
		pq[i] = pq[j];
	}
	
	//Returns the index of element x from the array representation of heap
	public int getIndex(T x){
		for(int i=0;i<size;i++){
			if(c.compare((T)pq[i], x)==0){
				return i;
			}
		}
		return -1;
	}
	
	// Return the minimum element based on the priority 
	public T peek() {
		if (size == 0) {
			return null;
		}
		return (T) pq[1];
	}	
	
	public void percolateUp(int i) { 
        pq[0] = pq[i]; 
        //If the parent node is greater than it's children then swap the nodes
        while (c.compare((T)pq[i / 2], (T)pq[0]) < 0) { 
            swap(i, i / 2);            
            i = i / 2;
        }
        pq[i] = pq[0];
    }

	void percolateDown(int i) { 
        T x = (T)pq[i];
        while (2 * i < size) {
            T lchild = (T)pq[2 * i];
            T rchild = null;
            if ((2 * i + 1) < size) {
                rchild =(T) pq[2 * i + 1];
            }
            int index=0;
            if (rchild != null) {
                index = c.compare(lchild, rchild) > 0 ? 2 * i : 2 * i + 1;
            } else {
                index = 2 * i;
            }
            if (c.compare(x,(T) pq[index]) < 0) {
                swap(i, index);
                i = index;
            } else {
                break;
            }

        }
        pq[i] = x;
    }
	
	// build a heap using percolate up operation
	void buildHeap() {       
            for (int i = 1; i < pq.length; i++) {
                size++;
                percolateUp(i);
            }       
    }
	
	protected void swap(int i, int j) {
        T temp =(T) pq[i];
        pq[i] = pq[j];
        pq[j] = temp;
    }


	// check if the heap is empty or not
	public boolean isEmpty() {
		return size == 0;
	}

	
	@Override
	public String toString() {
		StringBuilder s = new StringBuilder();
		for (int i = 1; i <= size; i++)
			s.append(pq[i] + " ");
		return s.toString();
	}

	

}


