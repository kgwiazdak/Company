import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

// process data from grid-x.txt

public class Maps {

    double[][][] matrix;
    final Fields[][] fields;
    Path[][] path;

    // create 3 maps
    // fields holds speed of movement and package for example P1 if exists in 2D array
    // matrix is the "value array" for dijkstra
    // path helps with recreating the best path
    public Maps(String filename) throws FileNotFoundException {

        // Preprocessing data
        File file = new File(filename);
        if (!file.exists()) throw new FileNotFoundException("File not found");
        Scanner myScanner = new Scanner(file);
        String shape = myScanner.nextLine();
        String[] sData = shape.split(" ");

        // dimensions
        int y = Integer.parseInt(sData[0]);
        int x = Integer.parseInt(sData[1]);
        Fields[][] fields = new Fields[x][y];
        double[][][] matrix = new double[x][y][2];
        Path[][] paths = new Path[x][y];

        String sRow;
        String[] row;



        // insert speed of current block to fields array
        // preprocessing matrix
        for (int i = 0; i <x; i++) {
            sRow = myScanner.nextLine();
            row = sRow.split("");
            for (int j = 0; j < y; j++) {
                paths[i][j] = new Path();
                switch (row[j]) {
                    case "H" -> {
                        fields[i][j] = new Fields(0.5);
                        matrix[i][j][0]=matrix[i][j][1] = Double.POSITIVE_INFINITY;
                    }
                    case "B" -> {
                        fields[i][j] = new Fields(1);
                        matrix[i][j][0]=matrix[i][j][1] = Double.POSITIVE_INFINITY;
                    }
                    case "S" -> {
                        fields[i][j] = new Fields(2);
                        matrix[i][j][0]=matrix[i][j][1] = Double.POSITIVE_INFINITY;
                    }
                    case "O" -> {
                        fields[i][j] = new Fields(Double.POSITIVE_INFINITY);
                        matrix[i][j][0]=matrix[i][j][1] = Double.NEGATIVE_INFINITY;
                    }
                }
            }
        }


        String pack;
        double valueOfPack;
        int xi,yi,zi;

        // inserting packages and cost of mining to fields
        while (myScanner.hasNextLine()){
            sRow = myScanner.nextLine();
            row = sRow.split(" ");

            pack = row[0];
            xi = Integer.parseInt(row[2]);
            yi = Integer.parseInt(row[1]);
            zi = Integer.parseInt(row[3]);

            Fields field = fields[xi][yi];
            valueOfPack = getCostOfMining(field.getValue(), zi);
            field.setValueOFPack(valueOfPack);
            field.setPack(pack);
        }
        this.fields = fields;
        this.matrix = matrix;
        this.path = paths;
    }


    private static int getCostOfMining(double mode, int n){
        if (mode==0.5) return 3 * n + 4;
        else if (mode==1) return 2 * n + 2;
        else if (mode==2) return n + 1;
        else return Integer.MAX_VALUE;

    }


}
