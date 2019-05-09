import javax.xml.bind.DatatypeConverter;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.function.Supplier;
import java.util.stream.IntStream;
import java.util.stream.Stream;


public class Main {

    private static int n;
    private static boolean flag;
    private static int target;


    public static void main(String[] args) {

        System.out.println("Welcome to the playground!");
        System.out.println("--------------------------");

        System.out.println("Let's convert capital letters to lowercase.");
        String changeToLower = "HELLO";
        System.out.println("Changed from" + changeToLower);
        changeToLower = toLowerCase(changeToLower);
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
        oddOrEven(value);

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
        System.out.println("And here is the flipped and inverted one: " + Arrays.deepToString(flipAndInvertImage(A)));

        System.out.println("--------------------------");
        System.out.println("Let's create a simple tree.");

        TreeNode root = createTree();
        System.out.println("Our root value is " + root.val);
        flag = false;
        target = 22;
        System.out.print("In-order traversal: ");
        inOrderTraversal(root);

        findTreeBranch(root, 0);
        System.out.println("\nCan we find a viable path to a leaf in our tree for given sum of" + target +": " + flag);

        System.out.println("--------------------------");

        // ***********************************************************************************************************
        // Chars are treated as numerics, we can perform arithmetic operations
        // ***********************************************************************************************************
        System.out.println("We can treat chars as numerics");

        int i = 'b' - 'a';
        System.out.println("b - a = " + i);
        System.out.println("because b = 62 and a = 61");


        n = 81;

        System.out.println("--------------------------");
        System.out.println("Num jewels available: " + numJewelsInStones("aA", "aaAAAbbbbBcDDE"));
        System.out.println("--------------------------");
        System.out.println("Is 9 a perfect square? " + (isPerfectSquare(9) ? "Yes!" : "No!"));
        System.out.println("--------------------------");
        String[] a = {"test.email+alex@leetcode.com","test.e.mail+bob.cathy@leetcode.com","testemail+david@lee.tcode.com"};
        System.out.println("# unique emails: " + numUniqueEmails(a));

        int[][] array = {{0,1},{0,2},{4,6},{8,10},{1,9},{1,5},{5,9},{7,10},{11,17}};
        System.out.println(array.length);
        System.out.println("Min # of clips needed: " + videoStitching(array, 10));

        System.out.println("--------------------------");
        System.out.println("Let's flatten an array, play with lambdas and method references, and use a stream Supplier...");

        IntStream stream = Arrays.stream(array).flatMapToInt(Arrays::stream);
        Supplier<Stream<String>> streamSupplier = () -> Stream.of("A", "B", "C", "D");
        stream.forEach(System.out::print);
        streamSupplier.get().forEach(System.out::print);

        // Sort array via lambda
        Arrays.sort(array, Comparator.comparingInt(c -> applyAsInt(c)));
        // Sort same array via method reference
        Arrays.sort(array, Comparator.comparingInt(Main::applyAsInt));

    }

    private static TreeNode createTree() {
        TreeNode root = new TreeNode(5);
        TreeNode four = new TreeNode(4);
        TreeNode eight = new TreeNode( 8);
        TreeNode eleven = new TreeNode(11);
        TreeNode thirteen = new TreeNode(13);
        TreeNode fourAgain = new TreeNode( 4);
        TreeNode seven = new TreeNode(7);
        TreeNode two = new TreeNode(2);
        TreeNode one = new TreeNode( 1);
        root.left = four;
        root.right = eight;
        four.left = eleven;
        four.right = null;
        eight.left = thirteen;
        eight.right = fourAgain;
        eleven.left = seven;
        eleven.right = two;
        thirteen.left = null;
        thirteen.right = null;
        fourAgain.left = null;
        fourAgain.right = one;
        seven.left = null;
        seven.right = null;
        two.left = null;
        two.right = null;
        one.left = null;
        one.right = null;
        return root;
    }

    private static void inOrderTraversal(TreeNode node) {

        if (node == null)
            return;

        inOrderTraversal(node.left);
        System.out.print(node.val + ",");
        inOrderTraversal(node.right);
    }

    /**
     * In progress
     * @param clips
     * @param T
     * @return
     */
    private static int videoStitching(int[][] clips, int T) {

        Arrays.sort(clips, Comparator.comparingInt(a -> a[1]));

        System.out.println(Arrays.deepToString(clips));
        int min = clips[0][0];
        // cannot assume last clip in sorted array is what we need
        int minLast = clips[clips.length - 1][0];
        int maxLast = clips[clips.length - 1][1];

        int maxStart = 0;

        for (int[] clip : clips) {
            //find first clip
            if (clip[0] == min && clip[1] > maxStart) {
                maxStart = clip[1];
            }
            //find last clip
            if (clip[1] >= T && clip[0] < minLast) {
                minLast = clip[0];
                maxLast = clip[1];
            }
        }

        System.out.println(minLast + " " + maxLast);
        return 0;
    }

