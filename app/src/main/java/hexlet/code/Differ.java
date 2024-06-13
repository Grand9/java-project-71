package hexlet.code;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.TreeSet;
import java.util.List;
import java.util.ArrayList;

public class Differ {

    public static String generate(String file1, String file2) throws Exception {
        return generate(file1, file2, "stylish");
    }

    public static String generate(String file1, String file2, String format) throws Exception {
        Map<String, Object> dataFile1 = readFileAndParse(file1);
        Map<String, Object> dataFile2 = readFileAndParse(file2);
        List<Map<String, Object>> diff = calculateDiff(dataFile1, dataFile2);
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

    public static List<Map<String, Object>> calculateDiff(Map<String, Object> dataFile1, Map<String,
            Object> dataFile2) {
        TreeSet<String> allKeys = new TreeSet<>(dataFile1.keySet());
        allKeys.addAll(dataFile2.keySet());

        List<Map<String, Object>> diffList = new ArrayList<>();

        for (String key : allKeys) {
            Map<String, Object> diff = Tree.buildDiff(key, dataFile1, dataFile2);
            diffList.add(diff);
        }
        return diffList;
    }
}
