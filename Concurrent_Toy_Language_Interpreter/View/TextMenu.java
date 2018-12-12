package View;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

@SuppressWarnings("InfiniteLoopStatement")
class TextMenu {
    private Map<String, Command> commands;

    TextMenu() {
        this.commands = new HashMap<String, Command>();
    }

    void addCommand(Command cmd) {
        this.commands.put(cmd.getKey(), cmd);
    }

    private void printMenu() {
        for(Command cmd : this.commands.values()) {
            String line = String.format("%4s: %s", cmd.getKey(), cmd.getDescription());
            System.out.println(line);
        }
    }

    void show() {
        Scanner scanner = new Scanner(System.in);
        while(true) {
            this.printMenu();
            System.out.println("Input the option: ");
            String key = scanner.nextLine();
            Command com = commands.get(key);
            if(com == null) {
                System.out.println("Invalid option.");
                continue;
            }
            com.execute();
        }
    }
}