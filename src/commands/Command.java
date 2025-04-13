package commands;

import java.io.IOException;

public interface Command {
    public void execute(String... command)throws IOException;
}
