package commands;

import common.JSONException;
import common.JSONManager;
import java.util.Scanner;

/**
 * Клас, който имплементира команда за създаване на елемент в JSON файл.
 */
public class CreateCommand implements Command{
    private JSONManager manager;
    public CreateCommand(JSONManager manager) {
        this.manager = manager;
    }

    /**
     * Метод, който изпълнява командата за създаване на елемент в JSON файл.
     * Изисква два параметъра:
     * <ol>
     *     <li>Път на създаване</li>
     *     <li>Текстов низ във формат JSON</li>
     * </ol>
     * @param args Аргументи, подадени от менюто.
     * @throws JSONException Обработва грешки, възникнали по време на изпълнение.
     */
    @Override
    public void execute(Scanner args) throws JSONException {
        if (args.hasNext())
        {
            String path = args.next();
            if (args.hasNext()){
            String text = args.nextLine().substring(1);
            String[] objects  = path.split("/");
            manager.create(path,text);
                System.out.println("Created object: \"" + objects[objects.length-1] + "\".");
            }
            else throw new JSONException("Command 'create' expects 2 arguments");
        }
        else throw new JSONException("Command 'create' expects 2 arguments");

    }
}
