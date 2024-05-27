package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;
import java.io.File;
import java.util.concurrent.Callable;

@Command(name = "gendiff", mixinStandardHelpOptions = true, version = "gendiff 4.0",
        description = "Compares two configuration files and shows a difference.")
public class App implements Callable {

    @Parameters(paramLabel = "filepath1",description = "path to first file")
    private File filepath1;
    @Parameters(paramLabel = "filepath2",description = "path to second file")
    private File filepath2;

    @Option(names = {"-f", "--format"},paramLabel = "format", description = "output format [default: stylish]")
    private String format = "SHA-256";

    public static void main(String... args) {
        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);
    }

    @Override
    public Object call() throws Exception {
        return null;
    }
}

