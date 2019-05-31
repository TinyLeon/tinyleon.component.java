package leetcode;

import java.util.Stack;

/**
 * description
 *
 * @author cl10805
 * @date 2019/1/24 10:46
 */
public class MinStack {
    private Stack<Integer> stack;

    /**
     * initialize your data structure here.
     */
    public MinStack() {
        stack = new Stack<Integer>();
    }

    public void push(int x) {
        if (stack.isEmpty()) {
            stack.push(x);
            stack.push(x);
        } else {
            int tmp = stack.peek();
            stack.push(x);
            if (tmp < x) {
                stack.push(tmp);
            } else {
                stack.push(x);
            }
        }
    }

    public void pop() {
        stack.pop();
        stack.pop();
    }

    public int top() {
        if (stack.size() > 1) {
            return stack.get(stack.size() - 2);
        } else {
            return stack.get(0);
        }

    }

    public int getMin() {
        return stack.peek();
    }

    public static void main(String[] args) {
        MinStack obj = new MinStack();
        obj.push(1);
//        obj.push(2);
//        obj.push(3);
        obj.pop();
        int param3 = obj.top();
        int param4 = obj.getMin();
        System.out.println(param3);
        System.out.println(param4);
    }
}
