package hexlet.code;

import hexlet.code.formats.Json;
import hexlet.code.formats.Plain;
import hexlet.code.formats.Stylish;

import java.util.List;
import java.util.Map;

public class Formatter {
    public static String formatter(List<Map<String, Object>> dataForFormatting, String format)
            throws Exception {
        return switch (format) {
            case "plain" -> Plain.plain(dataForFormatting);
            case "json" -> Json.json(dataForFormatting);
            default -> Stylish.format(dataForFormatting);
        };
    }
}
