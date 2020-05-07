package EntityManager;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import tsp.model.RoundtripEntity;
import tsp.model.SubsequenceEntity;

import java.sql.Timestamp;
import java.util.Iterator;
import java.util.List;

public class SubsequenceEntityManager extends EntityManager {
    /* Method to CREATE a subsequence in the database */
    public Integer createNewSubsequence(String cities, int tspId){
        Session session = factory.openSession();
        Transaction tx = null;
        Integer subsequenceId = null;
        Integer subsequenceOrderId = null;

        try {
            tx = session.beginTransaction();
            SubsequenceEntity se = new SubsequenceEntity();
            se.setTspId(tspId);
            subsequenceId = (Integer) session.save(se);
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return subsequenceId;
    }

    /* Method to DELETE a subsequence from the records */
    public void deleteRoundtrip(Integer subsequenceId){
        Session session = factory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            SubsequenceEntity subsequence = (SubsequenceEntity)session.get(SubsequenceEntity.class, subsequenceId);
            session.delete(subsequence);
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
}
