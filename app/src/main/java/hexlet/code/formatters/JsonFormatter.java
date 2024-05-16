package hexlet.code.formatters;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Map;


public class JsonFormatter {

    public static String render(Map<String, Object> differences) throws Exception {

        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(differences);
    }
}
