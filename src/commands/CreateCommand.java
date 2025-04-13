package commands;

import common.JSONManager;

import java.io.IOException;

public class CreateCommand implements Command{
    private JSONManager manager;
    public CreateCommand(JSONManager manager) {
        this.manager = manager;
    }
    @Override
    public void execute(String... command) throws IOException {
        if (command.length ==2)
        {
            String path = command[0];
            String text = command[1];
            manager.create(path,text);
        }
        else System.out.println("Command 'create' expects 2 arguments");

    }
}
