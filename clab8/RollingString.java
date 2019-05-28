/**
 * A String-like class that allows users to add and remove characters in the String
 * in constant time and have a constant-time hash function. Used for the Rabin-Karp
 * string-matching algorithm.
 */
import java.util.LinkedList;

class RollingString{


    private int storedLength;
    private LinkedList<Character> storedString;
    private int hashValue;
    /**
     * Number of total possible int values a character can take on.
     * DO NOT CHANGE THIS.
     */
    static final int UNIQUECHARS = 128;

    /**
     * The prime base that we are using as our mod space. Happens to be 61B. :)
     * DO NOT CHANGE THIS.
     */
    static final int PRIMEBASE = 6113;

    /**
     * Initializes a RollingString with a current value of String s.
     * s must be the same length as the maximum length.
     */
    public RollingString(String s, int length) {

        storedLength = length;
        storedString = new LinkedList<>();
        hashValue = 0;
        for (int i = 0; i < length; i++) {
            char ch = s.charAt(i);
            int j = length - i - 1;
            storedString.addLast(ch);
            hashValue += (int) ch * Math.pow(UNIQUECHARS, j);
        }


    }

    /**
     * Adds a character to the back of the stored "string" and 
     * removes the first character of the "string". 
     * Should be a constant-time operation.
     */
    public void addChar(char c) {
        char temp = storedString.removeFirst();
        storedString.addLast(c);
        hashValue -= (int) temp * Math.pow(UNIQUECHARS, storedLength - 1);
        hashValue *= UNIQUECHARS;
        hashValue += (int) c;
    }


    /**
     * Returns the "string" stored in this RollingString, i.e. materializes
     * the String. Should take linear time in the number of characters in
     * the string.
     */
    public String toString() {
        StringBuilder strb = new StringBuilder();
        for (char ch: storedString) {
            strb.append(ch);
        }
        return strb.toString();
    }

    /**
     * Returns the fixed length of the stored "string".
     * Should be a constant-time operation.
     */
    public int length() {
        return storedLength;
    }


    /**
     * Checks if two RollingStrings are equal.
     * Two RollingStrings are equal if they have the same characters in the same
     * order, i.e. their materialized strings are the same.
     */
    @Override
    public boolean equals(Object o) {
        RollingString other = (RollingString) o;

        if (this.toString().equals(other.toString())) {
            return true;
        }
        return false;
    }

    /**
     * Returns the hashcode of the stored "string".
     * Should take constant time.
     */
    @Override
    public int hashCode() {
        return hashValue % PRIMEBASE;
    }
}
