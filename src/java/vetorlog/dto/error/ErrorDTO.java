package vetorlog.dto.error;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO de erros
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorDTO {
    protected String message;

    public String getMessage() {
        return message;
    }
}