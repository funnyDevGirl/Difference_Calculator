package hexlet.code.formatters;

import java.util.List;
import java.util.Map;

public class StylishFormatter {
    private static final String ADDEDSYMBOL = "  + ";
    private static final String REMOVEDSYMBOL = "  - ";
    private static final String NONSYMBOL = "    ";
    public static String render(List<Map<String, Object>> differences) throws Exception {

        var result = new StringBuilder("{\n");

        for (Map<String, Object> map : differences) {
            for (String s : map.keySet()) {

                if (map.get(s).equals("changed")) {
                    String value1 = stringify(map.get("oldValue"));
                    String value2 = stringify(map.get("newValue"));

                    result.append(REMOVEDSYMBOL + map.get("name") + ": " + value1 + "\n");
                    result.append(ADDEDSYMBOL + map.get("name") + ": " + value2 + "\n");

                } else if (map.get(s).equals("added")) {
                    String value = stringify(map.get("newValue"));

                    result.append(ADDEDSYMBOL + map.get("name") + ": " + value + "\n");

                } else if (map.get(s).equals("deleted")) {
                    String value = stringify(map.get("oldValue"));

                    result.append(REMOVEDSYMBOL + map.get("name") + ": " + value + "\n");

                } else if (map.get(s).equals("unchanged")) {
                    String value = stringify(map.get("oldValue"));

                    result.append(NONSYMBOL + map.get("name") + ": " + value + "\n");
                }
            }
        }
        return result.append("}").toString().trim();
    }

    private static String stringify(Object value) {

        if (value == null) {
            return "null";
        }
        // Тип результата всегда должен быть строкой.
        return value.toString();
    }
}
