import java.util.Arrays;
import java.util.Comparator;
import java.util.function.Supplier;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {

        System.out.println("Welcome to the playground!");
        System.out.println("--------------------------");

        Easy easyProblems = new Easy();

        System.out.println("Let's convert capital letters to lowercase.");
        String changeToLower = "HELLO";
        System.out.println("Changed from" + changeToLower);
        changeToLower = easyProblems.toLowerCase(changeToLower);
        System.out.println(" to " + changeToLower);

        // ***********************************************************************************************************
        // using labels for loops
        // ***********************************************************************************************************
        outer:
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (j == 1)
                    continue outer;
                //System.out.println(" value of j = " + j);
            }
        } // end of outer loop


        int value = 0;
        easyProblems.oddOrEven(value);

        System.out.println("-----");

        double[] leftVals = {100.0d, 40.0d, 30.0d, 10.0d};
        double[] rightVals = {2.0d, 25.0d, 3.0d, 5.0d};
        char[] opcodes = {'a', 's', 'm', 'd'};
        double[] results = new double[opcodes.length];

        for (int i = 0; i < opcodes.length; i++) {
            switch (opcodes[i]) {
                case 'a':
                    results[i] = leftVals[i] + rightVals[i];
                    break;
                case 's':
                    results[i] = leftVals[i] - rightVals[i];
                    break;
                case 'm':
                    results[i] = leftVals[i] * rightVals[i];
                    break;
                case 'd':
                    results[i] = rightVals[i] != 0.0d ? leftVals[i] / rightVals[i] : 0.0d;
                    break;
                default:
                    System.err.println("Error - invalid OpCode!");
                    results[i] = 0.0d;
                    break;
            }
        }

        for (double result : results) {
            System.out.println("Result = " + result);
        }

        System.out.println("--------------------------");
        System.out.println("Let's flip and invert arrays.");

        int[][] A = {{1,1,0},{1,0,1},{0,0,0}};
        System.out.println("Here is the original array: " + Arrays.deepToString(A));
        System.out.println("And here is the flipped and inverted one: " + Arrays.deepToString(easyProblems.flipAndInvertImage(A)));

        System.out.println("--------------------------");
        System.out.println("Let's create a simple tree.");

        TreeNode root = easyProblems.createTree();
        System.out.println("Our root value is " + root.val);
        easyProblems.flag = false;
        easyProblems.target = 22;
        System.out.print("In-order traversal: ");
        easyProblems.inOrderTraversal(root);

        easyProblems.findTreeBranch(root, 0);
        System.out.println("\nCan we find a viable path to a leaf in our tree for given sum of" + easyProblems.target +": " + easyProblems.flag);

        System.out.println("--------------------------");

        // ***********************************************************************************************************
        // Chars are treated as numerics, we can perform arithmetic operations
        // ***********************************************************************************************************
        System.out.println("We can treat chars as numerics");

        int i = 'b' - 'a';
        System.out.println("b - a = " + i);
        System.out.println("because b = 62 and a = 61");

        System.out.println("--------------------------");
        System.out.println("Num jewels available: " + easyProblems.numJewelsInStones("aA", "aaAAAbbbbBcDDE"));
        System.out.println("--------------------------");
        System.out.println("Is 9 a perfect square? " + (easyProblems.isPerfectSquare(9) ? "Yes!" : "No!"));
        System.out.println("--------------------------");
        String[] a = {"test.email+alex@leetcode.com","test.e.mail+bob.cathy@leetcode.com","testemail+david@lee.tcode.com"};
        System.out.println("# unique emails: " + easyProblems.numUniqueEmails(a));

        int[][] array = {{0,1},{0,2},{4,6},{8,10},{1,9},{1,5},{5,9},{7,10},{11,17}};
        System.out.println(array.length);
        System.out.println("Min # of clips needed: " + easyProblems.videoStitching(array, 10));

        System.out.println("--------------------------");
        System.out.println("Let's flatten an array, play with lambdas and method references, and use a stream Supplier...");

        IntStream stream = Arrays.stream(array).flatMapToInt(Arrays::stream);
        Supplier<Stream<String>> streamSupplier = () -> Stream.of("A", "B", "C", "D");
        stream.forEach(System.out::print);
        streamSupplier.get().forEach(System.out::print);

        // Sort array via lambda
        Arrays.sort(array, Comparator.comparingInt(c -> easyProblems.applyAsInt(c)));
        // Sort same array via method reference
        Arrays.sort(array, Comparator.comparingInt(easyProblems::applyAsInt));

        System.out.println("--------------------------");
        System.out.println("Let's calculate distance from a cell in a matrix and return the order in a 2d array");

        int[][] matrixDistanceOrder = easyProblems.allCellsDistOrder(2, 2, 0, 1);
        System.out.println("Order of cells is: " + Arrays.deepToString(matrixDistanceOrder));

    }
}
