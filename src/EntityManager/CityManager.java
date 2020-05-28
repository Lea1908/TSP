package EntityManager;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import tsp.model.CityEntity;

import java.util.Iterator;
import java.util.List;

public class CityManager extends EntityManager {

    /* Method to CREATE a city in the database */
    public Integer findExistingOrCreateNewCity(String name, double x, double y){
        Session session = factory.openSession();
        Transaction tx = null;
        Integer cityId = null;
        try {
            tx = session.beginTransaction();
            String queryString = "from CityEntity where name=" + name + ", xCoordinate=" + x + ", yCoordinate=" + y;
            Query query = session.createQuery(queryString);
            CityEntity existingCityEntity = (CityEntity) query.uniqueResult();
            if (existingCityEntity != null) {
                cityId = existingCityEntity.getId();
            } else {
                CityEntity cityEntity = new CityEntity();
                cityEntity.setName(name);
                cityEntity.setxCoordinate(x);
                cityEntity.setyCoordinate(y);
                cityId = (Integer) session.save(cityEntity);
            }
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return cityId;
    }
    public Integer findExistingOrCreateNewCity(CityEntity cityEntity){
        return findExistingOrCreateNewCity(cityEntity.getName(), cityEntity.getxCoordinate(), cityEntity.getyCoordinate());
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
