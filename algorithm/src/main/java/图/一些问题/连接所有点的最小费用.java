package 图.一些问题;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author parry
 * @date 2021/01/19
 * https://leetcode-cn.com/problems/min-cost-to-connect-all-points/
 */
public class 连接所有点的最小费用 {
    
    public static void main(String[] args) {
        int[][] points = {{0, 0}, {2, 2}, {3, 10}, {5, 2}, {7, 0}};
        points = new int[][]{{3, 12}, {-2, 5}, {-4, 1}};
        points = new int[][]{{0, 0}, {1, 1}, {1, 0}, {-1, 1}};
        points = new int[][]{{-1000000, -1000000}, {1000000, 1000000}};
        points = new int[][]{{2, -3}, {-17, -8}, {13, 8}, {-17, -15}};
        int[] node = new int[points.length];
        int[][] worth = new int[node.length][node.length];
        for (int i = 0; i < points.length; i++) {
            for (int j = 0; j < points.length; j++) {
                if (i == j) {
                    worth[i][j] = Integer.MAX_VALUE;
                } else {
                    worth[i][j] = Math.abs(points[i][0] - points[j][0]) + Math.abs(points[i][1] - points[j][1]);
                }
            }
        }
        System.out.println(prime(node, worth));
        node = new int[points.length];
        System.out.println(kruskal(node, worth));
    }
    
    /**
     * 边
     */
    @Getter
    @AllArgsConstructor
    private static class Edge implements Comparable<Edge> {
        private int point1;
        private int point2;
        private int worth;
        
        
        @Override
        public int compareTo(Edge o) {
            return this.worth - o.worth;
        }
    
        @Override
        public String toString() {
            return String.valueOf(worth);
        }
    }
    
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
    
    private static int kruskal(int[] node, int[][] worth) {
        DisjointSetUnion dsu = new DisjointSetUnion(node.length);
        List<Edge> edges = new ArrayList<>(node.length);
        for (int i = 0; i < node.length; i++) {
            for (int j = 0; j < node.length; j++) {
                if (i != j) {
                    edges.add(new Edge(i, j, worth[i][j]));
                }
            }
        }
        quickSort(edges, 0, edges.size() - 1);
        int sum = 0, num = 1;
        for (Edge edge : edges) {
            if (dsu.merge(edge.getPoint1(), edge.getPoint2())) {
                sum += edge.getWorth();
                num++;
                if (num == node.length) {
                    break;
                }
            }
        }
        return sum;
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
    
    private static int prime(int[] node, int[][] worth) {
        int sum = 0;
        int[] lowCost = new int[node.length];
//        node[i]=0表示未遍历 node[i]=1表示已遍历
        node[0] = 1;
        for (int i = 1; i < node.length; i++) {
            lowCost[i] = worth[i][0];
        }
        for (int i = 1; i < node.length; i++) {
            sum += findMinEdge(node, worth, lowCost);
        }
        return sum;
    }
    
    private static int findMinEdge(int[] node, int[][] worth) {
        int minWorth = Integer.MAX_VALUE;
        int start = Integer.MIN_VALUE;
        int end = Integer.MAX_VALUE;
        for (int i = 0; i < node.length; i++) {
//            已遍历
            if (node[i] != 1) {
                continue;
            }
            for (int j = 0; j < node.length; j++) {
//                未遍历
                if (node[j] != 0) {
                    continue;
                }
                if (worth[i][j] <= minWorth) {
                    minWorth = worth[i][j];
//                    start是已遍历的边 end是未遍历的边
                    start = i;
                    end = j;
                }
            }
        }
        node[end] = 1;
        return worth[start][end];
    }
    
    private static int findMinEdge(int[] node, int[][] worth, int[] lowCost) {
        int minWorth = Integer.MAX_VALUE;
        int minWorthIndex = -1;
        for (int i = 0; i < node.length; i++) {
//            已遍历
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
}
