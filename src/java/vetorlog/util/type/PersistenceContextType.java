package vetorlog.util.type;

// Tipo do contexto do banco de dados definido no persistence.xml
public enum PersistenceContextType {
    RESOURCE_LOCAL ("resource_local"),
    JTA ("jta");

    private final String name;

    PersistenceContextType(String s) {
        name = s;
    }

    public boolean equalsName(String otherName) {
        return name.equals(otherName);
    }

    public String toString() {
        return this.name;
    }
}
