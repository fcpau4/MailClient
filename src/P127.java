/**
 * Created by dremon on 08/02/16.
 */


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;


/**
 * Created by 47419119l on 04/02/16.
 */
public class P127 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String numero = sc.nextLine();
        String[] array = new String[Integer.parseInt(numero)];
        String[] results = new String[Integer.parseInt(numero)];

        for (int x = 0; x < array.length; x++) {
            array[x] = sc.nextLine();
            results[x] = calc(array[x]);
        }
        print(results);
    }

    public static String calc(String linea) {
        Integer camas;
        Integer songTimes;
        String[] merda = linea.split(" ");

        camas = Integer.parseInt(merda[merda.length - 2]);
        songTimes = Integer.parseInt(merda[merda.length - 1]);
        String[] peregrinos = new String[merda.length - 3];

        for (int x = 0; x < peregrinos.length; x++) {
            peregrinos[x] = merda[x];
        }
        if (camas == 0) {
            return "NADIE TIENE CAMA";
        }
        if (peregrinos.length <= camas) {
            return "TODOS TIENEN CAMA";
        }

        Integer posicio_incial = 0;
        Integer posicio_final = 0;

        ArrayList<String> peregrinosList = new ArrayList<>(Arrays.asList(peregrinos));

        while (peregrinosList.size() > camas) {
            posicio_final = (posicio_incial + songTimes-1) % peregrinosList.size();
            peregrinosList.remove(posicio_final%peregrinosList.size());
            posicio_incial = posicio_final%(peregrinosList.size());
        }
        String nombres = "";

        String[] peregrinosaux = new String[peregrinosList.size()];

        for (int i = 0; i < peregrinosList.size(); i++) {
            peregrinosaux[i] = peregrinosList.get(i);
        }

        for (int x = 0; x < peregrinosaux.length; x++) {
            if (peregrinosaux[x].equals("")) {
            } else {
                if (x == peregrinosaux.length -1){
                    nombres = nombres + peregrinosaux[x];
                }else {
                    nombres = nombres + peregrinosaux[x] + " ";
                }
            }
        }
        return nombres;
    }

    public static void print(String[] results) {
        for (int x = 0; x < results.length; x++) {
            System.out.println(results[x]);
        }
    }
}