package EntityManager;

import com.sun.istack.Nullable;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import tsp.model.CityEntity;
import tsp.model.RoundtripEntity;
import tsp.model.TspEntity;
import tsp.model.TspRoundtripsEntity;

import java.util.List;

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
        Session session = factory.openSession();
        Transaction tx = null;
        Integer tspRoundtripId = null;

        try {
            tx = session.beginTransaction();
            TspRoundtripsEntity tspRoundtripsEntity = new TspRoundtripsEntity(tspId, roundtripId);
            tspRoundtripId = (Integer) session.save(tspRoundtripsEntity);
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
