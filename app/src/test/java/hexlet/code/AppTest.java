package hexlet.code;

import org.junit.jupiter.api.Test;
import java.nio.file.Files;
import java.nio.file.Paths;
import static org.assertj.core.api.Assertions.assertThat;

public class AppTest {

    @Test
    public void testGenerateStylishComparison() throws Exception {
        String expected = new String(Files.readAllBytes(Paths.get("src/test/resources/expected1"))).trim();
        String result = Differ.generate("stylish", "src/test/resources/file1.json",
                "src/test/resources/file2.json").trim();

        assertThat(result).isEqualToIgnoringNewLines(expected);
    }

    @Test
    public void testGenerateYamlComparison() throws Exception {
        String expected = """
            Property 'chars2' was updated. From [complex value] to false
            Property 'checked' was updated. From false to true
            Property 'default' was updated. From null to [complex value]
            Property 'id' was updated. From 45 to null
            Property 'key1' was removed
            Property 'key2' was added with value: 'value2'
            Property 'numbers2' was updated. From [complex value] to [complex value]
            Property 'numbers3' was removed
            Property 'numbers4' was added with value: [complex value]
            Property 'obj1' was added with value: [complex value]
            Property 'setting1' was updated. From 'Some value' to 'Another value'
            Property 'setting2' was updated. From 200 to 300
            Property 'setting3' was updated. From true to 'none'
            """;
        String result = Differ.generate("plain", "src/test/resources/file11.yaml",
                "src/test/resources/file22.yaml").trim();

        assertThat(result).isEqualToIgnoringNewLines(expected);
    }

    @Test
    public void testGenerateStylishComparisonSecond() throws Exception {
        String expected = new String(Files.readAllBytes(Paths.get("src/test/resources/expected11"))).trim();
        String result = Differ.generate("stylish", "src/test/resources/file11.json",
                "src/test/resources/file22.json").trim();

        assertThat(result).isEqualToIgnoringNewLines(expected);
    }

    @Test
    public void testGenerateYamlComparisonSecond() throws Exception {
        String expected = new String(Files.readAllBytes(Paths.get("src/test/resources/expected11"))).trim();
        String result = Differ.generate("stylish", "src/test/resources/file11.yaml",
                "src/test/resources/file22.yaml").trim();

        assertThat(result).isEqualToIgnoringNewLines(expected);
    }
    @Test
    public void testGenerateJsonComparison() throws Exception {
        String expected = new String(Files.readAllBytes(Paths.get("src/test/resources/diffList.json"))).trim();
        String result = Differ.generate("json", "src/test/resources/file11.yaml",
                "src/test/resources/file22.yaml").trim();

        assertThat(result).isEqualToIgnoringNewLines(expected);
    }
}
