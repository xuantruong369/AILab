package astar;

import utils.InputReader;
import utils.OutputWriter;

public class AstarMain {
    public static void main(String[] args) {
        InputReader reader = new InputReader("astar/inputAstar.txt");
        OutputWriter out = new OutputWriter("astar/outputAstar.txt");
        Astar astar = new Astar
        (
            reader.getMatrix(),
            reader.getNodes(),
            reader.getStart(),
            reader.getGoal()

        );

        astar.hasPath();
        out.writeTable(astar.buildTable());
        out.writePath(astar.getPath());
        out.close();
    }
    

}
