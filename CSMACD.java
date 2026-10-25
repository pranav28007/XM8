

import java.util.Random;
import java.util.Scanner;

public class CSMACD {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Random random = new Random();

        System.out.print("Enter number of devices: ");
        int n = sc.nextInt();

        System.out.print("Enter maximum transmission attempts: ");
        int maxAttempts = sc.nextInt();

        for (int i = 1; i <= n; i++) {
            System.out.println("\nDevice " + i + " wants to transmit.");

            boolean success = false;

            for (int attempt = 1; attempt <= maxAttempts; attempt++) {
                System.out.println("Attempt " + attempt
                        + ": Checking channel...");

                if (random.nextInt(100) < 30) {
                    System.out.println("Channel is busy.");
                    continue;
                }

                System.out.println("Channel is free. Transmitting...");

                // Simulate collision detection
                if (random.nextInt(100) < 30) {
                    System.out.println("Collision detected!");
                    int backoff = random.nextInt(
                            Math.min(1 << Math.min(attempt, 10), 1024));
                    System.out.println("Waiting for " + backoff
                            + " time slots before retrying.");
                } else {
                    System.out.println("Transmission successful!");
                    success = true;
                    break;
                }
            }

            if (!success) {
                System.out.println(
                        "Transmission failed after maximum attempts.");
            }
        }

        sc.close();
    }
}
