package commands;

import common.JSONManager;

import java.io.IOException;

public class MoveCommand implements Command{
    private JSONManager manager;
    public MoveCommand(JSONManager manager) {
        this.manager = manager;
    }
    @Override
    public void execute(String... command) throws IOException {
        if (command.length ==2)
        {
            String from = command[0];
            String to = command[1];
            manager.move(from,to);
        }
        else System.out.println("Command 'move' expects 2 arguments");

    }
}
