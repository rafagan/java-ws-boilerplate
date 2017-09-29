package vetorlog.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TypologyPumpDTO {
    private long id;
    private String description;
    private String observations;
}
