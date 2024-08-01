package lab9;

import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
public class Person implements Comparable<Person> {
    private final String id;
    private String firstName;
    private String lastName;
    
    public Person(String id, String firstName, String lastName) {
        this.id = id;
        setFirstName(firstName); setLastName(lastName);
    }
    
    @Override
    public int compareTo(Person o) {
        return getId().compareTo(o.getId());
    }
    
    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Person person = (Person) object;
        return Objects.equals(id, person.id) && Objects.equals(firstName, person.firstName) && Objects.equals(lastName, person.lastName);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName);
    }
    
    @Override
    public String toString() {
        return "Person [id=" + getId()
                + ", firstName=" + getFirstName()
                + ", lastName=" + getLastName() + "]";
    }
}
