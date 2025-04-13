package common;

import commands.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class Menu {
    private JSONManager manager;
    private HashMap<String, Command> commands;

    public Menu() {
        manager = new JSONManager();
        commands = new HashMap<>();
        loadCommands();
    }

    public void startMenu() // common.Menu function
    {
        Scanner s = new Scanner(System.in);
        Command command;
        while(true) {
            String commandLine = s.nextLine();
            String[] args = commandLine.split(" ");
            if (this.commands.containsKey(args[0]))
            {
                try
                {
                    command = this.commands.get(args[0]);
                    command.execute(Arrays.stream(args).skip(1).toArray(String[]::new));
                }
                catch (IOException e)
                {
                    System.out.println(e.getMessage());
                }
                default: System.out.println("Nevalidna komanda, opitaite pak ili izpolzvaite 'help' za pomosht");break;
            }
        }while(!command[0].equals("exit"));
    }
    private void loadCommands() {
        this.commands.put("open", new OpenCommand(this.manager));
        this.commands.put("close", new CloseCommand(this.manager));
        this.commands.put("save", new SaveCommand(this.manager));
        this.commands.put("saveas", new SaveAsCommand(this.manager));
        this.commands.put("help", new HelpCommand());
        this.commands.put("validate", new ValidateCommand(this.manager));
        this.commands.put("print", new PrintCommand(this.manager));
        this.commands.put("search", new SearchCommand(this.manager));
        this.commands.put("set", new SetCommand(this.manager));
        this.commands.put("create", new CreateCommand(this.manager));
        this.commands.put("delete", new DeleteCommand(this.manager));
        this.commands.put("move", new MoveCommand(this.manager));
        this.commands.put("exit", new ExitCommand());
    }
}
