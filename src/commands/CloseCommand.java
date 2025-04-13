package commands;

import common.JSONManager;

import java.io.IOException;

public class CloseCommand implements Command {
    private JSONManager manager;

    public CloseCommand(JSONManager manager) {
        this.manager = manager;
    }

    @Override
    public void execute(String... command) throws IOException {
        if (command.length ==0)
        {
            manager.closeFile();
        }
        else System.out.println("Command 'close' doesn't expect arguments");
    }
}
