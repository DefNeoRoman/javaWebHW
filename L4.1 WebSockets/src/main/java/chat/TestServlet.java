package chat;



import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class TestServlet extends HttpServlet {

    public TestServlet() {
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
        HashMap pageVariables = new HashMap();
        response.getWriter().println(PageGenerator.instance().getPage("index.html", pageVariables));

        System.out.println(PageGenerator.instance().getPage("index.html", pageVariables));
    }
}
