package hexlet.code.formats;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;
import java.util.Map;

public class Json {
    public static String json(List<Map<String, Object>> listForFormatting) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        var result = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(listForFormatting);
        return result;
    }
}
