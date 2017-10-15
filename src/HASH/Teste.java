package HASH;

import java.util.List;

/**
 * Created by thial on 06/08/2017.
 */
public class Teste {
    public static void main(String[] args) {
        ConjuntoEspalhamento conjunto = new ConjuntoEspalhamento();

        conjunto.adiciona("palavra");
        conjunto.adiciona("computador");
        conjunto.adiciona("apostila");
        conjunto.adiciona("instrutor");
        conjunto.adiciona("mesa");
        conjunto.adiciona("telefone");

        List<String> palavras = conjunto.pegatodas();

        for (String palavra:
                palavras) {
            System.out.println(palavra);
        }

        if (!conjunto.contem("apostila")){
            System.out.println("Erro: não tem a palavra: apostila");
        }

        conjunto.remove("apostila");

        if(conjunto.contem("apostila")){
            System.out.println("Erro: tem a palavra: apostila");
        }

        if (conjunto.tamanho() != 5){
            System.out.println("Erro: o tamanho do conjunto deveria ser 5");
        }

        List<String> palavras2 = conjunto.pegatodas();

        for (String palavra:
                palavras2) {
            System.out.println(palavra);
        }

    }
}
