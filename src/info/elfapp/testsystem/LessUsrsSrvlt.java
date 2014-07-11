package info.elfapp.testsystem;

import info.elfapp.testsystem.DAO.DAO;
import info.elfapp.testsystem.Maps.Users;
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
@WebServlet("/LessUsrsSrvlt")
public class LessUsrsSrvlt extends HttpServlet {
    private TSystem tsystem;

    public LessUsrsSrvlt() {
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
        JSONArray jar = new JSONArray();

        String grp = request.getParameter("group");
        try {
            if (grp != null && !grp.equals("All users")) {
                System.err.println("VAL: " + grp);
                UsersGroups ug = DAO.getInstance().getUsrGroupsDAO().getUGroupFromName(grp);
                //for(Questions q:(ArrayList<Questions>)DAO.getInstance().getQstsDAO().getAllObj()){/*DAO.getInstance().getQGroupsDAO().getQGroupsFromName(request.getParameter("group")).getQGroupName())){ */
                if (ug != null) {
                    for (Users u : ug.getUsrs()) {
                        JSONObject j = new JSONObject();
                        j.put("id", u.getIdUsers());
                        j.put("name", u.getUserName());
                        j.put("group", u.getGroup().getUGroupName());
                        j.put("admin", (u.isAdmin() ? "1" : "0"));
                        jar.add(j);
                    }
                }
            } else {

                for (Users u : (ArrayList<Users>) DAO.getInstance().getUsrsDAO().getAllObj()) {
                    if (!u.getUserName().equals("admin")) {
                        JSONObject j = new JSONObject();
                        j.put("id", u.getIdUsers());
                        j.put("name", u.getUserName());
                        j.put("group", u.getGroup().getUGroupName());
                        j.put("admin", (u.isAdmin() ? "1" : "0"));
                        jar.add(j);
                    }
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        JSONObject res = new JSONObject();
        res.put("users", jar);
        System.err.println("RES: " + res.toJSONString());
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
