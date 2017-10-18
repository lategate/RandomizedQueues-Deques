/**
 * @author Ryan Boken
 * Assignment: A02 Randomized Queues and Deques
 * Class: CSIS-2420
 * @date 10/4/2017
 */

import edu.princeton.cs.algs4.StdIn;

public class Subset {

	public static void main(String[] args) {
		RandomizedQueue<String> randomizedQueue = new RandomizedQueue<>();
		
        while (!StdIn.isEmpty()) {
            randomizedQueue.enqueue(StdIn.readString());
        }
        int c = Integer.parseInt(args[0]);
        for (int i = 0; i < c; i++) {
            System.out.println(randomizedQueue.dequeue());
        }

	}

}
