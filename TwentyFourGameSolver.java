import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class TwentyFourGameSolver {

    private static final double TARGET_NUMBER = 24;
    private static final double EPSILON = 1e-6;
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] input = new int[4];

        System.out.println("Enter four numbers:");

        for (int i = 0; i < 4; i++) {
            while (true) {
                try {
                    System.out.print("Number " + (i + 1) + ": ");
                    input[i] = scanner.nextInt();
                    break;
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input. Please enter a valid number.");
                    scanner.nextLine(); // Clear the input buffer
                }
            }
        }

        List<String> steps = solveTwentyFourGame(input);
        if (steps != null) {
        	System.out.println("Solution exists for the input " + input[0] + ", " + input[1] + ", " + input[2] + ", " + input[3] + ":");
            for (String step : steps) {
                System.out.println(step);
            }
        } else {
            System.out.println("No solution exists.");
        }
    }

   

    public static List<String> solveTwentyFourGame(int[] input) {
        List<String> steps = new ArrayList<>();
        List<Double> numbers = new ArrayList<>();
        for (int i : input) {
            numbers.add((double) i);
        }
        if (solve(numbers, steps)) {
            return steps;
        }
        return null;
    }

    private static boolean solve(List<Double> numbers, List<String> steps) {
        if (numbers.size() == 1) {
            double number = numbers.get(0);
            if (Math.abs(number - TARGET_NUMBER) < EPSILON) {
                return true;
            }
            return false;
        }
        int size = numbers.size();
        for (int i = 0; i < size; i++) {
            for (int j = i + 1; j < size; j++) {
                double num1 = numbers.get(i);
                double num2 = numbers.get(j);

                List<Double> remainingNumbers = new ArrayList<>();
                for (int k = 0; k < size; k++) {
                    if (k != i && k != j) {
                        remainingNumbers.add(numbers.get(k));
                    }
                }

                // Addition
                steps.add(num1 + " + " + num2 + " = " + (num1 + num2));
                remainingNumbers.add(num1 + num2);
                if (solve(remainingNumbers, steps)) {
                    return true;
                }
                steps.remove(steps.size() - 1);
                remainingNumbers.remove(remainingNumbers.size() - 1);

                // Subtraction
                steps.add(num1 + " - " + num2 + " = " + (num1 - num2));
                remainingNumbers.add(num1 - num2);
                if (solve(remainingNumbers, steps)) {
                    return true;
                }
                steps.remove(steps.size() - 1);
                remainingNumbers.remove(remainingNumbers.size() - 1);

                steps.add(num2 + " - " + num1 + " = " + (num2 - num1));
                remainingNumbers.add(num2 - num1);
                if (solve(remainingNumbers, steps)) {
                    return true;
                }
                steps.remove(steps.size() - 1);
                remainingNumbers.remove(remainingNumbers.size() - 1);

                // Multiplication
                steps.add(num1 + " * " + num2 + " = " + (num1 * num2));
                remainingNumbers.add(num1 * num2);
                if (solve(remainingNumbers, steps)) {
                    return true;
                }
                steps.remove(steps.size() - 1);
                remainingNumbers.remove(remainingNumbers.size() - 1);

                // Division
                if (Math.abs(num2) > EPSILON) {
                    steps.add(num1 + " / " + num2 + " = " + (num1 / num2));
                    remainingNumbers.add(num1 / num2);
                    if (solve(remainingNumbers, steps)) {
                        return true;
                    }
                    steps.remove(steps.size() - 1);
                    remainingNumbers.remove(remainingNumbers.size() - 1);
                }

                if (Math.abs(num1) > EPSILON) {
                    steps.add(num2 + " / " + num1 + " = " + (num2 / num1));
                    remainingNumbers.add(num2 / num1);
                    if (solve(remainingNumbers, steps)) {
                        return true;
                    }
                    steps.remove(steps.size() - 1);
                    remainingNumbers.remove(remainingNumbers.size() - 1);
                }
            }
        }
        return false;
    }
}
