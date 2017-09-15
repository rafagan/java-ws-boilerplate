package vetorlog.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import vetorlog.model.prototype.ModelLong;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="Role")
@Data
@EqualsAndHashCode(callSuper = true)
public class RoleModel extends ModelLong {
    @Column
    private String name; //TODO: mudar o nome para suportar apenas enum

    @OneToMany(mappedBy = "role")
    private Set<UserModel> users = new HashSet<>();
}
