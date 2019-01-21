package GUI;

public class HeapEntry {

    private Integer key;
    private Integer value;

    public HeapEntry(Integer k, Integer v){
        this.key = k;
        this.value = v;
    }

    public Integer getKey() {
        return key;
    }

    public void setKey(Integer key) {
        this.key = key;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
}

