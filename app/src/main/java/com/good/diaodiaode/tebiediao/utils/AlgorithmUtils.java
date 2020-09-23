package com.good.diaodiaode.tebiediao.utils;

import android.util.Log;
import android.util.SparseArray;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by ex-lizheng102 on 2017-03-01.
 */

public class AlgorithmUtils {

    public AlgorithmUtils() {
    }
    public static float getNumber(float health, float atk, float armor) {

        ArrayList al = new ArrayList(16);
        al.add(12,"asdasd");
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

    public static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {
            val = x;
        }
    }

    public boolean isValidSudoku(char[][] board) {
        HashMap<Character,HashMap<Integer,Boolean>> h = new HashMap();
        HashMap<Character,HashMap<Integer,Boolean>> l = new HashMap();
        HashMap<Character,HashMap<Integer,Boolean>> f = new HashMap();
        for(int j=0;j<9;j++){
            for(int i=0;i<9;i++){
                char key = board[i][j];

                if(key  == '.'){
                    continue;
                }
                //先查同一行
                if(h.get(key)!=null){
                    if(h.get(key).get(j)){
                        return false;
                    }else{
                        h.get(key).put(j,true);
                    }

                }else{
                    HashMap<Integer,Boolean> sp = new HashMap();
                    sp.put(j,true);
                    h.put(key,sp);
                }

                //查同一列
                if(l.get(key)!=null){
                    if(l.get(key).get(i)){
                        return false;
                    }else{
                        l.get(key).put(i,true);
                    }

                }else{
                    HashMap<Integer,Boolean> sp = new HashMap();
                    sp.put(i,true);
                    l.put(key,sp);
                }

                //查同3*3单元
                if(f.get(key)!=null){
                    if(f.get(key).get(j*3+i/3)){
                        return false;
                    }else{
                        f.get(key).put(j*3+i/3,true);
                    }

                }else{
                    HashMap<Integer,Boolean> sp = new HashMap();
                    sp.put(j*3+i/3,true);
                    f.put(key,sp);
                }


            }
        }

        return true;
    }

    public ListNode mergeKLists1(ListNode[] lists) {

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

    public int minDistance(String word1, String word2) {

        char[] words1 = word1.toCharArray();
        char[] words2 = word2.toCharArray();

        int len1 = words1.length;
        int len2 = words2.length;
        int[][] dp = new int[len1][len2];

        dp[0][0] = words1[0] == words2[0]?0:1;

        for(int i=1;i<len1;i++){
            if(words2[0] == words1[i]){
                dp[i][0] = i;
            }else{
                dp[i][0] = dp[i-1][0] +1;
            }
        }

        for(int j=1;j<len1;j++){
            if(words1[0] == words2[j]){
                dp[0][j] = j;
            }else{
                dp[0][j] = dp[0][j-1] +1;
            }
        }

        for(int i=1;i<len1;i++){
            for(int j=1;i<len2;j++){
                if(words1[i] == words2[j]){
                    dp[i][j] = dp[i-1][j-1];
                }else{
                    dp[i][j] = dp[i-1][j-1] + 1;
                }
            }
        }
        return dp[len1-1][len2-1];
    }
    public static List<String> letterCombinations(String digits) {
        List<String> res = new ArrayList();
        if (digits.isEmpty()) {
            return res;
        }
        Map<Character, List<String>> maps = new HashMap();
        maps.put('2', Arrays.asList("a", "b", "c"));
        maps.put('3', Arrays.asList("d", "e", "f"));
        maps.put('4', Arrays.asList("g", "h", "i"));
        maps.put('5', Arrays.asList("j", "k", "l"));
        maps.put('6', Arrays.asList("m", "n", "o"));
        maps.put('7', Arrays.asList("p", "q", "r", "s"));
        maps.put('8', Arrays.asList("t", "u", "v"));
        maps.put('9', Arrays.asList("w", "x", "y", "z"));

        char[] chars = digits.toCharArray();

        for (char ch : chars) {

            List<String> strs = maps.get(ch);

            List<String> temp = new ArrayList<>();
            for (String end : strs) {
                if (res.isEmpty()) {
                    temp.add(end);
                } else {
                    for (String prefix : res) {
                        temp.add(prefix + end);
                    }
                }
            }

            res = temp;
        }

        return res;
    }


    public static ListNode removeNthFromEnd(ListNode head, int n) {

        int length = 1;
        List<ListNode> list = new ArrayList();
        list.add(head);

        ListNode pre = head;
        ListNode next;
        while ((next = pre.next) != null) {
            list.add(next);
            pre = next;
            length++;
        }

        if (n >= length) {
            head = head.next;
        } else {
            ListNode prel = list.get(length - n - 1);
            prel.next = prel.next.next;
        }


        return head;
    }

    public boolean isValid(String s) {

        if (s == null || s.isEmpty()) {
            return false;
        }
        ArrayDeque<Character> records = new ArrayDeque();

        char[] chars = s.toCharArray();


        for (char c : chars) {
            switch (c) {
                case '(':
                case '[':
                case '{':
                    records.push(c);
                    break;
                case ')':
                    if (records.isEmpty() || '(' != records.pop()) {
                        return false;
                    }
                    break;
                case ']':
                    if (records.isEmpty() || '[' != records.pop()) {
                        return false;
                    }
                    break;
                case '}':
                    if (records.isEmpty() || '{' != records.pop()) {
                        return false;
                    }
                    break;
            }
        }

        return records.isEmpty();
    }

    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode temp1 = new ListNode(0);
        temp1.next = l1;

        ListNode temp2 = new ListNode(0);
        temp2.next = l2;

        ListNode dummy = new ListNode(0);
        ListNode current = dummy;
        while (temp1.next != null || temp2.next != null) {

            if (temp1.next == null) {
                current.next = temp2.next;
                break;
            }

            if (temp2.next == null) {
                current.next = temp1.next;
                break;
            }

            if (temp1.next.val < temp2.next.val) {
                current.next = temp1.next;
                current = temp1.next;
                temp1 = temp1.next;
            } else {
                current.next = temp2.next;
                current = temp2.next;
                temp2 = temp2.next;
            }
        }

        return dummy.next;
    }

