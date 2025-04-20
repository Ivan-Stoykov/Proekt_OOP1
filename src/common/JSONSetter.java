package common;

import java.util.ArrayList;
import java.util.HashMap;

public class JSONSetter {
    private Object json;

    public JSONSetter(String jsonText) {
        this.json = parseJson(jsonText);
    }

    public Object getJson() {
        return json;
    }

    private Object parseJson(String json) {
        if (json.startsWith("[")) {
            return parseArray(json.substring(1, json.length() - 1));
        } else if (json.startsWith("{")) {
            return parseObject(json.substring(1, json.length() - 1));
        } else {
            return parseValue(json);
        }
    }

    private ArrayList<Object> parseArray(String content) {
        ArrayList<Object> result = new ArrayList<>();
        int i = 0;
        while (i < content.length()) {
            char c = content.charAt(i);
            if (c == '[') {
                int start = i, brackets = 1;
                i++;
                while (i < content.length() && brackets > 0) {
                    if (content.charAt(i) == '[') brackets++;
                    else if (content.charAt(i) == ']') brackets--;
                    i++;
                }
                String nested = content.substring(start, i);
                result.add(parseArray(nested.substring(1, nested.length() - 1)));
            } else if (c == '{') {
                int start = i, braces = 1;
                i++;
                while (i < content.length() && braces > 0) {
                    if (content.charAt(i) == '{') braces++;
                    else if (content.charAt(i) == '}') braces--;
                    i++;
                }
                String objStr = content.substring(start, i);
                result.add(parseObject(objStr.substring(1, objStr.length() - 1)));
            } else {
                int start = i;
                boolean inQuotes = false;
                while (i < content.length()) {
                    char ch = content.charAt(i);
                    if (ch == '"' && i == 0 ) inQuotes = !inQuotes;
                    else if ((ch == ',' || ch == ']' || ch == '}') && !inQuotes) break;
                    i++;
                }
                String val = content.substring(start, i);
                if (!val.isEmpty()) result.add(parseValue(val));
            }
            while (i < content.length() && (content.charAt(i) == ',')) i++;
        }
        return result;
    }

    private HashMap<String, Object> parseObject(String content) {
        HashMap<String, Object> map = new HashMap<>();
        int i = 0;
        while (i < content.length()) {
            while (i < content.length() && content.charAt(i) ==' ') i++;
            if (i >= content.length() || content.charAt(i) != '"') break;
            int keyStart = ++i;
            while (i < content.length() && (content.charAt(i) != '"')) i++;
            String key = "\"" + content.substring(keyStart, i) + "\"";
            while (i < content.length() && (content.charAt(i) != ':')) i++;
            i++;
            char c = content.charAt(i);
            Object value;

            if (c == '"') {
                int valStart = ++i;
                while (i < content.length() && (content.charAt(i) != '"')) i++;
                value = content.substring(valStart, i++);
            } else if (c == '[') {
                int start = i, brackets = 1;
                i++;
                while (i < content.length() && brackets > 0) {
                    if (content.charAt(i) == '[') brackets++;
                    else if (content.charAt(i) == ']') brackets--;
                    i++;
                }
                value = parseArray(content.substring(start + 1, i - 1).trim());
            } else if (c == '{') {
                int start = i, braces = 1;
                i++;
                while (i < content.length() && braces > 0) {
                    if (content.charAt(i) == '{') braces++;
                    else if (content.charAt(i) == '}') braces--;
                    i++;
                }
                value = parseObject(content.substring(start + 1, i - 1).trim());
            } else {
                int start = i;
                while (i < content.length() && content.charAt(i) != ',' && content.charAt(i) != '}') i++;
                value = parseValue(content.substring(start, i));
            }
            map.put(key, value);
            while (i < content.length() && (content.charAt(i) == ',')) i++;
        }
        return map;
    }

    private Object parseValue(String value) {
        if (value.equals("null")) return null;
        else if (value.equals("true") || value.equals("false")) return Boolean.parseBoolean(value);
        else if (value.charAt(0) == '-' || Character.isDigit(value.charAt(0)))
        {
            if (value.contains(".")) return Double.parseDouble(value);
            else return Integer.parseInt(value);
        }
        return value;
    }
}
