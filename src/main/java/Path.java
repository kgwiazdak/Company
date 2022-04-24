import java.util.Objects;

// gather data to recreate path
public class Path {

    public Vector2d vector1, vector2;
    int number;


    @Override
    public String toString() {
        return "Path{" +
                "vector1=" + vector1 +
                ", vector2=" + vector2 +
                ", number=" + number +
                '}';
    }

    public Path(){
            this.vector1 = new Vector2d(-1,-1);
            this.vector2 = new Vector2d(-1, -1);
            this.number = Integer.MAX_VALUE;
        }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Path)) return false;
        Path path = (Path) o;
        return number == path.number && Objects.equals(vector1, path.vector1) && Objects.equals(vector2, path.vector2);
    }

    @Override
    public int hashCode() {
        return Objects.hash(vector1, vector2, number);
    }
}
