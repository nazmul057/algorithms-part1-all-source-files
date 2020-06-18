import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
// import java.util.ListIterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

    private Node first, last;
    private int sizeOfList;

    private class Node {
        Item item;
        Node next;
        Node previous;
    }

    // construct an empty deque
    public Deque() {
        this.first = null;
        this.last = null;
        this.sizeOfList = 0;
    }

    // is the deque empty?
    public boolean isEmpty() {
        return this.first == null;
    }

    // return the number of items on the deque
    public int size() {
        return this.sizeOfList;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if (item == null) { throw new IllegalArgumentException("This is not valid input for first. And Audri Wrote this."); }

        Node oldFirst = this.first;
        this.first = new Node();
        this.first.item = item;
        this.first.next = oldFirst;
        this.first.previous = null;

        if (this.last == null) this.last = this.first;
        else oldFirst.previous = this.first;

        this.sizeOfList += 1;
    }

    // add the item to the back
    public void addLast(Item item) {
        if (item == null) { throw new IllegalArgumentException("This is not valid input for last. And Audri Wrote this."); }

        Node oldLast = this.last;
        this.last = new Node();
        this.last.item = item;
        this.last.next = null;
        this.last.previous = oldLast;

        if (isEmpty()) this.first = this.last;
        else oldLast.next = this.last;

        this.sizeOfList += 1;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (isEmpty()) { throw new NoSuchElementException(" There is no such element to remove first. And Audri Wrote this."); }

        Item item = this.first.item;
        this.first = this.first.next;
        if (this.first != null) this.first.previous = null; // Added later this line.

        if (isEmpty()) this.last = null;

        this.sizeOfList -= 1;
        return item;
    }

    // remove and return the item from the back
    public Item removeLast() {
        if (isEmpty()) { throw new NoSuchElementException(" There is no such element to remove last. And Audri Wrote this. "); }

        Item item = this.last.item;
        this.last = this.last.previous;
        if (this.last != null) this.last.next = null; //Added later this line
        if (this.last == null) this.first = null;

        this.sizeOfList -= 1;
        return item;

    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new ListIterator();
    }
    private class ListIterator implements Iterator<Item> {
        private Node current = first;

        public boolean hasNext() { return current != null; }
        public void remove() { throw new UnsupportedOperationException(" remove() is not supported here. And Audri Wrote this."); }
        public Item next() {
            if (current == null) { throw new NoSuchElementException(" you cannot go there . And Audri Wrote this."); }

            Item item = current.item;
            current = current.next;
            return item;

        }
    }

    // unit testing (required)
    public static void main(String[] args) {

        Deque<String> myStrings = new Deque<String>();

        // StdOut.println(myStrings.isEmpty());
        // StdOut.println(myStrings.size());

        myStrings.addFirst("test");
        myStrings.addLast("this");
        myStrings.addLast("thing.");
        myStrings.addFirst("to");
        myStrings.addFirst("Trying");


        StdOut.println(myStrings.isEmpty());
        StdOut.println(myStrings.size());

        Iterator<String> myStringIterator =  myStrings.iterator();

        /*

        for (int i=0; i<5; i++) {
            String b = myStringIterator.next();
            StdOut.println(b);
        }

        */

        StdOut.println("Iterator Output: ");

        StdOut.println(myStringIterator.next());
        StdOut.println(myStringIterator.next());
        StdOut.println(myStringIterator.next());
        StdOut.println(myStringIterator.next());
        StdOut.println(myStringIterator.hasNext());
        StdOut.println(myStringIterator.next());
        StdOut.println(myStringIterator.hasNext());

        StdOut.println("The Size: ");
        StdOut.println(myStrings.size());

        StdOut.println(myStrings.removeFirst());
        StdOut.println(myStrings.removeLast());
        StdOut.println(myStrings.removeFirst());
        // StdOut.println(myStrings.removeLast());
        // StdOut.println(myStrings.removeLast());

        StdOut.println(myStrings.size());
        StdOut.println(myStrings.isEmpty());

        /*

        StdOut.println();
        StdOut.println();

        Iterator<String> myStringIterator2 =  myStrings.iterator();

        StdOut.println(myStringIterator2.next());
        StdOut.println(myStringIterator2.next());
        StdOut.println(myStringIterator2.next());
        // StdOut.println(myStringIterator2.next());


        StdOut.println(myStrings.size());



        StdOut.println(myStrings.removeLast());
        StdOut.println(myStrings.removeLast());
        StdOut.println(myStrings.removeLast());
        StdOut.println(myStrings.removeLast());
        StdOut.println(myStrings.removeLast());
        // StdOut.println(myStrings.removeLast());

        StdOut.println(myStrings.size());

        myStrings.addFirst("test");
        myStrings.addLast("this");
        myStrings.addLast("thing.");
        myStrings.addFirst("to");
        myStrings.addFirst("Trying");

        StdOut.println(myStrings.size());

        StdOut.println(myStrings.removeFirst());
        StdOut.println(myStrings.removeLast());
        StdOut.println(myStrings.removeLast());
        StdOut.println(myStrings.removeFirst());
        StdOut.println(myStrings.removeLast());

        StdOut.println(myStrings.size());


         */



    }

}

