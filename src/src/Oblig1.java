import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Random;

public class Oblig1 {

    //Når blir det flest ombyttinger?
    //Det blir flest ombytter dersom elementene er sortert synkende.
    //Når blir det færrest ombyttinger?
    //Det blir færrest hvis den er sortert
    public static int maks(int[] a){


        if (a.length <= 0){
            throw new NoSuchElementException("Tabellen er tom!");
        }

        for(int i = 0; i < a.length-1; i++){
            if(a[i] > a[i+1])
                bytt(a,i,i+1);
        }

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
            if(a[i] > a[i+1]) {
                bytt(a, i, i + 1);
                teller++;
            }
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

        if(a.length == 0){
            return 0;
        }

        if(inversjon(a)){
            throw new IllegalStateException("Tabellen er ikke sortert");
        }

        int teller = 0;

        for(int i = 0; i < a.length-1; i++){
            if(a[i] == a[i+1]){
                teller++;
            }
        }
        return a.length-teller;
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

    public static boolean inversjon(int[] a){

        int antall = 0;

        for (int i = 0; i < a.length - 1; i++) {
            for (int j = i + 1; j < a.length; j++) {
                if (a[i] > a[j]){
                    antall++;
                }
            }
        }
        if(antall > 0){
            return true;
        }else{
            return false;
        }
    }

    public static int antallUlikeUsortert(int[] a){
        int teller = a.length;

        for(int i = 0; i < a.length; i++){
            for(int j = i+1; j < a.length; j++){
                if(a[j] == a[i]){
                    teller--;
                    break;
                }
            }
        }

        return teller;
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
                //deler også opp tabellen slik at man får oddetall på venstre side
                //og partall på høyre side
                bytt(a,oddeTallTeller,i);
                oddeTallTeller++;
            }
        }

