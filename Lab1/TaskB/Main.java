package Lab1.TaskB;

import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(new InputStreamReader(System.in));

        System.out.println("Enter a string:");
        String input = in.nextLine();

        String[] fragments = new String[input.length() / 3];
        for (int i = 0; i < fragments.length; ++i) {
            fragments[i] = randomMiddleCharacter(input.substring(i * 3, i * 3 + 3));
        }

        Arrays.sort(fragments);
        for (String fragment : fragments) {
            System.out.println(fragment);
        }
    }

    static String randomMiddleCharacter(String str) {
        Random random = new Random();
        char randomChar = (char) random.nextInt(125);
        while (randomChar == str.charAt(0) || randomChar == str.charAt(2)) {
            randomChar = (char) random.nextInt(125);
        }

        return "" + str.charAt(0) + randomChar + str.charAt(2);
    }
}


