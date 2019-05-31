package leetcode;

import java.util.*;

/**
 * description
 *
 * @author cl10805
 * @date 2019/2/12 10:16
 */
public class StackTest {
    public static void main(String[] args) {
        test();
    }

    /**
     * 下一个更大的数字
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        if (nums1 == null || nums2 == null || nums1.length == 0 || nums2.length == 0) {
            return null;
        }
        Stack<Integer> stack = new Stack<>();
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i : nums2) {
            while (!stack.isEmpty() && stack.peek() < i) {
                map.put(stack.pop(), i);
            }
            stack.push(i);
        }
        int[] result = new int[nums1.length];
        for (int i = 0; i < nums1.length; i++) {
            result[i] = map.getOrDefault(nums1[i], -1);
        }
        return result;
    }

    /**
     * 给定一个字符串列表，每个字符串可以是以下四种类型之一：
     * 1.整数（一轮的得分）：直接表示您在本轮中获得的积分数。
     * 2. "+"（一轮的得分）：表示本轮获得的得分是前两轮有效 回合得分的总和。
     * 3. "D"（一轮的得分）：表示本轮获得的得分是前一轮有效 回合得分的两倍。
     * 4. "C"（一个操作，这不是一个回合的分数）：表示您获得的最后一个有效 回合的分数是无效的，应该被移除。
     *
     * @param ops
     * @return
     */
    public int calPoints(String[] ops) {
        Stack<Integer> stack = new Stack<>();
        for (String str : ops) {
            switch (str) {
                case "+":
                    int pre = stack.pop();
                    int prePre = stack.peek();
                    stack.push(pre);
                    stack.push(pre + prePre);
                    break;
                case "D":
                    stack.push(stack.peek() * 2);
                    break;
                case "C":
                    stack.pop();
                    break;
                default:
                    stack.push(Integer.valueOf(str));
                    break;
            }
        }
        int res = 0;
        while (!stack.isEmpty()) {
            res += stack.pop();
        }
        return res;
    }

    /**
     * 带退格键的字符串比较
     *
     * @param S
     * @param T
     * @return
     */
    public static boolean backspaceCompare(String S, String T) {
        if (S == null && T == null) {
            return true;
        }
        if ((S == null && T != null) || (S != null && T == null)) {
            return false;
        }
        Stack<Character> stackS = new Stack<>();
        for (char c : S.toCharArray()) {
            if (c != '#') {
                stackS.push(c);
            } else {
                if (!stackS.empty()) {
                    stackS.pop();
                }
            }
        }

        Stack<Character> stackT = new Stack<>();
        for (char c : T.toCharArray()) {
            if (c != '#') {
                stackT.push(c);
            } else {
                if (!stackT.empty()) {
                    stackT.pop();
                }
            }
        }
        return stackS.equals(stackT);
    }

    private static void test() {
        Queue<String> queue = new LinkedList<>();
        queue.add("1");
        queue.offer("2");
        queue.offer("3");
        System.out.println(queue);
        //reverse queue
        for (int i = 0; i < queue.size() - 1; i++) {
            queue.add(queue.poll());
        }
        System.out.println(queue);
    }

    public static class MyQueue {

        Stack<Integer> stack;
        Stack<Integer> stackTemp;

        /**
         * Initialize your data structure here.
         */
        public MyQueue() {
            stack = new Stack<>();
            stackTemp = new Stack<>();
        }

        /**
         * Push element x to the back of queue.
         */
        public void push(int x) {
            while (!stack.isEmpty()) {
                stackTemp.push(stack.pop());
            }
            stackTemp.push(x);
            while (!stackTemp.isEmpty()) {
                stack.push(stackTemp.pop());
            }
        }

        /**
         * Removes the element from in front of queue and returns that element.
         */
        public int pop() {
            return stack.pop();
        }

        /**
         * Get the front element.
         */
        public int peek() {
            return stack.peek();
        }

        /**
         * Returns whether the queue is empty.
         */
        public boolean empty() {
            return stack.isEmpty();
        }
    }
}
