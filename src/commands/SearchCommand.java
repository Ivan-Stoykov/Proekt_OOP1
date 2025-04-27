package commands;

import common.JSONManager;

import java.io.IOException;

public class SearchCommand implements Command{
    private JSONManager manager;
    public SearchCommand(JSONManager manager) {
        this.manager = manager;
    }
    @Override
    public void execute(String... command) throws IOException {
        if (command.length ==1)
        {
            String key = command[0];
            manager.search(key);
        }
        else System.out.println("Command 'search' expects 1 argument");
    }
}
