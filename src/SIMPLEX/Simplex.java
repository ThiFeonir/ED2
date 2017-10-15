package SIMPLEX;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by thial on 01/09/2017.
 */
public class Simplex {

    private static int qnt;
    private static int qntRest;
    private static ArrayList<Double> valorDecisao;
    private static ArrayList<Double> vlrRest = new ArrayList<>();
    private static int contaRest = 0;
    private static int contaDecisao = 0;
    private static double[] base;
    private static Tabela tabela;

    public static void main(String[] args) throws IOException {
        //UM CASO JÁ PREFENIDO PARA FACILITAR OS TESTES...
        qnt = 4;   //Quantidade de variaveis de decisão
        valorDecisao = new ArrayList<>(); //um array de float do tamanho de qnt para armazenar os valores de decisão
        valorDecisao.add(0.20);   //adicionando os valores de decisão no array de valores de decisão
        valorDecisao.add(0.10);   //adicionando os valores de decisão no array de valores de decisão
        valorDecisao.add(0.09);   //adicionando os valores de decisão no array de valores de decisão
        valorDecisao.add(0.11);   //adicionando os valores de decisão no array de valores de decisão
        qntRest = 6;   //quantidade de restrições
        vlrRest.add(1.); // Arraylist contendo os valores das restrições em sequencia
        vlrRest.add(0.); // EX: 2x1 + 1x2 <= 18
        vlrRest.add(0.);
        vlrRest.add(0.);
        vlrRest.add(12500.);
        vlrRest.add(1.);
        vlrRest.add(1.);
        vlrRest.add(1.);
        vlrRest.add(1.);
        vlrRest.add(50000.);
        vlrRest.add(0.);
        vlrRest.add(0.);
        vlrRest.add(1.);
        vlrRest.add(0.);
        vlrRest.add(10500.);
        vlrRest.add(0.);
        vlrRest.add(0.);
        vlrRest.add(0.);
        vlrRest.add(1.);
        vlrRest.add(10000.);
        vlrRest.add(3.);
        vlrRest.add(-1.);
        vlrRest.add(0.);
        vlrRest.add(0.);
        vlrRest.add(0.);
        vlrRest.add(0.5);
        vlrRest.add(0.5);
        vlrRest.add(-1.);
        vlrRest.add(-1.);
        vlrRest.add(0.);
        base = new double[qntRest];  //Array para armazenar as variaveis que iniciam na base
        base[0] = 5;        //Atribuindo os valores na base a partir de qnt+1
        base[1] = 6;
        base[2] = 7;
        base[3] = 8;
        base[4] = 9;
        base[5] = 10;
       while (true) {
            Scanner scan = new Scanner(System.in);

            System.out.println("+---------------------------------------------+");
            System.out.println("|                    Menu                     |");
            System.out.println("+---+-----------------------------------------+");
            System.out.println("| C |                      Definir o problema |");
            System.out.println("+---+-----------------------------------------+");
            System.out.println("| S |                   Solução Passo a Passo |");
            System.out.println("+---+-----------------------------------------+");
            System.out.println("| D |                          Solução Direta |");
            System.out.println("+---+-----------------------------------------+");
            System.out.println("| R |                         Gerar Relatório |");
            System.out.println("+---+-----------------------------------------+");
            System.out.println("| E |                                    Exit |");
            System.out.println("+---+-----------------------------------------+");

            char op = (char) System.in.read();
            op = Character.toUpperCase(op);
            scan.nextLine();

            switch (op) {
                case 'C':
                    definirProblema();
                    break;
                case 'S':
                    fazerCalculoPasso();
                    break;
                case 'D':
                    fazerCalc();
                    break;
                case 'R':
                    fazerCalculo();
                    break;
                case 'E':
                    System.exit(0);
                    break;
                default:
                    System.out.println("Erro");
                    break;
            }
        }
   }

