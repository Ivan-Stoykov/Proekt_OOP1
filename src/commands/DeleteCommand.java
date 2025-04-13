package commands;

import common.JSONManager;

import java.io.IOException;

public class DeleteCommand implements Command{
    private JSONManager manager;
    public DeleteCommand(JSONManager manager) {
        this.manager = manager;
    }
    @Override
    public void execute(String... command) throws IOException {
        if (command.length ==1)
        {
            String path = command[0];
            manager.delete(path);
        }
        else System.out.println("Command 'delete' expects 1 argument");

    }
}
