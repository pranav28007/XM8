

import java.util.Scanner;

public class ECAddition {
    static int p = 17, a = 2;

    // Point addition
    static int[] add(int x1, int y1, int x2, int y2) {
        if (x1 == x2 && (y1 + y2) % p == 0)
            return null; // Point at infinity

        int num, den;

        if (x1 == x2 && y1 == y2) {
            num = (3 * x1 * x1 + a) % p;
            den = (2 * y1) % p;
        } else {
            num = (y2 - y1 + p) % p;
            den = (x2 - x1 + p) % p;
        }

        int m = -1;
        for (int i = 1; i < p; i++) {
            if ((den * i) % p == 1) {
                m = (num * i) % p;
                break;
            }
        }

        if (m == -1)
            return null;

        int x3 = ((m * m - x1 - x2) % p + p) % p;
        int y3 = ((m * (x1 - x3) - y1) % p + p) % p;

        return new int[]{x3, y3};
    }

    // Point subtraction: P - Q = P + (-Q)
    static int[] subtract(int x1, int y1, int x2, int y2) {
        int negY2 = (p - y2) % p;
        return add(x1, y1, x2, negY2);
    }

    static void display(String msg, int[] R) {
        if (R == null)
            System.out.println(msg + "Point at infinity");
        else
            System.out.println(msg + "(" + R[0] + ", " + R[1] + ")");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter P (x1 y1): ");
        int x1 = sc.nextInt();
        int y1 = sc.nextInt();

        System.out.print("Enter Q (x2 y2): ");
        int x2 = sc.nextInt();
        int y2 = sc.nextInt();

        display("P + Q = ", add(x1, y1, x2, y2));
        display("P - Q = ", subtract(x1, y1, x2, y2));

        sc.close();
    }
}
