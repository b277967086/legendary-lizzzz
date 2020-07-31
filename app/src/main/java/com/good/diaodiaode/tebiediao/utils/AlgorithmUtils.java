package com.good.diaodiaode.tebiediao.utils;

import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ex-lizheng102 on 2017-03-01.
 */

public class AlgorithmUtils {

    public AlgorithmUtils() {
    }

    public static float getNumber(float health, float atk, float armor) {

        float damageReduction = (float) (0.06 * armor / (1 + 0.06 * armor));

        return health / ((1 - damageReduction) * atk);

    }

    /**
     * 最多有k个不同字符的最长子字符串
     * O(n²)
     */
    public static String getSubString(String str, int k) {

        int n = str.length();
        String subString = "";
        for (int i = 0; i < n; i++) {
            HashMap<Character, Integer> map = new HashMap<>();
            for (int j = i + 1; j < n; j++) {
                String subStr = str.substring(i, j);
                char charAt = str.charAt(j - 1);
                if (map.containsKey(charAt)) {
                    map.put(charAt, map.get(charAt) + 1);
                } else {
                    map.put(charAt, 1);
                }
                if (map.size() <= k) {
                    if (subStr.length() >= subString.length()) {
                        subString = subStr;
                    }
                } else {
                    break;
                }

            }
        }
        return subString;
    }

    /**
     * 最多有k个不同字符的最长子字符串
     * 标准版  确实时间复杂度小很多
     * O(n)
     */
    public static String getSubStrings(String str, int k) {

        int n = str.length();
        int j = 0;
        String subString = "";
        char charAt;
        HashMap<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            while (j < str.length()) {
                charAt = str.charAt(j);
                if (map.containsKey(charAt)) {
                    map.put(charAt, map.get(charAt) + 1);
                } else {
                    if (map.size() == k) break;
                    map.put(charAt, 1);
                }
                j++;
            }

            String subStr = str.substring(i, j);
            if (subString.length() < subStr.length()) {
                subString = subStr;
            }
            charAt = str.charAt(i);
            if (map.containsKey(charAt)) {
                int count = map.get(charAt);
                if (count > 1) {
                    map.put(charAt, count - 1);
                } else {
                    map.remove(charAt);
                }
            }
        }
        return subString;
    }


    public static int getfeiI(int i) {
        return ~i;
    }

    public static String longestPalindrome(String s) {
        String maxSubStr = "";
        int maxLength = 0;
        int[][] arr = new int[s.length()][s.length()];

        for (int i = 0; i < s.length(); i++) {
            for (int j = 0; j < s.length(); j++) {
                if (s.charAt(i) == s.charAt(s.length() - j - 1)) {
                    if (i > 0 && j > 0) {
                        arr[i][j] = arr[i - 1][j - 1] + 1;
                    } else {
                        arr[i][j] = 1;
                    }
                }

                if (arr[i][j] > maxLength && j - arr[i][j] + 1 == s.length() - i - 1) {
                    maxLength = arr[i][j];
                    maxSubStr = s.substring(i - maxLength + 1, i + 1);
                }
            }
        }
        return maxSubStr;
    }

    public String convert(String s, int numRows) {

        HashMap<Integer, StringBuilder> maps = new HashMap();

        for (int i = 0; i < s.length(); i++) {
            int raw;
            if ((raw = i % (2 * numRows - 2)) <= numRows - 1) {
            } else {
                raw = 2 * numRows - 2 - raw;
            }
            StringBuilder sb = maps.get(raw);
            if (sb == null) {
                sb = new StringBuilder();
                maps.put(raw, sb);
            }
            sb.append(s.charAt(i));
        }
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < numRows; i++) {
            builder.append(maps.get(i));
        }
        return builder.toString();
    }

    /**
     * int反转   321->123
     *
     * @param x
     * @return
     */
    public int reverse(int x) {

        long ans = 0;
        int temp = 0;

        while (x != 0) {
            temp = x % 10;

            ans = ans * 10 + temp;

            if (ans > Integer.MAX_VALUE || ans < Integer.MIN_VALUE) {
                return 0;
            }

            x = x / 10;
        }

        return (int) ans;
    }

    /**
     * 请你来实现一个 atoi 函数，使其能将字符串转换成整数。
     * <p>
     * 首先，该函数会根据需要丢弃无用的开头空格字符，直到寻找到第一个非空格的字符为止。接下来的转化规则如下：
     * <p>
     * 如果第一个非空字符为正或者负号时，则将该符号与之后面尽可能多的连续数字字符组合起来，形成一个有符号整数。
     * 假如第一个非空字符是数字，则直接将其与之后连续的数字字符组合起来，形成一个整数。
     * 该字符串在有效的整数部分之后也可能会存在多余的字符，那么这些字符可以被忽略，它们对函数不应该造成影响。
     * 注意：假如该字符串中的第一个非空格字符不是一个有效整数字符、字符串为空或字符串仅包含空白字符时，则你的函数不需要进行转换，即无法进行有效转换。
     * <p>
     * 在任何情况下，若函数不能进行有效的转换时，请返回 0 。
     * <p>
     * 提示：
     * <p>
     * 本题中的空白字符只包括空格字符 ' ' 。
     * 假设我们的环境只能存储 32 位大小的有符号整数，那么其数值范围为 [−231,  231 − 1]。如果数值超过这个范围，请返回  INT_MAX (231 − 1) 或 INT_MIN (−231) 。
     *  
     * <p>
     * 示例 1:
     * <p>
     * 输入: "42"
     * 输出: 42
     * 示例 2:
     * <p>
     * 输入: "   -42"
     * 输出: -42
     * 解释: 第一个非空白字符为 '-', 它是一个负号。
     *      我们尽可能将负号与后面所有连续出现的数字组合起来，最后得到 -42 。
     * 示例 3:
     * <p>
     * 输入: "4193 with words"
     * 输出: 4193
     * 解释: 转换截止于数字 '3' ，因为它的下一个字符不为数字。
     * 示例 4:
     * <p>
     * 输入: "words and 987"
     * 输出: 0
     * 解释: 第一个非空字符是 'w', 但它不是数字或正、负号。
     * 因此无法执行有效的转换。
     * 示例 5:
     * <p>
     * 输入: "-91283472332"
     * 输出: -2147483648
     * 解释: 数字 "-91283472332" 超过 32 位有符号整数范围。
     *      因此返回 INT_MIN (−231) 。
     */

    public static int myAtoi(String str) {

        if (str == null) {
            return 0;
        }
        str = str.trim();

        boolean b = false;
        int start = 0;
        while (start < str.length() && ((str.charAt(start) == '-') || str.charAt(start) == '+')) {
            b ^= str.charAt(start) == '-';
            start++;
        }

        if (start > 1) {
            return 0;
        }

        while (start < str.length() && (str.charAt(start) == '0')) {
            start++;
        }

        String ans = null;
        int end = start;
        while (end < str.length() && (str.charAt(end) <= '9' && str.charAt(end) >= '0')) {
            end++;
        }
        ans = str.substring(start, end);

        if (ans == null || ans.equals("") || ans.equals("-") || ans.equals("+")) {
            return 0;
        }

        if (ans.length() > 10) {
            return b ? Integer.MIN_VALUE : Integer.MAX_VALUE;
        }

        long l = Long.parseLong(ans);

        if (b) {
            l = -l;
        }

        if (l > Integer.MAX_VALUE) {
            return Integer.MAX_VALUE;
        }

        if (l < Integer.MIN_VALUE) {
            return Integer.MIN_VALUE;
        }
        return (int) l;
    }

    /**
     * 判断一个整数是否是回文数。回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。
     * <p>
     * 示例 1:
     * <p>
     * 输入: 121
     * 输出: true
     * 示例 2:
     * <p>
     * 输入: -121
     * 输出: false
     * 解释: 从左向右读, 为 -121 。 从右向左读, 为 121- 。因此它不是一个回文数。
     * 示例 3:
     * <p>
     * 输入: 10
     * 输出: false
     * 解释: 从右向左读, 为 01 。因此它不是一个回文数。
     * 进阶:
     * <p>
     * 你能不将整数转为字符串来解决这个问题吗？
     */

