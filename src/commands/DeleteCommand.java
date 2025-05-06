package commands;

import common.JSONException;
import common.JSONManager;
import java.util.Scanner;

/**
 * Клас, който имплементира команда за изтриване на елемент в JSON файл.
 */
public class DeleteCommand implements Command{
    private JSONManager manager;
    public DeleteCommand(JSONManager manager) {
        this.manager = manager;
    }

    /**
     * Метод, който изпълнява командата за изтриване на елемент в JSON файл.
     * Изисква един параметър:
     * <ol>
     *     <li>Път на елемент за изтриване</li>
     * </ol>
     * @param args Аргументи, подадени от менюто.
     * @throws JSONException при неуспешно триене на елемента се извежда съобщение.
     */
    @Override
    public void execute(Scanner args) throws JSONException {
        if (args.hasNext())
        {
            String path = args.next();
            String[] objects  = path.split("/");
            if (manager.delete(path))
            {
                System.out.println("Deleted object: \"" + objects[objects.length-1] + "\".");
            }
            else throw new JSONException("Object does not exist");
        }
        else throw new JSONException("Command 'delete' expects 1 argument");

    }
}
