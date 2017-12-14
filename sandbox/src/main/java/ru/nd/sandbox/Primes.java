package ru.nd.sandbox;

public class Primes {
    public static boolean isPrime(int n) {
        int m = (int)Math.sqrt(n);
        for (int i = 2; i < m; i++) {
            if (n % i == 0) {
                return false;
            }
        }

        return true;
    }

    public static boolean isPrime(long n) {
        for (int i = 2; i < n / 2; i++) {
            if (n % i == 0) {
                return false;
            }
        }

        return true;
    }

    public static boolean isPrimeWhile(int n) {
        int i = 2;
        while (i < n / 2 && n % i == 0) {
            i++;
        }

        return i == n;
    }
}
