package 一些问题;

/**
 * @author parry
 * @date 2020/11/17
 */
public class 二维数组查找数字 {
    
    public static void main(String[] args) {
        int[][] arr = {{1, 2, 8, 9}, {2, 4, 9, 12}, {4, 7, 10, 13}, {6, 8, 11, 15}};
        fun(arr, 7);
        fun(arr, 5);
    }
    
    /**
     * 数组横向、纵向皆为有序排列
     */
    private static void fun(int[][] arr, int target) {
        int i = arr[0].length - 1;
        
        for (; i >= 0; i--) {
            for (int j = 0; j < arr[i].length; j++) {
                if (arr[i][j] == target) {
                    System.out.println(String.format("arr[%d][%d]", i, j));
                    return;
                }
                if (arr[i][j] > target) {
                    break;
                }
            }
        }
    }
}
