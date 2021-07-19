package 二分法;

/**
 * @author parry
 * @date 2021/07/16
 * https://leetcode-cn.com/problems/search-a-2d-matrix/
 */
public class 搜索二维矩阵 {
    
    public static void main(String[] args) {
        int[][] matrix = {{1, 3, 5, 7}, {10, 11, 16, 20}, {23, 30, 34, 60}};
        System.out.println(searchMatrix(matrix, 3));
    }
    
    public static boolean searchMatrix(int[][] matrix, int target) {
        int left = 0;
        int right = matrix.length * matrix[0].length - 1;
        while (left <= right) {
            int mid = (left + right) >> 1;
            int num = get(matrix, mid);
            if (num == target) {
                return true;
            }
            if (target < num) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return false;
    }
    
    public static int get(int[][] matrix, int index) {
        int rowCount = matrix[0].length;
        int i = index / rowCount;
        int j = index - i * rowCount;
        return matrix[i][j];
    }
}
