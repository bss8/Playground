package codepath;

import java.util.HashSet;

public class WeekFive {


    public static void main(String...args) {
        Node nOne = new Node(2);
        Node nTwo = new Node(1);
        Node nThree = new Node(5);
        Node nFour = new Node (7);
        nOne.next = nTwo;
        nTwo.next = nThree;
        nThree.next = nFour;
        nTwo.prev = nOne;
        nThree.prev = nTwo;
        nFour.prev = nThree;

        int[] nums1 = {1,2,3,1};
        int[] nums2 = {2,3};
        int [] test = intersection(nums1, nums2);
        for (int i : test)
            System.out.println(i);

        nOne = reverseDoublyLinkedList(nOne);
        System.out.println(nOne.toString());
    }

    static int[] nextLargerNodes(Node head) {

        return new int[] {0};
    }

    /**
     * Finds only the unique intersection between two arrays, not the exact one (will not show duplicates)
     * @param nums1
     * @param nums2
     * @return
     */
    public static int[] intersection(int[] nums1, int[] nums2) {
        HashSet<Integer> set1 = new HashSet<Integer>();
        for (Integer n : nums1) set1.add(n);
        HashSet<Integer> set2 = new HashSet<Integer>();
        for (Integer n : nums2) set2.add(n);

        set1.retainAll(set2);

        int [] output = new int[set1.size()];
        int idx = 0;
        for (int s : set1) output[idx++] = s;
        return output;
    }

    public static Node reverseDoublyLinkedList(Node head) {
        if (head == null || head.next == null)
            return head;

        Node p = head.next;
        while (true) {
            head.next = head.prev;
            head.prev = p;
            if (p == null) return head;
            head = p;
            p = head.next;
        }
    }
}
