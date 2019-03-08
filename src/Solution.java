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

    //Leetcode 9. Palindrome Number
    //too easy algorithm
    public boolean isPalindrome(int x) {
        String xStr = Integer.toString(x);
        for(int i = 0; i < xStr.length()/2 + 1; i++){
            if(xStr.charAt(i) != xStr.charAt(xStr.length() - i -1)){
                return false;
            }
        }
        return true;
    }

    //Leetcode 10. Regular Expression Matching
    //This must be a hard one, but I will try
    //Dynamic -programming skills, I forgot, but now I remember.
    //Coped from the discussion board, but I forget who is the writer
    public boolean isMatch(String s, String p){
        if(p == null || p.length()==0) return (s==null|| s.length()==0);
        boolean dp[][] = new boolean[s.length()+1][p.length()+1];
        dp[0][0] = true;
        for(int j=2; j<p.length();j++){
            dp[0][j] = p.charAt(j-1)=='*'&& dp[0][j-2];
        }
        for(int j=1; j<=p.length();j++){
            for(int i=1; i<=s.length();i++){
                if(p.charAt(j-1)==s.charAt(i-1) || p.charAt(j-1)=='.')
                    dp[i][j] = dp[i-1][j-1];
                else if(p.charAt(j-1)=='*')
                    dp[i][j]= dp[i][j-2] || ((s.charAt(i-1)==p.charAt(j-2)|| p.charAt(j-2)=='.') && dp[i-1][j]);
                else continue;
            }
        }
        return dp[s.length()][p.length()];
    }

    //Leetcode 11.  Container With Most Water
    //The answer is so simple but the performance is bad.
    public int maxArea(int[] height) {
        int maxArea = 0;
        for(int i=0; i<height.length-1; i++){
            for(int j=i+1; j<height.length; j++){
                if((j-i)*Math.min(height[i],height[j])>maxArea){
                    maxArea = (j-i)*Math.min(height[i],height[j]);
                }
            }
        }
        return maxArea;
    }
    //Leetcode 11 Another algorithm from discussion board
    //performs really well
    public int maxArea2(int[] height) {
        int start = 0;
        int end = height.length - 1;
        int volume = 0;
        while(start < end) {
            if (height[start] > height[end]) {
                volume = Math.max(volume, height[end]*(end-start));
                end = end - 1;
            } else {
                volume = Math.max(volume, height[start]*(end-start));
                start = start + 1;
            }
        }
        return volume;
    }

    //Leetcode 12.Integer to Roman
    //very nice method from discussion board. Exactly the same as my method
    //but it uses arrays to make the code neater.
    //Also he uses string buffer, which improves the performance significantly
    public static String intToRoman(int num) {
        if (num <= 0)
            return null;
        int[] a = { 1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1 };
        String[] s = { "M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I" };
        StringBuffer result = new StringBuffer();
        for (int i = 0; i < a.length; i++) {
            int RomanNumber = a[i];
            while (num >= RomanNumber) {
                num -= RomanNumber;
                result.append(s[i]);
                if (num == 0)
                    return result.toString();
            }
        }
        return null;
    }

    //Leetcode 13. Roman to Integer
    public static int romanToInt(String s) {
        if (s == null || s.length() == 0)
            return -1;
        HashMap<Character, Integer> map = new HashMap<Character, Integer>();
        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);
        int len = s.length(), result = map.get(s.charAt(len - 1));
        for (int i = len - 2; i >= 0; i--) {
            if (map.get(s.charAt(i)) >= map.get(s.charAt(i + 1)))
                result += map.get(s.charAt(i));
            else
                result -= map.get(s.charAt(i));
        }
        return result;
    }

    //Leetcode 14. longest Common Refix
    //A very ugly method
    public String longestCommonPrefix(String[] strs) {
        if(strs.length==0) return "";
        int k = 1;
        if(strs[0].equals("")) return "";
        String temp = strs[0].substring(0,1);
        try{
            while(true){
                for(int i=0; i<strs.length;i++){
                    if(!strs[i].substring(0,k).equals(temp)) return strs[0].substring(0,k-1);
                }
                k++;
                temp = strs[0].substring(0,k);
            }
        }catch(Exception e){
            return strs[0].substring(0,k-1);
        }
    }
    //Leetcode 14. longest Common Refix
    //Another very ugly method, but the performance is good.
    //The memory usage is very poor
    public String longestCommonPrefix2(String[] strs) {
        if (strs == null || strs.length == 0) return "";
        return longestCommonPrefix(strs, 0);
    }
    private String longestCommonPrefix(String[] strs, int index) {
        if (index >= strs[0].length()) return "";
        String strAtIndex = strs[0].substring(index, index + 1);
        for (int i = 1; i < strs.length; i++)
            if (strs[i].length() <= index || !strs[i].substring(index, index + 1).equals(strAtIndex))
                return "";
        return strs[0].substring(index, index + 1) + longestCommonPrefix(strs, index + 1);
    }








}
