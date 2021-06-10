package 排序;

import java.util.Arrays;

import static 排序.快速排序.quickSort;

public class 归并排序 {
    
    public static void main(String[] args) {
        int[] data = {1, 2, 3};
        int[] data1 = {4, 5, 6, 7};
        int[] data2 = {7, 6, 5, 4, 3, 2, 1};
        int[] data3 = {4, 5, 6, 7, 1, 2, 3};
        mergeSort(data2, 0, data2.length - 1);
//        mergeArray(data2, 0, 3, 4, 6);
        Arrays.stream(data2).forEach(x -> System.out.print(x + " "));
    }
    
    /**
     *
     * @param data 有序列表
     * @param data1 有序列表
     * @return
     */
    private static int[] mergeSort(int[] data, int[] data1) {
        
        int length = data.length;
        int length1 = data1.length;
        data = quickSort(data, 0, length - 1);
        data1 = quickSort(data1, 0, length1 - 1);
        
        int[] res = new int[length + length1];
        int[] overageData;
        int i = 0;
        int j;
        int index = 0, index1 = 0;
        for (; ; i++) {
            if (data[index] < data1[index1]) {
                res[i] = data[index];
                index++;
                if (index == length) {
                    overageData = data1;
                    j = index1;
                    break;
                }
            } else {
                res[i] = data1[index1];
                index1++;
                if (index1 == length1) {
                    overageData = data;
                    j = index;
                    break;
                }
            }
        }
        for (; j < overageData.length; j++) {
            res[++i] = overageData[j];
        }
        return res;
    }
 
    private static void mergeSort(int[] data, int start, int end) {
        if (start == end) {
            return;
        }
        int mid = (start + end) / 2;
        mergeSort(data, start, mid);
        mergeSort(data, mid + 1, end);
        mergeArray(data, start, mid, mid + 1, end);
    }
    
    /**
     *
     * @param data 需要排序的数组
     * @param start1 开始index
     * @param end1 结束index(有效)
     * @param start2 开始index
     * @param end2 结束index(有效)
     */
    private static void mergeArray(int[] data, int start1, int end1, int start2, int end2) {
        int[] res = new int[end1 - start1 + end2 - start2 + 2];
        int i = 0, tmp, overDataStart, overDataEnd;
        while (true) {
            if (start1 == (end1 + 1)) {
                overDataStart = start2;
                overDataEnd = end2;
                break;
            }else if (start2 == (end2 + 1)){
                overDataStart = start1;
                overDataEnd = end1;
                break;
            }
            if (data[start1] < data[start2]) {
                tmp = start1++;
            }else {
                tmp = start2++;
            }
            res[i++] = data[tmp];
        }
        for (; overDataStart <= overDataEnd; overDataStart++) {
            res[i++] = data[overDataStart];
        }
        int j = Math.min(start1, start2);
        for (i = 0; i < res.length; i++, j++) {
            data[j] = res[i];
        }
    }
}
