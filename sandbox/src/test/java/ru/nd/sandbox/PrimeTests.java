package ru.nd.sandbox;

import org.testng.annotations.Test;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class PrimeTests {
    @Test
    public void testPrimes() {
        assertTrue(Primes.isPrime(Integer.MAX_VALUE));
    }

    @Test(enabled = false)
    public void testPrimesLong() {
        long n = Integer.MAX_VALUE;
        assertTrue(Primes.isPrime(n));
    }

    @Test
    public void testNonPrimes() {
        assertFalse(Primes.isPrime(Integer.MAX_VALUE - 2));
    }
}
