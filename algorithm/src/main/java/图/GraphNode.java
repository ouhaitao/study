package 图;

import lombok.Getter;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 * @author parry
 * @date 2020/04/29
 * 无向图
 */
@Getter
public class GraphNode {
    
    private int val;
    /**
     * 邻接表
     */
    private LinkedList<GraphNode> adj;
    
    public GraphNode(int val) {
        this.val = val;
        adj = new LinkedList<>();
    }
    
    public void addEdge(GraphNode node) {
        adj.add(node);
    }
    
    /**
     * @param data 图节点数据
     * @param adj  邻接表 adj[i][j] 表示data[i]的邻接节点在在data数组中的下标
     */
    public static List<GraphNode> build(int[] data, int[][] adj) {
        List<GraphNode> graphNodes = new ArrayList<>(data.length);
        for (int i = 0; i < data.length; i++) {
            graphNodes.add(new GraphNode(data[i]));
        }
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < adj[i].length; j++) {
                graphNodes.get(i).addEdge(graphNodes.get(adj[i][j]));
            }
        }
        return graphNodes;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof GraphNode)) {
            return false;
        }
        GraphNode graphNode = (GraphNode) o;
        return val == graphNode.val;
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(val);
    }
    
    @Override
    public String toString() {
        return String.valueOf(val);
    }
}
