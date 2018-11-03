package View;

import Controller.Controller;

public class View {
    private Controller controller;

    public View(Controller ctrl) {
        this.controller = ctrl;
    }

    public void runCrtPrg() {
        this.controller.allStep();
    }

    public void runAllSteps() {
        int prg_num = 1;
        while(!this.controller.isEmpty()) {
            System.out.println("PROGRAM NUMBER: " + Integer.toString(prg_num));
            this.controller.allStep();
            prg_num++;
        }
    }

}
