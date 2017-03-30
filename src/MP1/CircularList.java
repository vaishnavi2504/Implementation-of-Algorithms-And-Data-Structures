package MP1;

/**
 * *  @author Vaishnavi Bhat
 *  Date 10/02/2016
 */

import java.util.Iterator;

public class CircularList<T> implements Iterable<T> {
	public class Entry<Vertex> {
		T element;
		Entry<Vertex> next;
		int indx;

		Entry(T x, int idx, Entry<Vertex> nxt) {
			element = x;
			next = nxt;
			indx = idx;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + getOuterType().hashCode();
			result = prime * result + ((element == null) ? 0 : element.hashCode());
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
			Entry other = (Entry) obj;
			if (!getOuterType().equals(other.getOuterType()))
				return false;
			if (element == null) {
				if (other.element != null)
					return false;
			} else if (!element.equals(other.element))
				return false;
			return true;
		}

		private CircularList getOuterType() {
			return CircularList.this;
		}

	}

	int i = 0;

	Entry<T> header = null, tail = null;
	int size;

	CircularList() {
		header = new Entry<>(null, 0, null);
		tail = null;
		size = 0;
	}

	public Iterator<T> iterator() {
		return new SLLIterator<>(header);
	}

	// Class that implements iterator
	private class SLLIterator<E> implements Iterator<E> {
		Entry<E> cursor, prev;

		// Constructor to initialize current and prev pointers
		SLLIterator(Entry<E> head) {
			cursor = head;
			prev = null;
		}

		public boolean hasNext() {
				return cursor.next != header.next;
		}

		public E next() {
			prev = cursor;
			cursor = cursor.next;
			return (E) cursor.element;
		}

		// To do: error checking
		// What should cursor be set to after a remove?
		public void remove() {
			prev.next = cursor.next;
			prev = null;
		}
	}

	//Adds vertices to the sub tour
	void add(T x) {
		// list is empty
		if (tail == null) {
			header.next = new Entry<>(x, i++, header.next);
			tail = header.next;
			tail.next = header;

		} else {
			tail.next = new Entry<>(x, i++, null);
			tail = tail.next;
			tail.next = header;
		}

		size++;
	}

	//Merges 2 circular lists/ sub tours
	public void merge1(CircularList<Vertex> c2) {
		Entry<T> temp = header.next;
		//Head of the second list
		Entry<T> b = (Entry<T>) c2.header.next;

		//Keep traversing the first list until there is a match with the first node of the second list
		while (temp.element != b.element) {
			temp = temp.next;
		}
		
		//To hold the address of the next node before breaking the link of the first list 
		Entry<T> link = temp.next;
		temp.next = b.next;

		Entry<T> temp1 = (Entry<T>) c2.header.next.next;
		while (temp1.element != c2.header.next.element) {
			temp1 = temp1.next;
		}

		temp1.next = link;
	}

	//To print the contents of the list
	void printList() {
		if (header == null) {
			System.out.println("Empty list");
			return;
		} else {
			Entry<T> x = header.next;			
			while (x.next != header) {
				if (x.next != null&&x.next!=header.next){
					System.out.println(x.element);						
					x = x.next;
				}
			} 			
		}
}

	/*public static void main(String[] args) {

		CircularList<Integer> lst = new CircularList<>();
		Iterator it = lst.iterator();	

	}
	*/
}
