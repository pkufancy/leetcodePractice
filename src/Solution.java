import java.util.HashMap;

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
    // better solution from discussion board
    public int lengthOfLongestSubstring(String s) {
        if (s.length()==0) return 0;
        HashMap<Character, Integer> map = new HashMap<Character, Integer>();
        int max=0;
        for (int i=0, j=0; i<s.length(); ++i){
            if (map.containsKey(s.charAt(i))){
                j = Math.max(j,map.get(s.charAt(i))+1);
            }
            map.put(s.charAt(i),i);
            max = Math.max(max,i-j+1);
        }
        return max;
    }

    //Leetcode 4. Median of Two sorted Arrays
    //A very elegant solution from discussion board
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m=nums1.length;
        int n=nums2.length;
        int k=(m+n)/2;
        int i=0, j=k;
        int lo=0, hi=Math.min(k,m);

        while (true) {
            i=(hi+lo)/2;
            j=k-i;
            if (get(nums1,i)>=get(nums2,j-1)) {
                if (get(nums2,j)>=get(nums1,i-1)) break;
                else hi=i-1;
            }
            else lo=i+1;
        }
        if ((m+n)%2==1) return Math.min(get(nums1,i),get(nums2,j)); //odd
        return (double)(Math.min(get(nums1,i),get(nums2,j))+Math.max(get(nums1,i-1),get(nums2,j-1)))/2;//even
    }
    private int get(int[] nums, int i) {
        if (i<0) return Integer.MIN_VALUE;
        if (i>=nums.length) return Integer.MAX_VALUE;
        return nums[i];
    }

}
