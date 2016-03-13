package de.laboranowitsch.jndimulti.entity;

/**
 * People database entity.
 *
 * Created by cla on 3/10/16.
 */
public class People {

    private Long personId;
    private String firstName;
    private String lastName;

    private People(Builder builder) {
        setPersonId(builder.personId);
        setFirstName(builder.firstName);
        setLastName(builder.lastName);
    }

    public Long getPersonId() {
        return personId;
    }

    public void setPersonId(Long personId) {
        this.personId = personId;
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

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("People{");
        sb.append("personId=").append(personId);
        sb.append(", firstName='").append(firstName).append('\'');
        sb.append(", lastName='").append(lastName).append('\'');
        sb.append('}');
        return sb.toString();
    }

    public static Builder builder() {
        return new Builder();
    }
    public static final class Builder {
        private Long personId;
        private String firstName;
        private String lastName;

        public Builder() {
        }

        public Builder personId(Long val) {
            personId = val;
            return this;
        }

        public Builder firstName(String val) {
            firstName = val;
            return this;
        }

        public Builder lastName(String val) {
            lastName = val;
            return this;
        }

        public People build() {
            return new People(this);
        }
    }
}
