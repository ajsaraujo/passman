import org.junit.Test;
import org.junit.jupiter.api.Nested;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SampleTest {
    @Test
    public void onePlusOneShouldBeTwo() {
        assertEquals(1 + 1, 2);
    }

    @Nested
    public class NestedTest {
        @Test
        public void twoPlusTwoShouldBeFour() {
            assertEquals(2 + 2, 4);
        }
    }
}
