package JSON;

import java.util.ArrayList;

/**
 * Клас, представляващ стойността 'масив' в JSON.
 */
public class JSONArray implements JSONElement{
    private ArrayList<JSONElement> value;

    /**
     * Конструкор, задаващ масива от JSON елементи.
     * @param value масив от JSON елементи.
     */
    public JSONArray(ArrayList<JSONElement> value) {
        this.value = value;
    }

    /**
     * Метод, връщащ масива от JSON елементи.
     * @return Масив от JSON елементи.
     */
    public ArrayList<JSONElement> getValue() {
        return value;
    }

    @Override
    public String getType() {
        return "Array";
    }

    /**
     * Метод, връщащ масива в JSON под формата на символен низ.
     * @return Стойностите на масива във формата на символен низ.
     */
    @Override
    public String toString() {
        return value.toString();
    }
}
