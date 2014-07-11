package info.elfapp.testsystem;

import info.elfapp.testsystem.DAO.DAO;
import info.elfapp.testsystem.Maps.QuestionsGroups;
import info.elfapp.testsystem.Maps.Tests;
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
@WebServlet("/LessTstsSrvlt")
public class LessTstsSrvlt extends HttpServlet {
    private TSystem tsystem;

    public LessTstsSrvlt() {
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
             ArrayList<Tests> tst=   (ArrayList<Tests>)DAO.getInstance().getTestsDAO().getAllObj();
            for(Tests t:tst){
                JSONObject j=new JSONObject();
                j.put("size", DAO.getInstance().getTestsDAO().getSize(t));
                j.put("name",t.getTestName());
                j.put("id", t.getTestID());
                JSONObject tmp = (JSONObject) new JSONParser().parse(t.getTestProp());
                j.put("dur",tmp.get("dur"));
                j.put("status",t.getInstances().size());
                jar.add(j);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        JSONObject res = new JSONObject();
        res.put("tsts",jar);
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
