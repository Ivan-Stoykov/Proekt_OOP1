import java.util.Scanner;

public class Menu {
    public static void startMenu() // Menu function
    {
        JSONManager manager = new JSONManager();
        Scanner s = new Scanner(System.in);
        String commandLine = "";
        String[] command;
        do {
            commandLine = s.nextLine();
            command = commandLine.split(" ");
            switch (command[0])
            {
                case "open":
                {
                    if (command.length >1) {
                        String filepath = command[1];
                        manager.openFile(filepath);
                    }
                    else System.out.println("please enter path");
                }break;
                case "close":manager.closeFile();break;
                case "save":System.out.println("save");break;
                case "save_as":System.out.println("save as");break;
                case "help":help();break;
                case "validate": manager.validate();break;
                case "print": manager.printFile();break;
                case "search":System.out.println("search");break;
                case "set":System.out.println("set");break;
                case "create":System.out.println("create");break;
                case "delete": System.out.println("delete");break;
                case "move":System.out.println("move");break;
                case "exit", "":break;
                default: System.out.println("Nevalidna komanda, opitaite pak ili izpolzvaite 'help' za pomosht");break;
            }
        }while(!command[0].equals("exit"));
    }

    private static void help()
    {
        System.out.println("open: Opens a file to work from.");
        System.out.println("close: Closes the currently opened file.");
        System.out.println("save <path>: Saves current file.");
        System.out.println("validate: Validates if the opened file is in correct format for JSON.");
        System.out.println("print: Prints out the current file.");
        System.out.println("search <key>: Searches for a key in current file and prints out its content if found.");
        System.out.println("set <path>: Overrides JSON file.");
        System.out.println("create <path>: Creates element in JSON file.");
        System.out.println("delete <path>: Deletes JSON file.");
        System.out.println("move <from> <to>: Moves elements in JSON file.");
        System.out.println("saveas <file> <path>: Creates new JSON file with current values.");
    }
}