    private static void definirProblema(){
        vlrRest = new ArrayList<>();
        Scanner scan = new Scanner(System.in);
        System.out.println("Montando Modelo...");
        System.out.println("Digite a quantidade de variaveis de decisão: ");
        qnt = scan.nextInt();
        valorDecisao = new ArrayList<>();
        for (int i = 1; i <= qnt; i++) {
            System.out.println("Digite o valor da variavel de decisão X"+i+":");
            valorDecisao.add(scan.nextDouble());
        }
        System.out.println("Digite a quantidade de restrições: ");
        qntRest = scan.nextInt();
        for (int i = 1; i <= qntRest; i++) {
            System.out.println("Restrição" + i + ":");
            for (int j = 1; j <= qnt; j++) {
                System.out.println("Digite a utilização de recurso da variavel X" + j + ":");
                vlrRest.add(scan.nextDouble());
            }
            System.out.println("Digite a disponibilidade do recurso: ");
            vlrRest.add(scan.nextDouble());
            System.out.println();
        }
        base = new double[qntRest];
        int baseNumber = qnt+1;
        for (int i = 1; i <= qntRest; i++) {
            base[i-1] = baseNumber++;
        }
        mostraModelo();
        mostraModeloAtualizado();
    }

    public static void mostraModelo() {
        System.out.println("+----------------+");
        System.out.println("| MODELO INICIAL |");
        System.out.println("+----------------+");
        System.out.print("MAXIMIZAR = ");
        System.out.print(valorDecisao.get(contaDecisao++) + "x" + 1);
        for (int i = 1; i < qnt; i++) {
            System.out.print(" + " + valorDecisao.get(contaDecisao++) + "x" + (i + 1));
        }
        System.out.println();

        for (int i = 1; i <= qntRest; i++) {
            System.out.print(vlrRest.get(contaRest++) + "x" + (1));
            for (int j = 1; j < qnt; j++) {
                System.out.print(" + " + vlrRest.get(contaRest++) + "x" + (j + 1));
            }
            System.out.print(" <= " + vlrRest.get(contaRest++));
            System.out.println();
        }
        for (int i = 1; i <= qnt; i++) {
            System.out.print(" X" + i + " >= 0");
        }
        System.out.println();
        System.out.println();
        contaRest = 0;
        contaDecisao = 0;
    }

    public static void mostraModeloAtualizado() {

        int contFolga = qnt +1;
        int cont = qnt +1;
        System.out.println("+-------------------------------+");
        System.out.println("| MODELO COM VARIAVEIS DE FOLGA |");
        System.out.println("+-------------------------------+");
        System.out.print("MAXIMIZAR = ");
        System.out.print( valorDecisao.get(contaDecisao++) + "x" + 1);
        for (int i = 1; i < qnt+qntRest; i++) {
            if (i > qnt-1)
                System.out.print(" + 0x"+cont++);
            else
                System.out.print(" + " + valorDecisao.get(contaDecisao++) + "x" + (i + 1));
        }
        System.out.print("");
        System.out.println();

        for (int i = 1; i <= qntRest; i++) {
            System.out.print(vlrRest.get(contaRest++) + "x" + (1));
            for (int j = 1; j < qnt; j++) {
                System.out.print(" + " + vlrRest.get(contaRest++) + "x" + (j + 1));
            }
            System.out.print(" + x"+contFolga++);
            System.out.print(" = " + vlrRest.get(contaRest++));
            System.out.println();
        }
        System.out.print("");
        for (int i = 1; i <= qnt+qntRest; i++) {
            System.out.print(" X" + i + " >= 0");
        }
        System.out.println("");
        System.out.println();
        contaRest = 0;
        contaDecisao = 0;
    }

    public static void montarTabela() {
        tabela = new Tabela();
        double[] vetAux = new double[qnt + qntRest + 1];
        int cont = qnt;

        for (int i = 0; i < qntRest; i++) {
            for (int j = 0; j < qnt + qntRest; j++) {
                if (j > qnt - 1) {
                    if (j == cont)
                        vetAux[j] = 1;
                    else
                        vetAux[j] = 0;
                } else
                    vetAux[j] = vlrRest.get(contaRest++);
            }
            cont++;
            vetAux[qnt + qntRest] = vlrRest.get(contaRest++);
            Linha linha = new Linha(vetAux);
            tabela.getLinhas().add(linha);
        }
        for (int i = 0; i < qnt + qntRest + 1; i++) {
            if (i > qnt - 1)
                vetAux[i] = 0;
            else
                vetAux[i] = -valorDecisao.get(contaDecisao++);
        }
        Linha linha = new Linha(vetAux);
        tabela.getLinhas().add(linha);
        contaRest = 0;
        contaDecisao = 0;
    }

