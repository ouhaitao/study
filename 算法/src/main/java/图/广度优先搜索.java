package 图;

import java.util.*;

/**
 * @author parry
 * @date 2020/04/29
 */
public class 广度优先搜索 {
    
    /**
     * 构建的图的图示 https://static001.geekbang.org/resource/image/00/ea/002e9e54fb0d4dbf5462226d946fa1ea.jpg
     */
    public static void main(String[] args) {
        int[] data = {0, 1, 2, 3, 4, 5, 6, 7};
        int[][] adj = {{1, 3}, {0, 2, 4}, {1, 5}, {0, 4}, {1, 3, 5, 6}, {2, 4, 7}, {4, 7}, {6, 5}};
        List<GraphNode> graphNodes = GraphNode.build(data, adj);
        dfs(graphNodes.get(0), graphNodes.get(7));
        
        System.out.println();
        
        List<GraphNode> path = new LinkedList<>();
        Set<GraphNode> visited = new LinkedHashSet<>();
        visited.add(graphNodes.get(0));
        dfs(graphNodes.get(0), graphNodes.get(7), visited, path);
        path = new ArrayList<>(path);
        System.out.print(graphNodes.get(0) + " ");
        for (int i = path.size() - 1; i >= 0; i--) {
            System.out.print(path.get(i) + " ");
        }
    }
    
    /**
     * 广度优先，即先把当前节点的邻接点遍历了在遍历其他节点
     */
    private static void bfs(GraphNode srcNode, GraphNode targetNode) {
        Set<GraphNode> visited = new LinkedHashSet<>();
        Queue<GraphNode> queue = new LinkedList<>();
        queue.add(srcNode);
        visited.add(srcNode);
//      表示搜索路径,表示key是从value搜索过来的
        Map<GraphNode, GraphNode> prev = new HashMap<>();
        while (queue.size() > 0) {
            GraphNode poll = queue.poll();
            LinkedList<GraphNode> pollAdj = poll.getAdj();
            for (GraphNode graphNode : pollAdj) {
                if (visited.add(graphNode)) {
                    prev.put(graphNode, poll);
                    if (graphNode.equals(targetNode)) {
                        print(prev, srcNode, targetNode);
                        break;
                    }
                    queue.add(graphNode);
                }
            }
        }
    }
    
    /**
     * 深度优先 队列实现
     */
    private static void dfs(GraphNode srcNode, GraphNode targetNode) {
        Queue<GraphNode> queue = new LinkedList<>();
        queue.add(srcNode);
        Set<GraphNode> visited = new LinkedHashSet<>();
        visited.add(srcNode);
//      表示搜索路径,表示key是从value搜索过来的
        Map<GraphNode, GraphNode> prev = new HashMap<>();
        while (queue.size() > 0) {
            GraphNode poll = queue.poll();
            if (poll.equals(targetNode)) {
                print(prev, srcNode, targetNode);
                break;
            }
            LinkedList<GraphNode> pollAdj = poll.getAdj();
            for (GraphNode graphNode : pollAdj) {
                if (visited.add(graphNode)) {
                    prev.put(graphNode, poll);
                    queue.add(graphNode);
                }
            }
        }
    }
    
    /**
     * 深度优先 递归实现
     */
    private static boolean dfs(GraphNode srcNode, GraphNode targetNode, Set<GraphNode> visited, List<GraphNode> path) {
        if (srcNode.equals(targetNode)) {
            return true;
        }
        LinkedList<GraphNode> adj = srcNode.getAdj();
        boolean res = false;
        for (GraphNode graphNode : adj) {
            if (visited.add(graphNode)) {
                res = dfs(graphNode, targetNode, visited, path);
                if (res) {
                    path.add(graphNode);
                    break;
                }
            }
        }
        return res;
    }
    
    private static void print(Map<GraphNode, GraphNode> prev, GraphNode src, GraphNode target) {
        if (target != src && prev.get(target) != null) {
            print(prev, src, prev.get(target));
        }
        System.out.print(target + " ");
    }
}
