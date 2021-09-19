package codepath;

public class WeekOne {

    public static void main(String...args) {
        int maxVal = 1000;
        System.out.println("Sum of multiples of 3 or 5 below " + maxVal + ": " + multiplesOfThreeOrFive(maxVal));

        System.out.println("nth fib term: " + nthFibValue(32));
        System.out.println("sum of even fib values under 4mil: " + sumEvenFibs());
    }

    // Find the sum of all the multiples of 3 or 5 below 1000.
    public static int multiplesOfThreeOrFive(int maxVal) {
        int sum = 0;

        for (int i = 0; i < maxVal; i++ ) {
            if ((i % 3 == 0) || (i % 5 == 0)) {
                sum += i;
            }
        }

        return sum;  // 233,168
    }

    // 0,1,1,2,3,5,8,13
    public static int nthFibValue(int n) {
        if (n <= 1) {
            return n;
        }

        return nthFibValue(n-1) + nthFibValue(n-2);
    }

    // find the nth fibonacci value and also the sum of all even fib values under it
    public static int sumEvenFibs() {
        int maxVal = 4000000;
        int i = 1, j = 1, sum = 0;
        while (i < maxVal) {
            i += j;
            j = i - j; // previous term
            if (i % 2 == 0)
                sum += i;
        }
        return sum;
    }

}
