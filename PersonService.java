import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PersonService {

    public PersonData processInputData(String[] inputData){
        String lastName = "";
        String firstName = "";
        String middleName = "";
        Date birthDate = null;
        long phoneNumber = 0;
        Gender gender = null;

        try {
            for (String item : inputData) {
                try {
                    phoneNumber = Long.parseLong(item);
                } catch (NumberFormatException e) {
                    if (item.matches("\\d{2}.\\d{2}.\\d{4}")) { 
                        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
                        birthDate = dateFormat.parse(item);
                    } else if (item.equalsIgnoreCase("m") || item.equalsIgnoreCase("f")) { 
                        char gen = item.toLowerCase().charAt(0);
                        if (gen == 'm') {
                            gender = Gender.M;
                        } else if (gen == 'f') {
                            gender = Gender.F;
                        }
                    } else {
                        if (lastName.equals("")) {
                            lastName = item;
                        } else if (firstName.equals("")) {
                            firstName = item;
                        } else if (middleName.equals("")) {
                            middleName = item;
                        } else {
                            throw new Exception("Неверный формат даты.");
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        PersonData person = new PersonData(lastName, firstName, middleName, birthDate, phoneNumber, gender);
        boolean correctData = checkPersonData(person);

        if (correctData) {
            writeToFile(person);
            return person;
        }
        return null;
    }

    private void writeToFile(PersonData person) {
        String fileName = person.getLastName() + ".txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            writer.write(person.getLastName() + " " +
                    person.getFirstName() + " " +
                    person.getMiddleName() + " " +
                    new SimpleDateFormat("dd.MM.yyyy").format(person.getBirthDate()) + " " +
                    person.getPhoneNumber() + " " +
                    person.getGender());
            writer.newLine();
            System.out.println("Данные успешно записаны в файл: " + fileName);
        } catch (IOException e) {
            System.err.println("Ошибка при записи в файл:");
            e.printStackTrace();
        }
    }

    private boolean checkPersonData(PersonData person){
        boolean correctData = true;

        if(person.getLastName() == ""){
            correctData = false;
            System.err.println("Ошибка при записи Фамилии");
        } else if(person.getFirstName() == ""){
            correctData = false;
            System.err.println("Ошибка при записи Имени");
        } else if(person.getMiddleName() == ""){
            correctData = false;
            System.err.println("Ошибка при записи Отчества");
        } else if(person.getBirthDate() == null){
            correctData = false;
            System.err.println("Ошибка при записи Даты Рождения");
        } else if(person.getPhoneNumber() == 0){
            correctData = false;
            System.err.println("Ошибка при записи Номера Телефона");
        } else if(person.getGender() == null){
            correctData = false;
            System.err.println("Ошибка при записи Пола");
        }
        return correctData;
    }
}
