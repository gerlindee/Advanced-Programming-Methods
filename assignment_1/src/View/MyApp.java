package View;
import Exceptions.Exception1;
import Model.*;
import Repository.*;
import Controller.*;

public class MyApp {
    public static void main(String[] args) {
        Animal pet1 = new Turtle("Baby", "Testudo kleinmanni", "Critically endangered", "Egyptian turtle", "Large", 25);
        Animal pet2 = new Fish("Nemo","Amphiprion ocellaris", 3, "Common clownfish", "Saltwater", "Orange and white");
        Animal pet3 = new Turtle("Sally", "Graptemys versa", "Least concern", "Texas map turtle", "Small", 0);
        Animal pet4 = new Fish("Roger", "Betta splendens", 1, "Betta", "Warm water", "Steel blue");
        Repo repository = new Repository(10);
        try {
            repository.add(pet1);
            repository.add(pet2);
            repository.add(pet3);
            repository.add(pet4);
        }catch (Exception1 exception1){
            System.out.println(exception1.getMessage());
        }
        Controller controller = new Controller(repository);
        Animal[] result = controller.filter();
        for(Animal ent : result) {
            System.out.println(ent.toString());
        }
    }
}
