package Lab2_3;


import java.util.Scanner;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {
       FolderProcessor folderProcessor = new FolderProcessor(Executors.newFixedThreadPool(10));
       Scanner scanner = new Scanner(System.in);
       System.out.println("Введіть назву директорії, які необхідно обробити.");
       String folderPath = scanner.next();
       folderProcessor.processFolder(folderPath);
        for (var f:folderProcessor.processedFiles) {
            System.out.println(f);
        }
    }
}
