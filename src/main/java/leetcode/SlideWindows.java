package leetcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * description
 *
 * @author cl10805
 * @date 2019/1/15 14:49
 */
public class SlideWindows {

    public static void main(String[] args) {
//        String test = "0  123";
//        int res = getNum(test);
//        System.out.println(res);

        Long temp = -20L >>> 1;
        System.out.println(Integer.toBinaryString(-20));
        System.out.println(temp.intValue());
        System.out.println(-20 >>> 32);
        char gg='1';
        Integer integer = Integer.valueOf(String.valueOf(gg));
        System.out.println(integer);

    }

    /**
     * 找出字符串中 无重复字符的最长子字符串
     *
     * @param s
     * @return
     */
    private static int lengthOfLongestSubstring(String s) {
        int n = s.length(), ans = 0;
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0, j = 0; j < n; j++) {
            if (map.containsKey(s.charAt(j))) {
                i = Math.max(map.get(s.charAt(j)), i);
            }
            ans = Math.max(ans, j - i + 1);
            map.put(s.charAt(j), j + 1);
        }
        return ans;
    }


    private static int getNum(String str) {
        Set<Character> metaNum = new HashSet<Character>() {{
            add('0');
            add('1');
            add('2');
            add('3');
            add('4');
            add('5');
            add('6');
            add('7');
            add('8');
            add('9');
        }};
        int posNegFlag = 1;
        boolean posNeg = false;
        str = str.trim();
        if (str.startsWith("+")) {
            posNegFlag = 1;
            str = str.substring(1, str.length());
            posNeg = true;
        } else if (str.startsWith("-")) {
            posNegFlag = -1;
            str = str.substring(1, str.length());
            posNeg = true;
        }
        while (str.startsWith("0")) {
            str = str.substring(1, str.length());
            posNeg = true;
        }

        StringBuilder rsp = new StringBuilder("");
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == ' ' && !posNeg) {
                continue;
            } else if (str.charAt(i) == ' ' && posNeg) {
                break;
            }
            if (!metaNum.contains(str.charAt(i))) {
                break;
            } else {
                rsp.append(str.charAt(i));
                posNeg = true;
            }
        }
        if (rsp.length() == 0) {
            rsp.append("0");
        }
        if (rsp.length() > 11) {
            if (posNegFlag < 0) {
                return Integer.MIN_VALUE;
            } else {
                return Integer.MAX_VALUE;
            }
        }

        long rspNum = Long.valueOf(rsp.toString()) * posNegFlag;
        if (rspNum >= Integer.MAX_VALUE) {
            return Integer.MAX_VALUE;
        } else if (rspNum <= Integer.MIN_VALUE) {
            return Integer.MIN_VALUE;
        }
        return Integer.valueOf(rsp.toString()) * posNegFlag;
    }
}
