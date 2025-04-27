package common;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class JSONManager {

    private File jsonFile;
    private File selectedFile;
    private boolean isFileSelected;
    private String filename;
    private Validation validation;
    private Object json;
    private JSONSetter jsonSetter;

    public JSONManager() {
        jsonFile = new File("temp.txt");
        this.isFileSelected = false;
        this.filename = "";
        validation = new Validation();
        jsonSetter = new JSONSetter();
    }

    public void openFile(String path)
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
        else System.out.println("Could not find that file. Try again");

    }

    public boolean validate()
    {
        try
        {
            BufferedReader reader = new BufferedReader(new FileReader(jsonFile));
            validation.setJson(reader.readLine());
            return validation.validate();
        }
        catch (IOException e)
        {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public void setJson()
    {
        try
        {
            BufferedReader reader = new BufferedReader(new FileReader(jsonFile));
            json = jsonSetter.parseJson(reader.readLine());
        }
        catch (IOException e)
        {
            System.out.println(e.getMessage());
        }
    }

    public String printFile()
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
                        else if (lineChars[i].equals(","))
                        {
                            content.append(",\n");
                            addTabs(tabs, content);
                        }
                        else content.append(lineChars[i]);
                    }
            return content.toString();
        }
        else System.out.print("Please open a file first");
        return "";
    }

    private void addTabs(int tabs, StringBuilder content)
    {
        for (int i = 0; i < tabs; i++) {
            content.append("\t");
        }
    }

    public void closeFile()
    {
        if (isFileSelected) System.out.println("Closed " + filename);
        else System.out.println("Please open a file first");
        this.isFileSelected = false;
    }

    public void search(String key)
    {
        if (json instanceof HashMap)
        {
            key = "\"" + key + "\"";
            if (((HashMap<String, Object>) json).containsKey(key))
            {
                if (((HashMap) json).get(key) instanceof HashMap)
                {
                    Set<Object> list  = ((HashMap) ((HashMap) json).get(key)).entrySet();
                    for (Object value : list)
                    {
                        boolean inQuotes=false;
                        StringBuilder text = new StringBuilder();
                        for (int i = 0; i < value.toString().length(); i++) {
                            if (value.toString().charAt(i) == '"') inQuotes = !inQuotes;
                            if (value.toString().charAt(i) == ' ' && !inQuotes) continue;
                            if (value.toString().charAt(i) == '=' && !inQuotes){
                                text.append(':').append(' ');
                                continue;
                            }
                            text.append(value.toString().charAt(i));
                        }
                        System.out.println(text);
                    }
                }
                else System.out.println(((HashMap) json).get(key));
            }
            else System.out.println("This JSON object doesn't contain key: " + key);
        }
        else System.out.println("This JSON object doesn't have objects inside.");
    }

    public void move(String from, String to)
    {
        openFile(from);
        if (isFileSelected)
        {
            try(BufferedReader reader = new BufferedReader(new FileReader(jsonFile)))
            {
                StringBuilder content = new StringBuilder(reader.readLine());
                create(to, content.toString());
            }
            catch (Exception e)
            {
                System.out.println(e.getMessage());
            }
        }

    }
    private Object findKey(String path)
    {
        boolean foundKey = true;
        String[] objects  = path.split("/");
        Object currentObject = json;
        for (int i = 0; i< objects.length-1; i++)
        {
            if (((HashMap)currentObject).containsKey("\"" + objects[i] + "\"") && currentObject instanceof HashMap)
                currentObject = ((HashMap)currentObject).get("\"" + objects[i] + "\"");
            else
            {
                foundKey = false;
                break;
            }
        }
        if (foundKey) return currentObject;
        return null;
    }

    public void set(String path, String text)
    {
        String[] objects  = path.split("/");
        Object currentObject = findKey(path);
        if (currentObject instanceof HashMap && ((HashMap)currentObject).containsKey("\"" + objects[objects.length-1] + "\"")) {
            validation.setJson(text);
            if (validation.validate())
            {
                ((HashMap)currentObject).put("\"" + objects[objects.length-1] + "\"",jsonSetter.parseJson(text) );
                System.out.println("New value set for object: \"" + objects[objects.length-1] + "\".");
                System.out.println(json);
            }
        }
        else System.out.println("Object not found");
    }

    public void create(String path, String text)
    {
        boolean checked = false;
        String[] objects  = path.split("/");
        Object currentObject = json;
        for (int i = 0; i< objects.length-1; i++)
        {
            if (currentObject instanceof HashMap && ((HashMap)currentObject).containsKey("\"" + objects[i] + "\""))
                currentObject = ((HashMap)currentObject).get("\"" + objects[i] + "\"");
            else
            {
                if (currentObject instanceof HashMap)
                {
                     ((HashMap)currentObject).put("\"" + objects[i] + "\"", jsonSetter.parseJson("{}"));
                     currentObject = ((HashMap)currentObject).get("\"" + objects[i] + "\"");
                }
                else {
                    checked = true;
                    System.out.println(objects[i-1] + " exists and is not an object.");
                }

            }
        }

        if (currentObject instanceof HashMap)
        {if ( ((HashMap)currentObject).containsKey(objects[objects.length-1])){
            System.out.println("Element"+objects[objects.length-1]+" already exists");
        }
        else
        {

                validation.setJson(text);
                if (validation.validate()) ((HashMap)currentObject).put("\"" + objects[objects.length-1] + "\"", jsonSetter.parseJson(text));
            }
        }else if (!checked)System.out.println(objects[objects.length-1] + " exists and is not an object.");
    }

    public void delete(String path)
    {
        String[] objects  = path.split("/");
        Object currentObject = findKey(path);
        if (currentObject instanceof HashMap)
        {
            ((HashMap) currentObject).remove("\"" + objects[objects.length-1] + "\"");
            System.out.println("Deleted object: \"" + objects[objects.length-1] + "\".");
        }
        else System.out.println("Object does not exist");
    }

    public void save(String path) {
        saveAs("", path);
    }

    public void saveAs(String name, String path)
    {
        if (isFileSelected)
        {
            if (path.isBlank()) path = selectedFile.getParent();
            if (name.isBlank()) name = selectedFile.getName();
            path += "/" + name;
            try
            {
                File selectFile = new File(path);
                if (!selectFile.createNewFile()) {
                    System.out.println("File already exists: " + selectFile.getName());
                } else {
                    BufferedWriter writer = new BufferedWriter(new FileWriter(selectFile));
                    writer.write(printFile());
                    writer.close();
                    System.out.println("File saved as " + name + " at " + path);
                }
            }
            catch(IOException e)
            {
                System.out.println(e.getMessage());
            }
        }
    }


}
