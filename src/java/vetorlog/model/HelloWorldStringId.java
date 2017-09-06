package vetorlog.model;


import vetorlog.model.prototype.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="HelloWorld")
public class HelloWorldStringId extends Model {
    @Column(name = "teste")
    private String teste;
}
