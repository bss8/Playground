package leetcode;

import java.math.BigInteger;

public class Medium {


    ListNode node1;
    ListNode node2;

    Medium() {
        this.node1 = new ListNode(3);
        this.node1.next = new ListNode(2);
        this.node1.next.next = new ListNode(1);
        this.node1.next.next.next = null;
        this.node2 = new ListNode(1);
        this.node2.next = new ListNode(2);
        this.node2.next.next = new ListNode(3);
        this.node2.next.next.next = null;
    }

    public static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    public static void main(String...args) {
        Medium medium = new Medium();
        medium.addTwoNumbers(medium.node1, medium.node2);

        ListNode head       = new ListNode(1);
        ListNode nodeTwo    = new ListNode(2);
        ListNode nodeThree  = new ListNode(3);
        ListNode nodeFour   = new ListNode(4);
        head.next = nodeTwo;
        nodeTwo.next = nodeThree;
        nodeThree.next = nodeFour;
        nodeFour.next = null;

        medium.swapPairs(head);
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        StringBuilder num1 = new StringBuilder();
        StringBuilder num2 = new StringBuilder();

        while (l1 != null) {
            num1.append(l1.val);
            l1 = l1.next;
        }
        while (l2 != null) {
            num2.append(l2.val);
            l2 = l2.next;
        }

        BigInteger a = new BigInteger(num1.reverse().toString()) ;
        BigInteger b = new BigInteger(num2.reverse().toString()) ;

        BigInteger summation = a.add(b);
        String str_sum = "" + summation;
        char[] arr = str_sum.toCharArray();
        ListNode sum = new ListNode(0);
        ListNode firstNode = sum;
        for (int i = arr.length - 1; i >= 0; i--) {
            sum.next = new ListNode(Character.getNumericValue(arr[i]));
            sum = sum.next;
        }
        sum = firstNode.next;
        System.out.println(sum.val + "|" + sum.next.val + "|" + sum.next.next.val);

        return sum;
    }

    /**
     * https://leetcode.com/problems/swap-nodes-in-pairs/
     * @param head
     * @return
     */
    public ListNode swapPairs(ListNode head) {
        // single or empty list edge case
        if (head.next == null || head == null)
            return head;

        ListNode dummyHead = new ListNode();
        ListNode curr = head;
        ListNode prev = dummyHead;


        while (curr != null && curr.next != null) {
            prev.next = curr.next;
            curr.next = curr.next.next;
            prev.next.next = curr;
            curr = curr.next;
            prev = prev.next.next;
        }

        return dummyHead.next;
    }
}
