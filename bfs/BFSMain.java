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
            in.getGoal(),
            out
        );
        bfs.hasPath();
        out.writePath(bfs.getPath());
        out.close();
    }
}
