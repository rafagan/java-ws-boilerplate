package vetorlog.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import vetorlog.api.util.DateParser;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExpectedPerformanceDTO {
    private double minimum;
    private double maximum;
    private double satisfactory;
    private double median;
    private double unsatisfactory;

    @JsonProperty("without_credibility")
    private double withoutCredibility;

    @JsonSerialize(using = DateParser.class)
    @JsonProperty(value = "created_at", access = JsonProperty.Access.READ_ONLY)
    private Date createdAt;

    @JsonProperty(value = "id", access = JsonProperty.Access.READ_ONLY)
    private long id;

    @JsonProperty(value = "user_name", required = true, access = JsonProperty.Access.READ_ONLY)
    private String userName;

    @JsonProperty(value = "typology_pump_id", access = JsonProperty.Access.WRITE_ONLY)
    private Long typologyPumpId;
}
