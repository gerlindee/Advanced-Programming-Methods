package Model;

public class Fish implements Animal {
    private String Name;
    private String Species;
    private String CommonName;
    private String Water;
    private String ScaleColors;
    private int Age;

    public int getAge() {
        return this.Age;
    }

    public Fish(String Name, String Species, int Age, String CommonName, String Water, String ScaleColors) {
        this.Name = Name;
        this.Species = Species;
        this.CommonName = CommonName;
        this.Water = Water;
        this.ScaleColors = ScaleColors;
        this.Age = Age;
    }

    public String toString() {
        return this.Name + " | " + this.Species + " | " + this.CommonName + " | " + this.Water + " | " + this.ScaleColors;
    }

    public String getID() {
        return this.Name;
    }

    public boolean equals(Animal ent) {
        return Name.equals(ent.getID());
    }
}
