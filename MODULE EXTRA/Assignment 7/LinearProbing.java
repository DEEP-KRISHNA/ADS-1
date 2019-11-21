import java.util.Arrays;

/**
 * The following is the implementation of Hashing using linear probing.
 * 
 * @author Sandeep Kolli
 */
class LinearProbing {
    private final int initialSize = 1000000; // inittial Size of the array.
    private final int randomPrime = 31; // Prime number for hashing helps in generating unique keys.
    private final int nullValues = -969; // Null value representation.
    int[] keys = new int[initialSize];
    int[] values = new int[initialSize];

    /**
     * when an object gets created the following constructor will initial array.
     */
    LinearProbing() {
        Arrays.fill(values, nullValues);
        Arrays.fill(keys, nullValues);
    }

    /**
     * the following function will calculate the hash value.
     * 
     * @param str the int for which you want to calculate the hash function.
     * @return the hash value.
     */
    private int hash(final Integer str) {
        int hashValue = 0;
        hashValue = ((str.hashCode() * randomPrime) % initialSize);
        return Math.abs(hashValue);
    }

    /**
     * the following function will insert the key value pairs into the array.
     * 
     * if key already exist value gets updated otherwise new key value pair gets
     * inserted.
     * 
     * @param key key.
     * @param val value assosiated with that key.
     */
    public void put(final int key, final int val) {
        int i = hash(key);
        while (keys[i] != -969) {
            if (keys[i] == key) {
                break;
            }
            i = (i + 1) % initialSize;
        }
        keys[i] = key;
        values[i] = val;
    }

    public int get(final int key) {
        int i = hash(key);
        while (keys[i] != -969) {
            if (keys[i] == key) {
                break;
            }
            i = (i + 1) % initialSize;
        }
        if (keys[i] == -969) {
            return nullValues;
        } else {
            return values[i];
        }
    }

    /**
     * the following function will delete the key, value pairs.
     * 
     * after deleteing the particular pair, rehashing will be done untill it find
     * the null value.
     * 
     * @param key the key value pair you want to delete.
     */
    public void delete(final int key) {
        int i = hash(key);
        keys[i] = -969;
        values[i] = nullValues;
        i = (i + 1) % initialSize;
        while (keys[i] != -969) {
            int tempKey = keys[i];
            int tempVal = values[i];
            keys[i] = -969;
            values[i] = nullValues;
            put(tempKey, tempVal); // rehashing
            i = (i + 1) % initialSize;
        }
    }

    /**
     * the following function will display the key value pairs in order.
     */
    public void display() {
        System.out.println(keys[31]);
        System.out.println(values[31]);
        System.out.println();
        System.out.println(keys[62]);
        System.out.println(values[62]);
        System.out.println();
        System.out.println(keys[93]);
        System.out.println(values[93]);
        System.out.println();
    }

    public static void main(final String[] args) {
        LinearProbing linear = new LinearProbing();
        int k = 0;
        while (k < 500000) {
            int temp = (int) (Math.random() * 1000000);
            if (linear.get(temp) == -969) {
                linear.put(temp, temp);
                k++;
            }
        }

        int count = 0;
        for (int i = 0; i < 1000000; i = i + 100) {
            if (linear.get(i) != -969) {
                count++;
            }
        }
        double finall = (double) count / 10000;
        System.out.println(finall);
    }
}
