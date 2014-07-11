package info.elfapp.testsystem;

import info.elfapp.testsystem.DAO.DAO;
import info.elfapp.testsystem.DAO.QGroupsDAO;
import info.elfapp.testsystem.Maps.Questions;
import info.elfapp.testsystem.Maps.QuestionsGroups;
import info.elfapp.testsystem.Maps.Users;
import info.elfapp.testsystem.Maps.UsersQuests;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Servlet implementation class GetQuestions
 */
@WebServlet("/LessQuestSrvlt")
public class LessQuestSrvlt extends HttpServlet {
    private TSystem tsystem;

    public LessQuestSrvlt() {
        super();
    }

    /**
     * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        response.setContentType("application/json; charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        JSONArray jar=new JSONArray();

        String grp= request.getParameter("group");
        try {
            if(grp!=null && !grp.equals("All questions")){
                System.err.println("VAL: "+grp);
               QuestionsGroups qg=DAO.getInstance().getQGroupsDAO().getQGroupsFromName(grp);
                //for(Questions q:(ArrayList<Questions>)DAO.getInstance().getQstsDAO().getAllObj()){/*DAO.getInstance().getQGroupsDAO().getQGroupsFromName(request.getParameter("group")).getQGroupName())){ */
                if(qg!=null){
                for(Questions q : qg.getQuests()){
                    JSONObject j=new JSONObject();
                    j.put("id",q.getQuestId());
                    j.put("txt",q.getQuestTxt());
                    j.put("answ",q.getQuestAnswr());
                    jar.add(j);
                    }
                }
                }else{

            for(Questions q:(ArrayList<Questions>)DAO.getInstance().getQstsDAO().getAllObj()){
                JSONObject j=new JSONObject();
                j.put("id",q.getQuestId());
                j.put("txt",q.getQuestTxt());
                j.put("answ",q.getQuestAnswr());
                jar.add(j);
            }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        JSONObject res = new JSONObject();
        res.put("quests",jar);
        System.err.println("RES: "+res.toJSONString());
        out.write(res.toJSONString());

    }

    /**
     * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
    }

    public void init() throws ServletException {
        tsystem = new TSystem();
    }
}
