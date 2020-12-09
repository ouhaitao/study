package 一些问题;

import java.util.HashMap;

/**
 * @author parry
 * @date 2020/06/04
 */
@SuppressWarnings("AlibabaClassNamingShouldBeCamel")
public class O1复杂度实现LRU {

    private static class LRUCache {
        
        private DeQueue queue;
        
        private int capacity;
        
        private int size;
        
        private HashMap<Integer, DeQueue.Node> cache;
        
        /**
         * 双端队列
         */
        private static class DeQueue {
            
            private Node head;
            
            private Node tail;
            
            public DeQueue() {
                head = new Node();
                tail = new Node();
                head.next = tail;
                tail.pre = head;
            }
            
            private static class Node {
                private Integer key;
                private Integer value;
                private Node next;
                private Node pre;
            }
            
            /**
             * 入队
             */
            void offerFirst(int key, int value) {
                Node newNode = new Node();
                newNode.key = key;
                newNode.value = value;
                newNode.next = head.next;
                newNode.pre = head;
                head.next.pre = newNode;
                head.next = newNode;
            }
            
            void offerFirst(Node node) {
                node.next = head.next;
                node.pre = head;
                head.next.pre = node;
                head.next = node;
            }
            
            /**
             * 出队
             */
            Integer poll(int key) {
                Node node = internalPeek(key);
                if (node == null) {
                    return null;
                }
                node.pre.next = node.next;
                node.next.pre = node.pre;
                return node.value;
            }
            
            Integer poll(Node node) {
                if (node == null) {
                    return null;
                }
                node.pre.next = node.next;
                node.next.pre = node.pre;
                return node.value;
            }
            
            Integer pollLast() {
                if (tail.pre == head) {
                    return null;
                }
                Integer value = tail.pre.value;
                tail.pre = tail.pre.pre;
                tail.pre.next = tail;
                return value;
            }
    
            /**
             * 查找key
             */
            private Node internalPeek(int key) {
                Node h = head.next;
                while (h != tail) {
                    if (h.key == key) {
                        return h;
                    }
                    h = h.next;
                }
                return null;
            }
            
            private Node getTail() {
                return tail.pre;
            }
        }
        
        public LRUCache(int capacity) {
            this.capacity = capacity;
            size = 0;
            queue = new DeQueue();
            cache = new HashMap<>(capacity);
        }
        
        public int get(int key) {
            Integer value = queue.poll(key);
            if (value != null) {
                queue.offerFirst(key, value);
                return value;
            }
            return -1;
        }
    
        /**
         * 时间复杂度O(1)
         */
        public int getFast(int key) {
            DeQueue.Node node = cache.get(key);
            if (node == null) {
                return -1;
            }
            queue.poll(node);
            queue.offerFirst(node);
            return node.value;
        }
        
        public void put(int key, int value) {
            if (queue.poll(key) == null) {
                if (size < capacity) {
                    size++;
                } else {
                    queue.pollLast();
                }
            }
            queue.offerFirst(key, value);
        }
    
        /**
         * 时间复杂度O(1)
         */
        public void putFast(int key, int value) {
            DeQueue.Node node = cache.get(key);
            if (node == null) {
                node = new DeQueue.Node();
                node.key = key;
                node.value = value;
                if (size < capacity) {
                    size++;
                }else {
                    cache.remove(queue.getTail().key);
                    queue.pollLast();
                }
                cache.put(key, node);
            }else {
                queue.poll(node);
                node.value = value;
            }
            queue.offerFirst(node);
        }
    }
    
    public static void main(String[] args) {
        fun1();
        fun2();
        fun3();
        fun4();
    }
    
    private static void fun1() {
        LRUCache cache = new LRUCache(2);
        
        cache.putFast(1, 1);
        cache.putFast(2, 2);
        System.out.println(cache.getFast(1));
        cache.putFast(3, 3);
        System.out.println(cache.getFast(2));
        cache.putFast(4, 4);
        System.out.println(cache.getFast(1));
        System.out.println(cache.getFast(3));
        System.out.println(cache.getFast(4));
//        1 -1 -1 3 4
        System.out.println("------------------------");
    }
    
