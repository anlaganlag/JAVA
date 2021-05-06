
package StringToInt.StringToInt.src;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class FileReader3000 {
    public static void main(String[] args) throws IOException {
        File file = new File("/home/gal/Desktop/projects/JAVA/StringToInt/StringToInt/src/python.txt");
        Scanner scan = new Scanner(file);
        // while (scan.hasNextLine()) {

        // System.out.println(scan.nextLine());
        // }
        String fileContent = "";
        while (scan.hasNextLine()) {
            fileContent = fileContent.concat(scan.nextLine() + "\n");
        }

        FileWriter writer = new FileWriter("/home/gal/Desktop/myText.txt");
        writer.write(fileContent);
        writer.close();
    }
}