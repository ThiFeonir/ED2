package HASH;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by thial on 06/08/2017.
 */
public class ConjuntoEspalhamento {

    private ArrayList<LinkedList<String>> tabela = new ArrayList<>();
    private int tamanho = 0;

    public ConjuntoEspalhamento(){
        for (int i = 0; i < 26 ; i++) {
            LinkedList<String> lista =  new LinkedList<>();
            tabela.add(lista);
        }
    }

    private int calculaIndiceDaTabela(String palavra){
        return palavra.toLowerCase().charAt(0) % 26;
    }

    public void adiciona(String palavra){
        if (!this.contem(palavra)) {
            int indice = this.calculaIndiceDaTabela(palavra);
            List<String> lista = this.tabela.get(indice);
            lista.add(palavra);
            tamanho++;
        }
    }

    public void remove(String palavra){
        if (this.contem(palavra)){
            int indice = this.calculaIndiceDaTabela(palavra);
            List<String> lista = this.tabela.get(indice);
            lista.remove(palavra);
        }
    }

    public boolean contem(String palavra){
        int indice = this.calculaIndiceDaTabela(palavra);
        List<String> lista = this.tabela.get(indice);

        return lista.contains(palavra);
    }

    public List<String> pegatodas(){
        List<String> palavras = new ArrayList<>();

        for (int i = 0; i < this.tabela.size(); i++) {
            palavras.addAll(this.tabela.get(i));
        }

        return palavras;
     }

    public int tamanho(){
        return tamanho;
    }
}
