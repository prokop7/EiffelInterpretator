public enum Modifier {
    STATIC("static"),
    ABSTRACT("abstract"),
    NONE("none");

    //region Returning String
    private final String value;

    private Modifier(final String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
    //endregion
}
