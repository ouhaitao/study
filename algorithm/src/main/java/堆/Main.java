package 堆;

/**
 * @author parry
 * @date 2021/06/10
 */
public class Main {
    
    /**
     * 堆操作只有两种 上滤(siftUp)与下滤(siftDown)
     * 两种操作都是用于维持堆性质的
     * 一般将上滤用于插入操作
     * 下滤用于删除操作
     * 两种操作都能用于建堆
     *
     */
    
    /**
     * 插入值 上滤操作
     * 这是一个大顶堆
     * 将新值插入到数组末尾 然后向上遍历 找到父节点大于该值的位置 即为插入位置
     *
     * @param newValue 值
     * @param heap     堆
     * @param heapSize 堆大小
     */
    private static void insert(int newValue, int[] heap, int heapSize) {
        if (heapSize + 1 > heap.length) {
            throw new IllegalArgumentException("head is full");
        }
        heap[heapSize] = newValue;
        int i = heapSize;
        for (; i > 0; i = parent(i)) {
            int parent = parent(i);
            if (heap[parent] < newValue) {
                heap[i] = heap[parent];
            } else {
                break;
            }
        }
        heap[i] = newValue;
    }
    
    /**
     * 删除堆顶元素 下滤操作
     * 将堆顶元素与数组最后一个元素交换 然后删除该元素 然后从堆顶向下遍历
     *
     * @param heap     堆
     * @param heapSize 堆大小
     */
    private static int delete(int[] heap, int heapSize) {
        if (heapSize == 0) {
            throw new IllegalArgumentException("heap is empty");
        }
        int res = heap[0];
        heap[0] = heap[heapSize - 1];
        int i = 0;
        for (; leftChild(i) < heapSize; i = leftChild(i)) {
            int child = leftChild(i);
            if ((child != heapSize - 1) && (heap[child] < heap[child + 1])) {
                child++;
            }
            if (heap[child] > heap[heapSize - 1]) {
                heap[i] = heap[child];
            } else {
                break;
            }
        }
        heap[i] = heap[heapSize - 1];
        return res;
    }
    
    private static int parent(int index) {
        return index >> 1;
    }
    
    private static int leftChild(int index) {
        return 2 * index + 1;
    }
    
    public static void main(String[] args) {
        int[] heap = {10, 9, 8, 7, 6, 5, 4, 0};
        int heapSize = heap.length - 1;
        insert(11, heap, heapSize);
        heapSize++;
        forEach(heap, heapSize);
        System.out.println(delete(heap, heap.length));
        heapSize--;
        forEach(heap, heapSize);
    }
    
    private static void forEach(int[] heap, int heapSize) {
        for (int i = 0; i < heapSize; i++) {
            System.out.print(heap[i] + " ");
        }
        System.out.println();
    }
}
