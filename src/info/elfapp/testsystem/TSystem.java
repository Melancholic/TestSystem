package info.elfapp.testsystem;

import info.elfapp.testsystem.DAO.DAO;
import info.elfapp.testsystem.Maps.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.sql.SQLException;

import static info.elfapp.testsystem.Consts.Keys;
import static info.elfapp.testsystem.Consts.TYPE_CHOSE;


public class TSystem {

    public void CreateQuestions(JSONObject jsnonObj) throws SQLException {
        JSONObject quest = new JSONObject();
        JSONObject answ = new JSONObject();
        switch ((String) jsnonObj.get(Keys.TYPE)) {
            case Consts.TYPE_CHOSE: {
                quest.put(Keys.ANSWRS, (JSONArray) jsnonObj.get(Keys.ANSWRS));
                quest.put(Keys.QUESTION, ((String) jsnonObj.get(Keys.QUESTION)));
                quest.put(Keys.TYPE, TYPE_CHOSE);


                String t = (String) jsnonObj.get(Consts.СolumnsName.Questions.ANSWER);
                answ.put(Consts.СolumnsName.Questions.ANSWER, t);
            }
            break;
            case Consts.TYPE_TXT: {
                quest.put(Keys.QUESTION, (String) jsnonObj.get(Keys.QUESTION));
                quest.put(Keys.TYPE, Consts.TYPE_TXT);

                String t = (String) jsnonObj.get(Keys.ANSWRS);
                answ.put(Consts.СolumnsName.Questions.ANSWER, t);
            }
            break;
            default:
        }
        //QuestionsDAO qDao=new QuestionsDAO();
        System.err.println(quest.toJSONString() + "     " + answ.toJSONString());
        Questions q = new Questions(quest.toJSONString(), answ.toJSONString());
        String Qgparrent = (String) jsnonObj.get(Keys.GROUP);
        q.setGroup(DAO.getInstance().getQGroupsDAO().getQGroupsFromName(Qgparrent));
        DAO.getInstance().getQstsDAO().addObj(q);
        //qDao.addQuestions(quest.toJSONString(),answ.toJSONString());


    }

    public void CreateQGroup(JSONObject json) throws SQLException {
        QuestionsGroups qg = new QuestionsGroups((String) json.get("name"), (String) json.get("descript"));
        DAO.getInstance().getQGroupsDAO().addObj(qg);
    }

    public void CreateTest(JSONObject json) throws SQLException {
        System.err.println("CREATE TEST JSON: " + json.toJSONString());
        Tests t = new Tests((String) json.get("name"), (String) json.get("prop"));
        t.setTestDescript((String) json.get("descript"));
        DAO.getInstance().getTestsDAO().addObj(t);
        System.err.println("\n" + json.toJSONString() + "\n");
        System.err.println("\n" + json.toJSONString() + "\n");
        JSONArray jar = (JSONArray) json.get("groups");
        for (Object jo : jar) {
            TestsQGroups tqg = new TestsQGroups();
            tqg = new TestsQGroups(t,
                    DAO.getInstance().getQGroupsDAO().getQGroupsFromName((String) (((JSONObject) jo).get("g_name"))),
                    Integer.valueOf((String) ((JSONObject) jo).get("g_size")),
                    Integer.valueOf((String) ((JSONObject) jo).get("g_num")));
            DAO.getInstance().getTQGDAO().addObj(tqg);
            t.getTestQGroups().add(tqg);
        }
    }

    public void CreateUGroup(JSONObject json) throws SQLException {
        UsersGroups ug = new UsersGroups((String) json.get("name"), (String) json.get("descript"));
        DAO.getInstance().getUsrGroupsDAO().addObj(ug);
    }

    public void CreateUser(JSONObject json) throws SQLException {
        System.err.println("CREATE USEr JSON: " + json.toJSONString());
        Users u = new Users((String)json.get("name"),"");
        u.setGroup(DAO.getInstance().getUsrGroupsDAO().getUGroupFromName((String)json.get("group")));
        u.setUserPass((String)json.get("pswd"));
        u.setAdmin(((Boolean)json.get("admin")));
        DAO.getInstance().getUsrsDAO().addObj(u);
    }
   /* public static String String parameter) throws UnsupportedEncodingException {
        return new String(parameter.getBytes("ISO-8859-1"),"UTF8");
    }  */
}
