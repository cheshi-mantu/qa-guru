package qa.guru.allure;
import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files

public class fileReadHelper {
    public static String getStringFromFile(String fullFileName) {
        String txtData = "";
        try {
            File myFileObj = new File(fullFileName);
            Scanner fileReader = new Scanner(myFileObj);
                txtData = fileReader.nextLine();
                fileReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return txtData;
    }
}
