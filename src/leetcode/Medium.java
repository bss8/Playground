package leetcode;

import java.math.BigInteger;
import java.util.Arrays;

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

        System.out.println("----Max Subarray----");
        int[] arr = new int[] {5,4,-1,7,8};
        System.out.println(medium.maxSubArray(arr));
        System.out.println(medium.maxSubArrayDp(arr));

        int[] nums = new int[] {2,0,2,1,1,0};
        medium.quicksort(nums, 0, nums.length-1);
        System.out.println(Arrays.toString(nums));
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

    /**
     * Regular approach
     * @param nums
     * @return
     */
    public int maxSubArrayReg(int[] nums) {
        int sum=0,max=nums[0];

        for (int num : nums) {
            sum += num;
            if (sum > max)
                max = sum;
            if (sum < 0)
                sum = 0;
        }

        return max;
    }

    /**
     * Max subarray using DP
     * @param nums
     * @return
     */
    public int maxSubArrayDp(int[] nums) {
        //dp[i] means the maximum subarray ending with A[i];
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        int max = dp[0];

        for(int i = 1; i < nums.length; i++){
            dp[i] = nums[i] + (Math.max(dp[i - 1], 0));
            max = Math.max(max, dp[i]);
        }

        return max;
    }

    /**
     * Using divide-and-conquer
     * @param nums
     * @return
     */
    public int maxSubArray(int[] nums) {
        int[] res = findMaxSub(nums, 0, nums.length-1);
        return res[2]; // sum of the max subarray
    }

    public int[] findMaxSub(int[] nums, int low, int high) {
        if (high == low)
            return new int[] {low, high, nums[low]};
        else {
            int mid = (low + high) / 2;
            int[] left = findMaxSub(nums, low, mid);
            int[] right = findMaxSub(nums, mid+1, high);
            int[] cross = findMaxCross(nums, low, mid, high);

            if (left[2] >= right[2] &&
                    left[2] >= cross[2])
                return left;
            else if (right[2] >= left[2] &&
                    right[2] >= cross[2])
                return right;
            else
                return cross;
        }
    }

    public int[] findMaxCross(int[] nums, int low,
                              int mid, int high) {
        int leftSum = Integer.MIN_VALUE;
        int sum = 0;
        int maxLeft = 0, maxRight = 0;
        for (int i = mid; i >= low; i--) {
            sum = sum + nums[i];
            if (sum > leftSum) {
                leftSum = sum;
                maxLeft = i;
                System.out.println("left sum " + leftSum);

            }
        }

        int rightSum = Integer.MIN_VALUE;
        sum = 0;
        for (int j = mid+1; j <= high; j++) {
            sum = sum + nums[j];
            if (sum > rightSum) {
                rightSum = sum;
                maxRight = j;
                System.out.println("right sum " + rightSum);

            }
        }
        System.out.println("FIN right sum " + rightSum);
        System.out.println("FIN left sum " + leftSum);
        return new int[] {maxLeft, maxRight, leftSum + rightSum};
    }


    private void quicksort(int[] nums, int p, int r) {
        if (p < r) {
            int q = partition(nums, p, r);
            quicksort(nums, p, q-1);
            quicksort(nums, q+1, r);
        }
    }

    private int partition(int[] nums, int p, int r) {
        int pivot = nums[r];
        int i = p - 1;
        for (int j = p; j < r; j++) {
            if (nums[j] <= pivot) {
                i++;
                swap(nums, i, j);
            }
        }
        swap(nums, i+1, r);
        return i+1;
    }

    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}
