package bfs;
import java.util.*;
import utils.Node;
import utils.OutputWriter;

public class BFS {

    private int[][] matrix;
    private List<Node> nodes;
    private boolean[] visited;
    private Map<Integer, Integer> parent; // truy vết
    private int start;
    private int goal;
    private OutputWriter out;
    private Queue<Integer> openList;

    public BFS(int[][] matrix, List<Node> nodes, int start, int goal, OutputWriter out) {
        this.matrix = matrix;
        this.nodes = nodes;
        this.start = start;
        this.goal = goal;
        this.visited = new boolean[nodes.size()];
        this.parent = new HashMap<>();
        this.openList = new LinkedList<>();
        this.out = out;
    }

    public boolean hasPath() {
        out.writeHeader();
        out.writeRow(nodes.get(start));

        openList.add(start);
        
        while (!openList.isEmpty()) {
            int u = openList.poll();

            if (visited[u]) continue;
            visited[u] = true;

            // Lấy danh sách kề
            List<Node> neighbors = new ArrayList<>();

            for (int i = 0; i < matrix.length; i++) {
                if (matrix[u][i] == 1 && !visited[i]) {
                    neighbors.add(nodes.get(i));
                    parent.put(i, u);
                    openList.add(i);
                }
            }

            // Lấy danh sách L (stack)
            List<Node> L = new ArrayList<>();
            for (int x : openList)
                L.add(nodes.get(x));

            // Ghi output
            if (u == goal) {
                //
                L.clear();
                out.writeRow(nodes.get(u), List.of(new Node("TTKT-DUNG", -1)), L);
                return true;
            }

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
