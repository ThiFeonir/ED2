package HASH;

/**
 * Created by thial on 17/08/2017.
 */
public class HashSala {

    public static int[] vetor1;
    public static int tamanho;

    public static void main(String[] args) {
        int[] vetor = {2421,4304,1324,3253,6908,8735,3435,666,756,5454};
        vetor1 = new int[achaPrimo(10)];
        tamanho = achaPrimo(10);
        int total = 0;
        for (int n:
             vetor) {
            total += insere(n);
        }
        System.out.println("Total de colisões: "+total);
    }

    public static int achaPrimo(int primo){
        boolean isPrimo = false;
        do {
            primo++;
            for (int i = 2; i <= primo; i++) {
                if ( ( (primo % i) == 0) && (i != primo) ) {
                    isPrimo = false;
                    break;
                }else
                    isPrimo = true;
            }
        } while (!isPrimo);

        return primo;
    }

    public static int hash(int chave){return chave % tamanho;}

    public static int insere(int chave) {

        int pos = hash(chave);
        int colisao = 0;
        final int TAMANHO_VETOR = vetor1.length;

        if (vetor1[pos] == 0) {
            vetor1[pos] = chave;
            System.out.println("Inserido "+chave+" na posição "+pos);
        } else {

            colisao++;
            System.out.println("Colisão na posição: "+pos);

            while (vetor1[++pos] != 0){
                if (pos ==  TAMANHO_VETOR){
                    pos = 0;
                }
            }
            vetor1[pos] = chave;
            System.out.println("Inserido "+chave+" na posição "+pos);
        }
        return colisao;
    }
}
