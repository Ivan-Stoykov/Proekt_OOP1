package commands;

import common.JSONManager;

import java.io.IOException;

public class OpenCommand implements Command{
    private JSONManager manager;
    public OpenCommand(JSONManager manager) {
        this.manager = manager;
    }
    @Override
    public void execute(String... command) throws IOException {
        if (command.length ==1)
        {
            String filepath = command[0];
            manager.openFile(filepath);
        }
        else System.out.println("Command 'open' expects 1 argument");

    }
}
