import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdRandom;

/**
 * @author Ryan Boken
 * Assignment: A02 Randomized Queues and Deques
 * Class: CSIS-2420
 * @date 10/4/2017
 * Credits: Derived from ResizingArrayQueue written by Robert Sedgewick and Kevin Wayne
 */
public class RandomizedQueue<Item> implements Iterable<Item> {
	
	private int count;
	private Item[] queue;
	private int first;
	private int last;
	
	// construct an empty randomized queue
	public RandomizedQueue() {
		count = 0;
		queue = (Item[]) new Object[2];
		first = 0;
        last = -1;
	}
	
	// is the queue empty?
	public boolean isEmpty() {
		return count == 0;
	}
	
	// return the number of items on the queue
	public int size() {
		return count;
	}
	
	// add the item
	public void enqueue(Item item) {
		if (item == null) throw new java.lang.NullPointerException(); 
		// double size of array if necessary and recopy to front of array
        if (count == queue.length) resize(2*queue.length);   // double size of array if necessary
        queue[++last] = item; // add item
        count++;
	}
	
	// delete and return a random item
	public Item dequeue() {
		if (count == 0) throw new java.lang.UnsupportedOperationException();
		int i = StdRandom.uniform(count);
		Item e = queue[i];
		exch(queue, i, last);
		queue[last] = null; //handle loitering
		last--;
		count--;
		return e;
	}
	
	 // exchange a[i] and a[j]
    private static void exch(Object[] a, int i, int j) {
        Object swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }
	
	// return (but do not delete) a random item
	public Item sample() {
		if (count == 0) throw new java.util.NoSuchElementException();
		return queue[StdRandom.uniform(count)];
	}
	
	// return an independent iterator over items in random order
	@Override
	public Iterator<Item> iterator() { return new RandomizedQueueIterator(); }
	
	private class RandomizedQueueIterator implements Iterator<Item> {
		
		Item[] random;
		int i;
		
		private RandomizedQueueIterator() {
			random = Arrays.copyOfRange(queue, first, last + 1);
			StdRandom.shuffle(random);
			i = 0;
		}
		
		@Override
		public boolean hasNext() { return i < random.length; }

		@Override
		public Item next() {
			if (!hasNext()) throw new NoSuchElementException();
			Item e = random[i++];
			return e;
		}
		
		@Override
		public void remove() { throw new UnsupportedOperationException(); }
		
	}
	
	private void resize(int capacity) {
        assert capacity >= count;
        Item[] temp = (Item[]) new Object[capacity];
        for (int i = 0; i < count; i++) {
            temp[i] = queue[(first + i) % queue.length];
        }
        queue = temp;
    }
	   
	public static void main(String[] args) {
		RandomizedQueue<Integer> r = new RandomizedQueue<>(); 
		
		System.out.println("Starting size: " + r.size());
		r.enqueue(1);
		r.enqueue(2);
		r.enqueue(3);
		r.enqueue(4);
		
		System.out.println("Queued size: " + r.size());
		
		for (Integer i : r)
			System.out.print(i);
		
		System.out.println();
		System.out.println("Sample: " + r.sample());
		
		System.out.println("Dequeue");
		System.out.println(r.dequeue());
		System.out.println(r.dequeue());
		System.out.println("Queued size: " + r.size());
		
		for (Integer i : r)
			System.out.print(i);

	}

}
