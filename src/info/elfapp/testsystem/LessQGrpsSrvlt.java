package info.elfapp.testsystem;

import info.elfapp.testsystem.DAO.DAO;
import info.elfapp.testsystem.Maps.Questions;
import info.elfapp.testsystem.Maps.QuestionsGroups;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

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
@WebServlet("/LessQGrpsSrvlt")
public class LessQGrpsSrvlt extends HttpServlet {
    private TSystem tsystem;

    public LessQGrpsSrvlt() {
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

        try {
             ArrayList<QuestionsGroups> arq=   (ArrayList<QuestionsGroups>)DAO.getInstance().getQGroupsDAO().getAllObj();
            for(QuestionsGroups q:arq){
                JSONObject j=new JSONObject();
                j.put("name",q.getQGroupName());
                j.put("size",q.getQuests().size());
                 j.put("id",q.getQGroupID());
                jar.add(j);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        JSONObject res = new JSONObject();
        res.put("groups",jar);
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
