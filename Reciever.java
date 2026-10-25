

import java.net.*;
import java.io.*;

public class Receiver {
    public static void main(String[] args) throws Exception {
        ServerSocket server = new ServerSocket(5000);

        System.out.println("Receiver is waiting for sender...");

        Socket socket = server.accept();
        System.out.println("Sender connected.");

        BufferedReader in = new BufferedReader(
                new InputStreamReader(socket.getInputStream()));

        PrintWriter out = new PrintWriter(
                socket.getOutputStream(), true);

        String frame;

        while ((frame = in.readLine()) != null) {
            if (frame.equals("STOP")) {
                System.out.println("Transmission completed.");
                break;
            }

            System.out.println("Received: " + frame);

            out.println("ACK " + frame);
            System.out.println("Acknowledgment sent for " + frame);
        }

        socket.close();
        server.close();
    }
}
