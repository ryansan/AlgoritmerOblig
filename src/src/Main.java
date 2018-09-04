import java.util.ArrayList;

public class Main {

    public static void main(String[] args){

        int[] a = {4,2,3,8,11,9};
        Oblig1.maks(a);

        int[] ti = Oblig1.randPerm(10);
        int[] femti = Oblig1.randPerm(50);
        int[] hundre = Oblig1.randPerm(100);
        int[] tusen = Oblig1.randPerm(1000);
        int[] hundretusen = Oblig1.randPerm(10000);

        ArrayList<int[]> t = new ArrayList<>();
        t.add(ti);
        t.add(femti);
        t.add(hundre);
        t.add(tusen);
        t.add(hundretusen);


        for (int[] e: t
             ) {
            int antall = Oblig1.ombyttinger(e);
            System.out.println(antall);
        }


        int[] ulike = {5,4,4,2,3,3,8,7,7,7,7,7,7,7,7,7,7,7,7,7,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9};

        Oblig1.antallUlikeSortert2(ulike);



    }
}
