package algorithms;

public class DivideAndConquerMain {

    public static void main(String...args) {
        int[] testArray = {13, -3, -25, 20, -3, -16, -23, 18, 20, -7, 12, -5, -22, 15, -4, 7};
        DivideAndConquer divideAndConquer = new DivideAndConquer();

        int maxSum = divideAndConquer.findMaxSubarray(testArray, 0, testArray.length - 1);
        System.out.println(maxSum);
        System.out.println(divideAndConquer.getCrossLeft() + " " + divideAndConquer.getCrossRight());
    }
}
