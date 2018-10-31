package Model;

public class Turtle implements Animal {
    private String Name;
    private String Species;
    private String ConservationStatus;
    private String CommonName;
    private String Size;
    private int Age;

    public int getAge() {
        return this.Age;
    }

    public Turtle(String Name, String Species, String ConservationStatus, String CommonName, String Size, int Age) {
        this.Name = Name;
        this.Species = Species;
        this.ConservationStatus = ConservationStatus;
        this.CommonName = CommonName;
        this.Size = Size;
        this.Age = Age;
    }

    public String getID() {
        return this.Name;
    }

    public boolean equals(Animal ent) {
        if(this.Name.equals(ent.getID()))
            return true;
        return false;
    }

    public String toString() {
        return this.Name + " | " + this.Species + " | " + this.ConservationStatus + " | " + this.CommonName +  " | " + this.Size;
    }
}
