/**
 * the following is the implementation of Sequential Search Symbol Tree.
 *
 * data types of key and value should implement Comparable.
 *
 * So primitive datatypes are not allowed only wrapper classes are allowed.
 *
 * @param <Key>   Data Type of Key - it should be Comparable.
 * @param <Value> Data Type of value - it should be Comparable.
 * @author Sandeep Krishna Kolli.
 * @author bob sedgewick for Queue (CREDITS).
 */
final public class SequentialSearchST<Key extends Comparable<Key>, Value extends Comparable<Value>> {

    private class SeqList {
        public Key key;
        public Value value;
        SeqList node;
    }

    SeqList head;

    SequentialSearchST() {
        head = null;
    }

    /**
     * if key already exist it will update with value 'v'.
     *
     * else it will add key at head with value 'v'.
     *
     * @param k key.
     * @param v value.
     */
    public void put(final Key k, final Value v) {
        SeqList temp = new SeqList();
        if (head == null) {
            temp.key = k;
            temp.value = v;
            temp.node = null;
            head = temp;
        } else {
            SeqList tempHead = head;
            int flag = 0;
            while (tempHead != null) {
                if (tempHead.key.equals(k)) {
                    tempHead.value = v;
                    flag = 1;
                    break;
                }
                tempHead = tempHead.node;
            }
            if (flag == 0) {
                temp.key = k;
                temp.value = v;
                temp.node = head;
                head = temp;
            }
        }
    }

    /**
     * return value assosiated with the key.
     *
     * the list will get reodered, the searched element moved to first.
     *
     * @param k value assosiated for that particular key.
     * @return return the value assosiated with the key.
     */
    public Value get(final Key k) {
        SeqList temp = head;
        int flag = 0;
        while (temp.node != null) {
            if (temp.node.key.equals(k)) {
                flag = 1;
                break;
            }
            temp = temp.node;
        }
        if (flag == 0) {
            return null;
        } else {
            SeqList change = temp.node;
            if (change.node == null) {
                temp.node = null;
            } else {
                temp.node = change.node;
            }
            putLast(change);
            return change.value;
        }
    }

    /**
     * the following function is a helper function.
     *
     * @param last node to be inserted in end.
     */
    private void putLast(final SeqList last) {
        SeqList temp = head;
        while (temp.node != null) {
            // System.out.println(temp.value);
            temp = temp.node;
        }
        temp.node = last;
        last.node = null;
    }

    /**
     * the following function convert keys into iterable with the help of queue.
     *
     * @return the iterable of keys.
     */
    public Iterable keys() {
        Queue<Key> queue = new Queue<Key>();
        SeqList temp = head;
        while (temp != null) {
            queue.enqueue(temp.key);
            temp = temp.node;
        }
        return queue;
    }

    /**
     * the following function is a helper function.
     *
     * helps in debugging, print all the keys present.
     */
    public void print() {
        SeqList temp = head;
        while (temp != null) {
            System.out.println(temp.key);
            temp = temp.node;
        }
    }

}
