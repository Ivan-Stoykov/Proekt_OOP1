import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Validation {
    private boolean checkedBrackets;
    private JSONMessage jsonMessage;

    public Validation() {
        jsonMessage = new JSONMessage();
    }

    public void validate(File jsonFile)
    {

            boolean isValid = true;
            try(BufferedReader reader = new BufferedReader(new FileReader(jsonFile)))
            {
                int c =reader.read();
                char ch = (char) c;
                StringBuilder check = ReadFile.readFile(jsonFile);
                if (ch == '"') isValid = ValidateString(check);
                else if (ch == 't' || ch == 'f' || ch =='n') isValid = ValidateIsBooleanOrNull(check);
                else if (ch == '-' || Character.isDigit(ch)) isValid = ValidateDigit(check);
                else if (ch == '[') isValid = ValidateArray(check);
                else isValid = false;
                if (!isValid) jsonMessage.setMessage("Expecting 'STRING', 'NUMBER', 'NULL', 'TRUE', 'FALSE', '{', '[', got 'undefined'");
                else jsonMessage.setMessage("VALID JSON!");
                System.out.println(jsonMessage.getMessage());
            }
            catch (IOException e) {e.printStackTrace();}
    }

    public boolean ValidateString(StringBuilder check) throws IOException
    {
        String[] string = check.toString().split("");
        boolean closedString = false;
        int quoteCounter = 1;
        for (int i = 1; i < string.length; i++) {
            if (string[i].equals("\"") && !string[i-1].equals("\\"))
            {
                closedString = true;
                quoteCounter++;
            }
        }
        if (quoteCounter != 2 && !closedString)
        {
            return false;
        }
        else {
            check.setLength(0);
            return true;
        }
    }
    public boolean ValidateIsBooleanOrNull(StringBuilder check) throws IOException
    {
        if (!check.toString().equals("true") && !check.toString().equals("false") && !check.toString().equals("null")) {
            jsonMessage.setMessage("Error at value: "+check.toString()+": Expecting 'STRING', 'NUMBER', 'NULL', 'TRUE', 'FALSE', '{', '[', got 'undefined'");
            return false;
        }
        else {
            check.setLength(0);
            return true;
        }
    }

    public boolean ValidateDigit(StringBuilder check) throws IOException
    {
        boolean isDigit = true;
        String[] string = check.toString().split("");
        for (int i = 1; i < string.length; i++) {
            if (!Character.isDigit(string[i].charAt(0)))
            {
                isDigit = false;
                jsonMessage.setMessage("Error at value "+ check.toString() +": Expecting 'STRING', 'NUMBER', 'NULL', 'TRUE', 'FALSE', '{', '[', got 'undefined'");
                break;
            }
        }
        check.setLength(0);
        return isDigit;
    }
    public boolean ValidateArray(StringBuilder check) throws IOException
    {
        checkedBrackets = false;
        if (checkedBrackets) {
            checkedBrackets = false;
            jsonMessage.setMessage("Expecting ',', ']', got 'EOF'");
        }
        if (!validateSquareBrackets(check))
        {
            return false;
        }
        boolean isValid = true, stringOpened = false, foundErrorValue = false;
        String[] string = check.toString().split("");
        StringBuilder value = new StringBuilder();
        int index = 1;
        String erroredValue = "";
        while(index < string.length && !string[index].equals("]"))
        {
            if (string[index].equals("\"") && !stringOpened) stringOpened = true;
            else if(string[index].equals("\"") && stringOpened) stringOpened = false;
            if (stringOpened||!string[index].equals(","))
            {
                value.append(string[index]);
            }
            if (string[index].equals(",") && !stringOpened)
            {
                erroredValue = value.toString();
                if (value.charAt(0) == '"') isValid = ValidateString(value);
                    //else if (value.charAt(0) == '{') isObject = true;
                else if (value.charAt(0) == '[') isValid = ValidateArray(value);
                else if (value.charAt(0) == 't' || value.charAt(0) == 'f' || value.charAt(0) == 'n') isValid = ValidateIsBooleanOrNull(value);
                else if (value.charAt(0) == '-' || Character.isDigit(value.charAt(0))) isValid = ValidateDigit(value);
                else {
                    isValid = false;
                    foundErrorValue = true;
                }

            }
            if (!isValid) break;
            index++;
            if (index == string.length) break;
        }
        if (!value.isEmpty())
        {
            if (value.charAt(0) == '"') isValid = ValidateString(value);
                //else if (value.charAt(0) == '{') isObject = true;
            else if (value.charAt(0) == '[') isValid = ValidateArray(value);
            else if (value.charAt(0) == 't' || value.charAt(0) == 'f' || value.charAt(0) == 'n') isValid = ValidateIsBooleanOrNull(value);
            else if (value.charAt(0) == '-' || Character.isDigit(value.charAt(0))) isValid = ValidateDigit(value);
            else
            {
                isValid = false;
                foundErrorValue = true;
                erroredValue = value.toString();
            }
        }

        if (foundErrorValue)
        {
            System.out.println("Error at: " + erroredValue);
            return false;
        }
        else return true;


    }

    private boolean validateSquareBrackets(StringBuilder check)
    {
        int brackets = 0;
        String[] string = check.toString().split("");
        for (int i = 0; i < string.length; i++) {
            if (string[i].equals("[")) brackets++;
            else if(string[i].equals("]")) brackets--;
        }
        if (brackets != 0) {
            checkedBrackets = true;
            return false;
        }
        else return true;
    }


}