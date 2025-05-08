package JSON;

import java.util.Objects;

/**
 * Клас, представляващ текстова стойност в JSON.
 */
public class JSONString implements JSONElement{
    private String value;

    /**
     * Конструктор, задаващ текстовата стойност в JSON.
     * @param value Текстова стойност на елемента.
     */
    public JSONString(String value) {
        this.value = value;
    }

    /**
     * Метод, връщащ тектовата стойност на JSON.
     * @return Текстова стойност на елемента.
     */
    public String getValue() {
        return value;
    }

    @Override
    public String getType() {
        return "String";
    }

    /**
     * Метод, връщащ текстовата стойност в JSON.
     * @return Текстова стойност в JSON.
     */
    @Override
    public String toString() {
        return value;
    }

    /**
     * Метод, сравняващ две текстови стойности в JSON. Използва се за сравняване на ключове при обектни стойности.
     * @param o Елемент от тип {@code JSONString}.
     * @return Еднаквост на текстовите стойности.
     */
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        JSONString that = (JSONString) o;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(value);
    }
}
