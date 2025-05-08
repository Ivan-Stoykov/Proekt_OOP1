package JSON;

/**
 * Клас, представляващ числовите стойности в JSON.
 */
public class JSONNumber implements JSONElement{
    private Double value;

    /**
     * Конструктор, задаващ числовата стойност в JSON.
     * @param value Числова стойност.
     */
    public JSONNumber(double value) {
        this.value = value;
    }

    /**
     * Метод, връщащ числовата стойност в JSON.
     * @return Числова стойност.
     */
    public Double getValue() {
        return value;
    }

    @Override
    public String getType() {
        return "Number";
    }
    /**
     * Метод, връщащ числовата стойност в JSON под формата на символен низ.
     * @return Числова стойност във формата на символен низ.
     */
    @Override
    public String toString() {
        return value.toString();
    }
}
