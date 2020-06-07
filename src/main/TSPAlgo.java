package main;

public class TSPAlgo {
    public static void main(String[] args) {
        System.out.println("TSP easy solved.");

        ////////////TESTSTAEDTE, WIRD DURCH INPUT ERSETZT////////////

        City city0 = new City(576,4, "Graz");
        City city1 = new City(594,432, "Wien");
        City city2 = new City(598, 9, "Salzburg");
        City city3 = new City(46, 777, "Innsbruck");
        City city4 = new City(181, 253, "Klagenfurt");
        City city5 = new City(35, 596, "Bregenz");
        City city6 = new City(817, 66, "St. Pölten");
        City city7 = new City(434, 746, "Eisenstadt");
        City city8 = new City(152, 989, "Linz");
        int cities_length = 9;

        City[] cities = new City[cities_length];

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



        call_tsp(cities);
    }

    static public Result call_tsp(City[] cities){
        //Erstes TSP-Objekt wird erstellt
        TspRun firstrun = new TspRun(cities);


        //Länge der berechneten Route
        double sum = 0;
        //Zwischenergebnis des bisher besten Ergebnisses
        double optimum;
        //Laufvariable, welcher Durchgang das bisher beste Ergebnis gefunden hat
        long best_run = 0;
        //citylength
        int cities_length = cities.length;
        //Zwischenergebnis der Anordnung der Staedte des bisher besten Ergebnisses
        City[] best_route = new City[cities_length];


        //Erster Durchgang, Funktion wird aufgerufen und Distanz berechnet, Array unverändert
        sum = firstrun.calcDistanceSum(cities);
        optimum = sum;

        //Berechnung läuft von
        long startTime = System.currentTimeMillis();

        //Berechnungsdauer in Sekunden
        int duration = 20;

        //Berechnung läuft bis
        long endTime = startTime + (duration * 1000);
        //Durchgangsnummer
        long i = 0;


        while(System.currentTimeMillis() < endTime)
        {
            firstrun.shuffleArray(cities);
            sum = firstrun.calcDistanceSum(cities);
            System.out.println("Durchgang Nummer " + i + ": " + sum);
            i++;
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
