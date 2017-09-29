package vetorlog.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import vetorlog.model.prototype.ModelLong;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="groupsites")
@Data
@EqualsAndHashCode(callSuper = true)
public class GroupSitesModel extends ModelLong {
    @Column(name = "nome")
    private String name;

    @ManyToMany
    @JoinTable(
            name="tipologia_grupos",
            joinColumns={@JoinColumn(name="groupsites_id")},
            inverseJoinColumns={@JoinColumn(name="tipologia_bombas_id")}
    )
    private Set<TypologyPumpModel> typologyPumps;
}
