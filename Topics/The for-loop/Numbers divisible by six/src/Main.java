import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        List<Integer> numbers = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        int amount = scanner.nextInt();
        for (int i = 0; i < amount; i++) {
            numbers.add(scanner.nextInt());
        }
        int result = countCurrentSum(numbers);
        System.out.println(result);
    }

    private static int countCurrentSum(List<Integer> numbers) {
        int sum = 0;
        for (int number : numbers) {
            if (number % 6 == 0) {
                sum += number;
            }
        }
        return sum;
    }
}