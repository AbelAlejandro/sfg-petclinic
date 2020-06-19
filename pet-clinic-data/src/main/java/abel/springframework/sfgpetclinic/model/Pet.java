package abel.springframework.sfgpetclinic.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "pets")
public class Pet extends BaseEntity {
    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "type_id")
    private PetType petType;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private Owner owner;

    @Column(name = "birthDate")
    private LocalDate birthDate;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pet")
    private Set<Visit> visits = new HashSet<>();

    public Set<Visit> getVisits() {
        return visits;
    }

    public void setVisits(Set<Visit> visits) {
        this.visits = visits;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PetType getPetType() {
        return petType;
    }

    public void setPetType(PetType petType) {
        this.petType = petType;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public static class Builder
            extends BaseEntity.Builder<Pet, Pet.Builder> {

        public Pet.Builder withName(String name) {
            obj.name = name;
            return thisObj;
        }

        public Pet.Builder withPetType(PetType petType) {
            obj.petType = petType;
            return thisObj;
        }

        public Pet.Builder withOwner(Owner owner) {
            obj.owner = owner;
            return thisObj;
        }

        public Pet.Builder withBirthDate(LocalDate birthDate) {
            obj.birthDate = birthDate;
            return thisObj;
        }

        protected Pet createObj() {
            return new Pet();
        }

        protected Pet.Builder getThis() {
            return this;
        }
    }
}
