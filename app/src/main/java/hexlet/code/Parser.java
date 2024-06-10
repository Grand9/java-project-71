package hexlet.code;

import java.util.Map;

public interface Parser {
    Map<String, Object> parse(String content) throws Exception;
}

/**
 * Parses the given content and returns a map representation.
 *
 * @param content The content to parse.
 * @return A map representation of the parsed content.
 * @throws Exception If an error occurs during parsing.
 */
