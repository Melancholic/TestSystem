package info.elfapp.testsystem;


import info.elfapp.testsystem.DAO.QGroupsDAO;
import info.elfapp.testsystem.DAO.UGroupsDAO;
import info.elfapp.testsystem.DAO.*;
import info.elfapp.testsystem.Maps.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

//import info.elfapp.testsystem.UserDAO.UserBean;


/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    //private UserDAO dao;

    public void init() throws ServletException {
       // dao = new UserDAO();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html; charset=UTF-8");

        String name = request.getParameter("user");
        String pwd = request.getParameter("pwd");
       // testDB(request,response);

        if (name == null || pwd == null) {
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/login.html");
            PrintWriter out = response.getWriter();
            out.println("<font color=red>Either user name or password is wrong.</font>");
            rd.include(request, response);
            return;
        }
        Users user = null;
        try {
            user = (new UsersDAO()).getUsrFromName(name);
        } catch (SQLException e) {
            e.printStackTrace();
        }
           /* UserBean user = dao.getUserByName(name); */

        if (user != null && (user.getUserPass()).equals(pwd)) {
            HttpSession session = request.getSession();
            session.setAttribute("userId", user.getIdUsers());
            session.setMaxInactiveInterval(30 * 60);
            //dao.setUserSession(user.getID(), session.getId());
            user.setSession(session.getId());
            try {
                (new UsersDAO()).updateObj(user);

            } catch (SQLException e) {
                e.printStackTrace();
            }

            //response.sendRedirect("LoginSuccess.jsp");
            response.sendRedirect("adm.html");
        } else {
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/login.html");
            PrintWriter out = response.getWriter();
            rd.include(request, response);
            out.println("<script> alert(\"Неправильный логин или пароль!\");</script>");
            return;
        }

    }

}
