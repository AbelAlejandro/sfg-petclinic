package abel.springframework.sfgpetclinic.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

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

    public abstract static class Builder<T extends PetType,
            B extends PetType.Builder<T, B>> {

        public T obj;
        public B thisObj;

        public Builder() {
            obj = createObj();
            thisObj = getThis();
        }

        public B withName(String name) {
            obj.name = name;
            return thisObj;
        }

        public T build() {
            return obj;
        }
        protected abstract T createObj();
        protected abstract B getThis();
    }
}
