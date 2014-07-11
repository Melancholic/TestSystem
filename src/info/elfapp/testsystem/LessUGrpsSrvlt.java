package info.elfapp.testsystem;

import info.elfapp.testsystem.DAO.DAO;
import info.elfapp.testsystem.Maps.QuestionsGroups;
import info.elfapp.testsystem.Maps.UsersGroups;
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
@WebServlet("/LessUGrpsSrvlt")
public class LessUGrpsSrvlt extends HttpServlet {
    private TSystem tsystem;

    public LessUGrpsSrvlt() {
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
             ArrayList<UsersGroups> ugs=   (ArrayList<UsersGroups>)DAO.getInstance().getUsrGroupsDAO().getAllObj();
            for(UsersGroups  ug:ugs){
                JSONObject j=new JSONObject();
                j.put("name",ug.getUGroupName());
                j.put("size",ug.getUsrs().size());
                 j.put("id",ug.getUGroupID());
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
