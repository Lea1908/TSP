import java.util.Arrays;
import java.util.Collections;

public class TSPAlgo {
    public static void main(String[] args) {
        System.out.println("TSP easy solved.");

        ////////////TESTSTAEDTE, WIRD DURCH INPUT ERSETZT////////////

        city city0 = new city(576,4, "Graz");
        city city1 = new city(594,432, "Wien");
        city city2 = new city(598, 9, "Salzburg");
        city city3 = new city(46, 777, "Innsbruck");
        city city4 = new city(181, 253, "Klagenfurt");
        city city5 = new city(35, 596, "Bregenz");
        city city6 = new city(817, 66, "St. Pölten");
        city city7 = new city(434, 746, "Eisenstadt");
        city city8 = new city(152, 989, "Linz");
        int cities_length = 9;

        city [] cities = new city[cities_length];

        cities[0] = city0;
        cities[1] = city1;
        cities[2] = city2;
        cities[3] = city3;
        cities[4] = city4;
        cities[5] = city5;
        cities[6] = city6;
        cities[7] = city7;
        cities[8] = city8;


        ////////////TESTSTAEDTE, WIRD DURCH INPUT ERSETZT////////////



        //Erstes TSP-Objekt wird erstellt
        TspRun firstrun = new TspRun(cities);


        //Länge der berechneten Route
        double sum = 0;
        //Zwischenergebnis des bisher besten Ergebnisses
        double optimum;
        //Laufvariable, welcher Durchgang das bisher beste Ergebnis gefunden hat
        int best_run = 0;
        //Zwischenergebnis der Anordnung der Staedte des bisher besten Ergebnisses
        city [] best_route = new city[cities_length];

        //Erster Durchgang, Funktion wird aufgerufen und Distanz berechnet, Array unverändert
        sum = firstrun.calcDistanceSum(cities);
        optimum = sum;


        for(int i = 1; i < 5000; i++)
        {
            sum = 0;
            firstrun.shuffleArray(cities);
            sum = firstrun.calcDistanceSum(cities);
            System.out.println("Durchgang Nummer " + i + ": " + sum);
            if(sum < optimum)
            {
                optimum = sum;
                best_run = i;
                for (int k = 0; k < cities_length; k++)
                {
                    best_route[k] = cities[k];
                }
            }

        }

        //Ausgabe des besten Ergebnis
        System.out.println("Beste Lösung aus Durchgang Nummer " + best_run + ": " + optimum);

        //Ausgabe der Reihenfolge der Staedte im besten Ergebnis
        for (int j = 0; j < cities_length; j++)
        {
            System.out.println(best_route[j].city_name);
        }

    }
}
