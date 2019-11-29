package leetcode;

import leetcode.Test.ListNode;
/**
 * description
 *
 * @author cl10805
 * @date 2019-05-14 14:58
 */
public class MergeSortTest {

    public static void main(String[] args) {
        int[] array = new int[]{6, 5, 4, 3, 2, 1};
        mergeSort(array, 0, array.length - 1);
    }

    /**
     * 链表的归并排序
     * @param head
     * @return
     */
    public ListNode sortList(ListNode head) {
        return head==null?null :mergeSort(head);
    }
    private ListNode mergeSort(ListNode node){
        if(node.next==null){
            return node;
        }
        ListNode p=node, q=node, pre=null;
        while(q!=null &&q.next!=null){
            pre=p;//pre的作用是将链表截断为2个
            p=p.next;
            q=q.next.next;
        }
        pre.next=null;
        ListNode l=mergeSort(node);
        ListNode r=mergeSort(p);
        return merge(l,r);
    }
    private ListNode merge(ListNode l, ListNode r){
        ListNode dummyHead=new ListNode(0),cur=dummyHead;
        while(l!=null && r!=null){
            if(l.val<=r.val){
                cur.next=l;
                l=l.next;
            }else{
                cur.next=r;
                r=r.next;
            }
            cur=cur.next;
        }
        if(l!=null){
            cur.next=l;
        }
        if(r!=null){
            cur.next=r;
        }
        return dummyHead.next;
    }

    /**
     * 两路归并算法，两个排好序的子序列合并为一个子序列
     *
     * @param a
     * @param start
     * @param end
     */
    public static void mergeSort(int[] a, int start, int end) {
        if (start < end) {//当子序列中只有一个元素时结束递归
            int mid = (start + end) / 2;//划分子序列
            mergeSort(a, start, mid);//对左侧子序列进行递归排序
            mergeSort(a, mid + 1, end);//对右侧子序列进行递归排序
            merge(a, start, mid, end);//合并
        }
    }

    private static void merge(int[] a, int left, int mid, int right) {
        int[] tmp = new int[a.length];//辅助数组
        int p1 = left, p2 = mid + 1, k = left;//p1、p2是检测指针，k是存放指针

        while (p1 <= mid && p2 <= right) {
            if (a[p1] <= a[p2]) {
                tmp[k++] = a[p1++];
            } else {
                tmp[k++] = a[p2++];
            }
        }

        while (p1 <= mid) {
            tmp[k++] = a[p1++];//如果第一个序列未检测完，直接将后面所有元素加到合并的序列中
        }
        while (p2 <= right) {
            tmp[k++] = a[p2++];//同上
        }

        //复制回原素组
        for (int i = left; i <= right; i++) {
            a[i] = tmp[i];
        }

    }
}
