package bfs;

import utils.InputReader;
import utils.OutputWriter;

public class BFSMain {
    public static void main(String[] args) {
        InputReader in = new InputReader("bfs/inputBfs.txt");
        OutputWriter out = new OutputWriter("bfs/outputBfs.txt");

        BFS bfs = new BFS
        (
            in.getMatrix(),
            in.getNodes(),
            in.getStart(),
            in.getGoal()
        );
        bfs.hasPath();
        out.writeTable(bfs.buildTable());
        out.writePath(bfs.getPath());
        out.close();
    }
}
