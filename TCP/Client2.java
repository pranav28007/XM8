

import java.io.*;
import java.net.*;

class Client2 {
    public static void main(String args[]) throws Exception {
        Socket cs = new Socket("localhost", 6789);
        System.out.println("Client Started...");

        BufferedReader in = new BufferedReader(
            new InputStreamReader(System.in));
        BufferedReader inp = new BufferedReader(
            new InputStreamReader(cs.getInputStream()));
        PrintWriter out = new PrintWriter(
            cs.getOutputStream(), true);

        String line;

        while (true) {
            System.out.print("Client: ");
            line = in.readLine();

            out.println(line);

            if (line.equalsIgnoreCase("q")) {
                break;
            }

            String reply = inp.readLine();
            if (reply == null) break;

            System.out.println("Received from server: " + reply);

            if (reply.equalsIgnoreCase("q")) {
                break;
            }
        }

        cs.close();
        System.out.println("Client disconnected.");
    }
}
