package vetorlog.dto.error;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class Unauthorized extends Error {
    private boolean critical;

    public Unauthorized(String message, boolean critical) {
        super(message);
        this.critical = critical;
    }
}