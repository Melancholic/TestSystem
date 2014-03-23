package info.elfapp.testsystem;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import java.sql.SQLException;
import java.util.Iterator;

import static info.elfapp.testsystem.Consts.Keys;
import static info.elfapp.testsystem.Consts.TYPE_CHOSE;


public class TSystem {

    private UserQuestionsDAO userQuestionsDAO = new UserQuestionsDAO();
    private QuestionsDAO questionsDAO = new QuestionsDAO();

    public String getUserQuestions(int userID) {
        JSONObject obj = (JSONObject) JSONValue.parse(userQuestionsDAO.getUserQuestions(userID, 0));
        JSONArray qarray = (JSONArray) obj.get("questions");
        JSONArray qtarray = new JSONArray();
        Iterator<Long> iterator = qarray.iterator();
        while (iterator.hasNext()) {

            qtarray.add((JSONObject) JSONValue.parse(questionsDAO.getUserQuestion(iterator.next().intValue())));
        }
        JSONObject r = new JSONObject();
        r.put("questions", qtarray);
        return r.toString();
    }

    public void CreateQuestions(JSONObject jsnonObj) throws SQLException {
        JSONObject quest = new JSONObject();
        JSONObject answ = new JSONObject();
        switch ((String)jsnonObj.get(Keys.TYPE)) {
            case Consts.TYPE_CHOSE: {
                quest.put(Keys.ANSWRS,(JSONArray)jsnonObj.get(Keys.ANSWRS));
                quest.put(Keys.QUESTION,(String)jsnonObj.get(Keys.QUESTION));
                quest.put(Keys.TYPE,TYPE_CHOSE);


                String t=(String)jsnonObj.get(Consts.СolumnsName.Questions.ANSWER);
                answ.put(Consts.СolumnsName.Questions.ANSWER,t);
            }
            break;
            case Consts.TYPE_TXT: {
                quest.put(Keys.QUESTION,(String)jsnonObj.get(Keys.QUESTION));
                quest.put(Keys.TYPE,Consts.TYPE_TXT);

                String t=(String)jsnonObj.get(Consts.СolumnsName.Questions.ANSWER);
                answ.put(Consts.СolumnsName.Questions.ANSWER,t);
            }
            break;
            default:
        }
        QuestionsDAO qDao=new QuestionsDAO();
        qDao.addQuestions(quest.toJSONString(),answ.toJSONString());



    }

}
