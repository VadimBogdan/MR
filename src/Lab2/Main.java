package Lab2;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Use default string? (y/n)");
        boolean _usedef = true;
        try {
            _usedef = sc.nextLine().equalsIgnoreCase("y");
        } catch (InputMismatchException e) {
            System.err.println(e.getMessage());
        }

        String input = "";

        if (_usedef) {
            input = "Text messaging, or texting, is the\n" +
                    "act of composing and sending electronic messages,\n" +
                    "typically consisting of alphabetic a\n" +
                    "nd numeric characters, between two or\n" +
                    " more users of mobile devices,\n" +
                    "desktops/laptops, or other type of\n" +
                    "compatible computer.\n" +
                    "Text messages may be sent over a cellular network,\n" +
                    " or may also be sent via an Internet connection.\n";
        } else {
            try {
                input = sc.nextLine();
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }

        System.out.println("Enter {trail substring} {what to add}");

        String answer = "";
        try {
            answer = sc.nextLine();
        } catch (InputMismatchException e) {
            System.err.println(e.getMessage());
        }
        String[] splittedAnswer = answer.split("\\s");
        String output = input.replaceAll("(" + splittedAnswer[0] + "(?![/\\\\])\\b)", "$1 " + splittedAnswer[1]);

        System.out.println(output);
    }
}
