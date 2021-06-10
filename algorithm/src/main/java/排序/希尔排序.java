package 排序;

import java.util.Arrays;

public class 希尔排序 {
    
    public static void main(String[] args) {
        int[] data = {6, 2, 7, 3, 8, 9};
        Arrays.stream(shellSort(data)).forEach(x -> System.out.print(x + " "));
    }
    
    private static int[] shellSort(int[] data) {
        int length = data.length;
        int increment = length << 1;
        while (increment != 0) {
            
            for (int i = 0; i < length; i += increment) {
                int tmp = data[i];
                int j = i - increment;
                while (j >= 0 && data[j] > tmp) {
                    data[j + increment] = data[j];
                    j -= increment;
                }
                if (i != j) {
                    data[j + increment] = tmp;
                }
            }
            
            increment = increment << 1;
        }
        return data;
    }
}
