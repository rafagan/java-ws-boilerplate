package vetorlog.dto.error;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class DetailedErrorDTO extends ErrorDTO {
    private String code;
    private String extra;

    public DetailedErrorDTO(String message, String code, String extra) {
        this.code = code;
        this.message = message;
        this.extra = extra;
    }
}
