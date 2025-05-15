package JSON;

/**
 * Клас, представляващ числовите стойности в JSON.
 */
public class JSONNumber implements JSONElement{
    private Double value;
    private boolean isInt;

    /**
     * Конструктор, задаващ числовата стойност в JSON.
     * @param value Числова стойност.
     */
    public JSONNumber(double value) {
        this.value = value;
        isInt = false;
    }

    public JSONNumber(int value) {
        this.value = (double)value;
        isInt = true;
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

        if (isInt) return ""+value.intValue();
        else return value.toString();
    }
}
