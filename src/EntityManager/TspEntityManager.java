package EntityManager;

import main.Result;
import main.TSP;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import tsp.model.*;

import java.util.List;
import java.util.Vector;

public class TspEntityManager extends EntityManager{
    /* Method to CREATE a tsp in the database */
    public Integer create(Double maxDuration, String name){
        if (name == null) {
            return -1;
        }
        Session session = factory.openSession();
        Transaction tx = null;
        Integer tspId = null;
        try {
            tx = session.beginTransaction();
            String queryString = "from TspEntity where name='" + name + "'";
            Query query = session.createQuery(queryString);
            TspEntity existingTspEntity = (TspEntity) query.uniqueResult();
            // return error if tsp with given name already exists - TODO maybe ask if tsp should be updated
            if (existingTspEntity != null) {
                return  -1;
            }

            TspEntity te = new TspEntity();
            if (maxDuration != null) {
                te.setMaxDuration(maxDuration);
            }
            te.setName(name);
            tspId = (Integer) session.save(te);
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return tspId;
    }
    public TSP loadTSP(TspEntity tspEntity) {
        if (tspEntity == null) {
            return null;
        }
        Session session = factory.openSession();
        Transaction tx = null;
        TSP tsp = null;
        var tspId = tspEntity.getId();
        try {
            tx = session.beginTransaction();

            // get all cities
            var cityQuery = "select city from CityEntity city " +
                    "inner join RoundtripCitiesEntity roundtripcities " +
                    "on city.id = roundtripcities.cityId " +
                    "inner join RoundtripEntity roundtrip " +
                    "on roundtripcities.roundtripId = roundtrip.id " +
                    "inner join TspRoundtripsEntity tspRoundtrip " +
                    "on roundtrip.id = tspRoundtrip.roudtrip_id " +
                    "where tspRoundtrip.tsp_id=" + tspId;
            Query query = session.createQuery(cityQuery);
            var cities = (List<CityEntity>) query.list();

            // get roundtrip
            var tspRoundtripQuery = "select roundtrip from RoundtripEntity roundtrip " +
                    "inner join TspRoundtripsEntity tspRoundtripsEntity " +
                    "on roundtrip.id = tspRoundtripsEntity.roudtrip_id " +
                    "where tspRoundtripsEntity.tsp_id=" + tspId;
            query = session.createQuery(tspRoundtripQuery);
            RoundtripEntity roundtrip = (RoundtripEntity) query.uniqueResult();
            CityEntity[] cityEntities = new CityEntity[cities.size()];
            cities.toArray(cityEntities);

            // get start & endcity
            var startCityQuery = "select city from CityEntity city " +
                    "inner join TspStartCityEntity tspStartCity " +
                    "on city.id = tspStartCity.city_id " +
                    "where tspStartCity.tsp_id=" + tspId;
            query = session.createQuery(startCityQuery);
            var startCity = (CityEntity) query.uniqueResult();
            var endCityQuery ="select city from CityEntity city " +
                    "inner join TspEndCityEntity tspEndCity " +
                    "on city.id = tspEndCity.city_id " +
                    "where tspEndCity.tsp_id=" + tspId;
            query = session.createQuery(endCityQuery);
            var endCity = (CityEntity) query.uniqueResult();

            // get subsequences
            var subsequenceQuery = "select subsequenceOrder from SubsequenceOrderEntity  subsequenceOrder " +
                    "inner join SubsequenceEntity subsequence " +
                    "on subsequenceOrder.subsequenceId = subsequence.id " +
                    "where subsequence.tspId=" + tspId;
            query = session.createQuery(subsequenceQuery);
            List<SubsequenceOrderEntity> subsequences = (List<SubsequenceOrderEntity>) query.list();
            // todo pass subsequences on in correct way

            // create result
            var result = new Result(cityEntities, roundtrip.getDistance());

            tsp = new TSP(tspEntity.getName(), cities, startCity, endCity, null, result);
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return tsp;
    }
    public Vector<String> listAllTSPNames() {
        List<TspEntity> tsps = listAllTSPs();
        return listAllTSPNames(tsps);
    }
    public Vector<String> listAllTSPNames(List<TspEntity> tsps) {
        Vector<String> tspNames = new Vector<String>();
        for (TspEntity tsp : tsps) {
            tspNames.add(tsp.getName());
        }
        return tspNames;
    }
    public List<TspEntity> listAllTSPs() {
        Session session = factory.openSession();
        Transaction tx = null;
        List<TspEntity> tsps = null;

        try {
            tx = session.beginTransaction();
            tsps = loadAllData(TspEntity.class, session);
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return tsps;
    }

    public Integer updateTsp(TspEntity tspEntity){
        Session session = factory.openSession();
        Transaction tx = null;
        Integer tspId = null;

        try {
            tx = session.beginTransaction();
            TspEntity existingTsp = session.find(TspEntity.class, tspEntity.getId());
            if (existingTsp != null) {
                existingTsp = tspEntity;
                session.update(existingTsp);
            }
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return tspId;
    }

    public Integer createTspRoundtrip(Integer tspId, Integer roundtripId) {
        if (tspId == null || roundtripId == null) {
            return null;
        }
        Session session = factory.openSession();
        Transaction tx = null;
        Integer tspRoundtripId = null;

        try {
            tx = session.beginTransaction();
            TspRoundtripsEntity tspRoundtripsEntity = new TspRoundtripsEntity(tspId, roundtripId);
            var result = (TspRoundtripsEntity) session.save(tspRoundtripsEntity);
            tspRoundtripId = result.getId();
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return tspRoundtripId;
    }

    public Integer createTspCity(int cityId, int tspId, String type) {
        Session session = factory.openSession();
        Transaction tx = null;
        Integer id = null;

        try {
            tx = session.beginTransaction();
            if (type == "start") {
                id = createTspStartCity(cityId, tspId, session);
            } else {
                id = createTspEndCity(cityId, tspId, session);
            }
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return id;
    }

    private Integer createTspStartCity(int cityId, int tspId, Session session) {
        TspStartCityEntity tspStartCityEntity = new TspStartCityEntity(tspId, cityId);
        var result = (TspStartCityEntity) session.save(tspStartCityEntity);
        return result.getId();
    }
    private Integer createTspEndCity(int cityId, int tspId, Session session) {
        TspEndCityEntity tspEndCityEntity = new TspEndCityEntity(tspId, cityId);
        var result = (TspEndCityEntity) session.save(tspEndCityEntity);
        return result.getId();
    }
}
