package info.elfapp.testsystem.DAO;

import info.elfapp.testsystem.HibernateUtil;
import info.elfapp.testsystem.Maps.QuestionsGroups;
import info.elfapp.testsystem.Maps.Tests;
import info.elfapp.testsystem.Maps.TestsQGroups;
import org.hibernate.Session;

import java.sql.SQLException;
import java.util.*;

/**
 * Created by sosnov on 24.04.14.
 */
public class TestsDAO implements DAOInterface {
    public TestsDAO() {
    }
    @Override
    public void addObj(Object obj) throws SQLException {
        try {

            HibernateUtil.getSessionFactory().getCurrentSession().save(obj);

        } catch (Exception e) {
             System.err.println(e.getMessage() + "\nОшибка при TestsDAO::addObj(Object obj)");
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

            System.err.println(e.getMessage() + "\nОшибка при TestsDAO::updateObj(Object obj)");
            if (HibernateUtil.getSessionFactory().getCurrentSession().getTransaction()!=null) {
                System.err.println("Trying to rollback database transaction after exception");
                HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().rollback();
            }
            throw new SQLException(e.getMessage());
        }
    }


    @Override
    public Tests getObjById(Long obj_id) throws SQLException {
        Tests tst = null;
        try {
            tst = (Tests) HibernateUtil.getSessionFactory().getCurrentSession().load(Tests.class, obj_id);
        } catch (Exception e) {
            System.err.println(e.getMessage() + "\nОшибка при TestsDAO::getObjById");
            if (HibernateUtil.getSessionFactory().getCurrentSession().getTransaction()!=null) {
                System.err.println("Trying to rollback database transaction after exception");
                HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().rollback();
            }
            throw new SQLException(e.getMessage());
        }
        return tst;
    }

    @Override
    public Collection getAllObj() throws SQLException {
        List tsts = new ArrayList<Tests>();
        try {
            tsts = HibernateUtil.getSessionFactory().getCurrentSession().createCriteria(Tests.class).list();
        } catch (Exception e) {
            System.err.println(e.getMessage() + "\nОшибка при  TestsDAO::getAllObj()");
            if (HibernateUtil.getSessionFactory().getCurrentSession().getTransaction()!=null) {
                System.err.println("Trying to rollback database transaction after exception");
                HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().rollback();
            }
            throw new SQLException(e.getMessage());
        }
        return tsts;
    }

    @Override
    public void deleteObj(Object obj) throws SQLException {
        try {

            HibernateUtil.getSessionFactory().getCurrentSession().delete(obj);

        } catch (Exception e) {
            System.err.println(e.getMessage() + "\nОшибка при TestsDAO::deleteObj(Object obj)");
            if (HibernateUtil.getSessionFactory().getCurrentSession().getTransaction()!=null) {
                System.err.println("Trying to rollback database transaction after exception");
                HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().rollback();
            }
            throw new SQLException(e.getMessage());
        }
    }
public Long getSize(Tests tst) throws SQLException {
        long result = 0;
        try {
             Set<TestsQGroups> qgs = tst.getTestQGroups();
            for(TestsQGroups tqg: qgs){
                result+=tqg.getSize();
            }

        } catch (Exception e) {
            System.err.println(e.getMessage() + "\nОшибка при TestsDAO::deleteObj(Object obj)");
            if (HibernateUtil.getSessionFactory().getCurrentSession().getTransaction()!=null) {
                System.err.println("Trying to rollback database transaction after exception");
                HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().rollback();
            }
            throw new SQLException(e.getMessage());
        }
        return result;
    }
}
