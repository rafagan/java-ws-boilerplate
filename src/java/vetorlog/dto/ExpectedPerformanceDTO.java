package vetorlog.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    @JsonProperty("created_at")
    private Date createdAt;

    @JsonProperty(value = "user_name", required = true, access = JsonProperty.Access.READ_ONLY)
    private String userName;

    @JsonProperty(value = "typology_pump_id", access = JsonProperty.Access.WRITE_ONLY)
    private String typologyPumpId;
}
