package task3;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        StreamApiHelper helper = new StreamApiHelper();
        List<Person> persons = helper.getPersons();
        helper.getUniqueNamePersons(persons);
        helper.getSumAgeForPersonWithNeedName(persons, "Алексей");
        helper.getSumAgeForPersonWithNeedName(persons, "Александр");
        helper.convertToAgeMap(persons);
        helper.convertToNameMap(persons);
        helper.getListPersonsWhoseAgeOverGiven(persons, 33);
        helper.getListPersonsWhoseAgeOverGiven(persons, 77);
    }


}
