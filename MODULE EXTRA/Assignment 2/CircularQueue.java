/**
 * @author Sandeep KOlli (2019501035)
 */

public class CircularQueue {
    private class Node {
        String item;
        Node next;

        Node(String item) {
            this.item = item;
            next = null;
        }
    }

    int size = 0;

    Node last = null;

    public void enqueue(String s) {
        Node newNode = new Node(s);
        if (size == 0) {
            last = newNode;
            last.next = last;
            size++;
        } else {
            Node temp = last.next;
            last.next = newNode;
            last = newNode;
            last.next = temp;
            size++;
        }
    }

    public String dequeue() {
        if (size == 0) {
            String temp = "there is no element to do dequeue";
            return temp;
        } else if (size == 1) {
            String temp = last.item;
            last = null;
            size--;
            return temp;
        } else {
            String temp = last.next.item;
            last.next = last.next.next;
            size--;
            return temp;
        }
    }

    public void print() {
        if (size == 0) {
            System.out.println("There are No elements to print");
        } else if (size == 1) {
            System.out.println(last.item);
        } else {
            Node temp = last.next;
            while (temp != last) {
                System.out.println(temp.item);
                temp = temp.next;
            }
            System.out.println(temp.item);
        }
    }

    public static void main(String[] args) {
        CircularQueue cq = new CircularQueue();
        cq.enqueue("sandy");
        cq.enqueue("krish");
        cq.enqueue("krishh");
        cq.enqueue("krishhh");
        System.out.println("------dequeue operations------");
        System.out.println(cq.dequeue());
        System.out.println(cq.dequeue());
        System.out.println("-----remaining elements in the queue----");
        cq.print();
    }
}