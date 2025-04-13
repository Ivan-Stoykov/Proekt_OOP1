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
    }
}
