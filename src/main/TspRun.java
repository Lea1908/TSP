package main;

import tsp.model.CityEntity;

import java.util.Random;
import java.util.Vector;

public class TspRun {

    //Array mit Stadt-Objekten
    public CityEntity[] cities;
    //Distanz zwischen zwei Staedten
    double dist_of_two;
    //Distanzumme der ganzen Rundreise
    double dist_sum;
    // Array mit den Städten in Array als Subsequence
    public CityEntity[][] cities_sub;


    //Konstruktor
    public TspRun(CityEntity[][] cities1)
    {
        cities_sub = cities1;
        System.out.println(cities_sub[0].length);
        System.out.println(cities_sub[0][0].getName());

        get_cities_array_from_cities_sub_array();
    }

    public void get_cities_array_from_cities_sub_array(){
        Vector help_city_vec = new Vector();
        // TODO Code can be optimized
        for(int i=0; i < cities_sub.length; i++){
            for(int j=0; j < cities_sub[i].length; j++){
                help_city_vec.add(cities_sub[i][j]);
            }
        }
        cities = new CityEntity[help_city_vec.size()];
        for(int i=0; i < cities.length; i++)
        {
            cities[i] = (CityEntity) help_city_vec.get(i);
        }
    }

    //Berechnet die Distanz zwischen zwei Städten
    public double calcDistanceOfTwo(CityEntity city1, CityEntity city2)
    {


        dist_of_two = java.lang.Math.sqrt(((city1.getxCoordinate() - city2.getxCoordinate()) * (city1.getxCoordinate() - city2.getxCoordinate())) + ((city1.getyCoordinate() - city2.getyCoordinate()) * (city1.getyCoordinate() - city2.getyCoordinate())));

        return dist_of_two;
    }

    //Berechnet die Distanzumme der ganzen Rundreise
    public double calcDistanceSum()
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

    private CityEntity[][] get_shuffeld_cities_sub(CityEntity[][] cities_sub_help){
        Random rngsus = new Random();
        int randvar1 = rngsus.nextInt(cities_sub_help.length);
        int randvar2 = rngsus.nextInt(cities_sub_help.length);
        //System.out.println("Erste Variable: " + randvar1);
        //System.out.println("Zweite Variable: " + randvar2);

        CityEntity[] temp = cities_sub_help[randvar1];
        cities_sub_help[randvar1] = cities_sub_help[randvar2];
        cities_sub_help[randvar2] = temp;
        return cities_sub_help;
    }

    public CityEntity[] shuffleArray(Boolean is_start_city_chosen, Boolean is_end_city_chosen)
    {
        if(is_start_city_chosen && !is_end_city_chosen){
            CityEntity[][] cities_sub_help = new CityEntity[cities_sub.length - 1][];
            for(int i=0; i < cities_sub_help.length; i++){
                cities_sub_help[i] = cities_sub[i + 1];
            }
            cities_sub_help = get_shuffeld_cities_sub(cities_sub_help);
            for(int i=1; i < cities_sub.length; i++){
                cities_sub[i] = cities_sub_help[i - 1];
            }
        }
        else if(is_end_city_chosen && !is_start_city_chosen){
            CityEntity[][] cities_sub_help = new CityEntity[cities_sub.length - 1][];
            for(int i=0; i < cities_sub_help.length; i++){
                cities_sub_help[i] = cities_sub[i];
            }
            cities_sub_help = get_shuffeld_cities_sub(cities_sub_help);
            for(int i=0; i < cities_sub.length - 1; i++){
                cities_sub[i] = cities_sub_help[i];
            }
        }
        else if(is_start_city_chosen && is_end_city_chosen){
            CityEntity[][] cities_sub_help = new CityEntity[cities_sub.length - 2][];
            for(int i=0; i < cities_sub_help.length; i++){
                cities_sub_help[i] = cities_sub[i + 1];
            }
            cities_sub_help = get_shuffeld_cities_sub(cities_sub_help);
            for(int i=1; i < cities_sub.length - 1; i++){
                cities_sub[i] = cities_sub_help[i - 1];
            }
        }
        else{
            cities_sub = get_shuffeld_cities_sub(cities_sub);
        }

        get_cities_array_from_cities_sub_array();

        return cities;
    }



}
