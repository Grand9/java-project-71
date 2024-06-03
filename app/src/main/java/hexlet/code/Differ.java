package hexlet.code;

import com.fasterxml.jackson.databind.JsonNode;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class Differ {
    public static String generateDiff(String filePath1, String filePath2, String format) throws IOException, Exception {
        String content1 = FileReader.readFile(filePath1);
        String content2 = FileReader.readFile(filePath2);

        String fileType1 = getFileExtension(filePath1);
        String fileType2 = getFileExtension(filePath2);

        JsonNode tree1 = JsonParser.parseContent(content1, fileType1);
        JsonNode tree2 = JsonParser.parseContent(content2, fileType2);

        List<Map<String, Object>> comparisonResult = Comparator.compare(tree1, tree2);

        return Formatter.formatter(comparisonResult, format);
    }

    private static String getFileExtension(String filePath) {
        String extension = "";

        int i = filePath.lastIndexOf('.');
        if (i > 0) {
            extension = filePath.substring(i + 1);
        }
        return extension;
    }
}
