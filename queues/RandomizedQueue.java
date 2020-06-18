import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

// import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;




public class RandomizedQueue<Item> implements Iterable<Item> {
    private int theArrayElement;
    private int totalArrayElements;
    private Item[] s;

    // construct an empty randomized queue
    public RandomizedQueue() {
        this.theArrayElement = 0;
        this.totalArrayElements = 0;
        this.s = (Item[]) new Object[1];
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return this.totalArrayElements == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        // StdOut.println("The array element at noon: " + this.theArrayElement);
        return this.totalArrayElements;
    }

    // add the item
    public void enqueue(Item item) {

        if (item == null) throw new IllegalArgumentException("This is not right. And Audri wrote this.");

        if (this.theArrayElement == this.s.length) this.resize(2 * this.theArrayElement);
        this.s[this.theArrayElement++] = item;
        this.totalArrayElements += 1;

    }

    private void resize(int capacity) {
        Item[] copy = (Item[]) new Object[capacity];

        int j = 0;
        for (int i = 0; i < this.theArrayElement; i++) {
            if (s[i] != null) {
                copy[j++] = s[i];
                // j++;
            }
        }

        this.s = copy;
        this.theArrayElement = j;

        // StdOut.println("The array element: " + this.theArrayElement);
    }

    // remove and return a random item
    public Item dequeue() {

        if (this.totalArrayElements == 0) throw new NoSuchElementException("No element remaining to deque.  And Audri wrote this.");

        Item itemToBeReturned;
        int randomNum = StdRandom.uniform(0, this.theArrayElement);

        // StdOut.println("Random Number: " + randomNum);

        if (this.s[randomNum] == null) {
            int i = 1;
            // int j = 1;
            while (true) {
                if (((randomNum - i) > -1) && (this.s[randomNum - i] != null)) {

                    itemToBeReturned = this.s[randomNum - i];
                    this.s[randomNum - i] = null;
                    this.totalArrayElements -= 1;
                    // StdOut.println("11");
                    break;

                }
                else if (((randomNum + i) < this.theArrayElement) && (this.s[randomNum + i] != null)) {
                    if (this.s[randomNum + i] != null) {
                        itemToBeReturned = this.s[randomNum + i];
                        this.s[randomNum + i] = null;
                        this.totalArrayElements -= 1;
                        // StdOut.println("22");
                        break;
                    }
                }
                else i++;
            }
        }
        else {
            itemToBeReturned = this.s[randomNum];
            this.s[randomNum] = null;
            this.totalArrayElements -= 1;
        }

        // StdOut.println("In Deque the arrayelement: " + this.theArrayElement);
        if ((this.totalArrayElements > 0) && (this.totalArrayElements <= this.theArrayElement/4)) resize(this.theArrayElement/2);
        return itemToBeReturned;

    }

    // return a random item (but do not remove it)
    public Item sample() {

        if (this.totalArrayElements == 0) throw new NoSuchElementException("No element remaining to sample.  And Audri wrote this.");

        Item itemToBeReturned;
        int randomNum = StdRandom.uniform(0, this.theArrayElement);

        // StdOut.println("Random Number: " + randomNum);

        if (this.s[randomNum] == null) {
            int i = 1;
            // int j = 1;
            while (true) {
                if (((randomNum - i) > -1) && (this.s[randomNum - i] != null)) {

                    itemToBeReturned = this.s[randomNum - i];

                    // StdOut.println("11");
                    break;

                }
                else if (((randomNum + i) < this.theArrayElement) && (this.s[randomNum + i] != null)) {
                    if (this.s[randomNum + i] != null) {
                        itemToBeReturned = this.s[randomNum + i];

                        // StdOut.println("22");
                        break;
                    }
                }
                else i++;
            }
        }
        else {
            itemToBeReturned = this.s[randomNum];

        }


        return itemToBeReturned;
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        // int hi = this.theArrayElement;
        StdRandom.shuffle(s, 0, this.theArrayElement);
        return new RandomArrayIterator();
    }

    private class RandomArrayIterator implements Iterator<Item> {
        private int i = theArrayElement;

        public boolean hasNext() { return i > 0; }
        public void remove() { throw new UnsupportedOperationException("You cannot use this.  And Audri Wrote this."); }
        public Item next() {

            if (!hasNext()) throw new NoSuchElementException("No items remaining to next to. And Audri Wrote this.");

            return s[--i]; }
    }



