package bfs;
import java.util.*;
import utils.Node;
import utils.Table;

public class BFS {

    private int[][] matrix;
    private List<Node> nodes;
    private boolean[] visited;
    private Map<Integer, Integer> parent; // truy vết
    private int start;
    private int goal;
    private Queue<Integer> openList;
    private Table tableData;

    public BFS(int[][] matrix, List<Node> nodes, int start, int goal) {
        this.matrix = matrix;
        this.nodes = nodes;
        this.start = start;
        this.goal = goal;
        this.visited = new boolean[nodes.size()];
        this.parent = new HashMap<>();
        this.openList = new LinkedList<>();

        this.tableData = new Table(3);
    }

    public boolean hasPath() {
        //
        tableData.writeAddCell("Phat trien TT");
        tableData.nextColumn();

        tableData.writeAddCell("Trang thai ke");
        tableData.nextColumn();

        tableData.writeAddCell("Danh sach L");
        tableData.nextRow();
        //

        openList.add(start);

        //
        tableData.writeAddCell("");
        tableData.nextColumn();

        tableData.writeAddCell("");
        tableData.nextColumn();

        tableData.writeAddCell(nodes.get(start).toString());
        tableData.nextRow();
        //
        
        while (!openList.isEmpty()) {
            int u = openList.poll();

            //
            tableData.writeAddCell(nodes.get(u).toString());
            tableData.nextColumn();
            //

            if (visited[u]) continue;
            visited[u] = true;

     

            for (int i = 0; i < matrix.length; i++) {
                if (matrix[u][i] == 1 && !visited[i]) {
                    parent.put(i, u);
                    openList.add(i);
                    //
                    tableData.writeAddCell(nodes.get(i).toString());
                    //
                }
            }


            // Ghi output
            if (u == goal) {
                //
                tableData.setCurrentCell("TTKT-DUNG");
                tableData.nextColumn();

                tableData.writeAddCell("");
                //
                return true;
            }

         

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

    public Table buildTable() {
        return tableData;
    }
}
