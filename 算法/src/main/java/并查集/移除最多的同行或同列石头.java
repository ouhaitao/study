package 并查集;

/**
 * @author parry
 * @date 2021/01/20
 * 题目 https://leetcode-cn.com/problems/most-stones-removed-with-same-row-or-column/
 * 详解 https://leetcode-cn.com/problems/most-stones-removed-with-same-row-or-column/solution/tu-jie-bing-cha-ji-by-yexiso-nbcz/
 */
public class 移除最多的同行或同列石头 {
    
    private static class Djs {
        // 记录根
        int[] parent;
        // 记录根的深度，由于优化 优化原理看题解
        int[] rank;
        // 移除了多少块石头
        int count;
        
        public Djs(int n) {
            rank = new int[n];
            parent = new int[n];
            // 将每个点的根都初始化为自己，即每个点都自成一棵树
            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
            count = n;
        }
        
        private int find(int i) {
            // 查找当前节点的根
            if (i != parent[i]) {
                // 压缩方式：详细看题解
                parent[i] = find(parent[i]);
            }
            return parent[i];
        }
        
        public void merge(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);
            if (rootX != rootY) {
                // 将深度更小的树合并到深度更高的树中 这样能够使合并之后的树的深度不变
                if (rank[rootX] < rank[rootY]) {
                    int tmp = rootX;
                    rootX = rootY;
                    rootY = tmp;
                }
                parent[rootY] = rootX;
                // 如果深度相同，则随意选择一个树作为深度更高的树，这里选择的是rootX
                if (rank[rootX] == rank[rootY]) {
                    rank[rootX] += 1;
                }
                count--;
            }
    
        }
        
        public int getCount() {
            return count;
        }
    }
    
    public static int removeStones(int[][] stones) {
        Djs djs = new Djs(stones.length);
        for (int i = 0; i < stones.length; i++) {
            for (int j = i + 1; j < stones.length; j++) {
                if (stones[i][0] == stones[j][0] || stones[i][1] == stones[j][1]) {
                    djs.merge(i, j);
                }
            }
        }
        return stones.length - djs.getCount();
    }
    
    public static void main(String[] args) {
        int[][] stones = {{0, 0}, {0, 1}, {1, 0}, {1, 2}, {2, 1}, {2, 2}, {3, 3}, {4, 3}, {3, 5}, {6, 4}};
        System.out.println(removeStones(stones));
    }
}
