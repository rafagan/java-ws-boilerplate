package vetorlog.dto.error;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * DTO de erros 401
 * Se critical é verdadeiro, então deve-se deslogar o usuário do app
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class UnauthorizedErrorDTO extends ErrorDTO {
    private boolean critical;

    public UnauthorizedErrorDTO(String message, boolean critical) {
        super(message);
        this.critical = critical;
    }
}