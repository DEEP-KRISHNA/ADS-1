import java.util.Arrays;

/**
 * The following is the implementation of Hashing using linear probing.
 * 
 * @author Sandeep Kolli
 */
class LinearProbing {
    private final int initialSize = 15; // inittial Size of the array.
    private final int randomPrime = 31; // Prime number for hashing helps in generating unique keys.
    private final int nullValues = -969; // Null value representation.
    String[] keys = new String[initialSize];
    int[] values = new int[initialSize];

    /**
     * when an object gets created the following constructor will initial array.
     */
    LinearProbing() {
        Arrays.fill(values, nullValues);
    }

    /**
     * the following function will calculate the hash value.
     * 
     * @param str the string for which you want to calculate the hash function.
     * @return the hash value.
     */
    private int hash(final String str) {
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
    public void put(final String key, final int val) {
        int i = hash(key);
        while (keys[i] != null) {
            if (keys[i].equals(key)) {
                break;
            }
            i = (i + 1) % initialSize;
        }
        keys[i] = key;
        values[i] = val;
    }

    public int get(final String key) {
        int i = hash(key);
        while (keys[i] != null) {
            if (keys[i].equals(key)) {
                break;
            }
            i = (i + 1) % initialSize;
        }
        if (keys[i] == null) {
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
    public void delete(final String key) {
        int i = hash(key);
        keys[i] = null;
        values[i] = nullValues;
        i = (i + 1) % initialSize;
        while (keys[i] != null) {
            String tempKey = keys[i];
            int tempVal = values[i];
            keys[i] = null;
            values[i] = nullValues;
            put(tempKey, tempVal); // rehashing
            i = (i + 1) % initialSize;
        }
    }

    /**
     * the following function will display the key value pairs in order.
     */
    public void display() {
        for (int i = 0; i < initialSize; i++) {
            System.out.println(keys[i]);
            System.out.println(values[i]);
            if (keys[i] != null) {
                System.out.println(hash(keys[i]));
            }
            System.out.println();
        }
    }

    public static void main(final String[] args) {
        LinearProbing linear = new LinearProbing();
        linear.put("S", 1);
        linear.put("Z", 2);
        linear.put("S", 1);
        // linear.delete("S");
        linear.display();

        // System.out.println(linear.get("A"));
    }
}
