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


        if(a.length < 0){
            return 0;
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

    public static int antallUlikeSortert2(int[] a){
        //Sjekk om tabell er sortert
        //return 0 hvis tabell er tom
        //Traverser
        //Sjekk om første taller er lik andre, bruk array til å telle opp ulike

        //1 2 2 3
        // får til sammen 3 ulike tall

        if(a.length < 0){
            return 0;
        }

        int teller = 1;

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
        return true;
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
}
