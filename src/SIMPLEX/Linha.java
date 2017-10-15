package SIMPLEX;

import java.util.ArrayList;

/**
 * Created by thial on 03/09/2017.
 */
public class Linha {
    double menor = 0;
    private ArrayList<Double> valores = new ArrayList<>();

    public Linha(double[] array){
        for (int i = 0; i <array.length ; i++) {
            valores.add(array[i]);
        }
    }

    public ArrayList<Double> getValores() {
        return valores;
    }

    public int getMenor(){
        int posi = 0;
        for (int i = 0; i <valores.size() ; i++) {
            if (valores.get(i)<menor) {
                menor = valores.get(i);
                posi = i;
            }
        }

        menor = 0;
        return posi;
    }
    public void transformarLinha(Linha linha, int posi){
        double pivo = valores.get(posi);
        for (int i = 0; i < valores.size(); i++) {
            double valorLinha = linha.valores.get(i);
            double aux = valores.get(i);
            valores.set(i,aux - (valorLinha * pivo));
        }
    }

    public void transformarLinha(int posi){
        double pivo = valores.get(posi);
        for (int i = 0; i < valores.size(); i++) {
            valores.set(i,valores.get(i)/ pivo);
        }
    }

    public double getPivo(int posi){
        return valores.get(posi);
    }

    public boolean hasNegative(){
        for (double valor:
             valores) {
            if (valor < 0)
                return true;
        }
        return false;
    }
}
