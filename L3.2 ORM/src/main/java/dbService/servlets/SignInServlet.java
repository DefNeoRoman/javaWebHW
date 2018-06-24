package dbService.servlets;

import com.google.gson.Gson;
import dbService.DBException;
import dbService.DBService;
import dbService.dataSets.UsersDataSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SignInServlet extends HttpServlet {
   private DBService dbService;
   private String path;

    public SignInServlet(DBService dbService) {
        this.dbService = dbService;
    }



    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String sessionId = request.getParameter("userID");
        UsersDataSet usersDataSet = null;
        try {
            usersDataSet = dbService.getUser(Long.valueOf(sessionId));
            if (usersDataSet == null) {
                response.setContentType("text/html;charset=utf-8");
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            } else {
                Gson gson = new Gson();
                String json = gson.toJson(usersDataSet);
                response.setContentType("text/html;charset=utf-8");
                response.getWriter().println(json);
                response.setStatus(HttpServletResponse.SC_OK);
            }
        } catch (DBException e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws  IOException {

        String login = request.getParameter("login");
        String pass = request.getParameter("password");


        if (login == null || pass == null) {
            response.setContentType("text/html;charset=utf-8");
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        UsersDataSet profile = null;
        try {
            profile = dbService.getUserByLogin(login);
            if (profile == null || !profile.getPassword().equals(pass)) {
                response.setContentType("text/html;charset=utf-8");
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                return;
            }
            dbService.addUser(profile);
            Gson gson = new Gson();
            String json = gson.toJson(profile);
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().println("Authorized: "+login);
            response.setStatus(HttpServletResponse.SC_OK);
        } catch (DBException e) {
            e.printStackTrace();
        }

    }
}
