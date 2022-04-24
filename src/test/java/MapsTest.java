import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MapsTest {
    Maps maps;

    public MapsTest() throws FileNotFoundException {
        maps = new Maps("file1.txt");
    }

    @Test
    public void matrixTest(){
        double[][][] m = new double[3][4][2];
        Path[][] paths = new Path[3][4];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 4; j++) {
                paths[i][j] = new Path();
                for (int k = 0; k < 2; k++) {
                    m[i][j][k] = Double.POSITIVE_INFINITY;
                }
            }
        }
        m[2][2][1] = Double.NEGATIVE_INFINITY;
        m[2][2][0] = Double.NEGATIVE_INFINITY;

        assertTrue(Arrays.deepEquals(maps.matrix, m));
        assertTrue(Arrays.deepEquals(maps.path, paths));
        assertEquals(maps.fields[0][0].getValue(), 0.5);
        assertEquals(maps.fields[0][0].getPack(), "null");
        assertEquals(maps.fields[0][0].getValueOFPack(), -1);

    }

}
