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
public class UsersTIDAO implements DAOInterface {
    public UsersTIDAO() {

    }
    @Override
    public void addObj(Object obj) throws SQLException {
        try {
            HibernateUtil.getSessionFactory().getCurrentSession().save(obj);

        } catch (Exception e) {
            System.err.println(e.getMessage() + "\nОшибка при UsersTIDAO::addObj(Object obj)");
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

            System.err.println(e.getMessage() + "\nОшибка при UsersTIDAO::updateObj(Object obj)");
            if (HibernateUtil.getSessionFactory().getCurrentSession().getTransaction()!=null) {
                System.err.println("Trying to rollback database transaction after exception");
                HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().rollback();
            }
            throw new SQLException(e.getMessage());
        }
    }


    @Override
    public UsersTI getObjById(Long obj_id) throws SQLException {
        new SQLException("UsersTIDAO have not ID! Use getObjByUser or getObjByTI or getObjByPK! ");
        return null;
    }


    public Collection getObjByUser(Users usr) throws SQLException {
        ArrayList<UsersTI> uti =null;
        try {

            uti = (ArrayList<UsersTI>) HibernateUtil.getSessionFactory().getCurrentSession().createCriteria(UsersTI.class).add(Restrictions.eq("pk.user",usr)).list();
        } catch (Exception e) {
            System.err.println(e.getMessage() + "\nОшибка при  UsersTIDAO::getObjByTest()");
            if (HibernateUtil.getSessionFactory().getCurrentSession().getTransaction()!=null) {
                System.err.println("Trying to rollback database transaction after exception");
                HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().rollback();
            }
            throw new SQLException(e.getMessage());
        }
        return uti;
    }

    public Collection getObjByQGroups(TestInstance ti) throws SQLException {
        ArrayList<UsersTI> uti =null;
        try {

            uti = (ArrayList<UsersTI>) HibernateUtil.getSessionFactory().getCurrentSession().createCriteria(UsersTI.class).add(Restrictions.eq("pk.ti",ti)).list();
        } catch (Exception e) {
            System.err.println(e.getMessage() + "\nОшибка при  UsersTIDAO::getObjByQGroups()");
            if (HibernateUtil.getSessionFactory().getCurrentSession().getTransaction()!=null) {
                System.err.println("Trying to rollback database transaction after exception");
                HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().rollback();
            }
            throw new SQLException(e.getMessage());
        }
        return uti;
    }

    public UsersTI getObjByPK(TestsQGroupsID pk) throws SQLException {
        UsersTI tqs = null;
        try {

            tqs = (UsersTI) HibernateUtil.getSessionFactory().getCurrentSession().get(UsersTI.class,pk);
        } catch (Exception e) {
            System.err.println(e.getMessage() + "\nОшибка при  UsersTIDAO::getObjByPK()");
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
        List qsts = new ArrayList<UsersTI>();
        try {
            qsts = HibernateUtil.getSessionFactory().getCurrentSession().createCriteria(UsersTI.class).list();
        } catch (Exception e) {
            System.err.println(e.getMessage() + "\nОшибка при  UsersTIDAO::getAllObj()");
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
            System.err.println(e.getMessage() + "\nОшибка при UsersTIDAO::deleteObj(Object obj)");
            if (HibernateUtil.getSessionFactory().getCurrentSession().getTransaction()!=null) {
                System.err.println("Trying to rollback database transaction after exception");
                HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().rollback();
            }
            throw new SQLException(e.getMessage());
        }
    }
}
