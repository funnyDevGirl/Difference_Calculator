package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;
import java.util.concurrent.Callable;

@Command(name = "gendiff", mixinStandardHelpOptions = true, version = "gendiff 4.7.5",
        description = "Compares two configuration files and shows a difference.")
public class App implements Callable<Integer> {

    @Parameters(index = "0", description = "path to first file")
    //@Parameters(description = "path to first file")
    private String filepath1;
    @Parameters(index = "1", description = "path to second file")
    //@Parameters(description = "path to second file")
    private String filepath2;
    @Option(names = {"-f", "--format"}, defaultValue = "stylish",
            description = "output format [default: ${DEFAULT-VALUE}]")
    private String format;

    @Override
    public Integer call() throws Exception {
        String file =  Differ.generate(filepath1, filepath2, format);
        //System.out.printf(file);
        System.out.println(file);
        return 0;
    }

    public static void main(String... args) {
        System.exit(new CommandLine(new App()).execute(args));
    }
}
