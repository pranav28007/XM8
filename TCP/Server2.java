

import java.io.*;
import java.net.*;

class Server2 {
    public static void main(String args[]) throws Exception {
        ServerSocket ss = new ServerSocket(6789);
        System.out.println("Server waiting for client...");

        Socket s = ss.accept();
        System.out.println("Client connected!");

        BufferedReader inp = new BufferedReader(
            new InputStreamReader(s.getInputStream()));
        PrintWriter out = new PrintWriter(
            s.getOutputStream(), true);
        BufferedReader in = new BufferedReader(
            new InputStreamReader(System.in));

        String line, reply;

        while (true) {
            line = inp.readLine();

            if (line == null || line.equalsIgnoreCase("q")) {
                System.out.println("Client disconnected.");
                break;
            }

            System.out.println("Received from client: " + line);

            System.out.print("Server: ");
            reply = in.readLine();

            out.println(reply);

            if (reply.equalsIgnoreCase("q")) {
                break;
            }
        }

        s.close();
        ss.close();
        System.out.println("Server stopped.");
    }
}
