package commands;

import common.JSONException;
import common.JSONManager;
import java.util.Scanner;

/**
 * Клас, който имплементира команда за задаване на нова стойност на елемент в JSON файл.
 */
public class SetCommand implements Command{
    private JSONManager manager;
    public SetCommand(JSONManager manager) {
        this.manager = manager;
    }

    /**
     * Метод, който изпълнява командата за създаване на елемент в JSON файл.
     * Изисква два параметъра:
     * <ol>
     *     <li>Път на елемент</li>
     *     <li>Текстов низ във формат JSON</li>
     * </ol>
     * @param args Аргументи, подадени от менюто.
     * @throws JSONException Обработване на възникнали грешки по време на изпълнение.
     */
    @Override
    public void execute(Scanner args) throws JSONException {
        if (args.hasNext())
        {
            String path = args.next();
            if (args.hasNext()){
            String text = args.nextLine().substring(1);
            manager.set(path, text);
            }
            else throw new JSONException("Command 'set' expects 2 arguments");
        }
        else throw new JSONException("Command 'set' expects 2 arguments");

    }
}
