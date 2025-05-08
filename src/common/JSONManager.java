package common;

import JSON.JSONElement;
import JSON.JSONObject;
import JSON.JSONString;

import java.io.*;
import java.util.*;

/**
 * Клас, който съдържа всички команди на приложението.
 */
public class JSONManager {

    private File jsonFile;
    private File selectedFile;
    private boolean isFileSelected;
    private String filename;
    private Validation validation;
    private JSONElement json;
    private JSONSetter jsonSetter;

    /**
     * Конструктор, който задава временен файл за използване и ивзиква класовете {@code Validation} и {@code JSONSetter}
     */
    public JSONManager() {
        jsonFile = new File("temp.txt");
        this.isFileSelected = false;
        this.filename = "";
        validation = new Validation();
        jsonSetter = new JSONSetter();
    }

    /**
     * Метод, който отваря и чете JSON файл, валидира го и го записва във временен файл и в паметта на програмата.
     * @param path път на JSON файл за четене.
     * @throws JSONException при възникване на грешка по време на четене се извежда съобщение.
     */
    public void openFile(String path)throws JSONException
    {
        boolean openQuotes = false;
        selectedFile = new File(path);
        filename = selectedFile.getName();
        if (selectedFile.isFile())
        {

            try (BufferedReader reader = new BufferedReader(new FileReader(selectedFile));
                 BufferedWriter writer = new BufferedWriter(new FileWriter(jsonFile))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] lineChars = line.split("");
                    for (int i = 0; i < lineChars.length; i++)
                    {
                        if (lineChars[i].equals("\"") && !openQuotes) {
                            openQuotes = true;
                        }
                        else if(lineChars[i].equals("\"") && openQuotes)
                        {
                            openQuotes = false;
                        }
                        if ((lineChars[i].equals(" ")||lineChars[i].equals("\t")) && !openQuotes)continue;
                        else writer.write(lineChars[i]);
                    }
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
            if (validate())
            {
                isFileSelected = true;
                setJson();
                System.out.println("Opened " + filename);
            }
        }
        else throw new JSONException("Could not find that file. Try again");

    }

    /**
     * Метод, който валидира записа във временния файл дали е в JSON формат
     * @return Валидност на временния файл.
     * @throws JSONException при възникване на грешка по време на четене се извежда съобщение.
     */
    public boolean validate() throws JSONException
    {
        try
        {
            BufferedReader reader = new BufferedReader(new FileReader(jsonFile));
            validation.setJson(reader.readLine());
            return validation.validate();
        }
        catch (IOException e)
        {
            throw new JSONException(e.getMessage());
        }
    }

    /**
     * Метод, който чете записа във временния файл и го записва като JSON обект в паметта.
     * @throws JSONException при възникване на грешка по време на четене се извежда съобщение.
     */
    private void setJson()throws JSONException
    {
        try
        {
            BufferedReader reader = new BufferedReader(new FileReader(jsonFile));
            json = jsonSetter.parseJson(reader.readLine());
        }
        catch (IOException e)
        {
            throw new JSONException(e.getMessage());
        }
    }

    /**
     * Метод, който извежда JSON обекта от паметта в конзолата в четим вид.
     * @return JSON обекта в четим вид.
     * @throws JSONException при неотворен файл се извежда съобщение.
     */
    public String printFile() throws JSONException
    {
        if (isFileSelected)
        {
            boolean inQuotes=false;
            StringBuilder text = new StringBuilder();
            for (int i = 0; i < json.toString().length(); i++) {
                if (json.toString().charAt(i) == '"') inQuotes = !inQuotes;
                if (json.toString().charAt(i) == ' ' && !inQuotes) continue;
                if (json.toString().charAt(i) == '=' && !inQuotes){
                    text.append(':');
                    continue;
                }
                text.append(json.toString().charAt(i));
            }
            int tabs = 0;
            StringBuilder content = new StringBuilder();
                    String[] lineChars = text.toString().split("");
                    for (int i = 0; i < lineChars.length; i++)
                    {
                        if (lineChars[i].equals("{") || lineChars[i].equals("["))
                        {
                         content.append(lineChars[i] + "\n");
                         tabs++;
                         addTabs(tabs, content);
                        }
                        else if (lineChars[i].equals("}") || lineChars[i].equals("]"))
                        {
                            content.append("\n");
                            tabs--;
                            addTabs(tabs, content);
                            content.append(lineChars[i]);

                        }
                        else if (lineChars[i].equals(":"))content.append(lineChars[i] + " ");
                        else if (lineChars[i].equals("\"")){
                            inQuotes = !inQuotes;
                            content.append("\"");
                        }
                        else if (lineChars[i].equals(",") && !inQuotes)
                        {
                            content.append(",\n");
                            addTabs(tabs, content);
                        }
                        else content.append(lineChars[i]);
                    }
            return content.toString();
        }
        else throw new JSONException("Please open a file first");
    }

