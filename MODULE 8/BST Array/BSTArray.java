/**
 * The following is the implementation of BST using Arrays.
 *
 * @author Sandeep Krishna Kolli.
 */
class BSTArray {
    private final int initialSize = 200;
    int[] keys = new int[initialSize];
    int[] keyLeft = new int[initialSize];
    int[] keyRight = new int[initialSize];
    private final int nullValue = -969;

    /**
     * initialise the arrays wgile creating an object.
     */
    BSTArray() {
        for (int i = 0; i < keyLeft.length; i++) {
            keyLeft[i] = -1;
        }
        for (int i = 0; i < keyRight.length; i++) {
            keyRight[i] = -1;
        }
        for (int i = 0; i < keys.length; i++) {
            keys[i] = nullValue;
        }
    }

    /**
     * the following function will insert the key at appropriate position. by
     * satisfying the rules of BST.
     * 
     * @param key value you want to insert.
     */
    void insert(final int key) {
        if (keys[1] == nullValue) {
            keys[1] = key;
        } else {
            int i = 1;
            while (i < keys.length) {
                if (keys[i] < key) {
                    if (keys[(2 * i) + 1] == nullValue) {
                        keys[(2 * i) + 1] = key;
                        keyRight[i] = (2 * i) + 1;
                        break;
                    } else {
                        i = (2 * i) + 1;
                    }
                } else if (keys[i] > key) {
                    if (keys[(2 * i)] == nullValue) {
                        keys[(2 * i)] = key;
                        keyLeft[i] = (2 * i);
                        break;
                    } else {
                        i = (2 * i);
                    }
                }
            }
        }
    }

    /**
     * the following function will delete the key by satisfying the BST properties.
     *
     * @param k key you want to delete.
     */
    void delete(final int k) {
        delete(k, 1);
    }

    void delete(int k, int init) {
        int i = init;
        int right = 0;
        int left = 0;
        int deleteFlag = 0;
        int deletePresent = keys[k];
        while (i < keys.length) {
            if (keys[i] == nullValue) {
                System.out.println("No element found");
                break;
            } else {
                if (k > keys[i]) {
                    i = (2 * i) + 1;
                    right = 1;
                    left = 0;
                } else if (k < keys[i]) {
                    i = 2 * i;
                    left = 1;
                    right = 0;
                } else {
                    if ((keys[2 * i] == nullValue) && (keys[(2 * i) + 1] == nullValue)) {
                        keys[i] = nullValue;
                        deleteFlag = 1;
                        break;
                    } else if (keys[2 * i] == nullValue) {
                        keys[i] = keys[(2 * i) + 1];
                        if (left == 0) {
                            keyRight[i / 2] = (2 * i) + 1;
                        } else if (right == 0) {
                            keyLeft[i / 2] = (2 * i) + 1;
                        }
                        deletePresent = (2 * i) + 1;
                        break;
                    } else if (keys[(2 * i) + 1] == nullValue) {
                        i = 2 * i;
                        if (left == 0) {
                            keyRight[i / 2] = (2 * i);
                        } else if (right == 0) {
                            keyLeft[i / 2] = (2 * i);
                        }
                        deletePresent = (2 * i);
                        break;
                    } else {
                        // sandy
                    }
                }
            }
        }
        if (deleteFlag == 0) {
            delete(keys[deletePresent], deletePresent);
        }

    }

    /**
     * The following function will print all the elements in a array.
     */
    void print() {
        for (int i = 0; i < keys.length; i++) {
            System.out.println(keys[i]);
        }
    }

    public static void main(final String[] args) {
        BSTArray b = new BSTArray();
        // b.insert(3);
        b.insert(10);
        b.insert(9);
        b.insert(8);
        b.insert(7);
        b.insert(6);
        b.insert(5);
        b.insert(4);
        b.delete(4);
        b.print();
    }
}
