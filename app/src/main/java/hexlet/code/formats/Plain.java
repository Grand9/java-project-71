package hexlet.code.formats;

import java.util.List;
import java.util.Map;

public class Plain {

    public static boolean isListOrMap(Object value) {
        return value instanceof Map || value instanceof List;
    }

    public static String formatValue(Object value) {
        if (value == null) {
            return "null";
        }
        if (isListOrMap(value)) {
            return "[complex value]";
        }
        if (value instanceof String) {
            return "'" + value + "'";
        }
        return value.toString();
    }

    public static String format(List<Map<String, Object>> changes) {
        StringBuilder result = new StringBuilder();

        for (Map<String, Object> change : changes) {
            String key = change.get("key").toString();
            String changeType = change.get("type").toString();
            Object value1 = change.get("value1");
            Object value2 = change.get("value2");

            switch (changeType) {
                case "added":
                    result.append(String.format("Property '%s' was added with value: %s\n",
                            key, formatValue(value2)));
                    break;
                case "deleted":
                    result.append(String.format("Property '%s' was removed\n", key));
                    break;
                case "changed":
                    result.append(String.format("Property '%s' was updated. From %s to %s\n",
                            key, formatValue(value1), formatValue(value2)));
                    break;
                default:
            }
        }

        return result.toString().trim();
    }
}
