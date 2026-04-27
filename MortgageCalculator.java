import java.text.NumberFormat;
import java.util.Scanner;

public class MortgageCalculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        NumberFormat currency = NumberFormat.getCurrencyInstance();

        int principal = 0;
        double rate = 0;
        int numberOfPayments = 0;
        int years = 0;

        while (true) {
            System.out.print("Principal: ");
            principal = scanner.nextInt();
            if (principal >= 1000 && principal <= 1_000_000) {
                break;
            }
            System.out.println("Enter a value between 1000 and 1000000");
        }

        final byte PERCENT = 100;
        final byte MONTHS_IN_YEAR = 12;

        while (true) {
            System.out.print("Annual Interest Rate: ");
            rate = scanner.nextFloat();
            if (rate >= 1 && rate <= 30) {
                rate = (rate / PERCENT / MONTHS_IN_YEAR);
                break;
            }
            System.out.println("Enter a value between 1 and 30");
        }

        while (true) {
            System.out.print("Period (Years): ");
            years = scanner.nextInt();
            if (years >= 1 && years <= 30) {
                numberOfPayments = years * MONTHS_IN_YEAR;
                break;
            }
            System.out.println("Enter a value between 1 and 30");
        }

        double numerator = rate * Math.pow((1 + rate), (numberOfPayments));
        double denominator = Math.pow((1+rate), (numberOfPayments)) - 1;
        double payment  = principal * numerator / denominator;

        String result = currency.format(payment);
        System.out.print("Monthly Payment: "+ result);

    }
}