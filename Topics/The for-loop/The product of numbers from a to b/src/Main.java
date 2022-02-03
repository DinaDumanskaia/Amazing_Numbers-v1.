import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] line = scanner.nextLine().split(" ");
        int theFirst = Integer.parseInt(line[0]);
        int theSecond = Integer.parseInt(line[1]);

        System.out.println(multiply(theFirst, theSecond));
    }

    private static long multiply(int theFirst, int theSecond) {
        long result = theFirst;
        if (theSecond - theFirst > 1) {
            for (int i = theFirst + 1; i < theSecond; i++) {
                result *= i;
            }
        }
        return result;
    }
}