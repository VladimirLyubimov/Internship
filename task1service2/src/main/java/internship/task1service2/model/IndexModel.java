package internship.task1service2.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class IndexModel {
    @JsonProperty("index_name")
    private String indexName;
    @JsonProperty("index_table")
    private String indexTable;
    @JsonProperty("index_column")
    private String indexColumn;
}
