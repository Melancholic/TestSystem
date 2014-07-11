package info.elfapp.testsystem;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.lwjgl.Sys;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.sql.SQLException;

/**
 * Servlet implementation class GetQuestions
 */
@WebServlet("/CrtQGroupSrvlt")
public class CrtQGroupSrvlt extends HttpServlet {
    private TSystem tsystem;

    public CrtQGroupSrvlt() {
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
        String param=request.getParameter("qgrp_crt_json");
        if (param != null) {
            JSONObject json = null;
            try {
               json = (JSONObject) new JSONParser().parse(param);
                System.err.println("\n JSON: "+param);

            } catch (ParseException e) {
                e.printStackTrace();
            }
            JSONObject res = new JSONObject();

            try {
                  tsystem.CreateQGroup(json);

                res.put("res","ok");
                out.append(res.toJSONString());
            } catch (SQLException e) {
                res.put("res","err");
                res.put("txt",e.getMessage());
                out.append(res.toJSONString());
            }

        }

        return;
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
