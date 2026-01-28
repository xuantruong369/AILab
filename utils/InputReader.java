package utils;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InputReader {

    private BufferedReader br;
    private List<Node> nodes;
    private Map<String, Integer> indexMap;
    private int[][] matrix;
    private int start;
    private int goal;

    public InputReader(String filePath){
        nodes = new ArrayList<>();
        indexMap = new HashMap<>();
        read(filePath);
    }

    private void read(String filePath) {
        try {
            br = new BufferedReader(new FileReader(filePath));
            int size = Integer.parseInt(br.readLine());
            String line = null;
            int index = 0;
            while (index < size) {
                line = br.readLine();
                String[] p = line.split(",");
                Node node;
                if (p.length >= 2) {
                    node = new Node(p[0], Integer.parseInt(p[1]));
                } else {
                    node = new Node(p[0], -1);
                }
                indexMap.put(node.getName(), nodes.size());
                nodes.add(node);
                index++;
            }

            start = indexMap.get(br.readLine());
            goal = indexMap.get(br.readLine());

            int n = nodes.size();
            matrix = new int[n][n];

            for (int i = 0; i < n; i++) {
                String[] p = br.readLine().split(",");
                for (int j = 0; j < n; j++) {
                    matrix[i][j] = Integer.parseInt(p[j]);
                }
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Node> getNodes() { return nodes; }
    public int[][] getMatrix() { return matrix; }
    public int getStart() { return start; }
    public int getGoal() { return goal; }
}
