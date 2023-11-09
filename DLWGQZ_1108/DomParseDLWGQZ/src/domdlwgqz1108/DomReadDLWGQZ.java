package domdlwgqz1108;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class DomReadDLWGQZ  {
    public static void main(String arg[]) throws ParserConfigurationException, SAXException, IOException {
        try {
            File xmlFile = new File("orarendDLWGQZ.xml");
        
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xmlFile);
            doc.getDocumentElement().normalize();
   
            Element gyokerElem = doc.getDocumentElement();
            System.out.println("Gyökér elem: " + gyokerElem.getNodeName());
            
            kiirTartalom(gyokerElem, "");
                        
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void kiirTartalom(Node node, String indent) {
	    if (node.getNodeType() == Node.ELEMENT_NODE) {
	        System.out.println(indent + "Elem: " + node.getNodeName());

	        if (node.hasAttributes()) {
	            NamedNodeMap attrib = node.getAttributes();
	            for (int i = 0; i < attrib.getLength(); i++) {
	                Node attribute = attrib.item(i);
	                System.out.println(indent + "  Attribútum: " + attribute.getNodeName() + " = " + attribute.getNodeValue());
	            }
            }
	        if (node.hasChildNodes()) {
	            NodeList gyerek = node.getChildNodes();
	            for (int i = 0; i < gyerek.getLength(); i++) {
	                Node child = gyerek.item(i);
	                kiirTartalom(child, indent + "  ");
	                }
	        }
	        } else if (node.getNodeType() == Node.TEXT_NODE) {
	            String datas = node.getNodeValue().trim();
	            if (!datas.isEmpty()) {
	                System.out.println(indent + "Tartalom: " + datas);
	            }
	        }
	}

}