    // unit testing (required)
    public static void main(String[] args) {

        RandomizedQueue<String> myTest = new RandomizedQueue<String>();

        /*

        1111111111111111111111111111111

        myTest.enqueue("1");
        myTest.enqueue("2");
        myTest.enqueue("3");
        myTest.enqueue("4");
        myTest.enqueue("5");
        myTest.enqueue("6");
        myTest.enqueue("7");
        myTest.enqueue("8");
        myTest.enqueue("9");
        myTest.enqueue("10");
        myTest.enqueue("11");
        myTest.enqueue("12");
        myTest.enqueue("13");
        myTest.enqueue("14");
        myTest.enqueue("15");
        myTest.enqueue("16");


        // StdOut.println(myTest.sample());
        StdOut.println("Size: " + myTest.size());

        StdOut.println(Arrays.toString(myTest.s));

        StdOut.println(myTest.dequeue());
        StdOut.println(myTest.dequeue());
        StdOut.println(myTest.dequeue());
        StdOut.println(myTest.dequeue());
        StdOut.println(myTest.dequeue());
        StdOut.println(myTest.dequeue());
        StdOut.println(myTest.dequeue());
        StdOut.println(myTest.dequeue());
        StdOut.println(myTest.dequeue());
        StdOut.println(myTest.dequeue());
        StdOut.println(myTest.dequeue());
        StdOut.println(myTest.dequeue());

        StdOut.println(myTest.dequeue());
        StdOut.println(myTest.dequeue());
        StdOut.println(myTest.dequeue());
        StdOut.println("Size: " + myTest.size());
        StdOut.println(Arrays.toString(myTest.s));
        StdOut.println(myTest.dequeue());
        StdOut.println("Size: " + myTest.size());
        StdOut.println(Arrays.toString(myTest.s));

        StdOut.println("Enqueueing");
        myTest.enqueue("1");

        StdOut.println("Size: " + myTest.size());
        StdOut.println(Arrays.toString(myTest.s));

        StdOut.println("Enqueueing");
        myTest.enqueue("2");

        StdOut.println("Size: " + myTest.size());
        StdOut.println(Arrays.toString(myTest.s));

        StdOut.println("Enqueueing");
        myTest.enqueue("3");

        StdOut.println("Size: " + myTest.size());
        StdOut.println(Arrays.toString(myTest.s));



        for (int i=0; i < 5; i++) {

            StdOut.println("Enqueueing");
            myTest.enqueue("3");

            StdOut.println("Size: " + myTest.size());
            StdOut.println(Arrays.toString(myTest.s));
        }


        for (int i=0; i < 8; i++) {

            StdOut.println("Dequeing");
            myTest.dequeue();

            StdOut.println("Size: " + myTest.size());
            StdOut.println(Arrays.toString(myTest.s));
        }

        1111111111111111111111111111111111111

        */

        /*

        2222222222222222222222222222222222222222


        // StdOut.println(myTest.s[1]);



        // myTestIterator.remove();

        // StdOut.println(myTestIterator.next());
        // StdOut.println(myTestIterator.next());
        // StdOut.println(myTestIterator.next());
        // StdOut.println(myTestIterator.next());
        // StdOut.println(myTestIterator.hasNext());
        // StdOut.println(myTestIterator.next());
        // StdOut.println(myTestIterator.next());
        // StdOut.println(myTestIterator.hasNext());
        // StdOut.println(myTestIterator.next());


        StdOut.println(myTest.dequeue());
        StdOut.println(myTest.dequeue());
        StdOut.println(myTest.dequeue());
        StdOut.println(myTest.dequeue());
        StdOut.println(myTest.dequeue());
        StdOut.println(myTest.dequeue());

        StdOut.println(Arrays.toString(myTest.s));

        StdOut.println(myTest.size());

        StdOut.println(myTest.sample());
        StdOut.println(myTest.sample());
        StdOut.println(myTest.sample());
        StdOut.println(myTest.sample());
        StdOut.println(myTest.sample());
        StdOut.println(myTest.sample());
        22222222222222222222222222222222222

        */

        myTest.enqueue("Trying");
        myTest.enqueue("to");
        myTest.enqueue("test");
        myTest.enqueue("this");
        myTest.enqueue("thing.");
        // myTest.enqueue("Again");
        // myTest.enqueue("but");

        StdOut.println(myTest.isEmpty());
        StdOut.println(myTest.size());

        StdOut.println(myTest.sample());

        Iterator<String> myTestIterator =  myTest.iterator();

        StdOut.println(myTestIterator.next());
        StdOut.println(myTestIterator.next());
        StdOut.println(myTestIterator.next());
        StdOut.println(myTestIterator.hasNext());

        StdOut.println(myTest.dequeue());

        // StdOut.println(Arrays.toString(myTest.s));

    }

}


