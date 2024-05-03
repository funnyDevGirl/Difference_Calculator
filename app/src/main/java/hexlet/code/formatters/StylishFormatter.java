package hexlet.code.formatters;

import hexlet.code.Status;
import hexlet.code.utils.Util;
import java.util.List;
import java.util.Map;
import static java.lang.String.format;


public class StylishFormatter {
    private static final String ADDEDSYMBOL = "  +";
    private static final String REMOVEDSYMBOL = "  -";
    private static final String NONSYMBOL = "   ";
    public static String render(List<Map<String, Object>> differences) throws Exception {

        var result = new StringBuilder();

        for (Map<String, Object> map : differences) {

            for (Map.Entry<String, Object> entry : map.entrySet()) {

                String key = entry.getKey();
                Status value = (Status) entry.getValue();
                String status = value.getStatusName();

                switch (status) {
                    case Status.CHANGED -> {

                        String oldValue = Util.stringifyForStylish(value.getOldValue());
                        String newValue = Util.stringifyForStylish(value.getNewValue());
                        String name = Util.stringifyForStylish(key);

                        result.append(format("%s %s: %s", REMOVEDSYMBOL, name, oldValue)).append("\n");
                        result.append(format("%s %s: %s", ADDEDSYMBOL, name, newValue)).append("\n");

                    }
                    case Status.ADDED -> {

                        String newValue = Util.stringifyForStylish(value.getNewValue());
                        String name = Util.stringifyForStylish(key);

                        result.append(format("%s %s: %s", ADDEDSYMBOL, name, newValue)).append("\n");

                    }
                    case Status.DELETED -> {

                        String oldValue = Util.stringifyForStylish(value.getOldValue());
                        String name = Util.stringifyForStylish(key);

                        result.append(format("%s %s: %s", REMOVEDSYMBOL, name, oldValue)).append("\n");

                    }
                    case Status.UNCHANGED -> {

                        String oldValue = Util.stringifyForStylish(value.getOldValue());
                        String name = Util.stringifyForStylish(key);

                        result.append(format("%s %s: %s", NONSYMBOL, name, oldValue)).append("\n");
                    }
                }
            }
        }
        return format("{\n%s}", result);
    }
}
