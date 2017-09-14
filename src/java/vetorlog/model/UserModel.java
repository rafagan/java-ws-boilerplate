package vetorlog.model;

import lombok.*;
import vetorlog.model.prototype.Model;

import javax.persistence.*;

@Entity
@Table(name="User")
@Data
@EqualsAndHashCode(callSuper = true)
public class UserModel extends Model {
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
