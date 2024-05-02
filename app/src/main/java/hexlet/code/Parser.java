package hexlet.code;

import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

public class Parser {

    private static Map<String, Object> parseYaml(String content) throws Exception  {
        ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());
        //return objectMapper.readValue(content, Map.class);
        return objectMapper.readValue(content, new TypeReference<>() { });
    }

    private static Map<String, Object> parseJson(String content) throws Exception  {
        ObjectMapper objectMapper = new ObjectMapper();
        //return objectMapper.readValue(content, Map.class);
        return objectMapper.readValue(content, new TypeReference<>() { });
    }

    // На вход идет тип данных и сами данные. Ничего про файлы!
    public static Map<String, Object> parse(String content, String dataFormat) throws Exception {
        return switch (dataFormat) {
            case "yml", "yaml" -> parseYaml(content);
            case "json" -> parseJson(content);
            default -> throw new Exception("Unknown format: '" + dataFormat + "'");
        };
    }
}
