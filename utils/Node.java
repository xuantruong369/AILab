package utils;
public class Node implements Comparable<Node> {
    private String name;
    private Integer weight;
    
    public Node(String name, Integer weight) {
        this.name = name;
        this.weight = weight;
    } 

    public String getName() {
        return name;
    }

    public Integer getWeight() {
        return weight;
    }

    @Override
    public int compareTo(Node other) {
        return Integer.compare(this.weight, other.weight);
    }

    @Override
    public String toString() {
        String output;
        if (weight == -1) {
            output = name;
        }
        else {
            output = name + "(" + weight + ")";
        }
        return output;
    }
}
