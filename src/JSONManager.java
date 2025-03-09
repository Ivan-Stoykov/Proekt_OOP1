import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class JSONManager {

    private File jsonFile;

    public void openFile(String path)
    {
        jsonFile = new File(path);
    }

    public void printFile()
    {
        StringBuilder content = new StringBuilder();
        try
        {
            BufferedReader reader = new BufferedReader(new FileReader(jsonFile));
            String line;
            while ((line = reader.readLine()) != null)
            {
                content.append(line + "\n");
            }
        }
        catch (IOException e)
        {
            System.out.println(e.getMessage());
        }
        System.out.println(content);
    }

    public void closeFile()
    {
        jsonFile = new File("");
    }
}
