

import java.util.Scanner;

public class EllipticCurve {

    static int p, a;

    // Point addition
    static int[] add(int[] P, int[] Q) {
        if (P == null) return Q;
        if (Q == null) return P;

        int x1 = P[0], y1 = P[1];
        int x2 = Q[0], y2 = Q[1];

        if (x1 == x2 && (y1 + y2) % p == 0)
            return null;

        int num, den;

        if (x1 == x2 && y1 == y2) {
            num = 3 * x1 * x1 + a;
            den = 2 * y1;
        } else {
            num = y2 - y1;
            den = x2 - x1;
        }

        num = ((num % p) + p) % p;
        den = ((den % p) + p) % p;

        int inv = -1;
        for (int i = 1; i < p; i++) {
            if ((den * i) % p == 1) {
                inv = i;
                break;
            }
        }

        if (inv == -1)
            throw new ArithmeticException("Modular inverse does not exist");

        int lambda = (num * inv) % p;
        int x3 = ((lambda * lambda - x1 - x2) % p + p) % p;
        int y3 = ((lambda * (x1 - x3) - y1) % p + p) % p;

        return new int[]{x3, y3};
    }

    // Point subtraction: P - Q = P + (-Q)
    static int[] subtract(int[] P, int[] Q) {
        if (Q == null) return P;
        int[] negQ = {Q[0], (p - Q[1]) % p};
        return add(P, negQ);
    }

    static void display(String msg, int[] R) {
        if (R == null)
            System.out.println(msg + "Point at infinity");
        else
            System.out.println(msg + "(" + R[0] + ", " + R[1] + ")");
    }

    static boolean validPoint(int[] P, int b) {
        if (P[0] < 0 || P[0] >= p || P[1] < 0 || P[1] >= p)
            return false;

        long left = ((long) P[1] * P[1]) % p;
        long right = (((long) P[0] * P[0] % p * P[0]) % p
                     + (long) a * P[0] + b) % p;

        right = (right + p) % p;
        return left == right;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter prime number p: ");
        p = sc.nextInt();

        System.out.print("Enter curve parameter a: ");
        a = sc.nextInt();

        System.out.print("Enter curve parameter b: ");
        int b = sc.nextInt();

        System.out.print("Enter x-coordinate of P: ");
        int x1 = sc.nextInt();
        System.out.print("Enter y-coordinate of P: ");
        int y1 = sc.nextInt();

        System.out.print("Enter x-coordinate of Q: ");
        int x2 = sc.nextInt();
        System.out.print("Enter y-coordinate of Q: ");
        int y2 = sc.nextInt();

        int[] P = {x1, y1};
        int[] Q = {x2, y2};

        if (p <= 2 || (((4L * a * a % p) * a + 27L * b * b) % p) == 0) {
            System.out.println("Invalid elliptic curve parameters.");
        } else if (!validPoint(P, b) || !validPoint(Q, b)) {
            System.out.println("Invalid points for the given curve.");
        } else {
            display("P + Q = ", add(P, Q));
            display("P - Q = ", subtract(P, Q));
        }

        sc.close();
    }
}
