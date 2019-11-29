package leetcode;

import java.util.*;

import leetcode.Test.ListNode;

/**
 * description
 *
 * @author cl10805
 * @date 2019/2/13 14:15
 */
public class TreeTest {

    public static void main(String[] args) {
        TreeNode treeNode = new TreeNode(1);
        treeNode.left = new TreeNode(2);
        treeNode.right = new TreeNode(3);
        treeNode.left.left = new TreeNode(4);
        treeNode.left.right = new TreeNode(5);
        treeNode.right.right = new TreeNode(6);
//        treeNode = treeNode.right;
//        treeNode.left = new TreeNode(3);
//        treeNode.right = new TreeNode(5);
//        System.out.println(findSecondMinimumValue(head));
//        List<Node> list = new ArrayList<>();
//        list.add(new Node(5, null));
//        list.add(new Node(6, null));
//        Node node3 = new Node(3, list);
//        System.out.println(maxDepth(node3));
//        levelOrderBottom(head);
//        System.out.println(0xf);
//        System.out.println(inorderTraversal2(head));

//        System.out.println(generateTrees(3));
//        System.out.println(numTrees(3));
//        System.out.println(maxDepth3(head));
//        int[] preArray = new int[]{3, 9, 20, 15, 7};
//        int[] inArray = new int[]{9, 3, 15, 20, 7};
//        System.out.println(buildTree(preArray, inArray));
//        flatten(treeNode);
//        sumNumbers(treeNode);
        System.out.println(lowestCommonAncestor(treeNode, treeNode.left.left, treeNode.left.right).val);
//        Node1 node1 = new Node1(1, null, null);
//        Node1 node2 = new Node1(2, null, null);
//        node2.random = node2;
//        node1.random = node2;
//        node1.next = node2;
//        System.out.println(copyRandomList(node1));
//        List<String> wordDict = new ArrayList<>();
//        wordDict.add("a");
//        wordDict.add("aa");
//        wordDict.add("aaa");
//        wordDict.add("aaaa");
//        wordDict.add("aaaaa");
//        wordDict.add("aaaaaa");
//        wordDict.add("aaaaaaa");
//        wordDict.add("aaaaaaaa");
//        wordDict.add("aaaaaaaaa");
//        wordDict.add("aaaaaaaaaa");
//        System.out.println((wordBreak("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaab", wordDict)));
//        ListNode l1 = new ListNode(-10);
//        l1.next = new ListNode(3);
//        l1.next.next = new ListNode(0);
//        l1.next.next.next = new ListNode(5);
//        l1.next.next.next.next = new ListNode(9);
//        sortedListToBST(l1);
    }

    /**
     * 二叉搜索树中第k小的元素 leetcode 230
     * @param root
     * @param k
     * @return
     */
    public int kthSmallest(TreeNode root, int k) {
        //前序遍历 获取第k-1个元素 比如用栈遍历二叉树
        int i=0;
        Stack<TreeNode> stack =new Stack<>();
        TreeNode node = root;
        while (node!=null ||!stack.isEmpty()){
            if(node!=null){
                stack.push(root);
                node = node.left;
            }else {
                node = stack.pop();
                i++;
                if(i==k){
                    return node.val;
                }
                node = node.right;
            }
        }
        return 0;
    }