    private static void fun2() {
        LRUCache cache = new LRUCache(1);
        
        cache.putFast(2, 1);
        System.out.println(cache.getFast(2));
        cache.putFast(3, 2);
        System.out.println(cache.getFast(2));
        System.out.println(cache.getFast(3));
//        1 -1 2
        System.out.println("------------------------");
    }
    
    private static void fun3() {
        LRUCache cache = new LRUCache(2);
        
        System.out.println(cache.getFast(2));
        cache.putFast(2, 6);
        System.out.println(cache.getFast(1));
        cache.putFast(1, 5);
        cache.putFast(1, 2);
        System.out.println(cache.getFast(1));
        System.out.println(cache.getFast(2));
//        -1 -1 2 6
        System.out.println("------------------------");
    }
    
    private static void fun4() {
        LRUCache cache = new LRUCache(3);
        
        cache.putFast(1, 1);
        cache.putFast(2, 2);
        cache.putFast(3, 3);
        cache.putFast(4, 4);
        System.out.println(cache.getFast(4));
        System.out.println(cache.getFast(3));
        System.out.println(cache.getFast(2));
        System.out.println(cache.getFast(1));
        cache.putFast(5, 5);
        System.out.println(cache.getFast(1));
        System.out.println(cache.getFast(2));
        System.out.println(cache.getFast(3));
        System.out.println(cache.getFast(4));
        System.out.println(cache.getFast(5));
//        4 3 2 -1 -1 2 3 -1 5
        System.out.println("------------------------");
    }
    
    
//    错误示范
//    private static class LRUCache {
//
//        private static class TreeNode {
//            private int key;
//            private int value;
//            private TreeNode next;
//            private TreeNode pre;
//        }
//
//        private final int capacity;
//
//        private int size;
//
//        private TreeNode head;
//
//        private TreeNode tail;
//
//        public LRUCache(int capacity) {
//            this.capacity = capacity;
//            head = null;
//            size = 0;
//        }
//
//        public int get(int key) {
//            if (head == null) {
//                return -1;
//            }
//            TreeNode contain = contain(key);
//            if (contain != null) {
//                int value = contain.value;
//                if (head != contain) {
//                    moveToHead(contain);
//                }
//                return value;
//            }
//            return -1;
//        }
//
//        public void put(int key, int value) {
//            TreeNode contain = contain(key);
//            if (contain != null) {
//                contain.value = value;
//                if (head != contain) {
//                    moveToHead(contain);
//                }
//                return;
//            }
//            boolean full = false;
//            if (size < capacity) {
//                size++;
//            } else {
//                full = true;
//            }
//            TreeNode node = new TreeNode();
//            node.key = key;
//            node.value = value;
//            if (head == null) {
//                head = node;
//                tail = head;
//            } else {
//                if (full) {
//                    if (head == tail) {
//                        head = node;
//                        tail = head;
//                        return;
//                    }
//                    tail = tail.pre;
//                    tail.next = null;
//                }
//                node.next = head;
//                head.pre = node;
//                head = node;
//            }
//        }
//
//        private TreeNode contain(int key) {
//            TreeNode current = head;
//            while (current != null) {
//                if (current.key == key) {
//                    return current;
//                }
//                current = current.next;
//            }
//            return null;
//        }
//
//        private void moveToHead(TreeNode node) {
//            if (size == 1) {
//
//            } else if (size == 2) {
//                int tmpKey = head.key;
//                int tmpValue = head.value;
//                head.key = node.key;
//                head.value = node.value;
//                node.key = tmpKey;
//                node.value = tmpValue;
//            }else {
//                if (head == node) {
//                    return;
//                }
//                if (tail == node) {
//                    TreeNode tmp = tail;
//                    tail = tail.pre;
//                    tail.next = null;
//                    tmp.next = head;
//                    tmp.pre = null;
//                    head.pre = tmp;
//                    head = tmp;
//                    return;
//                }
//                node.pre.next = node.next;
//                node.next.pre = node.pre;
//                node.next = head;
//                node.pre = null;
//                head.pre = node;
//                head = node;
//            }
//        }
//    }
}
