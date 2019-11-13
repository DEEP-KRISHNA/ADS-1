import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    private class Node {
        Item item;
        Node left;
        Node right;

        Node(Item it) {
            left = null;
            right = null;
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
            current = current.right;
            return item;
        }
    }

    private Node start;
    private Node end;

    private int size = 0;

    // construct an empty deque
    public Deque() {
        start = null;
        end = null;
    }

    // is the deque empty?
    public boolean isEmpty() {
        return (start == null && end == null);
    }

    // return the number of items on the deque
    public int size() {
        return size;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
        Node newNode = new Node(item);
        if (isEmpty()) {
            start = newNode;
            end = newNode;
            size++;
        } else {
            newNode.right = start;
            start.left = newNode;
            start = newNode;
            size++;
        }
    }

    // add the item to the back
    public void addLast(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
        Node newNode = new Node(item);
        if (isEmpty()) {
            start = newNode;
            end = newNode;
            size++;
        } else {
            newNode.left = end;
            end.right = newNode;
            end = newNode;
            size++;
        }
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (size() == 0) {
            throw new NoSuchElementException();
        } else if (size() == 1) {
            Item it = start.item;
            start = null;
            end = null;
            size--;
            return it;
        } else {
            Item it = start.item;
            start = start.right;
            start.left = null;
            size--;
            return it;
        }
    }

    // remove and return the item from the back
    public Item removeLast() {
        if (size() == 0) {
            throw new NoSuchElementException();
        } else if (size() == 1) {
            Item it = end.item;
            start = null;
            end = null;
            size--;
            return it;
        } else {
            Item it = end.item;
            end = end.left;
            end.right = null;
            size--;
            return it;
        }
    }

    // return an iterator over items in order from front to back

    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    // unit testing (required)
    public static void main(String[] args) {
        Deque<String> s = new Deque<String>();
        s.addFirst("Sandy");
        s.addFirst("Krish");
        s.removeLast();
        Iterator i = s.iterator();
        while (i.hasNext()) {
            System.out.println(i.next());
        }
    }
}