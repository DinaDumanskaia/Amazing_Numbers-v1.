import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] numbers = scanner.nextLine().split("\\s");
        int a = Integer.parseInt(numbers[0]);
        int b = Integer.parseInt(numbers[1]);
        int n = Integer.parseInt(numbers[2]);

        System.out.println(countDivisibleByN(a, b, n));
    }

    private static int countDivisibleByN(int a, int b, int n) {
        int counter = 0;
        for (int i = a; i <= b; i++) {
            if (i % n == 0) {
                counter++;
            }
        }
        return counter;
    }
}