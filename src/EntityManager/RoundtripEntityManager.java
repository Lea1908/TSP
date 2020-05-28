package EntityManager;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import tsp.model.RoundtripCitiesEntity;
import tsp.model.RoundtripEntity;

import java.sql.Timestamp;
import java.util.Iterator;
import java.util.List;

public class RoundtripEntityManager extends EntityManager{

    /* Method to CREATE a roundtrip in the database */
    public Integer create(Timestamp createDate, String name, double distance){
        Session session = factory.openSession();
        Transaction tx = null;
        Integer roundtripId = null;

        try {
            tx = session.beginTransaction();
            RoundtripEntity rte = new RoundtripEntity();
            rte.setMembers(createDate, name, distance);
            roundtripId = (Integer) session.save(rte);
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return roundtripId;
    }

    public Integer create(Timestamp createDate, String name, double distance, Integer tspId, List<Integer> cityIds){
        Integer roundtripId = create(createDate, name, distance);
        for (Integer cityId : cityIds) {
            createRoundTripCityEntity(roundtripId, cityId);
        }
        return roundtripId;
    }

    /* Method to  READ all the roundtrips */
    public void listRoundtrips( ){
        Session session = factory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            List roundtrips = session.createQuery("FROM RoundtripEntity ").list();
            for (Iterator iterator = roundtrips.iterator(); iterator.hasNext();){
                RoundtripEntity roundtripEntity = (RoundtripEntity) iterator.next();
                System.out.print("Name: " + roundtripEntity.getName());
                System.out.print("Distance: " + roundtripEntity.getDistance());
                System.out.println("Create date: " + roundtripEntity.getCreateDate());
            }
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    /* Method to DELETE a roundtrip from the records */
    public void deleteRoundtrip(Integer roundtripId){
        Session session = factory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            RoundtripEntity city = (RoundtripEntity)session.get(RoundtripEntity.class, roundtripId);
            session.delete(city);
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    /* Method to CREATE a roundtripCities entry in the database */
    public Integer createRoundTripCityEntity(int roundtrip_id, int city_id){
        Session session = factory.openSession();
        Transaction tx = null;
        Integer roundtripCitiesEntityId = null;

        try {
            tx = session.beginTransaction();
            RoundtripCitiesEntity rtce = new RoundtripCitiesEntity();
            rtce.setRoundtripId(roundtrip_id);
            rtce.setCityId(city_id);
            roundtripCitiesEntityId = (Integer) session.save(rtce);
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return roundtripCitiesEntityId;
    }


    /* Method to DELETE a RoundtripCities entry from the records */
    public void deleteRoundtripCityEntry(Integer roundtripCitiesEntityId){
        Session session = factory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            RoundtripCitiesEntity city = (RoundtripCitiesEntity)session.get(RoundtripCitiesEntity.class, roundtripCitiesEntityId);
            session.delete(city);
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
}
