package hexlet.code;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.IOException;

public class YamlConverter {
    private static final ObjectMapper YAML_MAPPER = new ObjectMapper(new YAMLFactory());

    public static JsonNode parse(String content) throws IOException {
        return YAML_MAPPER.readTree(content);
    }
}
