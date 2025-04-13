package commands;

import common.JSONManager;

import java.io.IOException;

public class SaveAsCommand implements Command{
    private JSONManager manager;
    public SaveAsCommand(JSONManager manager) {
        this.manager = manager;
    }

    @Override
    public void execute(String... command) throws IOException {
        if (command.length ==2)
        {
            String name = command[0];
            String filepath = command[1];
            manager.saveAs(name, filepath);
        }
        else System.out.println("Command 'saveas' expects 2 arguments");

    }
}
