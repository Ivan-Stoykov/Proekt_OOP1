package commands;

import common.JSONException;
import common.JSONManager;
import java.util.Scanner;

/**
 * Клас, който имплементира команда за търсене и извеждане на данните на обект в JSON файл.
 */
public class SearchCommand implements Command{
    private JSONManager manager;
    public SearchCommand(JSONManager manager) {
        this.manager = manager;
    }

    /**
     * Метод, който изпълнява командата за търсене и извеждане на данните на обект в JSON файл.
     * Изисква един параметър:
     * <ol>
     *     <li>Ключ в обекта</li>
     * </ol>
     * @param args Аргументи, подадени от менюто.
     * @throws JSONException Обработване на възникнали грешки по време на изпълнение.
     */
    @Override
    public void execute(Scanner args) throws JSONException {
        if (args.hasNext())
        {
            String key = args.next();
            manager.search(key);
        }
        else throw new JSONException("Command 'search' expects 1 argument");
    }
}
