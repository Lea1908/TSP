package EntityManager;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class EntityManager {
    public static SessionFactory factory;
    public EntityManager() {
        try {
            factory = new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Failed to create sessionFactory object." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }
}