    /**
     * Метод, който добавя нужни табулации към символен низ.
     * @param tabs брой табулации.
     * @param content символен низ, към който да се прибавят табулациите.
     */
    private void addTabs(int tabs, StringBuilder content)
    {
        for (int i = 0; i < tabs; i++) {
            content.append("\t");
        }
    }

    /**
     * Метод, затварящ текущо отворения файл.
     * @throws JSONException при проба на затваряне, когато не е отворен файл се изписва съобщение.
     */
    public void closeFile() throws JSONException
    {
        if (isFileSelected) System.out.println("Closed " + filename);
        else throw new JSONException("Please open a file first");
        this.isFileSelected = false;
    }

    /**
     * Метод, който търси дали има записани данни под подадения ключ.
     * @param key ключ за търсене.
     * @throws JSONException когато JSON файла не съдържа обекти или няма обекта под ключа на търсене се извежда съобщение.
     */
    public void search(String key) throws JSONException
    {
        if (json.getType().equals("Object"))
        {
            key = "\"" + key + "\"";
            JSONString jkey = new JSONString(key);
            if (((JSONObject) json).getValue().containsKey(jkey))
            {
                if (((JSONObject) json).getValue().get(jkey).getType().equals("Object"))
                {
                    ((JSONObject) ((JSONObject) json).getValue().get(jkey)).listValues();
                }
                else System.out.println(((JSONObject) json).getValue().get(jkey));
            }
            else throw new JSONException("This JSON object doesn't contain key: " + key);
        }
        else throw new JSONException("This JSON object doesn't have objects inside.");
    }

    /**
     * Метод, който мести обект от JSON файла от пътя {@code from} към пътя {@code to}.
     * @param from начален път на обекта.
     * @param to път, на който да се премести обекта.
     * @throws JSONException при възникване на грешка при преместване се извежда съобщение.
     */
    public void move(String from, String to)throws JSONException
    {
        JSONElement currentObject = findKey(from);
        String[] objects  = from.split("/");
        String[] objects1  = to.split("/");
        if (currentObject.getType().equals("Object") && ((JSONObject)currentObject).getValue().containsKey(new JSONString("\"" + objects[objects.length-1] + "\"")))
        {
            currentObject = ((JSONObject)currentObject).getValue().get(new JSONString("\"" + objects[objects.length-1] + "\""));
        }

        if (((JSONObject)findKey(from)).getValue().containsKey(new JSONString("\"" + objects[objects.length-1] + "\""))
                &&((JSONObject)findKey(to)).getValue().containsKey(new JSONString("\"" + objects1[objects1.length-1] + "\"")))
        {
//            boolean inQuotes=false;
//            StringBuilder text = new StringBuilder();
//            for (int i = 0; i < currentObject.toString().length(); i++) {
//                if (currentObject.toString().charAt(i) == '"') inQuotes = !inQuotes;
//                if (currentObject.toString().charAt(i) == ' ' && !inQuotes) continue;
//                if (currentObject.toString().charAt(i) == '=' && !inQuotes){
//                    text.append(':');
//                    continue;
//                }
//                text.append(currentObject.toString().charAt(i));

            delete(from);
            create(to + "/" + objects[objects.length-1], currentObject.toString());
            System.out.println("Moved object \"" + objects[objects.length-1] + "\" to " + to);
        }

    }

    /**
     * Метод, който търси и връща обект по даден ключ.
     * @param path път на обекта.
     * @return Търсения обект.
     */
    private JSONElement findKey(String path)
    {
        boolean foundKey = true;
        String[] objects  = path.split("/");
        JSONElement currentObject = json;
        for (int i = 0; i< objects.length-1; i++)
        {
            if (currentObject.getType().equals("Object") && ((JSONObject)currentObject).getValue().containsKey(new JSONString("\"" + objects[i] + "\"")))
                currentObject = ((JSONObject)currentObject).getValue().get(new JSONString("\"" + objects[i] + "\""));
            else
            {
                foundKey = false;
                break;
            }
        }
        if (foundKey) return currentObject;
        return null;
    }

