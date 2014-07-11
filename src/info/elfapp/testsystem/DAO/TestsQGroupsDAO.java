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
public class TestsQGroupsDAO implements DAOInterface {
    public TestsQGroupsDAO() {
    }
    @Override
    public void addObj(Object obj) throws SQLException {
        try {

            HibernateUtil.getSessionFactory().getCurrentSession().save(obj);

        } catch (Exception e) {
            System.err.println(e.getMessage() + "\nОшибка при TestsQGroupsDAO::addObj(Object obj)");
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
;
        } catch (Exception e) {

            System.err.println(e.getMessage() + "\nОшибка при TestsQGroupsDAO::updateObj(Object obj)");
            if (HibernateUtil.getSessionFactory().getCurrentSession().getTransaction()!=null) {
                System.err.println("Trying to rollback database transaction after exception");
                HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().rollback();
            }
            throw new SQLException(e.getMessage());
        }
    }


    @Override
    public TestsQGroups getObjById(Long obj_id) throws SQLException {
        new SQLException("TestsQGroupsDAO have not ID! Use getObjByTest or getObjByQGroups or getObjByPK! ");
        return null;
    }


    public Collection getObjByTest(Tests tst) throws SQLException {
        ArrayList<TestsQGroups> tqs =null;
        try {

             tqs = (ArrayList<TestsQGroups>) HibernateUtil.getSessionFactory().getCurrentSession().createCriteria(TestsQGroups.class).add(Restrictions.eq("pk.tests",tst)).list();
        } catch (Exception e) {
            System.err.println(e.getMessage() + "\nОшибка при  TestsQGroupsDAO::getObjByTest()");
            if (HibernateUtil.getSessionFactory().getCurrentSession().getTransaction()!=null) {
                System.err.println("Trying to rollback database transaction after exception");
                HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().rollback();
            }
            throw new SQLException(e.getMessage());
        }
        return tqs;
    }

    public Collection getObjByQGroups(QuestionsGroups qg) throws SQLException {
        ArrayList<TestsQGroups> tqs =null;
        try {

            tqs = new ArrayList( HibernateUtil.getSessionFactory().getCurrentSession().createCriteria(TestsQGroups.class).add(Restrictions.eq("pk.qgroups",tqs)).list());
        } catch (Exception e) {
            System.err.println(e.getMessage() + "\nОшибка при  TestsQGroupsDAO::getObjByQGroups()");
            if (HibernateUtil.getSessionFactory().getCurrentSession().getTransaction()!=null) {
                System.err.println("Trying to rollback database transaction after exception");
                HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().rollback();
            }
            throw new SQLException(e.getMessage());
        }
        return tqs;
    }

    public TestsQGroups getObjByPK(TestsQGroupsID pk) throws SQLException {
        TestsQGroups tqs = null;
        try {

            tqs = (TestsQGroups) HibernateUtil.getSessionFactory().getCurrentSession().get(TestsQGroups.class,pk);
        } catch (Exception e) {
            System.err.println(e.getMessage() + "\nОшибка при  TestsQGroupsDAO::getObjByPK()");
            if (HibernateUtil.getSessionFactory().getCurrentSession().getTransaction()!=null) {
                System.err.println("Trying to rollback database transaction after exception");
                HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().rollback();
            }
            throw new SQLException(e.getMessage());
        }
        return tqs;
    }

    @Override
    public Collection getAllObj() throws SQLException {
        List qsts = new ArrayList<TestsQGroups>();
        try {
            qsts = HibernateUtil.getSessionFactory().getCurrentSession().createCriteria(TestsQGroups.class).list();
        } catch (Exception e) {
            System.err.println(e.getMessage() + "\nОшибка при  TestsQGroupsDAO::getAllObj()");
            if (HibernateUtil.getSessionFactory().getCurrentSession().getTransaction()!=null) {
                System.err.println("Trying to rollback database transaction after exception");
                HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().rollback();
            }
            throw new SQLException(e.getMessage());
        }
        return qsts;
    }

    @Override
    public void deleteObj(Object obj) throws SQLException {
        try {

            HibernateUtil.getSessionFactory().getCurrentSession().delete(obj);

        } catch (Exception e) {
            System.err.println(e.getMessage() + "\nОшибка при TestsQGroupsDAO::deleteObj(Object obj)");
            if (HibernateUtil.getSessionFactory().getCurrentSession().getTransaction()!=null) {
                System.err.println("Trying to rollback database transaction after exception");
                HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().rollback();
            }
            throw new SQLException(e.getMessage());
        }
    }
}
