package requestUtils;

import com.typesafe.config.ConfigValue;
import lombok.*;

import lombok.Builder;

import java.util.Map;
import java.util.Set;

@Builder(setterPrefix = "set")
@AllArgsConstructor
@Getter
@NoArgsConstructor
public class RequestElements {
    private String baseUrl;
    private String endpoint;
//    private Map<String, String> headers;
    private Set<Map.Entry<String, ConfigValue>> pathParams;
    private Set<Map.Entry<String, ConfigValue>> queryParams;

}
