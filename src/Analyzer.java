import java.util.regex.Pattern;

public class Analyzer {
    static Pattern classPattern =
            Pattern.compile
                    ("(public |private |protected )?(static |abstract )?(class) (\\w+)\\s*?\\{");


    static Pattern featurePattern =
            Pattern.compile
                    ("(public |private |protected )?(static |abstract )?(void |int |String )?(\\[\\])?(\\w+)\\((.*)\\)\\s*?\\{");

    /**
     * Variable Pattern
     * just to check if there is a variable declaration
     */
    static Pattern variableCheck =
            Pattern.compile
                    ("(?:public\\s+|private\\s+|protected\\s+|final\\s+|static\\s+)?(int\\s+|String\\s+|double\\s+)(\\w+)\\s*(.*);");

    /**
     * Variable with decalration Pattern
     * group 1 - all modifiers
     * group 2 - type
     * group 3 - name
     * group 4 - initialization
     */
    static Pattern variableDeclared =
            Pattern.compile
                    ("(public\\s+|private\\s+|protected\\s+|final\\s+|static\\s+)*(\\w+)\\s+(\\w+)\\s*(?<!=)=(?!=)\\s*(.*);");


    /**
     * Variables by ',' Pattern
     * group 1 - all modifiers
     * group 2 - type
     * group 3 - variables
     */
    static Pattern variableComma =
            Pattern.compile
                    ("((?:public\\s+|private\\s+|protected\\s+|final\\s+|static\\s+)*)(\\w+)\\s+((?:\\w+(?:\\s*,\\s*)?)+);");


    static Pattern forPattern =
            Pattern.compile
                    ("for.*?\\(([int|char|String].* = .*)?;(.*)?;(.*)?\\)((?s:.)*?(;|\\{(?s:.)*?}))");


    static Pattern whilePattern =
            Pattern.compile
                    ("while.*?\\((.*)\\)((?s:.)*?(;|\\{(?s:.)*?}))");

    /**
     * Variable Pattern
     * group 1 - variable visibility
     * group 2 - modifier
     * group 3 - final?
     * group 4 - type
     * group 5 - array braces. Optional.
     * group 6 - array length. Optional.
     * group 7 - names, divided by ','
     * group 8 - name if with initialization. Optional.
     * group 9 - initialization. Optional.
     */
    static Pattern variable =
            Pattern.compile
                    ("(public |private |protected )?(static )?(final )?(\\w+) \\s*(\\[(\\d*)\\]\\s*)*\\s*(?:(\\w+)(?:\\s*,\\s*))*(\\w+)(?:(\\s*(?<!=)=(?!=)\\s*(.*))?);");


    /**
     * if Pattern
     * group 1 - if-type
     * group 2 - condition
     * doesn't give a shit about {-braces or ;.
     */
    static Pattern ifPattern =
            Pattern.compile
                    ("(if|if else|else)\\s*\\((.*)\\)\\s*\\{?");
}
