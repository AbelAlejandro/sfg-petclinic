package abel.springframework.sfgpetclinic.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "vets")
public class Vet implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @Column(name = "first_name") //Snake casing for column definition is default Hibernate behavior
    protected String firstName;

    @Column(name = "last_name")
    protected String lastName;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "vet_specialties", joinColumns = @JoinColumn(name = "vet_id"),
            inverseJoinColumns = @JoinColumn(name = "specialty_id"))
    private Set<Specialty> specialties = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isNew() {
        return this.id == null;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Set<Specialty> getSpecialties() {
        return specialties;
    }

    public void setSpecialties(Set<Specialty> specialties) {
        this.specialties = specialties;
    }

    public static class Builder {
        private Long id;
        private String firstName;
        private String lastName;
        private Set<Specialty> specialties;

        public Builder() {
        }

        public Builder withId(long id) {
            this.id = id;
            return this;
        }

        public Builder withFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder withLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder withSpecialties(Set<Specialty> specialties) {
            this.specialties = specialties;
            return this;
        }

        public Vet build() {
            Vet vet = new Vet();
            vet.id = this.id;
            vet.firstName = this.firstName;
            vet.lastName = this.lastName;
            vet.specialties = this.specialties;
            return vet;
        }
    }
}
