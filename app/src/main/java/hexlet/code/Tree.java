package hexlet.code;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Tree {
    public static Map<String, Object> buildDiff(String key, Map<String, Object> dataFile1,
                                                Map<String, Object> dataFile2) {
        Map<String, Object> diff = new HashMap<>();
        diff.put("key", key);

        if (dataFile1.containsKey(key) && dataFile2.containsKey(key)) {
            if (Objects.equals(dataFile1.get(key), dataFile2.get(key))) {
                diff.put("type", "unchanged");
                diff.put("value1", dataFile1.get(key));
                diff.put("value2", dataFile1.get(key));
            } else {
                diff.put("type", "changed");
                diff.put("value1", dataFile1.get(key));
                diff.put("value2", dataFile2.get(key));
            }
        } else if (dataFile1.containsKey(key)) {
            diff.put("type", "deleted");
            diff.put("value1", dataFile1.get(key));
        } else {
            diff.put("type", "added");
            diff.put("value2", dataFile2.get(key));
        }
        return diff;
    }
}
