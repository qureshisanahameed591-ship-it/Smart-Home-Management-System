package smarthome.utils;
import java.util.Scanner;
public class Console implements AutoCloseable {
    private final Scanner sc = new Scanner(System.in);
    public String line(String prompt) {
        System.out.print(prompt);
        return sc.nextLine().trim();
    }
    public int integer(String prompt, int min, int max) {
        while (true) {
            System.out.print(prompt);
            String s = sc.nextLine().trim();
            try {
                int v = Integer.parseInt(s);
                if (v < min || v > max) {
                    System.out.println("Value must be " + min + "-" + max);
                    continue;
                }
                return v;
            } catch (NumberFormatException e) {
                System.out.println("Invalid number, try again.");
            }
        }
    }
    public double decimal(String prompt) {
        while (true) {
            System.out.print(prompt);
            String s = sc.nextLine().trim();
            try { return Double.parseDouble(s); }
            catch (NumberFormatException e) { System.out.println("Invalid number."); }
        }
    }
    @Override public void close() { sc.close(); }
}