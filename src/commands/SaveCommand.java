package commands;

import common.JSONException;
import common.JSONManager;
import java.util.Scanner;

/**
 * Клас, който имплементира команда за запазване на JSON файл.
 */
public class SaveCommand implements Command{
    private JSONManager manager;
    public SaveCommand(JSONManager manager) {
        this.manager = manager;
    }

    /**
     * Метод, който изпълнява командата за запазване на JSON файл.
     * Изисква един параметър:
     * <ol>
     *     <li>Път, на който да се запази файлът</li>
     * </ol>
     * @param args Аргументи, подадени от менюто.
     * @throws JSONException Обработване на възникнали грешки по време на изпълнение.
     */
    @Override
    public void execute(Scanner args)throws JSONException {
        if (args.hasNext())
        {
            String filepath = args.next();
            manager.save(filepath);
        }
        else manager.save("");

    }
}
