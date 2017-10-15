package MergeParcial;

import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by thial on 24/08/2017.
 */
public class MergeParcial {

    int contArquivo = 0;
    int contArquivoMaior = contArquivo +1;
    public File mergeParcialUm()throws IOException{

        int vetOrdenado[] = new int[9];
        int vetaux[] = new int[contArquivo];
        int posVet = 0;
        int posVetOrd = 0;

        for (int i = 0; i < contArquivo; i++) {
            String nmArquivo = "arquivo" + i;
            File f = new File(nmArquivo);
            FileInputStream fis = new FileInputStream(f);
            DataInputStream dis = new DataInputStream(fis);

            vetaux[posVet++] = dis.readInt();
        }

        for (int i = 0; i < 9; i++) {
            int posMenor = posiMenor(vetaux, vetaux.length);
            String nmArquivo2 = "arquivo" + posMenor;
            File f = new File(nmArquivo2);
            FileInputStream fis = new FileInputStream(f);
            DataInputStream dis = new DataInputStream(fis);

            vetOrdenado[posVetOrd++] = vetaux[posMenor];
            vetaux[posMenor++] = dis.readInt();
        }

        String nmArquivo3 = "arquivo" + contArquivoMaior;
        File f = new File(nmArquivo3);
        FileOutputStream fos = new FileOutputStream(f);
        DataOutputStream dos = new DataOutputStream(fos);

        for (int num:
             vetOrdenado) {
            dos.writeInt(num);
        }
        contArquivoMaior++;
        contArquivo = 0;
        dos.close();
        fos.close();
        return f;
    }

    public File mergeParcialFinal()throws IOException{

        int vetOrdenado[] = new int[9];
        int vetaux[] = new int[contArquivoMaior - contArquivo];
        int posVet = 0;
        int posVetOrd = 0;

        for (int i = contArquivo + 1; i <= contArquivoMaior; i++) {
            String nmArquivo = "arquivo" + i;
            File f = new File(nmArquivo);
            FileInputStream fis = new FileInputStream(f);
            DataInputStream dis = new DataInputStream(fis);

            vetaux[posVet++] = dis.readInt();
        }

        for (int i = 0; i < 9; i++) {
            int posMenor = posiMenor(vetaux, vetaux.length);
            String nmArquivo2 = "arquivo" + posMenor;
            File f = new File(nmArquivo2);
            FileInputStream fis = new FileInputStream(f);
            DataInputStream dis = new DataInputStream(fis);

            vetOrdenado[posVetOrd++] = vetaux[posMenor];
            vetaux[posMenor++] = dis.readInt();
        }

        String nmArquivo3 = "arquivoFinal";
        File f = new File(nmArquivo3);
        FileOutputStream fos = new FileOutputStream(f);
        DataOutputStream dos = new DataOutputStream(fos);

        for (int num:
                vetOrdenado) {
            dos.writeInt(num);
        }
        dos.close();
        fos.close();
        return f;
    }

    public File criadorArquivo(int vet[]) throws IOException {
        quickSort(vet, 0, vet.length -1);

        String nmArquivo = "arquivo" + contArquivo;
        File f = new File(nmArquivo);
        FileOutputStream fos = new FileOutputStream(f);
        DataOutputStream dos = new DataOutputStream(fos);

        for (int num:
             vet) {
            dos.writeInt(num);
        }
        contArquivo++;
        dos.close();
        fos.close();
        return f;
    }

    public int[] quickSort(int vetor[], int low, int high){
        int i = low, j = high;
        int meio = vetor[low + (high - low)/2];

        while(i <= j){

            while (vetor[i] < meio)
                i++;
            while(vetor[j] > meio)
                j--;

            if(i <= j){
                troca(vetor, i, j);
                i++;
                j--;
            }
        }
        if(low < j)
            quickSort(vetor, low, j);
        if(i < high)
            quickSort(vetor, i, high);

        return vetor;
    }
    private void troca(int vetor[], int i, int j){
        int temp = vetor[i];
        vetor[i] = vetor[j];
        vetor[j] = temp;
    }

    public int posiMenor(int vetor[], int tamanho){

        int menor = vetor[0];
        int posiMenor = 0;

        for (int i = 1; i < vetor.length; i++)
            if(vetor[i] < menor) {
                menor = vetor[i];
                posiMenor = i;
            }
            return posiMenor;
    }
}

class Teste{

    public static void main(String[] args) throws IOException {
        int vet[] = {9,14,20,5,18,3,1,12,1,4,2,15,6,12,17,22,30,28};
        int tamanhoMemoria = 3;
        MergeParcial mg = new MergeParcial();


        File f = new File("arquivotest.dat");
        FileOutputStream fos = new FileOutputStream(f);
        DataOutputStream dos = new DataOutputStream(fos);

        for (int i = 0; i < vet.length; i++) {
            dos.writeInt(vet[i]);
        }

        //mostraDadods(f);
        dividirArquivo(f, tamanhoMemoria, mg);
        mg.mergeParcialUm();
        //dividirArquivo(f, tamanhoMemoria, mg);
        //mg.mergeParcialUm();
        //mg.mergeParcialFinal();

        for (int i = 0; i < 1; i++) {
            String nmArquivo2 = "arquivo" + i;
            File f2 = new File("arquivo2");
            mostraDadods(f2);
        }

        dos.close();
        fos.close();

    }

    public static void mostraDadods(File arquivo) throws IOException {
        try {
            FileInputStream fis = new FileInputStream(arquivo);
            DataInputStream dis = new DataInputStream(fis);

            System.out.println("\nMostrando os dados que foram gravados: ");
            System.out.println("=======================");
            while (dis.available() > 0) {
                System.out.println("numero: " + dis.readInt());
                System.out.println("=======================");
            }
            fis.close();
            dis.close();

        } catch (EOFException e) {
            e.printStackTrace();
        }
    }

    public static void dividirArquivo(File arquivo, int tamanhoMemoria, MergeParcial mg) throws IOException{

        int  vetaux[] = new int[tamanhoMemoria];
        FileInputStream fis = new FileInputStream(arquivo);
        DataInputStream dis = new DataInputStream(fis);

        for (int i = 0; i < (tamanhoMemoria); i++) {
            for (int j = 0; j < tamanhoMemoria; j++) {
                vetaux[j] = dis.readInt();
            }
            mg.criadorArquivo(vetaux);
        }
    }
}
