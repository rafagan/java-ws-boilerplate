package vetorlog.model;

import lombok.*;
import vetorlog.model.prototype.Model;

import javax.persistence.*;
import java.security.Principal;

@Entity
@Table(name="User")
@Data
@EqualsAndHashCode(callSuper = true, exclude = {"role"})
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
