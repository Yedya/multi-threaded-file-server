package main.Parser;

import java.util.HashMap;
import java.util.Map;
import javax.xml.parsers.*;

import org.w3c.dom.*;

public class XMLParser implements ContextBuilder {

    private final String absolutePathToFile;
    private final String USERNAME = "username";
    private final String SERVER_HOST = "server-host";
    private final String DOWNLOAD_DIRECTORY = "download-dir";
    private final String SERVER_PORT = "server-port";

    //constructor
    public XMLParser(String absolutePathToFile) {
        this.absolutePathToFile = absolutePathToFile;
    }

    //parses the file and creates a context in the form of a Map
    public Map<String, String> buildContext() throws ContextBuildException {

        DocumentBuilderFactory dbf;
        DocumentBuilder db;
        Document doc;

        /* These three lines are part of JAXP (Java API for XML Processing) and are designed to
         * completely encapsulate how a DOM node tree in manufactured. The concrete classes that
         * are doing the actual work are part of the Apache Xerces project. JAXP shields us from
         * having to know and understand the complexity of Xerces through encapsulating the
         * process.
          */

        try {
            dbf = DocumentBuilderFactory.newInstance();
            db = dbf.newDocumentBuilder();
            doc = db.parse(absolutePathToFile);
        } catch (Throwable e) {
            e.printStackTrace();
            throw new ContextBuildException("Failure building Context");
        }

        /* The type Document in the line above is an org.w3c.dom.Document. From this
         * point on in the method, we will only use the DOM "STANDARD" to do the
		 * processing. DOM is language neutral and completely abstract. As a result,
		 * it is completely stable! The DOM standard hasn't changed much since the
		 * year 2000...! Abstractions are highly stable and can be relied upon.
		 */
        Element root = doc.getDocumentElement(); //Get the root of the node tree
        NodeList children = root.getChildNodes(); //Get the child node of the root

        //Map contains context values
        Map<String, String> ctx = new HashMap<>();

        //Get username from root node attribute
        ctx.put(USERNAME, root.getAttribute(USERNAME));

        //Find the elements database and driver
        for (int i = 0; i < children.getLength(); i++){ //Loop over the child nodes
            Node next = children.item(i); //Get the next child

            if (next instanceof Element){ //Check if it is an element node. There are 12 different types of Node
                Element e = (Element) next; //Cast the general node to an element node

                ctx.put(e.getNodeName(), e.getFirstChild().getNodeValue());
            }
        }

        return ctx;
    }
}
