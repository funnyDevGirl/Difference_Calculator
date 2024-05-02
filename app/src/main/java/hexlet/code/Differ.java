package hexlet.code;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

public class Differ {
    public static String generate(String filepath1, String filepath2, String stylish) throws Exception {
        //формирую мапы с ключами-значениями:
        Map<String, Object> preparedDataFromFile1 = getData(filepath1);
        Map<String, Object> preparedDataFromFile2 = getData(filepath2);

        //словарь с изменениями:
        List<Map<String, Object>> differences = Comparator.genDiff(preparedDataFromFile1, preparedDataFromFile2);

        return switch (stylish) {
            case "json" -> Formatter.generateJson(differences);
            case "stylish" -> Formatter.generateStylish(differences);
            case "plain" -> Formatter.generatePlain(differences);
            default -> throw new Exception("Unknown format: '" + stylish + "'");
        };
    }

    //делаю перегрузку метода generate():
    public static String generate(String filepath1, String filepath2) throws Exception {

        Map<String, Object> preparedDataFromFile1 = getData(filepath1);
        Map<String, Object> preparedDataFromFile2 = getData(filepath2);

        List<Map<String, Object>> differences = Comparator.genDiff(preparedDataFromFile1, preparedDataFromFile2);
        return Formatter.generateStylish(differences);
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
}
