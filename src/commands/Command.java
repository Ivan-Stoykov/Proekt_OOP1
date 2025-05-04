package commands;

import common.JSONException;
import java.util.Scanner;

/**
 * Общ интерфейс на командите, които могат да се изпълняват.
 * Имплементира се от класовете на командите за да реализират метод {@code execute} за изпълнение на командата.
 */
public interface Command {
    /**
     * Метод за изпълняване на команда.
     * @param args Аргументи, подадени от менюто.
     * @throws JSONException Обработване на възникнали грешки по време на изпълнение.
     */
    public void execute(Scanner args)throws JSONException;
}
