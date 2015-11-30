public enum Access {
    PRIVATE("private"),
    PROTECTED("protected"),
    PUBLIC("public"),
    NONE("none");

    //region Returning String
    private final String value;

    private Access(final String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
    //endregion
}

