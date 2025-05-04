package common;

public class Validation {
    private String json;
    private int index;

    public Validation() {
        this.index = 0;
    }

    public void setJson(String json) {
        this.json = json;
        this.index = 0;
    }

    public boolean validate()
    {
        try
        {
            validateValue();
            return true;
        } catch (JSONException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    private void validateValue() throws JSONException
    {
        if (index >= json.length()) throw new JSONException("Unexpected end of input at index " + index);
        char c = json.charAt(index);
        if (c == '"') {
            validateString();
            return;
        }
        if (c == '{'){
            validateObject();
            return;
        }
        if (c == '['){
            validateArray();
            return;
        }
        if (Character.isDigit(c) || c == '-'){
            validateDigit();
            return;
        }
        if (json.startsWith("true", index)) {
            index += 4;
            return;
        }
        if (json.startsWith("false", index)) {
            index += 5;
            return;
        }
        if (json.startsWith("null", index)) {
            index += 4;
            return;
        }

        throw new JSONException("Invalid value at index " + index);
    }

    private void validateString() throws JSONException
    {
        if (json.charAt(index) != '"') throw new JSONException("Expected '\"' at index " + index);
        index++;
        while (index < json.length())
        {
            char c = json.charAt(index++);
            if (c == '"') return;
        }
        throw new JSONException("Unclosed string at index " + index);
    }

    private void validateDigit() throws JSONException
    {
        if (json.charAt(index) == '-') index++;
        while (index < json.length() && Character.isDigit(json.charAt(index))) index++;
        if (index < json.length()&& json.charAt(index) == '.')
        {
            index++;
            if (index >= json.length() || !Character.isDigit(json.charAt(index)))
                throw new JSONException("Invalid number format at index " + index);
            while (index < json.length() && Character.isDigit(json.charAt(index))) index++;
        }
    }

    private void validateArray() throws JSONException
    {
        if (json.charAt(index) != '[') throw new JSONException("Expected '[' at index " + index);
        index++;
        if (index < json.length() && json.charAt(index) == ']')
        {
            index++;
            return;
        }

        while(true) {
            validateValue();
            if (index < json.length() && json.charAt(index) == ',') index++;
            else if (index < json.length() && json.charAt(index) == ']')
            {
                index++;
                break;
            }
            else throw new JSONException("Expected ',' or ']' in array at index " + index);

        }
    }

    private void validateObject() throws JSONException
    {
        if (json.charAt(index) != '{') throw new JSONException("Expected '{' at index " + index);
        index++;
        if (index < json.length() && json.charAt(index) == '}')
        {
            index++;
            return;
        }

        while(true) {
            if (index >= json.length() || json.charAt(index) != '"') throw new JSONException("Expected string key at index " + index);
            validateString();
            if (index >= json.length() || json.charAt(index) != ':') throw new JSONException("Expected ':' at index " + index);
            index++;
            validateValue();
            if (index < json.length() && json.charAt(index) == ',') index++;
            else if (index < json.length() && json.charAt(index) == '}') {
                index++;
                break;
            }
            else throw new JSONException("Expected ',' or '}' in object at index " + index);
        }
    }

}