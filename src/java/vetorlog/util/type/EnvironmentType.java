package vetorlog.util.type;

/**
 * Tipo do ambiente
 * Tipo que define qual é o ambiente da aplicação. Decisões no código, como qual é o persistence-unit do JPA a ser injetado,
 * podem ser realizadas analisando o tipo do ambiente
 */
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
