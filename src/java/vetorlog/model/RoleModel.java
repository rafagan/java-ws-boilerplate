package vetorlog.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import vetorlog.model.prototype.ModelLong;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Mapeamento objeto-relacional da tabela de papéis (Roles)
 * Atualmente, o sistema suporta admin e user.
 * Para que o usuário seja autorizado, deve-se validar se o método da requisição está mapeado com RolesAllowed e
 * se o AuthorizationInterceptor está identificado o acesso como não restrito
 */
@Entity
@Table(name="Role")
@Data
@EqualsAndHashCode(callSuper = true, exclude = {"users"})
@ToString(exclude = {"users"})
public class RoleModel extends ModelLong {
    @Column(unique = true)
    private String name;

    @Column
    private boolean admin;

    @Column
    private boolean user;

    @OneToMany(mappedBy = "role")
    private Set<UserModel> users = new HashSet<>();
}
