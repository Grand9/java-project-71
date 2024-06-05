package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

public class JsonParser {

    public static Map<String, Object> parsing(String filename) throws Exception {
        String extension = getFileExtension(filename);
        if (extension.equals("json")) {
            return getJsonData(filename);
        } else if (extension.equals("yml") || extension.equals("yaml")) {
            return getYmlData(filename);
        } else {
            throw new RuntimeException(extension + " is an unknown file type");
        }
    }

    public static Map<String, Object> getJsonData(String filepath) throws Exception {
        Path path = Paths.get(filepath).toAbsolutePath().normalize();
        byte[] readFile = Files.readAllBytes(path);
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(readFile, new TypeReference<>() {
        });
    }

    public static Map<String, Object> getYmlData(String filepath) throws Exception {
        Path path = Paths.get(filepath).toAbsolutePath().normalize();
        byte[] readFile = Files.readAllBytes(path);
        ObjectMapper objectMapper = new YAMLMapper();
        return objectMapper.readValue(readFile, new TypeReference<>() {
        });
    }

    private static String getFileExtension(String filename) {
        int lastIndexOfDot = filename.lastIndexOf('.');
        if (lastIndexOfDot == -1) {
            throw new RuntimeException("No file extension found in " + filename);
        }
        return filename.substring(lastIndexOfDot + 1);
    }
}
