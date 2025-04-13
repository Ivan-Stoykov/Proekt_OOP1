package commands;

import java.io.IOException;

public class HelpCommand implements Command{
    @Override
    public void execute(String... command) throws IOException {
        if (command.length == 0)
        {
            System.out.println("open <path>: Opens a file to work from.");
            System.out.println("close: Closes the currently opened file.");
            System.out.println("save <path>: Saves current file.");
            System.out.println("saveas <file> <path>: Creates new JSON file with current values.");
            System.out.println("validate: Validates if the opened file is in correct format for JSON.");
            System.out.println("print: Prints out the current file.");
            System.out.println("search <key>: Searches for a key in current file and prints out its content if found.");
            System.out.println("set <path>: Overrides JSON file.");
            System.out.println("create <path>: Creates element in JSON file.");
            System.out.println("delete <path>: Deletes element in JSON file.");
            System.out.println("move <from> <to>: Moves elements in JSON file.");
            System.out.println("exit: Exits program.");
        }
        else System.out.println("Command 'help' doesn't expect arguments");

    }
}
