package hexlet.code.formats;

import java.util.List;
import java.util.Map;

public class Stylish {
    public static String format(List<Map<String, Object>> diff) {
        StringBuilder formattedDiff = new StringBuilder();
        formattedDiff.append("{\n");

        for (Map<String, Object> change : diff) {
            String key = (String) change.get("key");
            String type = (String) change.get("type");

            if (key == null || type == null) {
                throw new IllegalArgumentException("Key or type cannot be null");
            }

            switch (type) {
                case "added":
                    formattedDiff.append(String.format("  + %s: %s\n", key, formatValue(change.get("value2"))));
                    break;
                case "deleted":
                    formattedDiff.append(String.format("  - %s: %s\n", key, formatValue(change.get("value1"))));
                    break;
                case "changed":
                    formattedDiff.append(String.format("  - %s: %s\n", key, formatValue(change.get("value1"))));
                    formattedDiff.append(String.format("  + %s: %s\n", key, formatValue(change.get("value2"))));
                    break;
                case "unchanged":
                    formattedDiff.append(String.format("    %s: %s\n", key, formatValue(change.get("value1"))));
                    break;
                default:
                    throw new IllegalArgumentException("Unexpected type: " + type);
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
