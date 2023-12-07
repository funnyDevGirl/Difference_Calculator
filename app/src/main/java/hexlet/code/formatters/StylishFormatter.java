package hexlet.code.formatters;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public class StylishFormatter {
    private static final String ADDEDSYMBOL = "  + ";
    private static final String REMOVEDSYMBOL = "  - ";
    private static final String NONSYMBOL = "    ";
    public static String render(List<Map<String, Object>> differences) throws Exception {

        var result = new StringBuilder("{\n");

        for (Map<String, Object> map : differences) {
            for (String s : map.keySet()) {

                Object value = !Objects.isNull(map.get(s)) ? map.get(s) : null;

                if (Objects.equals(value, "changed")) {

                    String value1 = stringify(map.get("oldValue"));
                    String value2 = stringify(map.get("newValue"));

                    result.append(REMOVEDSYMBOL + map.get("name") + ": " + value1 + "\n");
                    result.append(ADDEDSYMBOL + map.get("name") + ": " + value2 + "\n");

                } else if (Objects.equals(value, "added")) {

                    String value1 = stringify(map.get("newValue"));

                    result.append(ADDEDSYMBOL + map.get("name") + ": " + value1 + "\n");

                } else if (Objects.equals(value, "deleted")) {

                    String value1 = stringify(map.get("oldValue"));

                    result.append(REMOVEDSYMBOL + map.get("name") + ": " + value1 + "\n");

                } else if (Objects.equals(value, "unchanged")) {

                    String value1 = stringify(map.get("oldValue"));

                    result.append(NONSYMBOL + map.get("name") + ": " + value1 + "\n");
                }
            }
        }
        //return result.append("}\n").toString().trim();
        return result.append("}").toString();
    }

    private static String stringify(Object value) {

        if (value == null) {
            return "null";
        }
        // Тип результата всегда должен быть строкой.
        return value.toString();
    }
}
