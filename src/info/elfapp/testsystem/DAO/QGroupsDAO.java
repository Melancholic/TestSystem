package info.elfapp.testsystem.DAO;

import info.elfapp.testsystem.HibernateUtil;
import info.elfapp.testsystem.Maps.QuestionsGroups;
import info.elfapp.testsystem.Maps.Users;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by sosnov on 19.04.14.
 */
public class QGroupsDAO implements DAOInterface {
 //   private Session HibernateUtil.getSessionFactory().getCurrentSession() = HibernateUtil.getSessionFactory().getCurrentSession();

    public QGroupsDAO() {
      //  this.HibernateUtil.getSessionFactory().getCurrentSession() =  HibernateUtil.getSessionFactory().getCurrentSession();;
    }

    @Override
    public void addObj(Object obj) throws SQLException {
        try {

            HibernateUtil.getSessionFactory().getCurrentSession().save(obj);

        } catch (Exception e) {
            System.err.println(e.getMessage() + "\nОшибка при QGroupsDAO::addObj(Object obj)");
            if (HibernateUtil.getSessionFactory().getCurrentSession().getTransaction()!=null) {
                System.err.println("Trying to rollback database transaction after exception");
                HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().rollback();
            }
            //HibernateUtil.getSessionFactory().getCurrentSession().flush();
            throw new SQLException(e.getMessage());
        }
    }

    @Override
    public void updateObj(Object obj) throws SQLException {

        try {

            HibernateUtil.getSessionFactory().getCurrentSession().update(obj);

        } catch (Exception e) {
            System.err.println(e.getMessage() + "\nОшибка при QGroupsDAO::updateObj(Object obj)");
        }

    }

    @Override
    public QuestionsGroups getObjById(Long obj_id) throws SQLException {
        QuestionsGroups qsts = null;
        try {
            qsts = (QuestionsGroups) HibernateUtil.getSessionFactory().getCurrentSession().load(QuestionsGroups.class, obj_id);
        } catch (Exception e) {
            System.err.println(e.getMessage() + "\nОшибка при QGroupsDAO::getObjById");
        }
        return qsts;
    }

    @Override
    public Collection getAllObj() throws SQLException {
        ArrayList<QuestionsGroups> qsts = new ArrayList<QuestionsGroups>();
        try {

            qsts = new ArrayList<QuestionsGroups>(HibernateUtil.getSessionFactory().getCurrentSession()/*.createCriteria(QuestionsGroups.class)*/.createQuery("from QuestionsGroups").list());
            System.err.println("SIZE QGroups: "+qsts.size());
        } catch (Exception e) {
            System.err.println(e.getMessage() + "\nОшибка при  QGroupsDAO::getAllObj()");
        }
        return qsts;
    }

    @Override
    public void deleteObj(Object obj) throws SQLException {
        try {
            HibernateUtil.getSessionFactory().getCurrentSession().delete(obj);

        } catch (Exception e) {
            System.err.println(e.getMessage() + "\nОшибка при QGroupsDAO::deleteObj(Object obj)");
        }
    }

    public QuestionsGroups getQGroupsFromName(String name) throws SQLException {

        QuestionsGroups qg = null;
        try {
            qg = (QuestionsGroups) HibernateUtil.getSessionFactory().getCurrentSession().createCriteria(QuestionsGroups.class).add(Restrictions.eq("QGroupName", name)).uniqueResult();
        } catch (Exception e) {
            System.err.println(e.getMessage() + "\nОшибка при QGroupsDAO::getQGroupsFromName(Object obj)");
            if (HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().isActive()) {
                System.err.println("Trying to rollback database transaction after exception");
                HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().rollback();
            }
            HibernateUtil.getSessionFactory().getCurrentSession().flush();
            throw new SQLException(e.getMessage());
        }
        return qg;
    }

}
