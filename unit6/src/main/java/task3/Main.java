package task3;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        StreamApiHelper helper = new StreamApiHelper();
        List<Person> persons = helper.getPersons();
        System.out.println(helper.getUniqueNamePersons(persons));
        System.out.println(helper.getSumAgeForPersonWithNeedName(persons, "Алексей"));
        System.out.println(helper.getSumAgeForPersonWithNeedName(persons, "Александр"));
        System.out.println(helper.convertToAgeMap(persons));
        System.out.println(helper.convertToNameMap(persons));
        System.out.println(helper.getListPersonsWhoseAgeOverGiven(persons, 33));
        System.out.println(helper.getListPersonsWhoseAgeOverGiven(persons, 77));
    }


}
