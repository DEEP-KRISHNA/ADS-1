/**
 * the following is the implementation of Binary Search Symbol Tree.
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
final public class BST_NEW<Key extends Comparable<Key>, Value extends Comparable<Value>> {
    private Key[] keys;
    private Value[] values;
    final private int initialSize = 20;
    private int eleSize = 0;

    BST_NEW() {
        keys = (Key[]) new Comparable[initialSize];
        values = (Value[]) new Comparable[initialSize];
    }

    /**
     * if key already exist it will update with value 'v'.
     *
     * else it will add key in sorted order with value 'v'.
     *
     * @param k key.
     * @param v value.
     */
    public void put(final Key k, final Value v) {
        if (eleSize == keys.length) {
            resize();
        }
        boolean flag = false;

        /**
         * here last elemnt comparision happens
         */

        if (eleSize > 0) {
            if (k.compareTo(keys[eleSize - 1]) == 0) {
                values[eleSize - 1] = v;
                flag = true;
            } else if (k.compareTo(keys[eleSize - 1]) == 1) {
                keys[eleSize] = k;
                values[eleSize] = v;
                eleSize++;
                flag = true;
            }
        }

        /**
         * if last comparision happens it wont enter down
         */

        if (flag == false) {
            int i = rank(k);
            if (i < eleSize && keys[i] != null && keys[i].compareTo(k) == 0) {
                values[i] = v;
            } else {
                for (int j = eleSize - 1; j >= i; j--) {
                    keys[j + 1] = keys[j];
                    values[j + 1] = values[j];
                }
                keys[i] = k;
                values[i] = v;
                eleSize++;
            }
        }
    }

    /**
     * the maximum key will be there in the end of array.
     *
     * as array is sorted with keys.
     *
     * @return the value of maximum key.
     */
    public Key max() {
        return keys[eleSize - 1];
    }

    /**
     * It will delete the minimum key and value assosiated with it.
     *
     * as it is already sorted the first element is minimum.
     *
     * it will delete and shift one element to left.
     *
     */
    public void deleteMin() {
        for (int j = 0; j < eleSize - 1; j++) {
            values[j] = values[j + 1];
            keys[j] = keys[j + 1];
        }
        values[eleSize - 1] = null;
        keys[eleSize - 1] = null;
        eleSize = eleSize - 1;
    }

    /**
     * the following function find rank and return the value.
     *
     * @param k for k rank should be found.
     * @return return the rank value.
     */
    public int rank(final Key k) {
        int lo = 0;
        int hi = eleSize - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            int cmp = k.compareTo(keys[mid]);
            if (cmp < 0) {
                hi = mid - 1;
            } else if (cmp > 0) {
                lo = mid + 1;
            } else {
                return mid;
            }
        }
        return lo;
    }

    /**
     * return value assosiated with the key.
     *
     * @param k value assosiated for that particular kaey.
     * @return return the value assosiated with the key.
     */
    public Value get(final Key k) {
        if (eleSize == 0) {
            return null;
        }
        int i = rank(k);
        if (i < eleSize && keys[i].compareTo(k) == 0) {
            return values[i];
        } else {
            return null;
        }
    }

    /**
     * return true if value already present, else false.
     *
     * @param k key value
     * @return true or false.
     */
    public boolean contains(final Key k) {
        if (eleSize == 0) {
            return false;
        }
        int i = rank(k);
        return (i < eleSize && keys[i].compareTo(k) == 0);
    }

    /**
     * if key already present, it will return the value.
     *
     * else return the the rank - 1 value.
     * 
     * @param k key value.
     * @return floor value.
     */
    public Key floor(final Key k) {
        int i = rank(k);
        if (i < eleSize && keys[i].compareTo(k) == 0) {
            return keys[i];
        } else {
            return keys[i - 1];
        }
    }

    /**
     * the following function convert keys into iterable with the help of queue.
     *
     * @return the iterable of keys.
     */
    public Iterable keys() {
        Queue<Key> queue = new Queue<Key>();
        for (int i = 0; i < eleSize; i++) {
            queue.enqueue(keys[i]);
        }
        return queue;
    }

    /**
     * if array is full, the follwoing function will be called for resizing.
     * 
     * it is a helper function.
     */
    private void resize() {
        int currentSize = keys.length;
        Key[] keysTemp = (Key[]) new Comparable[currentSize * 2];
        Value[] valuesTemp = (Value[]) new Comparable[currentSize * 2];
        for (int i = 0; i < currentSize; i++) {
            keysTemp[i] = keys[i];
            valuesTemp[i] = values[i];
        }
        keys = keysTemp;
        values = valuesTemp;
    }

    /**
     * the following function is a helper function.
     * 
     * helps in debugging, print all the keys present.
     */
    public void print() {
        for (int i = 0; i < eleSize; i++) {
            System.out.println(keys[i]);
            System.out.println(values[i]);
            System.out.println();
        }
    }
}
