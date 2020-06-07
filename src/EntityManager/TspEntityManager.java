package EntityManager;

import main.TSP;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import tsp.model.CityEntity;
import tsp.model.TspEntity;
import tsp.model.TspRoundtripsEntity;

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
                    "where tspRoundtrip.tsp_id=" + tspEntity.getId();

            /*var tspRoundtripQuery = "select roudtrip_id from TspRoundtripsEntity where tsp_id=" + tsp.getId();
            Query query = session.createQuery(tspRoundtripQuery);
            List<Integer> tspRoundtripsEntities = (List<Integer>) query.getResultList();
            var roundtripQuery = "from RoundtripEntity where id IN:ids";
            Query roundtripQ = session.createQuery(roundtripQuery);
            roundtripQ.setParameter("id", tspRoundtripsEntities);*/

            Query query = session.createQuery(cityQuery);
            List<CityEntity> cities = (List<CityEntity>) query.list();
            // todo add result

            // todo add statistics
            
            tsp = new TSP(tspEntity.getName(), cities);
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
}
