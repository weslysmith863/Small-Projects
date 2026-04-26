import java.text.NumberFormat;
import java.util.Scanner;

public class MortgageCalculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        NumberFormat currency = NumberFormat.getCurrencyInstance();

        System.out.print("Principal: ");
        int principal = scanner.nextInt();
        System.out.print("Annual Interest Rate: ");
        double rate = ((scanner.nextDouble() / 100) / 12);
        System.out.print("Period (Years): ");
        int period = scanner.nextInt();

        double numerator = rate * Math.pow((1 + rate), (period * 12));
        double denominator = Math.pow((1+rate), (period * 12)) - 1;
        double payment  = principal * numerator / denominator;
        String result = currency.format(payment);
        System.out.print(result);

    }
}