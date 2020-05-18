package 排序;

import java.util.Arrays;

/**
 * @author parry
 * @date 2020/05/18
 */
public class 计数排序 {
    
    public static void main(String[] args) {
        int[] data = {2, 3, 8, 7, 1, 2, 2, 2, 7, 3, 9, 8, 2, 1, 4, 2, 4, 6, 9, 2};
        System.out.println(Arrays.toString(countingSort(data, 10, 0)));
        for (int i = 0; i < data.length; i++) {
            data[i] += 90;
        }
        System.out.println(Arrays.toString(countingSort(data, 100, 90)));
    }
    
    /**
     * 非比较排序，其速度快起其他比较排序，但是需要明确知道数据的范围
     * 时间复杂度 O(n+k) n是数组个数，k是（max-min+1）抹去常数阶1之后的结果即（max-min）
     * 空间复杂度 O(k)
     *
     * 计数排序也有桶的概念，只是一个桶只存一个数据
     */
    private static int[] countingSort(int[] data, int max, int min) {
        int[] bucket = new int[max - min + 1];
        for (int i = 0; i < data.length; i++) {
            bucket[data[i] - min]++;
        }
        int k = 0;
        for (int i = 0; i < bucket.length; i++) {
            for (int j = 0; j < bucket[i]; j++) {
                data[k++] = i + min;
            }
        }
        return data;
    }
}
