package hexlet.code;

import java.util.*;

public class Differ {

    public static String generate(String format, String file1, String file2) throws Exception {
        Map<String, Object> dataFile1 = JsonParser.parsing(file1);
        Map<String, Object> dataFile2 = JsonParser.parsing(file2);
        List<Map<String, Object>> diff = calculateDiff(dataFile1, dataFile2);
        switch (format) {
            case "plain":
                return hexlet.code.formats.Plain.format(diff);
            case "stylish":
            default:
                return stylish(diff);
        }
    }

    public static List<Map<String, Object>> calculateDiff(Map<String, Object> dataFile1, Map<String, Object> dataFile2) {
        TreeSet<String> allKeys = new TreeSet<>(dataFile1.keySet());
        allKeys.addAll(dataFile2.keySet());

        List<Map<String, Object>> diffList = new ArrayList<>();

        for (String key : allKeys) {
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

            diffList.add(diff);
        }

        return diffList;
    }

    public static String stylish(List<Map<String, Object>> diffList) {
        StringBuilder result = new StringBuilder();
        result.append("{\n");
        for (Map<String, Object> diff : diffList) {
            String key = diff.get("key").toString();
            String changeType = diff.get("type of change").toString();
            Object oldValue = diff.get("oldValue");
            Object newValue = diff.get("newValue");

            switch (changeType) {
                case "unchanged":
                    result.append(String.format("    %s: %s\n", key, oldValue));
                    break;
                case "change":
                    result.append(String.format("  - %s: %s\n", key, oldValue));
                    result.append(String.format("  + %s: %s\n", key, newValue));
                    break;
                case "delete":
                    result.append(String.format("  - %s: %s\n", key, oldValue));
                    break;
                case "add":
                    result.append(String.format("  + %s: %s\n", key, newValue));
                    break;
                default:
                    // do nothing
            }
        }
        result.append("}");
        return result.toString();
    }
}
