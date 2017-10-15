package Dinamico;

import java.util.Scanner;

/**
 * Created by thial on 21/09/2017.
 */
public class Dinamico {

    static int piramide[][];
    static int piramideSoma[][];

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);

        System.out.println("Digite o tamanho da piramide: ");
        int tam = scan.nextInt();

        piramide = new int[tam][tam];
        piramideSoma = new int[tam][tam];


        System.out.println("Preencha a piramide...");

        piramide[0][0] = 5;
        piramide[1][0] = 6;
        piramide[1][1] = 9;
        piramide[2][0] = 15;
        piramide[2][1] = 3;
        piramide[2][2] = 7;
        piramide[3][0] = 8;
        piramide[3][1] = 1;
        piramide[3][2] = 2;
        piramide[3][3] = 19;
        piramide[4][0] = 13;
        piramide[4][1] = 4;
        piramide[4][2] = 21;
        piramide[4][3] = 32;
        piramide[4][4] = 11;
        piramide[5][0] = 7;
        piramide[5][1] = 5;
        piramide[5][2] = 11;
        piramide[5][3] = 15;
        piramide[5][4] = 3;
        piramide[5][5] = 21;
        piramide[6][0] = 32;
        piramide[6][1] = 7;
        piramide[6][2] = 9;
        piramide[6][3] = 13;
        piramide[6][4] = 17;
        piramide[6][5] = 9;
        piramide[6][6] = 7;

        for (int i = 0; i < piramide.length; i++) {
            piramideSoma[piramide.length-1][i] = piramide[piramide.length-1][i];
        }

        executar();
    }

    public static void executar(){

        int qntPai = piramide.length -1;
        int linha = piramide.length -1;
        int posPai = 0;
        int posFilho = 0;
        int pai;
        int filho1;
        int filho2;

        while (linha != 0) {
            while(posFilho < qntPai) {

                pai = piramide[linha - 1][posPai];
                filho1 = piramideSoma[linha][posFilho];
                filho2 = piramideSoma[linha][posFilho + 1];

                if (pai + filho1 > pai + filho2)
                    piramideSoma[linha-1][posPai] = pai + filho1;
                else
                    piramideSoma[linha-1][posPai] = pai + filho2;

                posPai++;
                posFilho++;

            }
            posPai = 0;
            posFilho = 0;
            qntPai--;
            linha--;
        }
        piramideSoma[0][0] = piramide[0][0];
        gerarResultado();
    }

    public static void gerarResultado(){
        int pos = 0;

        System.out.println("Melhor caminho...");
        System.out.println(piramide[0][0]);
        for (int i = 1; i < piramide.length; i++) {

            pos = posMaior(pos, i);
            System.out.println(piramide[i][pos]);
        }

    }

    public static int posMaior(int pos, int linha){

        if(piramideSoma[linha][pos] > piramideSoma[linha][pos+1])
            return  pos;
        else
            return pos+1;
    }

}