    /**
     * Definition for singly-linked list.
     * public class ListNode {
     * int val;
     * ListNode next;
     * ListNode(int x) { val = x; }
     * }
     */

    ListNode dump = new ListNode(0);
    ListNode cur = dump;

    public ListNode mergeKLists(ListNode[] lists) {

        if (lists.length == 0) {
            return null;
        }
        while (true) {
            int changeIndex = 0;
            int tempMax = Integer.MIN_VALUE;
            for (int i = 0; i < lists.length; i++) {
                //链表循环完成了
                if (lists[i] == null) {
                    continue;
                }
                if (lists[i].val > tempMax) {
                    changeIndex = i;
                    tempMax = lists[i].val;
                }
            }
            if (tempMax == Integer.MIN_VALUE) {
                return dump.next;
            } else {
                lists[changeIndex] = lists[changeIndex].next;
                cur.next = lists[changeIndex].next;
                cur = cur.next;
            }
        }
    }


    private volatile static int i = 0;
    private volatile static int currenThread = 1;

    public static boolean threadTest() {

        final ReentrantLock lock = new ReentrantLock();
        final Condition conditionA = lock.newCondition();
        final Condition conditionB = lock.newCondition();
        final Condition conditionC = lock.newCondition();


        Thread t1 = new Thread("thread1") {
            @Override
            public void run() {
                plusI(lock, conditionA, conditionB, 1, 2);
            }
        };

        Thread t2 = new Thread("thread2") {
            @Override
            public void run() {
                plusI(lock, conditionB, conditionC, 2, 3);
            }
        };

        Thread t3 = new Thread("thread3") {
            @Override
            public void run() {
                plusI(lock, conditionC, conditionA, 3, 1);
            }
        };

        t1.start();
        t2.start();
        t3.start();

        return true;
    }

    private static void plusI(ReentrantLock lock, Condition conditionC, Condition conditionA, int i, int i2) {
        while (i < 1000) {
            lock.lock();
            try {
                if (currenThread != i) {
                    try {
                        conditionC.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                if (i >= 1000) {
                    break;
                }

                i++;
                currenThread = i2;
                Log.e("threadtest", Thread.currentThread().getName() + ";i:" + i);
                conditionA.signal();
            } catch (Exception e) {

            } finally {
                lock.unlock();
            }

        }
    }

    public static String countAndSay(int n) {

        if(n==1){
            return "1";
        }

        String s = countAndSay(n-1);

        StringBuilder res =new StringBuilder();
        int start = 0;
        for(int i=0;i<s.length();i++){
            int current = s.charAt(i) -'0';

            if(i==(s.length()-1)){
                res.append(i-start+1).append(s.charAt(i));
            }else{
                int next = s.charAt(i+1)-'0';
                //当前已经是该char的结束位置
                if(current!=next){
                    res.append(String.valueOf(i-start+1)).append(String.valueOf(current));
                    start = next;
                }
            }
        }

        return res.toString();
    }
}

