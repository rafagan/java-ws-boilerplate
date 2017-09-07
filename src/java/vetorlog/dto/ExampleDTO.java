package vetorlog.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExampleDTO {
    private String id;
    private Date createdAt;
    private Date updatedAt;
    private String valueString;
    private double valueDouble;
}
