package main;

public class Result {
    City[] best_tour;
    double opt_tour_len;

    Result(City[] best_tour, double opt_tour_len){
        this.best_tour = best_tour;
        this.opt_tour_len = opt_tour_len;
    }

    public City[] getBest_tour(){
        return this.best_tour;
    }

    public double getOpt_tour_len(){
        return this.opt_tour_len;
    }

    public void setBest_tour(City[] best_tour){
        this.best_tour = best_tour;
    }

    public void setOpt_tour_len(double opt_tour_len){
        this.opt_tour_len = opt_tour_len;
    }
}
