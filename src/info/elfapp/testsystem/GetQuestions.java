package info.elfapp.testsystem;

import info.elfapp.testsystem.DAO.DAO;
import info.elfapp.testsystem.Maps.Users;
import info.elfapp.testsystem.Maps.UsersQuests;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class GetQuestions
 */
@WebServlet("/GetQuestions")
public class GetQuestions extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private TSystem tsystem;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetQuestions() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Long userID = (Long) session.getAttribute("userId");
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter out = response.getWriter();

        Users usr;
        try {
            usr = DAO.getInstance().getUsrsDAO().getObjById(Long.valueOf(userID));
        for(UsersQuests uq: (ArrayList<UsersQuests>)DAO.getInstance().getUQstDAO().getObjByUser(usr)){
            System.out.println(uq.getQuestion().getQuestId());
            out.write(String.valueOf(uq.getQuestion().getQuestId()));
        }
        //System.out.println(tsystem.getUserQuestions(userID));
		//out.write(tsystem.getUserQuestions(userID));
        } catch (SQLException e) {
            e.printStackTrace();
        }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

    public void init() throws ServletException {
    	tsystem = new TSystem();
    }
}
