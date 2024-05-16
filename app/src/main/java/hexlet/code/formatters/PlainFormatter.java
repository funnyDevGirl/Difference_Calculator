package hexlet.code.formatters;

import hexlet.code.Status;
import java.util.List;
import java.util.Map;
import static java.lang.String.format;


public class PlainFormatter {

    public static String render(Map<String, Object> differences) throws Exception {

        StringBuilder result = new StringBuilder();

        for (Map.Entry<String, Object> entry : differences.entrySet()) {

            String key = entry.getKey();
            Status value = (Status) entry.getValue();
            String status = value.getStatusName();

            switch (status) {
                case Status.CHANGED -> {

                    String oldValue = stringify(value.getOldValue());
                    String newValue = stringify(value.getNewValue());
                    String name = stringify(key);

                    result.append(format("Property %s was updated. From %s to %s",
                                    name, oldValue, newValue))
                            .append("\n");

                }
                case Status.ADDED -> {

                    String newValue = stringify(value.getNewValue());
                    String name = stringify(key);

                    result.append(format("Property %s was added with value: %s",
                                    name, newValue))
                            .append("\n");

                }
                case Status.DELETED -> {

                    String name = stringify(key);

                    result.append(format("Property %s was removed", name))
                            .append("\n");

                }
                case Status.UNCHANGED -> {
                    continue;

                }
                default -> {
                    throw new Exception("Unknown status: '" + status + "'");
                }
            }
        }

        return result.toString().trim();
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

        // Тип результата всегда должен быть строкой
        return value.toString();
    }
}
