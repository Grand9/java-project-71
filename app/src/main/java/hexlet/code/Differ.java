package hexlet.code;

import java.util.Objects;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

public class Differ {

    public static String generate(String file1, String file2) throws Exception {
        return generate(file1, file2, "stylish");
    }

    public static String generate(String file1, String file2, String format) throws Exception {
        Map<String, Object> dataFile1 = Parser.parsing(file1);
        Map<String, Object> dataFile2 = Parser.parsing(file2);
        List<Map<String, Object>> diff = calculateDiff(dataFile1, dataFile2);
        return Formatter.format(diff, format);
    }

    public static List<Map<String, Object>> calculateDiff(Map<String, Object> dataFile1, Map<String,
            Object> dataFile2) {
        TreeSet<String> allKeys = new TreeSet<>(dataFile1.keySet());
        allKeys.addAll(dataFile2.keySet());

        List<Map<String, Object>> diffList = new ArrayList<>();

        for (String key : allKeys) {
            Map<String, Object> diff = Tree.buildDiff(key, dataFile1, dataFile2);
            diffList.add(diff);
        }
        return diffList;
    }
}
