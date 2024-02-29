import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class App {
    private static int goal = 13;

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
        System.out.println("Input: ");
        int number = Integer.parseInt(scanner.nextLine());

        List<String> items = new ArrayList<>();

        for (int n = 0; n < number; n++) {
            String[] pair = scanner.nextLine().split(" ");

            int found = 0;
            for (int i = 0; i < items.size(); i++) {
                String graph = items.get(i);
                String[] nodes = graph.split(" ");

                if (Arrays.asList(nodes).contains(pair[0])) {
                    items.set(i, graph + " " + pair[1]);
                    found++;
                }
                if (Arrays.asList(nodes).contains(pair[1])) {
                    items.set(i, graph + " " + pair[0]);
                    found++;
                }

            }
            // If no found items, add this connection to the list
            if (found == 0)
                items.add(pair[0] + " " + pair[1]);
            // If one connection found, it has already been added
            // If more connections found, merge those connections into one item
            else if (found > 1) {
                for (int i = 0; i < items.size(); i++) {
                    String[] str1 = items.get(i).split(" ");
                    for (int j = i + 1; j < items.size(); j++) {
                        String[] str2 = items.get(j).split(" ");

                        // Check if items i and j are connected
                        boolean isConnection = Arrays.stream(str1)
                                .mapToInt(Integer::parseInt)
                                .anyMatch(num1 -> Arrays.stream(str2)
                                        .mapToInt(Integer::parseInt)
                                        .anyMatch(num2 -> num1 == num2));
                        if (!isConnection)
                            continue;

                        // If so, concat strings, order numbers ascending, eliminate repeats
                        String finalStr = items.get(i) + " " + items.get(j);
                        String result = Arrays.stream(finalStr.split(" "))
                                .mapToInt(Integer::parseInt)
                                .sorted()
                                .distinct()
                                .mapToObj(Integer::toString)
                                .collect(Collectors.joining(" "));
                        // Remove items i and j, add a new one being a merge of them
                        items.remove(j);
                        items.remove(i);
                        items.add(result);
                        // Reset the loop
                        i = 0;
                        j = i + 1;
                    }
                }
            }

        }

        System.out.println(items.size());
    }

    private static void task2Function() {
        System.out.println("Input: ");
        String input = scanner.nextLine();
        String[] numbers = input.split(" ");

        // PriorityQueue has build-in sorting but allows duplicates
        PriorityQueue<String> pairs = new PriorityQueue<String>();
        Set<Integer> seen = new HashSet<>();

        for (String number : numbers) {
            int num = Integer.parseInt(number);
            int complement = goal - num;
            // Check if already processed values contain a complement to the difference of
            // goal and current value
            if (seen.contains(complement)) {
                // If so, you have found your pair
                pairs.add(Math.min(num, complement) + ", " + Math.max(num, complement));
            }
            seen.add(num);
        }

        // Output:
        for (String pair : pairs) {
            System.out.println(pair);
        }
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
