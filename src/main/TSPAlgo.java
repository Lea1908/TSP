package main;

import tsp.model.CityEntity;

public class TSPAlgo {
    public static void main(String[] args) {
        System.out.println("TSP easy solved.");

        ////////////TESTSTAEDTE, WIRD DURCH INPUT ERSETZT////////////

        CityEntity city0 = new CityEntity(576,4, "Graz");
        CityEntity city1 = new CityEntity(594,432, "Wien");
        CityEntity city2 = new CityEntity(598, 9, "Salzburg");
        CityEntity city3 = new CityEntity(46, 777, "Innsbruck");
        CityEntity city4 = new CityEntity(181, 253, "Klagenfurt");
        CityEntity city5 = new CityEntity(35, 596, "Bregenz");
        CityEntity city6 = new CityEntity(817, 66, "St. Pölten");
        CityEntity city7 = new CityEntity(434, 746, "Eisenstadt");
        CityEntity city8 = new CityEntity(152, 989, "Linz");
        int cities_length = 9;

        CityEntity[] cities = new CityEntity[cities_length];

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



        //call_tsp(cities);
    }

    static public Result call_tsp(CityEntity[][] cities, Boolean is_start_city_chosen, Boolean is_end_city_chosen, Integer demandedDuration){
        //Erstes TSP-Objekt wird erstellt
        TspRun firstrun = new TspRun(cities);


        //Länge der berechneten Route
        double sum = 0;
        //Zwischenergebnis des bisher besten Ergebnisses
        double optimum;
        //Laufvariable, welcher Durchgang das bisher beste Ergebnis gefunden hat
        long best_run = 0;
        //citylength
        int cities_length = firstrun.cities.length;
        //Zwischenergebnis der Anordnung der Staedte des bisher besten Ergebnisses
        //City[] best_route = new City[cities_length];
        CityEntity[] best_route = firstrun.cities;


        //Erster Durchgang, Funktion wird aufgerufen und Distanz berechnet, Array unverändert
        sum = firstrun.calcDistanceSum();
        optimum = sum;

        //Berechnung läuft von
        long startTime = System.currentTimeMillis();

        //Berechnungsdauer in Sekunden
        int duration = demandedDuration != null ? demandedDuration : 20;
        //int duration = 5;

        //Berechnung läuft bis
        long endTime = startTime + (duration * 1000);
        //Durchgangsnummer
        long i = 0;


        while(System.currentTimeMillis() < endTime)
        {
            firstrun.shuffleArray(is_start_city_chosen, is_end_city_chosen);
            sum = firstrun.calcDistanceSum();
            //System.out.println("Durchgang Nummer " + i + ": " + sum);
            i++;
            if(sum < optimum)
            {
                optimum = sum;
                best_run = i;
                for (int k = 0; k < cities_length; k++)
                {
                    best_route[k] = firstrun.cities[k];
                }
            }

        }

        //Ausgabe des besten Ergebnis
        //System.out.println("Beste Lösung aus Durchgang Nummer " + best_run + ": " + optimum);

        //Ausgabe der Reihenfolge der Staedte im besten Ergebnis
        //for (int j = 0; j < cities_length; j++)
        //{
            //System.out.println(best_route[j].city_name);
        //}




        Result result = new Result(best_route, optimum);
        return result;

    }

}
