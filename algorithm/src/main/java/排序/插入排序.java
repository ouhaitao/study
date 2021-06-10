package 排序;

import java.util.Arrays;

public class 插入排序 {
    
    public static void main(String[] args) {
        int[] data = {6, 2, 7, 3, 8, 9};
        Arrays.stream(insertSort(data)).forEach(x -> System.out.print(x + " "));
    }
    
    public static int[] insertSort(int[] data) {
        
        int length = data.length;
        for (int i = 1; i < length; i++) {
            int tmp = data[i];
            int j = i - 1;
            while (j >= 0 && data[j] > tmp) {
                data[j + 1] = data[j];
                j--;
            }
            if (i != j) {
                data[j + 1] = tmp;
            }
        }
        
        return data;
    }
}
