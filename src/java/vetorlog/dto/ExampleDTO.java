package vetorlog.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * Exemplo de mapeamento de um Data Transfer Object (DTO), utilizando Jackson
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExampleDTO {
    private String id;
    @JsonProperty("created_at")
    private Date createdAt;
    @JsonProperty("updated_at")
    private Date updatedAt;
    @JsonProperty("value_string")
    private String valueString;
    @JsonProperty("value_double")
    private double valueDouble;
}
