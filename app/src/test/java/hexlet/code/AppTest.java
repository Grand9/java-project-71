package hexlet.code;

import com.fasterxml.jackson.databind.ObjectMapper;
import hexlet.code.formats.Plain;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class AppTest {

    @Test
    public void testGenerateJsonComparison() throws Exception {
        String expected = new String(Files.readAllBytes(Paths.get("src/test/resources/expected1"))).trim();
        String result = Differ.generate("src/test/resources/file1.json",
                "src/test/resources/file2.json", "stylish").trim();

        assertThat(result).isEqualToIgnoringNewLines(expected);
    }

    @Test
    public void testGenerateYamlComparison() throws Exception {
        String expected = new String(Files.readAllBytes(Paths.get("src/test/resources/expected1"))).trim();
        String result = Differ.generate("src/test/resources/file1.yaml",
                "src/test/resources/file2.yaml", "stylish").trim();

        assertThat(result).isEqualToIgnoringNewLines(expected);
    }

    @Test
    public void testGenerateJsonComparisonSecond() throws Exception {
        String expected = new String(Files.readAllBytes(Paths.get("src/test/resources/expected11"))).trim();
        String result = Differ.generate("src/test/resources/file11.json",
                "src/test/resources/file22.json", "stylish").trim();

        assertThat(result).isEqualToIgnoringNewLines(expected);
    }

    @Test
    public void testGenerateYamlComparisonSecond() throws Exception {
        String expected = new String(Files.readAllBytes(Paths.get("src/test/resources/expected11"))).trim();
        String result = Differ.generate("src/test/resources/file11.yaml",
                "src/test/resources/file22.yaml", "stylish").trim();

        assertThat(result).isEqualToIgnoringNewLines(expected);
    }

    @Test
    public void testPlainFormatter() throws Exception {
        String diffFilePath = "src/test/resources/diffList.json";
        String expectedFilePath = "src/test/resources/expectedPlain.txt";

        ObjectMapper objectMapper = new ObjectMapper();
        List<Map<String, Object>> diff = objectMapper.readValue(Paths.get(diffFilePath).toFile(), List.class);

        String expected = new String(Files.readAllBytes(Paths.get(expectedFilePath))).trim();

        String result = Plain.format(diff).trim();

        assertThat(result).isEqualToIgnoringNewLines(expected);
    }
}
