package dbService.servlets;

import dbService.DBException;
import dbService.DBService;
import dbService.dataSets.UsersDataSet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SignInServlet extends HttpServlet {
   private DBService dbService;

    public SignInServlet(DBService dbService) {
        this.dbService = dbService;
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
                response.getWriter().println("Unauthorized");
                return;
            }
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().println("Authorized: "+login);
            response.setStatus(HttpServletResponse.SC_OK);
        } catch (DBException e) {
            e.printStackTrace();
        }

    }
}
