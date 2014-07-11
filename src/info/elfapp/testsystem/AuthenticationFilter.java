package info.elfapp.testsystem;

//import info.elfapp.testsystem.UserDAO.UserBean;


import info.elfapp.testsystem.DAO.UsersDAO;
import info.elfapp.testsystem.Maps.Users;
import org.hibernate.Hibernate;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 * Servlet Filter implementation class AuthenticationFilter
 */
@WebFilter("/AuthenticationFilter")
public class AuthenticationFilter implements Filter {
   
	//private UserDAO dao;

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
         
        String uri = req.getRequestURI();
        System.err.println("REQUEST: "+uri);
        if((uri.endsWith("html") || uri.endsWith("LoginServlet"))&&!uri.endsWith("adm.html") ){
        	chain.doFilter(request, response);
        	return;
        }
        
        HttpSession session = req.getSession(false);
        
        if(session==null){
        	res.sendRedirect("login.html");
        	return;
        }
        Long userId = (Long) session.getAttribute("userId");
        if (userId==null){
    		res.sendRedirect("login.html");
    		return;
    	}
        Users user=null;
        try {
             user = (new UsersDAO()).getObjById(userId);
        } catch (SQLException e) {
            e.printStackTrace();
            res.sendRedirect("login.html");
            return;
        }
        if(user!=null && !user.isAdmin()){
            if(uri.endsWith("adm.html")){
               res.sendRedirect("user.html");
                return;
            }
        }
        /*UserBean user = dao.getUserByID(userId);  */
        Hibernate.initialize(user.getSession());
        System.err.println("SESSION: " + user.getSession());
        if(user!=null && session.getId().equals(user.getSession())){
        	chain.doFilter(request, response);
        } else {
        	res.sendRedirect("login.html");
        }  //*/

	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
			//dao = new UserDAO();
	}

}
