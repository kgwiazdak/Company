import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class Vector2dTest {
    Vector2d vector1 = new Vector2d(1, -10);
    Vector2d vector2 = new Vector2d(2, 9);
    Vector2d vector3 = new Vector2d(-210, 15);
    Vector2d vector4 = new Vector2d(2, 9);

    @Test
    public void testEquals() {
        assertEquals(vector2, vector4);
        assertNotEquals(vector2, vector3);
    }

    @Test
    public void testToString() {
        assertEquals("Vector2d{x=-210, y=15}", vector3.toString());
        assertEquals("Vector2d{x=1, y=-10}", vector1.toString());
        assertNotEquals("Vector2d{x=-4, y=-4}", vector3.toString());
        assertNotEquals("Vector2d{x=0, y=0}", vector2.toString());
    }

    @Test
    public void testPrecedes() {
        assertFalse(vector2.precedes(vector1));
        assertFalse(vector3.precedes(vector1));
        assertTrue(vector1.precedes(vector2));
        assertFalse(vector4.precedes(vector1));
    }

    @Test
    public void testFollows() {
        assertFalse(vector2.follows(vector3));
        assertFalse(vector3.follows(vector1));
        assertFalse(vector1.follows(vector4));
        assertTrue(vector4.follows(vector1));
    }

}
