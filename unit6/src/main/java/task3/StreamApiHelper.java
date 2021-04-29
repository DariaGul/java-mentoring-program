package task3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

public class StreamApiHelper {

    public List<Person> getListPersonsWhoseAgeOverGiven(List<Person> persons, int age) {
        List<Person> personsWithAgeOverGiven = persons.stream()
            .filter(c -> c.getAge() > age)
            .collect(Collectors.toList());
        return personsWithAgeOverGiven;
    }

    public Map<UUID, String> convertToNameMap(List<Person> persons) {
        Map<UUID, String> map = persons.stream()
            .collect(Collectors.toMap(Person::getId, Person::getName));
        return map;
    }

    public Map<Integer, List<Person>> convertToAgeMap(List<Person> persons) {
        Map<Integer, List<Person>> map = persons.stream()
            .collect(Collectors.groupingBy(Person::getAge, Collectors.toCollection(ArrayList::new)));
        return map;
    }

    public int getSumAgeForPersonWithNeedName(List<Person> persons, String needName) {
        int age = persons.stream()
            .filter(c -> needName.equals(c.getName()))
            .map(Person::getAge)
            .mapToInt(Integer::intValue)
            .sum();
        return age;
    }

    public Set<String> getUniqueNamePersons(List<Person> persons) {
        Set<String> name = persons.stream()
            .map(Person::getName)
            .collect(Collectors.toSet());
        return name;
    }

    public List<Person> getPersons() {
        List<Person> persons = new ArrayList<>();
        persons.add(new Person("Александр", 42));
        persons.add(new Person("Николай", 21));
        persons.add(new Person("Александр", 21));
        persons.add(new Person("Алексей", 36));
        persons.add(new Person("Алексей", 19));

        return persons;
    }

}
