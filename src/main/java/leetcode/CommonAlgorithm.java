package leetcode;

import java.util.*;

/**
 * Created by cl10805 on 2017/3/20.
 */
public class CommonAlgorithm {
    public static void main(String[] args) {
        //getnarcissiFew();
        //printImage();
        //        sqrt(2147395600);
//        int[] nums = {1, 2, 5, 10, 11};
//        threeNumsClosest(nums, 12);
//        System.out.println(getSum(1,Integer.MIN_VALUE));
//        System.out.println(generateParenthesis(3));
//        System.out.println(divide(1,1));
//        System.out.println(multiply("123","456"));
//        int[] array = new int[]{1, 3, 1, 1, 1};
//        System.out.println(search(array, 3));
//        ListNode head=new ListNode(0);
//        ListNode cur=new ListNode(1);
//        head.next=cur;
//        cur.next=new ListNode(4);
//        cur.next.next=new ListNode(3);
//        cur.next.next.next=new ListNode(2);
//        cur.next.next.next.next=new ListNode(5);
//        cur.next.next.next.next.next=new ListNode(2);
//        partition(head.next,3);
//        TreeNode treeNode = new TreeNode(5);
//        treeNode.left = new TreeNode(4);
//        treeNode.right = new TreeNode(8);
//        treeNode.left.left = new TreeNode(11);
//        treeNode.right.left = new TreeNode(13);
//        treeNode.right.right = new TreeNode(4);
//        treeNode.left.left.left = new TreeNode(7);
//        treeNode.left.left.right = new TreeNode(2);
//        treeNode.right.right.left = new TreeNode(5);
//        treeNode.right.right.right = new TreeNode(1);
//        pathSum(treeNode, 22);
//        compareVersion("0.1","1.1");

        int[] nums = new int[]{1, 2, 3};
        System.out.println(permuteUnique(nums));
        System.out.println("test");

    }

    /**
     * 全排列 包含重复元素
     *
     * @param nums
     * @return
     */
    public static List<List<Integer>> permuteUnique(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> rsp = new ArrayList<>();
        sortAll(rsp, nums, 0, nums.length - 1);
        return rsp;
    }

    private static void sortAll(List<List<Integer>> rsp, int[] nums, int left, int right) {
        if (left == right) {
            List<Integer> tempList = new ArrayList<>();
            for (int i : nums) {
                tempList.add(i);
            }
            if (!rsp.contains(tempList)) {
                rsp.add(tempList);
            }
        } else {
            for (int i = left; i <= right; i++) {
                if (i != left && nums[left] == nums[i]) {
                    continue;//去重
                }
                swap(nums, left, i);
                sortAll(rsp, nums, left + 1, right);
                swap(nums, i, left);
            }
        }
    }

    private static void swap(int[] nums, int i, int j) {
        if (i == j) {
            return;
        }
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    /**
     * 最大数
     *
     * @param nums
     * @return
     */
    public static String largestNumber(int[] nums) {
        //本质是个排序题，试试用冒泡排一下
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = i; j < nums.length; j++) {
                if (compare(nums[i], nums[j]) < 0) {
                    int temp = nums[i];
                    nums[i] = nums[j];
                    nums[j] = temp;
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int item : nums) {
            sb.append(item);
        }
        return sb.toString();
    }

    private static int compare(int a, int b) {
        if (a == b) {
            return 0;
        } else {
            String aa = String.valueOf(a);
            String bb = String.valueOf(b);
            int i = 0, n = Math.max(aa.length(), bb.length());
            while (i < n) {
                if (i >= aa.length()) {
                    return 1;
                } else if (i >= bb.length()) {
                    return -1;
                } else {
                    char c = aa.charAt(i);
                    char d = bb.charAt(i);
                    if (c > d) {
                        return 1;
                    } else if (c < d) {
                        return -1;
                    }
                }
                i++;
            }
        }
        return 0;
    }

    public static int compareVersion(String version1, String version2) {
        String[] array1 = version1.split("\\.");
        String[] array2 = version2.split("\\.");
        int len = Math.max(array1.length, array2.length);
        for (int i = 0; i < len; i++) {
            int a = getNum(array1, i);
            int b = getNum(array2, i);
            if (a > b) {
                return 1;
            } else if (a < b) {
                return -1;
            }
        }
        return 0;
    }

    private static int getNum(String[] array, int index) {
        if (index < array.length) {
            int len = array[index].length();
            if (len == 0) {
                return 0;
            } else {
                int num = 0, i = 0;
                while (i < len) {
                    num = num * 10 + array[index].charAt(i++) - '0';
                }
                return num;
            }
        }
        return 0;
    }

    /**
     * 二叉树路径综合等于某值
     *
     * @param root
     * @param sum
     * @return
     */
    public static List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> rsp = new ArrayList<>();
        dfs(root, rsp, new ArrayList<Integer>(), sum);
        return rsp;
    }

