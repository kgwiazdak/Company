import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParametersTest {
    Parameters parameters;
    public ParametersTest() throws FileNotFoundException {

        parameters = new Parameters("C:\\Users\\User\\IdeaProjects\\botrouteplanner\\src\\main\\resources\\job-1.txt");
    }

    @Test
    public void parametersTest(){
        assertEquals(parameters.myPos, new Vector2d(1,1));
        assertEquals(parameters.recStation, new Vector2d(0,0));
        assertEquals(parameters.product, "P1");
    }


}
