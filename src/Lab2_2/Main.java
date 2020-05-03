package Lab2_2;


import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        PrintWriter outputStream;
        BufferedReader inputStream;
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введіть назву створюваного файлу.");
        String fileName = scanner.next();
        outputStream = new PrintWriter(new FileWriter(fileName));
        System.out.println("Вводіть числа, які необхідно записати у файл.");
        String val;
        try {
            while (!((val = scanner.next()).isEmpty())) {
                outputStream.print(Integer.parseInt(val) + " ");
            }
        } catch (NumberFormatException ignored) { }
        outputStream.close();
        System.out.println("Числа були введені.");
        inputStream = new BufferedReader(new FileReader(fileName));
        String output;
        ArrayList<Integer> values = new ArrayList<>();
        System.out.println("Зчитування даних з файлу.");
        while ((output = inputStream.readLine()) != null) {
            for (var n:output.split(" ")) {
                values.add(Integer.parseInt(n));
            }
        }
        inputStream.close();
        System.out.println("Сортування...");
        values.sort(Integer::compareTo);

        outputStream = new PrintWriter(new FileWriter(fileName));
        for (var n:values) {
            System.out.println(n);
            outputStream.print(n + " ");
        }
        outputStream.close();
    }
}
