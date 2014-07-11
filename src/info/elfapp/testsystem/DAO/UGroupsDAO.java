package info.elfapp.testsystem.DAO;

import info.elfapp.testsystem.HibernateUtil;
import info.elfapp.testsystem.Maps.UsersGroups;
import org.hibernate.criterion.Restrictions;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by sosnov on 19.04.14.
 */
public class UGroupsDAO implements DAOInterface {
    //  private Session HibernateUtil.getSessionFactory().getCurrentSession() = HibernateUtil.getSessionFactory().getCurrentSession();

    public UGroupsDAO() {
        //  this.HibernateUtil.getSessionFactory().getCurrentSession() =  HibernateUtil.getSessionFactory().getCurrentSession();;
    }

    @Override
    public void addObj(Object obj) throws SQLException {
        try {
            HibernateUtil.getSessionFactory().getCurrentSession().save(obj);
        } catch (Exception e) {
            System.err.println(e.getMessage() + "\nОшибка при UGroupsDAO::addObj");
            if (HibernateUtil.getSessionFactory().getCurrentSession().getTransaction() != null) {
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

            System.err.println(e.getMessage() + "\nОшибка при UGroupsDAO::updateObj(Object obj)");
            if (HibernateUtil.getSessionFactory().getCurrentSession().getTransaction() != null) {
                System.err.println("Trying to rollback database transaction after exception");
                HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().rollback();
            }
            throw new SQLException(e.getMessage());
        }

    }

    @Override
    public UsersGroups getObjById(Long obj_id) throws SQLException {

        UsersGroups ugrps = null;
        try {

            ugrps = (UsersGroups) HibernateUtil.getSessionFactory().getCurrentSession().load(UsersGroups.class, obj_id);
        } catch (Exception e) {
            System.err.println(e.getMessage() + "\nОшибка при UGroupsDAO::getObjById");
            if (HibernateUtil.getSessionFactory().getCurrentSession().getTransaction() != null) {
                System.err.println("Trying to rollback database transaction after exception");
                HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().rollback();
            }
            throw new SQLException(e.getMessage());
        }
        return ugrps;
    }

    @Override
    public Collection getAllObj() throws SQLException {
        List ugrps = new ArrayList<UsersGroups>();
        try {
            ugrps = HibernateUtil.getSessionFactory().getCurrentSession().createQuery("from UsersGroups ").list();
        } catch (Exception e) {
            System.err.println(e.getMessage() + "\nОшибка при  UGroupsDAO::getAllObj()");
            if (HibernateUtil.getSessionFactory().getCurrentSession().getTransaction() != null) {
                System.err.println("Trying to rollback database transaction after exception");
                HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().rollback();
            }
            throw new SQLException(e.getMessage());
        }
        return ugrps;
    }

    @Override
    public void deleteObj(Object obj) throws SQLException {

        try {

            HibernateUtil.getSessionFactory().getCurrentSession().delete(obj);

        } catch (Exception e) {
            System.err.println(e.getMessage() + "\nОшибка при UGroupsDAO::deleteObj(Object obj)");
            if (HibernateUtil.getSessionFactory().getCurrentSession().getTransaction() != null) {
                System.err.println("Trying to rollback database transaction after exception");
                HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().rollback();
            }
            throw new SQLException(e.getMessage());
        }
    }


    public UsersGroups getUGroupFromName(String name) throws SQLException {

        UsersGroups ug = null;
        try {
            ug = (UsersGroups) HibernateUtil.getSessionFactory().getCurrentSession().createCriteria(UsersGroups.class).add(Restrictions.eq("UGroupName", name)).uniqueResult();
        } catch (Exception e) {
            System.err.println(e.getMessage() + "\nОшибка при UGroupsDAO::getQGroupsFromName(Object obj)");
            if (HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().isActive()) {
                System.err.println("Trying to rollback database transaction after exception");
                HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().rollback();
            }
            HibernateUtil.getSessionFactory().getCurrentSession().flush();
            throw new SQLException(e.getMessage());
        }
        return ug;
    }
}
