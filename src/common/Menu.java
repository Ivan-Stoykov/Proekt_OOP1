package common;

import commands.*;

import java.util.HashMap;
import java.util.Scanner;

/**
 * Клас, който управлява менюто на програмата.
 */
public class Menu {
    private JSONManager manager;
    private HashMap<String, Command> commands;

    /**
     * Конструктор, зареждащ класа за обработване на команди {@code JSONManager} и списъка с команди .
     */
    public Menu() {
        manager = new JSONManager();
        commands = new HashMap<>();
        loadCommands();
    }

    /**
     * Метод, който чете команди и входни данни и ги обработва.
     * При въвеждане на команда, тя се изпълнява и се обработва за грешки.
     */
    public void startMenu() // common.Menu function
    {
        Scanner s = new Scanner(System.in);
        Command command;
        while(true) {
            String c = s.next();
            if (this.commands.containsKey(c))
            {
                try
                {
                    command = this.commands.get(c);
                    Scanner args = new Scanner(s.nextLine());
                    command.execute(args);
                }
                catch (JSONException e)
                {
                    System.out.println(e.getMessage());
                }
            }
            else {
                s.nextLine();
                System.out.println("Invalid command. Use 'help' for help.");
            }
        }

    }

    /**
     * Метод, зареждащ командите в списъка с команди.
     */
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
