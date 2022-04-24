import java.util.ArrayList;
import java.util.Objects;

public class Vector2d {
    private final int x;
    private final int y;

    public Vector2d(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public ArrayList<Vector2d> neighbours(Vector2d minVector, Vector2d maxVector, Fields[][] fields){
        ArrayList<Vector2d> arr = new ArrayList<>();
        Vector2d v= this.add(new Vector2d(1, 0));
        if (v.precedes(maxVector) && Double.isFinite(fields[v.getX()][v.getY()].getValue())) arr.add(v);
        v= this.add(new Vector2d(0, 1));
        if (v.precedes(maxVector)&& Double.isFinite(fields[v.getX()][v.getY()].getValue())) arr.add(v);
        v= this.add(new Vector2d(-1, 0));
        if (v.follows(minVector)&& Double.isFinite(fields[v.getX()][v.getY()].getValue())) arr.add(v);
        v= this.add(new Vector2d(0, -1));
        if (v.follows(minVector)&& Double.isFinite(fields[v.getX()][v.getY()].getValue())) arr.add(v);
        return arr;
    }

    @Override
    public String toString() {
        return "Vector2d{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

    public boolean precedes(Vector2d other) {
        return this.x <= other.x && this.y <= other.y;
    }

    public boolean follows(Vector2d other) {
        return this.x >= other.x && this.y >= other.y;
    }

    public Vector2d add(Vector2d other) {
        return new Vector2d(this.x + other.x, this.y + other.y);
    }


    @Override
    public boolean equals(Object other) {
        if (other instanceof Vector2d) {
            return this.x == ((Vector2d) other).x && this.y == ((Vector2d) other).y;
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.x, this.y);
    }
}