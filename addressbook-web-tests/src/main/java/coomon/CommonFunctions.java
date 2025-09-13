package coomon;

import java.io.File;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CommonFunctions {
    public static String randomString(int n){
        Random rnd = new Random();
        Supplier<Integer> randomNumbers = ()-> rnd.nextInt(26);
        var result = Stream.generate(randomNumbers)
                .limit(n)
                .map(i-> 'a' + i)
                .map(Character::toString)
                .collect(Collectors.joining());

       return result;

    }

    public static String randomPhoneNumber(){
        Random rnd = new Random();
        int areaCodeNumber = rnd.nextInt(1000);
        String codeNumberStr = String.format("%03d", areaCodeNumber);

        int mainNumber = rnd.nextInt(10000000);
        String mainNumberStr = String.format("%07d", mainNumber);

        String fullNumber = "8("+codeNumberStr+")-"+mainNumberStr;
        return fullNumber;
    }

    public static String randomPhoto(String path){
        var file = new File(path).list();
        var rnd = new Random();
        var index = rnd.nextInt(file.length);
        return Paths.get(path, file[index]).toString();

    }

    public static String nameGenerator(String gender, String personalData){

        /**
         * Генерирует часть персональных данных в зависимости от пола и типа данных.
         * @param gender Пол человека ("male" или "female").
         * @param personalData Тип данных для генерации ("name" или "surname").
         * @return Сгенерированное имя или фамилия в зависимости от параметров.
         */

        // Приведение к нижнему регистру
        gender = gender.toLowerCase();
        personalData = personalData.toLowerCase();

        // Проверка допустимых значений
        if (!gender.equals("male") && !gender.equals("female")) {
            throw new IllegalArgumentException("Некорректный пол: " + gender);
        }
        if (!personalData.equals("firstname") && !personalData.equals("lastname")) {
            throw new IllegalArgumentException("Некорректный тип данных: " + personalData);
        }

        final String[] maleFirstNames = {"Алексей", "Иван", "Павел", "Дмитрий", "Сергей"};
        final String[] femaleFirstNames = {"Анна", "Мария", "Елена", "Ольга", "Наталья"};
        final String[] maleLastNames = {"Иванов", "Петров", "Сидоров", "Кузнецов", "Смирнов"};
        final String[] femaleLastNames = {"Иванова", "Петрова", "Сидорова", "Кузнецова", "Смирнова"};

        var rnd = new Random();

        switch (gender) {
            case "male":
                if (personalData.equals("firstname")) {
                    return maleFirstNames[rnd.nextInt(maleFirstNames.length)];
                } else { // lastname
                    return maleLastNames[rnd.nextInt(maleLastNames.length)];
                }
            case "female":
                if (personalData.equals("firstname")) {
                    return femaleFirstNames[rnd.nextInt(femaleFirstNames.length)];
                } else { // lastname
                    return femaleLastNames[rnd.nextInt(femaleLastNames.length)];
                }
            default:
                throw new IllegalArgumentException("Некорректные параметры");
        }
    }
}





