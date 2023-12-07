package hexlet.code.formatters;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public class PlainFormatter {
    public static String render(List<Map<String, Object>> differences) throws Exception {

        var result = new StringBuilder();

        for (Map<String, Object> map : differences) {
            for (String s : map.keySet()) {

                Object value = !Objects.isNull(map.get(s)) ? map.get(s) : null;

                if (Objects.equals(value, "changed")) {

                    String value1 = stringify(map.get("oldValue"));
                    String value2 = stringify(map.get("newValue"));
                    String name = stringify(map.get("name"));

                    result.append("Property ");
                    result.append(name + " was updated. From " + value1 + " to " + value2 + "\n");

                } else if (Objects.equals(value, "added")) {

                    String value1 = stringify(map.get("newValue"));

                    result.append("Property '");
                    result.append(map.get("name") + "' was added with value: ");
                    result.append(value1).append("\n");

                } else if (Objects.equals(value, "deleted")) {

                    result.append("Property '");
                    result.append(map.get("name") + "' was removed").append("\n");
                }
            }
        }
        return result.toString().trim() + "\n";
    }

    private static String stringify(Object value) {

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
}
