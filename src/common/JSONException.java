package common;

/**
 *  Клас за обработване на грешки
 */
public class JSONException extends Exception {

    /**
     * Конструктор, приемащ съобщение на грешката
     * @param message съобщение на грешката
     */
    public JSONException(String message) {
        super(message);
    }
}
