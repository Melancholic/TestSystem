package info.elfapp.testsystem.DAO;

import info.elfapp.testsystem.Maps.TestInstance;
import info.elfapp.testsystem.Maps.UsersTI;

/**
 * Created by sosnov on 19.04.14.
 */
public class DAO {
    private static UGroupsDAO ugroupsDAO = null;
    private static UsersDAO usrsDAO = null;
    private static QuestionsDAO qstsDAO= null;
    private static QGroupsDAO qgroupsDAO = null;
    private static TestsDAO testsDAO=null;
    private static UserQuestsDAO uQstDAO=null;
    private static TestInstanceDAO tiDAO=null;
    private static TestsQGroupsDAO tqgDAO=null;
    private static UsersTIDAO utiDAO=null;
    private static DAO connector = null;

    public UGroupsDAO getUsrGroupsDAO() {
        if (ugroupsDAO == null) {
            ugroupsDAO = new UGroupsDAO();
        }
        return ugroupsDAO;
    }

    public UsersDAO getUsrsDAO() {
        if (usrsDAO == null) {
            usrsDAO = new UsersDAO();
        }
        return usrsDAO;
    }

    public  QGroupsDAO getQGroupsDAO() {
        if (qgroupsDAO == null) {
            qgroupsDAO = new QGroupsDAO();
        }
        return qgroupsDAO;
    }

    public  QuestionsDAO getQstsDAO() {
        if (qstsDAO == null) {
            qstsDAO = new QuestionsDAO();
        }
        return qstsDAO;
    }

    public  TestsDAO getTestsDAO() {
        if (testsDAO == null) {
            testsDAO = new TestsDAO();
        }
        return testsDAO;
    }

    public  TestsQGroupsDAO getTQGDAO() {
        if (tqgDAO == null) {
            tqgDAO = new TestsQGroupsDAO();
        }
        return tqgDAO;
    }

    public UserQuestsDAO getUQstDAO() {
        if (uQstDAO == null) {
            uQstDAO = new UserQuestsDAO();
        }
        return uQstDAO;
    }

    public TestInstanceDAO getTIDAO() {
        if (tiDAO == null) {
            tiDAO = new TestInstanceDAO();
        }
        return tiDAO;
    }

    public UsersTIDAO getUtiDAO() {
        if (utiDAO == null) {
            utiDAO = new UsersTIDAO();
        }
        return utiDAO;
    }



    public static synchronized DAO getInstance() {
        if (connector == null) {
            connector = new DAO();
        }
        return connector;
    }
}