        //hvis det kun er partall eller oddetall, sorter hele tabellen, hvis ikke, utfør bubblesort på hver side
        if(oddeTallTeller != 0 && parTallTeller == 0 || oddeTallTeller == 0 && parTallTeller != 0){
            kvikksortering(a);
        }
        else{
            kvikksortering(a,0,midten);
            kvikksortering(a,midten,a.length);
        }


    }

    //Kvikksorterings metoder fra kompendiet for å effektivisere søket
    private static void kvikksortering0(int[] a, int v, int h)  // en privat metode
    {
        if (v >= h) return;  // a[v:h] er tomt eller har maks ett element
        int k = sParter0(a, v, h, (v + h)/2);  // bruker midtverdien
        kvikksortering0(a, v, k - 1);     // sorterer intervallet a[v:k-1]
        kvikksortering0(a, k + 1, h);     // sorterer intervallet a[k+1:h]
    }

    public static void kvikksortering(int[] a, int fra, int til) // a[fra:til>
    {
        fratilKontroll(a.length, fra, til);  // sjekker når metoden er offentlig
        kvikksortering0(a, fra, til - 1);  // v = fra, h = til - 1
    }

    public static void kvikksortering(int[] a)   // sorterer hele tabellen
    {
        kvikksortering0(a, 0, a.length - 1);
    }
    private static int parter0(int[] a, int v, int h, int skilleverdi)
    {
        while (true)                                  // stopper når v > h
        {
            while (v <= h && a[v] < skilleverdi) v++;   // h er stoppverdi for v
            while (v <= h && a[h] >= skilleverdi) h--;  // v er stoppverdi for h

            if (v < h) bytt(a,v++,h--);                 // bytter om a[v] og a[h]
            else  return v;  // a[v] er nåden første som ikke er mindre enn skilleverdi
        }
    }

    private static int sParter0(int[] a, int v, int h, int indeks)
    {
        bytt(a, indeks, h);           // skilleverdi a[indeks] flyttes bakerst
        int pos = parter0(a, v, h - 1, a[h]);  // partisjonerer a[v:h − 1]
        bytt(a, pos, h);              // bytter for å få skilleverdien på rett plass
        return pos;                   // returnerer posisjonen til skilleverdien
    }



    public static void fratilKontroll(int tablengde, int fra, int til)
    {
        if (fra < 0)                                  // fra er negativ
            throw new ArrayIndexOutOfBoundsException
                    ("fra(" + fra + ") er negativ!");

        if (til > tablengde)                          // til er utenfor tabellen
            throw new ArrayIndexOutOfBoundsException
                    ("til(" + til + ") > tablengde(" + tablengde + ")");

        if (fra > til)                                // fra er større enn til
            throw new IllegalArgumentException
                    ("fra(" + fra + ") > til(" + til + ") - illegalt intervall!");
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

        if(s.length <= 0){
            return "";
        }

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

    public static int[] indekssorteringDØD(int[] a){

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

    public static int[] indekssortering(int[] a){



        int[] hjelpeTabell = new int[a.length];

        for (int i = 0; i < a.length; i++){
            hjelpeTabell[i] = a[i];
        }

        int[] indekser = new int[a.length];


        for(int i = 0; i < hjelpeTabell.length; i++) {
            int indeks = min(hjelpeTabell);
            indekser[i] = indeks;
            hjelpeTabell[indeks] = 0x7fffffff; // legger tallet 2147483647 sist
        }

        System.out.println(Arrays.toString(indekser));

        return indekser;
    }

    public static int min(int[]a){
        int min = a[0];
        int minIndeks = 0;

        for(int i = 0; i < a.length; i++){
           if(a[i] < min){
               min = a[i];
               minIndeks = i;
           }
        }
        return minIndeks;
    }

    public static int[] tredjeMin(int[] a) // ny versjon
    {
        int n = a.length;     // tabellens lengde
        if (n < 3) throw      // må ha minst to verdier
                new java.util.NoSuchElementException("a.length(" + n + ") < 3!");

        int m = 0;      // m er posisjonen til største verdi
        int nm = 1;     // nm er posisjonen til nest største verdi
        int tnm = 2; // tnm er posisjonen til nest største verdi

        // bytter om m og nm hvis a[1] er større enn a[0]
        int[] hjelpeTabell = new int[]{a[0],a[1],a[2]};
        hjelpeTabell = indekssortering(hjelpeTabell);

        m = hjelpeTabell[0];
        nm = hjelpeTabell[1];
        tnm = hjelpeTabell[2];

        int minsteverdi = a[m];                // største verdi
        int nestminstverdi = a[nm];           // nest største verdi
        int tredjeverdi = a[tnm];

        //starter på 3(4) element
        for (int i = 3; i < n; i++) {
            if (a[i] < tredjeverdi) {

                if (a[i] < nestminstverdi) {

                    if (a[i] < minsteverdi) {
                        tnm = nm;
                        tredjeverdi = nestminstverdi;     // ny tredje minst

                        nm = m;
                        nestminstverdi = minsteverdi;     // ny nest minst

                        m = i;
                        minsteverdi = a[m];              // ny minst
                    } else {
                        tnm = nm;
                        tredjeverdi = nestminstverdi;    // ny tredjeminst

                        nm = i;
                        nestminstverdi = a[nm];         // ny nest minst
                    }
                }
                else{
                    tnm = nm;
                    tredjeverdi = nestminstverdi;    // ny tredjeminst
                }
            }

        }// for
        return new int[] {m,nm,tnm};    // n i posisjon 0, nm i posisjon 1, tnm i posisjon 2

    }


    public static int[] nestMaks(int[] a) // ny versjon
    {
        int n = a.length;     // tabellens lengde
        if (n < 2) throw      // må ha minst to verdier
                new java.util.NoSuchElementException("a.length(" + n + ") < 2!");

        int m = 0;      // m er posisjonen til største verdi
        int nm = 1;     // nm er posisjonen til nest største verdi

        // bytter om m og nm hvis a[1] er større enn a[0]
        if (a[1] > a[0]) { m = 1; nm = 0; }

        int maksverdi = a[m];                // største verdi
        int nestmaksverdi = a[nm];           // nest største verdi

        for (int i = 2; i < n; i++)
        {
            if (a[i] > nestmaksverdi)
            {
                if (a[i] > maksverdi)
                {
                    nm = m;
                    nestmaksverdi = maksverdi;     // ny nest størst

                    m = i;
                    maksverdi = a[m];              // ny størst
                }
                else
                {
                    nm = i;
                    nestmaksverdi = a[nm];         // ny nest størst
                }
            }
        } // for

        return new int[] {m,nm};    // n i posisjon 0, nm i posisjon 1

    }

    public static boolean inneholdt(String s, String t){

        int lengsteOrd = 0;
        int kortesteOrd = 0;

        String kortesteString;

        if(s.length() > t.length()){
            lengsteOrd = s.length();
            kortesteOrd = t.length();
            kortesteString = t;
        }else{
            lengsteOrd = t.length();
            kortesteOrd = s.length();
            kortesteString = s;
        }

        int finnesTeller = s.length();

        if(s.isEmpty() && t.isEmpty()){
            return true;
        }

        if(t.isEmpty()){
            return false;
        }

        int funnetPos = 0;

        int duplikater = 0;

        //boolean finnes = false;

        int[] indekser = new int[lengsteOrd];

        int i = 0;

        if(s.charAt(0) == t.charAt(0)){
            indekser[0] = 0;
            finnesTeller--;
            i = 1;
        }else{
            i = 0;
        }

        for(; i < kortesteOrd; i++){
            for(int j = 0; j < lengsteOrd; j++){
                if(s.charAt(i) == t.charAt(j) && !alleredeFunnet(indekser,j)){
                    indekser[i] = j;
                    finnesTeller--;
                    break;
                }
            }
        }

        if(finnesTeller == 0){
            System.out.println("Finnes");
            return true;
        }else{
            System.out.println("NOPE");
            return false;
        }
    }

    public static boolean alleredeFunnet(int[] a, int indeks){
        for(int i = 0; i < a.length; i++){
            if(a[i] == indeks){
                return true;
            }
        }
        return false;
    }


}
