package abel.springframework.sfgpetclinic.model;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class Person extends BaseEntity {
    @Column(name = "first_name") //Snake casing for column definition is default Hibernate behavior
    protected String firstName;
    @Column(name = "last_name")
    protected String lastName;

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

    public abstract static class Builder<T extends Person,
            B extends Builder<T, B>> {

        public T obj;
        public B thisObj;

        public Builder() {
            obj = createObj();
            thisObj = getThis();
        }
        public B withLastName(String lastName) {
            obj.lastName = lastName;
            return thisObj;
        }
        public B withFirstName(String firstName ) {
            obj.firstName = firstName;
            return thisObj;
        }
        public T build() {
            return obj;
        }
        protected abstract T createObj();
        protected abstract B getThis();
    }
}
