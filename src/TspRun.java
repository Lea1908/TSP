import java.util.Random;

public class TspRun {

    //Array mit Stadt-Objekten
    public City[] cities;
    //Distanz zwischen zwei Staedten
    double dist_of_two;
    //Distanzumme der ganzen Rundreise
    double dist_sum;


    //Konstruktor
    public TspRun(City[] cities1)
    {
        cities = cities1;
    }

    //Berechnet die Distanz zwischen zwei Städten
    public double calcDistanceOfTwo(City city1, City city2)
    {


        dist_of_two = java.lang.Math.sqrt(((city1.x - city2.x) * (city1.x - city2.x)) + ((city1.y - city2.y) * (city1.y - city2.y)));

        return dist_of_two;
    }

    //Berechnet die Distanzumme der ganzen Rundreise
    public double calcDistanceSum(City[] cities)
    {
        double temp_dist = 0;
        int length = cities.length;
        dist_sum = 0;

        for(int i = 0; i < length - 1; i++)
        {
            temp_dist = java.lang.Math.sqrt(((cities[i].x - cities[i + 1].x) * (cities[i].x - cities[i + 1].x)) +
                    ((cities[i].y - cities[i + 1].y) * (cities[i].y - cities[i + 1].y)));
            dist_sum += temp_dist;
            //System.out.println("In Funktion: " + dist_sum);
        }

        return dist_sum;
    }

    public City[] shuffleArray(City[] cities)
    {
        Random rngsus = new Random();
        int randvar1 = rngsus.nextInt(cities.length);
        int randvar2 = rngsus.nextInt(cities.length);
        //System.out.println("Erste Variable: " + randvar1);
        //System.out.println("Zweite Variable: " + randvar2);

        City temp = cities[randvar1];
        cities[randvar1] = cities[randvar2];
        cities[randvar2] = temp;

        return cities;
    }



}
