package vetorlog.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import vetorlog.model.prototype.ModelLong;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="desempenho_esperado")
@Data
@EqualsAndHashCode(callSuper = true)
public class ExpectedPerformanceModel extends ModelLong {
    @Column(name = "minimo")
    private double minimum;

    @Column(name = "maximo")
    private double maximum;

    @Column(name = "satisfatorio")
    private double satisfactory;

    @Column(name = "mediano")
    private double median;

    @Column(name = "insatisfatorio")
    private double unsatisfactory;

    @Column(name = "sem_credibilidade")
    private double withoutCredibility;

    @Column(name = "user_id")
    private String userId;
}
