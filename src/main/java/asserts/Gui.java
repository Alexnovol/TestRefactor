package asserts;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Gui {

    public static <T> void shouldBeEquals(T expected, T actual) {

        assertEquals(expected, actual);

    }
}
