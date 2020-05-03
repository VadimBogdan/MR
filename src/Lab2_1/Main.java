package Lab2_1;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>();
        System.out.println("Вводіть цілі числа");
        System.out.println("Для індикації завершення введення, введіть не число.");

        String val;
        Scanner scanner = new Scanner(System.in);
        try {
        while (!((val = scanner.next()).isBlank())) {
           list.add(Integer.parseInt(val));
        }
        } catch (NumberFormatException ignored) { }
        for (var v:
             list) {
            System.out.println(v);
        }
        int value;
        System.out.println("Введіть число з колекції завдяки якому зробити сортування.");
        try {
            value = scanner.nextInt();
            if (list.contains(value)) {
                list.sort(Integer::compareTo);
                for (var v:
                        list) {
                    System.out.println(v);
                }
            } else {
                System.out.println("Числа "+value+" не існує в колекції.");
            }
        } catch (NumberFormatException ex) {
            System.err.println("Введене значення не було числом.");
        }

    }
}
