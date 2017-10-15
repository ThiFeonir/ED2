package Hanoi;

/**
 * Created by thial on 23/09/2017.
 */
public class Hanoi {
    public static void main(String[] args) {

        hanoi(7, "a", "b", "c");

    }

    public static void hanoi(int n, String a, String b, String c){
        if(n == 1)
            System.out.println("move disco 1 da torre "+a+" para a torre "+b);
            else {
            hanoi(n - 1, a, c, b);
            System.out.println("move disco "+n+" da torre "+a+" para a torre "+b);
            hanoi(n - 1, c, b, a);
        }
    }
}
