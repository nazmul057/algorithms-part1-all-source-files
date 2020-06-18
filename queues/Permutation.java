import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;

public class Permutation {
    public static void main(String[] args) {
        RandomizedQueue<String> myTest = new RandomizedQueue<String>();

        StdOut.println("Please Enter value of k: ");
        int k = StdIn.readInt();


        StdOut.println("Please the Strings now: ");

        while (!StdIn.isEmpty()) {
            String a = StdIn.readString();

            myTest.enqueue(a);

        }

        // int k = StdIn.readInt();

        Iterator<String> myTestIterator = myTest.iterator();

        for (int i=0; i < k; i++) {
            StdOut.println(myTestIterator.next());
        }

        /*


        StdOut.println(myTestIterator.hasNext());
        StdOut.println(myTestIterator.next());
        StdOut.println(myTestIterator.hasNext());
        StdOut.println(myTestIterator.next());
        StdOut.println(myTestIterator.hasNext());
        StdOut.println(myTestIterator.next());
        StdOut.println(myTestIterator.hasNext());
        StdOut.println(myTestIterator.next());
        StdOut.println(myTestIterator.hasNext());
        StdOut.println(myTestIterator.next());

         */



    }
}

/*

StdOut.println("Please Enter value of k: ");
        int k = StdIn.readInt();
        StdOut.println("Please Enter the Strings: ");
        String[] a = StdIn.readAllStrings();

        StdRandom.shuffle(a);



        for(int i = 0; i < k; i++){
            StdOut.println(a[i]);
        }
 */