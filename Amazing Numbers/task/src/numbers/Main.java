package numbers;

import java.util.*;

public class Main {
    final static Set<String> properties = new HashSet<>();

    static {
        properties.add("BUZZ");
        properties.add("DUCK");
        properties.add("PALINDROMIC");
        properties.add("GAPFUL");
        properties.add("SPY");
        properties.add("SQUARE");
        properties.add("SUNNY");
        properties.add("EVEN");
        properties.add("ODD");
        properties.add("JUMPING");
        properties.add("HAPPY");
        properties.add("SAD");
        properties.add("-BUZZ");
        properties.add("-DUCK");
        properties.add("-PALINDROMIC");
        properties.add("-GAPFUL");
        properties.add("-SPY");
        properties.add("-SQUARE");
        properties.add("-SUNNY");
        properties.add("-EVEN");
        properties.add("-ODD");
        properties.add("-JUMPING");
        properties.add("-HAPPY");
        properties.add("-SAD");
    }


    public static void main(String[] args) {
        System.out.println("Welcome to Amazing Numbers!");
        System.out.println();
        printMenu();
        inputAnalysis();
    }

    private static void inputAnalysis() {
        NaturalNumber value = null;
        long mainNumber;
        long counter;
        do {
            System.out.print("Enter a request: ");
            String[] inputNumberInString = readInputLineFromUser();

            if (inputNumberInString.length == 1) {
                long inputNumber = 0;
                try {
                    inputNumber = Long.parseLong(inputNumberInString[0]);
                    value = checkIfNumberIsNatural(inputNumber);
                } catch (NumberFormatException e) {
                    value = NaturalNumber.NOT_NATURAL;
                }

                switch (value) {
                    case NULL_VALUE:
                        System.out.println();
                        System.out.println("Goodbye!");
                        break;
                    case NOT_NATURAL:
                        printErrorAboutFirstParameter();
                        break;
                    case NATURAL_NUMBER:
                        checkTheInputNumber(inputNumber);
                        break;
                    default:
                        printMenu();
                        break;
                }
            } else {
                mainNumber = Long.parseLong(inputNumberInString[0]);
                counter = Long.parseLong(inputNumberInString[1]);

                if (!isNatural(mainNumber)) {
                    printErrorAboutFirstParameter();
                } else if (!isNatural(counter)) {
                    printErrorAboutSecondParameter();
                } else {
                    if (inputNumberInString.length == 2) {
                        printNumbersAccordingToCounter(mainNumber, counter);
                    } else {
                        describeNumbersWithInputParameters(inputNumberInString);
                    }
                }
            }
        } while (value != NaturalNumber.NULL_VALUE);
    }

    private static void printErrorAboutSecondParameter() {
        System.out.println("The second parameter should be a natural number.");
    }

    private static void describeNumbersWithInputParameters(String[] input) {
        long numberToStartWith;
        long amountOfNumbers;
        List<String> properties = new ArrayList<>();

        try {
            numberToStartWith = Long.parseLong(input[0]);
            try {

                amountOfNumbers = Long.parseLong(input[1]);
                for (int i = 2; i < input.length; i++) {
                    properties.add(input[i].toUpperCase());
                }

                try {
                    if (properties.size() == 1) {
                        properties = correctNamesOfPropertiesWithMinus(properties);
                        List<Long> filteredNumbersByParameter = listByOneParameter(numberToStartWith, amountOfNumbers, properties.get(0));
                        printListNumbersWithProperties(filteredNumbersByParameter);
                    } else {
                        List<String> errorProperties = new ArrayList<>();
                        try {
                            errorProperties = checkErrorInputProperties(properties);
                        } catch (IllegalArgumentException a) {
                            printPropertyError(errorProperties);
                        }
                        properties = correctNamesOfPropertiesWithMinus(properties);

                        if (errorProperties.size() != 0) {
                            printPropertyError(errorProperties);
                        } else if (checkExclusiveProperties(properties).size() > 0) {
                            printExclusiveError(checkExclusiveProperties(properties));
                        } else {
                            List<Long> filteredNumbers = fillListOfNumbersByProperties(numberToStartWith, amountOfNumbers, properties);
                            printListNumbersWithProperties(filteredNumbers);
                        }
                    }
                }catch (IllegalArgumentException e) {
                    System.out.println("The property [" + properties.get(0) + "] is wrong.");
                    System.out.println("Available properties: [BUZZ, DUCK, PALINDROMIC, GAPFUL, SPY, SQUARE, SUNNY, JUMPING, EVEN, ODD, HAPPY, SAD]");
                }
            } catch (NumberFormatException e) {
                printErrorAboutSecondParameter();
            }

        } catch (NumberFormatException e) {
            printErrorAboutFirstParameter();
        }
    }

