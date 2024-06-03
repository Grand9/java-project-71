package hexlet.code.formats;

import java.util.List;
import java.util.Map;

public class Stylish {
    public static String format(List<Map<String, Object>> diff) {
        StringBuilder formattedDiff = new StringBuilder();
        formattedDiff.append("{\n");

        for (Map<String, Object> change : diff) {
            String key = (String) change.get("key");
            String type = (String) change.get("type of change");

            switch (type) {
                case "add":
                    formattedDiff.append(String.format("  + %s: %s\n", key, formatValue(change.get("newValue"))));
                    break;
                case "delete":
                    formattedDiff.append(String.format("  - %s: %s\n", key, formatValue(change.get("oldValue"))));
                    break;
                case "change":
                    formattedDiff.append(String.format("  - %s: %s\n", key, formatValue(change.get("oldValue"))));
                    formattedDiff.append(String.format("  + %s: %s\n", key, formatValue(change.get("newValue"))));
                    break;
                case "nothing":
                    formattedDiff.append(String.format("    %s: %s\n", key, formatValue(change.get("oldValue"))));
                    break;
            }
        }

        formattedDiff.append("}");
        return formattedDiff.toString();
    }

    private static String formatValue(Object value) {
        if (value instanceof String) {
            return value.toString();
        } else if (value instanceof List) {
            return formatList((List<?>) value);
        } else if (value instanceof Map) {
            return formatMap((Map<?, ?>) value);
        } else if (value == null) {
            return "null";
        } else {
            return value.toString();
        }
    }

    private static String formatList(List<?> list) {
        StringBuilder result = new StringBuilder("[");
        for (Object element : list) {
            if (result.length() > 1) {
                result.append(", ");
            }
            result.append(formatValue(element));
        }
        result.append("]");
        return result.toString();
    }

    private static String formatMap(Map<?, ?> map) {
        StringBuilder result = new StringBuilder("{");
        for (Map.Entry<?, ?> entry : map.entrySet()) {
            if (result.length() > 1) {
                result.append(", ");
            }
            result.append(entry.getKey()).append("=").append(formatValue(entry.getValue()));
        }
        result.append("}");
        return result.toString();
    }
}