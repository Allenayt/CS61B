package hw3.hash;

import java.util.List;

public class OomageTestUtility {
    public static boolean haveNiceHashCodeSpread(List<Oomage> oomages, int M) {
        /* TODO:
         * Write a utility function that returns true if the given oomages
         * have hashCodes that would distribute them fairly evenly across
         * M buckets. To do this, convert each oomage's hashcode in the
         * same way as in the visualizer, i.e. (& 0x7FFFFFFF) % M.
         * and ensure that no bucket has fewer than N / 50
         * Oomages and no bucket has more than N / 2.5 Oomages.
         */

        int[] bucket = new int[M];
        int N = oomages.size();
        for (Oomage oo: oomages) {
            int bucketNum = (oo.hashCode() & 0x7FFFFFFF) % M;
            bucket[bucketNum] += 1;
        }

        for (int i = 0; i < M; i++) {
            if ((bucket[i] < N / 50) || (bucket[i] > N / 2.5)) {
                return false;
            }
        }
        /*int[] buckets = new int[M];
        for (int i = 0; i < oomages.size(); i++) {
            int bucketNum = (oomages.get(i).hashCode() & 0x7FFFFFFF) % M;
            buckets[bucketNum] += 1;
        }
        for (int j = 0; j < M; j++) {
            if ((buckets[j] > oomages.size()/2.5) || (buckets[j] < oomages.size()/50.0)) {
                return false;
            }
        }*/
        return true;
    }
}
