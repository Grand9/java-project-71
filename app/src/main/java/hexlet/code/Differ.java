package hexlet.code;

import com.fasterxml.jackson.databind.JsonNode;
import java.io.IOException;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class Differ {

    public static String generate(String filePath1, String filePath2) throws IOException {
        String content1 = FileReader.readFile(filePath1);
        String content2 = FileReader.readFile(filePath2);
        JsonNode tree1 = parseContent(content1, getFileExtension(filePath1));
        JsonNode tree2 = parseContent(content2, getFileExtension(filePath2));
        return generateDiff(tree1, tree2);
    }

    private static JsonNode parseContent(String content, String fileType) throws IOException {
        switch (fileType) {
            case "json":
                return JsonParser.parse(content);
            case "yaml":
            case "yml":
                return YamlConverter.parse(content);
            default:
                throw new IllegalArgumentException("Unsupported file type: " + fileType);
        }
    }

    private static String getFileExtension(String filePath) {
        return filePath.substring(filePath.lastIndexOf('.') + 1).toLowerCase();
    }

    private static String generateDiff(JsonNode tree1, JsonNode tree2) {
        Map<String, JsonNode> map1 = toMap(tree1);
        Map<String, JsonNode> map2 = toMap(tree2);
        Set<String> allKeys = new TreeSet<>(map1.keySet());
        allKeys.addAll(map2.keySet());

        StringBuilder result = new StringBuilder();
        result.append("{\n");

        for (String key : allKeys) {
            if (map1.containsKey(key) && map2.containsKey(key)) {
                String value1 = map1.get(key).asText();
                String value2 = map2.get(key).asText();
                if (value1.equals(value2)) {
                    result.append("  ").append(key).append(": ").append(value1).append("\n");
                } else {
                    result.append("- ").append(key).append(": ").append(value1).append("\n");
                    result.append("+ ").append(key).append(": ").append(value2).append("\n");
                }
            } else if (map1.containsKey(key)) {
                result.append("- ").append(key).append(": ").append(map1.get(key).asText()).append("\n");
            } else {
                result.append("+ ").append(key).append(": ").append(map2.get(key).asText()).append("\n");
            }
        }

        result.append("}");
        return result.toString();
    }

    private static Map<String, JsonNode> toMap(JsonNode node) {
        Map<String, JsonNode> map = new TreeMap<>();
        node.fields().forEachRemaining(entry -> map.put(entry.getKey(), entry.getValue()));
        return map;
    }
}