    private static List<String> correctNamesOfPropertiesWithMinus(List<String> properties) {
        List<String> newProperties = new ArrayList<>();
        for (String pro : properties) {
            if  (pro.contains("-")) {
                pro = pro.replace("-", "NOT");
            }
            newProperties.add(pro);
        }
        return newProperties;
    }

    private static List<Long> fillListOfNumbersByProperties(long numberToStartWith, long amountOfNumbers, List<String> properties) {
        List<Long> filtered = new ArrayList<>();
        List<Boolean> allPropertiesChecked;
        Properties property;
        //брать числа по порядку с первого
        //если соответсвует одному параметру, пропускать через второй и так далее
        //если подошло всем параметрам - записать в лист
        long counter = 0;
        long numberToCheck = numberToStartWith;

        while (counter != amountOfNumbers) {
            allPropertiesChecked = new ArrayList<>();
            for (String s : properties) {
                property = Properties.valueOf(s);
                if (property.isMagicNumber(numberToCheck)) {
                    allPropertiesChecked.add(true);
                } else {
                    allPropertiesChecked.add(false);
                }
            }
            if (!allPropertiesChecked.contains(false)) {
                filtered.add(numberToCheck);
                counter++;
            }
            numberToCheck++;
        }
        return filtered;
    }

    private static List<String> checkErrorInputProperties(List<String> propertiesFromUser) {
        List<String> errors = new ArrayList<>();

        for (String a : propertiesFromUser) {
            if (!properties.contains(a)) {
                errors.add(a);
            }
        }

        return errors;
    }

    private static List<String> checkExclusiveProperties(List<String> properties) {
        List<String> exclusivePair = new ArrayList<>();

        if (properties.contains("EVEN") && properties.contains("ODD")) {
            exclusivePair.add("EVEN");
            exclusivePair.add("ODD");
        } else if ( properties.contains("NOTEVEN") && properties.contains("NOTODD")) {
            exclusivePair.add("-EVEN");
            exclusivePair.add("-ODD");
        } else if (properties.contains("DUCK") && properties.contains("SPY")) {
            exclusivePair.add("DUCK");
            exclusivePair.add("SPY");
        } else if (properties.contains("NOTDUCK") && properties.contains("NOTSPY")) {
            exclusivePair.add("-DUCK");
            exclusivePair.add("-SPY");
        } else if (properties.contains("SUNNY") && properties.contains("SQUARE")) {
            exclusivePair.add("SUNNY");
            exclusivePair.add("SQUARE");
        } else if (properties.contains("NOTSUNNY") && properties.contains("NOTSQUARE")) {
            exclusivePair.add("-SUNNY");
            exclusivePair.add("-SQUARE");
        } else if (properties.contains("HAPPY") && properties.contains("SAD")) {
            exclusivePair.add("HAPPY");
            exclusivePair.add("SAD");
        } else if (properties.contains("NOTHAPPY") && properties.contains("NOTSAD")) {
            exclusivePair.add("-HAPPY");
            exclusivePair.add("-SAD");
        } else if (!notTheOppositeProperties(properties, exclusivePair)) {
            return exclusivePair;
        }
        return exclusivePair;
    }

    private static boolean notTheOppositeProperties(List<String> properties, List<String> exclusivePair) {
        for (int i = 0; i < properties.size(); i++) {
            for (int u = 0; u < properties.size(); u++) {
                if ((u + 1) < properties.size()) {
                    if (properties.get(i).equals("NOT" + properties.get(u + 1))) {
                        exclusivePair.add(properties.get(i));
                        exclusivePair.add("-" + properties.get(u + 1));
                    } else if (("NOT" + properties.get(i)).equals(properties.get(u + 1))) {
                        exclusivePair.add("-" + properties.get(i));
                        exclusivePair.add(properties.get(u + 1));
                        return false;
                    }
                }
            }
        }
        return true;
    }

