package hexlet.code.formats;

import java.util.List;
import java.util.Map;

public class Plain {

    public static boolean isListOrMap(Object value) {
        return value instanceof Map || value instanceof List;
    }

    public static boolean quotationsNeeded(Object value) {
        return !isListOrMap(value) && !value.equals("null") && !value.equals(false)
                && !value.equals(true) && !(value instanceof Number);
    }

    public static Object replaceComplexValueWithString(Object object) {
        return isListOrMap(object) ? "[complex value]" : object;
    }

    public static String plain(List<Map<String, Object>> listForFormatting) {
        StringBuilder formattedString = new StringBuilder();
        listForFormatting.forEach(map -> {
            var value1 = replaceComplexValueWithString(map.get("oldValue"));
            var value2 = replaceComplexValueWithString(map.get("newValue"));
            switch (map.get("type of change").toString()) {
                case "add" -> formattedString.append(String.format(quotationsNeeded(map.get("newValue"))
                        ? "Property '" + map.get("key") + "' was added with value: '" + value2 + "'\n"
                        : "Property '" + map.get("key") + "' was added with value: " + value2 + "\n"));
                case "delete" -> formattedString.append("Property '" + map.get("key") + "' was removed\n");
                case "change" -> {
                    String stringForOutput = "Property '%s' was updated. From "
                            + (quotationsNeeded(map.get("oldValue")) ? "'%s' to " : "%s to ")
                            + (quotationsNeeded(map.get("newValue")) ? "'%s'\n" : "%s\n");
                    formattedString.append(String.format(stringForOutput, map.get("key"), value1, value2));
                }
                default -> { }
            }
        });
        return formattedString.substring(0, formattedString.length() - 1);
    }
}