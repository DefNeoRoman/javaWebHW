package main;


import resources.DBParametersResource;
import sax.ReadXMLFileSAX;

import java.io.File;
import java.net.URL;

/**
 * @author v.chibrikov
 *         <p>
 *         Пример кода для курса на https://stepic.org/
 *         <p>
 *         Описание курса и лицензия: https://github.com/vitaly-chibrikov/stepic_java_webserver
 */
public class Main {
    public static void main(String[] args) throws Exception {
        String property = System.getProperty("user.dir");

        File file = new File("/");
        System.out.println(file.getAbsolutePath().toString());
        DBParametersResource resource = (DBParametersResource) ReadXMLFileSAX.readXML(property+"/L6.3 XML/"+"data/MySqlResource.xdb");
        System.out.println(resource);
    }
}
