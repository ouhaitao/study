package 回溯;

import java.util.Arrays;

/**
 * @author parry
 * @date 2020/11/19
 */
public class 矩阵路径 {
    
    /**
     * 给出一个矩阵，然后给出一个字符串，判断矩阵中是否存在一条包含字符串的路径
     * 每一步可以上下左右移动，矩阵中一格只能够经过一次
     */
    public static void main(String[] args) {
        char[][] matrix = {{'a', 'b', 't', 'g'}, {'c', 'f', 'c', 's'}, {'j', 'd', 'e', 'h'}};
        String str = "bfce";
        System.out.println(fun(matrix, str.toCharArray()));
        str = "abfb";
        System.out.println(fun(matrix, str.toCharArray()));
    }
    
    private static boolean fun(char[][] matrix, char[] path) {
        assert matrix != null && path != null && path.length > 0;
        
        int[] index = findIndexInArray(matrix, path[0]);
        if (index == null) {
            return false;
        }
        return funCore(matrix, path, new int[matrix.length][matrix[index[0]].length], index[0], index[1], 0);
    }
    
    /**
     *
     * @param matrix 矩阵
     * @param path 求解路径
     * @param visited 已经过的节点
     * @param pathInMatrixI 当前矩阵的位置
     * @param pathInMatrixJ 当前矩阵的位置
     * @param currentPathIndex 当前需要的path的节点
     * 判断当前矩阵节点是否是当前需要的path节点
     */
    private static boolean funCore(char[][] matrix, char[] path, int[][] visited, int pathInMatrixI, int pathInMatrixJ, int currentPathIndex) {
        if (currentPathIndex == path.length) {
            return true;
        }
        Character currentChar = get(matrix, pathInMatrixI, pathInMatrixJ);
        if (currentChar != null
            && visited[pathInMatrixI][pathInMatrixJ] == 0
            && currentChar == path[currentPathIndex]) {
            visited[pathInMatrixI][pathInMatrixJ] = 1;
            return funCore(matrix, path, visited, pathInMatrixI, pathInMatrixJ + 1, currentPathIndex + 1)
                || funCore(matrix, path, visited, pathInMatrixI, pathInMatrixJ - 1, currentPathIndex + 1)
                || funCore(matrix, path, visited, pathInMatrixI + 1, pathInMatrixJ, currentPathIndex + 1)
                || funCore(matrix, path, visited, pathInMatrixI - 1, pathInMatrixJ, currentPathIndex + 1);
        }
        return false;
    }
    
    private static int[] findIndexInArray(char[][] matrix, char target) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] == target) {
                    return new int[]{i, j};
                }
            }
        }
        return null;
    }
    
    private static Character get(char[][] matrix, int i, int j) {
        if (i < 0 || i >= matrix.length || j < 0 || j >= matrix[i].length) {
            return null;
        }
        return matrix[i][j];
    }
}
