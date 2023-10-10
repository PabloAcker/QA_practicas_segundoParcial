package jsonCompareTest;

import JsonCompare_Tarea_Acker.JsonCompare;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
public class JsonCompareTest {
    @Test
    public void testSimpleMatch() {
        String expected = "{\"Content\": \"Pablo\", \"Date\": 10}";
        String actual = "{\"Content\": \"Pablo\", \"Date\": 10}";

        boolean result = JsonCompare.compareJson(expected, actual);
        Assertions.assertTrue(result);
    }

    @Test
    public void testIgnoringDate() {
        String expected = "{\"Content\": \"Pablo\", \"Date\": \"ignore\"}";
        String actual = "{\"Content\": \"Pablo\", \"Date\": 10}";

        boolean result = JsonCompare.compareJson(expected, actual);
        Assertions.assertTrue(result);
    }

    @Test
    public void testMismatchOnNestedAge() {
        String expected = "{\"Content\": {\"name\": \"Pablo\", \"Date\": 10}}";
        String actual = "{\"Content\": {\"name\": \"Pablo\", \"Date\": 10}}";

        boolean result = JsonCompare.compareJson(expected, actual);
        Assertions.assertTrue(result);
    }

    @Test
    public void testIgnoringNestedAge() {
        String expected = "{\"Content\": {\"name\": \"Pablo\", \"Date\": \"ignore\"}}";
        String actual = "{\"Content\": {\"name\": \"Pablo\", \"Date\": 10}}";

        boolean result = JsonCompare.compareJson(expected, actual);
        Assertions.assertTrue(result);
    }

    @Test
    public void testMismatchOnArrayLength() {
        String expected = "{\"colors\": [\"red\", \"blue\", \"green\"]}";
        String actual = "{\"colors\": [\"red\", \"blue\"]}";

        boolean result = JsonCompare.compareJson(expected, actual);
        Assertions.assertFalse(result);
    }

    @Test
    public void testArrayItemsMatch() {
        String expected = "{\"colors\": [\"red\", \"blue\"]}";
        String actual = "{\"colors\": [\"red\", \"blue\"]}";

        boolean result = JsonCompare.compareJson(expected, actual);
        Assertions.assertTrue(result);
    }
}
