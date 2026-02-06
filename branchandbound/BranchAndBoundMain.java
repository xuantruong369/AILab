package branchandbound;
import utils.InputReader;
import utils.OutputWriter;

public class BranchAndBoundMain {
    public static void main(String[] args) {
        InputReader reader = new InputReader("branchandbound/inputBranchAndBound.txt");
        OutputWriter out = new OutputWriter("branchandbound/outputBranchAndBound.txt");
        BranchAndBound bab = new BranchAndBound
        (
            reader.getMatrix(),
            reader.getNodes(),
            reader.getStart(),
            reader.getGoal()

        );

        bab.hasPath();
        out.writeTable(bab.buildTable());
        out.writePath(bab.getPath());
        out.writePathCost(bab.getPathCost());
        out.close();
    }
}
