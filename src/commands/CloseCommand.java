package commands;

import common.JSONException;
import common.JSONManager;
import java.util.Scanner;

/**
 * Клас, който имплементира команда за затваряне на JSON файл.
 */
public class CloseCommand implements Command {
    private JSONManager manager;

    public CloseCommand(JSONManager manager) {
        this.manager = manager;
    }

    /**
     * Метод, който изпълнява командата за затваряне на файл.
     * @param args Аргументи, подадени от менюто.
     * @throws JSONException Обработва грешки, възникнали по време на изпълнение.
     */
    @Override
    public void execute(Scanner args) throws JSONException {
        if (!args.hasNext())
        {
            manager.closeFile();
        }
        else throw new JSONException("Command 'close' doesn't expect arguments");
    }
}
