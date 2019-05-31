package leetcode;

import java.util.HashSet;
import java.util.Stack;

/**
 * 邻接表测试
 *
 * @author cl10805
 * @createTime 2019-05-06 10:09
 */
public class EdgeNodeTest {
    public static void main(String[] args) {
        int numCourses = 2;
        int[][] pre = new int[][]{{1, 0}};
        System.out.println(canFinish2(numCourses, pre));
    }

    static HashSet<Integer> set = new HashSet<Integer>();
    static boolean[][] adjMat;
    static boolean[] visited;

    public static boolean canFinish2(int numCourses, int[][] prerequisites) {
        adjMat = new boolean[numCourses][numCourses];
        visited = new boolean[numCourses];
        /**
         * 构建邻接矩阵
         */
        for (int i = 0; i < prerequisites.length; i++) {
            adjMat[prerequisites[i][1]][prerequisites[i][0]] = true;
        }
        /**
         * 深度优先搜索
         */
        for (int i = 0; i < numCourses; i++) {
            if (!visited[i]) {
                set.clear();
                if (!DFS(i))
                    return false;
            }
        }
        return true;
    }

    private static boolean DFS(int index) {
        visited[index] = true;
        set.add(index);
        for (int i = 0; i < visited.length; i++) {
            if (adjMat[index][i] && set.contains(i))
                return false;
            if (!visited[i] && adjMat[index][i]) {
                if (!DFS(i))
                    return false;
            }
        }
        set.remove(index);
        return true;
    }


    public static boolean canFinish(int numCourses, int[][] prerequisites) {
        /**
         * 构建邻接表
         */
        EdgeNode[] edges = new EdgeNode[numCourses];
        Node temp = null;
        int topoSize = 0;
        for (int i = 0; i < numCourses; i++) {
            edges[i] = new EdgeNode();
            edges[i].in = 0;
            edges[i].val = i;
        }
        for (int i = 0; i < prerequisites.length; i++) {
            temp = edges[prerequisites[i][1]].next;
            Node newNode = new Node();
            newNode.val = prerequisites[i][0];
            edges[prerequisites[i][1]].next = newNode;
            newNode.next = temp;
            edges[prerequisites[i][0]].in++;
        }
        /**
         * 进行拓扑排序
         */
        Stack<EdgeNode> stack = new Stack<EdgeNode>();//存储入度为0的结点
        for (int i = 0; i < numCourses; i++) {                //将入度为0的结点压入栈中
            if (edges[i].in == 0) {
                stack.push(edges[i]);
            }
        }
        EdgeNode deletedNode = null;
        while (!stack.isEmpty()) {
            topoSize++;
            deletedNode = stack.pop();               //删除入读为0的结点
            temp = deletedNode.next;
            while (temp != null) {                       //更新其邻接点的入度
                if (edges[temp.val].in > 0) {
                    edges[temp.val].in--;
                    if (edges[temp.val].in == 0)      //如果更新后的邻接结点的入度为0，将其压入栈中
                        stack.push(edges[temp.val]);
                }
                temp = temp.next;
            }
        }
        return topoSize == numCourses;
    }

    static class EdgeNode {
        int in;
        int val;
        Node next;
    }

    static class Node {
        int val;
        Node next;
    }
}
