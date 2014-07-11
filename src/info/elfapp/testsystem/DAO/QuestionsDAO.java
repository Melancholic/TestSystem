package info.elfapp.testsystem.DAO;

import info.elfapp.testsystem.HibernateUtil;
import info.elfapp.testsystem.Maps.Questions;
import info.elfapp.testsystem.Maps.QuestionsGroups;
import org.hibernate.Session;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by sosnov on 24.04.14.
 */
public class QuestionsDAO implements DAOInterface {

    public QuestionsDAO() {

    }


    @Override
    public void addObj(Object obj) throws SQLException {
        try {

            HibernateUtil.getSessionFactory().getCurrentSession().save(obj);

        } catch (Exception e) {
            System.err.println(e.getMessage() + "\nОшибка при QuestionsDAO::addObj(Object obj)");
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

            System.err.println(e.getMessage() + "\nОшибка при QuestionsDAO::updateObj(Object obj)");
            if (HibernateUtil.getSessionFactory().getCurrentSession().getTransaction()!=null) {
                System.err.println("Trying to rollback database transaction after exception");
                HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().rollback();
            }
            throw new SQLException(e.getMessage());
        }
    }


    @Override
    public Questions getObjById(Long obj_id) throws SQLException {
        Questions qst = null;
        try {
            qst = (Questions) HibernateUtil.getSessionFactory().getCurrentSession().load(Questions.class, obj_id);
        } catch (Exception e) {
            System.err.println(e.getMessage() + "\nОшибка при QuestionsDAO::getObjById");
            if (HibernateUtil.getSessionFactory().getCurrentSession().getTransaction()!=null) {
                System.err.println("Trying to rollback database transaction after exception");
                HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().rollback();
            }
            throw new SQLException(e.getMessage());
        }
        return qst;
    }

    @Override
    public Collection getAllObj() throws SQLException {
        List qsts = new ArrayList<Questions>();
        try {
            qsts = HibernateUtil.getSessionFactory().getCurrentSession().createCriteria(Questions.class).list();
        } catch (Exception e) {
            System.err.println(e.getMessage() + "\nОшибка при  QuestionsDAO::getAllObj()");
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
            Questions q=(Questions)obj;
            q.getGroup().getQuests().remove(q);
            HibernateUtil.getSessionFactory().getCurrentSession().delete(q);

        } catch (Exception e) {
            System.err.println(e.getMessage() + "\nОшибка при QuestionsDAO::deleteObj(Object obj)");
            if (HibernateUtil.getSessionFactory().getCurrentSession().getTransaction()!=null) {
                System.err.println("Trying to rollback database transaction after exception");
                HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().rollback();
            }
            throw new SQLException(e.getMessage());
        }
    }


}
