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
                case "save":if (command.length >1) {
                    String filepath = command[1];
                    manager.save(filepath);}break;
                case "saveas":if (command.length >1) {
                    String name = command[1];
                    String filepath = "";
                    if (command.length > 2)filepath = command[2];
                    manager.saveAs(name, filepath);
                }break;
                case "help":help();break;
                case "validate": manager.validate();break;
                case "print": manager.printFile();break;
                case "search":System.out.println("search");break;
                case "set":if (command.length >1) {
                    String filepath = command[1];
                    String text = command[2];
                    manager.set(filepath, text);
                }
                else System.out.println("please enter 2 paths");break;
                case "create":{
                    if (command.length >1) {
                        String filepath = command[1];
                        String text = command[2];
                        manager.create(filepath, text);
                    }
                    else System.out.println("please enter path");
                }break;
                case "delete": if (command.length >1) {
                    String filepath = command[1];
                    manager.delete(filepath);
                }
                else System.out.println("please enter path");break;
                case "move":if (command.length >1) {
                    String from = command[1];
                    String to = command[2];
                    manager.move(from, to);
                }
                else System.out.println("please enter 2 paths");break;
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
