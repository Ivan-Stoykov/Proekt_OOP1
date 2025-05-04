package commands;

import java.util.Scanner;

/**
 * Клас, който имплементира команда за помощ с приложението.
 */
public class HelpCommand implements Command{
    /**
     * Метод, който изпълнява командата за помощ с приложението.
     * @param args Аргументи, подадени от менюто (не са нужни в случая).
     */
    @Override
    public void execute(Scanner args) {
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
}
