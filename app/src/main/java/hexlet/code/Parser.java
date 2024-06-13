package hexlet.code;

import java.util.Map;

public interface Parser {
    /**
     * Parses the given content and returns a map representation.
     *
     * @param content The content to parse.
     * @return A map representation of the parsed content.
     * @throws Exception If an error occurs during parsing.
     */
    Map<String, Object> parse(String content) throws Exception;

    static Parser getParserByExtension(String extension) {
        return switch (extension.toLowerCase()) {
            case "json" -> new JsonParser();
            case "yml", "yaml" -> new YamlParser();
            default -> throw new RuntimeException(extension + " is an unknown file type");
        };
    }
}
