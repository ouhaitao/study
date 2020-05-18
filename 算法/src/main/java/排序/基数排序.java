package 排序;

import java.util.Arrays;

/**
 * @author parry
 * @date 2020/05/18
 */
public class 基数排序 {
    
    public static void main(String[] args) {
        int[] data = {3, 44, 38, 5, 47, 15, 36, 26, 27, 2, 46, 4, 19, 50, 48};
        System.out.println(Arrays.toString(radixSort(data, getMaxDigit(data))));
    }
    
    /**
     * 基数排序是一种非比较型整数排序算法，其原理是将整数按位数切割成不同的数字，然后按每个位数分别比较。
     * 由于整数也可以表达字符串（比如名字或日期）和特定格式的浮点数，所以基数排序也不是只能使用于整数。
     *
     * 基数排序的桶根据键值来存储数据
     */
    private static int[] radixSort(int[] data, int maxDigit) {
        int mod = 10;
        int dev = 1;
        
        for (int i = 0; i < maxDigit; i++, dev *= 10, mod *= 10) {
//            这里只考虑正数，没考虑负数
            int[][] counter = new int[mod][0];
            
            for (int j = 0; j < data.length; j++) {
                int bucket = ((data[j] % mod) / dev);
                counter[bucket] = arrayAppend(counter[bucket], data[j]);
            }
            
            int pos = 0;
            for (int[] bucket : counter) {
                for (int value : bucket) {
                    data[pos++] = value;
                }
            }
        }
        
        return data;
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
    
    /**
     * 获取最高位数
     */
    private static int getMaxDigit(int[] arr) {
        int maxValue = getMaxValue(arr);
        return getNumLength(maxValue);
    }
    
    private static int getMaxValue(int[] arr) {
        int maxValue = arr[0];
        for (int value : arr) {
            if (maxValue < value) {
                maxValue = value;
            }
        }
        return maxValue;
    }
    
    private static int getNumLength(long num) {
        if (num == 0) {
            return 1;
        }
        int lenght = 0;
        for (long temp = num; temp != 0; temp /= 10) {
            lenght++;
        }
        return lenght;
    }
}
