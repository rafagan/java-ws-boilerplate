package vetorlog.model.prototype;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

/**
 * Protótipo de id em String
 */
@Data
@MappedSuperclass
@EqualsAndHashCode(of = "id")
public class Model implements IModel {
    @Id
    @Column(name = "id", nullable = false, updatable = false)
    private String id;

    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;

    @PrePersist
    protected void onCreate() {
        if(this.getId() == null)
            this.setId(UUID.randomUUID().toString());
        createdAt = updatedAt = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = new Date();
    }
}
