package vetorlog.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import vetorlog.model.prototype.ModelLong;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="tipologia_bombas")
@Data
@EqualsAndHashCode(callSuper = true)
public class TypologyPumpModel extends ModelLong {
    @Column(name = "descricao")
    private String description;

    @Column(name = "observacoes")
    private String observations;

    @Column(name = "user_id")
    private String userId;

    @ManyToMany(mappedBy = "typologyPumps")
    private Set<GroupSitesModel> groupSites;

    @OneToMany(mappedBy = "typologyPump")
    private Set<ExpectedPerformanceModel> expectedPerformances = new HashSet<>();
}
