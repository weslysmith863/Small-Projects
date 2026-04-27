import java.text.NumberFormat;
import java.util.Scanner;

public class MortgageCalculator {
    public static void main(String[] args) {
        int principal = (int)readNumber("Principal: ", 1000, 1_000_000);
        float annualInterest = (float)readNumber("Annual Interest Rate: ", 1, 30);
        byte years = (byte)readNumber("Period (Years): ", 1, 30);

        double mortgage = calculateMortgage(principal, annualInterest, years);

        NumberFormat currency = NumberFormat.getCurrencyInstance();
        String mortgageFormatted = currency.format(mortgage);
        System.out.print("Monthly Payment: "+ mortgageFormatted);
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

    public static double calculateMortgage(
            int principal,
            double annualInterest,
            byte years) {

        final byte PERCENT = 100;
        final byte MONTHS_IN_YEAR = 12;

        double monthlyInterest = (annualInterest / PERCENT / MONTHS_IN_YEAR);
        short numberOfPayments = (short)(years * MONTHS_IN_YEAR);

        double numerator = monthlyInterest * Math.pow((1 + monthlyInterest), (numberOfPayments));
        double denominator = Math.pow((1+monthlyInterest), (numberOfPayments)) - 1;
        double mortgage  = principal * numerator / denominator;

        return mortgage;
    }

}