package commands;

import common.JSONManager;

import java.io.IOException;

public class ExitCommand implements Command{
    @Override
    public void execute(String... command) throws IOException {
        if (command.length==0)
        {
            System.out.println("Exiting program.");
            System.exit(0);
        }
        else System.out.println("Command 'exit' doesn't expect arguments");

    }
}
