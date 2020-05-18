package leetcode;

/**
 * 双向链表
 */
public class DoubleList {
    private  Node head, tail;
    private int size;

    public DoubleList(){
        head = new Node(0, 0);
        tail = new Node(0, 0);
        head.next = tail;
        tail.prev = head;
        size = 0;
    }

    public void addFirst(Node x){
        x.next = head.next;
        x.prev = head.prev;
        head.next.prev = x;
        head.next = x;
        size++;
    }

    public void remove(Node x){
        x.prev.next = x.next;
        x.next.prev = x.prev;
        size--;
    }

    public Node removeLast(){
        if(tail.prev == head){
            return null;
        }
        Node last = tail.prev;
        remove(last);
        return last;
    }

    public int size(){
        return size;
    }

    public static class Node{
        public int key, val;
        public Node next, prev;
        public Node(int k, int v){
            this.key = k;
            this.val = v;
        }
    }
}
