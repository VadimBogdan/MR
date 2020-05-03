package Lab4;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int size = 0;
        do {
            System.out.println("Введіть кількість студентів, яких необхідно створити.");
            try {
                size = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException exc) {
                System.err.println("Невірно набране число.");
            }
        } while (size == 0);

        System.out.println("Ініціалізація масиву з " + size + " студентів");
        StudentRecordBook[] recordBooks = new StudentRecordBook[size];
        for (int i = 0; i < size; i++) {
            String firstName = "";
            String middleName = "";
            String lastName = "";
            boolean isNMLCorrect = false;
            do {
                System.out.println("Введіть ім'я, прізвище і по-батькові студента.");
                try {
                    String[] NML = scanner.nextLine().split("\\s");
                    for (var s : NML) {
                        if (Pattern.matches("[0-9]", s)) {
                            throw new StudentRecordBookWrongNameException(s);
                        }
                    }
                    if (NML.length != 3) {
                        throw new StudentRecordBookWrongNMLLengthException();
                    }
                    firstName = NML[0];
                    lastName = NML[1];
                    middleName = NML[2];
                    isNMLCorrect = true;
                } catch (StudentRecordBookWrongNameException exc) {
                    System.err.println("Слово +" + exc + "+ набрано з помилками.");
                } catch (StudentRecordBookWrongNMLLengthException exc) {
                    System.err.println("ПІБ набрано не вірно.");
                }
            } while (!isNMLCorrect);

            ArrayList<String> exams = new ArrayList<>();
            ArrayList<String> credits = new ArrayList<>();
            boolean isExamsCorrect = false;
            boolean isCreditsCorrect = false;

            do {
                exams.clear();
                System.out.println("Виберіть экзамени, які проходив студент.");
                for (int j = 0; j < Exams.length; j++) {
                    System.out.println(j + ". " + Exams[j]);
                }
                try {
                    for (var s : scanner.nextLine().split("")) {
                        if (!Pattern.matches("^[0-9]$", s)) {
                            throw new StudentRecordBookWrongExamException(s);
                        }
                        int index = Integer.parseInt(s);
                        if (Exams.length <= index) {
                            throw new IndexOutOfBoundsException(index + "");
                        }
                        exams.add(Exams[index]);
                    }
                    isExamsCorrect = true;
                } catch (StudentRecordBookWrongExamException exc) {
                    System.err.println("Символ +" + exc.getLocalizedMessage() + "+ не є числом зі списку.");
                } catch (IndexOutOfBoundsException exc) {
                    System.err.println("Номера +" + exc.getLocalizedMessage() + "+ не існує.");
                }
            } while (!isExamsCorrect);

            do {
                credits.clear();
                System.out.println("Виберіть заліки, які отримав студент.");
                for (int j = 0; j < Credits.length; j++) {
                    System.out.println(j + ". " + Credits[j]);
                }
                try {
                    for (var s : scanner.nextLine().split("")) {
                        if (!Pattern.matches("^[0-9]$", s)) {
                            throw new StudentRecordBookWrongExamException(s);
                        }
                        int index = Integer.parseInt(s);
                        if (Credits.length <= index) {
                            throw new IndexOutOfBoundsException(index + "");
                        }
                        credits.add(Credits[index]);
                    }
                    isCreditsCorrect = true;
                } catch (StudentRecordBookWrongNameException | NumberFormatException exc) {
                    System.err.println("Символ +" + exc.getLocalizedMessage() + "+ не є числом зі списку.");
                } catch (IndexOutOfBoundsException exc) {
                    System.err.println("Номера +" + exc.getLocalizedMessage() + "+ не існує.");
                }
            } while (!isCreditsCorrect);


            int num = initIdNum();
            int course = initCourse();
            double averageScorePerSession = initAvgScore();

            recordBooks[i] = new StudentRecordBook(firstName, middleName, lastName, num, course,
                    exams, credits, averageScorePerSession);
        }
        for (var m : recordBooks) {
            System.out.println(m);
        }
        System.out.println("\n");
        ArrayList<StudentRecordBook> matched = findByAverageSessionScore(recordBooks, 4.5);
        for (var m : matched) {
            System.out.println(m);
        }

        String firstName = "";
        String middleName = "";
        String lastName = "";
        boolean isNMLCorrect = false;
        do {
            System.out.println("Введіть ім'я, прізвище і по-батькові студента для пошуку його заліків.");
            String[] NML = scanner.nextLine().split("\\s");
            try {
                for (var s : NML) {
                    if (Pattern.matches("[0-9]", s)) {
                        throw new StudentRecordBookWrongNameException(s);
                    }
                }
                if (NML.length != 3) {
                    throw new StudentRecordBookWrongNameException();
                }
                firstName = NML[0];
                lastName = NML[1];
                middleName = NML[2];
                isNMLCorrect = true;
            } catch (StudentRecordBookWrongNameException exc) {
                System.err.println("Слово +" + exc.getLocalizedMessage() + "+ набрано з помилками.");
            }
        } while (!isNMLCorrect);

        ArrayList<String> matched_credits = findStudentCredits(recordBooks, firstName, middleName, lastName);
        if (matched_credits != null) {
            for (var credit : matched_credits) {
                System.out.println(credit);
            }
        } else {
            System.out.println("Не знайдено студента з такими даними");
        }
    }

    private static ArrayList<String> findStudentCredits(StudentRecordBook[] recordBooks, String firstName, String middleName, String lastName) {
        for (var r : recordBooks) {
            if (r.FirstName.equals(firstName) && r.LastName.equals(lastName) && r.MiddleName.equals(middleName)) {
                return r.Credits;
            }
        }
        return null;
    }

    private static ArrayList<StudentRecordBook> findByAverageSessionScore(StudentRecordBook[] recordBooks, double higherThenInclusiveBound) {
        ArrayList<StudentRecordBook> rBooks = new ArrayList<>();
        for (var r : recordBooks) {
            if (r.AverageScorePerSession >= higherThenInclusiveBound) {
                rBooks.add(r);
            }
        }
        return rBooks;
    }

    private static Random random = new Random();
    private static String[] Exams = {"Discrete Math", "Linear Algebra", "Software Development Basics", "Algorithms and Data Structures"};
    private static String[] Credits = {"English", "Psychology", "Physical Culture", "Software requirements analysis"};
    private static int idNumBase = 1000000;

    private static int initCourse() {
        return random.nextInt(5) + 1;
    }

    private static int initIdNum() {
        idNumBase = +random.nextInt(10);
        return idNumBase;
    }

    private static double initAvgScore() {
        BigDecimal bigDecimal = BigDecimal.valueOf(random.nextDouble() * 5);
        return bigDecimal.setScale(1, RoundingMode.HALF_UP).doubleValue();
    }
}

