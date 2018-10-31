package Controller;
import Exceptions.Exception1;
import Model.Animal;
import Model.Fish;
import Model.Turtle;
import Repository.*;

import java.util.Arrays;
import java.util.Scanner;


public class Controller {
    private Repo repository;

    public Controller(Repo repository) {
        this.repository = repository;
    }

    public void add() {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Animal type: ");
        String type = keyboard.nextLine();
        Animal dummy;
        if (type.equals("Turtle") || type.equals("turtle")) {
            System.out.println("Name: ");
            String name = keyboard.nextLine();
            System.out.println("Species: ");
            String species = keyboard.nextLine();
            System.out.println("Conservation status: ");
            String status = keyboard.nextLine();
            System.out.println("Common name: ");
            String cname = keyboard.nextLine();
            System.out.println("Size: ");
            String size = keyboard.nextLine();
            System.out.println("Age: ");
            int age = keyboard.nextInt();
            dummy = new Turtle(name, species, status, cname, size, age);
            try {
                this.repository.add(dummy);
            }catch (Exception1 exception1){
                System.out.println(exception1.getMessage());
            }
        }
        else if(type.equals("fish") || type.equals("Fish")) {
                System.out.println("Name: ");
                String name = keyboard.nextLine();
                System.out.println("Species: ");
                String species = keyboard.nextLine();
                System.out.println("Common name: ");
                String cname = keyboard.nextLine();
                System.out.println("Water type: ");
                String water = keyboard.nextLine();
                System.out.println("Scale Colour: ");
                String color = keyboard.nextLine();
                System.out.println("Age: ");
                int age = keyboard.nextInt();
                dummy = new Fish(name, species, age, cname, water, color);
                try {
                    this.repository.add(dummy);
                }catch (Exception1 exception1){
                    System.out.println(exception1.getMessage());
                }
        }
    }

    public void remove() {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Animal type: ");
        String type = keyboard.nextLine();
        System.out.println("Name: ");
        String name = keyboard.nextLine();
        if(type.equals("Turtle") || type.equals("turtle")) {
            Turtle dummy = new Turtle(name,"","","","",0);
            try{
                this.repository.remove(dummy);
            }catch (Exception1 exception1) {
                System.out.println(exception1.getMessage());
            }
        }
        else if(type.equals("Fish") || type.equals("fish")) {
            Fish dummy = new Fish(name,"",0,"","","");
            try{
                this.repository.remove(dummy);
            }catch (Exception1 exception1) {
                System.out.println(exception1.getMessage());
            }
        }
    }

    public Animal[] filter() {
        Animal[] temp = this.repository.getAll();
        Animal[] filtered = new Animal[0];
        int i, pos = 0;
        for(i = 0; i < this.repository.getPos(); i++) {
            if(temp[i].getAge() > 1) {
                filtered = Arrays.copyOf(filtered, filtered.length + 1);
                filtered[pos] = temp[i];
                pos++;
            }
        }
        return filtered;
    }
}
