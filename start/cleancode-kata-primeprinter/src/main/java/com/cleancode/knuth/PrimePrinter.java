package com.cleancode.knuth;

public class PrimePrinter {
    private static int numberOfNumbers;

    public static void main(String[] args) {
        final int M = 1000;
        final int RR = 50;
        final int CC = 4;
        final int ORDMAX = 30;

        int[] numbers = generatePrimes(M, ORDMAX);
        NumberPrinter printer = new NumberPrinter(RR, CC);
        printer.printNumbers(numbers, numberOfNumbers);
    }

    public static int[] generatePrimes(int M, int ORDMAX) {
        int[] primes = new int[M + 1];
        int[] MULT = new int[ORDMAX + 1];
        int J = 1;
        int K = 1;
        primes[1] = 2;
        int ORD = 2;
        int SQUARE = 9;
        int JPRIME;
        int N;

        while (K < M) {
            do {
                J += 2;
                if (J == SQUARE) {
                    ORD++;
                    SQUARE = primes[ORD] * primes[ORD];
                    MULT[ORD - 1] = J;
                }
                N = 2;
                JPRIME = 1;
                while (N < ORD && JPRIME == 1) {
                    while (MULT[N] < J)
                        MULT[N] += primes[N] + primes[N];
                    if (MULT[N] == J)
                        JPRIME = 0;
                    N++;
                }
            } while (JPRIME == 0);
            K++;
            primes[K] = J;
        }
        numberOfNumbers = K;
        return primes;
    }
}

class NumberPrinter {
    private final int linesPerPage;
    private final int columns;

    public NumberPrinter(int linesPerPage, int columns) {
        this.linesPerPage = linesPerPage;
        this.columns = columns;
    }

    public void printNumbers(int[] numbers, int numberOfNumbers) {
        int pageNumber = 1;
        int pageOffset = 1;

        while (pageOffset <= numberOfNumbers) {
            System.out.print("The First ");
            System.out.print(numberOfNumbers);
            System.out.print(" Numbers === Page ");
            System.out.print(pageNumber);
            System.out.println("\n");
            for (int rowOffset = pageOffset; rowOffset <= pageOffset + linesPerPage - 1; rowOffset++) {
                for (int column = 0; column < columns; column++)
                    if (rowOffset + column * linesPerPage <= numberOfNumbers)
                        System.out.printf("%10d", numbers[rowOffset + column * linesPerPage]);
                System.out.println();
            }
            System.out.println("\f");
            pageNumber++;
            pageOffset += linesPerPage * columns;
        }
    }
}
