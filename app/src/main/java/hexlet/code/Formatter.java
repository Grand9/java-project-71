package hexlet.code;

import hexlet.code.formats.Json;
import hexlet.code.formats.Plain;
import hexlet.code.formats.Stylish;

import java.util.List;
import java.util.Map;

public class Formatter {
    public static String format(List<Map<String, Object>> diff, String format) throws Exception {
        return switch (format) {
            case "plain" -> Plain.format(diff);
            case "json" -> Json.json(diff);
            case "stylish" -> Stylish.format(diff);
            default -> throw new IllegalArgumentException("Unknown format: " + format);
        };
    }
}
