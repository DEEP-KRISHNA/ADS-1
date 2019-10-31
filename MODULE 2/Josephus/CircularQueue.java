/**
 * implementation of circular queue.
 *
 * @author Sandeep Kolli.
 */
public class CircularQueue {
    private class Node {
        /**
         * ele is data stored inside node.
         */
        private int ele;
        /**
         * next points to the next node.
         */
        private Node next;

        Node(final int a) {
            this.ele = a;
            next = null;
        }
    }

    /**
     * head is reference for the first node.
     */
    private Node head;
    /**
     * tail is reference for the last node.
     */
    private Node tail;

    CircularQueue() {
        head = null;
        tail = null;
    }

    /**
     * each element addaed in to the queue in a circular way.
     *
     * @param a element need to be added.
     */
    public void add(final int a) {
        if (head != null) {
            Node temp = new Node(a);
            tail.next = temp;
            tail = temp;
            tail.next = head;
        } else {
            Node temp = new Node(a);
            head = temp;
            tail = temp;
        }
    }

    /**
     * removes a element and still the order will be in circular way.
     *
     * @param a element you want to remove
     */
    public void remove(final int a) {
        Node temp = head;
        while (temp.next.ele != a) {
            temp = temp.next;
        }
        if (temp.next == head) {
            head = temp.next.next;
            temp.next = temp.next.next;
        } else if (temp.next == tail) {
            temp.next = temp.next.next;
            tail = temp;
        } else {
            temp.next = temp.next.next;
        }
    }

    // public void display() {
    // Node temp = head;
    // int count = 1;
    // while (temp != null && count != 15) {
    // System.out.println(temp.ele);
    // temp = temp.next;
    // count++;
    // }
    // }

    /**
     * prints the head element.
     */
    public void headElement() {
        System.out.println("head element is :" + head.ele);
    }

    /**
     * prints the tail element.
     */
    public void tailElement() {
        System.out.println("tail element is :" + tail.ele);
    }

    /**
     * josph pattern is solved here.
     *
     * @param a at how many turns you want to remove
     * @return the rmoved pattern
     */
    public String removeAccordingToEle(final int a) {
        String finalSet = "";
        int counter = 1;
        Node temp = head;
        while (head != tail) {
            if (counter == a) {
                finalSet = finalSet + temp.ele + " ";
                remove(temp.ele);
                counter = 1;
            } else {
                counter++;
            }
            temp = temp.next;
        }
        finalSet = finalSet + temp.ele;
        return finalSet;
    }
}
