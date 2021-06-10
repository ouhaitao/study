package 图;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author parry
 * @date 2021/01/19
 */
public class 最小生成树 {
    
    public static void main(String[] args) {
        int maxWorth = Integer.MAX_VALUE;
        // 表示5个节点,以数组下标命名不同节点
        int[] node = new int[5];
        // 邻接表 adj[i]表示node[i]的邻接点下标
        int[][] adj = {{1, 3, 4}, {0, 2, 4}, {1, 3, 4}, {0, 2}, {0, 1, 2}};
        // 权重 worth[i][j]表示相邻节点node[i]与node[j]连成的边的权重
        int[][] worth = {
            {maxWorth, 4, maxWorth, 7, 12},
            {4, maxWorth, 3, maxWorth, 9},
            {maxWorth, 3, maxWorth, 4, 10},
            {7, maxWorth, 4, maxWorth, maxWorth},
            {12, 9, 10, maxWorth, maxWorth}
        };
        prime(node, adj, worth);
        node = new int[5];
        kruskal(node, adj, worth);
    }
    
    /**
     * prime算法 贪心
     *
     * 1).输入：一个加权连通图，其中顶点集合为V，边集合为E；
     * 2).初始化：Vnew= {x}，其中x为集合V中的任一节点（起始点），Enew= {},为空；
     * 3).重复下列操作，直到Vnew= V：
     *  a.在集合E中选取权值最小的边<u, v>，其中u为集合Vnew中的元素，而v不在Vnew集合当中，并且v∈V（如果存在有多条满足前述条件即具有相同权值的边，则可任意选取其中之一）；
     *  b.将v加入集合Vnew中，将<u, v>边加入集合Enew中；
     * 4).输出：使用集合Vnew和Enew来描述所得到的最小生成树。
     *
     */
    private static void prime(int[] node, int[][] adj, int[][] worth) {
        int sum = 0;
        int[] lowCost = new int[node.length];
//        node[i]=0表示未遍历 node[i]=1表示已遍历
        node[0] = 1;
        for (int i = 1; i < node.length; i++) {
            lowCost[i] = worth[i][0];
        }
        for (int i = 1; i < node.length; i++) {
//            sum += findMinEdge(node, adj, worth);
            sum += findMinEdge(node, adj, worth, lowCost);
        }
        System.out.println(sum);
    }
    
    /**
     * 从V-Vnew中选取一个点，该点到Vnew中的任意一点的边权值最小
     */
    private static int findMinEdge(int[] node, int[][] adj, int[][] worth) {
        int minWorth = Integer.MAX_VALUE;
        int start = Integer.MIN_VALUE;
        int end = Integer.MAX_VALUE;
//        遍历Vnew,从V-Vnew中找到一个点距离Vnew中的点最近
        for (int i = 0; i < node.length; i++) {
//            从Vnew中找到一个点
            if (node[i] == 0) {
                continue;
            }
//            遍历V - Vnew
            for (int j = 0; j < adj[i].length; j++) {
                if (node[adj[i][j]] == 1) {
                    continue;
                }
                if (worth[i][adj[i][j]] <= minWorth) {
                    minWorth = worth[i][adj[i][j]];
//                    start是已遍历的点 end是未遍历的点
                    start = i;
                    end = adj[i][j];
                }
            }
        }
        node[end] = 1;
        return worth[start][end];
    }
    
    /**
     * 从V-Vnew中选取一个点，该点到Vnew中的任意一点的边权值最小
     *
     * 使用额外的数组lowCost，替代上面的方法中的双层循环 用空间换时间
     * lowCost表示当前点距离Vnew中的点最近的距离
     * 此解法一般是最优解
     *
     * 该方法还可以优化，lowCost可以替换成堆，这样就不需要遍历V - Vnew
     */
    private static int findMinEdge(int[] node, int[][] adj, int[][] worth, int[] lowCost) {
        int minWorth = Integer.MAX_VALUE;
        int minWorthIndex = -1;
        for (int i = 0; i < node.length; i++) {
            if (node[i] == 1) {
                continue;
            }
            if (minWorth > lowCost[i]) {
                minWorth = lowCost[i];
                minWorthIndex = i;
            }
        }
//        更新lowCost
//        点node[minWorthIndex]加入了,更新该点的邻接点的lowCost
        for (int i = 0; i < node.length; i++) {
            if (node[i] == 0 && worth[i][minWorthIndex] != Integer.MAX_VALUE && worth[i][minWorthIndex] < lowCost[i]) {
                lowCost[i] = worth[i][minWorthIndex];
            }
        }
        node[minWorthIndex] = 1;
        return minWorth;
    }
    
    /**
     * 边
     */
    @Getter
    @Setter
    @AllArgsConstructor
    private static class Edge implements Comparable<Edge> {
        private int point1;
        private int point2;
        private int worth;
    
        @Override
        public int compareTo(Edge o) {
            return this.worth - o.worth;
        }
    }
    
    /**
     * 并查集
     */
    private static class DisjointSetUnion {
        int[] parent;
        int[] rank;
        int n;
        
        public DisjointSetUnion(int n) {
            this.n = n;
            this.rank = new int[n];
            this.parent = new int[n];
            for (int i = 0; i < n; i++) {
                this.parent[i] = i;
            }
        }
        
        public int find(int x) {
            if (x != parent[x]) {
                parent[x] = find(parent[x]);
            }
            return parent[x];
        }
        
        public boolean merge(int x, int y) {
            int rootX = find(x), rootY = find(y);
            // 根节点相同说明已经合并
            if (rootX == rootY) {
                return false;
            }
            if (rank[rootX] < rank[rootY]) {
                int temp = rootX;
                rootX = rootY;
                rootY = temp;
            } else if (rank[rootX] == rank[rootY]) {
                rank[rootX] ++;
            }
            parent[rootY] = rootX;
            return true;
        }
    }
    
    private static void kruskal(int[] node, int[][] adj, int[][] worth) {
        List<Edge> edges = new ArrayList<>(node.length);
        for (int i = 0; i < adj.length; i++) {
            for (int j = 0; j < adj[i].length; j++) {
                edges.add(new Edge(i, adj[i][j], worth[i][adj[i][j]]));
            }
        }
        quickSort(edges, 0, edges.size() - 1);
        int sum = 0;
        DisjointSetUnion djs = new DisjointSetUnion(node.length);
        for (Edge edge : edges) {
            if (djs.merge(edge.getPoint1(), edge.getPoint2())) {
                sum += edge.worth;
                
            }
        }
        System.out.println(sum);
    }
    
    private static void quickSort(List<Edge> edges, int start, int end) {
        if (start >= end) {
            return;
        }
        int partition = partition(edges, start, end);
        quickSort(edges, start, partition - 1);
        quickSort(edges, partition + 1, end);
    }
    
    private static int partition(List<Edge> edges, int start, int end) {
        
        Edge key = edges.get(end);
        int index = start;
        for (int i = start; i < end; i++) {
            if (edges.get(i).compareTo(key) < 0) {
                Collections.swap(edges, index, i);
                index++;
            }
        }
        Collections.swap(edges, index, end);
        return index;
    }
}
