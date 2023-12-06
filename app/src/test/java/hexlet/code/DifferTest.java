package hexlet.code;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


public class DifferTest {

    @Test
    public void differStylishTest1() throws Exception {
        String filepath4 = "src/test/resources/filepath4.json";
        String filepath5 = "src/test/resources/filepath5.json";
        String format = "stylish";

        String expected = readFixture("filepath6.json");
        String actual = Differ.generate(filepath4, filepath5, format);
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void differStylishTest2() throws Exception {
        String filepath4 = "src/test/resources/filepath4.json";
        String filepath5 = "src/test/resources/filepath5.json";

        String expected = readFixture("filepath6.json");
        String actual = Differ.generate(filepath4, filepath5);
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void differPlainTest() throws Exception {
        String filepath4 = "src/test/resources/filepath4.json";
        String filepath5 = "src/test/resources/filepath5.json";
        String format = "plain";

        String expected = readFixture("filepath7.json");
        String actual = Differ.generate(filepath4, filepath5, format);
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void differJsonTest() throws Exception {
        String filepath4 = "src/test/resources/filepath4.json";
        String filepath5 = "src/test/resources/filepath5.json";
        String format = "json";

        String expected = readFixture("filepath8.json");
        String actual = Differ.generate(filepath4, filepath5, format);
        assertThat(actual).isEqualTo(expected);
    }


    private static Path getFixturePath(String fileName) {
        return Paths.get("src", "test", "resources", fileName)
                .toAbsolutePath().normalize();
    }

    private static String readFixture(String fileName) throws Exception {
        Path filePath = getFixturePath(fileName);
        //return Files.readString(filePath).trim();
        return Files.readString(filePath);
    }
}
