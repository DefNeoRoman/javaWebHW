package sax;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;


public class ReadXMLSax {
    public Object readXML(String xmlFile) {
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();

            SaxHandler handler = new SaxHandler();

            saxParser.parse(getClass().getResource(xmlFile).getFile(), handler);


            return handler.getObject();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }

}
