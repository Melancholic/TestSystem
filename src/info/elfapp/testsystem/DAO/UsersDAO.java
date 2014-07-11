package info.elfapp.testsystem.DAO;

import info.elfapp.testsystem.HibernateUtil;
import info.elfapp.testsystem.Maps.Users;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by sosnov on 24.04.14.
 */
public class UsersDAO implements DAOInterface {

    public UsersDAO() {
    }
    @Override
    public void addObj(Object obj) throws SQLException {
        try {
            HibernateUtil.getSessionFactory().getCurrentSession().save(obj);

        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getMessage() + "\nОшибка при UsersDAO::addObj(Object obj)");
            if (HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().isActive()) {
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
            System.err.println(e.getMessage() + "\nОшибка при UsersDAO::updateObj(Object obj)");
            if (HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().isActive()) {
                System.err.println("Trying to rollback database transaction after exception");
                HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().rollback();
            }

            throw new SQLException(e.getMessage());
        }
    }

    @Override
    public Users getObjById(Long obj_id) throws SQLException {
        //Session HibernateUtil.getSessionFactory().getCurrentSession() = null;
        Users usr = null;
        try {
          //  HibernateUtil.getSessionFactory().getCurrentSession() = HibernateUtil.getSessionFactory().openSession();
            usr = (Users) HibernateUtil.getSessionFactory().getCurrentSession().load(Users.class, obj_id);
        } catch (Exception e) {
            System.err.println(e.getMessage() + "\nОшибка при UsersDAO::getObjById");
            if (HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().isActive()) {
                System.err.println("Trying to rollback database transaction after exception");
                HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().rollback();
            }

            throw new SQLException(e.getMessage());
        }
        return usr;
    }

    @Override
    public Collection getAllObj() throws SQLException {
        List usrs = new ArrayList<Users>();
        try {
            usrs = new ArrayList<Users>(HibernateUtil.getSessionFactory().getCurrentSession().createQuery("from Users ").list());
        } catch (Exception e) {
            System.err.println(e.getMessage() + "\nОшибка при  UsersDAO::getAllObj()");
            if (HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().isActive()) {
                System.err.println("Trying to rollback database transaction after exception");
                HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().rollback();
            }

            throw new SQLException(e.getMessage());
        }

        return usrs;
    }

    @Override
    public void deleteObj(Object obj) throws SQLException {
        try {
            Users u=(Users)obj;
            u.getGroup().getUsrs().remove(u);
            HibernateUtil.getSessionFactory().getCurrentSession().delete(u);
        } catch (Exception e) {
            System.err.println(e.getMessage() + "\nОшибка при UsersDAO::deleteObj(Object obj)");
            if (HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().isActive()) {
                System.err.println("Trying to rollback database transaction after exception");
                HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().rollback();
            }

            throw new SQLException(e.getMessage());
        }
    }

    public Users getUsrFromName(String nick) throws SQLException {

        Users usr = null;
        try {
            usr = (Users) HibernateUtil.getSessionFactory().getCurrentSession().createCriteria(Users.class).add(Restrictions.eq("UserName", nick)).uniqueResult();
        } catch (Exception e) {
            System.err.println(e.getMessage() + "\nОшибка при UsersDAO::getUsrFromName(Object obj)");
            if (HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().isActive()) {
                System.err.println("Trying to rollback database transaction after exception");
                HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().rollback();
            }

            throw new SQLException(e.getMessage());
        }
        return usr;
    }

    }
