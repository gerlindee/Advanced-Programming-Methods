package View;

import Controller.Controller;

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
            this.controller.allStep();
            prg_num++;
        }
    }
}
