package commands;

import common.JSONException;
import common.JSONManager;
import java.util.Scanner;

/**
 * Клас, който имплементира команда за извеждане на съдържанието на JSON файл.
 */
public class PrintCommand implements Command{
    private JSONManager manager;
    public PrintCommand(JSONManager manager) {
        this.manager = manager;
    }

    /**
     * Метод, който изпълнява командата за извеждане на съдържанието JSON файл.
     * @param args Аргументи, подадени от менюто (не са нужни в случая).
     * @throws JSONException Обработване на възникнали грешки по време на изпълнение.
     */
    @Override
    public void execute(Scanner args) throws JSONException {
        if (!args.hasNext())
        {
            System.out.println(manager.printFile());
        }
        else throw new JSONException("Command 'print' doesn't expect arguments");

    }
}
