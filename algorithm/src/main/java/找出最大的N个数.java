import java.util.Arrays;

public class 找出最大的N个数 {
    
    public static void main(String[] args) {

//        int n=10000;
//        int[] data=new int[n];
//        for (int i=0;i<n;i++){
//            data[i]= (int) (Math.random()*n);
//        }
        int[] data = {0, 2, 3, 4, 4, 4, 4, 5, 6, 6};
        Arrays.sort(data);
        Arrays.stream(data).forEach(x -> System.out.print(x + " "));
        System.out.println();
        Arrays.stream(findMaxInt(data, 3)).forEach(x -> System.out.print(x + " "));
    }
    
    /**
     * @param data 数据
     * @param n    前n个最大的数
     * @return 这里如果使用数组的话, 在进行插入排序时可能会有额外的开销(在数组中某个地方插入一个元素, 会导致后面的所有元素向后移动)
     * 所以使用链表,这里也可以用使用数组,一开始将前n个数据录入到结果数组中,然后进行一次排序即可,但是这样会有额外的排序开销.
     */
    public static int[] findMaxInt(int[] data, int n) {
        int length = data.length;
        if (length < n) {
            return null;
        }
        node fail = new node();
        node head = fail;
        fail.data = data[0];
        int count = 1;
        for (int i = 1; i < length; i++) {
            //插入排序算法
            node node = fail;
            while (node != null && node.data < data[i]) {
                node = node.pre;
            }
            //待插入节点
            node tmp = new node();
            tmp.data = data[i];
            //当链表数据不足n个时
            if (count != n) {
                //如果node==nul表示当前元素大于链表中最大的数即头结点
                if (node == null) {
                    tmp.next = head;
                    head.pre = tmp;
                    head = tmp;
                } else {
                    node next = node.next;
                    //node==fail表示当前节点小于链表中最小的数即尾节点
                    if (node == fail) {
                        fail = tmp;
                    } else {//当前元素插入位置在链表中
                        tmp.next = next;
                        next.pre = tmp;
                    }
                    tmp.pre = node;
                    node.next = tmp;
                }
                count++;
            } else if (node != fail) {//当count!=n且node!=fail表示链表长度已达到最大值
                if (node == null) {//如果node==nul表示当前元素大于链表中最大的数即头结点
                    tmp.next = head;
                    head.pre = tmp;
                    head = tmp;
                } else {//当前元素插入位置在链表中
                    tmp.next = node.next;
                    tmp.pre = node;
                    node.next = tmp;
                    tmp.next.pre = tmp;
                }
                fail = fail.pre;
                fail.next = null;
            }
        }
        
        int[] result = new int[n];
        
        for (int i = n - 1; i >= 0; i--) {
            result[i] = fail.data;
            fail = fail.pre;
        }
        
        return result;
    }
    
    static class node {
        int data;
        node pre = null;
        node next = null;
    }
}
