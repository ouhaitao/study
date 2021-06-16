package 排序;

/**
 * 堆排序的核心是建堆
 * 建堆有两种方法 https://www.cnblogs.com/ZeroTensor/p/10559876.html
 * 第一种HeapInsert很好理解 将数组元素一个一个插入堆即可 也就是使用上滤建堆
 * 第二种Heapify是一种分治的思想 其使用下滤建堆：
 * 如果一个堆root节点的左子堆与右子堆都满足堆性质，那么只要对root节点进行一次下滤操作就能
 * 那么我们从堆的最下层的不可再分的子堆（只包含3个节点）开始，向上开始依次执行一次下滤操作，直到root节点，那么便完成了建堆
 * https://blog.csdn.net/qq508618087/article/details/53362999
 * HeapInsert复杂度为N*logN Heapify复杂度为N（后者时间复杂度计算比较复杂）
 */
public class 堆排序 {

    /**
     * 下滤操作
     *
     * @param a 操作的数组
     * @param i 下虑开始的位置
     * @param n 数组大小
     * 该堆是大顶堆
     */
    private static void siftDown(Integer[] a, Integer i, Integer n) {
        Integer child;
        Integer temp=a[i];

        for (; leftChild(i) < n; i = child) {
            //获取左儿子节点
            child = leftChild(i);
            //获取两个儿子节点中最大的节点
            if ((child != n - 1) && (a[child] < a[child + 1])) {
                child++;
            }
            //由于是大顶堆,所以父节点必须大于子节点,所以如果temp小于最大的子节点时,不满足堆序性质,空穴下虑
            if (temp < a[child]) {
                a[i] = a[child];
            } else {
                //反之,满足堆序性质
                break;
            }
        }
        a[i] = temp;
    }
    
    private static Integer leftChild(Integer i) {
        return 2 * i + 1;
    }

    public static void main(String[] args) {
        Integer[] a = { 26, 53, 41, 58, 59, 31, 97 };
//        length/2 ~ length都是叶子结点 不是堆 叶子结点与其父节点构成了最下层的最小堆
        for (Integer i = a.length / 2 - 1; i >= 0; i--) {
            siftDown(a, i, a.length);
        }
        forEach(a);
//        执行删除操作，最终数组就是一个有序数组
//        swap + siftDown就构成了删除堆顶元素的操作
        for (Integer i = a.length - 1; i > 0; i--) {
            swap(a, 0, i);
            siftDown(a, 0, i);
        }
        forEach(a);
    }
    
    private static void swap(Integer[] a, Integer i, Integer n) {
        Integer temp = a[i];
        a[i] = a[n];
        a[n] = temp;
    }
    
    private static void forEach(Integer[] a) {
        for (Integer integer : a) {
            System.out.print(integer + " ");
        }
        System.out.println();
    }
}
