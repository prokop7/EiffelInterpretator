/**
 * Created by proko on 30-Nov-15.
 */
public class Variable extends Node {
    Type type;

    public Variable(Type type, String name) {
        this.type = type;
        this.name = name;
    }
}
