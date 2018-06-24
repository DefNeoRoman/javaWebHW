package main;


import dbService.DBService;
import dbService.dataSets.UsersDataSet;
import dbService.servlets.SignInServlet;
import dbService.servlets.SignUpServlet;
import dbService.servlets.TestServlet;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

/**
 * @author v.chibrikov
 * <p>
 * Пример кода для курса на https://stepic.org/
 * <p>
 * Описание курса и лицензия: https://github.com/vitaly-chibrikov/stepic_java_webserver
 */
public class Main {
    public static void main(String[] args) {
        DBService dbService = new DBService();
        dbService.printConnectInfo();

        try {
            long userId = dbService.addUser(new UsersDataSet("admin","admin"));
            System.out.println("Added user id: " + userId);

            UsersDataSet dataSet = dbService.getUser(userId);
            System.out.println("User data set: " + dataSet);
            ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);

            context.addServlet(new ServletHolder(new SignInServlet(dbService)), "/signin");
            context.addServlet(new ServletHolder(new SignUpServlet(dbService)), "/signup");
            context.addServlet(new ServletHolder(new TestServlet()), "/test");

            ResourceHandler resource_handler = new ResourceHandler();
            resource_handler.setResourceBase("public_html");
            HandlerList handlers = new HandlerList();
            handlers.setHandlers(new Handler[]{resource_handler, context});
            Server server = new Server(8080);
            server.setHandler(handlers);
            server.start();
            System.out.println("Server started");
            server.join();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
