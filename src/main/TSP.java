package main;

import EntityManager.CityManager;
import EntityManager.RoundtripEntityManager;
import EntityManager.SubsequenceEntityManager;
import EntityManager.TspEntityManager;
import tsp.model.CityEntity;
import tsp.model.TspEntity;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;

public class TSP extends TspEntity {
    List<CityEntity> cities;
    CityEntity start_city;
    CityEntity end_city;
    CityEntity[][] subsequences;
    Result tsp_result;

    public TSP() { }
    public TSP(String name) {
        this.setName(name);
    }
    public TSP(String name, List<CityEntity> cities) {
        this.setName(name);
        this.cities = cities;
    }
    public TSP(String name, List<CityEntity> cities, CityEntity[][] subsequences) {
        this.setName(name);
        this.cities = cities;
        this.subsequences = subsequences;
    }
    public TSP(String name, List<CityEntity> cities, CityEntity[][] subsequences, Result result) {
        this.setName(name);
        this.cities = cities;
        this.subsequences = subsequences;
        this.tsp_result = result;
    }
    public TSP(int id, String name, List<CityEntity> cities, CityEntity start_city, CityEntity end_city, CityEntity[][] subsequences, Result result, Double maxDuration) {
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

    public void setSubsequences(CityEntity[][] subsequences) {
        this.subsequences = subsequences;
    }

    public CityEntity[][] getSubsequences() {
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
        // Initializing a Dictionary
        Dictionary cityDict = new Hashtable();
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
            var cityId = cityManager.findExistingOrCreateNewCity(city);
            cityIds.add(cityId);
            cityDict.put(city.getName(), cityId);
            // create start and end city
            if (this.start_city != null && this.start_city.equals(city)) {
                tspEntityManager.createTspCity(cityId, tspId, "start");
            }
            if (this.end_city != null && this.end_city.equals(city)) {
                tspEntityManager.createTspCity(cityId, tspId, "end");
            }
        }

        // TODO create subsequences
        if (subsequences != null && subsequences.length != 0) {
            SubsequenceEntityManager subsequenceEntityManager = new SubsequenceEntityManager();
            List<String> subsequenceOrders = new ArrayList<>();
            for (CityEntity[] subsequence : subsequences) {
                StringBuilder subsequenceOrder = new StringBuilder();
                for (CityEntity cityEntity : subsequence) {
                    var id = cityDict.get(cityEntity.getName());
                    subsequenceOrder.append(id);
                    subsequenceOrder.append(",");
                }
                subsequenceOrders.add(subsequenceOrder.toString());
            }
            subsequenceEntityManager.createNewSubsequence(subsequenceOrders, tspId);

        }

        // create roundtrip
        RoundtripEntityManager roundtripEntityManager = new RoundtripEntityManager();
        java.sql.Timestamp timestamp = new Timestamp(System.currentTimeMillis());

        // save cities in roundTripCity table in best order
        Integer roundtripEntityId = roundtripEntityManager.create(timestamp, getName() + "_run", tsp_result.getOpt_tour_len(), tspId, cityIds);
        tspEntityManager.createTspRoundtrip(tspId, roundtripEntityId);

        return tspId;
    }

}
