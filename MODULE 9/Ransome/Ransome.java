import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

/**
 * the following class implements the functionalities of ransome.
 * 
 * the following class uses hashing using sequence chaining implementation.
 * 
 * @author Sandeep Krishna.
 */
class Ransome {

    /**
     * as it is a sequence chaining implementation.
     * 
     * a series of nodes required.
     * 
     * the following data structure holds word value and its count.
     */
    private class Node {
        String word;
        int count;
        Node next;

        Node() {
            next = null;
        }

        Node(String s, int c, Node n) {
            word = s;
            count = c;
            next = n;
        }
    }

    /**
     * all words present in the text file stored in string format.
     */
    String totalWords = "";
    /**
     * the above stored words were splitted with space as delimitor and saved in the
     * following array.
     */
    String[] words;
    /**
     * nodes size initially declaring as 50.
     */
    int numberOfNodes = 50;
    /**
     * array of nodes
     */
    Node[] wordsSummary = new Node[numberOfNodes];

    /**
     * the following function will calculate and return hash value for the
     * particular string
     *
     * @param str string for which you want to calculate hash value.
     * @return the hash value for that particular string.
     */
    private int hash(String str) {
        int hashValue = 0;
        hashValue = ((str.hashCode() * 31) % numberOfNodes);
        return Math.abs(hashValue);
    }

    /**
     * if you call the following function, all the words present in the file will be
     * arranged according to seperate chaining concept.
     * 
     * on each word some operations were done like, converting the word into
     * lowercase so that there wont be any anomoly in inserting and retreval.
     * 
     * if the word ends with "." then the word is being truncted till N-1 positon,
     * this helps in increasing the efficiency of the code.
     * 
     * if the word appear for twice or more, the count gets keep on updating.
     * 
     * the summary of words will get stored in wordsSummary nodes array.
     */
    public void createWordsSummary() {
        words = totalWords.split(" ");
        String currentWord;
        int currentHash;
        for (int i = 0; i < words.length; i++) {
            if (!words[i].equals("")) {
                if (words[i].endsWith(".")) {
                    currentWord = words[i].substring(0, words[i].length() - 1).toLowerCase();
                } else {
                    currentWord = words[i].toLowerCase();
                }

                currentHash = hash(currentWord);
                if (wordsSummary[currentHash] == null) {
                    Node newElement = new Node(currentWord, 1, null);
                    wordsSummary[currentHash] = newElement;
                } else {
                    int updateFlag = 0;
                    for (Node x = wordsSummary[currentHash]; x != null; x = x.next) {
                        if (currentWord.equals(x.word)) {
                            x.count = x.count + 1;
                            updateFlag = 1;
                            break;
                        }
                    }
                    if (updateFlag == 0) {
                        wordsSummary[currentHash] = new Node(currentWord, 1, wordsSummary[currentHash]);
                    }
                }
            }
        }
    }

    /**
     * the following function searches for the word.
     * 
     * if the word present it will return the frequency, if its not present it will
     * return -1.
     * 
     * @param str the string you want to search.
     * @return the frequency
     */
    public int get(String str) {
        String currentWord;
        if (str.endsWith(".")) {
            currentWord = str.substring(0, str.length() - 1).toLowerCase();
        } else {
            currentWord = str.toLowerCase();
        }
        int i = hash(currentWord);
        for (Node x = wordsSummary[i]; x != null; x = x.next) {
            if (currentWord.equals(x.word)) {
                return x.count;
            }
        }
        return -1;
    }

    /**
     * As ransom deals with huge sentences, its always appropriate to read the text
     * file and converting it into string.
     * 
     * the following function will read the text file from system and convert it
     * into string.
     * 
     * @param fileName path of file
     * @throws IOException handle the exception
     */
    private void readFile(String fileName) throws IOException {
        Path path = Paths.get(fileName);
        Scanner scanner = new Scanner(path);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            totalWords = totalWords + line;
        }
        scanner.close();
    }

    /**
     * the following function will compare two inputs.
     * 
     * the following function will get called only after dictionaries gets created
     * for both the text files.
     * 
     * it will compare if 2nd input is part of first input or not.
     * 
     * even if single word doesnt match it will return false.
     * 
     * word by word frequency gets compared.
     * 
     * for a particular word frequency of second should be lower than first one. if
     * not return false.
     * 
     * @param inpt the input you want to compare
     */
    public void compare(Ransome inpt) {
        int sameFlag = 1;
        for (int i = 0; i < words.length; i++) {
            if (inpt.get(words[i]) == -1) {
                sameFlag = 0;
                break;
            } else if (get(words[i]) > inpt.get(words[i])) {
                sameFlag = 0;
                break;
            }
        }
        if (sameFlag == 1) {
            System.out.println("The second input contains in first one");
        } else {
            System.out.println("The second input does not contain in first one");
        }
    }

    /**
     * the following function helps in debugging.
     */
    public void dummy() {
        for (int i = 0; i < words.length; i++) {
            System.out.println(words[i]);
            System.out.println(hash(words[i]));
            System.out.println(get(words[i]));
        }
    }

    /**
     * the following function helps in debugging. at k node what all elememts
     * present.
     */
    public void dummyNode(int k) {
        for (Node x = wordsSummary[k]; x != null; x = x.next) {
            System.out.println(x.word);
            System.out.println(x.count);
        }
    }

    public static void main(String[] args) throws Exception {
        Ransome input1 = new Ransome();
        input1.readFile("C:\\Users\\PINAKA\\Desktop\\ADS 1\\ADS-1\\MODULE 9\\Ransome\\Input1.txt");
        input1.createWordsSummary();
        Ransome input2 = new Ransome();
        input2.readFile("C:\\Users\\PINAKA\\Desktop\\ADS 1\\ADS-1\\MODULE 9\\Ransome\\Input2.txt");
        input2.createWordsSummary();
        input2.compare(input1);
    }
}