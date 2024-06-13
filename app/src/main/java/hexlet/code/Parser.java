package hexlet.code;

import java.util.Map;

public class Parser {
    Map<String, Object> parse(String content) throws Exception {
        return null;
    }

    static Parser getParserByExtension(String extension) {
        return switch (extension.toLowerCase()) {
            case "json" -> new JsonParser();
            case "yml", "yaml" -> new YamlParser();
            default -> throw new RuntimeException(extension + " is an unknown file type");
        };
    }
}