    /**
     * https://leetcode.com/problems/jewels-and-stones/
     * String indexOf() : This method returns the index within this string of the first occurrence of the
     * specified character or -1, if the character does not occur.
     * @param J
     * @param S
     * @return
     */
    private static int numJewelsInStones(String J, String S) {
        if (J.length()==0 || S.length()==0)
            return 0;

        int result = 0;

        for (char c : S.toCharArray()) {
            if (J.indexOf(c)!=-1) {
                result++;
            }
        }

        return result;
    }

    /**
     * https://leetcode.com/problems/unique-email-addresses/
     * @param emails String[] of emails
     * @return
     */
    private static int numUniqueEmails(String[] emails) {
        int i = 0;

        for (String email : emails) {
            //String[] tmp = email.split("@");
            //emails[i] = tmp[0].replaceAll("(\\.)|(\\+.*)", "") + "@" + tmp[1];
            emails[i] = email.substring(0,email.indexOf("@")).replaceAll("(\\.)|(\\+.*)", "") + email.substring(email.indexOf("@"));
            i++;
        }

        Set<String> uniqueSet = new HashSet<>(Arrays.asList(emails));

        return uniqueSet.size();
    }

    /**
     * https://leetcode.com/problems/valid-perfect-square/
     * @param num is an integer
     * @return whether num is a perfect square or not
     */
    private static boolean isPerfectSquare(int num) {

        double sr = sqrt(num);

        // If square root is an integer
        return ((sr - Math.floor(sr)) == 0);
    }


    /**
     * https://www.programcreek.com/2012/02/java-calculate-square-root-without-using-library-method/
     * https://blogs.sas.com/content/iml/2016/05/16/babylonian-square-roots.html
     * @param number
     * @return the square root value
     */
    private static double sqrt(int number) {
        double t;

        double squareRoot = number / 2;

        do {
            t = squareRoot;
            squareRoot = (t + (number / t)) / 2;
        } while ((t - squareRoot) != 0);

        return squareRoot;
    }

    /**
     * https://leetcode.com/problems/path-sum/
     * @param node initially pass in root, then recursively pass in node.left then node.right
     * @param sum
     * @return
     */
    private static int findTreeBranch(TreeNode node, int sum) {
        if (node == null) return 0;

        sum += node.val;

        if (node.left == null && node.right == null) {
            if (sum == target) {
                flag = true;
            }
        }

        return findTreeBranch(node.left, sum) + findTreeBranch(node.right, sum);
    }

    /**
     * https://leetcode.com/problems/flipping-an-image/
     * @param A
     * @return
     */
    private static int[][] flipAndInvertImage(int[][] A) {
        for (int i = 0; i < A.length; i++) {
            int left = 0;
            int right = A[i].length - 1;

            while (left <= right) {
                int tmp = A[i][left];
                A[i][left] = A[i][right] == 0 ? 1 : 0;
                A[i][right] = tmp == 0 ? 1 : 0;

                left++;
                right--;
            }
        }
        return A;
    }

    /**
      *  When would you not want to use a break?
      *  https://www.inf.unibz.it/~calvanese/teaching/05-06-ip/lecture-notes/uni05/node29.html
      *  One case may be listing out all polygons up to n sides
      */
    private static void oddOrEven(int value) {

        switch (value) {
            case 0:
                System.out.println("even");
                break;
            case 1:
                System.out.println("odd");
                break;
            default:
                System.out.println("neither");
                break;
        }
    }

    /**
     * https://leetcode.com/problems/to-lower-case/
     * @param str string with one or more capital letters to convert
     * @return all lower case
     */
    private static String toLowerCase (String str) {

        str = String.format("%x", new BigInteger(1, str.getBytes(StandardCharsets.UTF_8)));
        String[] strArray = str.split("(?<=\\G..)");
        System.out.println(Arrays.toString(strArray));

        for (int i = 0; i < strArray.length; i++) {
            String tmp = "0x" + strArray[i];
            int hexInt = Integer.decode(tmp);
            if (hexInt > 0x5A || hexInt < 0x41) {

            }
            else {
                strArray[i] = Integer.toHexString((hexInt + 0x20));
            }
        }

        str = String.join("", strArray);
        byte[] bytes = DatatypeConverter.parseHexBinary(str);
        str = new String(bytes, StandardCharsets.UTF_8);

        return str;
    }

    /**
     * Example of sorting array via method reference
     * @param c
     * @return
     */
    private static int applyAsInt(int[] c) {
        return c[0] - c[1];
    }
}
