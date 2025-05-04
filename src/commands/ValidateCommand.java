package commands;

import common.JSONException;
import common.JSONManager;
import java.util.Scanner;

/**
 * Клас, който имплементира команда за валидиране на JSON файл.
 */
public class ValidateCommand implements Command{
    private JSONManager manager;
    public ValidateCommand(JSONManager manager) {
        this.manager = manager;
    }

    /**
     * Метод, който изпълнява командата за валидиране на JSON файл.
     * @param args Аргументи, подадени от менюто (не са нужни в случая).
     * @throws JSONException Обработване на възникнали грешки по време на изпълнение.
     */
    @Override
    public void execute(Scanner args) throws JSONException {
        if (!args.hasNext())
        {
           if(manager.validate()) System.out.println("Valid JSON!");
           else System.out.println("Not a valid JSON");
        }
        else throw new JSONException("Command 'validate' doesn't expect arguments");

    }
}