//    public boolean isPalindrome(int x) {
//        if(x<0){
//            return false;
//        }
//
//        if(x==0){
//            return true;
//        }
//        char[] chars = Integer.toString(x).toCharArray();
//        int end = chars.length - 1;
//        for (int start = 0; start < end; start++,end--) {
//            if (chars[start] == chars[end]){
//                continue;
//            }else {
//                return false;
//            }
//        }
//        return true;
//    }
    public boolean isPalindrome(int x) {
        if (x < 0) {
            return false;
        }

        int revert = 0;
        int origin = x;
        while (x > 0) {
            revert = revert * 10 + x % 10;
            x /= 10;
        }
        return revert == origin;
    }

    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public ListNode mergeKLists(ListNode[] lists) {

        if (lists.length == 0) {
            return null;
        }
        return merge(lists, 0, lists.length - 1);
    }

    private ListNode merge(ListNode[] lists, int start, int end) {

        if (end - start > 1) {
            int mid = (start + end) / 2;
           return mergeTwoList(merge(lists, start, mid),merge(lists, mid + 1, end));
        } else if(end - start == 1){
            return mergeTwoList(lists[start],lists[end]);
        }else {
            return lists[start];
        }
    }

    private ListNode mergeTwoList(ListNode l1, ListNode l2) {

        if(l1 == null){
            return l2;
        }
        if(l2 == null){
            return l1;
        }

        if (l1.val > l2.val) {
            l2.next = mergeTwoList(l1,l2.next);
            return l2;
        }else {
            l1.next = mergeTwoList(l1.next,l2);
            return l1;
        }
    }

    public ListNode reverseKGroup(ListNode head, int k) {

        //返回条件-当前链表不足k个
        ListNode cursor = new ListNode(0);
        cursor.next = head;
        for(int i=0;i<k;i++){
            if(cursor.next == null){
                return head;
            }else{
                cursor = cursor.next;
            }
        }

        //翻转k个链表
        ListNode  next = reverseK(head,1,k);
        next.next = reverseKGroup(cursor.next,k);
        return cursor;
    }

    //翻转k个链表
    private ListNode reverseK(ListNode head,int index,int k){
        if(index == k){
            return head;
        }

        ListNode temp  = reverseK(head.next,++index,k);
        temp.next = head;
        return head;
    }

    public static double sqrt_1(int v, double t) {
        //0-v
        double res = 0;
        double start = 0d;
        double end = v;
        while(start<end){
            res = (start + end) / 2;
            if ((res + t) * (res + t) >= v && (res - t) * (res - t) <= v) {
                return res;
            }else if((res - t) * (res - t) > v){
                end = res;
            } else if ((res + t) * (res + t) < v) {
                start  = res;
            }
        }
        return res;
    }

    public static double sqrt_2(int v, double t) {
        return mid(0d,v,v,t);
    }

    private static double mid(double start ,double end,int v,double t){
        double mid = (start+end)/2;
        if((mid + t) * (mid + t) >= v && (mid - t) * (mid - t) <= v){
            return mid;
        }
        if ((mid - t) * (mid - t) > v) {
            end = mid;
        } else if ((mid + t) * (mid + t) < v) {
            start = mid;
        }
        return mid(start,end,v,t);
    }

    public static int strStr(String haystack, String needle) {
        if(needle.equals("")) {
            return 0;
        }
        if(haystack.equals("")) {
            return -1;
        }

        // 构造KMP中的dp矩阵
        int m = needle.length();
        // 各个状态(行)遇到下一个字符(列)跳转到哪个状态
        int[][] dp = new int[m][256];
        // 影子状态
        int X = 0;
        dp[0][needle.charAt(0)] = 1;
        for (int i = 1; i < m; i++) {
            for (int j = 0; j < 256; j++) {
                //假设下个字符不匹配，此时要回去看影子状态，从而得知跳转到哪个状态
                dp[i][j] = dp[X][j];
            }
            // 只有pat上i的字符匹配，跳转到下个状态
            dp[i][needle.charAt(i)] = i + 1;
            // 更新影子状态
            X = dp[X][needle.charAt(i)];
//            Log.i("strStr","i:"+i+";x:"+X);
        }

        // 构造dp完成后，开始search
        // 初始状态为0
        int s = 0;
        for (int i = 0; i < haystack.length(); i++) {
            s = dp[s][haystack.charAt(i)];
            if (s == m) {
                return i - m + 1;
            }
        }

        // 匹配失败，返回-1
        return -1;
    }

    public static int divide(int dividend, int divisor) {

        int newDividend  = dividend;

        if(dividend == -2147483648&& divisor == -1){
            return 2147483647;
        }

        if(dividend == 0){
            return 0;
        }
        int tem_divisor = divisor;
        int tem_res = 1;
        int res = 0;
        while(Math.abs(newDividend)>=Math.abs(divisor)){
            if((newDividend > 0 && divisor > 0) ||(newDividend<0 && divisor<0)){
                tem_res = 1;
            }else{
                tem_res = -1;
            }

            tem_divisor = divisor;
            while (Math.abs(newDividend>>1) >= Math.abs(tem_divisor)) {
                tem_res = tem_res << 1;
                tem_divisor = tem_divisor << 1;
            }

            if((newDividend > 0 && divisor > 0) ||(newDividend<0 && divisor<0)){
                newDividend = newDividend - tem_divisor;
            }else{
                newDividend = newDividend + tem_divisor;
            }

            res += tem_res;

        }

        return res;
    }

    public static int[] longestValidParentheses(String s) {

        if(s == null ){
            return null;
        }

        int[] dp = new int[s.length()];
        int res  = 0;
        for(int i = 0; i < s.length();i++){

            if (')' == s.charAt(i)) {
                if (i > 0 && '(' == s.charAt(i - 1)) {
                    dp[i] = (i > 1 ? dp[i - 2] : 0) + 2;
                }else if(i>0 && ')'==s.charAt(i-1)){
                    if(i>=dp[i-1]+1&& '(' == s.charAt(i-dp[i-1]-1)){
                        dp[i] = dp[i-1]+2+(i>=dp[i-1]+2?dp[i-dp[i-1]-2]:0);
                    }
                }
                res = Math.max(res,dp[i]);
            }
        }
        return dp;
    }

//    class Solution {
//        public int maxSubArray(int[] nums) {
//
//            int[] dp = new int[nums.length];
//            int res = 0;
//            for (int i=0;i<nums.length;i++) {
//                if (nums[i] >= 0) {
//                    //如果遍历到是正数，不用想直接加只大不小
//                    dp[i] = i >= 1 ? dp[i - 1] : 0 + i;
//                }else {
//                    //如果是负数，这个时候就得好好考虑下了，到底要不要记录dp[i]
//                    if(dp[i-1]<0){
//                        dp[]
//                    }
//                }
//                res = Math.max(res,dp[i]);
//            }
//            return res;
//        }
//    }
}
