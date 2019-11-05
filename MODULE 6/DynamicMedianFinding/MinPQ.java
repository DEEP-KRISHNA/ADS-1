/**
 * MaxPQ is the implementation of maximum Priority queue.
 *
 * @author Sandeep Kolli.
 */
final public class MinPQ {
    /**
     * Store the elements in in min Heap order.
     */
    private double[] minArr;
    /**
     * Keep track of number of elements stored.
     */
    private int eleSize;
    /**
     * you can define your initial size of the array.
     */
    private final int initialSize = 20;

    MinPQ() {
        minArr = new double[initialSize];
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
        while (k > 1 && (minArr[k / 2] > minArr[k])) {
            temp = minArr[k / 2];
            minArr[k / 2] = minArr[k];
            minArr[k] = temp;
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
            if (j < eleSize && (minArr[j] > minArr[j + 1])) {
                j++;
            }
            if (minArr[k] < minArr[j]) {
                break;
            }
            temp = minArr[k];
            minArr[k] = minArr[j];
            minArr[j] = temp;
            k = j;
        }
    }

    /**
     * the following function will delete the root node and reorder itself. the
     * deleted value gets returned.
     *
     * @return the min element
     */
    public double delMin() {
        double min = minArr[1];
        double temp = minArr[1];
        minArr[1] = minArr[eleSize];
        minArr[eleSize] = temp;
        eleSize--;
        sink(1);
        return min;
    }

    /**
     * the following function will insert in the leaf node and reorder itself untill
     * the condition gets satisfied. the deleted value gets returned.
     *
     * @param key value need to be inserted.
     */
    public void insert(final double key) {
        if ((eleSize + 1) >= minArr.length) {
            resize();
        }
        minArr[++eleSize] = key;
        swim(eleSize);
    }

    /**
     * when array gets filled up the following function gets called. its a helper
     * function.
     */
    private void resize() {
        double[] temp = new double[minArr.length * 2];
        for (int i = 0; i < minArr.length; i++) {
            temp[i] = minArr[i];
        }
        minArr = temp;
    }

    /**
     * As the min element is the root node, so it will return first element.
     *
     * @return the minimum element in array
     */
    public double minElement() {
        return minArr[1];
    }

    /**
     * The following function will print the first five elements. this function is
     * used for debugging purpose.
     */
    public void minFirstFive() {
        final int three = 3;
        final int four = 4;
        final int five = 5;
        System.out.println("in min array");
        System.out.println(minArr[1]);
        System.out.println(minArr[2]);
        System.out.println(minArr[three]);
        System.out.println(minArr[four]);
        System.out.println(minArr[five]);
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
