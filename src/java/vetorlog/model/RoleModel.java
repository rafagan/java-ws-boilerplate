package vetorlog.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import vetorlog.model.prototype.ModelLong;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

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
