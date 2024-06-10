package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

import java.util.Map;

public class YamlParser implements Parser {

    @Override
    public Map<String, Object> parse(String content) throws Exception {
        ObjectMapper objectMapper = new YAMLMapper();
        return objectMapper.readValue(content, new TypeReference<>() { });
    }
}
