package hexlet.code;

import java.util.Map;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

public class Parser {

    private static Map parseYaml(String content) throws Exception  {
        ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());
        return objectMapper.readValue(content, Map.class);
    }

    private static Map parseJson(String content) throws Exception  {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(content, Map.class);
    }

    // На вход идет тип данных и сами данные. Ничего про файлы!
    public static Map parse(String content, String dataFormat) throws Exception {
        switch (dataFormat) {
            case "yml":
            case "yaml":
                return parseYaml(content);
            case "json":
                return parseJson(content);
            default:
                throw new Exception("Unknown format: '" + dataFormat + "'");
        }
    }
}
