/**
 * MaxPQ is the implementation of maximum Priority queue.
 *
 * @author Sandeep Kolli.
 */
final public class MaxPQ {
    /**
     * Store the elements in in max Heap order.
     */
    private double[] maxArr;
    /**
     * Keep track of number of elements stored.
     */
    private int eleSize;
    /**
     * you can define your initial size of the array.
     */
    private final int initialSize = 20;

    MaxPQ() {
        maxArr = new double[initialSize];
        eleSize = 0;
    }

    /**
     * the following function start at k node. traverse or swim till top until
     * priority queue condition gets satisfied.
     *
     * @param k represents node
     */
    private void swim(int k) {
        double temp;
        while (k > 1 && (maxArr[k / 2] < maxArr[k])) {
            temp = maxArr[k / 2];
            maxArr[k / 2] = maxArr[k];
            maxArr[k] = temp;
            k = k / 2;
        }
    }

    /**
     * the following function start at k node. traverse or sink down till leaf node
     * until priority queue condition gets satisfied.
     *
     * @param k represents node
     */
    private void sink(int k) {
        double temp;
        while ((2 * k) <= eleSize) {
            int j = 2 * k;
            if (j < eleSize && (maxArr[j] < maxArr[j + 1])) {
                j++;
            }
            if (maxArr[k] > maxArr[j]) {
                break;
            }
            temp = maxArr[k];
            maxArr[k] = maxArr[j];
            maxArr[j] = temp;
            k = j;
        }
    }

    /**
     * the following function will delete the root node and reorder itself. the
     * deleted value gets returned.
     *
     * @return the max element
     */
    public double delMax() {
        double max = maxArr[1];
        double temp = maxArr[1];
        maxArr[1] = maxArr[eleSize];
        maxArr[eleSize] = temp;
        eleSize--;
        sink(1);
        return max;
    }

    /**
     * the following function will insert in the leaf node and reorder itself untill
     * the condition gets satisfied. the deleted value gets returned.
     *
     * @param key value need to be inserted.
     */
    public void insert(final double key) {
        if ((eleSize + 1) >= maxArr.length) {
            resize();
        }
        maxArr[++eleSize] = key;
        swim(eleSize);
    }

    /**
     * when array gets filled up the following function gets called. its a helper
     * function.
     */
    private void resize() {
        double[] temp = new double[maxArr.length * 2];
        for (int i = 0; i < maxArr.length; i++) {
            temp[i] = maxArr[i];
        }
        maxArr = temp;
    }

    /**
     * As the max element is the root node, so it will return first element.
     *
     * @return the maximum element in array
     */
    public double maxElement() {
        return maxArr[1];
    }

    /**
     * The following function will print the first five elements. this function is
     * used for debugging purpose.
     */
    public void maxFirstFive() {
        final int three = 3;
        final int four = 4;
        final int five = 5;
        System.out.println("in max array");
        System.out.println(maxArr[1]);
        System.out.println(maxArr[2]);
        System.out.println(maxArr[three]);
        System.out.println(maxArr[four]);
        System.out.println(maxArr[five]);
        System.out.println();
    }

    /**
     * it is getter function for element size.
     *
     * @return number of elements array contains.
     */
    public int getEleSize() {
        return eleSize;
    }
}
