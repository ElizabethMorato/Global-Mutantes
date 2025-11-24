package org.example.service;
/// Lógica de negocio (Algoritmo core)

import org.springframework.stereotype.Service;

@Service
public class MutantDetector {

    private static final int SEQUENCE_LENGTH = 4;

    public boolean isMutant(String[] dna) {
        if (dna == null || dna.length < SEQUENCE_LENGTH) {
            return false;
        }

        int n = dna.length;
        char[][] matrix = new char[n][n];
        for (int i = 0; i < n; i++) {
            matrix[i] = dna[i].toCharArray();
        }

        int sequencesFound = 0;

        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {

                if (col <= n - SEQUENCE_LENGTH &&
                        hasHorizontalSequence(matrix, row, col)) {
                    sequencesFound++;
                }

                if (row <= n - SEQUENCE_LENGTH &&
                        hasVerticalSequence(matrix, row, col)) {
                    sequencesFound++;
                }

                if (row <= n - SEQUENCE_LENGTH && col <= n - SEQUENCE_LENGTH &&
                        hasDiagonalDownSequence(matrix, row, col)) {
                    sequencesFound++;
                }

                if (row >= SEQUENCE_LENGTH - 1 && col <= n - SEQUENCE_LENGTH &&
                        hasDiagonalUpSequence(matrix, row, col)) {
                    sequencesFound++;
                }

                if (sequencesFound > 1) {
                    return true; // early exit
                }
            }
        }
        return false;
    }

    private boolean hasHorizontalSequence(char[][] m, int row, int col) {
        char b = m[row][col];
        return m[row][col + 1] == b &&
                m[row][col + 2] == b &&
                m[row][col + 3] == b;
    }

    private boolean hasVerticalSequence(char[][] m, int row, int col) {
        char b = m[row][col];
        return m[row + 1][col] == b &&
                m[row + 2][col] == b &&
                m[row + 3][col] == b;
    }

    private boolean hasDiagonalDownSequence(char[][] m, int row, int col) {
        char b = m[row][col];
        return m[row + 1][col + 1] == b &&
                m[row + 2][col + 2] == b &&
                m[row + 3][col + 3] == b;
    }

    private boolean hasDiagonalUpSequence(char[][] m, int row, int col) {
        char b = m[row][col];
        return m[row - 1][col + 1] == b &&
                m[row - 2][col + 2] == b &&
                m[row - 3][col + 3] == b;
    }
}