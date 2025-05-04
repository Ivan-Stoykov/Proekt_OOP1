package commands;

import common.JSONException;
import common.JSONManager;
import java.util.Scanner;

/**
 * Клас, който имплементира команда за преместване на елемент в JSON файл.
 */
public class MoveCommand implements Command{
    private JSONManager manager;
    public MoveCommand(JSONManager manager) {
        this.manager = manager;
    }

    /**
     *vМетод, който изпълнява командата за преместване на елемент в JSON файл.
     * Изисква два параметъра:
     * <ol>
     *     <li>Път на елемент за преместване</li>
     *     <li>Път, където да се постави елемента</li>
     * </ol>
     * @param args Аргументи, подадени от менюто.
     * @throws JSONException Обработване на възникнали грешки по време на изпълнение.
     */
    @Override
    public void execute(Scanner args) throws JSONException {
        if (args.hasNext())
        {
            String from = args.next();
            if (args.hasNext())
            {
            String to = args.next();
            manager.move(from,to);
            }
            else throw new JSONException("Command 'move' expects 2 arguments");
        }
        else throw new JSONException("Command 'move' expects 2 arguments");

    }
}
