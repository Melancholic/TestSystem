package info.elfapp.testsystem;

import info.elfapp.testsystem.DAO.DAO;
import info.elfapp.testsystem.Maps.Users;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.sql.SQLException;

/**
 * Created by sosnov on 19.04.14.
 */
public class HibernateListener implements ServletContextListener {

    public void contextInitialized(ServletContextEvent event) {
        System.err.println("ServletContextListener started");
        HibernateUtil.getSessionFactory(); // create a factory
        //Create admin
        HibernateUtil.getSessionFactory().getCurrentSession().beginTransaction();
        try {
            if(DAO.getInstance().getUsrsDAO().getUsrFromName("admin")==null){
                Users adm = new Users("admin","");
                adm.setUserPass("admin");
                adm.setAdmin(true);
                DAO.getInstance().getUsrsDAO().addObj(adm);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().commit();
        //Create admin
    }

    public void contextDestroyed(ServletContextEvent event) {

        HibernateUtil.getSessionFactory().close(); // free resources

    }

}

