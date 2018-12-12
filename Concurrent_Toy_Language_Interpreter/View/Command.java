package View;

public abstract class Command {
    private String key;
    private String description;

    Command(String k, String d) {
        this.key = k;
        this.description = d;
    }

    public abstract void execute();

    String getKey() {
        return this.key;
    }

    String getDescription() {
        return this.description;
    }
}