    /**
     * Метод, който задава нова стойност на обекта, намиращ се на подадения път.
     * @param path път на обекта за промяна на стойност.
     * @param text нова стойност (символен низ в JSON формат).
     * @throws JSONException при невъзможност за намиране на обекта се извежда съобщение.
     */
    public void set(String path, String text) throws JSONException
    {
        String[] objects  = path.split("/");
        JSONElement currentObject = findKey(path);
        boolean inQuotes=false;
        StringBuilder tempText = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i) == '"') inQuotes = !inQuotes;
            if (text.charAt(i) == ' ' && !inQuotes) continue;
            tempText.append(text.charAt(i));
        }
        if (currentObject.getType().equals("Object") && ((JSONObject)currentObject).getValue().containsKey(new JSONString("\"" + objects[objects.length-1] + "\""))) {
            validation.setJson(tempText.toString());
            if (validation.validate())
            {
                ((JSONObject)currentObject).getValue().put(new JSONString("\"" + objects[objects.length-1] + "\""),jsonSetter.parseJson(tempText.toString()) );
                System.out.println("New value set for object: \"" + objects[objects.length-1] + "\".");
            }
        }
        else throw new JSONException("Object not found");
    }

    /**
     * Метод, който създава нов обект от тип JSON от подаден символен низ на подаден път
     * @param path път, където да се създаде новия обект.
     * @param text стойност на обекта (символен низ във формат JSON)
     * @throws JSONException при невъзможност на създаване на обект се извежда съобщение.
     */
    public void create(String path, String text) throws JSONException
    {
        String[] objects  = path.split("/");
        JSONElement currentObject = json;
        boolean inQuotes=false;
        StringBuilder tempText = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i) == '"') inQuotes = !inQuotes;
            if (text.charAt(i) == ' ' && !inQuotes) continue;
            tempText.append(text.charAt(i));
        }
        for (int i = 0; i< objects.length-1; i++)
        {
            if (currentObject.getType().equals("Object") && ((JSONObject)currentObject).getValue().containsKey(new JSONString("\"" + objects[i] + "\"")))
                currentObject = ((JSONObject)currentObject).getValue().get(new JSONString("\"" + objects[i] + "\""));
            else
            {
                if (currentObject.getType().equals("Object"))
                {
                     ((JSONObject)currentObject).getValue().put(new JSONString("\"" + objects[i] + "\""), jsonSetter.parseJson("{}"));
                     currentObject = ((JSONObject)currentObject).getValue().get(new JSONString("\"" + objects[i] + "\""));
                }
                else {
                    throw new JSONException(objects[i-1] + " exists and is not an object.");
                }

            }
        }

        if (currentObject.getType().equals("Object"))
        {
            if ( ((JSONObject)currentObject).getValue().containsKey(new JSONString(objects[objects.length-1])))
            {
            throw new JSONException("Element"+objects[objects.length-1]+" already exists");
            }
            else
            {
                validation.setJson(tempText.toString());
                if (validation.validate()) ((JSONObject)currentObject).getValue().put(new JSONString("\"" + objects[objects.length-1] + "\""), jsonSetter.parseJson(tempText.toString()));
            }
        }
        else
            throw new JSONException(objects[objects.length-1] + " exists and is not an object.");
    }

    /**
     * Метод, който изтрива JSON обекта на подадения път.
     * @param path път на JSON обект за триене.
     * @return успех на триене на обекта.
     */
    public boolean delete(String path)
    {
        String[] objects  = path.split("/");
        JSONElement currentObject = findKey(path);
        if (currentObject.getType().equals("Object"))
        {
            ((JSONObject) currentObject).getValue().remove(new JSONString("\"" + objects[objects.length-1] + "\""));
            return true;
        }
        else return false;
    }

    /**
     * Метод, който запазва промените във файл със същото име на подадения път. Използва {@code saveas} метод.
     * @param path път, където да се запише файла. При невъведен път се записва на текущия път на файла.
     * @throws JSONException при грешка по време на записване на файла се извежда съобщение.
     */
    public void save(String path)throws JSONException {
        saveAs("", path);
    }

    /**
     * Метод, който запазва промените във файл със подадено име на подаден път.
     * @param name име на новия файл.
     * @param path път, където да се запише файла. При невъведен път се записва на текущия път на файла.
     * @throws JSONException при грешка по време на записване на файла се извежда съобщение.
     */
    public void saveAs(String name, String path)throws JSONException
    {
        if (isFileSelected)
        {
            if (path.isBlank()) path = selectedFile.getParent();
            if (name.isBlank()) name = selectedFile.getName();
            path += "\\" + name;
            try
            {
                File selectFile = new File(path);
                BufferedWriter writer = new BufferedWriter(new FileWriter(selectFile));
                writer.write(printFile());
                writer.close();
                System.out.println("File saved as " + name + " at " + path);
            }
            catch(IOException e)
            {
                throw new JSONException(e.getMessage());
            }
        }
    }


}
