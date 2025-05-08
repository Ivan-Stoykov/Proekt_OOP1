package JSON;

/**
 * Клас, представляващ празна стойност в JSON.
 */
public class JSONNull implements JSONElement{
    private Object value;

    /**
     * Конструктор, задаващ празната стойноств JSON.
     */
    public JSONNull() {
        this.value = null;
    }

    /**
     * Метод, връщащ празната стойност в JSON.
     * @return Празна стойност.
     */
    public Object getValue() {
        return value;
    }

    @Override
    public String getType() {
        return "Null";
    }
    /**
     * Метод, връщащ нулевата стойност в JSON под формата на символен низ.
     * @return Нулева стойност във формата на символен низ.
     */
    @Override
    public String toString() {
       return value.toString();
    }
}
