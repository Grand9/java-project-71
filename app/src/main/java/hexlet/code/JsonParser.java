package hexlet.code;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class JsonParser {

    public static JsonNode parse(String jsonContent) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readTree(jsonContent);
    }
}
