

import java.net.*;
import java.io.*;
import java.util.Scanner;

public class Sender {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);

        Socket socket = new Socket("localhost", 5000);

        BufferedReader in = new BufferedReader(
                new InputStreamReader(socket.getInputStream()));

        PrintWriter out = new PrintWriter(
                socket.getOutputStream(), true);

        System.out.print("Enter number of frames: ");
        int n = sc.nextInt();

        System.out.println("1. Stop-and-Wait");
        System.out.println("2. Sliding Window");
        System.out.print("Enter choice: ");
        int choice = sc.nextInt();

        if (n <= 0) {
            System.out.println("Number of frames must be positive.");
            out.println("STOP");
            socket.close();
            sc.close();
            return;
        }

        if (choice == 1) {
            System.out.println("\nStop-and-Wait Protocol");

            for (int i = 1; i <= n; i++) {
                out.println("Frame" + i);
                System.out.println("Sent: Frame" + i);

                String ack = in.readLine();
                System.out.println("Received: " + ack);
            }

        } else if (choice == 2) {
            System.out.print("Enter window size: ");
            int window = sc.nextInt();

            if (window <= 0) {
                System.out.println("Invalid window size.");
                out.println("STOP");
                socket.close();
                sc.close();
                return;
            }

            System.out.println("\nSliding Window Protocol");

            for (int i = 1; i <= n; i += window) {
                int end = Math.min(i + window - 1, n);

                // Send all frames in the current window
                for (int j = i; j <= end; j++) {
                    out.println("Frame" + j);
                    System.out.println("Sent: Frame" + j);
                }

                // Receive acknowledgments
                for (int j = i; j <= end; j++) {
                    String ack = in.readLine();
                    System.out.println("Received: " + ack);
                }
            }

        } else {
            System.out.println("Invalid choice.");
        }

        out.println("STOP");

        socket.close();
        sc.close();
    }
}
