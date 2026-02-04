package utils;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.List;
import java.util.stream.Collectors;

public class OutputWriter {
    private BufferedWriter bw;

    public OutputWriter(String filePath) {
        try {
            bw = new BufferedWriter(new FileWriter(filePath));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void writeHeader() {
        writeLine(
            String.format("%-30s | %-30s | %s", "Phat trien TT", "Trang thai ke", "Danh sach L")
        );
        writeLine("-".repeat(96));
    }

    public void writeRow(Node node) {
        writeLine(String.format("%-30s | %-30s | %s", " " , " ", node));
    }

    public void writeRow(
        Node current,
        List<Node> neighbors,
        List<Node> openList
    ) {
        String cur = current == null ? "" : current.toString();

        String ke = neighbors.isEmpty()
                ? ""
                : neighbors.stream()
                        .map(Node::toString)
                        .collect(Collectors.joining(","));

        String L = openList.isEmpty()
                ? ""
                : openList.stream()
                        .map(Node::toString)
                        .collect(Collectors.joining(","));

        writeLine(String.format("%-30s | %-30s | %s", cur, ke, L));
    }


    public void writeLine(String s) {
        try {
            bw.write(s);
            bw.newLine();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void writePath(List<Node> path) {
        writeLine("");
        writeLine("Duong di:");

        if (path.isEmpty()) {
            writeLine("Khong tim duoc duong di");
            return;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < path.size(); i++) {
            sb.append(path.get(i).getName());
            if (i < path.size() - 1)
                sb.append(" -> ");
        }

        writeLine(sb.toString());
    }

    public void writeTable(Table table) {
    int rows = table.getRowCount();
    int cols = table.getColCount();

    int[] colWidths = table.getMaxColWidths(3);

        // ===== 1. IN HEADER (row 0) =====
        StringBuilder header = new StringBuilder();
        for (int c = 0; c < cols; c++) {
            String cell = table.get(0, c);
            if (cell == null) cell = "";

            header.append(String.format("%-" + colWidths[c] + "s", cell));
            if (c < cols - 1) header.append(" | ");
        }
        writeLine(header.toString());

        // ===== 2. IN DÒNG GẠCH =====
        StringBuilder sep = new StringBuilder();
        for (int c = 0; c < cols; c++) {
            sep.append("-".repeat(colWidths[c]));
            if (c < cols - 1) sep.append("-+-");
        }
        writeLine(sep.toString());

        // ===== 3. IN DATA (từ row 1) =====
        for (int r = 1; r < rows; r++) {
            StringBuilder line = new StringBuilder();
            for (int c = 0; c < cols; c++) {
                String cell = table.get(r, c);
                if (cell == null) cell = "";

                line.append(String.format("%-" + colWidths[c] + "s", cell));
                if (c < cols - 1) line.append(" | ");
            }
            writeLine(line.toString());
        }
    }



    public void close() {
        try {
            bw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}