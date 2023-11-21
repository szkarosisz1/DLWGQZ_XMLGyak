package hu.domparse.dlwgqz;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.*;


public class DOMReadDLWGQZ {
    
	 public static void main(String[] args) {
	        // XML dokumentum beolvasása és feldolgozása
	        readXMLocument("./XMLDLWGQZ.xml");
	    }

	 private static void readXMLocument(String filePath) {
	     try {
	         // Fájl beolvasása
	         File xmlFile = new File(filePath);

	         // DocumentBuilder inicializálása
	         DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	         DocumentBuilder documentBuilder = factory.newDocumentBuilder();
	         Document document = documentBuilder.parse(xmlFile);

	         document.getDocumentElement().normalize();

	         // Gyökérelem kiiratása a konzolra
	         System.out.println("Gyökérelem: " + document.getDocumentElement().getNodeName());

	         // Elemek feldolgozása
	         processXMLElements(document, "Csapat");
	         processXMLElements(document, "Logó");
	         processXMLElements(document, "Csoport");
	         processXMLElements(document, "Labdarúgó");
	         processXMLElements(document, "Mérkőzés");
	         processXMLElements(document, "Helyszín");
	         processXMLElements(document, "Típus");
	         processXMLElements(document, "Mérkőzik");

	     } catch (Exception e) {
	         e.printStackTrace();
	     }
	 }

	 private static void processXMLElements(Document document, String elementName) {
	     NodeList nodeList = document.getElementsByTagName(elementName);
	     printNodeList(nodeList);
	 }

	 private static void printNodeList(NodeList nodeList) {
	     for (int i = 0; i < nodeList.getLength(); i++) {
	         Node node = nodeList.item(i);
	         System.out.println("\nJelenlegi elem: " + node.getNodeName());

	         if (node.getNodeType() == Node.ELEMENT_NODE) {
	             Element element = (Element) node;
	             System.out.println("Elemhez tartozó attribútumok: ");
	             for (int j = 0; j < element.getAttributes().getLength(); j++) {
	                 Node attr = element.getAttributes().item(j);
	                 System.out.println(" ===== " + attr.getNodeName() + ": " + attr.getNodeValue());
	             }

	             NodeList children = element.getChildNodes();
	             System.out.println("Gyerek elemei:");
	             for (int j = 0; j < children.getLength(); j++) {
	                 Node child = children.item(j);

	                 if (child.getNodeType() == Node.ELEMENT_NODE) {
	                     System.out.println(" ===== " + child.getNodeName() + ": " + child.getTextContent());
	                 }
	             }
	         }

	         System.out.println("=======================");
	     }
	}
}

