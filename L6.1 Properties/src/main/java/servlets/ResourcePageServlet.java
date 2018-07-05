package servlets;

import resources.TestResource;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Properties;
import java.util.logging.Logger;

public class ResourcePageServlet extends HttpServlet {
    static final Logger logger = Logger.getLogger(ResourcePageServlet.class.getName());
    public static final String PAGE_URL = "/resources";
    private TestResource testResource;

    public ResourcePageServlet(TestResource testResource) {
        this.testResource = testResource;
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
        Properties properties = new Properties();
        System.out.println(new File("").getAbsolutePath());
        System.out.println(new File(getClass().getClassLoader().getResource("").getFile()).getAbsolutePath());

    try (InputStream input = getClass().getClassLoader().getResourceAsStream(filePath)) {
            properties.load(input);
            System.out.println(properties.getProperty("database"));
            System.out.println(properties.getProperty("dbuser"));
            System.out.println(properties.getProperty("dbpassword"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
