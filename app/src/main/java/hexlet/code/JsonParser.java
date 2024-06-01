package hexlet.code;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class JsonParser {
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    public static JsonNode parse(String content) throws IOException {
        return OBJECT_MAPPER.readTree(content);
    }
}
