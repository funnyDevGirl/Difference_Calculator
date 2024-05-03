package hexlet.code.utils;

import java.util.List;
import java.util.Map;


public class Util {

    public static String stringifyForPlain(Object value) {

        if (value == null) {
            return "null";
        }

        if (value instanceof String) {
            return "'" + value + "'";
        }

        if (value instanceof Map || value instanceof List) {
            return "[complex value]";
        }

        // Тип результата всегда должен быть строкой.
        return value.toString();
    }

    public static String stringifyForStylish(Object value) {

        if (value == null) {
            return "null";
        }
        // Тип результата всегда должен быть строкой.
        return value.toString();
    }
}
