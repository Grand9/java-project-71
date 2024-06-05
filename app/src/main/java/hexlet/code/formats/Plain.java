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
            String changeType = change.get("type of change").toString();
            Object oldValue = change.get("oldValue");
            Object newValue = change.get("newValue");

            switch (changeType) {
                case "add":
                    result.append(String.format("Property '%s' was added with value: %s\n", key, formatValue(newValue)));
                    break;
                case "delete":
                    result.append(String.format("Property '%s' was removed\n", key));
                    break;
                case "change":
                    result.append(String.format("Property '%s' was updated. From %s to %s\n",
                            key, formatValue(oldValue), formatValue(newValue)));
                    break;
                default:
            }
        }

        return result.toString().trim();
    }
}
