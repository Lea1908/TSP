package EntityManager;

import com.sun.istack.Nullable;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import tsp.model.CityEntity;
import tsp.model.RoundtripEntity;
import tsp.model.TspEntity;

import java.util.List;

public class TspEntityManager extends EntityManager{
    /* Method to CREATE a tsp in the database */
    // todo nullable parameters
    public Integer create(Integer startCityId, Integer endCityId, Integer subSequenceId, Double maxDuration){
        Session session = factory.openSession();
        Transaction tx = null;
        Integer tspId = null;

        try {
            tx = session.beginTransaction();
            TspEntity te = new TspEntity();
            if (maxDuration != null) {
                te.setMaxDuration(maxDuration);
            }
            if (startCityId != null) {
                te.setStartCityId(startCityId);
            }
            if (endCityId != null) {
                te.setEndCityId(endCityId);
            }
            if (subSequenceId != null) {
                te.setSubsequenceId(subSequenceId);
            }
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
}
