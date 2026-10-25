

import java.util.Scanner;

public class CRC {

    static String xor(String a, String b) {
        StringBuilder result = new StringBuilder();

        for (int i = 1; i < b.length(); i++) {
            result.append(a.charAt(i) == b.charAt(i) ? '0' : '1');
        }

        return result.toString();
    }

    static String mod2Division(String data, String divisor) {
        int len = divisor.length();
        String temp = data.substring(0, len);

        int pointer = len;

        while (pointer < data.length()) {
            if (temp.charAt(0) == '1') {
                temp = xor(divisor, temp)
                        + data.charAt(pointer);
            } else {
                temp = xor("0".repeat(len), temp)
                        + data.charAt(pointer);
            }

            pointer++;
        }

        if (temp.charAt(0) == '1') {
            temp = xor(divisor, temp);
        } else {
            temp = xor("0".repeat(len), temp);
        }

        return temp;
    }

    static String toBinary(String text) {
        StringBuilder binary = new StringBuilder();

        for (char c : text.toCharArray()) {
            binary.append(String.format("%8s",
                    Integer.toBinaryString(c))
                    .replace(' ', '0'));
        }

        return binary.toString();
    }

    static void calculateCRC(String text, String polynomial,
                             String name) {
        String data = toBinary(text);
        int degree = polynomial.length() - 1;

        String appendedData = data + "0".repeat(degree);
        String remainder = mod2Division(appendedData, polynomial);
        String codeword = data + remainder;

        System.out.println("\n" + name);
        System.out.println("Data bits       : " + data);
        System.out.println("CRC remainder   : " + remainder);
        System.out.println("Transmitted data: " + codeword);

        String check = mod2Division(codeword, polynomial);

        if (check.chars().allMatch(c -> c == '0')) {
            System.out.println("CRC Check       : No error detected");
        } else {
            System.out.println("CRC Check       : Error detected");
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter character data: ");
        String text = sc.nextLine();

        if (text.isEmpty()) {
            System.out.println("Input cannot be empty.");
            sc.close();
            return;
        }

        calculateCRC(text,
                "1100000001111", "CRC-12");

        calculateCRC(text,
                "11000000000000101", "CRC-16");

        calculateCRC(text,
                "10001000000100001", "CRC-CCITT");

        sc.close();
    }
}
