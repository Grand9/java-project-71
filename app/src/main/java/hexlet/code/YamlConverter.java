package hexlet.code;

import com.fasterxml.jackson.databind.JsonNode;
import java.io.IOException;

public class YamlConverter {
    public static JsonNode parse(String content) throws IOException {
        return JsonParser.parse(content);
    }
}
