package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;
import java.util.concurrent.Callable;


@Command(name = "gendiff", mixinStandardHelpOptions = true, version = "gendiff 4.7.5",
        description = "Compares two configuration files and shows a difference.")
public final class App implements Callable<Integer> {

    private static final int SUCCESS_EXIT_CODE = 0;
    private static final int ERROR_EXIT_CODE = 1;

    @Parameters(index = "0", description = "path to first file")
    //@Parameters(description = "path to first file")
    private String filepath1;
    @Parameters(index = "1", description = "path to second file")
    //@Parameters(description = "path to second file")
    private String filepath2;
    @Option(names = {"-f", "--format"}, defaultValue = "stylish",
            description = "output format [default: ${DEFAULT-VALUE}]")
    private String format;

    /**
     * @return returns a string of differences between 2 files
     */
    @Override
    public Integer call() {

        try {
            String formattedDiff =  Differ.generate(filepath1, filepath2, format);
            System.out.println(formattedDiff);

        } catch (Exception e) {
            System.err.println(e.getMessage());
            return ERROR_EXIT_CODE;
        }

        return SUCCESS_EXIT_CODE;
    }

    public static void main(String... args) {
        System.exit(new CommandLine(new App()).execute(args));
    }
}
