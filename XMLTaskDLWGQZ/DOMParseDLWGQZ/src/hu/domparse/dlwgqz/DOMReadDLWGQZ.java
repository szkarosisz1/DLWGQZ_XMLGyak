package hu.domparse.dlwgqz;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.*;


public class DOMReadDLWGQZ {
    
	public static void main(String[] args) {
		// Metódus meghívása
		readXMLDLWGQZDocument("./XMLDLWGQZ.xml");	
	}
	
	private static void readXMLDLWGQZDocument(String filePath) {
		try{
			// Fájl beolvasása
			File xmlFile = new File(filePath);
			
			// DocumentBuilder inicializálása
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = factory.newDocumentBuilder();
			Document document = dBuilder.parse(xmlFile);
			
			document.getDocumentElement().normalize();
		
			// Gyökérelem kiiratása a konzolra
			System.out.println("Gyökérelem: " + document.getDocumentElement().getNodeName());
		
			// Csapat elemek
		    NodeList nodeList = document.getElementsByTagName("Csapat");
		    printNodeList(nodeList);
		
		    // Logó elemek
		    nodeList = document.getElementsByTagName("Logó"); 
		    printNodeList(nodeList);
		
		    // Csoport elemek
		    nodeList = document.getElementsByTagName("Csoport");
		    printNodeList(nodeList);
		
		    // Labdarúgó elemek
		    nodeList = document.getElementsByTagName("Labdarúgó");
		    printNodeList(nodeList);
		
		    // Mérkőzés elemek
		    nodeList = document.getElementsByTagName("Mérkőzés");
		    printNodeList(nodeList);
		
		    // Helyszín elemek
		    nodeList = document.getElementsByTagName("Helyszín"); 
		    printNodeList(nodeList);
			    
		    // Típus elemek
		    nodeList = document.getElementsByTagName("Típus");
		    printNodeList(nodeList);
		    
		    // Mérkőzik elemek
		    nodeList = document.getElementsByTagName("Mérkőzik"); 
		    printNodeList(nodeList);

		    // Gyökérelem kiíratása a konzolra
		    Element rootElement = document.getDocumentElement();
		    System.out.println("Gyökérelem: " + rootElement.getNodeName());	        
		} catch (Exception e) {
		    e.printStackTrace();
		}
	}
	
	private static void printNodeList(NodeList nodeList) {
		for (int i = 0; i < nodeList.getLength(); i++) {
			Node nNode = nodeList.item(i);
		    System.out.println("\nJelenlegi elem: " + nNode.getNodeName());
	
		    if (nNode.getNodeType() == Node.ELEMENT_NODE) {
		    	Element element = (Element) nNode;
		    	System.out.println("Elemhez tartozó attribútumok: ");
		        for (int j = 0; j < element.getAttributes().getLength(); j++) {
		        	Node attr = element.getAttributes().item(j);
		            System.out.println(" ===== " + attr.getNodeName() + ": " + attr.getNodeValue());
		        }
		
		        NodeList children = element.getChildNodes();
		        System.out.println("Gyerek elemei:");
		        for (int j = 0; j < children.getLength(); j++) {
		        	
		        	Node child = children.item(j);
		       
		            if (child.getNodeType() == Node.ELEMENT_NODE) 
		            {	            	
		            	System.out.println(" ===== " + child.getNodeName() + ": " + child.getTextContent());		                    
		            }
		        }
		    }
		    
		    System.out.println("=======================");
		}	
	}
}

