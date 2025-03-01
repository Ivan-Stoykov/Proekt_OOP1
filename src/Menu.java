import java.util.Scanner;

public class Menu {
    public static void StartMenu() // Menu function
    {
        Scanner s = new Scanner(System.in);
        String command = "";
        do {
            command = s.nextLine(); // Reading command
            switch (command) //Chooses command and runs it
            {
                case "open":System.out.println("open");break;
                case "close":System.out.println("close");break;
                case "save":System.out.println("save");break;
                case "save_as":System.out.println("save as");break;
                case "help":System.out.println("help");break;
                case "validate":System.out.println("validate");break;
                case "print":System.out.println("print");break;
                case "search":System.out.println("search");break;
                case "set":System.out.println("set");break;
                case "create":System.out.println("create");break;
                case "delete":System.out.println("delete");break;
                case "move":System.out.println("move");break;
                default: System.out.println("Nevalidna komanda, opitaite pak ili izpolzvaite 'help' za pomosht");break;
            }
        }while(!command.equals("exit"));
    }
}
