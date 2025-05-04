package commands;

import common.JSONException;
import common.JSONManager;
import java.util.Scanner;

/**
 * Клас, който имплементира команда за отваряне на JSON файл.
 */
public class OpenCommand implements Command{
    private JSONManager manager;
    public OpenCommand(JSONManager manager) {
        this.manager = manager;
    }

    /**
     * Метод, който изпълнява командата за отваряне на JSON файл.
     * Изисква един параметър:
     * <ol>
     *     <li>Път на файл за отваряне</li>
     * </ol>
     * @param args Аргументи, подадени от менюто.
     * @throws JSONException Обработване на възникнали грешки по време на изпълнение.
     */
    @Override
    public void execute(Scanner args) throws JSONException {
        if (args.hasNext())
        {
            String filepath = args.next();
            manager.openFile(filepath);
        }
        else throw new JSONException("Command 'open' expects 1 argument");

    }
}
