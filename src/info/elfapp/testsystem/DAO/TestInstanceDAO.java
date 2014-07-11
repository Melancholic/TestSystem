package info.elfapp.testsystem.DAO;

import info.elfapp.testsystem.HibernateUtil;
import info.elfapp.testsystem.Maps.*;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by sosnov on 24.04.14.
 */
public class TestInstanceDAO implements DAOInterface {


    public TestInstanceDAO() {

    }

    @Override
    public void addObj(Object obj) throws SQLException {
        try {

            HibernateUtil.getSessionFactory().getCurrentSession().save(obj);

        } catch (Exception e) {
            System.err.println(e.getMessage() + "\nОшибка при TestInstanceDAO::addObj(Object obj)");
            if (HibernateUtil.getSessionFactory().getCurrentSession().getTransaction()!=null) {
                System.err.println("Trying to rollback database transaction after exception");
                HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().rollback();
            }
            throw new SQLException(e.getMessage());
        }
    }

    @Override
    public void updateObj(Object obj) throws SQLException {
        try {

            HibernateUtil.getSessionFactory().getCurrentSession().update(obj);

        } catch (Exception e) {

            System.err.println(e.getMessage() + "\nОшибка при TestInstanceDAO::updateObj(Object obj)");
            if (HibernateUtil.getSessionFactory().getCurrentSession().getTransaction()!=null) {
                System.err.println("Trying to rollback database transaction after exception");
                HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().rollback();
            }
            throw new SQLException(e.getMessage());
        }
    }


    @Override
    public TestInstance getObjById(Long obj_id) throws SQLException {
        TestInstance ti = null;
        try {
            ti = (TestInstance) HibernateUtil.getSessionFactory().getCurrentSession().load(TestInstance.class, obj_id);
        } catch (Exception e) {
            System.err.println(e.getMessage() + "\nОшибка при TestInstanceDAO::getObjById");
            if (HibernateUtil.getSessionFactory().getCurrentSession().getTransaction()!=null) {
                System.err.println("Trying to rollback database transaction after exception");
                HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().rollback();
            }
            throw new SQLException(e.getMessage());
        }
        return ti;
    }



    @Override
    public Collection getAllObj() throws SQLException {
        List tis = new ArrayList<TestInstance>();
        try {
            tis = HibernateUtil.getSessionFactory().getCurrentSession().createCriteria(TestInstance.class).list();
        } catch (Exception e) {
            System.err.println(e.getMessage() + "\nОшибка при  TestInstanceDAO::getAllObj()");
            if (HibernateUtil.getSessionFactory().getCurrentSession().getTransaction()!=null) {
                System.err.println("Trying to rollback database transaction after exception");
                HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().rollback();
            }
            throw new SQLException(e.getMessage());
        }
        return tis;
    }

    @Override
    public void deleteObj(Object obj) throws SQLException {
        try {

            HibernateUtil.getSessionFactory().getCurrentSession().delete(obj);

        } catch (Exception e) {
            System.err.println(e.getMessage() + "\nОшибка при TestInstanceDAO::deleteObj(Object obj)");
            if (HibernateUtil.getSessionFactory().getCurrentSession().getTransaction()!=null) {
                System.err.println("Trying to rollback database transaction after exception");
                HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().rollback();
            }
            throw new SQLException(e.getMessage());
        }
    }
}
