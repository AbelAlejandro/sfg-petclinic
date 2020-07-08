package abel.springframework.sfgpetclinic.model;

import java.io.Serializable;

public class HealthCheck implements Serializable {
    public enum Status {
        OK,
        FAILED
    }

    private Status status;

    public Status getStatus() {
        return status;
    }


    public static class Builder {
        private Status status;

        public Builder() {
        }

        public Builder withStatus(Status status) {
            this.status = status;
            return this;
        }

        public HealthCheck build() {
            HealthCheck check = new HealthCheck();
            check.status = this.status;
            return check;
        }
    }
}
