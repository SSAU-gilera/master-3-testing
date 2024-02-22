package org.example.Calculator;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Choose the number system:");
            String[] bases = {"binary", "octal", "decimal", "hexadecimal"};
            int baseChoice = getUserChoice(scanner, bases);
            String base = bases[baseChoice - 1];

            System.out.println("Choose the operation:");
            String[] operations = {"add", "subtract", "multiply", "divide"};
            int operationChoice = getUserChoice(scanner, operations);
            String operation = operations[operationChoice - 1];

            System.out.println("Enter the first number:");
            scanner.nextLine();
            String num1 = scanner.next();

            System.out.println("Enter the second number:");
            scanner.nextLine();
            String num2 = scanner.next();

            CalculatorFactory calculatorFactory = new CalculatorFactory();
            Calculator calculator = calculatorFactory.createCalculator(base, operation);

            String result = calculator.calculate(operation, base, num1, num2);

            System.out.println("Result: " + result);

            System.out.println("Do you want to perform another calculation? (yes/no)");
            scanner.nextLine();
            String choice = scanner.nextLine().toLowerCase();
            if (!choice.equals("yes")) {
                break;
            }
        }
    }

    private static int getUserChoice(Scanner scanner, String[] options) {
        for (int i = 0; i < options.length; i++) {
            System.out.println((i + 1) + ") " + options[i]);
        }
        return scanner.nextInt();
    }
}
