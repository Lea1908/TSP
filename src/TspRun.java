import java.util.Random;
import java.util.Vector;

public class TspRun {

    //Array mit Stadt-Objekten
    public City[] cities;
    //Distanz zwischen zwei Staedten
    double dist_of_two;
    //Distanzumme der ganzen Rundreise
    double dist_sum;
    // Array mit den Städten in Array als Subsequence
    public City[][] cities_sub;


    //Konstruktor
    public TspRun(City[][] cities1)
    {
        cities_sub = cities1;
        System.out.println(cities_sub[0].length);
        System.out.println(cities_sub[0][0].city_name);

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
        cities = new City[help_city_vec.size()];
        for(int i=0; i < cities.length; i++)
        {
            cities[i] = (City) help_city_vec.get(i);
        }
    }

    //Berechnet die Distanz zwischen zwei Städten
    public double calcDistanceOfTwo(City city1, City city2)
    {


        dist_of_two = java.lang.Math.sqrt(((city1.x - city2.x) * (city1.x - city2.x)) + ((city1.y - city2.y) * (city1.y - city2.y)));

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
            temp_dist = java.lang.Math.sqrt(((cities[i].x - cities[i + 1].x) * (cities[i].x - cities[i + 1].x)) +
                    ((cities[i].y - cities[i + 1].y) * (cities[i].y - cities[i + 1].y)));
            dist_sum += temp_dist;
            //System.out.println("In Funktion: " + dist_sum);
        }

        return dist_sum;
    }

    private City[][] get_shuffeld_cities_sub(City[][] cities_sub_help){
        Random rngsus = new Random();
        int randvar1 = rngsus.nextInt(cities_sub_help.length);
        int randvar2 = rngsus.nextInt(cities_sub_help.length);
        //System.out.println("Erste Variable: " + randvar1);
        //System.out.println("Zweite Variable: " + randvar2);

        City[] temp = cities_sub_help[randvar1];
        cities_sub_help[randvar1] = cities_sub_help[randvar2];
        cities_sub_help[randvar2] = temp;
        return cities_sub_help;
    }

    public City[] shuffleArray(Boolean is_start_city_chosen, Boolean is_end_city_chosen)
    {
        if(is_start_city_chosen && !is_end_city_chosen){
            City[][] cities_sub_help = new City[cities_sub.length - 1][];
            for(int i=0; i < cities_sub_help.length; i++){
                cities_sub_help[i] = cities_sub[i + 1];
            }
            cities_sub_help = get_shuffeld_cities_sub(cities_sub_help);
            for(int i=1; i < cities_sub.length; i++){
                cities_sub[i] = cities_sub_help[i - 1];
            }
        }
        else if(is_end_city_chosen && !is_start_city_chosen){
            City[][] cities_sub_help = new City[cities_sub.length - 1][];
            for(int i=0; i < cities_sub_help.length; i++){
                cities_sub_help[i] = cities_sub[i];
            }
            cities_sub_help = get_shuffeld_cities_sub(cities_sub_help);
            for(int i=0; i < cities_sub.length - 1; i++){
                cities_sub[i] = cities_sub_help[i];
            }
        }
        else if(is_start_city_chosen && is_end_city_chosen){
            City[][] cities_sub_help = new City[cities_sub.length - 2][];
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
