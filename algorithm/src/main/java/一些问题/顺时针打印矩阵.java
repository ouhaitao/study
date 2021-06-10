package 一些问题;

/**
 * @author parry
 * @date 2020/12/01
 * 本题没有复杂的算法以及数据结构
 * 需要的是画图分析
 */
public class 顺时针打印矩阵 {
    
    public static void main(String[] args) {
        int[][] a = {
            {1, 2, 3, 4},
            {5, 6, 7, 8},
            {9, 10, 11, 12},
            {13, 14, 15, 16}
        };
        fun(a);
        System.out.println();
        a = new int[][]{{1},{2},{3}};
        fun(a);
        System.out.println();
        a = new int[][]{{1}};
        fun(a);
    }
    
    private static void fun(int[][] a) {
        if (a == null || a.length == 0 || a[0].length == 0) {
            throw new IllegalArgumentException("a can not be null");
        }
//        4个边界
        int top = 0, left = 0, right = a[0].length - 1, bottom = a.length - 1;
        int i = 0, j = 0;
//        先对top进行初始化
        top++;
//        每一次循环都是走完一圈
        while (true) {
    
            if (left > right) {
                break;
            }
//            向右
            for (; j <= right; j++) {
                System.out.print(a[i][j] + ",");
            }
//            循环完之后j超出了right,需要处理
            j--;
//            处理边界
            right--;
//            为了避免重复打印，先将i++
            i++;
    
            if (top > bottom) {
                break;
            }
//            向下
            for (; i <= bottom; i++) {
                System.out.print(a[i][j] + ",");
            }
            i--;
            bottom--;
            j--;
            
            if (left > right) {
                break;
            }
//            向左
            for (; j >= left; j--) {
                System.out.print(a[i][j] + ",");
            }
            j++;
            left++;
            i--;
            
            if (top > bottom) {
                break;
            }
//            向上
            for (; i >= top; i--) {
                System.out.print(a[i][j] + ",");
            }
            i++;
            top++;
            j++;
        }
    }
}
