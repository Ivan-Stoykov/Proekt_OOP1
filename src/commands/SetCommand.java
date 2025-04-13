package commands;

import common.JSONManager;

import java.io.IOException;

public class SetCommand implements Command{
    private JSONManager manager;
    public SetCommand(JSONManager manager) {
        this.manager = manager;
    }
    @Override
    public void execute(String... command) throws IOException {
        if (command.length ==2)
        {
            String path = command[0];
            String text = command[1];
            manager.set(path, text);
        }
        else System.out.println("Command 'set' expects 2 arguments");

    }
}
