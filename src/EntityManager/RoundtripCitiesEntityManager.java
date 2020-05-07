package EntityManager;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import tsp.model.RoundtripCitiesEntity;

public class RoundtripCitiesEntityManager extends EntityManager {


    /* Method to CREATE a roundtripCities entry in the database */
    public Integer create(int roundtrip_id, int city_id){
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
