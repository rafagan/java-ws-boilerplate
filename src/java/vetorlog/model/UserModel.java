package vetorlog.model;

import lombok.*;
import vetorlog.model.prototype.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="User")
@Data
@EqualsAndHashCode(callSuper = true)
public class UserModel extends Model {
    @Column(unique = true)
    private String username;
    @Column
    private String email;
    @Column
    private String password;
}
