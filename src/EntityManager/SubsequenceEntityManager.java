package EntityManager;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import tsp.model.SubsequenceEntity;
import tsp.model.SubsequenceOrderEntity;

import java.util.List;

public class SubsequenceEntityManager extends EntityManager {
    /* Method to CREATE a subsequence in the database */
    public Integer createNewSubsequence(List<String> cities, int tspId){
        Session session = factory.openSession();
        Transaction tx = null;
        Integer subsequenceId = null;
        Integer subsequenceOrderId = null;

        try {
            tx = session.beginTransaction();
            SubsequenceEntity se = new SubsequenceEntity();
            se.setTspId(tspId);
            var subsequence = (SubsequenceEntity) session.save(se);
            subsequenceId = subsequence.getId();
            for (String cityOrder : cities) {
                SubsequenceOrderEntity so = new SubsequenceOrderEntity();
                so.setCityOrder(cityOrder);
                so.setSubsequenceId(subsequenceId);
                var soE = (SubsequenceOrderEntity) session.save(so);
                var soId = soE.getId();
            }
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
