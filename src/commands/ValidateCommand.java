package commands;

import common.JSONManager;

import java.io.IOException;

public class ValidateCommand implements Command{
    private JSONManager manager;
    public ValidateCommand(JSONManager manager) {
        this.manager = manager;
    }
    @Override
    public void execute(String... command) throws IOException {
        if (command.length ==0)
        {
            manager.validate();
        }
        else System.out.println("Command 'validate' doesn't expect arguments");

    }
}
