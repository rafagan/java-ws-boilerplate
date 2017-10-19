package vetorlog.model;

import lombok.*;
import vetorlog.model.prototype.Model;

import javax.persistence.*;
import java.security.Principal;

/**
 * Mapeamento objeto-relacional da tabela de usuários
 * Caso isSuperUser esteja marcado e o método da requisição esteja mapeado como RolesAllowed, o usuário sempre será autorizado
 */
@Entity
@Table(name="User")
@Data
@EqualsAndHashCode(callSuper = true, exclude = {"role"})
@ToString(exclude = {"role"})
public class UserModel extends Model implements Principal {
    @Column
    private String name;
    @Column
    private String email;
    @Column
    private String password;
    @Column
    private boolean isSuperUser;

    @ManyToOne
    private RoleModel role;
}
