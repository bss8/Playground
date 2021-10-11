package codepath;

public class WeekFour {
    public static class Node {
        Node next;
        int data;

        Node(int data) {
            this.next = null;
            this.data = data;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder(50);
            sb.append("[ ");
            sb.append(this.data);
            sb.append(" ]");
            Node tmpNext = this.next;

            while (next != null) {
                sb.append(" -> ");
                sb.append("[ ");
                sb.append(next.data);
                sb.append(" ]");
                next = next.next;
            }
            sb.append(" -> |||"); // null terminal symbol
            next = tmpNext; // need to restore next ptr after mutating
            return sb.toString();
        }

        public boolean equals(Node n) {
            if (data != n.data)
                return false;

            if (next == null)
                return n.next == null;

            return next.equals(n.next);
        }
    }


    public static void main(String... args) {
        System.out.println("--- Add two numbers as Lists ---");
        System.out.println("--- 12,342 + 465 = 12,807; List prints from least significant digit ---");
        // 12,342
        Node nOne = new Node(2);
        Node nTwo = new Node(4);
        Node nThree = new Node(3);
        Node nSeven = new Node(2);
        Node nEight = new Node(1);

        nOne.next = nTwo;       // 2 -> 4
        nTwo.next = nThree;     // 2 -> 4 -> 3
        nThree.next = nSeven;   // 2 -> 4 -> 3 -> 2
        nSeven.next = nEight;   // 2 -> 4 -> 3 -> 2 -> 1

        // 465
        Node nFour = new Node(5);
        Node nFive = new Node(6);
        Node nSix = new Node(4);

        nFour.next = nFive; // 5 -> 6
        nFive.next = nSix;  // 5 -> 6 -> 4

        // 12,342 + 465 = 12,807
        // return: 7 -> 0 -> 8 -> 2 -> 1
        Node sumResult = addTwoLists(nOne, nFour);
        System.out.println(sumResult.toString());

        System.out.println("\n--- Find the length of a List ---");
        Node n0 = new Node(0);
        System.out.println("Test 1 passed: " + (getLength(n0) == 1));

        Node n1 = new Node(1);
        n1.next = new Node(2);
        System.out.println("Test 2 passed: " + (getLength(n1) == 2));


        System.out.println("\n--- Is a given List a palindrome?  ---");
        Node n1_1 = new Node(1);
        System.out.println("Test case 1 passed: " + isPalindrome(n1_1));

        Node n2_1 = new Node(1);
        n2_1.next = new Node(2);
        System.out.println("test case 2 passed: " + !isPalindrome(n2_1));

        Node n3_1 = new Node(1);
        Node n3_2 = new Node(2);
        Node n3_3 = new Node(3);
        n3_1.next = n3_2;
        n3_2.next = n3_3;
        System.out.println("test case 3 passed: " + !isPalindrome(n3_1));

        Node n4_1 = new Node(1);
        Node n4_2 = new Node(2);
        Node n4_3 = new Node(1);
        n4_1.next = n4_2;
        n4_2.next = n4_3;
        System.out.println("test case 4 passed: " + isPalindrome(n4_1));


        System.out.println("\n--- Remove duplicates from a List ---");
        Node n1_1a = new Node(1);
        System.out.println("Test case 1 passed: " + removeDuplicates(n1_1a).equals(n1_1a));

        Node n2_1a = new Node(1);
        n2_1a.next = new Node(1);

        Node n2_answer = new Node(1);
        System.out.println(removeDuplicates(n2_1a).toString());
        System.out.println("Test case 2 passed: " + removeDuplicates(n2_1a).equals(n2_answer));

        Node n3_1a = new Node(1);
        Node n3_1b = new Node(1);
        Node n3_2a = new Node(2);
        Node n3_2b = new Node(2);
        n3_1a.next = n3_1b;
        n3_1b.next = n3_2a;
        n3_2a.next = n3_2b;

        Node n3_answer1 = new Node(1);
        n3_answer1.next = new Node(2);

        System.out.println("Test case 3 passed: " + removeDuplicates(n3_1a).equals(n3_answer1));
    }

