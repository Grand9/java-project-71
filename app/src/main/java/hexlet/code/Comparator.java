package hexlet.code;

import com.fasterxml.jackson.databind.JsonNode;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.TreeSet;
import java.util.ArrayList;

public class Comparator {
    public static List<Map<String, Object>> compare(JsonNode node1, JsonNode node2) {
        List<Map<String, Object>> changesList = new ArrayList<>();
        TreeSet<String> keys = new TreeSet<>();

        node1.fieldNames().forEachRemaining(keys::add);
        node2.fieldNames().forEachRemaining(keys::add);

        for (String key : keys) {
            Map<String, Object> changeMap = new LinkedHashMap<>();
            changeMap.put("key", key);

            boolean node1HasKey = node1.has(key);
            boolean node2HasKey = node2.has(key);

            JsonNode value1 = node1HasKey ? node1.get(key) : null;
            JsonNode value2 = node2HasKey ? node2.get(key) : null;

            if (node1HasKey && !node2HasKey) {
                changeMap.put("type of change", "delete");
                changeMap.put("oldValue", replaceNullValueWithStringNull(value1));
            } else if (!node1HasKey && node2HasKey) {
                changeMap.put("type of change", "add");
                changeMap.put("newValue", replaceNullValueWithStringNull(value2));
            } else {
                Object oldValue = replaceNullValueWithStringNull(value1);
                Object newValue = replaceNullValueWithStringNull(value2);

                if (Objects.equals(oldValue, newValue)) {
                    changeMap.put("type of change", "nothing");
                    changeMap.put("oldValue", oldValue);
                } else {
                    changeMap.put("type of change", "change");
                    changeMap.put("oldValue", oldValue);
                    changeMap.put("newValue", newValue);
                }
            }
            changesList.add(changeMap);
        }
        return changesList;
    }

    private static Object replaceNullValueWithStringNull(JsonNode node) {
        if (node == null || node.isNull()) {
            return "null";
        } else if (node.isBoolean()) {
            return node.asBoolean();
        } else if (node.isInt()) {
            return node.asInt();
        } else if (node.isTextual()) {
            return node.asText();
        } else {
            return node.toString();
        }
    }
}
