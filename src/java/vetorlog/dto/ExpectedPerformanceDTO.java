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

    @JsonProperty("user_id")
    private String userId;

    @JsonProperty("created_at")
    private Date createdAt;
}
