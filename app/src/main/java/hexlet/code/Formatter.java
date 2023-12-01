package hexlet.code;

import hexlet.code.formatters.JsonFormatter;
import hexlet.code.formatters.PlainFormatter;
import hexlet.code.formatters.StylishFormatter;

import java.util.List;
import java.util.Map;

public class Formatter {

    public static String generateJson(List<Map<String, Object>> differences) throws Exception {
     return JsonFormatter.render(differences);
    }

    public static String generatePlain(List<Map<String, Object>> differences) throws Exception {
        return PlainFormatter.render(differences);
    }

    public static String generateStylish(List<Map<String, Object>> differences) throws Exception {
        return StylishFormatter.render(differences);
    }
}
