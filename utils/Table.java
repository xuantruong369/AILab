package utils;

import java.util.ArrayList;
import java.util.List;

public class Table {

    private final int cols;
    private final List<String[]> rows;

    private int rowIndex;
    private int colIndex;

    private String value;
    private boolean first;

    public Table(int cols) {
        this.cols = cols;
        this.rows = new ArrayList<>();
        init();
    }

    /* ================== GHI CELL ================== */

    public void writeAddCell(String value) {
        this.value = addString(value);
        commitCell();
    }

    public void writeAddCell(String... values) {
        for (String s : values) {
            this.value = addString(s);
        }
        commitCell();
    }

    public void setCurrentCell(String newValue) {
        ensureRowExists();
        rows.get(rowIndex)[colIndex] = newValue;
    }


    /* ================== DI CHUYỂN ================== */

    public void nextColumn() {
        if (colIndex < cols - 1) {
            colIndex++;
        }
        reset();
    }

    public void nextRow() {
        rowIndex++;
        colIndex = 0;
        reset();
    }

    /* ================== CORE ================== */

    private void commitCell() {
        ensureRowExists();
        rows.get(rowIndex)[colIndex] = value;
    }

    private void ensureRowExists() {
        while (rows.size() <= rowIndex) {
            rows.add(new String[cols]);
        }
    }

    private void init() {
        rowIndex = 0;
        colIndex = 0;
        reset();
    }

    private String addString(String v) {
        if (!first) value += ",";
        value += v;
        first = false;
        return value;
    }

    private void reset() {
        value = "";
        first = true;
    }

    /* ================== GETTER ================== */

    public int getRowCount() {
        return rows.size();
    }

    public int getColCount() {
        return cols;
    }

    public List<String[]> getRows() {
        return rows;
    }

    public String get(int r, int c) {
        return rows.get(r)[c];
    }

    /* ================== TIỆN ÍCH ================== */

    public int[] getMaxColWidths(int minWidth) {
        int[] widths = new int[cols];

        for (int c = 0; c < cols; c++) {
            int max = 0;
            for (String[] row : rows) {
                if (row[c] != null) {
                    max = Math.max(max, row[c].length());
                }
            }
            widths[c] = Math.max(max, minWidth);
        }
        return widths;
    }

}