    /**
     * 在二叉树中增加一行
     *
     * @param root
     * @param v
     * @param d
     * @return
     */
    public TreeNode addOneRow(TreeNode root, int v, int d) {
        if (d == 1) {
            TreeNode rsp = new TreeNode(v);
            rsp.left = root;
            return rsp;
        }
        //层序遍历
        int depth = 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            depth++;
            if (depth == d - 1) {
                //加层逻辑
                while (!queue.isEmpty()) {
                    TreeNode node = queue.poll();
                    TreeNode leftTemp = node.left;
                    TreeNode rightTemp = node.right;
                    node.left = new TreeNode(v);
                    node.left.left = leftTemp;
                    node.right = new TreeNode(v);
                    node.right.right = rightTemp;
                }
                break;
            }
            int n = queue.size();
            while (n > 0) {
                TreeNode node = queue.poll();
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
                n--;
            }
        }
        return root;
    }

    /**
     * 合并两个二叉树，重叠的节点用二者的和覆盖
     *
     * @param t1
     * @param t2
     * @return
     */
    public TreeNode mergeTrees_1(TreeNode t1, TreeNode t2) {
        if (t1 == null) {
            return t2;
        }
        if (t2 == null) {
            return t1;
        }
        // 先合并根节点
        t1.val += t2.val;
        // 再递归合并左右子树
        t1.left = mergeTrees_1(t1.left, t2.left);
        t1.right = mergeTrees_1(t1.right, t2.right);
        return t1;
    }

    /**
     * 不修改原二叉树的解法
     */
    public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
        if (t1 == null && t2 == null) {
            return null;
        }
        // 先合并根节点
        TreeNode root = new TreeNode((t1 == null ? 0 : t1.val) + (t2 == null ? 0 : t2.val));
        // 再递归合并左右子树
        root.left = mergeTrees(t1 == null ? null : t1.left, t2 == null ? null : t2.left);
        root.right = mergeTrees(t1 == null ? null : t1.right, t2 == null ? null : t2.right);
        return root;
    }

    private int n = 0;

    /**
     * 二叉树的坡度
     * 一个树的节点的坡度定义即为，该节点左子树的结点之和和右子树结点之和的差的绝对值。空结点的的坡度是0。
     *
     * @param root
     * @return
     */
    public int findTilt(TreeNode root) {
        add(root);
        return n;
    }

    public int add(TreeNode treeNode) {
        if (treeNode == null) {
            return 0;
        }
        int left = add(treeNode.left);
        int right = add(treeNode.right);
        n += Math.abs(left - right);
        return treeNode.val + left + right;
    }

    private int max = 0;

    /**
     * 二叉树直径（两个节点之间 边的最大数）
     *
     * @param root
     * @return
     */
    public int diameterOfBinaryTree2(TreeNode root) {
        depth(root);
        return max;
    }

    public int depth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = depth(root.left);
        int right = depth(root.right);
        max = Math.max(max, left + right);
        return Math.max(left, right) + 1;
    }

    /**
     * 插入二叉搜索树节点
     *
     * @param root
     * @param val
     * @return
     */
    public TreeNode insertIntoBST(TreeNode root, int val) {
        if (root == null) {
            return new TreeNode(val);
        }
        if (root.val < val) {
            if (root.right == null) {
                root.right = new TreeNode(val);
            } else {
                insertIntoBST(root.right, val);
            }
        } else {
            if (root.left == null) {
                root.left = new TreeNode(val);
            } else {
                insertIntoBST(root.left, val);
            }
        }
        return root;
    }

    /**
     * 删除二叉搜索树节点
     *
     * @param root
     * @param key
     * @return
     */
    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) {
            return null;
        }
        if (key < root.val) {
            // 待删除节点在左子树中
            root.left = deleteNode(root.left, key);
            return root;
        } else if (key > root.val) {
            // 待删除节点在右子树中
            root.right = deleteNode(root.right, key);
            return root;
        } else {
            // key == root.val，root 为待删除节点
            if (root.left == null) {
                // 返回右子树作为新的根
                return root.right;
            } else if (root.right == null) {
                // 返回左子树作为新的根
                return root.left;
            } else {
                // 左右子树都存在，返回后继节点（右子树最左叶子）作为新的根
                TreeNode successor = min(root.right);
                successor.right = deleteMin(root.right);
                successor.left = root.left;
                return successor;
            }
        }
    }

    private TreeNode min(TreeNode node) {
        if (node.left == null) {
            return node;
        }
        return min(node.left);
    }

    private TreeNode deleteMin(TreeNode node) {
        if (node.left == null) {
            return node.right;
        }
        node.left = deleteMin(node.left);
        return node;
    }

    /**
     * 路径总和，找出所有路径
     *
     * @param root
     * @param sum
     * @return
     */
    public List<List<Integer>> pathSum2(TreeNode root, int sum) {
        List<List<Integer>> rsp = new ArrayList<>();
        if (root == null) {
            return rsp;
        }
        pathSum(rsp, new ArrayList<Integer>(), root, sum);
        return rsp;
    }

    private void pathSum(List<List<Integer>> rsp, List<Integer> list, TreeNode root, int left) {
        if (root != null) {
            if (root.left == null && root.right == null) {
                if (root.val == left) {
                    list.add(left);
                    rsp.add(list);
                }
            } else {
                list.add(root.val);
                List<Integer> temp = new ArrayList<>(list);
                pathSum(rsp, list, root.left, left - root.val);
                pathSum(rsp, temp, root.right, left - root.val);
            }
        }
    }

    /**
     * 有序链表转换二叉搜索树
     *
     * @param head
     * @return
     */
    public static TreeNode sortedListToBST(ListNode head) {
        if (head == null) {
            return null;
        }
        if (head.next == null) {
            return new TreeNode(head.val);
        }
        return sortedListToBST(head, null);
    }

    public static TreeNode sortedListToBST(ListNode head, ListNode tail) {
        if (head == tail) {
            return null;
        }
        //快慢指针
        ListNode fast = head, slow = head;
        while (fast != tail && fast.next != tail) {
            slow = slow.next;
            fast = fast.next.next;
        }
        TreeNode node = new TreeNode(slow.val);
        node.left = sortedListToBST(head, slow);
        node.right = sortedListToBST(slow.next, tail);
        return node;
    }

    /**
     * 后续遍历和中序遍历构建二叉树
     *
     * @param inorder
     * @param postorder
     * @return
     */
    public TreeNode buildTreePost(int[] inorder, int[] postorder) {
        if (inorder.length == 0) {
            return null;
        }
        return rebuildBT(inorder, 0, inorder.length - 1, postorder, 0, postorder.length - 1);
    }

    private TreeNode rebuildBT(int[] in, int il, int ir, int[] pos, int pl, int pr) {
        if (ir < il || pr < pl) {
            return null;
        }
        TreeNode root = new TreeNode(pos[pr]);
        int idx = il;
        while (in[idx] != pos[pr]) {
            idx++;
        }
        root.left = rebuildBT(in, il, idx - 1, pos, pl, idx + pl - il - 1);
        root.right = rebuildBT(in, idx + 1, ir, pos, idx + pl - il, pr - 1);
        return root;
    }

    /**
     * 根据前序和中序遍历结果，构建二叉树
     *
     * @param preorder
     * @param inorder
     * @return
     */
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        return method(preorder, inorder, 0, 0, inorder.length - 1);
    }

    public TreeNode method(int[] preorder, int[] inorder, int preStart, int inStart, int inEnd) {
        if (inStart > inEnd) {
            return null;
        }
        int rootVal = preorder[preStart];
        TreeNode root = new TreeNode(rootVal);
        int index = 0;
        for (int i = inStart; i <= inEnd; i++) {
            if (inorder[i] == rootVal) {
                index = i;
            }
        }
        TreeNode left = method(preorder, inorder, preStart + 1, inStart, index - 1);
        TreeNode right = method(preorder, inorder, preStart + index - inStart + 1, index + 1, inEnd);
        root.left = left;
        root.right = right;
        return root;
    }

    /**
     * 根据前序和中序遍历结果，构建二叉树
     *
     * @param preorder
     * @param inorder
     * @return
     */
    public static TreeNode buildTree2(int[] preorder, int[] inorder) {
        //preorder第一个元素为root，在inorder里面找到root，在它之前的为左子树（长l1），之后为右子树（长l2）。preorder[1]到preorder[l1]为左子树,之后为右子树，分别递归。
        TreeNode head = solu(preorder, inorder, 0, preorder.length - 1, 0, inorder.length - 1);
        return head;
    }

    private static TreeNode solu(int[] pre, int[] in, int preStart, int preEnd, int inStart, int inEnd) {
        if (preStart > preEnd) {
            return null;
        }
        if (preStart == preEnd) {
            return new TreeNode(pre[preStart]);
        }
        TreeNode head = new TreeNode(pre[preStart]);
        for (int i = inStart; i <= inEnd; i++) {
            if (in[i] == pre[preStart]) {
                head.left = solu(pre, in, preStart + 1, preStart + i - inStart, inStart, i - 1);
                head.right = solu(pre, in, preStart + i - inStart + 1, preEnd, i + 1, inEnd);
                break;
            }
        }
        return head;
    }

    /**
     * 单词拆分
     *
     * @param s
     * @param wordDict
     * @return
     */
    public static boolean wordBreak(String s, List<String> wordDict) {
//递归太耗时
        //        if (wordDict.contains(s)) {
//            return true;
//        } else {
//            for (int i = 1; i <= s.length(); i++) {
//                String temp = s.substring(0, i);
//                if (wordDict.contains(temp) && wordBreak(s.substring(i), wordDict)) {
//                    return true;
//                }
//            }
//            return false;
//        }

        // 可以类比于背包问题
        int n = s.length();
        // memo[i] 表示 s 中以 i - 1 结尾的字符串是否可被 wordDict 拆分
        boolean[] memo = new boolean[n + 1];
        memo[0] = true;
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < i; j++) {
                if (memo[j] && wordDict.contains(s.substring(j, i))) {
                    memo[i] = true;
                    break;
                }
            }
        }
        return memo[n];
    }


    /**
     * 复制带随机指针的链表
     *
     * @param head
     * @return
     */
    public static Node1 copyRandomList(Node1 head) {
        Map<Node1, Node1> map = new HashMap<>();
        Node1 cur = head;
        while (cur != null) {
            Node1 copy = new Node1(cur.val, null, null);
            map.put(cur, copy);
            cur = cur.next;
        }
        cur = head;
        while (cur != null) {
            Node1 copy = map.get(cur);
            copy.next = map.get(cur.next);
            copy.random = map.get(cur.random);
            cur = cur.next;
        }
        return map.get(head);
    }

    /**
     * 二叉树最近的公共祖先
     *
     * @param root
     * @param p
     * @param q
     * @return
     */
    public static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        // LCA 问题，查阅相关资料
        if (root == null) {
            return root;
        }
        if (root == p || root == q) {
            return root;
        }
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        if (left != null && right != null) {
            return root;
        } else if (left != null) {
            return left;
        } else if (right != null) {
            return right;
        }
        return null;
    }


    /**
     * 根到叶子节点的数字之和
     *
     * @param root
     * @return
     */
    public static int sumNumbers(TreeNode root) {
        //最大深度遍历
        List<List<Integer>> rspList = new ArrayList<>();
        dfs(root, rspList, new ArrayList<Integer>());
        if (rspList.size() == 0) {
            return 0;
        }
        int rsp = 0;
        for (List<Integer> item : rspList) {
            rsp += getNum(item);
        }
        return rsp;
    }

    private static void dfs(TreeNode root, List<List<Integer>> rspList, List<Integer> temp) {
        if (root != null) {
            temp.add(root.val);
            if (root.left == null && root.right == null) {
                if (temp.size() > 1) {
                    rspList.add(new ArrayList<Integer>(temp));
                }
            } else {
                List<Integer> newTemp = new ArrayList<>(temp);
                dfs(root.left, rspList, temp);
                dfs(root.right, rspList, newTemp);
            }
        }
    }

    private static int getNum(List<Integer> list) {
        int rsp = 0;
        for (int i : list) {
            rsp = rsp * 10 + i;
        }
        return rsp;
    }

    public void flatten3(TreeNode root) {
        Stack<TreeNode> stack = new Stack();
        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }

            if (!stack.isEmpty()) {
                TreeNode node = stack.pop();
                TreeNode tmp = node.right;
                node.right = node.left;
                node.left = null;

                while (node.right != null) {
                    node = node.right;
                }
                node.right = tmp;
                root = tmp;
            }
        }
    }

    public void flatten2(TreeNode root) {
        if (root == null) {
            return;
        }
        flatten2(root.left);
        flatten2(root.right);
        TreeNode tmp = root.right;
        root.right = root.left;
        root.left = null;
        while (root.right != null) {
            root = root.right;
        }
        root.right = tmp;
    }

    /**
     * 二叉树展开成链表
     *
     * @param root
     */
    public static void flatten(TreeNode root) {
        _helper(root);
    }

    private static TreeNode _helper(TreeNode root) {
        if (root == null) {
            return root;
        }
        if (root.left == null && root.right == null) {
            return root;
        }
        if (root.left != null) {
            TreeNode right = root.right;
            root.right = root.left;
            root.left = null;
            if (right == null) {
                return _helper(root.right);
            } else {
                _helper(root.right).right = right;
                return _helper(right);
            }
        } else {
            return _helper(root.right);
        }
    }

    /**
     * 二叉树的深度
     *
     * @param root
     * @return
     */
    public static int maxDepth3(TreeNode root) {
        //递归是一个方向，层序遍历也可以
        if (root == null) {
            return 0;
        }
        int rsp = 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            rsp++;
            int n = queue.size();
            while (n > 0) {
                TreeNode node = queue.poll();
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
                n--;
            }
        }
        return rsp;
    }

    /**
     * 二叉树的锯齿形层次遍历
     *
     * @param root
     * @return
     */
    public static List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> rsp = new ArrayList<>();
        if (root == null) {
            return rsp;
        }
        Stack<TreeNode> stack = new Stack<>();
        Queue<TreeNode> queue = new LinkedList<>();//临时队列，用于存储临时数据
        stack.add(root);
        boolean leftFirst = false;
        while (!stack.isEmpty()) {
            List<Integer> list = new ArrayList<>();
            int n = stack.size();
            while (n > 0) {
                TreeNode node = stack.pop();
                list.add(node.val);
                if (leftFirst) {
                    if (node.left != null) {
                        queue.add(node.left);
                    }
                    if (node.right != null) {
                        queue.add(node.right);
                    }
                } else {
                    if (node.right != null) {
                        queue.add(node.right);
                    }
                    if (node.left != null) {
                        queue.add(node.left);
                    }
                }
                n--;
            }
            leftFirst = !leftFirst;
            rsp.add(list);
            //把队列的数据丢给栈
            while (!queue.isEmpty()) {
                stack.push(queue.poll());
            }
        }
        return rsp;
    }

    /**
     * 二叉树的层序遍历
     *
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrder2(TreeNode root) {
        List<List<Integer>> rsp = new ArrayList<>();
        if (root == null) {
            return rsp;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int n = queue.size();
            List<Integer> list = new ArrayList<>();
            while (n > 0) {
                TreeNode node = queue.poll();
                list.add(node.val);
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
                n--;
            }
            if (list.size() > 0) {
                rsp.add(list);
            }
        }
        return rsp;
    }

    /**
     * 判断是否为二叉搜索树
     *
     * @param root
     * @return
     */
    public static boolean isValidBST(TreeNode root) {
        boolean leftFlag = false, rightFlag = false;
        if (root != null) {
            if (root.left == null || (root.val > root.left.val && isValidBST(root.left))) {
                leftFlag = true;
            }
            if (root.right == null || (root.val < root.right.val && isValidBST(root.right))) {
                rightFlag = true;
            }
        }
        return root == null || leftFlag && rightFlag;
    }

    /**
     * 不同的二叉搜索树
     *
     * @param n
     * @return
     */
    public static int numTrees(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            for (int j = 1; j <= i; j++) {
                dp[i] += dp[j - 1] * dp[i - j];
            }
        }
        return dp[n];
    }

    /**
     * 生成所有的二叉搜索树
     *
     * @param n
     * @return
     */
    public static List<TreeNode> generateTrees(int n) {
        if (n == 0) {
            return new LinkedList<TreeNode>();
        }
        return generateTrees(1, n);
    }

    private static List<TreeNode> generateTrees(int start, int end) {
        List<TreeNode> res = new LinkedList<TreeNode>();
        if (start > end) {
            res.add(null);
            return res;
        }
        for (int i = start; i <= end; i++) {
            List<TreeNode> subLeftTree = generateTrees(start, i - 1);
            List<TreeNode> subRightTree = generateTrees(i + 1, end);
            for (TreeNode left : subLeftTree) {
                for (TreeNode right : subRightTree) {
                    TreeNode node = new TreeNode(i);
                    node.left = left;
                    node.right = right;
                    res.add(node);
                }
            }
        }
        return res;
    }

    private void dfsTees(List<TreeNode> rsp, TreeNode temp, int n, int k) {
        if (k > n) {
            rsp.add(temp);
        }

    }

    /**
     * 中序遍历二叉树(非递归版本)
     *
     * @param root
     * @return
     */
    public static List<Integer> inorderTraversal2(TreeNode root) {
        List<Integer> rsp = new ArrayList<>();
        if (root == null) {
            return rsp;
        }
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        while (cur != null || !stack.isEmpty()) {
            if (cur != null) {
                stack.push(cur);
                cur = cur.left;
            } else {
                cur = stack.pop();
                rsp.add(cur.val);
                cur = cur.right;
            }
        }
        return rsp;
    }

    /**
     * 中序遍历二叉树
     *
     * @param root
     * @return
     */
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> rsp = new ArrayList<>();
        dfsInOrderTree(root, rsp);
        return rsp;
    }

    private void dfsInOrderTree(TreeNode root, List<Integer> rsp) {
        if (root == null) {
            return;
        }
        if (root.left != null) {
            dfsInOrderTree(root.left, rsp);
        }
        rsp.add(root.val);
        if (root.right != null) {
            dfsInOrderTree(root.right, rsp);
        }
    }


    static int pathNumber = 0;

    /**
     * 所有总和为sum的路径
     *
     * @param root
     * @param sum
     * @return
     */
    public static int pathSum(TreeNode root, int sum) {
        //用双重递归，先遍历所有节点removeElement
        if (root != null) {
            dfsPathSum(root, sum);
            pathSum(root.left, sum);
            pathSum(root.right, sum);
        }
        return pathNumber;
    }

    private static void dfsPathSum(TreeNode root, int sum) {
        if (root != null) {
            sum -= root.val;
            if (sum == 0) {
                pathNumber++;
            }
            dfsPathSum(root.left, sum);
            dfsPathSum(root.right, sum);
        }
    }

    /**
     * 左叶子之和
     *
     * @param ransomNote
     * @param magazine
     * @return
     */
    public boolean canConstruct(String ransomNote, String magazine) {
        if (ransomNote == null || magazine == null) {
            return false;
        }
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < magazine.length(); i++) {
            char c = magazine.charAt(i);
            if (map.containsKey(c)) {
                map.put(c, map.get(c) + 1);
            } else {
                map.put(c, 1);
            }
        }
        for (int i = 0; i < ransomNote.length(); i++) {
            char c = ransomNote.charAt(i);
            if (!map.containsKey(c) || map.get(c) == 0) {
                return false;
            } else {
                map.put(c, map.get(c) - 1);
            }
        }
        return true;
    }

    /**
     * 左叶子之和
     *
     * @param root
     * @return
     */
    static int total = 0;

    public static int sumOfLeftLeaves(TreeNode root) {
        if (root != null) {
            if (root.left != null) {
                total += root.left.val;
            }
            sumOfLeftLeaves(root.left);
            sumOfLeftLeaves(root.right);
        }

        return total;
    }

    public List<String> binaryTreePaths(TreeNode root) {
        List<String> list = new ArrayList<>();
        if (root == null) {
            return list;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(root.val);
        dfs(root.left, list, sb);
        sb.delete(0, sb.length());
        sb.append(root.val);
        dfs(root.right, list, sb);
        return list;
    }

    private void dfs(TreeNode root, List<String> list, StringBuilder sb) {
        if (root != null) {
            sb.append("->").append(root.val);
            if (root.left == null && root.right == null) {
                list.add(sb.toString());
            }
            StringBuilder secondSb = new StringBuilder(sb.toString());
            dfs(root.left, list, sb);
            dfs(root.right, list, secondSb);
        }
    }

    public static List<List<Integer>> levelOrderBottom(TreeNode root) {
        //二叉树的层次遍历，需要依赖队列
        List<List<Integer>> rsp = new ArrayList<>();
        if (root == null) {
            return rsp;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        //由于要计算层级，我们要先预设每一层的队列数量进行循环
        int loop = 1;
        while (!queue.isEmpty()) {
            loop = queue.size();
            List<Integer> list = new ArrayList<>();
            while (loop > 0) {
                TreeNode node = queue.poll();
                list.add(node.val);
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
                loop--;
            }
            rsp.add(list);
        }
        Collections.reverse(rsp);
        return rsp;
    }

    /**
     * 获取第二小的值
     * 这颗二叉树很特殊，叶子节点不小于根节点，且如果有子节点，就必然有两个子节点
     *
     * @param root
     * @return
     */
    public static int findSecondMinimumValue(TreeNode root) {
        //后续遍历
        if (root == null) {
            return -1;
        }
        TreeSet<Integer> list = new TreeSet<>();
        dfsTree(root, list);
        if (list.first() == list.last()) {
            return -1;
        }
        list.pollFirst();
        return list.first();
    }

    public static void dfsTree(TreeNode root, TreeSet<Integer> list) {
        if (root != null) {
            dfsTree(root.left, list);
            dfsTree(root.right, list);
            list.add(root.val);
        }
    }

    static int maxL = 0;

    /**
     * 最长同值路径
     *
     * @param root
     * @return
     */
    public static int longestUnivaluePath(TreeNode root) {
        dfsSameRoute(root, 0);
        return maxL;
    }

    public static int dfsSameRoute(TreeNode root, int val) {
        if (root == null) {
            return 0;
        }
        int left = dfsSameRoute(root.left, root.val);
        int right = dfsSameRoute(root.right, root.val);
        maxL = Math.max(maxL, left + right);
        if (root.val == val) {
            return maxL + 1;
        }

        return 0;
    }

    /**
     * n叉树的层序遍历
     *
     * @param root
     * @return
     */
    public static List<List<Integer>> levelOrder(Node root) {
        if (root == null) {
            return new ArrayList<>();
        }
        List<List<Integer>> rsp = new ArrayList<>();
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int n = queue.size();
            List<Integer> list = new ArrayList<>();
            while (n > 0) {
                Node node = queue.poll();
                list.add(node.val);
                //给队列添加新元素
                if (node.children != null) {
                    for (Node item : node.children) {
                        queue.add(item);
                    }
                }
                n--;
            }
            rsp.add(list);
        }
        return rsp;
    }

    /**
     * 二叉树层序遍历
     *
     * @param root
     * @return
     */
    public static List<List<Integer>> levelOrder(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        List<List<Integer>> rsp = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int n = queue.size();
            List<Integer> list = new ArrayList<>();
            while (n > 0) {
                TreeNode node = queue.poll();
                list.add(node.val);
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
                n--;
            }
            rsp.add(list);
        }
        return rsp;
    }

    static int rspDep = 0;

    /**
     * n叉树的最大深度
     *
     * @param root
     * @return
     */
    public static int maxDepth(Node root) {
        if (root != null) {
            if (root.children != null) {
                boolean onlyOnce = true;
                for (Node item : root.children) {
                    int tempDep = 0;
                    if (onlyOnce) {
                        tempDep = maxDepth(item) + 1;
                        onlyOnce = false;
                    } else {
                        tempDep = maxDepth(item);
                    }
                    rspDep = Math.max(rspDep, tempDep);
                }
            }
        } else {
            return 0;
        }
        return rspDep;
    }

    static List<Integer> rsp;

    /**
     * N叉树的前序遍历
     *
     * @param root
     * @return
     */
    public List<Integer> preOrder(Node root) {
        if (root != null) {
            rsp.add(root.val);
            if (root.children != null) {
                for (Node item : root.children) {
                    preOrder(item);
                }
            }

        }
        return rsp;
    }

    /**
     * n叉树的后序遍历
     *
     * @param root
     * @return
     */
    public static List<Integer> postorder(Node root) {
        List<Integer> rsp = new ArrayList<>();
        rsp = nTree(root, rsp);
        return rsp;
    }

    public static List<Integer> nTree(Node root, List<Integer> rsp) {
        if (root != null) {
            if (root.children != null) {
                for (Node item : root.children) {
                    rsp = nTree(item, rsp);
                }
            }
            rsp.add(root.val);
        }
        return rsp;
    }

    /**
     * 二叉搜索树中的搜索
     *
     * @param root
     * @param val
     * @return
     */
    public static TreeNode searchBST(TreeNode root, int val) {
        if (root != null) {
            if (root.val > val) {
                return searchBST(root.left, val);
            } else if (root.val < val) {
                return searchBST(root.right, val);
            } else {
                return root;
            }
        }
        return null;
    }

    /**
     * 叶子相似的树
     *
     * @param root1
     * @param root2
     * @return
     */
    public static boolean leafSimilar(TreeNode root1, TreeNode root2) {
        // 后序遍历叶子节点，拿到节点值
        List<Integer> list1 = new ArrayList<>();
        list1 = getLeaf(root1, list1);

        List<Integer> list2 = new ArrayList<>();
        list2 = getLeaf(root2, list2);
        return list1.equals(list2);
    }

    public static List<Integer> getLeaf(TreeNode root, List<Integer> rsp) {
        if (root != null) {
            rsp = getLeaf(root.left, rsp);
            rsp = getLeaf(root.right, rsp);
            if (root.left == null && root.right == null) {
                rsp.add(root.val);
            }
        }
        return rsp;
    }

    /**
     * 递增顺序查找树
     *
     * @param root
     * @return
     */
    public static TreeNode increasingBST(TreeNode root) {
        TreeNode rsp = new TreeNode(0);
        TreeNode head = rsp;
        func(root, rsp);
        return head.right;

    }

    public static TreeNode func(TreeNode root, TreeNode rsp) {
//        if (root != null) {
//            rsp = func(root.left, rsp);
//            rsp.right = new TreeNode(root.val);
//            rsp = rsp.right;
//            rsp = func(root.right, rsp);
//        }
//        return rsp;
        //换一种写法试试
        if (root == null) {
            return rsp;
        } else {
            rsp = func(root.left, rsp);
            rsp.right = new TreeNode(root.val);
            rsp = rsp.right;
            rsp = func(root.right, rsp);
            return rsp;
        }
    }

    /**
     * 是否为单值二叉树
     *
     * @param root
     * @return
     */
    public static boolean isUnivalTree(TreeNode root) {
//        boolean left_same = (root.left == null ||
//                (root.left.val == root.val && isUnivalTree(root.left)));
//
//        boolean right_same = (root.right == null ||
//                (root.right.val == root.val && isUnivalTree(root.right)));
//        return left_same && right_same;

        //深度优先遍历，将节点都取出来
        values = new ArrayList<>();
        f(root);
        for (int i = 0; i < values.size() - 1; i++) {
            if (values.get(i) != values.get(i + 1)) {
                return false;
            }
        }
        return true;
    }

    static List<Integer> values;

    private static void f(TreeNode root) {
        if (root != null) {
            values.add(root.val);
            f(root.left);
            f(root.right);
        }
    }

    public static int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        } else {
            int left_height = maxDepth(root.left);
            int right_height = maxDepth(root.right);
            return Math.max(left_height, right_height) + 1;
        }
    }

    public static int getTreeDepth() {
//        Queue<Pair<TreeNode, Integer>> stack = new LinkedList<>();
//        if (root != null) {
//            stack.add(new Pair(root, 1));
//        }
//
//        int depth = 0;
//        while (!stack.isEmpty()) {
//            Pair<TreeNode, Integer> current = stack.poll();
//            root = current.getKey();
//            int current_depth = current.getValue();
//            if (root != null) {
//                depth = Math.max(depth, current_depth);
//                stack.add(new Pair(root.left, current_depth + 1));
//                stack.add(new Pair(root.right, current_depth + 1));
//            }
//        }
        return 0;
    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public static class Node {
        public int val;
        public List<Node> children;

        public Node() {
        }

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    }

    public static class Node1 {
        public int val;
        public Node1 next;
        public Node1 random;

        public Node1() {
        }

        public Node1(int _val, Node1 _next, Node1 _random) {
            val = _val;
            next = _next;
            random = _random;
        }
    }
}
