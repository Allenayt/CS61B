public class RabinKarpAlgorithm {


    /**
     * This algorithm returns the starting index of the matching substring.
     * This method will return -1 if no matching substring is found, or if the input is invalid.
     */
    public static int rabinKarp(String input, String pattern) {

        int n = input.length();
        int m = pattern.length();

        if(n < m) {
            return -1;
        }


        RollingString comp = new RollingString(pattern, m);
        RollingString origin = new RollingString(input, m);


        int hashComp = comp.hashCode();

        for (int i = 0; i < n - m + 1; i++) {
            int hashOrigin = origin.hashCode();
            if (hashOrigin == hashComp) {
                if (origin.equals((RollingString) comp)) {
                    return i;
                }
            }
            origin.addChar(input.charAt(m + i));
        }

        return -1;
    }

}