    private static void printPropertyError(List<String> properties) {
        properties = convertNotProperties(properties);
        if (properties.size() == 1) {
            System.out.println("The property [" + properties.get(0) + "] is wrong");
        } else {
            System.out.print("The properties ");
            for (int i = 0; i < properties.size(); i++) {
                System.out.print("[" + properties.get(i) + "]");
                if (i != properties.size() - 1) {
                    System.out.print(" and ");
                }
            }
            System.out.println(" are wrong");
        }
        System.out.println("Available properties: ");
        System.out.println("[BUZZ, DUCK, PALINDROMIC, GAPFUL, SPY, SQUARE, SUNNY, JUMPING, EVEN, ODD, HAPPY, SAD]");
    }

    private static List<String> convertNotProperties(List<String> properties) {
        List<String> newProperties = new ArrayList<>();
        for (String pro : properties) {
            if  (pro.contains("NOT")) {
                pro = pro.replace("NOT", "-");
            }
            newProperties.add(pro);
        }
        return newProperties;
    }

    private static void printExclusiveError(List<String> properties) {
        System.out.println("The request contains mutually exclusive properties: [" + properties.get(0) + ", "
                + properties.get(1) + "]");
        System.out.println("There are no numbers with these properties.");
    }

    private static List<Long> listByOneParameter(long mainNumber, long counter, String inputParameter) {
        List<Long> list = new ArrayList<>();
        Properties candidate;
        int addedToList = 0;
        candidate = Properties.valueOf(inputParameter);

        long currentValueToCheck = mainNumber;
        while (addedToList < counter) {
            if (candidate.isMagicNumber(currentValueToCheck)) {
                list.add(currentValueToCheck);
                addedToList++;
            }
            currentValueToCheck++;
        }
        return list;
    }

    private static void printListNumbersWithProperties(List<Long> sortedNumbersByParameter) {
        for (Long g : sortedNumbersByParameter) {
            getNumberDescription(g);
        }
    }

    private static void printNumbersAccordingToCounter(long mainNumber, long counter) {
        System.out.println();
        for (int i = 0; i < counter; i++) {
            mainNumber = getNumberDescription(mainNumber);
        }
        System.out.println();
    }

    private static long getNumberDescription(long mainNumber) {
        System.out.print("             " + mainNumber + " is ");
        if (isBuzz(mainNumber)) {
            System.out.print("buzz, ");
        }
        if (isDuck(mainNumber)) {
            System.out.print("duck, ");
        }
        if (isPalindromic(mainNumber)) {
            System.out.print("palindromic, ");
        }
        if (isGapful(mainNumber)) {
            System.out.print("gapful, ");
        }
        if (isSpy(mainNumber)) {
            System.out.print("spy, ");
        }
        if (isSquare(mainNumber)) {
            System.out.print("square, ");
        }
        if (isSunny(mainNumber)) {
            System.out.print("sunny, ");
        }
        if (isJumping(mainNumber)) {
            System.out.print("jumping, ");
        }
        if (isHappy(mainNumber)){
            System.out.print("happy, ");
        }
        if (!isHappy(mainNumber)){
            System.out.print("sad, ");
        }
        if (isEven(mainNumber)) {
            System.out.print("even");
        }
        if (isOdd(mainNumber)){
            System.out.print("odd");
        }

        mainNumber++;
        System.out.println();
        return mainNumber;
    }

