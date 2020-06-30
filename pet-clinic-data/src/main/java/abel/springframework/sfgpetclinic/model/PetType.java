package abel.springframework.sfgpetclinic.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "types")
public class PetType implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @Column(name = "name")
    protected String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PetType(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public PetType() {
    }

    public boolean isNew() {
        return this.id == null;
    }

    @Override
    public String toString() {
        return name;
    }
}
