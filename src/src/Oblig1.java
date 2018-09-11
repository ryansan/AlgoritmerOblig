import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Random;

public class Oblig1 {

    //Når blir det flest ombyttinger?
    //Det blir flest ombytter dersom elementene er sortert synkende.
    //Når blir det færrest ombyttinger?
    //Det blir færrest hvis den er sortert
    public static int maks(int[] a){

        System.out.println("Init " + Arrays.toString(a));

        if (a.length < 0){
            throw new NoSuchElementException("TOM");
        }

        for(int i = 0; i < a.length-1; i++){
            if(a[i] > a[i+1])
                bytt(a,i,i+1);
        }

        System.out.println("Etter sortering: " + Arrays.toString(a));
        System.out.println("største tallet: " + a[a.length-1]);

        return a[a.length-1];
    }

    public static void bytt(int[] a, int i, int j)
    {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    public static void bytt(char[] a, int i, int j)
    {
        char temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    //Hvor mange blir det i gjennomsnitt?
    //Ser at jeg i gjennomsnitt får halvparten, eller litt under. SJEKK OM BEDRE ENN MAKS
    public static int ombyttinger(int[] a){

        int teller = 0;

        for(int i = 0; i < a.length-1; i++){
            if(a[i] > a[i+1])
                teller++;
        }

        return teller;
    }

    public static int[] randPerm(int n)
    {
        int[] a = new int[n]; // fyller tabellen med 1, 2, . . , n
        for (int i = 0; i < n; i++) a[i] = i+1;

        Random r = new Random();  // hentes fra java.util

        for (int k = n-1; k > 0; k--)
        {
            int i = r.nextInt(k+1);  // tilfeldig tall fra [0,k]
            bytt(a,k,i);
        }

        return a; // tabellen med permutasjonen returneres

    } // randPerm

    public static int antallUlikeSortert(int[] a){

        //Sjekk om tabell er sortert
        //return 0 hvis tabell er tom
        //Traverser
        //Sjekk om første taller er lik andre, bruk array til å telle opp ulike

        //1 2 2 3
        // får til sammen 3 ulike tall


        if(a.length <= 0){
            return 0;
        }

        if(inversjon(a)){
            throw new IllegalStateException("Tabellen er ikke sortert");
        }




        int teller = 1;
        int[] testArray = new int[a.length];

        testArray[0] = a[0];

        for(int i = 0; i < a.length-1; i++){
//            for(int j = 0; j < a.length-1; j++){
//                if(a[i] !=a[i+1] && a[i] != a[j]){
//                    teller++;
//                }
//            }
            if(a[i] != a[i+1] && notInList(testArray,i,a[i+1])){
                teller++;
                testArray[i] = a[i+1];
            }
        }

        System.out.println("antall ulike " + teller);
        System.out.println(Arrays.toString(testArray));
        return teller;
    }

    public static boolean inversjon(int[] a){


        for (int i = 0; i < a.length - 1; i++) {
            for (int j = i + 1; j < a.length; j++) {
                if (a[i] > a[j]){
                    return true;
                }
            }
        }
        return false;
    }

    public static int antallUlikeUsortert(int[] a){
        //Sjekk om tabell er sortert
        //return 0 hvis tabell er tom
        //Traverser
        //Sjekk om første taller er lik andre, bruk array til å telle opp ulike

        //1 2 2 3 4 4 7 7 7 8
        // 6 ulike; tilsammen: 10; tilsamen-reps=
        // får til sammen 3 ulike tall

        if(a.length <= 0){
            return 0;
        }

        int teller = 0;

        if(a.length == 1){
            teller++;
        }

        for(int i = 0; i < a.length-1; i++){
            //sjekk
            if(a[i] != a[i+1] && notInList(a,i+1,a[i+1])){
                teller++;
            }
        }

        System.out.println("antall ulike " + teller);
        return teller;
    }


    public static boolean notInList(int[] array, int indeks, int tall){
        for(int i = 0; i < array.length; i++){
            if(i == indeks){
                continue;
            }
            if(array[i] == tall && i != indeks){
                return true;
            }
        }
        return false;
    }

    public static boolean notInListFØRSTEFUNGERTE(int[] array, int indeks, int tall){
        for(int i = 0; i < array.length; i++){
            if(i == indeks){
                continue;
            }
            if(array[i] == tall && i != indeks){
                return false;
            }
        }
        return true;
    }

    public static void delsortering(int[] a){

        int midten = a.length/2;

        int oddeTallTeller = 0;

        int parTallTeller = 0;

        for(int i = 0; i < a.length; i++){

            //partallene
            if(a[i]%2==0){
                parTallTeller++;
            }else{
                //oddetall
                bytt(a,oddeTallTeller,i);
                oddeTallTeller++;
            }
        }

        //hvis det kun er partall eller oddetall, sorter hele tabellen, hvis ikke, utfør bubblesort på hver side
        if(oddeTallTeller != 0 && parTallTeller == 0 || oddeTallTeller == 0 && parTallTeller != 0){
            bubbleSort(a,0,a.length);
        }
        else{
            bubbleSort(a,0,midten);
            bubbleSort(a,midten,a.length);
        }


    }

    public static void bubbleSort(int[] a, int fra, int til){
        for(int i = fra; i < til-1; i++){
            bubble(a, fra, til);
        }
    }

    public static void bubble(int[] a, int fra, int til){
        for(int i = fra; i < til-1;i++){
            if (a[i] > a[i + 1]) {
                bytt(a, i, i + 1);
            }
        }
    }


    public static void rotasjon(char[] a){

        if(a.length == 0){
            return;
        }

        char temp = a[a.length-1];

        for(int i = a.length-2; i >= 0; i--){
            a[i+1] = a[i];
        }

        a[0] = temp;

    }

    public static void rotasjon(char[] a, int k){

        if(k == 1){
            rotasjon(a);
            return;
        }

        int venstre = 0;
        int høyre = 0;

        int n = a.length;

        if (n < 2){
            rotasjon(a);
            return;   // tomt eller en verdi
        }

        if((k %= n) < 0){
            k += n;   // motsatt vei?
        }

        for (venstre = 0, høyre = n - 1; venstre < høyre;){
            bytt(a, venstre++, høyre--); // snur a[a:n>
        }

        for (venstre = 0, høyre = k - 1; venstre < høyre;){
            bytt(a, venstre++, høyre--); // snur a[0:d>
        }

        for (venstre = k, høyre = n - 1; venstre < høyre;){
            bytt(a, venstre++, høyre--);   // snur a[d:n>
        }

    }

    public static String flett(String s, String t){

        if(s.length() < 0)
            throw new NoSuchElementException("fail");

        StringBuilder sb = new StringBuilder();

        String lengste;
        String korteste;

        if(s.length() > t.length()){
            lengste = s;
            korteste = t;

        }else{
            lengste = t;
            korteste = s;
        }

        int i = 0;
        for(; i < korteste.length(); i++){
            sb.append(s.charAt(i));
            sb.append(t.charAt(i));
        }

        System.out.println(sb.toString());
        System.out.println("APPENDER: ");

        for(; i < lengste.length(); i++){
            sb.append(lengste.charAt(i));
        }


        System.out.println(sb.toString());
        return sb.toString();
    }

    public static String flett(String... s){

        String lengste = s[0];

        StringBuilder sb = new StringBuilder();

        for (String streng: s
             ) {
            if(streng.length() > lengste.length()){
                lengste = streng;
            }

        }

        for(int i = 0; i < lengste.length(); i++){
            for(int j = 0; j < s.length; j++){
                if(s[j].length() > i){
                    sb.append(s[j].charAt(i));
                }
            }
        }

        return sb.toString();
    }

    public static int[] indekssortering(int[] a){

        int[] indekser = new int[a.length];
        int[] kopiAvTabellen = Arrays.copyOf(a,a.length);
        int[] hjelpeTabell = new int[a.length];


        int minste = a[0];
        int minsteIndeks = 0;

        for(int i = 0; i < kopiAvTabellen.length; i++){
            for(int j = 0; j < kopiAvTabellen.length; j++){
                if(kopiAvTabellen[i] > kopiAvTabellen[j]){
                    int temp = kopiAvTabellen[i];
                    kopiAvTabellen[i] = kopiAvTabellen[j];
                    kopiAvTabellen[j] = temp;
                }
            }
            if(a[i] < minste){
                minste = a[i];
                minsteIndeks = i;
                bytt(kopiAvTabellen,i, minsteIndeks);
            }
        }

        System.out.println(Arrays.toString(a));
        System.out.println(Arrays.toString(kopiAvTabellen));

//        for(int i = kopiAvTabellen.length; i > 0; i++){
//            if(kopiAvTabellen[i]
//        }

        return new int[]{};
    }


    public static int min(int[] a, int fra, int til)
    {
        if (fra < 0 || til > a.length || fra >= til)
            throw new IllegalArgumentException("Illegalt intervall!");

        int m = fra;             // indeks til minste verdi i a[fra:til>
        int minverdi = a[fra];  // minste verdi i a[fra:til>

        for (int i = fra + 1; i < til; i++) if (a[i] < minverdi)
        {
            m = i;               // indeks til minste verdi oppdateres
            minverdi = a[m];    // minste verdi oppdateres
        }

        return m;  // posisjonen til minste verdi i a[fra:til>
    }
}
