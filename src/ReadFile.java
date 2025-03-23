import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class ReadFile {
    public static StringBuilder readFile(File jsonFile) throws IOException
    {
        BufferedReader reader2 = new BufferedReader(new FileReader(jsonFile));
        StringBuilder check = new StringBuilder();
        int c =reader2.read();
        while (c != -1)
        {
            char ch = (char) c;
            check.append(ch);
            c =reader2.read();
        }
        return check;
    }
}
