package asserts;

import org.junit.jupiter.api.Assertions;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Gui {

    public static <T> void shouldBeEquals(T expected, T actual) {

        assertEquals(expected, actual);

    }
}
