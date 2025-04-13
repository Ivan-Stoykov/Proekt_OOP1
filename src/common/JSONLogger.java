package common;

public class JSONLogger {
    private String message;

    public JSONLogger() {
        this.message = "VALID JSON!";
    }
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
