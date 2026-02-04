package hillclimbing;
import java.util.*;
import utils.Node;
import utils.Table;

public class HillClimbing {

    private int[][] matrix;
    private List<Node> nodes;
    private boolean[] visited;
    private Map<Integer, Integer> parent;
    private int start;
    private int goal;
    private PriorityQueue<Integer> maxList;
    private Stack<Integer> openList;
    private Table tableData;

    public HillClimbing(int[][] matrix,
                        List<Node> nodes,
                        int start,
                        int goal
    ) {

        this.matrix = matrix;
        this.nodes = nodes;
        this.start = start;
        this.goal = goal;

        visited = new boolean[nodes.size()];
        parent = new HashMap<>();

        maxList = new PriorityQueue<>(
            Comparator.comparingInt(
                (Integer i) -> nodes.get(i).getWeight()
            ).reversed()
        );
        openList = new Stack<>();
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
        
        
        openList.push(start);

        //
        tableData.writeAddCell("");
        tableData.nextColumn();

        tableData.writeAddCell("");
        tableData.nextColumn();

        tableData.writeAddCell(nodes.get(start).toString());
        tableData.nextRow();
        //
        

        while (!openList.isEmpty()) {
            int u = openList.pop();
            visited[u] = true;

            //
            tableData.writeAddCell(nodes.get(u).toString());
            tableData.nextColumn();
            //


            // duyệt các đỉnh kề
            for (int v = 0; v < matrix.length; v++) {
                if (matrix[u][v] == 1 && !visited[v]) {
                    parent.put(v, u);
                    maxList.add(v);
                    //
                    tableData.writeAddCell(nodes.get(v).toString());
                    //
                }
            }
            //
            while(!maxList.isEmpty()) {
                openList.add(maxList.poll());
            }
            //

            if (u == goal) {
                //
                tableData.setCurrentCell("TTKT-DUNG");
                tableData.nextColumn();

                tableData.writeAddCell("");
                //
                return true;
            }
           
            //
            tableData.nextColumn();
            //
            List<Integer> list = new ArrayList<>(openList);
            //list.sort(Comparator.comparingInt(i -> nodes.get(i).getWeight()));
            Collections.reverse(list);

            for (Integer value : list) {
                tableData.writeAddCell(nodes.get(value).toString());
            }
            tableData.nextRow();
            //
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
