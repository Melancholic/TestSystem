package info.elfapp.testsystem;


import org.hibernate.SessionFactory;
import org.hibernate.StaleObjectStateException;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;


/**
 * Servlet Filter implementation class AuthenticationFilter
 */
@WebFilter("/HibernateFltr")
public class HibernateFltr implements Filter {
    private SessionFactory sf;

    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain chain)
            throws IOException, ServletException {

        try {

            System.err.println("Starting a database transaction");
            sf.getCurrentSession().beginTransaction();
            // Call the next filter (continue request processing)
            chain.doFilter(request, response);
            // Commit and cleanup

            if (sf.getCurrentSession().getTransaction().isActive()) {
                System.err.println("Committing the database transaction");
                sf.getCurrentSession().getTransaction().commit();
            }else{
                System.err.println("The database transaction could not commiting after exception  ");
            }
        } catch (StaleObjectStateException staleEx) {
            System.err.println("This interceptor does not implement optimistic concurrency control!");
            System.err.println("Your application will not work until you add compensation actions!");
            // Rollback, close everything, possibly compensate for any permanent changes
            // during the conversation, and finally restart business conversation. Maybe
            // give the user of the application a chance to merge some of his work with
            // fresh data... what you do here depends on your applications design.
            throw staleEx;
        } catch (Throwable ex) {
            // Rollback only
            ex.printStackTrace();
            try {
                if (sf.getCurrentSession().getTransaction().isActive()) {
                    System.err.println("Trying to rollback database transaction after exception");
                    sf.getCurrentSession().getTransaction().rollback();
                }
            } catch (Throwable rbEx) {
                System.err.println("Could not rollback transaction after exception!" + rbEx.getMessage());
            }
            // Let others handle it... maybe another interceptor for exceptions?
            throw new ServletException(ex);
        }
    }

    public void init(FilterConfig filterConfig) throws ServletException {
        System.err.println("Initializing filter...");
        System.err.println("Obtaining SessionFactory from static HibernateUtil singleton");
        sf = HibernateUtil.getSessionFactory();
    }

    public void destroy() {
    }


}
