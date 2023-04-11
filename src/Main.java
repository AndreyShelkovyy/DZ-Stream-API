import java.util.*;
import java.util.stream.Collectors;


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

        long minors = persons.stream()
                .filter(i -> i.getAge() < 18)
                .count();
        System.out.println("Количество несовершеннолетних: " + minors);

        List<String> listConscripts = persons.stream()
                .filter(a -> a.getSex() == Sex.MAN)
                .filter(b -> b.getAge() >= 18)
                .filter(c -> c.getAge() <= 27)
                .map(Person::getFamily).collect(Collectors.toList());
        System.out.println("Список мужчин призывного возраста от 18 и до 27 лет: ");
        for (int i = 0; i < listConscripts.size(); i++) {
            System.out.println((i + 1) + ". " + listConscripts.get(i));
        }

        List<String> listWorkable = persons.stream()
                .filter(a -> (a.getEducation() == Education.HIGHER))
                .filter(b -> (b.getAge() >= 18))
                .filter(c -> (c.getSex() == Sex.WOMAN && c.getAge() <= 60)
                        || (c.getSex() == Sex.MAN && c.getAge() <= 65))
                .sorted(Comparator.comparing(Person::getFamily))
                .map(Person::getFamily).collect(Collectors.toList());
        System.out.println("Работоспособные люди с высшим образованием: ");
        for (int i = 0; i < listWorkable.size(); i++) {
            System.out.println((i + 1) + ". " + listWorkable.get(i));
        }
    }
}