package View;

import Controller.Controller;
import Exceptions.*;

import java.io.IOException;

public class RunExample extends Command {
    private Controller controller;

    RunExample(String key, String description, Controller ctrl) {
        super(key, description);
        this.controller = ctrl;
    }

    @Override
    public void execute() {
        int prg_num = 1;
        while(!this.controller.isEmpty()) {
            System.out.println("PROGRAM NUMBER: " + Integer.toString(prg_num));
            try {
                this.controller.allStep();
            } catch (NullPointerException ex7) {
                System.out.println("Missing file!");
            } catch (IOException ex2) {
                System.out.println(ex2.toString());
            } catch (UndefinedVariableException ex4) {
                System.out.println(ex4.getMessage());
            } catch (DivisionByZeroException ex5) {
                System.out.println(ex5.getMessage());
            } catch (UndefinedOperationException ex6) {
                System.out.println(ex6.getMessage());
            } catch (HeapReadingException ex7) {
                System.out.println(ex7.getMessage());
            }
            prg_num++;
        }
    }
}
