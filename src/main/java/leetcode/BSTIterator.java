package leetcode;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

import leetcode.TreeTest.TreeNode;

/**
 * description
 *
 * @author cl10805
 * @date 2019/4/26 14:23
 */
public class BSTIterator {
    private Queue<Integer> queue;

    public BSTIterator(TreeNode root) {
        queue = new LinkedList<>();
        if (root != null) {
            TreeNode cur = root;
            Stack<TreeNode> stack = new Stack<>();
            while (cur != null || !stack.isEmpty()) {
                if (cur != null) {
                    stack.push(cur);
                    cur = cur.left;
                } else {
                    TreeNode node = stack.pop();
                    queue.add(node.val);
                    cur = node.right;
                }
            }
        }
    }

    /**
     * @return the next smallest number
     */
    public int next() {
        if (!queue.isEmpty()) {
            return queue.poll();
        }
        return 0;
    }

    /**
     * @return whether we have a next smallest number
     */
    public boolean hasNext() {
        return !queue.isEmpty();
    }


    public static void main(String[] args) {
        TreeNode treeNode = new TreeNode(7);
        treeNode.left = new TreeNode(3);
        treeNode.right = new TreeNode(15);
        treeNode.right.left = new TreeNode(9);
        treeNode.right.right = new TreeNode(20);
        BSTIterator b = new BSTIterator(treeNode);
        while (b.hasNext()) {
            System.out.println(b.next());
        }
    }
}
