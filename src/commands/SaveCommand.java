package commands;

import common.JSONManager;

import java.io.IOException;

public class SaveCommand implements Command{
    private JSONManager manager;
    public SaveCommand(JSONManager manager) {
        this.manager = manager;
    }

    @Override
    public void execute(String... command) throws IOException {
        if (command.length ==1)
        {
            String filepath = command[0];
            manager.save(filepath);
        }
        else System.out.println("Command 'save' expects 1 argument");

    }
}
