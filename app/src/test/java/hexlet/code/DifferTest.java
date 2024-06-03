package hexlet.code;

import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Paths;

import static org.assertj.core.api.Assertions.assertThat;

public class DifferTest {

    @Test
    public void testGenerateJsonComparison() throws Exception {
        String expected = new String(Files.readAllBytes(Paths.get("src/test/resources/expected1"))).trim();
        String result = Differ.generateDiff("src/test/resources/file1.json",
                "src/test/resources/file2.json","stylish").trim();

        assertThat(result).isEqualToIgnoringNewLines(expected);
    }

    @Test
    public void testGenerateYamlComparison() throws Exception {
        String expected = new String(Files.readAllBytes(Paths.get("src/test/resources/expected1"))).trim();
        String result = Differ.generateDiff("src/test/resources/file1.yaml",
                "src/test/resources/file2.yaml",
                "stylish").trim();

        assertThat(result).isEqualToIgnoringNewLines(expected);
    }

    @Test
    public void testGenerateJsonComparisonSecond() throws Exception {
        String expected = new String(Files.readAllBytes(Paths.get("src/test/resources/expected11"))).trim();
        String result = Differ.generateDiff("src/test/resources/file11.json",
                "src/test/resources/file22.json",
                "stylish").trim();

        assertThat(result).isEqualToIgnoringNewLines(expected);
    }

    @Test
    public void testGenerateYamlComparisonSecond() throws Exception {
        String expected = new String(Files.readAllBytes(Paths.get("src/test/resources/expected11"))).trim();
        String result = Differ.generateDiff("src/test/resources/file11.yaml",
                "src/test/resources/file22.yaml",
                "stylish").trim();

        assertThat(result).isEqualToIgnoringNewLines(expected);
    }
}
