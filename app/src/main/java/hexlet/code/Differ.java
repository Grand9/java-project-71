package hexlet.code;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class Differ {

    // Метод генерации диффа на основе файловых путей
    public static String generate(String filePath1, String filePath2) throws IOException {
        // Создаем ObjectMapper для чтения JSON
        ObjectMapper mapper = new ObjectMapper();

        // Считываем JSON из файловых путей и генерируем дифф
        JsonNode tree1 = mapper.readTree(new File(filePath1));
        JsonNode tree2 = mapper.readTree(new File(filePath2));

        return generate(tree1, tree2);
    }

    // Метод генерации диффа на основе JsonNode объектов
    private static String generate(JsonNode tree1, JsonNode tree2) {
        // Преобразуем JsonNode в отсортированные Map
        Map<String, JsonNode> map1 = toMap(tree1);
        Map<String, JsonNode> map2 = toMap(tree2);

        // Собираем множество всех ключей из обоих Map
        Set<String> allKeys = new TreeSet<>(map1.keySet());
        allKeys.addAll(map2.keySet());

        // Подготавливаем StringBuilder для построения результата
        StringBuilder result = new StringBuilder();
        result.append("{\n");

        // Итерируемся по всем ключам
        for (String key : allKeys) {
            // Если ключ присутствует в обоих Map
            if (map1.containsKey(key) && map2.containsKey(key)) {
                // Сравниваем значения для данного ключа
                String value1 = map1.get(key).toString();
                String value2 = map2.get(key).toString();
                if (value1.equals(value2)) {
                    result.append("  ").append(key).append(": ").append(value1).append("\n");
                } else {
                    result.append("- ").append(key).append(": ").append(value1).append("\n");
                    result.append("+ ").append(key).append(": ").append(value2).append("\n");
                }
            } else if (map1.containsKey(key)) {
                // Если ключ присутствует только в первой Map
                result.append("- ").append(key).append(": ").append(map1.get(key)).append("\n");
            } else {
                // Если ключ присутствует только во второй Map
                result.append("+ ").append(key).append(": ").append(map2.get(key)).append("\n");
            }
        }

        result.append("}");

        return result.toString();
    }

    // Метод преобразования JsonNode в отсортированную Map
    private static Map<String, JsonNode> toMap(JsonNode node) {
        Map<String, JsonNode> map = new TreeMap<>();
        // Используем forEachRemaining для итерации по полям JsonNode
        node.fields().forEachRemaining(entry -> map.put(entry.getKey(), entry.getValue()));
        return map;
    }
}
