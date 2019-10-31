/**
 * Stack class implements stack data structure.
 *
 * @author Sandeep Kolli.
 */

public class Stack {
    /**
     * charArr stores all symbols.
     */
    private char[] charArr;
    /**
     * index points to current element.
     */
    private int index;
    /**
     * ten is a magic number which indicates initial size.
     */
    private final int ten = 10;

    Stack() {
        charArr = new char[ten];
        index = 0;
    }

    /**
     *
     * @param a charachter input. push the char into the stack.
     */

    public void push(final char a) {
        if (index == charArr.length) {
            resize();
        }
        charArr[index] = a;
        index++;
    }

    /**
     * pop will delete the element from array.
     *
     * @return the most recently inserted char
     */

    public char pop() {
        char temp = charArr[index - 1];
        charArr[index - 1] = '\0';
        index = index - 1;
        return temp;
    }

    /**
     * array gets resized when its reached to max limit.
     */

    public void resize() {
        char[] temp = new char[2 * charArr.length];
        for (int i = 0; i < charArr.length; i++) {
            temp[i] = charArr[i];
        }
        charArr = temp;
    }

    /**
     * checks whether array is empty or not.
     *
     * @return true if array is empty else false.
     */

    public boolean isEmpty() {
        return (index == 0);
    }
}
