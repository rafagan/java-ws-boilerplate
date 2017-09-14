package vetorlog.util.type;

public enum EnvironmentType {
    LOCAL ("local"),
    STAGING ("staging"),
    PRODUCTION ("production"),
    UNDEFINED ("undefined");

    private final String name;

    EnvironmentType(String s) {
        name = s;
    }

    public boolean equalsName(String otherName) {
        return name.equals(otherName);
    }

    public String toString() {
        return this.name;
    }
}
