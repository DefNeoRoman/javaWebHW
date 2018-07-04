package servlets;

import resources.TestResource;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.logging.Logger;

public class ResourcePageServlet extends HttpServlet {
    static final Logger logger = Logger.getLogger(ResourcePageServlet.class.getName());
    public static final String PAGE_URL = "/resources";
    private TestResource testResource;

    public ResourcePageServlet(TestResource testResource) {
        this.testResource = testResource;
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       String filePath = request.getParameter("path");
        logger.info(filePath);
        readFile(filePath);


    }

    private void readFile(String filePath) {
        try (RandomAccessFile aFile = new RandomAccessFile(filePath, "rw")) {
            FileChannel inChannel = aFile.getChannel();
            ByteBuffer buf = ByteBuffer.allocate(500);
            inChannel.read(buf);
            final String s = new String(buf.array(), "ASCII");
            System.out.printf(s);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