    public static void fazerCalculo() {
            restauraBase();
            montarTabela();
            mostraModelo();
            mostraModeloAtualizado();
            System.out.println("+----------------+");
            System.out.println("| TABELA INICIAL |");
            System.out.println("+----------------+");
            mostrarTabela(qntRest + 1, qnt + qntRest + 1);

            while (tabela.getLinhas().get(qntRest).hasNegative()) {
                mostrarResultadoParcial();
                int posi = tabela.getLinhas().get(qntRest).getMenor();
                double[] vetAux = new double[qntRest];

                for (int i = 0; i < qntRest; i++) {
                    vetAux[i] = (int) (tabela.getLinhas().get(i).getValores().
                            get(tabela.getLinhas().get(i).getValores().size() - 1) / tabela.getLinhas().get(i).getValores().get(posi));
                }

                int posiMenor = getMenor(vetAux, posi);
                double pivo = tabela.getLinhas().get(posiMenor).getPivo(posi);
                tabela.getLinhas().get(posiMenor).transformarLinha(posi);
                Linha linha = tabela.getLinhas().get(posiMenor);

                for (int i = 0; i <= qntRest; i++) {
                    if (i != posiMenor)
                        tabela.getLinhas().get(i).transformarLinha(linha, posi);

                }
                System.out.printf("%-10s %2.2f\n", "Pivô atual = ", pivo);
                System.out.println("Variavel que vai entrar na base é X" + (posi + 1) + " e a que sai é X" + (int) base[posiMenor] + ".");

                base[posiMenor] = ++posi;

                System.out.println("Atualizando tabela...");
                System.out.println();
                mostrarTabela(qntRest + 1, qnt + qntRest + 1);
            }
            mostrarResultado();
    }

    public static void fazerCalc() {
        montarTabela();
        while (tabela.getLinhas().get(qntRest).hasNegative()) {

            int posi = tabela.getLinhas().get(qntRest).getMenor();
            double[] vetAux = new double[qntRest];

            for (int i = 0; i < qntRest; i++) {
                vetAux[i] = (int) (tabela.getLinhas().get(i).
                        getValores().get(tabela.getLinhas().get(i).getValores().size() - 1) / tabela.getLinhas().get(i).getValores().get(posi));
            }
            int posiMenor = getMenor(vetAux, posi);
            tabela.getLinhas().
                    get(posiMenor).transformarLinha(posi);
            Linha linha = tabela.getLinhas().get(posiMenor);

            for (int i = 0; i <= qntRest; i++) {
                if (i != posiMenor)
                    tabela.getLinhas().get(i).transformarLinha(linha, posi);

            }
            base[posiMenor] = ++posi;
        }
        mostrarResultado();
    }

    private static void fazerCalculoPasso() throws IOException {
        boolean continuar;
        restauraBase();
        montarTabela();
        System.out.println("+----------------+");
        System.out.println("| TABELA INICIAL |");
        System.out.println("+----------------+");
        mostrarTabela(qntRest + 1, qnt + qntRest + 1);

        continuar = continuar();

        while (continuar && tabela.getLinhas().get(qntRest).hasNegative()) {
            mostrarResultadoParcialPasso();
            int posi = tabela.getLinhas().get(qntRest).getMenor();
            double[] vetAux = new double[qntRest];

            for (int i = 0; i < qntRest; i++) {
                vetAux[i] = (tabela.getLinhas().get(i).getValores().
                        get(tabela.getLinhas().get(i).getValores().size() - 1) / tabela.getLinhas().get(i).getValores().get(posi));
            }

            int posiMenor = getMenor(vetAux, posi);

            double pivo = tabela.getLinhas().get(posiMenor).getPivo(posi);
            tabela.getLinhas().
                    get(posiMenor).transformarLinha(posi);
            Linha linha = tabela.getLinhas().get(posiMenor);
            for (int i = 0; i <= qntRest; i++) {
                if (i != posiMenor)
                    tabela.getLinhas().get(i).transformarLinha(linha, posi);

            }
            System.out.printf("%-10s %2.2f\n", "Pivô atual = ", pivo);
            System.out.println("Variavel que vai entrar na base é X" + (posi + 1) + " e a que sai é X" + (int) base[posiMenor] + ".");

            base[posiMenor] = ++posi;

            System.out.println("Atualizando tabela...");
            System.out.println();
            mostrarTabela(qntRest + 1, qnt + qntRest + 1);

            continuar = continuar();
        }
        mostrarResultado();
    }

