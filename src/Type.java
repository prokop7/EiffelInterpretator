public enum Type {
    CLASS("class"),
    NONE("none"),
    INTERFACE("interface"),
    VOID("Void"),
    STRING("STRING"),
    INTEGER("INTEGER"),
    INTEGER_32("INTEGER_32"),
    CHRARACTER("CHARACTER");

    //region Returning String
    private final String value;

    private Type(final String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
    //endregion
}