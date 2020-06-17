package abel.springframework.sfgpetclinic.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

@MappedSuperclass
public class BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public static abstract class Builder<T extends BaseEntity,
            B extends BaseEntity.Builder<T, B>> {

        protected T obj;
        protected B thisObj;

        public Builder() {
            obj = createObj();
            thisObj = getThis();
        }
        public B withId(Long id) {
            obj.id = id;
            return thisObj;
        }
        public T build() {
            return obj;
        }
        protected abstract T createObj();
        protected abstract B getThis();
    }
}
