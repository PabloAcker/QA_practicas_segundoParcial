package JsonCompare_Tarea_Acker;

import org.json.JSONArray;
import org.json.JSONObject;
public class JsonCompare {
    public static boolean compareJson(String expectedResult, String actualResult) {
        return compare(new JSONObject(expectedResult), new JSONObject(actualResult));
    }

    private static boolean compare(Object expected, Object actual) {
        if (expected instanceof JSONObject) {
            return compareObjects((JSONObject) expected, (JSONObject) actual);
        } else if (expected instanceof JSONArray) {
            return compareArrays((JSONArray) expected, (JSONArray) actual);
        } else {
            return expected.equals(actual);
        }
    }

    private static boolean compareObjects(JSONObject expected, JSONObject actual) {
        for (String key : expected.keySet()) {
            if (!actual.has(key)) {
                printDifference("Key not found", key, expected.get(key), null);
                return false;
            }

            if ("ignore".equals(expected.get(key))) {
                continue;
            }

            if (!compare(expected.get(key), actual.get(key))) {
                printDifference("Values don't match for key", key, expected.get(key), actual.get(key));
                return false;
            }
        }
        return true;
    }

    private static boolean compareArrays(JSONArray expected, JSONArray actual) {
        if (expected.length() != actual.length()) {
            System.out.println("Different lengths - Expected: " + expected.length() + ", Actual: " + actual.length());
            return false;
        }

        for (int i = 0; i < expected.length(); i++) {
            if (!compare(expected.get(i), actual.get(i))) {
                System.out.println("Items don't match at index " + i + " - Expected: " + expected.get(i) + ", Actual: " + actual.get(i));
                return false;
            }
        }
        return true;
    }

    private static void printDifference(String message, String key, Object expected, Object actual) {
        System.out.println(message + " - Key: " + key + ", Expected: " + expected + ", Actual: " + actual);
    }
}
