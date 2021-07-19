package 动态规划;

import 排序.快速排序;

import java.util.Arrays;

/**
 * @author parry
 * @date 2021/07/15
 */
public class 减小和重新排列数组后的最大元素 {
    
    public static void main(String[] args) {
        int[] arr = new int[10001];
        Arrays.fill(arr, 209);
        arr[10000] = 10000;
        System.out.println(maximumElementAfterDecrementingAndRearranging(arr));
    }
    
    public static int maximumElementAfterDecrementingAndRearranging(int[] arr) {
        if (arr.length == 1) {
            return 1;
        }
        arr = sort(arr, 0, arr.length - 1);
//        Arrays.sort(arr);
        arr[0] = 1;
        int max = 1;
        for (int i = 1; i < arr.length; i++) {
            int tmp = Math.min(i + 1, arr[i - 1] + 1);
            if (arr[i] > tmp) {
                arr[i] = tmp;
            }
            max = Math.max(max, arr[i]);
        }
        return max;
    }
    
    public static int[] sort(int[] data, int start, int end) {
        if (end <= start) {
            return data;
        }
        int partition = partition(data, start, end);
        sort(data, start, partition - 1);
        sort(data, partition + 1, end);
        return data;
    }
    
    public static int partition(int[] data, int start, int end) {
        int key = data[end];
        int i = start;
        for (int j = start; j < end; j++) {
            if (data[j] < key) {
                int tmp = data[i];
                data[i] = data[j];
                data[j] = tmp;
                i++;
            }
        }
        int tmp = data[i];
        data[i] = data[end];
        data[end] = tmp;
        return i;
    }
}
