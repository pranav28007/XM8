

import java.security.MessageDigest;
import java.util.Scanner;

public class SHA512 {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter a string: ");
        String input = sc.nextLine();

        // Create SHA-512 MessageDigest instance
        MessageDigest md = MessageDigest.getInstance("SHA-512");

        // Generate hash
        byte[] hash = md.digest(input.getBytes("UTF-8"));

        // Convert hash bytes to hexadecimal
        StringBuilder hex = new StringBuilder();
        for (byte b : hash) {
            hex.append(String.format("%02x", b & 0xff));
        }

        System.out.println("SHA-512 Hash: " + hex);

        sc.close();
    }
}
