package branchandbound;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Stack;
import utils.Node;
import utils.Pair;
import utils.Table;

public class BranchAndBound {
    private int[][] matrix;
    private List<Node> nodes;
    private boolean[] visited;
    private Map<Integer, Integer> parent;
    private int start;
    private int goal;
    private PriorityQueue<Pair> maxList;//openList
    private Map<Pair, Integer> gCost;
    private Stack<Pair> openList;
    private Table tableData;
    private int pathCost;

    public BranchAndBound (int[][] matrix,
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
            Comparator.comparingInt(Pair::getSecond).reversed()
        );
        openList = new Stack<>();
        gCost = new HashMap<>();
        pathCost = Integer.MAX_VALUE;
        this.tableData = new Table(8);
    }

    public boolean hasPath() {
        int indexRow = 1;
        int count;
        //title table
        tableData.writeAddCell("TT");
        tableData.nextColumn();

        tableData.writeAddCell("TTK");
        tableData.nextColumn();

        tableData.writeAddCell("k(u,v)");
        tableData.nextColumn();

        tableData.writeAddCell("h(v)");
        tableData.nextColumn();

        tableData.writeAddCell("g(v)");
        tableData.nextColumn();

        tableData.writeAddCell("f(v)");
        tableData.nextColumn();

        tableData.writeAddCell("DS L1");
        tableData.nextColumn();

        tableData.writeAddCell("Danh sach L");
        tableData.nextColumn();

        tableData.nextRow();

        //
        
        Pair pStart = new Pair(start, 0);
        openList.push(pStart);
        gCost.put(pStart, 0);
        
        while (!openList.isEmpty()) {
            Pair u = openList.pop();
            visited[u.getFirst()] = true;

            //
            count = 0;
            //

            // duyệt các đỉnh kề
            for (int v = 0; v < matrix.length; v++) {
                if (matrix[u.getFirst()][v] != -1 /*&& !visited[v]*/) {
                    //
                    // tinh g(v) = g(u) + k(u, v)
                    int k = k(u.getFirst(), v);
                    int h = h(v);
                    int g = gCost.get(u) + k;
                    int f = g + h;
                    // tinh f(v) = g(v) + h(v)
                    // Map(f(v))
                    Pair pv = new Pair(v, f);
                    parent.put(v, u.getFirst());
                    //openList.push(pv);
                    maxList.add(pv);
                    gCost.put(pv, g);
                    //
                    tableData.writeAddCell("");
                    tableData.nextColumn();

                    tableData.writeAddCell(nodes.get(v).getName());
                    tableData.nextColumn();

                    tableData.writeAddCell(String.valueOf(k));
                    tableData.nextColumn();

                    tableData.writeAddCell(String.valueOf(h));
                    tableData.nextColumn();

                    tableData.writeAddCell(String.valueOf(g));
                    tableData.nextColumn();

                    tableData.writeAddCell(String.valueOf(f));
                    tableData.nextColumn();

                    tableData.writeAddCell("");
                    tableData.nextRow();
                    count++;
                    //
                }
            }
            //
            tableData.next(indexRow, 6);
            List<Pair> mList = new ArrayList<>(maxList);
            mList.sort(Comparator.comparingInt(Pair::getSecond));

            for (Pair p : mList) {
                tableData.writeAddCell(nodes.get(p.getFirst()).getName() + String.valueOf(p.getSecond()));
            }
            //
            while (!maxList.isEmpty()) {
                openList.push(maxList.poll());
            }

            tableData.next(indexRow, 0);
            tableData.writeAddCell(nodes.get(u.getFirst()).getName());

            if (u.getFirst() == goal) {
                if (gCost.get(u) <= pathCost) {
                    pathCost = gCost.get(u);
                }
                else {
                    tableData.nextColumn();
                    tableData.writeAddCell("TTKT-DUNG");
                    return true;
                }
            }
            
            

            tableData.next(indexRow, 7);
            //
            List<Pair> list = new ArrayList<>(openList);
            // list.sort(Comparator.comparingInt(Pair::getSecond));
            Collections.reverse(list);

            for (Pair p : list) {
                tableData.writeAddCell(nodes.get(p.getFirst()).getName() + String.valueOf(p.getSecond()));
            }

            indexRow += count;
            tableData.setIndex(indexRow, 0);

            //

         
        }

        return false;
    }

    private int k(int u, int v) {
        return matrix[u][v];
    }

    private int h(int v) {
        return nodes.get(v).getWeight();
    }

    // private int g(int v) {
    //     return 0;
    // }

    // private int f(int v) {
    //     return h(v) + g(v);
    // }

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

    public int getPathCost() {
        return pathCost;
    }

    public Table buildTable() {
        return tableData;
    }
}
