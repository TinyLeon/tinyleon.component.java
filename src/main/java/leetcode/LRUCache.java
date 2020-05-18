package leetcode;

import java.util.HashMap;

public class LRUCache {
    private HashMap<Integer, DoubleList.Node> map;
    private DoubleList cache;
    private int capacity;

    public LRUCache(int capacity) {
        map = new HashMap<>();
        cache = new DoubleList();
        this.capacity = capacity;
    }

    public int get(int key) {
        if(!map.containsKey(key)){
            return -1;
        }
        int val = map.get(key).val;
        put(key, val);
        return val;
    }

    public void put(int key, int value) {
        //做新节点
        DoubleList.Node x = new DoubleList.Node(key, value);
        if(map.containsKey(key)){
            cache.remove(map.get(key));
        }else{
            if(cache.size() == capacity){
                DoubleList.Node last = cache.removeLast();
                map.remove(last.key);
            }
        }
        cache.addFirst(x);
        map.put(key, x);
    }

    public static void main(String[] args) {
        LRUCache cache = new LRUCache( 2 /* 缓存容量 */ );

        cache.put(1, 1);
        cache.put(2, 2);
        cache.get(1);       // 返回  1
        cache.put(3, 3);    // 该操作会使得密钥 2 作废
        cache.get(2);       // 返回 -1 (未找到)
        cache.put(4, 4);    // 该操作会使得密钥 1 作废
        cache.get(1);       // 返回 -1 (未找到)
        cache.get(3);       // 返回  3
        cache.get(4);       // 返回  4
    }
}
