package hexlet.code.formatters;

import hexlet.code.Status;
import hexlet.code.utils.Util;
import java.util.List;
import java.util.Map;
import static java.lang.String.format;


public class PlainFormatter {

    public static String render(List<Map<String, Object>> differences) {

        StringBuilder result = new StringBuilder();

        for (Map<String, Object> map : differences) {

            for (Map.Entry<String, Object> entry : map.entrySet()) {

                String key = entry.getKey();
                Status value = (Status) entry.getValue();
                String status = value.getStatusName();

                switch (status) {
                    case Status.CHANGED -> {

                        String oldValue = Util.stringifyForPlain(value.getOldValue());
                        String newValue = Util.stringifyForPlain(value.getNewValue());
                        String name = Util.stringifyForPlain(key);

                        result.append(format("Property %s was updated. From %s to %s",
                                        name, oldValue, newValue))
                                .append("\n");

                    }
                    case Status.ADDED -> {

                        String newValue = Util.stringifyForPlain(value.getNewValue());
                        String name = Util.stringifyForPlain(key);

                        result.append(format("Property %s was added with value: %s",
                                        name, newValue))
                                .append("\n");

                    }
                    case Status.DELETED -> {

                        String name = Util.stringifyForPlain(key);

                        result.append(format("Property %s was removed", name))
                                .append("\n");

                    }
                    default -> {}
                }
            }
        }
        return result.toString().trim();
    }
}
