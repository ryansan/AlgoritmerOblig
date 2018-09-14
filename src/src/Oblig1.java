import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Random;

public class Oblig1 {

    // Naar blir det flest ombyttinger?
    // Det blir flest ombyttinger dersom det hoyeste tallet ligger forst i tabellen.
    //
    // Naar blir det faerrest ombyttinger?
    // Det blir faerrest hvis den er sortert etter stigende rekkefolge, da blir
    // det ingen ombyttinger.
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

    // Hvor mange blir det i gjennomsnitt?
    // I gjennomsnitt blir ombyttinger lik n-Hn der n er
    // antall elementer og h(n) er det harmoniske tallet log(n)+5.77
    // Etter aa ha testet med hoye tall som 10.000 og 100.000, ser
    // jeg at jeg i gjennomsnitt blir naermere n, jo hoyere n er.
    // Maks metoden: n=100000: 100000-log(100000) + 0.5777 = 99995.577
    // Gamle maks-metoden: n=100.000: log(n) – 0,423 = 11,1
    // Paa grunnlag av dette kan vi si at de tidligere maks-metodene er mer effektive
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
                //deler ogsaa opp tabellen slik at man faar oddetall paa venstre side
                //og partall på hoyre side
                bytt(a,oddeTallTeller,i);
                oddeTallTeller++;
            }
        }

        //hvis det kun er partall eller oddetall, sorter hele tabellen, hvis ikke, utfor quicksort på hver side
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

        for(; i < lengste.length(); i++){
            sb.append(lengste.charAt(i));
        }

        return sb.toString();
    }

    public static String flett(String... s){

        if(s.length <= 0){
            return "";
        }

        String lengste = s[0];

        StringBuilder sb = new StringBuilder();

        for (String temp: s
             ) {
            if(temp.length() > lengste.length()){
                lengste = temp;
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
        if (n < 3) throw      // må ha minst tre verdier
                new java.util.NoSuchElementException("Maa ha minst tre verdier i tabellen!");

        int[] hjelpeTabell = new int[]{a[0],a[1],a[2]};
        hjelpeTabell = indekssortering(hjelpeTabell);

        int m = hjelpeTabell[0];              // m er posisjonen til minste verdi
        int nm = hjelpeTabell[1];             // nm er posisjonen til nest minste verdi
        int tnm = hjelpeTabell[2];            // tnm er posisjonen til tredje minste verdi

        int minsteverdi = a[m];                // minste verdi
        int nestminstverdi = a[nm];            // nest minste verdi
        int tredjeverdi = a[tnm];              // tredje minste verdi

        //starter på 3(4) element
        for (int i = 3; i < n; i++) {
            if (a[i] < minsteverdi) {
                tnm = nm;
                tredjeverdi = nestminstverdi;     // ny tredje minst

                nm = m;
                nestminstverdi = minsteverdi;     // ny nest minst

                m = i;
                minsteverdi = a[m];               // ny minst
            }

            else if(a[i] < nestminstverdi) {
                tnm = nm;
                tredjeverdi = nestminstverdi;    // ny tredjeminst

                nm = i;
                nestminstverdi = a[nm];         // ny nest minst
            }
            else if(a[i] < tredjeverdi) {
                tnm = i;
                tredjeverdi = a[i];    // ny tredjeminst
            }

        }// for

        return new int[] {m,nm,tnm};    // n i posisjon 0, nm i posisjon 1, tnm i posisjon 2
    }


    public static boolean inneholdt(String a, String b){

        if(a.length() == 0 && b.length() == 0) {
            return true;
        } else if(a.length() > 0 && b.length() == 0) {
            return false;
        }


        char[] alfabet = new char[29];
        int tellerForASCIIVerdi = (int) 'A';

        int[] antallBokstaverArray = new int[29];

        //Populerer alfabet tabellen
        for(int i = 0; i < alfabet.length-3;i++){
            alfabet[i] = (char) tellerForASCIIVerdi;
            tellerForASCIIVerdi++;
        }

        //LEGG TIL ÆØÅ
        alfabet[26] = (int) 'Æ';
        alfabet[27] = (int) 'Ø';
        alfabet[28] = (int) 'Å';

        //Tell opp antallet av hver bokstav i String a
        for(int i = 0; i < a.length(); i++){
            for(int j = 0; j < alfabet.length; j++){
                if(a.charAt(i) == alfabet[j]){
                    antallBokstaverArray[j]++;
                }
            }
        }

        //Tell ned antallet av hver bokstav i String b mot antallet
        //i hver indeks i antallBokstavArray
        for(int i = 0; i < b.length(); i++){
            for(int j = 0; j < alfabet.length; j++){
                if(b.charAt(i) == alfabet[j]){
                    antallBokstaverArray[j]--;
                }
            }
        }


        //Sjekker om det fortsatt finnes tall i antallBokstavArray
        //hvis det finnes, return false
        for(int i = 0; i < antallBokstaverArray.length; i++){
            if(antallBokstaverArray[i] > 0){
                return false;
            }
        }

        return true;
    }

}
