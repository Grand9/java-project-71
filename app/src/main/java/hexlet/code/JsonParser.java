package hexlet.code;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class JsonParser {
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    public static JsonNode parse(String content) throws IOException {
        return OBJECT_MAPPER.readTree(content);
    }

    public static JsonNode parseContent(String content, String fileType) throws IOException {
        return switch (fileType) {
            case "json" -> JsonParser.parse(content);
            case "yaml", "yml" -> YamlConverter.parse(content);
            default -> throw new IllegalArgumentException("Unsupported file type: " + fileType);
        };
    }
}
