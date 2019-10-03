package Lab1.TaskA;

import java.io.InputStreamReader;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(new InputStreamReader(System.in));

        System.out.println("Enter matrix dimensions:");
        int n = in.nextInt();
        int m = in.nextInt();

        int[][] matrix = new int[n][m];

        for (int i = 0; i < matrix.length; ++i) {
            matrix[i][0] = 1;
            for (int j = 1; j < matrix[i].length; ++j) {
                matrix[i][j] = 0;
            }
        }

        for (int[] row : matrix) {
            for (int el : row) {
                System.out.print(el + " ");
            }
            System.out.println();
        }
    }
}
