public class Solution {

    //Leetcode 1. Two Sum
    public int[] twoSum(int[] nums, int target) {
        for(int i = 0; i<nums.length-1; i++){
            for(int j = i + 1; j < nums.length; j++){
                if(nums[i]+nums[j] == target){
                    return new int[]{i,j};
                }
            }
        }
        return new int[]{-1, -1};
    }

    //Leetcode 2. Add Two Numbers
    //This structure is interesting, I would like to learn more
     //Definition for singly-linked list.
     public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
     }
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int carry = 0;
        ListNode l = null, head = null, prev = null;
        while (l1 != null || l2 != null|| carry > 0) {
            int sum = ((l1 == null)? 0 : l1.val) + ((l2 == null)? 0 : l2.val) + carry;
            int val = sum % 10;
            carry =  sum / 10;
            l = new ListNode(val);
            if (head == null) {
                head = l;
            }
            if (prev != null) {
                prev.next = l;
            }
            l1 = (l1 != null)? l1.next : null;
            l2 = (l2 != null)? l2.next : null;
            prev = l;
        }
        return head;
    }

    //Leetcode 3.Longest Substring Without Repeating Characters
    // Still have problems, need to be revised
    public int lengthOfLongestSubstring(String s) {
        int max = 1;
        for(int i = 0; i < s.length()-max; i++){
            for(int j = i + 1; j < s.length(); j++){
                if(s.charAt(i)==s.charAt(j)){
                    i++;
                    if(j-i>max){ max = j-i;}
                }
                System.out.println(max);
                System.out.println(s.substring(i,j));
            }
        }
        return max;
    }

}
