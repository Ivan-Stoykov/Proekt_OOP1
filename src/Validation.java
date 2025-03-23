import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Validation {
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


}