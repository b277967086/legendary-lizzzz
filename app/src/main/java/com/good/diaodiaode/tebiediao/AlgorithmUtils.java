package com.good.diaodiaode.tebiediao;

import java.util.HashMap;

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

}
