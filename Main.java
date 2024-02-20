import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        PersonService service = new PersonService();
        PersonControler controler = new PersonControler(service);

        System.out.print("Введите колличество людей: ");
        int number = scanner.nextInt();

        for(int i = 0; i < number; i++){
            PersonData person = controler.createPerson();
            System.out.println();
        }

        scanner.close();
    }
}

