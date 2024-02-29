import java.util.*;

public class App {
    private static Scanner scanner;

    public static void main(String[] args) throws Exception {
        // Using Scanner for getting input
        scanner = new Scanner(System.in);

        // Main loop for operation
        while (true) {
            System.out.println("Task: (Options: 1, 2, 3)");
            String choiceOfTask = scanner.nextLine();

            // Should the user input 'q' it will end the program
            if (choiceOfTask.toLowerCase().equals("q")) {
                System.out.println("You closed the app.");
                break;
            } else if (choiceOfTask.equals("1")) {
                task1Function();
            } else if (choiceOfTask.equals("2")) {
                task2Function();
            } else if (choiceOfTask.equals("3")) {
                task3Function();
            } else
                System.out.println("Text you provided is not one of options! Try again.");
        }

        // Closing scanner
        scanner.close();
    }

    private static void task3Function() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'task3Function'");
    }

    private static void task2Function() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'task2Function'");
    }

    private static void task1Function() {
        System.out.println("Input: ");
        String input = scanner.nextLine();
        String[] numbers = input.split(" ");

        // TreeSet is perfect for holding unique values and having them sorted during
        // insertion
        TreeSet<Integer> uniqueNumbers = new TreeSet<>();

        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        int count = 0;

        for (String number : numbers) {
            int num = Integer.parseInt(number);

            // Update count
            count++;

            // Update min and max
            if (num < min) {
                min = num;
            }
            if (num > max) {
                max = num;
            }

            uniqueNumbers.add(num);
        }

        // Output:
        System.out.println(uniqueNumbers.toString().replaceAll("\\[|\\]|,", ""));
        System.out.println("count: " + count);
        System.out.println("distinct: " + uniqueNumbers.size());
        System.out.println("min: " + min);
        System.out.println("max: " + max);
    }

}
