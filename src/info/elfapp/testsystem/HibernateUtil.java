package info.elfapp.testsystem;

/**
 * Created by sosnov on 19.04.14.
 */

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

public class HibernateUtil {
    private static SessionFactory sessionFactory;
    private static ServiceRegistry serviceRegistry;

    public static SessionFactory createSessionFactory() {
        Configuration configuration = new Configuration();
        configuration.configure();
        serviceRegistry = new ServiceRegistryBuilder().applySettings(
                configuration.getProperties()).buildServiceRegistry();
        sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        System.err.println("Factory create!");
        return sessionFactory;
    }

    public static SessionFactory getSessionFactory() {
        if(sessionFactory==null){
            createSessionFactory();
        }
            return sessionFactory;
    }

    public static void shutDown() {
        //closes caches and connections
        getSessionFactory().close();
    }
}