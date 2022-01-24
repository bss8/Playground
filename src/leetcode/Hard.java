package leetcode;


import java.util.HashSet;
import java.util.Set;

public class Hard {
    int[][] board = {
            {5, 3, 0, 0, 7, 0, 0, 0, 0},
            {6, 0, 0, 1, 9, 5, 0, 0, 0},
            {0, 9, 8, 0, 0, 0, 0, 6, 0},
            {8, 0, 0, 0, 6, 0, 0, 0, 3},
            {4, 0, 0, 8, 0, 3, 0, 0, 1},
            {7, 0, 0, 0, 2, 0, 0, 0, 6},
            {0, 6, 0, 0, 0, 0, 2, 8, 0},
            {0, 0, 0, 4, 1, 9, 0, 0, 5},
            {0, 0, 0, 0, 8, 0, 0, 7, 9}};

    int[][] centers = {
            {1, 1}, {1, 4}, {1, 7},
            {4, 1}, {4, 4}, {4, 7},
            {7, 1}, {7, 4}, {7, 7}};

    int[] positionVals = {1,2,3,4,5,6,7,8,9};
    static Set<Integer> set = new HashSet<>();

    public void reset() {
        positionVals = new int[]{1,2,3,4,5,6,7,8,9};
    }

    public static void main(String...args) {
        Hard hard = new Hard();
        for (int i : hard.positionVals)
            set.add(i);

        MedianFinder mf = new MedianFinder();
        mf.addNum(1);
        mf.addNum(2);
        System.out.println(mf.findMedian());
        mf.addNum(3);
        System.out.println(mf.findMedian());
    }

}
