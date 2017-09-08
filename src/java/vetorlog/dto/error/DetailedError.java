package vetorlog.dto.error;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class DetailedError extends Error {
    private String code;
    private String extra;

    public DetailedError(String message, String code, String extra) {
        this.code = code;
        this.message = message;
        this.extra = extra;
    }
}
