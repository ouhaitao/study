package 排序;

import java.util.Arrays;

/**
 * @author parry
 * @date 2020/05/18
 */
public class 桶排序 {
    
    public static void main(String[] args) {
        int[] data = {3, 44, 38, 5, 47, 15, 36, 26, 27, 2, 46, 4, 19, 50, 48};
        System.out.println(Arrays.toString(bucketSort(data, 5)));
    }
    
    /**
     * 桶排序是计数排序的升级版。它通过一个映射函数将数据映射到桶中，然后通过其他排序算法对每个桶进行排序。为了使桶排序更加高效，我们需要做到这两点：
     *  1、在额外空间充足的情况下，尽量增大桶的数量
     *  2、使用的映射函数能够将输入的 N 个数据均匀的分配到 K 个桶中
     *  3、排序算法合理
     *
     *  1. 什么时候最快
     *  当输入的数据可以均匀的分配到每一个桶中。
     *
     *  2. 什么时候最慢
     *  当输入的数据被分配到了同一个桶中。
     */
    private static int[] bucketSort(int[] data, int bucketSize) {
        int minValue = data[0];
        int maxValue = data[0];
        for (int value : data) {
            if (value < minValue) {
                minValue = value;
            } else if (value > maxValue) {
                maxValue = value;
            }
        }
        int[][] buckets = createBucket(bucketSize);
    
        int interval = (int) (Math.floor(maxValue - minValue) / bucketSize + 1);
        for (int datum : data) {
            int index = (datum - minValue) / interval;
            pushBucket(buckets, datum, index);
        }
    
        int dataIndex = 0;
        for (int[] bucket : buckets) {
            if (bucket.length <= 0) {
                continue;
            }
            插入排序.insertSort(bucket);
            for (int value : bucket) {
                data[dataIndex++] = value;
            }
        }
        return data;
    }
    
    private static void pushBucket(int[][] bucket, int value, int index) {
        bucket[index] = arrayAppend(bucket[index], value);
    }
    
    private static int[][] createBucket(int bucketSize) {
        return new int[bucketSize][0];
    }
    
    /**
     * 自动扩容，并保存数据
     * 可用链表替换
     */
    private static int[] arrayAppend(int[] arr, int value) {
        arr = Arrays.copyOf(arr, arr.length + 1);
        arr[arr.length - 1] = value;
        return arr;
    }
}
