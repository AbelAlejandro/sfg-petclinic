package abel.springframework.sfgpetclinic.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "types")
public class PetType extends BaseEntity {
    public PetType(Long id, String name) {
        super(id);
        this.name = name;
    }

    public PetType() {
    }

    @Column(name = "name")
    protected String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    public static class Builder
            extends BaseEntity.Builder<PetType, PetType.Builder> {

        public PetType.Builder withName(String name) {
            obj.name = name;
            return thisObj;
        }

        protected PetType createObj() {
            return new PetType();
        }

        protected PetType.Builder getThis() {
            return this;
        }
    }
}
