package GUI;

public class SymTableEntry {
    private String key;
    private Integer value;

    public SymTableEntry(String key, Integer value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return this.key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Integer getValue() {
        return this.value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
}
