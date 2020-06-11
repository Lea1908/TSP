package main;

import EntityManager.CityManager;
import EntityManager.RoundtripEntityManager;
import EntityManager.TspEntityManager;
import tsp.model.CityEntity;
import tsp.model.TspEntity;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class TSP extends TspEntity {
    List<CityEntity> cities;
    CityEntity start_city;
    CityEntity end_city;
    List<String> subsequences;
    Result tsp_result;

    public TSP() { }
    public TSP(String name) {
        this.setName(name);
    }
    public TSP(String name, List<CityEntity> cities) {
        this.setName(name);
        this.cities = cities;
    }
    public TSP(String name, List<CityEntity> cities, List<String> subsequences) {
        this.setName(name);
        this.cities = cities;
        this.subsequences = subsequences;
    }
    public TSP(String name, List<CityEntity> cities, List<String> subsequences, Result result) {
        this.setName(name);
        this.cities = cities;
        this.subsequences = subsequences;
        this.tsp_result = result;
    }
    public TSP(int id, String name, List<CityEntity> cities, CityEntity start_city, CityEntity end_city, List<String> subsequences, Result result, Double maxDuration) {
        this.setId(id);
        this.setName(name);
        this.cities = cities;
        this.start_city = start_city;
        this.end_city = end_city;
        this.subsequences = subsequences;
        this.tsp_result = result;
        if (maxDuration != null) {
            this.setMaxDuration(maxDuration);
        }
    }
    public void setCities(List<CityEntity> cities) {
        this.cities = cities;
    }

    public List<CityEntity> getCities() {
        return cities;
    }

    public CityEntity getStart_city() {
        return start_city;
    }

    public void setStart_city(CityEntity start_city) {
        this.start_city = start_city;
    }

    public CityEntity getEnd_city() {
        return end_city;
    }

    public void setEnd_city(CityEntity end_city) {
        this.end_city = end_city;
    }

    public void setSubsequences(List<String> subsequences) {
        this.subsequences = subsequences;
    }

    public List<String> getSubsequences() {
        return subsequences;
    }

    public Result getTsp_result() {
        return tsp_result;
    }

    public void setTsp_result(Result tsp_result) {
        this.tsp_result = tsp_result;
    }

    public Boolean deleteCityFromList(String name) {
        CityEntity city = CityEntity.findCityByName(this.cities, name);
        if (city != null) {
            this.cities.remove(city);
            return true;
        }
        return false;
    }

    public Integer CreateTSP() {
        Integer startCityId = null;
        Integer endCityId = null;
        List<Integer> cityIds = new ArrayList<Integer>();
        CityManager cityManager = new CityManager();

        // create tsp
        TspEntityManager tspEntityManager = new TspEntityManager();
        Integer tspId = tspEntityManager.create(getMaxDuration(), getName());
        // name already exists
        if (tspId == -1) {
            return tspId;
        }
        var resultCities = getTsp_result().best_tour;

        // create all cities or get existing ids in best order
        for (CityEntity city : resultCities) {
            cityIds.add(cityManager.findExistingOrCreateNewCity(city));
        }
        // create start and end city
        if (this.start_city != null) {
            startCityId = cityManager.findExistingOrCreateNewCity(this.start_city);
            tspEntityManager.createTspCity(startCityId, tspId, "start");
        }
        if (this.end_city != null) {
            endCityId = cityManager.findExistingOrCreateNewCity(this.end_city);
            tspEntityManager.createTspCity(endCityId, tspId, "end");
        }

        // TODO create subsequences


        // create roundtrip
        RoundtripEntityManager roundtripEntityManager = new RoundtripEntityManager();
        java.sql.Timestamp timestamp = new Timestamp(System.currentTimeMillis());

        // save cities in roundTripCity table in best order
        Integer roundtripEntityId = roundtripEntityManager.create(timestamp, getName() + "_run", tsp_result.getOpt_tour_len(), tspId, cityIds);
        tspEntityManager.createTspRoundtrip(tspId, roundtripEntityId);

        return tspId;
    }

}
