package commands;

import common.JSONException;
import common.JSONManager;
import java.util.Scanner;

/**
 * Клас, който имплементира команда за запазване на JSON файл с друго име.
 */
public class SaveAsCommand implements Command{
    private JSONManager manager;
    public SaveAsCommand(JSONManager manager) {
        this.manager = manager;
    }

    /**
     * Метод, който изпълнява командата за запазване на JSON файл с друго име.
     * Изисква два параметъра:
     * <ol>
     *     <li>Име на новия файл</li>
     *     <li>Път, на който да се запази файлът</li>
     * </ol>
     * @param args Аргументи, подадени от менюто.
     * @throws JSONException Обработване на възникнали грешки по време на изпълнение.
     */
    @Override
    public void execute(Scanner args) throws JSONException {
        if (args.hasNext())
        {
            String name = args.next();
            if (args.hasNext()){
            String filepath = args.next();
            manager.saveAs(name, filepath);
            }
            else throw new JSONException("Command 'saveas' expects 2 arguments");
        }
        else throw new JSONException("Command 'saveas' expects 2 arguments");

    }
}
