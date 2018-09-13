import java.util.ArrayList;
import java.util.Arrays;

public class Main {

    public static void main(String[] args){

        //Oblig1.maks(a);

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

//
//        for (int[] e: t
//             ) {
//            int antall = Oblig1.ombyttinger(e);
//            System.out.println(antall);
//        }


        int[] ulike = {2, 4, 6, 9};

//        Oblig1.antallUlikeSortert(ulike);

        int[] del = {6,10,9,4,1,3,8,5,2,7};

        int[] it = new int[]{1, 2, 3, 4, 5, 6};

        //Oblig1.delsortering(i);

        char[] chars = new char[]{'A','B','C', 'D', 'E'};

//        System.out.println("Før: " + Arrays.toString(chars));
//        //Oblig1.rotasjon(chars,-1);
//        System.out.println("Etter: " + Arrays.toString(chars));


        int[] a = {1,2,3,4,5,6};
        //int[] minste = Oblig1.tredjeMin(a);
        //FULLLFØR DENNE, F er feil

        int[] k = {4,2,5,1};
        //Oblig1.inneholdt("ABBA", "BARBER");
        System.out.println(Arrays.toString(k));



    }

}
