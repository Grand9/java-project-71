package hexlet.code;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.List;

import static hexlet.code.Parser.*;

public class Differ {

    public static String generate(String file1, String file2) throws Exception {
        return generate(file1, file2, "stylish");
    }

    public static String generate(String file1, String file2, String format) throws Exception {
        Map<String, Object> dataFile1 = readFileAndParse(file1);
        Map<String, Object> dataFile2 = readFileAndParse(file2);
        List<Map<String, Object>> diff = Tree.calculateDiff(dataFile1, dataFile2);
        return Formatter.format(diff, format);
    }

    private static Map<String, Object> readFileAndParse(String filepath) throws Exception {
        Path path = Paths.get(filepath).toAbsolutePath().normalize();
        byte[] fileContent = Files.readAllBytes(path);
        String content = new String(fileContent);
        String extension = getFileExtension(filepath);

        Parser parser = Parser.getParserByExtension(extension);
        return parser.parse(content);
    }

    private static String getFileExtension(String filename) {
        int lastIndexOfDot = filename.lastIndexOf('.');
        if (lastIndexOfDot == -1) {
            throw new RuntimeException("No file extension found in " + filename);
        }
        return filename.substring(lastIndexOfDot + 1);
    }
}
