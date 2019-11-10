/**
 * the following is the implementation of BST.
 * 
 * @param <Key>   specify the data type of key.
 * @param <Value> specify the data type of value.
 * @author Sandeep KOlli.
 * @author Bob Sedgewick for queue code (CREDITS)
 */

class BST<Key extends Comparable<Key>, Value extends Comparable<Value>> {

    /**
     * the following holds the reference for root node.
     */
    Node root;

    private class Node {
        private Key key;
        private Value val;
        private Node left, right;
        private int size;

        public Node(Key key, Value val, int size) {
            this.key = key;
            this.val = val;
            this.size = size;
        }
    }

    BST() {
    }

    /**
     * Returns true if this symbol table is empty.
     *
     * @return boolean value.
     */
    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     * Returns true if this symbol table is empty.
     *
     * @return boolean value
     */

    public int size() {
        return size(root);
    }

    /**
     * return number of key-value pairs in BST rooted at x
     * 
     * @param x represents node
     * @return size
     */
    private int size(Node x) {
        if (x == null)
            return 0;
        else
            return x.size;
    }

    /**
     * Does this symbol table contain the given key?
     *
     * @param key the key
     * @return true if this symbol table contains key.
     */
    public boolean contains(Key key) {
        return get(key) != null;
    }

    /**
     * Returns the value associated with the given key.
     *
     * @param key the key
     * @return the value associated with the given key.
     */
    public Value get(Key key) {
        return get(root, key);
    }

    private Value get(Node x, Key key) {
        if (x == null)
            return null;
        int cmp = key.compareTo(x.key);
        if (cmp < 0)
            return get(x.left, key);
        else if (cmp > 0)
            return get(x.right, key);
        else
            return x.val;
    }

    /**
     * Inserts the specified key-value pair into the symbol table, overwriting the
     * old value with the new value if the symbol table already contains the
     * specified key.
     *
     * @param key the key
     * @param val the value
     */
    public void put(Key key, Value val) {
        if (val == null) {
            delete(key);
            return;
        }
        root = put(root, key, val);
    }

    private Node put(Node x, Key key, Value val) {
        if (x == null)
            return new Node(key, val, 1);
        int cmp = key.compareTo(x.key);
        if (cmp < 0)
            x.left = put(x.left, key, val);
        else if (cmp > 0)
            x.right = put(x.right, key, val);
        else
            x.val = val;
        x.size = 1 + size(x.left) + size(x.right);
        return x;
    }

    /**
     * Removes the smallest key and associated value from the symbol table.
     */
    public void deleteMin() {
        root = deleteMin(root);
    }

    private Node deleteMin(Node x) {
        if (x.left == null)
            return x.right;
        x.left = deleteMin(x.left);
        x.size = size(x.left) + size(x.right) + 1;
        return x;
    }

    /**
     * Removes the largest key and associated value from the symbol table.
     */

    public void deleteMax() {
        root = deleteMax(root);
    }

    private Node deleteMax(Node x) {
        if (x.right == null)
            return x.left;
        x.right = deleteMax(x.right);
        x.size = size(x.left) + size(x.right) + 1;
        return x;
    }

    /**
     * Removes the specified key and its associated value from this symbol table
     *
     * @param key the key
     */
    public void delete(Key key) {
        root = delete(root, key);
    }

    private Node delete(Node x, Key key) {
        if (x == null)
            return null;

        int cmp = key.compareTo(x.key);
        if (cmp < 0)
            x.left = delete(x.left, key);
        else if (cmp > 0)
            x.right = delete(x.right, key);
        else {
            if (x.right == null)
                return x.left;
            if (x.left == null)
                return x.right;
            Node t = x;
            x = min(t.right);
            x.right = deleteMin(t.right);
            x.left = t.left;
        }
        x.size = size(x.left) + size(x.right) + 1;
        return x;
    }

    /**
     * Returns the smallest key in the symbol table.
     *
     * @return the smallest key in the symbol table
     */
    public Key min() {
        return min(root).key;
    }

    private Node min(Node x) {
        if (x.left == null)
            return x;
        else
            return min(x.left);
    }

    /**
     * Returns the largest key in the symbol table.
     *
     * @return the largest key in the symbol table
     */
    public Key max() {
        return max(root).key;
    }

    private Node max(Node x) {
        if (x.right == null)
            return x;
        else
            return max(x.right);
    }

    /**
     * Returns the largest key in the symbol table less than or equal to key.
     *
     * @param key the key
     * @return the largest key in the symbol table less than or equal to KEY.
     */
    public Key floor(Key key) {
        Node x = floor(root, key);
        if (x == null)
            return null;
        else
            return x.key;
    }

    private Node floor(Node x, Key key) {
        if (x == null)
            return null;
        int cmp = key.compareTo(x.key);
        if (cmp == 0)
            return x;
        if (cmp < 0)
            return floor(x.left, key);
        Node t = floor(x.right, key);
        if (t != null)
            return t;
        else
            return x;
    }

    /**
     * Returns the smallest key in the symbol table greater than or equal to key.
     *
     * @param key the key
     * @return the smallest key in the symbol table greater than or equal to key.
     */
    public Key ceiling(Key key) {
        Node x = ceiling(root, key);
        if (x == null)
            return null;
        else
            return x.key;
    }

    private Node ceiling(Node x, Key key) {
        if (x == null)
            return null;
        int cmp = key.compareTo(x.key);
        if (cmp == 0)
            return x;
        if (cmp < 0) {
            Node t = ceiling(x.left, key);
            if (t != null)
                return t;
            else
                return x;
        }
        return ceiling(x.right, key);
    }

    /**
     * Return the key in the symbol table whose rank is k.
     *
     * This is the (k+1)st smallest key in the symbol table.
     *
     * @param k the order statistic
     * @return the key in the symbol table of rank k
     */
    public Key select(int k) {
        Node x = select(root, k);
        return x.key;
    }

    // Return key of rank k.
    private Node select(Node x, int k) {
        if (x == null)
            return null;
        int t = size(x.left);
        if (t > k)
            return select(x.left, k);
        else if (t < k)
            return select(x.right, k - t - 1);
        else
            return x;
    }

    /**
     * Return the number of keys in the symbol table strictly less than key.
     *
     * @param key the key
     * @return the number of keys in the symbol table strictly less than key.
     */
    public int rank(Key key) {
        return rank(key, root);
    }

    // Number of keys in the subtree less than key.
    private int rank(Key key, Node x) {
        if (x == null)
            return 0;
        int cmp = key.compareTo(x.key);
        if (cmp < 0)
            return rank(key, x.left);
        else if (cmp > 0)
            return 1 + size(x.left) + rank(key, x.right);
        else
            return size(x.left);
    }
}