    private static void dfs(TreeNode root, List<List<Integer>> rsp, List<Integer> temp, int sum) {
        //回溯法
        if (root != null && getSum(temp) < sum) {
            temp.add(root.val);
            if (root.left == null && root.right == null) {
                if (sum == getSum(temp)) {
                    rsp.add(new ArrayList<Integer>(temp));
                }
            }
            List<Integer> forRight = new ArrayList<>(temp);
            dfs(root.left, rsp, temp, sum);
            dfs(root.right, rsp, forRight, sum);
        }
    }

    private static int getSum(List<Integer> list) {
        int sum = 0;
        for (int i : list) {
            sum += i;
        }
        return sum;
    }

    /**
     * 分隔链表
     */
    public static ListNode partition(ListNode head, int x) {
        ListNode small = new ListNode(0);
        ListNode big = new ListNode(0);
        ListNode bigPoint = big;
        ListNode dummyHead = small, cur = head;
        while (cur != null) {
            if (cur.val < x) {
                small.next = cur;
                small = small.next;
            } else {
                big.next = cur;
                big = big.next;
            }
            cur = cur.next;
        }
        big.next = null;
        small.next = bigPoint.next;
        return dummyHead.next;
    }

    /**
     * 旋转列表查找
     *
     * @param nums
     * @param target
     * @return
     */
    public static boolean search(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return false;
        }
        return search2(nums, 0, nums.length - 1, target);
    }

    private static boolean search2(int[] nums, int start, int end, int target) {
        if (start > end) {
            return false;
        }
        int mid = (start + end) / 2;
        if (nums[mid] == target) {
            return true;
        }
        //前半段有序
        if (nums[start] <= nums[mid]) {
            if (target >= nums[start] && target < nums[mid]) {
                return search2(nums, start, mid, target);
            } else {
                return search2(nums, mid + 1, end, target);
            }
        } else {//前半段无序 后半段有序
            if (target > nums[mid] && target <= nums[end]) {
                return search2(nums, mid, end, target);
            } else {
                return search2(nums, start, mid - 1, target);
            }
        }
    }

    public static String multiply(String num1, String num2) {
        List<List<Integer>> list = new ArrayList<>(num2.length());
        for (int i = num2.length() - 1; i >= 0; i--) {
            list.add(multiplySingle(num1, num2.charAt(i), num2.length() - i - 1));
        }
        List<Integer> numList = list.get(0);
        //list中的元素逐个相加
        if (list.size() > 1) {
            for (int i = 1; i < list.size(); i++) {
                numList = add(numList, list.get(i));
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i : numList) {
            sb.append(i);
        }
        return sb.reverse().toString();
    }

    private static List<Integer> add(List<Integer> list1, List<Integer> list2) {
        int carry = 0;
        int i = 0;
        List<Integer> list = new ArrayList<>();
        while (i < list1.size() || i < list2.size()) {
            int num1 = list1.size() > i ? list1.get(i) : 0;
            int num2 = list2.size() > i ? list2.get(i) : 0;
            int temp = num1 + num2 + carry;
            list.add(temp % 10);
            carry = temp / 10;
            i++;
        }
        if (carry > 0) {
            list.add(carry);
        }
        return list;
    }

    private static List<Integer> multiplySingle(String num1, char c, int zeroCount) {
        List<Integer> rsp = new ArrayList<>();
        while (zeroCount > 0) {
            rsp.add(0);
            zeroCount--;
        }
        int i = num1.length() - 1;
        int carry = 0;
        while (i >= 0) {
            char cc = num1.charAt(i);
            int temp = (c - '0') * (cc - '0') + carry;
            rsp.add(temp % 10);
            carry = temp / 10;
            i--;
        }
        if (carry > 0) {
            rsp.add(carry);
        }
        return rsp;
    }

    /**
     * 不使用除法乘法 去计算除法
     *
     * @param dividend
     * @param divisor
     * @return
     */
    public static int divide(int dividend, int divisor) {
        if (divisor == 0) {
            return 0;
        }
        int posNeg = 1;
        if ((dividend < 0 && divisor > 0) || (dividend > 0 && divisor < 0)) {
            posNeg = -1;
        }
        divisor = Math.abs(divisor);
        dividend = Math.abs(dividend);
        int num = divisor, times = 1, rsp = 0;
        while (num < dividend) {
            num = num << 1;
            times = times << 1;
            if (num > dividend) {
                dividend = dividend - num / 2;
                num = divisor;
                rsp += times / 2;
                times = 1;
            }
        }
        return rsp * posNeg;
    }


    /**
     * 给定括号对数，求出所有括号的可能性
     *
     * @param n
     * @return
     */
    public static List<String> generateParenthesis(int n) {
        List<String> ans = new ArrayList();
        backtrack(ans, "", 0, 0, n);
        return ans;
    }

    private static void backtrack(List<String> ans, String cur, int open, int close, int max) {
        if (cur.length() == max * 2) {
            ans.add(cur);
            return;
        }

        if (open < max)
            backtrack(ans, cur + "(", open + 1, close, max);
        if (close < open)
            backtrack(ans, cur + ")", open, close + 1, max);
    }


    public static int getSum(int a, int b) {
        while (b > 0) {
            int bTemp = b;
            b = (a & b) << 1;
            a = a ^ bTemp;
        }
        return a;
    }

    /**
     * 完全平方数
     *
     * @param num
     * @return
     */
    public boolean isPerfectSquare(int num) {
        int i = 1;
        int rsp = 0;
        while (i < num) {
            if (rsp == num) {
                return true;
            }
            rsp += 2 * i - 1;
            i++;
        }
        return false;
    }

    /**
     * 两数之和
     *
     * @param numbers
     * @param target
     * @return
     */
    public static int[] twoSum(int[] numbers, int target) {
        if (numbers == null || numbers.length < 2) {
            return new int[]{};
        }
        int start = 0;
        int end = numbers.length - 1;
        while (start < end && numbers[start] + numbers[end] != target) {
            int temp = numbers[start] + numbers[end];
            if (target == temp) {
                return new int[]{start + 1, end + 1};
            } else if (temp < target) {
                start++;
            } else if (temp > target) {
                end--;
            }
        }
        return new int[]{start, end};
    }


    private static int sqrt(int x) {
        if (x <= 1) {
            return x;
        }
        int i = 46340;
        while (i * i > x) {
            i /= 2;
        }
        for (int j = i; j < i * 2; j++) {
            if (j * j > x) {
                return j - 1;
            }
        }
        return 0;
    }

    /**
     * 获取水仙花数
     */
    private static void getnarcissiFew() {
        int a = 0;
        int b = 0;
        int c = 0;
        for (int i = 100; i < 1000; i++) {
            a = i / 100;
            b = i % 100 / 10;
            c = i % 100 % 10;
            if ((mi3(a) + mi3(b) + mi3(c)) == i) {
                System.out.println(i);
            }
        }
    }

    private static int mi3(int number) {
        return (int) Math.pow(number, 3);
    }

    /**
     * 打印图形，类似于： * * * * * * * * * * * * * * *
     */
    private static void printImage() {
        int colMax = 9;
        int rowMax = 5;
        for (int i = 1, z = 0; i <= rowMax; i++, z++) {
            for (int j = 1; j <= colMax; j++) {
                if (colMax / 2 - z + 1 <= j && j <= colMax / 2 + z + 1) {// 获取打印*的范围
                    if ((i % 2 == 1 && j % 2 == 1) || (i % 2 == 0 && j % 2 == 0)) {// 如果行为奇数，则奇数为打印*，若行为偶数，在偶数位打印
                        System.out.print("*");
                    } else {
                        System.out.print(" ");
                    }
                } else {
                    System.out.print(" ");
                }
            }
            System.out.println("");
        }
    }

    public static List<List<Integer>> threeSum(int[] nums) {
        if (nums.length < 3) {
            return new ArrayList<>();
        }
        Arrays.sort(nums);

        List<List<Integer>> rsp = new ArrayList<>();

        Set<String> duplicate = new HashSet<>();
        for (int i = 0; i < nums.length - 2; i++) {
            int head = i + 1;
            int tail = nums.length - 1;
            while (nums[i] + nums[head] + nums[tail] > 0 && head < tail - 1) {
                tail--;
            }
            while (nums[i] + nums[head] + nums[tail] == 0) {
                String only = String.valueOf(nums[i]) + String.valueOf(nums[head])
                        + String.valueOf(nums[tail]);
                if (!duplicate.contains(only)) {
                    List<Integer> temp = new ArrayList<>(3);
                    temp.add(nums[i]);
                    temp.add(nums[head]);
                    temp.add(nums[tail]);
                    rsp.add(temp);
                    duplicate.add(only);
                }
                while (head < tail - 2) {
                    head++;
                    tail--;
                    if (nums[i] + nums[head] + nums[tail] == 0) {
                        String only1 = String.valueOf(nums[i])
                                + String.valueOf(nums[head]) + String.valueOf(nums[tail]);
                        if (!duplicate.contains(only1)) {
                            List<Integer> temp = new ArrayList<>(3);
                            temp.add(nums[i]);
                            temp.add(nums[head]);
                            temp.add(nums[tail]);
                            rsp.add(temp);
                            duplicate.add(only);
                        }
                    }
                }

            }
        }
        return rsp;
    }


    public static int threeNumsClosest(int[] nums, int target) {
        if (nums.length < 3) {
            return 0;
        }
        Arrays.sort(nums);

        int rsp = nums[0] + nums[1] + nums[2];
        for (int i = 0; i < nums.length - 2; i++) {
            int head = i + 1, tail = nums.length - 1;
            while (head < tail - 1) {
                int threeNum = nums[i] + nums[head] + nums[tail];
                if (Math.abs(rsp - target) > Math.abs(threeNum - target)) {
                    rsp = threeNum;
                }
                if (threeNum < target) {
                    while (head < tail - 1 && nums[head] == nums[head + 1]) {
                        head++;
                    }
                    head++;
                }
                if (threeNum > target) {
                    while (head < tail - 1 && nums[tail] == nums[head - 1]) {
                        tail--;
                    }
                    tail--;
                }
            }
        }
        return rsp;
    }

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}
