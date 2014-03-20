package info.elfapp.testsystem;

import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;


public class TSystem {
	
	private UserQuestionsDAO userQuestionsDAO = new UserQuestionsDAO();
	private QuestionsDAO questionsDAO = new QuestionsDAO();
	
	public String getUserQuestions(int userID){
		JSONObject obj=(JSONObject) JSONValue.parse(userQuestionsDAO.getUserQuestions(userID, 0));
		JSONArray qarray = (JSONArray) obj.get("questions");
		JSONArray qtarray = new JSONArray();
		Iterator<Long> iterator = qarray.iterator();
		while(iterator.hasNext()){
			
			qtarray.add((JSONObject) JSONValue.parse(questionsDAO.getUserQuestion(iterator.next().intValue())));
		}
		JSONObject r = new JSONObject();
		r.put("questions", qtarray);
		return r.toString();
	}
}
