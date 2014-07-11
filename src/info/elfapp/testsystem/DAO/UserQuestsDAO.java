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
public class UserQuestsDAO implements DAOInterface {


    public UserQuestsDAO() {

    }
    @Override
    public void addObj(Object obj) throws SQLException {
        try {

            HibernateUtil.getSessionFactory().getCurrentSession().save(obj);

        } catch (Exception e) {
            System.err.println(e.getMessage() + "\nОшибка при UserQuestsDAO::addObj(Object obj)");
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

            System.err.println(e.getMessage() + "\nОшибка при UserQuestsDAO::updateObj(Object obj)");
            if (HibernateUtil.getSessionFactory().getCurrentSession().getTransaction()!=null) {
                System.err.println("Trying to rollback database transaction after exception");
                HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().rollback();
            }
            throw new SQLException(e.getMessage());
        }
    }


    @Override
    public UsersQuests getObjById(Long obj_id) throws SQLException {
        UsersQuests uq = null;
        try {
            uq = (UsersQuests) HibernateUtil.getSessionFactory().getCurrentSession().load(UsersQuests.class, obj_id);
        } catch (Exception e) {
            System.err.println(e.getMessage() + "\nОшибка при UserQuestsDAO::getObjById");
            if (HibernateUtil.getSessionFactory().getCurrentSession().getTransaction()!=null) {
                System.err.println("Trying to rollback database transaction after exception");
                HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().rollback();
            }
            throw new SQLException(e.getMessage());
        }
        return uq;
    }


    public Collection getObjByUser(Users usr) throws SQLException {
        ArrayList<UsersQuests> tqs =null;
        try {

             tqs = (ArrayList<UsersQuests>) HibernateUtil.getSessionFactory().getCurrentSession().createCriteria(UsersQuests.class).add(Restrictions.eq("pk.user",usr)).list();
        } catch (Exception e) {
            System.err.println(e.getMessage() + "\nОшибка при  UserQuestsDAO::getObjByUser()");
            if (HibernateUtil.getSessionFactory().getCurrentSession().getTransaction()!=null) {
                System.err.println("Trying to rollback database transaction after exception");
                HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().rollback();
            }
            throw new SQLException(e.getMessage());
        }
        return tqs;
    }

    public Collection getObjByQuest(Questions qst) throws SQLException {
        ArrayList<UsersQuests> tqs =null;
        try {

            tqs = (ArrayList<UsersQuests>) HibernateUtil.getSessionFactory().getCurrentSession().createCriteria(UsersQuests.class).add(Restrictions.eq("pk.quesr",qst)).list();
        } catch (Exception e) {
            System.err.println(e.getMessage() + "\nОшибка при  UserQuestsDAO::getObjByQuest()");
            if (HibernateUtil.getSessionFactory().getCurrentSession().getTransaction()!=null) {
                System.err.println("Trying to rollback database transaction after exception");
                HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().rollback();
            }
            throw new SQLException(e.getMessage());
        }
        return tqs;
    }

    public Collection getObjByTI(TestInstance ti) throws SQLException {
        ArrayList<UsersQuests> tqs =null;
        try {

            tqs = (ArrayList<UsersQuests>) HibernateUtil.getSessionFactory().getCurrentSession().createCriteria(UsersQuests.class).add(Restrictions.eq("pk.ti",ti)).list();
        } catch (Exception e) {
            System.err.println(e.getMessage() + "\nОшибка при  UserQuestsDAO::getObjByTI()");
            if (HibernateUtil.getSessionFactory().getCurrentSession().getTransaction()!=null) {
                System.err.println("Trying to rollback database transaction after exception");
                HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().rollback();
            }
            throw new SQLException(e.getMessage());
        }
        return tqs;
    }

    public UsersQuests getObjByPK(UserQuestsPK pk) throws SQLException {
        UsersQuests tqs = null;
        try {

            tqs = (UsersQuests) HibernateUtil.getSessionFactory().getCurrentSession().get(UsersQuests.class,pk);
        } catch (Exception e) {
            System.err.println(e.getMessage() + "\nОшибка при  UserQuestsDAO::getObjByPK()");
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
        List qsts = new ArrayList<UsersQuests>();
        try {
            qsts = HibernateUtil.getSessionFactory().getCurrentSession().createCriteria(UsersQuests.class).list();
        } catch (Exception e) {
            System.err.println(e.getMessage() + "\nОшибка при  UserQuestsDAO::getAllObj()");
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
            System.err.println(e.getMessage() + "\nОшибка при UserQuestsDAO::deleteObj(Object obj)");
            if (HibernateUtil.getSessionFactory().getCurrentSession().getTransaction()!=null) {
                System.err.println("Trying to rollback database transaction after exception");
                HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().rollback();
            }
            throw new SQLException(e.getMessage());
        }
    }
}
