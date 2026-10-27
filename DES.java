

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Scanner;
import java.util.Base64;

public class DES {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the message: ");
        String message = sc.nextLine();

        System.out.print("Enter 8-character secret key: ");
        String key = sc.nextLine();

        if (key.length() != 8) {
            System.out.println("Key must be exactly 8 characters!");
            sc.close();
            return;
        }

        // Create DES key
        SecretKeySpec secretKey =
            new SecretKeySpec(key.getBytes("UTF-8"), "DES");

        // Encryption
        Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);

        byte[] encrypted = cipher.doFinal(message.getBytes("UTF-8"));
        String encryptedText = Base64.getEncoder()
                                     .encodeToString(encrypted);

        System.out.println("Encrypted text: " + encryptedText);

        // Decryption
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] decrypted = cipher.doFinal(
            Base64.getDecoder().decode(encryptedText));

        System.out.println("Decrypted text: "
                           + new String(decrypted, "UTF-8"));

        sc.close();
    }
}
