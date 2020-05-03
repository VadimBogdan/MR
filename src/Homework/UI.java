package Homework;

import java.io.IOException;
import java.nio.file.FileSystemException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class UI {
    private Scanner scanner = new Scanner(System.in);
    private final Object lock = new Object();

    public void Start() throws IOException, InterruptedException {
        boolean isWorking = true;
        System.out.println("Введіть шлях до файлу, його назву та розширення для початку роботи з ним.");
        String filePath;
        do {
            filePath = scanner.next();
            try {
                if (!filePath.matches(".*[/\n\r\t\0\f`?*<>|\":].*")) {
                    throw new FileSystemException(filePath);
                }
            } catch (FileSystemException exc) {
                System.err.println("Шлях " +filePath+ " введений з помилками.");
            }
        } while (!filePath.matches(".*[/\n\r\t\0\f`?*<>|\":].*"));
        ThreadedObjectSerializer<Subscriber> objectSerializer = new ThreadedObjectSerializer<>(filePath);
        while (isWorking) {
            System.out.println("Доступні дії:");
            System.out.println("1. Додати об'єкт.");
            System.out.println("2. Показати всі елементи.");
            System.out.println("3. Завершити роботу з програмою.");
            switch (scanner.nextInt()) {
                case 1:
                    Subscriber subscriber = initSubscriber();
                    objectSerializer.writeSubscriber(subscriber);
                    break;
                case 2:
                    objectSerializer.show(this::retrieveSubscribers);
                    synchronized(lock) {
                        lock.wait();
                    }
                    break;
                case 3:
                    isWorking = false;
                    break;
            }
        }
    }

    private Subscriber initSubscriber() {
        scanner.nextLine();
        String firstName = "";
        String middleName = "";
        String lastName = "";
        boolean isNMLCorrect = false;
        do {
            System.out.println("Введіть прізвище, ім'я і по-батькові абонента.");
            try {
                String[] NML = (scanner.nextLine()).split("\\s");
                for (var s : NML) {
                    if (Pattern.matches("^[0-9]$", s)) {
                        throw new RuntimeException(s);
                    }
                }
                if (NML.length != 3) {
                    throw new Exception();
                }
                firstName = NML[0];
                lastName = NML[1];
                middleName = NML[2];
                isNMLCorrect = true;
            } catch (RuntimeException exc) {
                System.err.println("Слово " + exc.getMessage() + " набрано з помилками.");
            } catch (Exception exc) {
                System.err.println("Не коректно сформоване ПІБ.");
            }
        } while (!isNMLCorrect);

        String address = null;

        do {
            System.out.println("Виберіть адресу абонента.");
            for (int j = 0; j < Addresses.length; j++) {
                System.out.println(j + ". " + Addresses[j]);
            }
            try {
                String input = scanner.next();
                if (!Pattern.matches("^[0-9]$", input)) {
                    throw new IllegalArgumentException(input);
                }
                int index = Integer.parseInt(input);
                if (Addresses.length <= index) {
                    throw new Exception(index + "");
                }
                address = Addresses[index];
            } catch (RuntimeException exc) {
                System.err.println("Символ " + exc.getMessage() + " не є числом зі списку.");
            } catch (Exception exc) {
                System.err.println("Номера " + exc.getMessage() + " не існує.");
            }
        } while (address == null);

        Integer intercitySpeakTime = null;

        do {
            System.out.println("Виберіть час міських переговорів");
            for (int j = 0; j < IntercitySpeakTimes.length; j++) {
                System.out.println(j + ". " + IntercitySpeakTimes[j]);
            }
            try {
                String input = scanner.next();
                if (!Pattern.matches("^[0-9]$", input)) {
                    throw new RuntimeException(input);
                }
                int index = Integer.parseInt(input);
                if (IntercitySpeakTimes.length <= index) {
                    throw new Exception(index + "");
                }
                intercitySpeakTime = IntercitySpeakTimes[index];
            } catch (RuntimeException exc) {
                System.err.println("Символ " + exc.getLocalizedMessage() + " не є числом зі списку.");
            } catch (Exception exc) {
                System.err.println("Номера +" + exc.getLocalizedMessage() + "+ не існує.");
            }
        } while (intercitySpeakTime == null);

        Integer urbanSpeakTime = null;

        do {
            System.out.println("Виберіть час міжміських переговорів");
            for (int j = 0; j < UrbanSpeakTimes.length; j++) {
                System.out.println(j + ". " + UrbanSpeakTimes[j]);
            }
            try {
                String input = scanner.next();
                if (!Pattern.matches("^[0-9]$", input)) {
                    throw new RuntimeException(input);
                }
                int index = Integer.parseInt(input);
                if (UrbanSpeakTimes.length <= index) {
                    throw new Exception(index + "");
                }
                urbanSpeakTime = UrbanSpeakTimes[index];
            } catch (RuntimeException exc) {
                System.err.println("Символ " + exc.getLocalizedMessage() + " не є числом зі списку.");
            } catch (Exception exc) {
                System.err.println("Номера " + exc.getLocalizedMessage() + " не існує.");
            }
        } while (urbanSpeakTime == null);

        return new Subscriber(firstName, middleName, lastName, address, intercitySpeakTime, urbanSpeakTime);
    }

    private static String[] Addresses = {"Kyiv", "Lviv", "Odessa"};
    private static Integer[] IntercitySpeakTimes = {100, 125, 150};
    private static Integer[] UrbanSpeakTimes = {30, 45, 60};

    private void retrieveSubscribers(List<Subscriber> subscribers) {
        if (subscribers != null && !subscribers.isEmpty()) {
            for (var s:subscribers) {
                System.out.println(s);
            }
            System.out.println();
        }
        synchronized (lock) {
            lock.notify();
        }
    }
}
