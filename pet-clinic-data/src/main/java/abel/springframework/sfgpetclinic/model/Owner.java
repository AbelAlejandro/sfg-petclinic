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
            if(pets == null) pets = new HashSet<>();
            obj.pets = pets;
            return thisObj;
        }

        protected Owner createObj() {
            return new Owner();
        }

        protected Builder getThis() {
            return this;
        }
    }
}
