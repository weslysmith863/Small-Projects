import java.text.NumberFormat;
import java.util.Scanner;

public class MortgageCalculator {
    final static byte PERCENT = 100;
    final static byte MONTHS_IN_YEAR = 12;

    public static void main(String[] args) {
        int principal = (int)readNumber("Principal: ", 1000, 1_000_000);
        float annualInterest = (float)readNumber("Annual Interest Rate: ", 1, 30);
        byte years = (byte)readNumber("Period (Years): ", 1, 30);

        double mortgage = calculateMortgage(principal, annualInterest, years);
        NumberFormat currency = NumberFormat.getCurrencyInstance();
        String mortgageFormatted = currency.format(mortgage);
        System.out.println();
        System.out.println("MORTGAGE");
        System.out.println("--------");
        System.out.print("Monthly Payment: "+ mortgageFormatted);

        System.out.println();
        System.out.println("PAYMENT SCHEDULE");
        System.out.println("----------------");
        for (short month = 1; month <= years * MONTHS_IN_YEAR; month++) {
            double balance = calculateBalance(principal, annualInterest, years, month);
            String formattedBalance = currency.format(balance);
            System.out.println(formattedBalance);
        }
    }

    public static double readNumber(String prompt, double min, double max) {
        Scanner scanner = new Scanner(System.in);
        double value;
        while (true) {
            System.out.print(prompt);
            value = scanner.nextFloat();
            if (value >= min && value <= max)
                break;
            System.out.println("Enter a value between " + min + "and " + max);
        }
        return value;

    }

    public static double calculateBalance(
            int principal,
            double annualInterest,
            byte years,
            short numberOfPaymentsMade
    ) {
        double monthlyInterest = (annualInterest / PERCENT / MONTHS_IN_YEAR);
        short numberOfPayments = (short)(years * MONTHS_IN_YEAR);

        double balance = principal
                * (Math.pow(1 + monthlyInterest, numberOfPayments) - Math.pow(1 + monthlyInterest, numberOfPaymentsMade))
                / (Math.pow(1 + monthlyInterest, numberOfPayments) - 1);

                return balance;
    }

    public static double calculateMortgage(
            int principal,
            double annualInterest,
            byte years) {

        double monthlyInterest = (annualInterest / PERCENT / MONTHS_IN_YEAR);
        short numberOfPayments = (short)(years * MONTHS_IN_YEAR);

        double numerator = monthlyInterest * Math.pow((1 + monthlyInterest), (numberOfPayments));
        double denominator = Math.pow((1+monthlyInterest), (numberOfPayments)) - 1;
        double mortgage  = principal * numerator / denominator;

        return mortgage;
    }

}