import java.util.LinkedList;

public class Node {

    public Node(String name) {
        this.name = name;
    }

    protected String name;
    protected Type type = Type.NONE;
    protected Access access = Access.PUBLIC;
    protected Modifier modifier = Modifier.NONE;
    protected LinkedList<Node> children = new LinkedList<>();
    public LinkedList<Variable> variables = new LinkedList<>();

    public Node() {
    }
}