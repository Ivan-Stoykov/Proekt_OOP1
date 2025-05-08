package JSON;

/**
 * Клас, представляващ булева стойност в JSON.
 */
public class JSONBoolean implements JSONElement{
    private Boolean value;

    /**
     * Конструктор, задаващ булевата стойност.
     * @param value Булева стойност.
     */
    public JSONBoolean(boolean value) {
        this.value = value;
    }

    /**
     * Метод, връщащ булевата стойност в JSON.
     * @return Булева стойност в JSON.
     */
    public Boolean getValue() {
        return value;
    }

    @Override
    public String getType() {
        return "Boolean";
    }
    /**
     * Метод, връщащ булевата стойност в JSOM под формата на символен низ.
     * @return Булевата стойност във формата на символен низ.
     */
    @Override
    public String toString() {
        return value.toString();
    }
}
