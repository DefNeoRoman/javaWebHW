package servlets;

import resources.TestResource;
import sax.ReadXMLSax;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Logger;

public class ResourcePageServlet extends HttpServlet {
    static final Logger logger = Logger.getLogger(ResourcePageServlet.class.getName());
    public static final String PAGE_URL = "/resources";
    private ReadXMLSax readXMLSax;
    private TestResource testResource;

    public ResourcePageServlet(TestResource testResource) {
        this.testResource = testResource;
        readXMLSax = new ReadXMLSax();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().println("hello");

    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setStatus(HttpServletResponse.SC_OK);
        response.setContentType("text/html;charset=utf-8");
        response.getWriter().println("hello");
       String filePath = request.getParameter("path");
        logger.info(filePath);
        readFile(filePath);


    }

    private void readFile(String filePath) {
        TestResource testResource = (TestResource) readXMLSax.readXML(filePath);

        this.testResource = testResource;

    }
}
