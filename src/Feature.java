import java.util.List;

public class Feature extends Node {
    Access access = Access.PUBLIC;
    Modifier modifier = Modifier.NONE;
    List<Feature> args;

    Feature() {

    }

    Feature(String name) {
        this.name = name;
    }

    Feature(Access newAcc, Modifier newMod, Type newType, String name) {
        this.name = name;
        this.access = newAcc;
        this.modifier = newMod;
        this.type = newType;
    }

    public boolean hasArgs() {
        return !args.isEmpty() || args != null || args.size() > 0;
    }

    public boolean isVariable() {
        return !hasArgs() && type != Type.VOID;
    }

    public boolean isLine() {
        return !isVariable() && access == null && type == null && children.isEmpty() && args.isEmpty();
    }

}
