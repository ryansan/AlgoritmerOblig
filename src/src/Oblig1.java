import java.util.NoSuchElementException;

public class Oblig1 {

    public static int maks(int[] a){
        if (a.length < 0){
            throw new NoSuchElementException("TOM");
        }

        int m = a[0];
        int indeks = 0;

        for(int i = 0; i < a.length+1; ++i){
            if(a[i] < a[i+1]){
                a[i] = a[i+1];
                a[i] = a[indeks];
            }
        }

        for (int c : a) {
            System.out.println(c);
        }
        System.out.println(m);
        return m;
    }

}
