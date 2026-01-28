package dfs;

import utils.InputReader;
import utils.OutputWriter;

public class DFSMain {
    public static void main(String[] args) {
        InputReader in = new InputReader("dfs/input.txt");
        OutputWriter out = new OutputWriter("dfs/outputDfs.txt");

        DFS dfs = new DFS
        (
            in.getMatrix(),
            in.getNodes(), 
            in.getStart(), 
            in.getGoal(), 
            out
        );
        dfs.hasPath();
        out.writePath(dfs.getPath());
        out.close();
    }
}
