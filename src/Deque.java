import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author Ryan Boken
 * Assignment: A02 Randomized Queues and Deques
 * Class: CSIS-2420
 * @date 10/4/2017
 * Credits: Derived from LinkedStack written by Robert Sedgewick and Kevin Wayne
 */
public class Deque<Item> implements Iterable<Item> {
	private Node first;
	private Node last;
	private int count;
	
	public Deque()  {
		// construct an empty deque
		first = new Node();
		last = new Node();
		
		first.next = last;
		first.item = null;
		
		last.prev = first;
		last.item = null;
		
		count = 0;
	}
	
	private class Node {
		private Item item;
		private Node next;
		private Node prev;
	}
	
	// is the deque empty?
	public boolean isEmpty() {
		if (count == 0) return false;
		else return true;
	}
	
	// return the number of items on the deque
	public int size()   {
		return count;
	}
	
	 // insert the item at the front
	public void addFirst(Item item) {
		if(checkAddItem(item)) {
			if(first.item == null) {
				first.item = item;
			}
			else {
				Node oldfirst = first;
				first = new Node();
				first.item = item;
				first.next = oldfirst;
			}
			count++;
		}		  
	}
	
	// insert the item at the end
	public void addLast(Item item) {
		if(checkAddItem(item)) {
			if (last.item == null) {
				last.item = item;
			} 
			else {
				Node oldlast = last;
				last = new Node();
				last.item = item;
				last.prev = oldlast;			
				oldlast.next = last;
			}
			count++;
		}	
	}
	
	// delete and return the item at the front
	public Item removeFirst() {
		if(first.item == null) throw new java.lang.UnsupportedOperationException();
		Node dFirst = first;
		first = first.next;
		count--;
		return dFirst.item;
	}
	
	// delete and return the item at the end
	public Item removeLast() {
		if(last.item == null) throw new java.lang.UnsupportedOperationException();
		Node dLast = last;
		last = last.prev;
		last.next = null;
		count--;
		return dLast.item;
	}

	// return an iterator over items in order from front to end
	@Override
	public Iterator<Item> iterator() { return new DequeIterator(); }
	
	private class DequeIterator implements Iterator<Item> {
		private Node current = first;

		@Override
		public boolean hasNext() { return current != null; }

		@Override
		public Item next() {
			if (!hasNext()) throw new NoSuchElementException();
            Item item = current.item;
            current = current.next; 
            return item;
		}
		
		public void remove() { throw new UnsupportedOperationException();  }
	}
	
	private boolean checkAddItem(Item i) {
		if (i == null) throw new java.lang.NullPointerException();
		else return true;
	}
	
	public static void main(String[] args) {
		Deque<Integer> d = new Deque<>();
		System.out.println("Start count: " + d.count);
		
		d.addFirst(2);
		d.addLast(3);
		d.addFirst(1);
		d.addLast(4);
		d.addLast(5);
		d.addLast(6);
		d.addFirst(0);
		
		System.out.println("End count: " + d.count);
		for(Integer i : d)
			System.out.print(i);
		System.out.println();
		
		d.removeFirst();
		System.out.println("First removed");
		for(Integer i : d)
			System.out.print(i);
		System.out.println();
		
		d.removeLast();
		System.out.println("Last removed");
		for(Integer i : d)
			System.out.print(i);
		System.out.println();
	}
}
