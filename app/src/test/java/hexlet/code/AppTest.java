package hexlet.code;

import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Paths;

import static org.assertj.core.api.Assertions.assertThat;

public class AppTest {

    static final String PATH_TO_RESOURCES = "src/test/resources/";

    public static String fixture(String nameOfFile) {
        return PATH_TO_RESOURCES + nameOfFile;
    }

    @Test
    public void testGenerateStylishComparisonJson() throws Exception {
        String expected = Files.readString(Paths.get(fixture("expected1.stylish"))).trim();
        String result = Differ.generate(fixture("file1.json"), fixture("file2.json"), "stylish").trim();
        assertThat(result).isEqualToIgnoringNewLines(expected);
    }

    @Test
    public void testGenerateStylishComparisonYaml() throws Exception {
        String expected = Files.readString(Paths.get(fixture("expected1.stylish"))).trim();
        String result = Differ.generate(fixture("file1.yaml"), fixture("file2.yaml"), "stylish").trim();
        assertThat(result).isEqualToIgnoringNewLines(expected);
    }

    @Test
    public void testGeneratePlainComparisonJson() throws Exception {
        String expected = Files.readString(Paths.get(fixture("expected1.plain"))).trim();
        String result = Differ.generate(fixture("file1.json"), fixture("file2.json"), "plain").trim();
        assertThat(result).isEqualToIgnoringNewLines(expected);
    }

    @Test
    public void testGeneratePlainComparisonYaml() throws Exception {
        String expected = Files.readString(Paths.get(fixture("expected1.plain"))).trim();
        String result = Differ.generate(fixture("file1.yaml"), fixture("file2.yaml"), "plain").trim();
        assertThat(result).isEqualToIgnoringNewLines(expected);
    }

    @Test
    public void testGenerateJsonComparisonJson() throws Exception {
        String expected = Files.readString(Paths.get(fixture("expected1.json"))).trim();
        String result = Differ.generate(fixture("file1.json"), fixture("file2.json"), "json").trim();
        assertThat(result).isEqualToIgnoringNewLines(expected);
    }

    @Test
    public void testGenerateJsonComparisonYaml() throws Exception {
        String expected = Files.readString(Paths.get(fixture("expected1.json"))).trim();
        String result = Differ.generate(fixture("file1.yaml"), fixture("file2.yaml"), "json").trim();
        assertThat(result).isEqualToIgnoringNewLines(expected);
    }

    @Test
    public void testGenerateWithoutFormatterJson() throws Exception {
        String expected = Files.readString(Paths.get(fixture("expected1.stylish"))).trim();
        String result = Differ.generate(fixture("file1.json"), fixture("file2.json")).trim();
        assertThat(result).isEqualToIgnoringNewLines(expected);
    }

    @Test
    public void testGenerateWithoutFormatterYaml() throws Exception {
        String expected = Files.readString(Paths.get(fixture("expected1.stylish"))).trim();
        String result = Differ.generate(fixture("file1.yaml"), fixture("file2.yaml")).trim();
        assertThat(result).isEqualToIgnoringNewLines(expected);
    }

}
