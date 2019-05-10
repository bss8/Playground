import javax.xml.bind.DatatypeConverter;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.util.*;

/**
 *
 */
class Easy {

    boolean flag;
    int target;

    class Coordinates {
        int x;
        int y;

        int getDist() {
            return dist;
        }

        int dist;

        Coordinates(int _x, int _y, int _dist) {
            x = _x;
            y = _y;
            dist = _dist;
        }


    }

    /**
     * https://leetcode.com/problems/matrix-cells-in-distance-order/
     *
     * @param R
     * @param C
     * @param r0
     * @param c0
     * @return
     */
    public int[][] allCellsDistOrder(int R, int C, int r0, int c0) {
        int[][] result = new int[(R*C)][2];

        for(int[] c:result)
            Arrays.fill(c, 0);

        System.out.println("array: " + Arrays.deepToString(result));
        LinkedList<Coordinates> myList = new LinkedList<>();

        for (int row = 0; row < R; row++) {
            for (int col = 0; col < C; col++) {
                int dist = Math.abs(row - r0) + Math.abs(col - c0);
                System.out.println("dist: " + dist);
                myList.add(new Coordinates(row, col, dist));
            }
        }
        /* Example of deprecated way that can be replaced by the first uncommented line that follows
         * myList.sort((Coordinates c1, Coordinates c2) -> Integer.compare(c1.getDist(), c2.getDist()));
         * Here is another way with a lambda instead of a method:
         * myList.sort(Comparator.comparingInt((Coordinates c) -> c.dist)); */
        myList.sort(Comparator.comparingInt(Coordinates::getDist));

        for (int i = 0; i < result.length; i++) {
            Coordinates c = myList.pop();
            result[i][0] = c.x;
            result[i][1] = c.y;
        }

        return result;
    }

    public int[][] altAllCellsDistOrder(int R, int C, int r0, int c0) {
        int[][] matrix = new int[R * C][2];
        int k = 0;
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                matrix[k][0] = i;
                matrix[k][1] = j;
                k++;
            }
        }

        Arrays.sort(matrix, (a, b) -> {
            int distA = Math.abs(a[0] - r0) + Math.abs(a[1] - c0);
            int distB = Math.abs(b[0] - r0) + Math.abs(b[1] - c0);
            return distA - distB;
        });
        return matrix;
    }


    /**
     * Populates a simple tree and returns the root node
     * @return TreeNode root
     */
    TreeNode createTree() {
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

    void inOrderTraversal(TreeNode node) {

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
    int videoStitching(int[][] clips, int T) {

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
    int numJewelsInStones(String J, String S) {
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
    int numUniqueEmails(String[] emails) {
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
    boolean isPerfectSquare(int num) {

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
    double sqrt(int number) {
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
    int findTreeBranch(TreeNode node, int sum) {
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
    int[][] flipAndInvertImage(int[][] A) {
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
    void oddOrEven(int value) {

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
    String toLowerCase (String str) {

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
    int applyAsInt(int[] c) {
        return c[0] - c[1];
    }
}
