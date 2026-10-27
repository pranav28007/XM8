

import java.util.Scanner;

public class DiffieHellman {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Publicly known values
        System.out.print("Enter prime number (p): ");
        int p = sc.nextInt();

        System.out.print("Enter primitive root (g): ");
        int g = sc.nextInt();

        // Private keys of Alice and Bob
        System.out.print("Enter Alice's private key: ");
        int a = sc.nextInt();

        System.out.print("Enter Bob's private key: ");
        int b = sc.nextInt();

        // Calculate public keys
        int A = (int) (Math.pow(g, a) % p);
        int B = (int) (Math.pow(g, b) % p);

        // Calculate shared secret keys
        int keyAlice = (int) (Math.pow(B, a) % p);
        int keyBob = (int) (Math.pow(A, b) % p);

        // Display results
        System.out.println("\nAlice's public key: " + A);
        System.out.println("Bob's public key: " + B);

        System.out.println("Alice's shared secret key: "
                           + keyAlice);
        System.out.println("Bob's shared secret key: "
                           + keyBob);

        if (keyAlice == keyBob) {
            System.out.println("Key exchange successful!");
        } else {
            System.out.println("Key exchange failed!");
        }

        sc.close();
    }
}
