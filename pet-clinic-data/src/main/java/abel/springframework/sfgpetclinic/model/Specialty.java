package abel.springframework.sfgpetclinic.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "specialties")
public class Specialty implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @Column(name = "description")
    private String description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isNew() {
        return this.id == null;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public static class Builder {
        private Long id;
        private String description;

        public Builder() {
        }

        public Builder withId(long id) {
            this.id = id;
            return this;
        }

        public Builder withDescription(String description) {
            this.description = description;
            return this;
        }

        public Specialty build() {
            Specialty specialty = new Specialty();
            specialty.id = this.id;
            specialty.description = this.description;
            return specialty;
        }
    }
}
