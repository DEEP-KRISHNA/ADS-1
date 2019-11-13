import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private class Node {
        Item item;
        Node next;

        Node(Item it) {
            next = null;
            item = it;
        }
    }

    private class ListIterator implements Iterator<Item> {
        private Node current = start;

        public boolean hasNext() {
            return current != null;
        }

        public void remove() {
            throw new UnsupportedOperationException();
            /* not supported */ }

        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            Item item = current.item;
            current = current.next;
            return item;
        }
    }

    private Node start;

    private int size = 0;

    // construct an empty randomized queue
    public RandomizedQueue() {
        start = null;
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return start == null;
    }

    // return the number of items on the randomized queue
    public int size() {
        return size;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
        Node newNode = new Node(item);
        if (start == null) {
            start = newNode;
            size++;
        } else {
            newNode.next = start;
            start = newNode;
            size++;
        }
    }

    // remove and return a random item
    public Item dequeue() {
        if (size == 0) {
            throw new NoSuchElementException();
        } else if (size == 1) {
            Node temp = start;
            start = null;
            size--;
            return temp.item;
        } else if (size == 2) {
            int random = (int) (Math.random() * size) + 1;
            if (random == 1) {
                Node temp = start;
                start = start.next;
                size--;
                return temp.item;
            } else {
                Node temp = start.next;
                start.next = null;
                size--;
                return temp.item;
            }
        } else {
            int random = (int) (Math.random() * size) + 1;
            if (random == 1) {
                Node temp = start;
                start = start.next;
                size--;
                return temp.item;
            } else {
                Node startNode = start;
                int reach = 1;
                while (reach != random - 1) {
                    startNode = startNode.next;
                    reach++;
                }
                Node temp = startNode.next;
                startNode.next = startNode.next.next;
                size--;
                return temp.item;
            }
        }
    }

    // return a random item (but do not remove it)
    public Item sample() {
        Item temp = dequeue();
        enqueue(temp);
        return temp;
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    // unit testing (required)
    public static void main(String[] args) {
        RandomizedQueue<String> r = new RandomizedQueue<String>();
        r.enqueue("Sandy");
        r.enqueue("Krish");
        r.enqueue("legend");
        r.enqueue("anuradha");
        r.enqueue("Kolli");
        System.out.println(r.dequeue());
        // System.out.println(r.sample());
    }

}