package abel.springframework.sfgpetclinic.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "pets")
public class Pet implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "type_id")
    private PetType petType;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private Owner owner;

    @Column(name = "birthDate")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthDate;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pet")
    private Set<Visit> visits = new HashSet<>();

    public Pet(String name, PetType petType, Owner owner, LocalDate birthDate, Set<Visit> visits) {
        this.name = name;
        this.petType = petType;
        this.owner = owner;
        this.birthDate = birthDate;
        if(visits == null || !visits.isEmpty()) this.visits = visits;
    }

    public Pet() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isNew() {
        return this.id == null;
    }

    public Set<Visit> getVisits() {
        if(this.visits == null) this.visits = new HashSet<>();
        return visits;
    }

    public Set<Visit> setVisits(Set<Visit> visits) {
        if(this.visits == null) this.visits = new HashSet<>();
        this.visits.addAll(visits);
        return visits;
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

    public void add(Visit visit) {
        if(this.visits == null) this.visits = new HashSet<>();
        this.visits.add(visit);
    }

    public static class Builder {
        private Long id;
        private String name;
        private PetType petType;
        private Owner owner;
        private LocalDate birthDate;
        private Set<Visit> visits;

        public Builder() {
        }

        public Builder withId(long id) {
            this.id = id;
            return this;
        }

        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public Builder withPetType(PetType petType) {
            this.petType = petType;
            return this;
        }

        public Builder withOwner(Owner owner) {
            this.owner = owner;
            return this;
        }

        public Builder withBirthDate(LocalDate birthDate) {
            this.birthDate = birthDate;
            return this;
        }

        public Builder withVisits(Set<Visit> visits) {
            this.visits = visits;
            return this;
        }

        public Pet build() {
            Pet pet = new Pet();
            pet.id = this.id;
            pet.name = this.name;
            pet.petType = this.petType;
            pet.owner = this.owner;
            pet.birthDate = this.birthDate;
            pet.visits = this.visits;
            return pet;
        }
    }
}
