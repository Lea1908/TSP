package EntityManager;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import tsp.model.CityEntity;

import java.util.Iterator;
import java.util.List;

public class CityManager extends EntityManager {

    /* Method to CREATE a city in the database */
    public Integer create(String name, double x, double y){
        Session session = factory.openSession();
        Transaction tx = null;
        Integer cityId = null;

        try {
            tx = session.beginTransaction();
            CityEntity cu = new CityEntity();
            cu.setName(name);
            cu.setxCoordinate(x);
            cu.setyCoordinate(y);
            cityId = (Integer) session.save(cu);
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return cityId;
    }

    /* Method to  READ all the cities */
    public void ListCities( ){
        Session session = factory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            List employees = session.createQuery("FROM CityEntity ").list();
            for (Iterator iterator = employees.iterator(); iterator.hasNext();){
                CityEntity city = (CityEntity) iterator.next();
                System.out.print("Name: " + city.getName());
                System.out.print("x coordinate: " + city.getxCoordinate());
                System.out.println("Y coordinate: " + city.getyCoordinate());
            }
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    /* Method to DELETE a city from the records */
    public void deleteCity(Integer cityId){
        Session session = factory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            CityEntity city = (CityEntity)session.get(CityEntity.class, cityId);
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
