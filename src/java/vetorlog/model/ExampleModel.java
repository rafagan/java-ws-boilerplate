package vetorlog.model;


import lombok.Data;
import lombok.EqualsAndHashCode;
import vetorlog.model.prototype.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Exemplo de mapeamento objeto relacional, utilizando JPA
 */
@Entity
@Table(name="ExampleTable")
@Data
@EqualsAndHashCode(callSuper = true)
public class ExampleModel extends Model {
    @Column(name = "value_string")
    private String valueString;

    @Column(name = "value_double")
    private double valueDouble;
}
