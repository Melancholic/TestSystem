package info.elfapp.testsystem;

import info.elfapp.testsystem.DAO.DAO;
import info.elfapp.testsystem.Maps.QuestionsGroups;
import info.elfapp.testsystem.Maps.Tests;
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

/**
 * Servlet implementation class GetQuestions
 */
@WebServlet("/DeleteTstsSrvlt")
public class DeleteTstsSrvlt extends HttpServlet {
    private TSystem tsystem;

    public DeleteTstsSrvlt() {
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
        JSONObject res = new JSONObject();
            if(request.getParameter("id")!=null){
                try {
                    Tests q = DAO.getInstance().getTestsDAO().getObjById(Long.decode(request.getParameter("id")));
                    DAO.getInstance().getTestsDAO().deleteObj(q);
                    res.put("res","ok");
                } catch (SQLException e) {
                    e.printStackTrace();
                    res.put("res", "err");
                    res.put("txt", e.getMessage());
                }
            }

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
