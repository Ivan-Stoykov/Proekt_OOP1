package commands;

import java.util.Scanner;

/**
 * Клас, който имплементира команда за излизане от програмата.
 */
public class ExitCommand implements Command{
    /**
     * Метод, който изпълнява командата за излизане от програмата.
     * @param args Аргументи, подадени от менюто (не са нужни в случая).
     */
    @Override
    public void execute(Scanner args) {
            System.out.println("Exiting program.");
            System.exit(0);
    }
}
