package codepath;

import java.util.HashSet;

public class WeekTwo {
    public static void main(String...args) {
        int[] array = {1, 9, 3, 10, 4, 20 , 2};
        System.out.println(longestSequence(array));

        findPairForSum(array);

    }

    public static int longestSequence(int[] array) {
        HashSet<Integer> set = new HashSet<>();
        int maxSequence = 0;

        for(int x : array)
            set.add(x);

        for(int x : set) {
            int count = 1;
            if(set.contains(x-1)) continue;
            while(set.contains(++x))
                count++;
            maxSequence = Math.max(maxSequence, count);
        }

        return maxSequence;
    }

    /**
     *
     * @param array
     */
    public static void findPairForSum(int[] array) {

    }
}
