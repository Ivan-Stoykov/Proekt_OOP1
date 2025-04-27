package commands;

import common.JSONManager;

import java.io.IOException;

public class PrintCommand implements Command{
    private JSONManager manager;
    public PrintCommand(JSONManager manager) {
        this.manager = manager;
    }
    @Override
    public void execute(String... command) throws IOException {
        if (command.length ==0)
        {
            System.out.println(manager.printFile());
        }
        else System.out.println("Command 'print' doesn't expect arguments");

    }
}
