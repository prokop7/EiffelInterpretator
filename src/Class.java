import java.util.LinkedList;

public class Class extends Node {
    String extendsFrom;
    String implementsClass;
    String generic;

    Class(Access newMod, String Name) {
        access = newMod;
        name = Name;
        type = Type.CLASS;
    }

    public boolean isGeneric() {
        return !generic.isEmpty() || !generic.matches("\\s+");
    }
}