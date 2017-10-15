package ProvaED2Unidade3;
/*Aluno = THIAGO ALESSANDRO DOS SANTOS PEREIRA, PROVA DIA 05 DE OUTUBRO*/
import java.util.Scanner;

public class ProvaED2Unidade3 {
    static int linhaMontagem[][];
    static int linha[];
    static int linha2[];
    static int n;
    static int tempoLinha1[];
    static int tempoLinha2[];
    static int f1[];
    static int f2[];

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Digite a quantidade de estações  na linha de montagem: ");
        n = scan.nextInt();

        linhaMontagem = new int[2][n+2];
        linha = new int[n];
        linha2 = new int[n];
        tempoLinha1 = new int [n];
        tempoLinha2 = new int[n];
        f1 = new int[n];
        f2 = new int[n];

        System.out.println("Digite as linhas de montagem, incluindo tempo inicial e final");
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < n + 2; j++) {
                linhaMontagem[i][j] = scan.nextInt();
            }
            System.out.println("Agora linha 2...");
        }
        System.out.println("Agora preencha os valores de tempo em sequência referente a cada linha...");
        System.out.println("Da linha 1...");
        for (int i = 0; i < n - 1; i++) {
            tempoLinha1[i] = scan.nextInt();
        }
        System.out.println("Agora da linha 2...");
        for (int i = 0; i < n - 1; i++) {
            tempoLinha2[i] = scan.nextInt();
        }

        fazerCalculo();


    }

    public static void fazerCalculo() {

        int valorTotal = 0;
        int linhafinal = 0;

        f1[0] = linhaMontagem[0][0] + linhaMontagem[0][1];
        f2[0] = linhaMontagem[1][0] + linhaMontagem[1][1];

        for (int i = 1; i < n; i++) {

            if (linhaMontagem[0][i + 1] + f1[i - 1] <= linhaMontagem[0][i + 1] + f2[i - 1] + tempoLinha2[i - 1]) {
                f1[i] = linhaMontagem[0][i + 1] + f1[i-1];
                linha[i] = 1;
            } else {
                f1[i] = linhaMontagem[0][i + 1] + f2[i-1] + tempoLinha2[i - 1];
                linha[i] = 2;
            }


            if (linhaMontagem[1][i + 1] + f2[i-1] <= linhaMontagem[1][i + 1] + f1[i -1] + tempoLinha1[i - 1]) {
                f2[i] = linhaMontagem[1][i + 1] + f2[i-1];
                linha2[i] = 1;
            } else {
                f2[i] = linhaMontagem[1][i + 1] + f1[i-1] + tempoLinha1[i - 1];
                linha2[i] = 2;
            }
        }

        if (f1[n - 1] + linhaMontagem[0][n + 1] <= f2[n - 1] + linhaMontagem[1][n + 1]){
            valorTotal = f1[n - 1] + linhaMontagem[0][n+1];
            linhafinal = 1;
        }else{
            valorTotal = f2[n - 1] + linhaMontagem[1][n+1];
            linhafinal = 2;
        }
        
        exibir(linhafinal, valorTotal);
    }

    public static void exibir(int linhafinal, int valor){
        int lin = 0;

        System.out.println("Valor em tempo gasto: "+valor);
        System.out.println("linha "+linhafinal+" estação "+n);
        for (int i = n - 1; i > 0; i--) {
            if(valor == 1) {
                lin = linha[i];
                System.out.println("linha " + lin + " estacão " + (i));
            }else{
                lin = linha2[i];
                System.out.println("linha " + lin + " estacão " + (i));
            }
        }
    }
}
