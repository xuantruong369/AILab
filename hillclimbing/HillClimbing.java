package hillclimbing;
import java.util.*;
import utils.Node;
import utils.OutputWriter;

public class HillClimbing {

    private int[][] matrix;
    private List<Node> nodes;
    private boolean[] visited;
    private Map<Integer, Integer> parent;
    private int start;
    private int goal;
    private OutputWriter out;
    private PriorityQueue<Integer> maxList;
    private Stack<Integer> openList;

    public HillClimbing(int[][] matrix,
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

        maxList = new PriorityQueue<>(
            Comparator.comparingInt(
                (Integer i) -> nodes.get(i).getWeight()
            ).reversed()
        );
        openList = new Stack<>();
    }

    public boolean hasPath() {
        out.writeHeader();
        out.writeRow(nodes.get(start));
        
        openList.push(start);
        

        while (!openList.isEmpty()) {
            int u = openList.pop();
            visited[u] = true;

            List<Node> neighbors = new ArrayList<>();

            // duyệt các đỉnh kề
            for (int v = 0; v < matrix.length; v++) {
                if (matrix[u][v] == 1 && !visited[v]) {
                    neighbors.add(nodes.get(v));
                    parent.put(v, u);
                    //
                    maxList.add(v);
                    //
                }
            }
            //
            while(!maxList.isEmpty()) {
                openList.add(maxList.poll());
            }
            //

            // danh sách L
            List<Node> L = new ArrayList<>();
            for (int x : openList)
                L.add(nodes.get(x));

            if (u == goal) {
                L.clear();
                out.writeRow(nodes.get(u),List.of(new Node("TTKT-DUNG", -1)), L);
                return true;
            }
            Collections.reverse(L);
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
