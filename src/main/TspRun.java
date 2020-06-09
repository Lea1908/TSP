package main;

import tsp.model.CityEntity;

import java.util.Random;

public class TspRun {

    //Array mit Stadt-Objekten
    public CityEntity[] cities;
    //Distanz zwischen zwei Staedten
    double dist_of_two;
    //Distanzumme der ganzen Rundreise
    double dist_sum;


    //Konstruktor
    public TspRun(CityEntity[] cities1)
    {
        cities = cities1;
    }

    //Berechnet die Distanz zwischen zwei Städten
    public double calcDistanceOfTwo(CityEntity city1, CityEntity city2)
    {


        dist_of_two = java.lang.Math.sqrt(((city1.getxCoordinate() - city2.getxCoordinate()) * (city1.getxCoordinate() - city2.getxCoordinate())) + ((city1.getyCoordinate() - city2.getyCoordinate()) * (city1.getyCoordinate() - city2.getyCoordinate())));

        return dist_of_two;
    }

    //Berechnet die Distanzumme der ganzen Rundreise
    public double calcDistanceSum(CityEntity[] cities)
    {
        double temp_dist = 0;
        int length = cities.length;
        dist_sum = 0;

        for(int i = 0; i < length - 1; i++)
        {
            temp_dist = java.lang.Math.sqrt(((cities[i].getxCoordinate() - cities[i + 1].getxCoordinate()) * (cities[i].getxCoordinate() - cities[i + 1].getxCoordinate())) +
                    ((cities[i].getyCoordinate() - cities[i + 1].getyCoordinate()) * (cities[i].getyCoordinate() - cities[i + 1].getyCoordinate())));
            dist_sum += temp_dist;
            //System.out.println("In Funktion: " + dist_sum);
        }

        return dist_sum;
    }

    public CityEntity[] shuffleArray(CityEntity[] cities)
    {
        Random rngsus = new Random();
        int randvar1 = rngsus.nextInt(cities.length);
        int randvar2 = rngsus.nextInt(cities.length);
        //System.out.println("Erste Variable: " + randvar1);
        //System.out.println("Zweite Variable: " + randvar2);

        CityEntity temp = cities[randvar1];
        cities[randvar1] = cities[randvar2];
        cities[randvar2] = temp;

        return cities;
    }



}
