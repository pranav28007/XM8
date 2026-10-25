

import java.util.Scanner;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PublicKey;
import java.security.PrivateKey;
import javax.crypto.Cipher;
import java.util.Base64;

public class RSA {
    public static void main(String[] args) {
        try {
            Scanner sc = new Scanner(System.in);

            // Get input text from user
            System.out.print("Enter the text to encrypt: ");
            String text = sc.nextLine();

            // Generate RSA key pair
            KeyPairGenerator keyGen =
                    KeyPairGenerator.getInstance("RSA");
            keyGen.initialize(2048);

            KeyPair keyPair = keyGen.generateKeyPair();
            PublicKey publicKey = keyPair.getPublic();
            PrivateKey privateKey = keyPair.getPrivate();

            // Encrypt the input text
            Cipher cipher = Cipher.getInstance(
                    "RSA/ECB/OAEPWithSHA-256AndMGF1Padding");

            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            byte[] encryptedBytes =
                    cipher.doFinal(text.getBytes("UTF-8"));

            // Convert encrypted bytes to Base64
            String encryptedText =
                    Base64.getEncoder().encodeToString(encryptedBytes);

            System.out.println("\nEncrypted text: " + encryptedText);

            // Decrypt the encrypted text
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            byte[] decryptedBytes = cipher.doFinal(
                    Base64.getDecoder().decode(encryptedText));

            String decryptedText =
                    new String(decryptedBytes, "UTF-8");

            System.out.println("Decrypted text: " + decryptedText);

            sc.close();

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
