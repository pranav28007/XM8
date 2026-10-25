

import java.util.Scanner;

public class DataLinkFraming {

    // Character Stuffing
    static void characterStuffing(String data) {
        char FLAG = 'F';
        char ESC = 'E';

        StringBuilder stuffed = new StringBuilder();
        stuffed.append(FLAG);

        for (int i = 0; i < data.length(); i++) {
            char ch = data.charAt(i);

            if (ch == FLAG || ch == ESC) {
                stuffed.append(ESC);
            }

            stuffed.append(ch);
        }

        stuffed.append(FLAG);

        System.out.println("After Character Stuffing: " + stuffed);
    }

    // Bit Stuffing
    static void bitStuffing(String data) {
        StringBuilder stuffed = new StringBuilder();
        int count = 0;

        for (int i = 0; i < data.length(); i++) {
            char bit = data.charAt(i);

            stuffed.append(bit);

            if (bit == '1') {
                count++;

                if (count == 5) {
                    stuffed.append('0');
                    count = 0;
                }
            } else {
                count = 0;
            }
        }

        System.out.println("After Bit Stuffing: 01111110"
                + stuffed + "01111110");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("DATA LINK LAYER FRAMING");
        System.out.println("1. Character Stuffing");
        System.out.println("2. Bit Stuffing");
        System.out.print("Enter your choice: ");

        int choice = sc.nextInt();
        sc.nextLine();

        if (choice == 1) {
            System.out.print("Enter the data: ");
            String data = sc.nextLine();
            characterStuffing(data);

        } else if (choice == 2) {
            System.out.print("Enter binary data: ");
            String data = sc.nextLine();

            if (!data.matches("[01]*")) {
                System.out.println(
                        "Invalid input! Enter only 0s and 1s.");
            } else {
                bitStuffing(data);
            }

        } else {
            System.out.println("Invalid choice!");
        }

        sc.close();
    }
}
