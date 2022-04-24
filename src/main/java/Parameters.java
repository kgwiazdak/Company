import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


// holds data from job-x.txt
public class Parameters {
    public Vector2d myPos, recStation;
    public String product;

    public Parameters(String filename) throws FileNotFoundException {
        File file = new File(filename);
        Scanner myScanner = new Scanner(file);

        String sData = myScanner.nextLine();
        String[] sDataList= sData.split(" ");
        this.myPos = new Vector2d(Integer.parseInt(sDataList[1]), Integer.parseInt(sDataList[0]));
        sData = myScanner.nextLine();
        sDataList= sData.split(" ");
        this.recStation = new Vector2d(Integer.parseInt(sDataList[1]), Integer.parseInt(sDataList[0]));
        sData = myScanner.nextLine();
        this.product = sData;
    }

}
