package hexlet.code;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;


public class Differ {
    public static String generate(String filepath1, String filepath2) throws Exception {
        //формирую мапы с ключами-значениями:
        Map<String, Object> preparedDataFromFile1 = getData(filepath1);
        Map<String, Object> preparedDataFromFile2 = getData(filepath2);

        //словарь с изменениями:
        Map<String, Object> changes = genDiff(preparedDataFromFile1, preparedDataFromFile2);

        return genToString(changes);
    }

    private static Map<String, Object> getData(String filePath) throws Exception {
        Path fullPath = getFullPath(filePath);
        //проверяем сущ-е файла
        if (!Files.exists(fullPath)) {
            throw new Exception("File '" + fullPath + "' does not exist");
        }
        //читаем файл
        String content = Files.readString(fullPath);
        //getDataFormat - определяет формат файла, yml или json
        //чтобы мы могли сообщить парсеру, в каком формате строку мы ему передаем
        String dataFormat = getDataFormat(filePath);

        // Парсинг выделяем в отдельный модуль
        return Parser.parse(content, dataFormat);
    }

    private static Path getFullPath(String filePath) {
        //формируем абсолютный путь
        return Paths.get(filePath).toAbsolutePath().normalize();
    }

    private static String getDataFormat(String filePath) {
        int index = filePath.lastIndexOf('.');
        return index > 0
                ? filePath.substring(index + 1)
                : "";
    }

    public static Map<String, Object> genDiff(Map<String, Object> data1, Map<String, Object> data2) {
        Map<String, Object> result = new LinkedHashMap<>();
        Set<String> keys = new TreeSet<>(data1.keySet());
        keys.addAll(data2.keySet());

        for (String key : keys) {
            if (!data1.containsKey(key)) {
                var addedValue = data2.get(key);
                String addedKey = "  + " + key;
                result.put(addedKey, addedValue);
            } else if (!data2.containsKey(key)) {
                var deletedValue = data1.get(key);
                String deletedKey = "  - " + key;
                result.put(deletedKey, deletedValue);
            } else if (data1.containsKey(key) && data2.containsKey(key)) {
                if (!data1.get(key).equals(data2.get(key))) {
                    String changedKey1 = "  - " + key;
                    String changedKey2 = "  + " + key;
                    result.put(changedKey1, data1.get(key));
                    result.put(changedKey2, data2.get(key));
                } else {
                    String sameKey = "    " + key;
                    result.put(sameKey, data1.get(key));
                }
            }
        }
        return result;
    }

    public static String genToString(Map<String, Object> diffData) {

        var result = new StringBuilder("{\n");

        for (Map.Entry<String, Object> entry : diffData.entrySet()) {
            result.append(entry.getKey() + ": " + entry.getValue());
            result.append("\n");
        }
        return result.append("}").toString();
    }
}
