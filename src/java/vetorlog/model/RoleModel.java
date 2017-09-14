package vetorlog.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import vetorlog.model.prototype.ModelLong;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="User")
@Data
@EqualsAndHashCode(callSuper = true)
public class RoleModel extends ModelLong {
    @Column
    private String name;

    @OneToMany(mappedBy = "roles")
    private Set<UserModel> users = new HashSet<>();
}
