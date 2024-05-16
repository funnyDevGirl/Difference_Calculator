package hexlet.code;

import hexlet.code.formatters.JsonFormatter;
import hexlet.code.formatters.PlainFormatter;
import hexlet.code.formatters.StylishFormatter;
import java.util.Map;


public class Formatter {

    public static String format(Map<String, Object> differences, String formatName) throws Exception {

        return switch (formatName) {
            case "json" -> JsonFormatter.render(differences);
            case "stylish" -> StylishFormatter.render(differences);
            case "plain" -> PlainFormatter.render(differences);
            default -> throw new Exception("Unknown format: '" + formatName + "'");
        };
    }
}
