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


    //Leetcode 5. Longest Palindromic Substring
    //This solution is ugly and inefficient
    public String longestPalindrome(String s) {
        if(s.equals("")){return "";}
        String result = s.substring(0,1);
        int max = 1;
        for(int i = 0; i<s.length();i++){
            for(int j = i+max; j<s.length();j++){
                if(isPalindrome(s.substring(i,j+1))){
                    if(s.substring(i,j+1).length()>max){
                        max = j-i;
                        result = s.substring(i,j+1);
                    }
                }
            }
        }
        return result;
    }
    public boolean isPalindrome(String s){
        char[] sChar = s.toCharArray();
        int len = sChar.length;
        for(int i = 0; i < len/2; i++){
            if(sChar[i]!=sChar[len-i-1]){
                return false;
            }
        }
        return true;
    }
    //Leetcode 5. Another answer
    //it performs better, but I still want something better
    //Looking forward to your ideas
    public String longestPalindrome2(String s) {
        if(s.equals("")){return "";}
        char[] ca = s.toCharArray();
        int rs = 0, re = 0;
        int max = 0;
        for(int i = 0; i < ca.length; i++) {
            if(isPalindrome(ca, i - max - 1, i)) {
                rs = i - max - 1; re = i;
                max += 2;
            } else if(isPalindrome(ca, i - max, i)) {
                rs = i - max; re = i;
                max += 1;
            }
        }
        return s.substring(rs, re + 1);
    }
    private boolean isPalindrome(char[] ca, int s, int e) {
        if(s < 0) return false;

        while(s < e) {
            if(ca[s++] != ca[e--]) return false;
        }
        return true;
    }

    //Leetcode 6.ZigZag Conversion
    //I actually love this method, but dealing with borders is not satisfying
    public String convert(String s, int numRows) {
        String[] rows = new String[numRows];
        if(s.length()<=2 || numRows ==1) return s;
        for(int i = 0; i < rows.length; i++){
            rows[i] = "";
        }
        for(int i = 0; i<s.length(); i++){
            if((i)%(2*numRows-2) <= numRows-1){
                rows[(i)%(2*numRows-2)] += s.charAt(i);
            }else{
                rows[numRows-1-(((i)%(2*numRows-2)+1)%numRows)] += s.charAt(i);
            }
        }
        String result = "";
        for(int i = 0; i < rows.length; i++){
            result += rows[i];
        }
        result.replace(null,"");
        return result;
    }

    //Leetcode 7. Reverse Integer
    //the performance is very good, it seems to be a good algorithm, but can be utilized
    //to have better space performance
    public int reverse(int x) {
        boolean positive = x>=0;
        String resultStr = "";
        int result = 0;
        if(!positive) x = 0 - x;
        String tempx = Integer.toString(x);
        for(int i = tempx.length()-1; i>=0; i--){
            resultStr += tempx.charAt(i);
        }
        try{
            result = Integer.parseInt(resultStr);
        }catch (Exception e){
            return 0;
        }

        return positive?result:(0-result);
    }

    //Leetcode 8. String to Integer (atoi)
    //The method is very ugly using long to store int, but I have no other ways to do that
    public int myAtoi(String str) {
        char[] arr = str.toCharArray();
        long out = 0;
        int i = 0;
        boolean minus = false;
        while(i < arr.length && (arr[i] == ' ')){
            i++;
        }
        if (i >= arr.length){
            return 0;
        }
        if (arr[i] == '-'){
            minus = true;
            i++;
        }else if (arr[i] == '+'){
            i++;
        }
        while (i < arr.length && arr[i] == '0'){
            i++;
        }
        int j = i;
        for (; j < Math.min(i + 11, arr.length); j++){
            if ((arr[j] >= '0' && arr[j] <= '9')){
                out = out*10 + arr[j] - '0';
            } else {
                break;
            }
        }
        if (minus){
            out = 0 - out;
        }
        if (out > Integer.MAX_VALUE){
            return Integer.MAX_VALUE;
        }
        if (out < Integer.MIN_VALUE){
            return Integer.MIN_VALUE;
        }
        return (int) out;
    }




}
