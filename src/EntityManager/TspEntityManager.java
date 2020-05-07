package EntityManager;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import tsp.model.CityEntity;
import tsp.model.TspEntity;

public class TspEntityManager extends EntityManager{
    /* Method to CREATE a tsp in the database */
    // todo nullable parameters
    public Integer create(int startCityId, int endCityId, int subSequenceId, double maxDuration){
        Session session = factory.openSession();
        Transaction tx = null;
        Integer tspId = null;

        try {
            tx = session.beginTransaction();
            TspEntity te = new TspEntity();
            te.setMaxDuration(maxDuration);
            // todo
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
}
