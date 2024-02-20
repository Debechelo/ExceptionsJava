import java.util.Scanner;

public class PersonControler {
    private PersonService personService;
    private Scanner scanner;

    public PersonControler(PersonService personService) {
        this.personService = personService;
        scanner = new Scanner(System.in);
    }

    public PersonData createPerson() {
        System.out.println("Введите данные в любом порядке, разделенные пробелом: Фамилия, Имя, Отчество, Дата Рождения, Номер Телефона, Пол");
        String input = scanner.nextLine();
        String[] data = input.split(" ");

        if (data.length != 6) {
            System.err.println("Произошла ошибка: Неверное количество данных.");
        }

        return personService.processInputData(data);
    }
}
