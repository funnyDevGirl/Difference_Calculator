package hexlet.code;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.assertj.core.api.Assertions.assertThat;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


public class DifferTest {

    @ParameterizedTest
    @ValueSource(strings = {"json", "yml"})
    public void generateTest(String format) throws Exception {

        String filePath1 = getFixturePath("file1." + format).toString();
        String filePath2 = getFixturePath("file2." + format).toString();

        // Тестируем вызов метода с каждым из фоматерров,
        // а также вызов с форматером по умолчанию
        assertThat(Differ.generate(filePath1, filePath2))
                .isEqualTo(readFixture("stylish.json"));

        assertThat(Differ.generate(filePath1, filePath2, "json"))
                .isEqualTo(readFixture("json.json"));

        assertThat(Differ.generate(filePath1, filePath2, "stylish"))
                .isEqualTo(readFixture("stylish.json"));

        assertThat(Differ.generate(filePath1, filePath2, "plain"))
                .isEqualTo(readFixture("plain.json"));
    }

    private static Path getFixturePath(String fileName) {
        return Paths.get("src", "test", "resources", fileName)
                .toAbsolutePath().normalize();
    }

    private static String readFixture(String fileName) throws Exception {
        Path filePath = getFixturePath(fileName);
        return Files.readString(filePath);
    }
}
