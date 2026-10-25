

import java.util.Random;
import java.util.Scanner;

public class CSMACA {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Random random = new Random();

        System.out.print("Enter number of devices: ");
        int n = sc.nextInt();

        for (int i = 1; i <= n; i++) {
            System.out.println("\nDevice " + i + " wants to transmit.");

            System.out.println("Sensing the channel...");

            if (random.nextInt(100) < 30) {
                System.out.println("Channel is busy. Waiting...");
            } else {
                System.out.println("Channel is free.");
            }

            int backoff = random.nextInt(8) + 1;
            System.out.println("Random backoff: "
                    + backoff + " time slots.");

            System.out.println("Waiting for backoff to finish...");
            System.out.println("Sending data...");

            if (random.nextInt(100) < 85) {
                System.out.println("Transmission successful!");
                System.out.println("ACK received.");
            } else {
                System.out.println("No ACK received.");
                System.out.println("Will retry transmission.");
            }
        }

        sc.close();
    }
}
