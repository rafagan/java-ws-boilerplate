package vetorlog.conf;

public enum AppEnvironment {
    LOCAL ("local"),
    STAGING ("staging"),
    PRODUCTION ("production");

    private final String name;

    AppEnvironment(String s) {
        name = s;
    }

    public boolean equalsName(String otherName) {
        return name.equals(otherName);
    }

    public String toString() {
        return this.name;
    }
}
