package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;

public class JsonParser implements Parser {
    /**
     * Parses the given content and returns a map representation.
     *
     * @param content The content to parse.
     * @return A map representation of the parsed content.
     * @throws Exception If an error occurs during parsing.
     */
    @Override
    public Map<String, Object> parse(String content) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(content, new TypeReference<>() { });
    }
}