    private static String[] readInputLineFromUser() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine().split(" ");
    }

    private static void printErrorAboutFirstParameter() {
        System.out.println();
        System.out.println("The first parameter should be a natural number or zero.");
        System.out.println();
    }

    private static NaturalNumber checkIfNumberIsNatural(long inputNumber) {
        if (inputNumber < 0) {
            return NaturalNumber.NOT_NATURAL;
        } else if (inputNumber == 0) {
            return NaturalNumber.NULL_VALUE;
        }
        return NaturalNumber.NATURAL_NUMBER;
    }

    private static void printMenu() {
        System.out.println("Supported requests:");
        System.out.println("- enter a natural number to know its properties;");
        System.out.println("- enter two natural numbers to obtain the properties of the list:");
        System.out.println("  * the first parameter represents a starting number;");
        System.out.println("  * the second parameter shows how many consecutive numbers are to be processed;");
        System.out.println("- two natural numbers and properties to search for;");
        System.out.println("- a property preceded by minus must not be present in numbers;");
        System.out.println("- separate the parameters with one space;");
        System.out.println("- enter 0 to exit.");
        System.out.println();
    }

    private static void checkTheInputNumber(long inputNumber) {
        try {
            if (isNatural(inputNumber)) {
                System.out.println();
                System.out.println("Properties of " + inputNumber + ":");
                System.out.println("        buzz: " + isBuzz(inputNumber));
                System.out.println("        duck: " + isDuck(inputNumber));
                System.out.println(" palindromic: " + isPalindromic(inputNumber));
                System.out.println("      gapful: " + isGapful(inputNumber));
                System.out.println("         spy: " + isSpy(inputNumber));
                System.out.println("      square: " + isSquare(inputNumber));
                System.out.println("       sunny: " + isSunny(inputNumber));
                System.out.println("     jumping: " + isJumping(inputNumber));
                System.out.println("        even: " + isEven(inputNumber));
                System.out.println("         odd: " + isOdd(inputNumber));
                System.out.println("       happy: " + isHappy(inputNumber));
                System.out.println("         sad: " + !isHappy(inputNumber));
                System.out.println();
            } else {
                System.out.println("This number is not natural!");
            }
        } catch (NumberFormatException e) {
            System.out.println("This is not a number!");
        }
    }

    private static boolean isHappy(long inputNumber) {
        if (inputNumber != 1) {
            while (true) {
                if (inputNumber == 1) {
                    return true;
                }
                inputNumber = sumDigitSquare(inputNumber);
                if (inputNumber == 4) {
                    return false;
                }
            }
        } else {
            return true;
        }
    }

    private static long sumDigitSquare(long inputNumber) {
        long square = 0;
        while (inputNumber != 0) {
            long digit = inputNumber % 10;
            square += digit * digit;
            inputNumber = inputNumber / 10;
        }
        return square;
    }

    private static Boolean isJumping(long inputNumber) {
        String[] numberByDigits = String.valueOf(inputNumber).split("");
        if (numberByDigits.length == 1) {
            return true;
        } else {
            for (int i = 0; i < numberByDigits.length - 1; i++) {
                int difference = Math.abs(Integer.parseInt(numberByDigits[i]) - Integer.parseInt(numberByDigits[i + 1]));
                if (difference != 1) {
                    return false;
                }
            }
        }
        return true;
    }

    private static Boolean isSunny(long inputNumber) {
        long nextNumber = inputNumber + 1;
        return isSquare(nextNumber);
    }

    private static Boolean isSquare(long inputNumber) {
        long toCompare = (long) Math.sqrt(inputNumber);
        return toCompare * toCompare == inputNumber;
    }

    private static Boolean isSpy(long inputNumber) {
        String[] digits = String.valueOf(inputNumber).split("");
        long product = countProduct(digits);
        long sum = countSum(digits);

        return product == sum;
    }

    private static long countSum(String[] digits) {
        long product = 0;
        for (String i : digits) {
            product += Long.parseLong(i);
        }
        return product;
    }

    private static long countProduct(String[] digits) {
        long product = 1;
        for (String i : digits) {
            product *= Long.parseLong(i);
        }
        return product;
    }

    private static Boolean isGapful(long inputNumber) {
        String[] numberByDigits = String.valueOf(inputNumber).split("");
        boolean isGapful = false;
        if (numberByDigits.length >= 3) {
            long concatenation;
            String builder = numberByDigits[0] +
                    numberByDigits[numberByDigits.length - 1];
            concatenation = Long.parseLong(builder);

            if (inputNumber % concatenation == 0) {
                isGapful = true;
            }
        }
        return isGapful;
    }

    private static Boolean isPalindromic(long inputNumber) {
        String[] line = String.valueOf(inputNumber).split("");
        List<Boolean> allPairsComparison = new ArrayList<>();
        boolean isPalindrome = true;

        for (int i = 0; i < line.length / 2; i++) {
            if (line[i].equals(line[line.length - 1 - i])) {
                allPairsComparison.add(true);
            } else {
                allPairsComparison.add(false);
            }
        }

        for (Boolean b : allPairsComparison) {
            if (!b) {
                isPalindrome = false;
                break;
            }
        }
        return isPalindrome;
    }

    private static boolean isDuck(long inputNumber) {
        String numberInString = String.valueOf(inputNumber);
        String[] numberLength = numberInString.split("");
        boolean hasNull = false;
        long wholeNumber = inputNumber;

        if (numberLength.length > 1) {
            for (int i = 0; i < numberLength.length - 1; i++) {
                if (wholeNumber % 10 == 0) {
                    hasNull = true;
                }
                wholeNumber /= 10;
            }
        }
        return hasNull;
    }

    private static boolean isOdd(long inputNumber) {
        return inputNumber % 2 != 0;
    }

    private static boolean isEven(long inputNumber) {
        return inputNumber % 2 == 0;
    }

    private static boolean isBuzz(long inputNumber) {
        boolean seven = isSeven(inputNumber);
        boolean endsSeven = isEndsWithSeven(inputNumber);
        boolean dividedBySeven = isDividedBySeven(inputNumber);

        return seven || endsSeven || dividedBySeven;
    }

    private static boolean isDividedBySeven(long inputNumber) {
        return inputNumber % 7 == 0;
    }

    private static boolean isEndsWithSeven(long inputNumber) {
        if (inputNumber > 9) {
            return inputNumber % 10 == 7;
        }
        return false;
    }

    private static boolean isSeven(long inputNumber) {
        return inputNumber == 7;
    }

    private static boolean isNatural(long inputNumber) {
        return inputNumber > 0;
    }

    public enum NaturalNumber {
        NULL_VALUE, NOT_NATURAL, NATURAL_NUMBER
    }

    public enum Properties /*implements Checkable*/ {
        BUZZ {
            public boolean isMagicNumber(long value) { return isBuzz(value); }
        },
        DUCK {
            public boolean isMagicNumber(long value) { return isDuck(value); }
        },
        PALINDROMIC {
            public boolean isMagicNumber(long value) { return isPalindromic(value); }
        },
        GAPFUL {
            public boolean isMagicNumber(long value) { return isGapful(value); }
        },
        SPY {
            public boolean isMagicNumber(long value) { return isSpy(value); }
        },
        SQUARE {
            public boolean isMagicNumber(long value) { return isSquare(value); }
        },
        SUNNY {
            public boolean isMagicNumber(long value) { return isSunny(value); }
        },
        JUMPING {
            public boolean isMagicNumber(long value) { return isJumping(value); }
        },
        EVEN {
            public boolean isMagicNumber(long value) { return isEven(value); }
        },
        ODD {
            public boolean isMagicNumber(long value) { return isOdd(value); }
        },
        HAPPY {
            public boolean isMagicNumber(long value) { return isHappy(value); }
        },
        SAD {
            public boolean isMagicNumber(long value) { return !isHappy(value); }
        },
        NOTBUZZ {
            public boolean isMagicNumber(long value) { return !isBuzz(value); }
        },
        NOTDUCK {
            public boolean isMagicNumber(long value) { return !isDuck(value); }
        },
        NOTPALINDROMIC {
            public boolean isMagicNumber(long value) { return !isPalindromic(value); }
        },
        NOTGAPFUL {
            public boolean isMagicNumber(long value) { return !isGapful(value); }
        },
        NOTSPY {
            public boolean isMagicNumber(long value) { return !isSpy(value); }
        },
        NOTSQUARE {
            public boolean isMagicNumber(long value) { return !isSquare(value); }
        },
        NOTSUNNY {
            public boolean isMagicNumber(long value) { return !isSunny(value); }
        },
        NOTJUMPING {
            public boolean isMagicNumber(long value) { return !isJumping(value); }
        },
        NOTEVEN {
            public boolean isMagicNumber(long value) { return !isEven(value); }
        },
        NOTODD {
            public boolean isMagicNumber(long value) { return !isOdd(value); }
        },
        NOTHAPPY {
            public boolean isMagicNumber(long value) { return !isHappy(value); }
        },
        NOTSAD {
            public boolean isMagicNumber(long value) { return isHappy(value); }
        };

        public boolean isMagicNumber(long value) {
            return false;
        }

    }


}
