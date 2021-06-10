package 排序;

import java.util.Arrays;

public class 选择排序 {
    
    public static void main(String[] args) {
        int[] data = {6, 2, 7, 3, 8, 9};
        Arrays.stream(chooseSort(data)).forEach(x -> System.out.print(x + " "));
    }
    
    public static int[] chooseSort(int[] data) {
        
        int length = data.length;
        for (int i = 0; i < length; i++) {
            int min = i;
            for (int j = i + 1; j < length; j++) {
                if (data[min] > data[j]) {
                    min = j;
                }
            }
            if (i != min) {
                int tmp = data[min];
                data[min] = data[i];
                data[i] = tmp;
            }
            
        }
        return data;
    }
}
