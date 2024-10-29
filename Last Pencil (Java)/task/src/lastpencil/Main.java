package lastpencil;

import java.util.Random;
import java.util.Scanner;

public class Main {
    public static Scanner scanner = new Scanner(System.in);
    public static Random random = new Random();


    public static void main(String[] args) {
        int num = 0;
        boolean error = true;
        System.out.println("How many pencils would you like to use:");
        while (true) {
            try {
                num = Integer.parseInt(scanner.nextLine());
                if (num < 0) {
                    System.out.println("The number of pencils should be numeric");
                    continue;
                }
            } catch (NumberFormatException NFE) {
                System.out.println("The number of pencils should be numeric");
                continue;
            }
            if (num != 0) {
                break;
            } else {
                System.out.println("The number of pencils should be positive");
            }

        }
        System.out.println("Who will be the first (John, Jack):");
        while (error) {
            String name = scanner.nextLine();
            switch (name) {
                case "John", "Jack" -> {
                    johnFirst(name, num);
                    error = false;
                }
                default -> {
                    System.out.println("Choose between Jack and John");
                }

            }
        }
    }


    private static void johnFirst(String name, int num) {
        int turnCounter = 0, inputNumber = 0;
        boolean error;
        String input = "";

        System.out.println("|".repeat(num));
        System.out.println(name + "'s turn!");

        while (true) {
            error = false;
            if (name.equals("Jack")) {
                int i = getBotTurn(num);
                input = Integer.toString(i);
                System.out.println(input);
            } else if (name.equals("John")) {
                input = scanner.nextLine();
            }

            if (!input.equals("1") && !input.equals("2") && !input.equals("3")) {
                System.out.println("Possible values: '1', '2' or '3'");
                error = true;
                continue;
            }

            try {
                inputNumber = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Possible values: '1', '2' or '3'");
                error = true;
                continue;
            }

            if (num < inputNumber) {
                System.out.println("Too many pencils were taken");
                error = true;
                continue;
            }

            if (!error) {
                num -= inputNumber;
                if (num == 0) {
                    // Declare the winner (the other player)
                    String winner = name.equals("Jack") ? "John" : "Jack";
                    System.out.println(winner + " won!");
                    break;
                }
                System.out.println("|".repeat(num));
                // Alternate players
                name = name.equals("Jack") ? "John" : "Jack";
                System.out.println(name + "'s turn!");
            }
        }
    }

    public static int getBotTurn(int num) {
        int upper = 3;
        int lower = 1;
        int result = 0;

        if(num == 1) {
            result = 1;
        } else if (num % 4 == 1) {
            result = random.nextInt((upper - lower) + 1) + lower;
        } else if (num % 4 == 2) {
            result = 1;
        } else if (num % 4 == 3) {
            result = 2;
        } else if (num % 4 == 0) {
            result = 3;
        }
        return result;
    }

}

