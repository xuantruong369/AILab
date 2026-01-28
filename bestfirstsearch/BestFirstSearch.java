package bestfirstsearch;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import utils.Node;
import utils.OutputWriter;

public class BestFirstSearch {
    private int[][] matrix;
    private List<Node> nodes;
    private boolean[] visited;
    private Map<Integer, Integer> parent;
    private int start;
    private int goal;
    private OutputWriter out;
    private PriorityQueue<Integer> openList;//openList

    public BestFirstSearch(int[][] matrix,
                        List<Node> nodes,
                        int start,
                        int goal,
                        OutputWriter out) {

        this.matrix = matrix;
        this.nodes = nodes;
        this.start = start;
        this.goal = goal;
        this.out = out;

        visited = new boolean[nodes.size()];
        parent = new HashMap<>();

        openList = new PriorityQueue<>(
            Comparator.comparingInt(
                (Integer i) -> nodes.get(i).getWeight()
            )
        );
    }

    public boolean hasPath() {
        out.writeHeader();
        out.writeRow(nodes.get(start));
        
        openList.add(start);
        

        while (!openList.isEmpty()) {
            int u = openList.poll();
            visited[u] = true;

            List<Node> neighbors = new ArrayList<>();

            // duyệt các đỉnh kề
            for (int v = 0; v < matrix.length; v++) {
                if (matrix[u][v] == 1 && !visited[v]) {
                    neighbors.add(nodes.get(v));
                    parent.put(v, u);
                    //
                    openList.add(v);
                    //
                }
            }

            // danh sách L
            List<Node> L = new ArrayList<>();
            for (int x : openList)
                L.add(nodes.get(x));

            if (u == goal) {
                L.clear();
                out.writeRow(nodes.get(u),List.of(new Node("TTKT-DUNG", -1)), L);
                return true;
            }
            L.sort(null);
            out.writeRow(nodes.get(u), neighbors, L);
        }

        return false;
    }

    public List<Node> getPath() {
        List<Node> path = new ArrayList<>();
        if (!visited[goal]) return path;

        int cur = goal;
        while (true) {
            path.add(nodes.get(cur));
            if (cur == start) break;
            cur = parent.get(cur);
        }
        Collections.reverse(path);
        return path;
    }
}
