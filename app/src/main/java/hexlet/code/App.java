package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.Callable;

@Command(name = "gendiff", mixinStandardHelpOptions = true, version = "gendiff 4.0",
        description = "Compares two configuration files and shows a difference.")
public class App implements Callable<Integer> {

    @Parameters(paramLabel = "filepath1",description = "path to first file")
    private File filepath1;
    @Parameters(paramLabel = "filepath2",description = "path to second file")
    private File filepath2;
    @Option(names = {"-f", "--format"}, description = "output format [default: stylish]")
    private String format = "SHA-256";
    @Option(names = {"-h", "--help"}, usageHelp = true, description = "Display this help message and exit.")
    private boolean helpRequested;

    @Option(names = {"-V", "--version"}, versionHelp = true, description = "Display version info and exit.")
    private boolean versionRequested;

    public static void main(String... args) {
        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);
    }

    @Override
    public Integer call() throws NoSuchAlgorithmException, IOException {
        if (!helpRequested && !versionRequested) {
            System.out.println("Hello World!");
        }

        byte[] file1Contents = Files.readAllBytes(filepath1.toPath());
        byte[] file2Contents = Files.readAllBytes(filepath2.toPath());

        byte[] digest1 = MessageDigest.getInstance(format).digest(file1Contents);
        byte[] digest2 = MessageDigest.getInstance(format).digest(file2Contents);

        System.out.printf("File 1 checksum: %0" + (digest1.length * 2) + "x%n", new BigInteger(1, digest1));
        System.out.printf("File 2 checksum: %0" + (digest2.length * 2) + "x%n", new BigInteger(1, digest2));
        return 0;
    }
}