    /**
     * Swap each pair of adjacent nodes.
     * Ex Input: 1 -> 2 -> 3 -> 4
     * Output: 2 -> 1 -> 4 -> 3
     *
     * https://leetcode.com/problems/swap-nodes-in-pairs/
     *
     * @param head the start of a singly linked list
     * @return the head of the new linked list after swapping
     */
    public static Node swapInPairs(Node head) {
        if (head == null || head.next == null)
            return head;

        Node newHead = head.next;
        Node previous = head;

        while (head != null && head.next != null) {
            Node tmp = head.next.next;

            previous.next = head.next;
            head.next.next = head;
            head.next = tmp;

            previous = head;
            head = tmp;
        }

        return newHead;
    }

    /**
     * Given a sorted linked list, delete all duplicates such that each element appear only once.
     * <p>
     * Input : 1 ; Output : 1
     * Input : 1->1 ; Output: 1
     * Input : 1->1->1->2->2 ; Output: 1->2
     */
    public static Node removeDuplicates(Node head) {
        if (head == null || head.next == null) {
            return head;
        }

        Node curNode = head;

        while (curNode != null) {
            if (curNode.data == curNode.next.data) {
                curNode.next = curNode.next.next;
            }

            curNode = curNode.next;
        }

        return head;
    }

    /**
     * Given a singly linked list, determine if it is a palindrome.
     * Assume a single node is always a palindrome.
     * We are interested in the node as a palindrome, not the number.
     * So a list with node {23} is a palindrome while the number 23
     * itself is not a palindrome.
     * <p>
     * Example 1:
     * Input: 1->2
     * Output: false
     * <p>
     * Example 2:
     * Input: 1->2->2->1
     * Output: true
     **/
    public static boolean isPalindrome(Node node) {
        int fullLength = getLength(node);

        // if list length is odd, it cannot be a palindrome
        if (fullLength == 1)
            return true;

        int halfLength = fullLength >> 1;

        int[] firstHalf = new int[halfLength];
        int currentNode = 0;

        // populate array with values for first half of list
        while (currentNode < halfLength) {
            firstHalf[currentNode] = node.data;
            node = node.next;
            currentNode++;
        }

        // if a list has odd length, the middle element can be anything
        if (fullLength % 2 != 0) node = node.next;

        // node now points to the first element of the second half of the list
        // we can compare it with the end of the array, working back to the beginning
        while (currentNode > 0) {
            if (node.data != firstHalf[currentNode - 1])
                return false;

            node = node.next;
            currentNode--;
        }

        return currentNode == 0;
    }

    /**
     * Given a node, return the length of the linked list
     * <p>
     * Input: 1 ; Return: 1
     * Input 1->2->3 ; Return 3
     */
    public static int getLength(Node node) {
        int length = 0;
        while (node != null) {
            length++;
            node = node.next;
        }

        return length;
    }

    /**
     * Input: 2 -> 4 -> 3,  5 -> 6 -> 4
     * Output: 7 -> 0 -> 8
     * <p>
     * Explanation: 342 + 465 = 807
     *
     * @param headOne start of first list
     * @param headTwo start of second list
     * @return start of resulting list, containing the sum
     */
    public static Node addTwoLists(Node headOne, Node headTwo) {
        Node temp = new Node(-1);
        Node tmpPtr = temp;

        if (headOne == null)
            return headTwo;
        if (headTwo == null)
            return headOne;

        int sum = 0;
        int carryover = 0;

        while (headOne != null
                || headTwo != null) {

            int first = (headOne != null) ? headOne.data : 0;
            int second = (headTwo != null) ? headTwo.data : 0;
            sum = first + second + carryover;

            if (sum >= 10) {
                carryover = 1;
                sum = sum % 10;
            } else {
                carryover = 0;
            }

            temp.next = new Node(sum);
            temp = temp.next;

            if (headOne != null)
                headOne = headOne.next;
            if (headTwo != null)
                headTwo = headTwo.next;
        }

        if (carryover > 0)
            temp.next = new Node(carryover);

        return tmpPtr.next;
    }
}
