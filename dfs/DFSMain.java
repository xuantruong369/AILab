package dfs;

import utils.InputReader;
import utils.OutputWriter;

public class DFSMain {
    public static void main(String[] args) {
        InputReader in = new InputReader("dfs/inputDfs.txt");
        OutputWriter out = new OutputWriter("dfs/outputDfs.txt");

        DFS dfs = new DFS
        (
            in.getMatrix(),
            in.getNodes(), 
            in.getStart(), 
            in.getGoal()
        );
        dfs.hasPath();
        out.writeTable(dfs.buildTable());
        out.writePath(dfs.getPath());
        out.close();
    }
}
