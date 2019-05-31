package leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * description
 *
 * @author cl10805
 * @date 2019/2/14 14:50
 */
public class HashMapTest {
    public static void main(String[] args) {
        System.out.println(countPrimes(10));
    }



    //两数组求交集
    public int[] intersect(int[] nums1, int[] nums2) {
        if (nums1 == null || nums2 == null) {
            return new int[]{};
        }
        Map<Integer, Integer> map = new HashMap<>();
        for (int i : nums1) {
            if (map.containsKey(i)) {
                map.replace(i, map.get(i) + 1);
            } else {
                map.put(i, 1);
            }
        }
        List<Integer> rspList = new ArrayList<>();
        for (int i : nums2) {
            if (map.containsKey(i) && map.get(i) > 0) {
                rspList.add(i);
                map.replace(i, map.get(i) - 1);
            }
        }
        int[] rsp = new int[rspList.size()];
        for (int i = 0; i < rspList.size(); i++) {
            rsp[i] = rspList.get(i);
        }
        return rsp;
    }

    /**
     * 单词模式
     *
     * @param pattern
     * @param str
     * @return
     */
    public boolean wordPattern(String pattern, String str) {
        if (pattern == null || str == null) {
            return false;
        }
        String[] array = str.split(" ");
        if (pattern.length() != array.length) {
            return false;
        }
        Map<Character, String> map = new HashMap<>();
        for (int i = 0; i < pattern.length(); i++) {
            if (map.containsKey(pattern.charAt(i))) {
                if (!map.get(pattern.charAt(i)).equals(array[i])) {
                    return false;
                }
            } else {
                if (map.containsValue(array[i])) {
                    return false;
                } else {
                    map.put(pattern.charAt(i), array[i]);
                }
            }
        }
        return true;
    }

    /**
     * 统计质数数量
     *
     * @param n
     * @return
     */
    public static int countPrimes(int n) {
        if (n <= 1) {
            return 0;
        }
        boolean[] isPrime = new boolean[n];
        isPrime[0] = true;
        isPrime[1] = true;

        for (int i = 2; i * i < n; i++) {
            if (!isPrime[i]) {
                for (int j = 2 * i; j < n; j += i) {
                    isPrime[j] = true;
                }
            }
        }
        int res = 0;
        for (boolean b : isPrime) {
            if (!b) {
                res++;
            }
        }
        return res;

    }

    /**
     * 是否为快乐数 快乐数即 每一位的平方相加，最终为1的是快乐数
     *
     * @param n
     * @return
     */
    public static boolean isHappy(int n) {
        Map<Integer, Integer> map = new HashMap<>();
        dsfHappy(n, map);
        if (map.containsKey(1)) {
            return true;
        }
        return false;
    }

    public static void dsfHappy(int n, Map<Integer, Integer> map) {
        if (map.containsKey(n)) {
            return;
        } else {
            int sum = 0;
            int tempN = n;
            while (n > 0) {
                sum += (n % 10) * (n % 10);
                n /= 10;
            }
            map.put(tempN, sum);
            dsfHappy(sum, map);
        }
    }

    /**
     * 数组中只有一个数字只出现一次，其他都出现两次，找出那个只出现一次的数字
     *
     * @param nums
     * @return
     */
    public static int singleNumber(int[] nums) {
        int rsp = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        if (nums == null || nums.length == 0) {
            return 0;
        }
        for (int i : nums) {
            if (map.containsKey(i)) {
                map.remove(i);
            } else {
                map.put(i, 1);
            }
        }
        for (int i : map.keySet()) {
            rsp = i;
        }
        return rsp;
    }
}
