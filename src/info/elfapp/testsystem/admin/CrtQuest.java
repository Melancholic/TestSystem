package info.elfapp.testsystem.admin;

import info.elfapp.testsystem.TSystem;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

/**
 * Created by sosnov on 22.03.14.
 */
@WebServlet("/CrtQuest")
public class CrtQuest extends HttpServlet {
    public void init() throws ServletException {
    }

    /**
     * @see HttpServlet#doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /*
        * {"question":"how much","type":"text", "Answer" :"256"}
        * */
        String jsonStrIn = request.getParameter("data");
        JSONObject jsonObj = null;
        response.setContentType("text/html");
        try {
            jsonObj = (JSONObject) (new JSONParser()).parse(jsonStrIn);
        } catch (ParseException e) {
            throw new ServletException(e.getMessage());
        }

        TSystem tSstm = new TSystem();
        try {
            tSstm.CreateQuestions(jsonObj);
        } catch (SQLException e) {
            RequestDispatcher rd = getServletContext().getRequestDispatcher("CreateQuestions.html");
            PrintWriter out = response.getWriter();
            out.println("<font color=red>Error #" + e.getErrorCode() + ": " + e.getMessage() + "</font>");
            rd.include(request, response);
            return;
        }

            RequestDispatcher rd = getServletContext().getRequestDispatcher("/LoginSuccess.jsp");
            PrintWriter out = response.getWriter();
            out.println("<font color=green>Question has been added!</font>");
            rd.include(request, response);
        return;
    }

}

