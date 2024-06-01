package hexlet.code;

import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Paths;

import static org.assertj.core.api.Assertions.assertThat;

public class DifferTest {

    @Test
    public void testGenerateJsonComparison() throws Exception {
        String expected = new String(Files.readAllBytes(Paths.get("src/test/resources/expected1.yaml"))).trim();
        String result = Differ.generate("src/test/resources/file1.json", "src/test/resources/file2.json").trim();

        assertThat(result).isEqualToIgnoringNewLines(expected);
    }

    @Test
    public void testGenerateYamlComparison() throws Exception {
        String expected = new String(Files.readAllBytes(Paths.get("src/test/resources/expected1.yaml"))).trim();
        String result = Differ.generate("src/test/resources/file1.yaml", "src/test/resources/file2.yaml").trim();

        assertThat(result).isEqualToIgnoringNewLines(expected);
    }
}
