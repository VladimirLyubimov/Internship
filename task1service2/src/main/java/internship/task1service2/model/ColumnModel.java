package internship.task1service2.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ColumnModel {
    private String name;
    private String type;
    private int scale;
    @JsonProperty("numeric_precision")
    private int numericPrecision;
    @JsonProperty("numeric_precision_radix")
    private int numericPrecisionRadix;
    @JsonProperty("datetime_precision")
    private int datetimePrecision;
    @JsonProperty("character_maximum_length")
    private int characterMaximumLength;
    @JsonProperty("is_nullable")
    private String isNullable;
}
