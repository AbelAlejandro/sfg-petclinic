package abel.springframework.sfgpetclinic.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "owners")
public class Owner extends Person {
    @Column(name = "address")
    private String address;
    @Column(name = "city")
    private String city;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "owner")
    private Set<Pet> pets = new HashSet<>();
    @Column(name = "telephone")
    private String telephone;

    public Owner(String address, String city, Set<Pet> pets, String telephone) {
        this.address = address;
        this.city = city;
        if(pets != null) {
            this.pets = pets;
        }
        this.telephone = telephone;
    }

    public Owner() {
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Set<Pet> getPets() {
        if(this.pets == null) this.pets = new HashSet<>();
        return pets;
    }

    public void setPets(Set<Pet> pets) {
        this.pets = pets;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public Pet getPet(String name) {
        return getPet(name, false);
    }

    public Pet getPet(String name, boolean ignoreNew) {
        name = name.toLowerCase();
        for (Pet pet : pets) {
            if (!ignoreNew || !pet.isNew()) {
                String compName = pet.getName();
                compName = compName.toLowerCase();
                if (compName.equals(name)) {
                    return pet;
                }
            }
        }
        return null;
    }

    public void addPet(Pet pet) {
        this.pets.add(pet);
    }

    public static class Builder
            extends Person.Builder<Owner, Owner.Builder> {

        public Builder withTelephone(String telephone) {
            obj.telephone = telephone;
            return thisObj;
        }

        public Builder withCity(String city) {
            obj.city = city;
            return thisObj;
        }

        public Builder withAddress(String address) {
            obj.address = address;
            return thisObj;
        }

        public Builder withPets(Set<Pet> pets) {
            if(pets != null) obj.pets = pets;
            else obj.pets = new HashSet<>();
            return thisObj;
        }

        protected Owner createObj() {
            Owner owner = new Owner();
            owner.pets = new HashSet<>();
            return owner;
        }

        protected Builder getThis() {
            return this;
        }
    }
}
