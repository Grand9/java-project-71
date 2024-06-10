package hexlet.code;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Tree {
    public static Map<String, Object> buildDiff(String key, Map<String, Object> dataFile1, Map<String,
            Object> dataFile2) {
        Map<String, Object> diff = new HashMap<>();
        diff.put("key", key);

        if (dataFile1.containsKey(key) && dataFile2.containsKey(key)) {
            if (Objects.equals(dataFile1.get(key), dataFile2.get(key))) {
                diff.put("type of change", "unchanged");
                diff.put("oldValue", dataFile1.get(key));
                diff.put("newValue", dataFile1.get(key));
            } else {
                diff.put("type of change", "change");
                diff.put("oldValue", dataFile1.get(key));
                diff.put("newValue", dataFile2.get(key));
            }
        } else if (dataFile1.containsKey(key)) {
            diff.put("type of change", "delete");
            diff.put("oldValue", dataFile1.get(key));
        } else {
            diff.put("type of change", "add");
            diff.put("newValue", dataFile2.get(key));
        }
        return diff;
    }
}
