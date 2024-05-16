package hexlet.code.formatters;

import hexlet.code.Status;
import java.util.Map;
import static java.lang.String.format;


public class StylishFormatter {
    private static final String ADDEDSYMBOL = "  +";
    private static final String REMOVEDSYMBOL = "  -";
    private static final String NONSYMBOL = "   ";


    public static String render(Map<String, Object> differences) throws Exception {

        var result = new StringBuilder();

        for (Map.Entry<String, Object> entry : differences.entrySet()) {

            String key = entry.getKey();
            Status value = (Status) entry.getValue();
            String status = value.getStatusName();

            switch (status) {
                case Status.CHANGED -> {

                    String oldValue = stringify(value.getOldValue());
                    String newValue = stringify(value.getNewValue());
                    String name = stringify(key);

                    result.append(format("%s %s: %s", REMOVEDSYMBOL, name, oldValue)).append("\n");
                    result.append(format("%s %s: %s", ADDEDSYMBOL, name, newValue)).append("\n");

                }
                case Status.ADDED -> {

                    String newValue = stringify(value.getNewValue());
                    String name = stringify(key);

                    result.append(format("%s %s: %s", ADDEDSYMBOL, name, newValue)).append("\n");

                }
                case Status.DELETED -> {

                    String oldValue = stringify(value.getOldValue());
                    String name = stringify(key);

                    result.append(format("%s %s: %s", REMOVEDSYMBOL, name, oldValue)).append("\n");

                }
                case Status.UNCHANGED -> {

                    String oldValue = stringify(value.getOldValue());
                    String name = stringify(key);

                    result.append(format("%s %s: %s", NONSYMBOL, name, oldValue)).append("\n");

                }
                default -> {
                    throw new Exception("Unknown status: '" + status + "'");
                }
            }
        }

        return format("{\n%s}", result);
    }

    private static String stringify(Object value) {

        if (value == null) {
            return "null";
        }
        // Тип результата всегда должен быть строкой.
        return value.toString();
    }
}
