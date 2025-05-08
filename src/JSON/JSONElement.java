package JSON;

/**
 * Общ интерфейс за всички възможни стойности на JSON. Класовете за стойност на JSON го имплементират.
 */
public interface JSONElement {
    /**
     * Метод, връщащ типа на стойността.
     * @return Вид стойност.
     */
    public String getType();
}
