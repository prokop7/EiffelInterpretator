import java.io.*;
import java.util.LinkedList;
import java.util.regex.Matcher;

public class Main {
    static Node root;
    static Node current;

    public static void main(String[] args) throws IOException {
        String path = "C:\\Users\\proko\\IdeaProjects\\hello_world\\src\\";
        BufferedReader reader = new BufferedReader(new FileReader(path + "Main.java"));
        BufferedWriter writer = new BufferedWriter(new FileWriter("test.e"));
        convertToTree(reader);
        /*root = new Class(Access.PUBLIC, "Main");
        root.name = root.name.replaceAll("Main", "APPLICATION");
        //root.access = Access.PUBLIC;
        Feature m = new Feature(Access.PUBLIC, Modifier.STATIC, Type.VOID, "main");
        m.name = m.name.replaceAll("main", "make");
        root.children.add(m);
        m.children.add(new Node("System.out.println(\"Hello, World!\");"));*/
        runDFS(root, writer);
        writer.close();
    }

    private static void runDFS(Node current, BufferedWriter writer) throws IOException {
        if (current.type == Type.CLASS) {
            writer.write(current.type + "\n\t" + current.name.toUpperCase() + "\n\ninherit\n\tARGUMENTS\n\n");
            for (Node child : current.children) {
                runDFS(child, writer);
                //writer.write("\n");
            }
            writer.write("end");
        }
        if (current.type != Type.CLASS && current.type != Type.NONE && current.type != Type.INTERFACE) {
            if (current.name.compareTo("main") == 0) {
                writer.write("create\n\tmake\n\n");
                writer.write("feature\n\t\t-- Run application");
            }
            writer.write("\n\t" + current.name.replaceAll("main", "make"));
            if (current.type != Type.VOID) writer.write(" : " + current.type.toString());
            writer.write("\n\t\tlocal\n\t\t");
            for (Variable var : current.variables)
                writer.write("\t" + var.type.toString() + " : " + var.name + "\n\t\t");
            writer.write("do\n");
            for (Node child : current.children) {
                runDFS(child, writer);
            }
            writer.write("\t\tend\n");
        }
        if (current.type == Type.NONE && current.name.compareTo("") != 0) {
            String name = current.name;
            name = name.replaceAll("System.out.println", "print");
            name = name.replaceAll("(?<!=)=(?!=)", ":=");
            if (name.matches("return.*"))
                name = name.replaceAll("return\\s+\\(?", "result:= ").replaceAll("\\)\\s*;", ";");
            writer.write("\t\t\t" + name + "\n");
        }
    }

    private static LinkedList<Variable> addVariables(LinkedList<Variable> list, Variable var) {
        int count = 0;
        for (Variable item : list) {
            if (item.type == var.type) {
                item.name = item.name + ", " + var.name;
                break;
            }
            count++;
        }
        if (count == list.size())
            list.add(var);
        return list;
    }

    private static void convertToTree(BufferedReader reader) throws IOException {
        String line = reader.readLine();
        if (line == null) return;
        else line = line.trim();
        Matcher aClass, feature, loop, var;
        aClass = Analyzer.classPattern.matcher(line);
        feature = Analyzer.featurePattern.matcher(line);
        loop = Analyzer.forPattern.matcher(line);
        var = Analyzer.variableCheck.matcher(line);
        if (aClass.find()) {
            Class temp = new Class(Access.valueOf(aClass.group(1).toUpperCase().trim()), aClass.group(4).replaceAll("Main", "APPLICATION"));
            root = temp;
            current = root;
            convertToTree(reader);
        } else if (feature.find()) {
            Feature temp = new Feature(Access.valueOf(feature.group(1).toUpperCase().trim()), Modifier.valueOf(feature.group(2).toUpperCase().trim()), Type.valueOf(feature.group(3).toUpperCase().trim().replaceAll("INT", "INTEGER")), feature.group(5));
            root.children.add(temp);
            current = temp;
            convertToTree(reader);
        } else if (line.matches("}")) {
            convertToTree(reader);
        } else if (var.find()) {
            var = Analyzer.variableDeclared.matcher(line);
            Variable temp;
            if (var.find()) {
                temp = new Variable(Type.valueOf(var.group(2).toUpperCase().trim().replaceAll("INT", "INTEGER")), var.group(3));
                current.children.add(new Node(line.replaceAll(var.group(2) + "\\s*", "")));
            } else {
                var = Analyzer.variableComma.matcher(line);
                var.find();
                temp = new Variable(Type.valueOf(var.group(2).toUpperCase().trim().replaceAll("INT", "INTEGER")), var.group(3));
            }
            //temp =current.variables.getFirst();

            current.variables = addVariables(current.variables, temp);
            convertToTree(reader);
        } else {
            Node temp = new Node(line);
            current.children.add(temp);
            convertToTree(reader);
        }
    }
}
//TODO convert to camel_case key.replaceAll("(.)(\\p{Upper})", "$1_$2").toLowerCase();