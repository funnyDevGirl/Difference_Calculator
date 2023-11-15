package hexlet.code;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DifferTest {
    @Test
    public void differTest() throws Exception {
        String filepath1 = "src/test/resources/filepath1.json";
        String filepath2 = "src/test/resources/filepath2.json";

        /*
        String expected = Differ.generate(filepath1, filepath2);
        String actual = """
                {
                  - follow: false
                    host: hexlet.io
                  - proxy: 123.234.53.22
                  - timeout: 50
                  + timeout: 20
                  + verbose: true
                }""";
        assertThat(actual).isEqualTo(expected);
         */

        String expected = readFixture("filepath3.json");
        String actual = Differ.generate(filepath1, filepath2);
        assertThat(actual).isEqualTo(expected);
    }

    private static Path getFixturePath(String fileName) {
        return Paths.get("src", "test", "resources", fileName)
                .toAbsolutePath().normalize();
    }

    private static String readFixture(String fileName) throws Exception {
        Path filePath = getFixturePath(fileName);
        return Files.readString(filePath).trim();
    }
}
