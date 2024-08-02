package lab10;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Getter
@Setter
public class Person implements Comparable<Person> {
    private String id, firstName, lastName;
    
    public Person(String id, String firstName, String lastName) { setId(id).setFirstName(firstName).setLastName(lastName); }
    
    @Override
    public int compareTo(Person o) {
        return getId().compareTo(o.getId());
    }
}
