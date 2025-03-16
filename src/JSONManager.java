import java.io.*;

public class JSONManager {

    private File jsonFile;
    private boolean fileSelected;
    private String filename;

    public JSONManager() {
        jsonFile = new File("temp.txt");
        this.fileSelected = false;
        this.filename = "";
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
}
