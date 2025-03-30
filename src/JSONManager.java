import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

public class JSONManager {

    private File jsonFile;
    private boolean fileSelected;
    private String filename;
    private Validation validation;

    public JSONManager() {
        jsonFile = new File("temp.txt");
        this.fileSelected = false;
        this.filename = "";
        validation = new Validation();
    }

    public void openFile(String path)
    {
        boolean openQuotes = false;
        File selectFile = new File(path);
        filename = selectFile.getName();
        if (selectFile.isFile())
        {
            fileSelected = true;
            try (BufferedReader reader = new BufferedReader(new FileReader(selectFile));
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
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("Opened " + filename);
        }
        else System.out.println("Could not find that file. Try again");

    }

    public void validate()
    {
        validation.validate(jsonFile);
    }

    public void printFile()
    {
        if (fileSelected)
        {
            int tabs = 0;
            StringBuilder content = new StringBuilder();
            try
            {
                BufferedReader reader = new BufferedReader(new FileReader(jsonFile));
                String line;
                while ((line = reader.readLine()) != null)
                {
                    String[] lineChars = line.split("");
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
                        else if (lineChars[i].equals(",") && (lineChars[i-1].equals("\"") || lineChars[i-1].equals("}")))
                        {
                            content.append(",\n");
                            addTabs(tabs, content);
                        }
                        else if (lineChars[i].equals("\n") || lineChars[i].equals("\t"))continue;
                        else content.append(lineChars[i]);
                    }
                }
            }
            catch (IOException e)
            {
                System.out.println(e.getMessage());
            }
            System.out.println(content);
        }
        else System.out.println("Please open a file first");
    }

    private void addTabs(int tabs, StringBuilder content)
    {
        for (int i = 0; i < tabs; i++) {
            content.append("\t");
        }
    }

    public void closeFile()
    {
        if (fileSelected) System.out.println("Closed " + filename);
        else System.out.println("Please open a file first");
        this.fileSelected = false;
    }

    public void move(String from, String to)
    {
        openFile(from);
        if (fileSelected)
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

    public void set(String path, String text)
    {
        int tabs = 0;
        File file = new File(path);
        StringBuilder content = new StringBuilder();
        if (file.exists())
        {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                String[] lineChars = text.split("");
                for (int i = 0; i < lineChars.length; i++) {
                    if (lineChars[i].equals("{") || lineChars[i].equals("[")) {
                        content.append(lineChars[i] + "\n");
                        tabs++;
                        addTabs(tabs, content);
                    } else if (lineChars[i].equals("}") || lineChars[i].equals("]")) {
                        content.append("\n");
                        tabs--;
                        addTabs(tabs, content);
                        content.append(lineChars[i]);

                    } else if (lineChars[i].equals(":")) content.append(lineChars[i] + " ");
                    else if (lineChars[i].equals(",") && (lineChars[i - 1].equals("\"") || lineChars[i - 1].equals("}"))) {
                        content.append(",\n");
                        addTabs(tabs, content);
                    } else if (lineChars[i].equals("\n") || lineChars[i].equals("\t")) continue;
                    else content.append(lineChars[i]);
                }
                for (int i = 0; i < content.length(); i++) {
                    writer.write(content.charAt(i));
                }
                writer.close();
                System.out.println("file set");
            }
                catch (IOException e)
            {
                System.out.println(e.getMessage());
            }
        }
        else System.out.println("Could not find file");
    }

    public void create(String path, String text)
    {
        try{
        int tabs = 0;
        StringBuilder content = new StringBuilder();
        String[] paths = path.split("/");
        String path1 = "";
            for (int i = 0; i < paths.length-1; i++) {
                if (paths[i].contains(":")) continue;
                path1 += ("/" + paths[i]);
                if (!Files.exists(Path.of(path1)))
                    Files.createDirectory(Path.of(path1));
            }
        File selectFile = new File(path);
            if (!selectFile.createNewFile()) {
                System.out.println("File already exists: " + selectFile.getName());
            } else {
            BufferedWriter writer = new BufferedWriter(new FileWriter(selectFile));
                String[] lineChars = text.split("");
                for (int i = 0; i < lineChars.length; i++) {
                    if (lineChars[i].equals("{") || lineChars[i].equals("[")) {
                        content.append(lineChars[i] + "\n");
                        tabs++;
                        addTabs(tabs, content);
                    } else if (lineChars[i].equals("}") || lineChars[i].equals("]")) {
                        content.append("\n");
                        tabs--;
                        addTabs(tabs, content);
                        content.append(lineChars[i]);

                    } else if (lineChars[i].equals(":")) content.append(lineChars[i] + " ");
                    else if (lineChars[i].equals(",") && (lineChars[i - 1].equals("\"") || lineChars[i - 1].equals("}"))) {
                        content.append(",\n");
                        addTabs(tabs, content);
                    } else if (lineChars[i].equals("\n") || lineChars[i].equals("\t")) continue;
                    else content.append(lineChars[i]);
                }
                for (int i = 0; i < content.length(); i++) {
                    writer.write(content.charAt(i));
                }
                writer.close();
        }}
        catch (IOException e) {
            System.out.println(e.getMessage());

        }
    }

    public void delete(String path)
    {
        File selectFile = new File(path);
        if (selectFile.isFile())selectFile.delete();
        else System.out.println("File does not exist");
    }
}
