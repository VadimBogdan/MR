package Lab1;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void showShortMatrix(short[][] matrix, int dim) {
        for (int i = 0; i < dim; i++) {
            for (int j = 0; j < dim; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
        System.out.println();
    }

    public static void main(String[] args) {
        String creator = "Bohdan V. V.";
        Random random = new Random();
        int dimension = 5,
        col = 1;

        Scanner scanner = new Scanner(System.in);
        System.out.println(creator);

        try {
            dimension = scanner.nextInt();
            col = scanner.nextInt();
        } catch (InputMismatchException e) {
            System.err.println("\n Using default dimension = 5");
        }


        short[][] matrix = new short[dimension][dimension];

        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                matrix[i][j] = ((short) random.nextInt(32768));
            }
        }

        showShortMatrix(matrix, dimension);

        short temp;
        for (int i = 0, k = col; i < dimension; i++) {
            for (int j = 0; j < dimension - 1 - i; j++) {
                if (matrix[j][k] > matrix[j + 1][k]) {
                    for (int l = 0; l < dimension; l++) {
                        temp = matrix[j][l];
                        matrix[j][l] = matrix[j + 1][l];
                        matrix[j + 1][l] = temp;
                    }
                }
            }
        }
        showShortMatrix(matrix, dimension);
    }
}
