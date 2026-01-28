package hillclimbing;
import utils.InputReader;
import utils.OutputWriter;

public class HillClimbingMain {
    public static void main(String[] args) {
        InputReader in = new InputReader("hillclimbing/inputHillClimbing.txt");
        OutputWriter out = new OutputWriter("hillclimbing/outputHillClimbing.txt");
        HillClimbing hill = new HillClimbing
        (
            in.getMatrix(), 
            in.getNodes(), 
            in.getStart(), 
            in.getGoal(), 
            out
        );
        hill.hasPath();
        out.writePath(hill.getPath());
        out.close();
    }
}