    public static int getMenor(double[] array, int posit) {
        double menor = 99999999999.;
        int posi = 0;
        for (int i = 0; i < array.length; i++) {
            if ((array[i] <= menor) && (tabela.getLinhas().get(i).getPivo(posit)) > 0) {
                menor = array[i];
                posi = i;
            }
        }
        return posi;
    }

    private static  void mostrarTabela(int linhas, int colunas) {
        for (int i = 0; i < linhas; i++) {
            if (i == 0) {
                gerarLinha(colunas);
                System.out.printf("|" + "%12s", "Base|");
                for (int k = 1; k <= colunas - 1; k++) {
                    System.out.printf("%12s", "X" + k + "|");
                }
                System.out.printf("%12s", "b" + "|");
                System.out.println(" ");
                gerarLinha(colunas);
            }

            if (i != linhas - 1) {
                System.out.printf("|" + "%12s", "X" + (int)base[i] + "|");
            } else {
                System.out.printf("|" + "%12s", "Z" + "|");
            }
            for (int j = 0; j < colunas; j++) {
                System.out.printf("%11.3f", tabela.getLinhas().get(i).getValores().get(j));
                System.out.print("|");
            }
            System.out.println(" ");
            gerarLinha(colunas);
        }
        System.out.println();
    }

    private static void gerarLinha(int colunas) {
        for (int i = 0; i <= colunas; i++) {
            System.out.print("+-----------");
        }
        System.out.print("+");
        System.out.println(" ");
    }
    private static void mostrarResultado(){
        System.out.println("Como não existem mais valores negativos na linha Z...");
        System.out.println("+----------------------------------------------------+");
        System.out.println("| A solução ótima é Z = " + tabela.getLinhas().get(tabela.getLinhas().size() - 1).
                getValores().get(tabela.getLinhas().get(tabela.getLinhas().size() - 1).getValores().size() - 1));

        for (int i = 0; i < qntRest; i++) {
            if(base[i] <= qnt) {
                System.out.print("| X" + (int) base[i] + " = ");
                System.out.printf("%3.1f", tabela.getLinhas().get(i).getValores().get(tabela.getLinhas().get(i).getValores().size() - 1));
            }
        }
        System.out.println();
        System.out.println("+----------------------------------------------------+");
        System.out.println("___________________________________________________________________________________________________________________________________________________________________________________");
    }

    private static void mostrarResultadoParcial(){

        System.out.println("A solução parcial é Z = " + tabela.getLinhas().get(tabela.getLinhas().size() - 1).
                getValores().get(tabela.getLinhas().get(tabela.getLinhas().size() - 1).getValores().size() - 1));

        for (int i = 0; i < qntRest; i++) {
            if(base[i]<=qnt) {
                System.out.print("X" + (int) base[i] + " = ");
                System.out.printf("%3.0f\n", tabela.getLinhas().get(i).getValores().get(tabela.getLinhas().get(i).getValores().size() - 1));
            }
        }
        System.out.println("Como ainda existe valores negativos na linha Z, ainda não é uma solução ótima...");
        System.out.println("___________________________________________________________________________________________________________________________________________________________________________________");
    }
    private static void mostrarResultadoParcialPasso(){
        System.out.println("___________________________________________________________________________________________________________________________________________________________________________________");
        System.out.println("A solução parcial é Z = " + tabela.getLinhas().get(tabela.getLinhas().size() - 1).
                getValores().get(tabela.getLinhas().get(tabela.getLinhas().size() - 1).getValores().size() - 1));

        for (int i = 0; i < qntRest; i++) {
            if(base[i]<=qnt) {
                System.out.print("X" + (int) base[i] + " = ");
                System.out.printf("%3.0f\n", tabela.getLinhas().get(i).getValores().get(tabela.getLinhas().get(i).getValores().size() - 1));
            }
        }
        System.out.println("Como ainda existe valores negativos na linha Z, ainda não é uma solução ótima...");

    }

    private static boolean continuar() throws IOException {
        Scanner scan = new Scanner(System.in);

        System.out.println("Continuar? (S/N)");
        char op = (char) System.in.read();
        op = Character.toUpperCase(op);
        scan.nextLine();

        if(op == 'S') return true;
        else return false;
    }

    private static void restauraBase(){
        base = new double[qntRest];
        int baseNumber = qnt+1;
        for (int i = 1; i <= qntRest; i++) {
            base[i-1] = baseNumber++;
        }
    }
}
