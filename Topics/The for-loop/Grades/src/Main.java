import java.util.Scanner;
class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        //считываем первое число n и создаем массив длинной значения этого числа
        int gradesListSize = scanner.nextInt();
        int[] gradesList = new int[gradesListSize];
        //вводим переменные А В С D
        int gradeA = 0;
        int gradeB = 0;
        int gradeC = 0;
        int gradeD = 0;

        //в цикле заносим все сделующие числа (в количестве n штук) в массив
        for (int i = 0; i < gradesListSize; i++) {
            int inputGrade = scanner.nextInt();
            gradesList[i] = inputGrade;
            //пользуясь циклом , сразу проверяем вводимые значения
            switch (inputGrade) {
                case 5 :
                    gradeA++;
                    break;
                case 4 :
                    gradeB++;
                    break;
                case 3 :
                    gradeC++;
                    break;
                case 2 :
                    gradeD++;
                    break;
                default :
                    break;
            }
        }
        //выводим получившиеся значения на экран
        System.out.println(gradeD + " " + gradeC + " " + gradeB + " " + gradeA);
    }
}