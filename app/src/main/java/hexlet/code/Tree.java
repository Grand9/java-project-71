package hexlet.code;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.ArrayList;
import java.util.TreeSet;

public class Tree {

    public static List<Map<String, Object>> calculateDiff(Map<String, Object> dataFile1, Map<String, Object> dataFile2) {
        TreeSet<String> allKeys = new TreeSet<>(dataFile1.keySet());
        allKeys.addAll(dataFile2.keySet());

        List<Map<String, Object>> diffList = new ArrayList<>();

        for (String key : allKeys) {
            Map<String, Object> diff = buildDiff(key, dataFile1, dataFile2);
            diffList.add(diff);
        }
        return diffList;
    }

    public static Map<String, Object> buildDiff(String key, Map<String, Object> dataFile1, Map<String, Object> dataFile2) {
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
