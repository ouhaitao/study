/**
 * @author parry
 * @date 2020/04/13
 */
public class Main {
    
    
    private static class LRUCache {
        
        private static class Node {
            private int key;
            private int value;
            private Node next;
            private Node pre;
        }
        
        private final int capacity;
        
        private int size;
        
        private Node head;
        
        private Node tail;
        
        public LRUCache(int capacity) {
            this.capacity = capacity;
            head = null;
            size = 0;
        }
        
        public int get(int key) {
            if (head == null) {
                return -1;
            }
            Node contain = contain(key);
            if (contain != null) {
                int value = contain.value;
                if (head != contain) {
                    moveToHead(contain);
                }
                return value;
            }
            return -1;
        }
        
        public void put(int key, int value) {
            Node contain = contain(key);
            if (contain != null) {
                contain.value = value;
                if (head != contain) {
                    moveToHead(contain);
                }
                return;
            }
            boolean full = false;
            if (size < capacity) {
                size++;
            } else {
                full = true;
            }
            Node node = new Node();
            node.key = key;
            node.value = value;
            if (head == null) {
                head = node;
                tail = head;
            } else {
                if (full) {
                    if (head == tail) {
                        head = node;
                        tail = head;
                        return;
                    }
                    tail = tail.pre;
                    tail.next = null;
                }
                node.next = head;
                head.pre = node;
                head = node;
            }
        }
        
        private Node contain(int key) {
            Node current = head;
            while (current != null) {
                if (current.key == key) {
                    return current;
                }
                current = current.next;
            }
            return null;
        }
        
        private void moveToHead(Node node) {
            if (size == 1) {
            
            } else if (size == 2) {
                int tmpKey = head.key;
                int tmpValue = head.value;
                head.key = node.key;
                head.value = node.value;
                node.key = tmpKey;
                node.value = tmpValue;
            }else {
                if (head == node) {
                    return;
                }
                if (tail == node) {
                    Node tmp = tail;
                    tail = tail.pre;
                    tail.next = null;
                    tmp.next = head;
                    tmp.pre = null;
                    head.pre = tmp;
                    head = tmp;
                    return;
                }
                node.pre.next = node.next;
                node.next.pre = node.pre;
                node.next = head;
                node.pre = null;
                head.pre = node;
                head = node;
            }
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
        LRUCache cache = new LRUCache(1 );
    
        cache.put(2, 1);
        System.out.println(cache.get(2));
        cache.put(3, 2);
        System.out.println(cache.get(2));
        System.out.println(cache.get(3));
//        1 -1 2
        System.out.println("------------------------");
    }
    
    private static void fun3() {
        LRUCache cache = new LRUCache(2);
    
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
        LRUCache cache = new LRUCache(3);
    
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
}
