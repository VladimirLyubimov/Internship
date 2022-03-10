package internship.task1service1.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class TableModel {
    private List<ColumnModel> columns;
    private List<IndexModel> index;
    @JsonProperty("table_type")
    private String tableType;
    @JsonProperty("table_schema")
    private String tableSchema;
    @JsonProperty("table_column")
    private List<String> keyColumn;
    @JsonProperty("table_name")
    private String tableName;
}
