package JSON;

import java.util.*;

/**
 * Клас, представляващ обектната стойност на JSON.
 */
public class JSONObject implements JSONElement{
    private HashMap<JSONString, JSONElement> value;

    /**
     * Конструктор, задаващ обектната стойност на елемента.
     * @param value Обектната стойност на елемента.
     */
    public JSONObject(HashMap<JSONString, JSONElement> value) {
        this.value = value;
    }

    /**
     * Метод, връщащ елемента на обектната стойност.
     * @return Стойност на обекта в JSON.
     */
    public HashMap<JSONString, JSONElement> getValue() {
        return value;
    }
    @Override
    public String getType() {
        return "Object";
    }

    /**
     * Метод, задаващ начин за изписване на обектната стойност на JSON във формата на символен низ.
     * @return Обектната стойност на JSON във формата на символен низ.
     */
    @Override
    public String toString() {
        boolean inQuotes = false;
        final StringBuilder sb = new StringBuilder();
        String text = value.toString();
        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i) == '"') inQuotes = !inQuotes;
            if (text.charAt(i) == ' ' && !inQuotes) continue;
            if (text.charAt(i) == '=' && !inQuotes){
                sb.append(':');
                continue;
            }
            sb.append(text.charAt(i));
        }
        return sb.toString();
    }

    /**
     * Метод, извеждащ всяка стойност, съдържаща се в обекта, и нейния ключ.
     */
    public void listValues() {
        for (JSONString key : value.keySet()) {
            JSONString k = key;
            JSONElement v = value.get(k);
            System.out.println(k + ": " + v);
        }
    }
}
