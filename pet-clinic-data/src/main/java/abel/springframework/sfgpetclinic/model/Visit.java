package abel.springframework.sfgpetclinic.model;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "visits")
public class Visit implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @Column(name = "date")
    private LocalDate date;
    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "pet_id")
    private Pet pet;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isNew() {
        return this.id == null;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Pet getPet() {
        return pet;
    }

    public void setPet(Pet pet) {
        this.pet = pet;
    }

    public static class Builder {
        private Long id;
        private String description;
        private LocalDate date;
        private Pet pet;

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

        public Builder withDate(LocalDate date) {
            this.date = date;
            return this;
        }

        public Builder withPet(Pet pet) {
            this.pet = pet;
            return this;
        }

        public Visit build() {
            Visit visit = new Visit();
            visit.id = this.id;
            visit.description = this.description;
            visit.date = this.date;
            visit.pet = this.pet;
            return visit;
        }
    }
}
