package 一些问题;

import java.util.HashMap;

/**
 * @author parry
 * @date 2020/06/04
 */
@SuppressWarnings("AlibabaClassNamingShouldBeCamel")
public class O1复杂度实现LRU {
    
    public static void main(String[] args) {
        fun1();
        fun2();
        fun3();
        fun4();
    }
    
    private static void fun1() {
        O1复杂度实现LRU cache = new O1复杂度实现LRU(2);
        
        cache.put(1, 1);
        cache.put(2, 2);
        System.out.println(cache.get(1));
        cache.put(3, 3);
        System.out.println(cache.get(2));
        cache.put(4, 4);
        System.out.println(cache.get(1));
        System.out.println(cache.get(3));
        System.out.println(cache.get(4));
//        1 -1 -1 3 4
        System.out.println("------------------------");
    }
    
    private static void fun2() {
        O1复杂度实现LRU cache = new O1复杂度实现LRU(1);
        
        cache.put(2, 1);
        System.out.println(cache.get(2));
        cache.put(3, 2);
        System.out.println(cache.get(2));
        System.out.println(cache.get(3));
//        1 -1 2
        System.out.println("------------------------");
    }
    
    private static void fun3() {
        O1复杂度实现LRU cache = new O1复杂度实现LRU(2);
        
        System.out.println(cache.get(2));
        cache.put(2, 6);
        System.out.println(cache.get(1));
        cache.put(1, 5);
        cache.put(1, 2);
        System.out.println(cache.get(1));
        System.out.println(cache.get(2));
//        -1 -1 2 6
        System.out.println("------------------------");
    }
    
    private static void fun4() {
        O1复杂度实现LRU cache = new O1复杂度实现LRU(3);
        
        cache.put(1, 1);
        cache.put(2, 2);
        cache.put(3, 3);
        cache.put(4, 4);
        System.out.println(cache.get(4));
        System.out.println(cache.get(3));
        System.out.println(cache.get(2));
        System.out.println(cache.get(1));
        cache.put(5, 5);
        System.out.println(cache.get(1));
        System.out.println(cache.get(2));
        System.out.println(cache.get(3));
        System.out.println(cache.get(4));
        System.out.println(cache.get(5));
//        4 3 2 -1 -1 2 3 -1 5
        System.out.println("------------------------");
    }
    
    private HashMap<Integer, Node> map;
    
    private DeQueue queue;
    
    private int capacity;
    
    public O1复杂度实现LRU(int capacity) {
        map = new HashMap<>(capacity);
        queue = new DeQueue();
        this.capacity = capacity;
    }
    
    public int get(int key) {
        Node node = map.get(key);
        if (node == null) {
            return -1;
        }
        queue.poll(node);
        queue.offerFirst(node);
        return node.value;
    }
    
    public void put(int key, int value) {
        Node old = map.get(key);
        Node node;
        if (old == null) {
            if (map.size() == capacity) {
                Node remove = queue.pollLast();
                map.remove(remove.key);
            }
            node = newNode(key, value);
        } else {
            queue.poll(old);
            map.remove(old.key);
            node = old;
            node.value = value;
        }
        queue.offerFirst(node);
        map.put(key, node);
    }
    
    private Node newNode(int key, int value) {
        Node node = new Node();
        node.key = key;
        node.value = value;
        return node;
    }
    
    private class Node {
        private Integer key;
        private Integer value;
        private Node next;
        private Node pre;
        
    }
    
    private class DeQueue {
        
        private Node head;
        
        private Node tail;
        
        public DeQueue() {
            head = new Node();
            tail = new Node();
            head.next = tail;
            tail.pre = head;
        }
        
        /**
         * 入队
         */
        void offerFirst(Node node) {
            node.next = head.next;
            node.pre = head;
            head.next.pre = node;
            head.next = node;
        }
        
        
        /**
         * 出队
         */
        Node poll(Node node) {
            if (node == null) {
                return null;
            }
            node.pre.next = node.next;
            node.next.pre = node.pre;
            return node;
        }
        
        Node pollLast() {
            if (tail.pre == head) {
                return null;
            }
            Node remove = tail.pre;
            tail.pre = tail.pre.pre;
            tail.pre.next = tail;
            return remove;
        }
        
        @Override
        public String toString() {
            Node node = head;
            StringBuilder sb = new StringBuilder();
            while (node.next != tail) {
                sb.append(node.next.value).append("->");
                node = node.next;
            }
            return sb.toString();
        }
        
    }
}
