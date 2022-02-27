import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {
        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();
        for (int i = 0; i < 10_000_000; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }

        Stream<Person> stream = persons.stream();
        long countPeople = stream
                .filter(p -> p.getAge() < 18)
                .count();
        System.out.println("Количество несовершеннолетних: " + countPeople);

        Stream<Person> stream1 = persons.stream();
        List<String> listFamily = stream1
                .filter(p -> p.getAge() > 18)
                .filter(p -> p.getAge() < 27)
                .map(Person::getFamily)
                .collect(Collectors.toList());

        System.out.println("Список фамилий призывников: ");
        listFamily.forEach(System.out::println);

        Stream<Person> stream3 = persons.stream();
        List<String> listFamily2 = stream3
                .filter(p -> p.getAge() > 18)
                .filter(p -> p.getEducation() == Education.HIGHER)
                .filter(p -> (p.getSex() == Sex.MAN) || p.getAge() < 65)
                .filter(p -> (p.getSex() == Sex.WOMAN) || p.getAge() < 60)
                .map(Person::getFamily)
                .sorted(Comparator.naturalOrder())
                .collect(Collectors.toList());

        System.out.println("Отсортированный по фамилии список потенциально работоспособных людей с высшим образованием: ");
        listFamily2.forEach(System.out::println);
    }
}
