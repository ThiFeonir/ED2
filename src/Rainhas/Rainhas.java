package Rainhas;

import java.util.Random;

/**
 * Created by thial on 29/09/2017.
 */
public class Rainhas {
    static int tabuleiro[][] = new int[8][8];
    public static void main(String[] args) {
        fazerJogadas();
        exibirMatriz();
    }

    public static void fazerJogadas() {
        int linhaX;
        Random random = new Random();
        for (int i = 0; i < 8; i++) {
            linhaX = random.nextInt(8);
            if (testarMovimento(linhaX, i)) {
                System.out.println("inseriu na coluna " + i);
                tabuleiro[linhaX][i] = i + 1;
            } else {
                System.out.println("Backtracking da coluna " + i + " para " + (i - 1) + "...");
                limparConflitos(i - 1);
                i = i - 2;
            }
        }
    }

    public static boolean testarMovimento(int linha, int coluna) {
        int linhaaux = linha;
        int colunaaux = coluna;

        for (int i = 0; i < 8; i++)
            if ((tabuleiro[linha][i] != 0) && (tabuleiro[linha][i] != tabuleiro[linha][coluna])) return false;

        for (int i = 0; i < 8; i++)
            if ((tabuleiro[i][coluna] != 0) && (tabuleiro[i][coluna] != tabuleiro[linha][coluna])) return false;

        while (colunaaux != 0 && linhaaux != 7) {
            colunaaux--;
            linhaaux++;
        }

        while (linhaaux != 0 && colunaaux != 7) {
            if (tabuleiro[linhaaux][colunaaux] != 0)
                return false;
            colunaaux++;
            linhaaux--;
        }

        linhaaux = linha;
        colunaaux = coluna;

        while (colunaaux != 7 && linhaaux != 7) {
            colunaaux++;
            linhaaux++;
        }

        while (linhaaux != -1 && colunaaux != -1) {
            if (tabuleiro[linhaaux][colunaaux] != 0)
                return false;
            colunaaux--;
            linhaaux--;
        }
        return true;
    }

    public static void limparConflitos(int coluna) {
        for (int i = 0; i < 8; i++) {
            tabuleiro[i][coluna] = 0;
        }
    }

    public static void exibirMatriz() {
        System.out.printf("+----+----+----+----+----+----+----+----+\n");
        for (int i = 0; i < 8; i++) {
            System.out.printf("|");
            for (int j = 0; j < 8; j++) {
                if (tabuleiro[i][j] != 0) {
                    System.out.printf(" Q%1d", tabuleiro[i][j]);
                } else System.out.printf("%3s", "  ");
                System.out.printf(" |");
            }
            System.out.printf("\n+----+----+----+----+----+----+----+----+\n");
        }
    }

    public static void fazerJogadasRecursivo(int i) {
        int linhaX;
        Random random = new Random();
        linhaX = random.nextInt(8);
        if (i == 7) {
            if (testarMovimento(linhaX, i)) {
                System.out.println("inseriu na coluna " + i);
                tabuleiro[linhaX][i] = i + 1;
            } else {
                System.out.println("Backtracking da coluna " + i + " para " + (i - 1) + "...");
                limparConflitos(i - 1);
                fazerJogadasRecursivo(i - 1);
            }
        }

        if (testarMovimento(linhaX, i)) {
            System.out.println("inseriu na coluna " + i);
            tabuleiro[linhaX][i] = i + 1;
            fazerJogadasRecursivo(i + 1);
        } else {
            System.out.println("Backtracking da coluna " + i + " para " + (i - 1) + "...");
            limparConflitos(i - 1);
            fazerJogadasRecursivo(i - 1);
        }
    }
}
