package main;

import tsp.model.CityEntity;

public class Result {
    CityEntity[] best_tour;
    double opt_tour_len;

    public Result(CityEntity[] best_tour, double opt_tour_len){
        this.best_tour = best_tour;
        this.opt_tour_len = opt_tour_len;
    }

    public CityEntity[] getBest_tour(){
        return this.best_tour;
    }

    public double getOpt_tour_len(){
        return this.opt_tour_len;
    }

    public void setBest_tour(CityEntity[] best_tour){
        this.best_tour = best_tour;
    }

    public void setOpt_tour_len(double opt_tour_len){
        this.opt_tour_len = opt_tour_len;
    }
}
