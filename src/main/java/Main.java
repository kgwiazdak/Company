import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;

public class Main {

    // dijkstra condition
    private static boolean subFunction(double[][][] matrix, Fields[][] fields, Path[][] paths, int x, int y, int xn, int yn, int mode){
        if (matrix[x][y][mode]+Math.max(fields[x][y].getValue(), fields[xn][yn].getValue()) < matrix[xn][yn][mode]){
            matrix[xn][yn][mode] = matrix[x][y][mode]+Math.max(fields[x][y].getValue(), fields[xn][yn].getValue());
            paths[xn][yn].number = paths[x][y].number+1;
            if (mode==1) paths[xn][yn].vector2 = new Vector2d(x, y);
            else paths[xn][yn].vector1 = new Vector2d(x, y);

            return true;
        } return false;
    }

    // recurse subclass to print solution
    private static void rec(Path[][] paths, ArrayList<Vector2d> visited, Vector2d curr, Vector2d myPos, boolean flag){
        if (flag && curr.equals(myPos)) {
            System.out.println(curr.getY()+" "+ curr.getX());
            return;
        }
        visited.add(curr);
        Vector2d prev = curr;

        if (!flag){
            Vector2d newVector = paths[curr.getX()][curr.getY()].vector2;
            if (visited.contains(newVector) || newVector.equals(new Vector2d(-1,-1))){
                flag=true;
                curr = paths[curr.getX()][curr.getY()].vector1;
            } else {
                curr = newVector;
            }
        } else curr = paths[curr.getX()][curr.getY()].vector1;
        rec(paths, visited, curr, myPos, flag);
        System.out.println(prev.getY()+" "+ prev.getX());

    }

    //print solution
    private static void printSolution(Path[][] paths, double[][][] matrix, Vector2d myPos, Vector2d recStation){
        System.out.println(paths[recStation.getX()][recStation.getY()].number);
        System.out.println(matrix[recStation.getX()][recStation.getY()][1]);

        rec(paths, new ArrayList<Vector2d>(), recStation, myPos,false);

    }

    // dijkstra
    private static void solve(Maps maps, Parameters parameters){

        //unpack array instances from classes (convenience)
        Vector2d myPos = parameters.myPos;
        Vector2d recStation = parameters.recStation;
        String product =parameters.product;
        Fields[][] fields = maps.fields;
        double[][][] matrix = maps.matrix;
        Path[][] path = maps.path;

        Vector2d minVector = new Vector2d(0,0);
        Vector2d maxVector = new Vector2d(fields.length-1, fields[0].length-1);


        // preprocessing to dijkstra
        matrix[myPos.getX()][myPos.getY()][0] =0;
        path[myPos.getX()][myPos.getY()].number=0;
        LinkedList<Vector2d> queue = new LinkedList<>();
        ArrayList<Vector2d> neighbours;
        queue.add(myPos);
        int x, y, xn,yn;

        // am I before mining
        boolean flag;

        // dijkstra
        while (!queue.isEmpty()){
            Vector2d vector = queue.getFirst();
            x = vector.getX();
            y = vector.getY();
            flag= Double.isFinite(matrix[x][y][1]);
            queue.removeFirst();

            neighbours = vector.neighbours(minVector,maxVector, fields);
            for (Vector2d v : neighbours){
                xn = v.getX();
                yn = v.getY();
                if (flag) {
                    if (v.equals(recStation)) {
                        matrix[xn][yn][1] = matrix[x][y][1]+Math.max(fields[x][y].getValue(), fields[xn][yn].getValue());
                        path[xn][yn].number = path[x][y].number+1;
                        path[xn][yn].vector2 = vector;
                        continue;
                    }
                    if (subFunction(matrix,fields,path,x,y,xn,yn, 1)){
                        queue.add(v);
                    }
                }
                if (subFunction(matrix, fields,path, x, y, xn, yn, 0)){
                    if (queue.isEmpty() || !queue.getLast().equals(v)) queue.add(v);
                    if (fields[xn][yn].getPack().equals(product))
                        matrix[xn][yn][1] = matrix[x][y][0]+Math.max(fields[x][y].getValue(), fields[xn][yn].getValue())+fields[xn][yn].getValueOFPack();
                }
            }
        }
    }




    public static void main(String args[]) throws FileNotFoundException {
        if (args.length<2) throw new IllegalArgumentException("Too less arguments");
        String filename1 = args[0];
        String filename2 = args[1];
        Maps maps = new Maps(filename1);
        Parameters parameters = new Parameters(filename2);
        solve(maps,parameters);
        printSolution(maps.path, maps.matrix, parameters.myPos, parameters.recStation);
    }
}
