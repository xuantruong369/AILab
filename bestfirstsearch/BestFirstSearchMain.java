package bestfirstsearch;

import utils.InputReader;
import utils.OutputWriter;

public class BestFirstSearchMain {
    public static void main(String[] args) {
        InputReader in = new InputReader("bestfirstsearch/inputBestFirstSearch.txt");
        OutputWriter out = new OutputWriter("bestfirstsearch/outputBestFirstSearch.txt");

        BestFirstSearch best = new BestFirstSearch
        (
            in.getMatrix(), 
            in.getNodes(),
            in.getStart(),
            in.getGoal()
        );

        best.hasPath();
        out.writeTable(best.buildTable());
        out.writePath(best.getPath());
        out.close();
    }
}
