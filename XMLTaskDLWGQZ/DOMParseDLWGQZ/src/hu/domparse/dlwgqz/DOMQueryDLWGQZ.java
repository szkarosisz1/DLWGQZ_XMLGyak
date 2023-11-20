package hu.domparse.dlwgqz;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.*;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

public class DOMQueryDLWGQZ {

	public static void main(String[] args) {
		//Metódusok meghívása
		taskOneQueryXMLDLWGQZDocument("./XMLDLWGQZ.xml");
		taskTwoQueryXMLDLWGQZDocument("./XMLDLWGQZ.xml");
		taskThreeQueryXMLDLWGQZDocument("./XMLDLWGQZ.xml");
		taskFourQueryXMLDLWGQZDocument("./XMLDLWGQZ.xml");
		taskFiveQueryXMLDLWGQZDocument("./XMLDLWGQZ.xml");
		
	}
	
	private static void taskFiveQueryXMLDLWGQZDocument(String filePath) {
		try {
			//Új sor a konzolra
			System.out.println();
			 
			// Fájl beolvasása
			File xmlFile = new File(filePath);
			 
			//DocumentBuilder inicializálása
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	        Document document = dBuilder.parse(xmlFile);
	         
	        //5. Feladat kiiratása a konzolra
		    System.out.println("<5. Feladat: Írassa ki a Logó elemek összes adatát!>");
		     
		    // Logó elemek létrehozása
	        NodeList logoList = document.getElementsByTagName("Logó");

	        for (int i = 0; i < logoList.getLength(); i++) {
	            Node logoNode = logoList.item(i);

	            if (logoNode.getNodeType() == Node.ELEMENT_NODE) {
	                Element logoElement = (Element) logoNode;

	                String logokod = logoElement.getAttribute("LOGÓkód");
	                System.out.println("Logókód: " + logokod);

	                NodeList SzinekList = logoElement.getElementsByTagName("Színek");
	                for (int j = 0; j < SzinekList.getLength(); j++) {
	                    Node SzinNode = SzinekList.item(j);
	                    if (SzinNode.getNodeType() == Node.ELEMENT_NODE) {
	                        Element SzinElement = (Element) SzinNode;
	                        String szin = SzinElement.getTextContent();
	                        System.out.println("Szín: " + szin);
	                    }
	                }

	                String tervezo = logoElement.getElementsByTagName("Tervező").item(0).getTextContent();
	                System.out.println("Tervező: " + tervezo);

	                System.out.println("========================");
	               }
	         }		     
		}catch(Exception e) {
            e.printStackTrace();
        }
	}
	
	private static void taskFourQueryXMLDLWGQZDocument(String filePath) {
		try {
			//Új sor a konzolra
			System.out.println();
			 
			// Fájl beolvasása
			File xmlFile = new File(filePath);
			 
			//DocumentBuilder inicializálása
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	        Document document = dBuilder.parse(xmlFile);
	         
	        //4. Feladat kiiratása a konzolra
			System.out.println("<4. Feladat: Írassa ki a legidősebb labdarúgó születési évét és nevét és azonosítóját!>");
			 
			// XPath inicializálása
	        XPathFactory xPathfactory = XPathFactory.newInstance();
	        XPath xpath = xPathfactory.newXPath();

	        // Legidősebb labdarúgó születési évét lekérdezése
	        XPathExpression expr = xpath.compile("//Labdarúgó[not(//Labdarúgó/Szülidő > //Labdarúgó/Szülidő)]/Szülidő/text()");
	        String szuletesiEv = (String) expr.evaluate(document, XPathConstants.STRING);

	        // Legidősebb labdarúgó nevét lekérdezése
	        expr = xpath.compile("//Labdarúgó[Szülidő = '" + szuletesiEv + "']/Név/Keresztnév/text()");
	        String keresztnev = (String) expr.evaluate(document, XPathConstants.STRING);
	        expr = xpath.compile("//Labdarúgó[Szülidő = '" + szuletesiEv + "']/Név/Vezetéknév/text()");
	        String vezeteknev = (String) expr.evaluate(document, XPathConstants.STRING);

	        // Legidősebb labdarúgó azonosítójának lekérdezése
	        expr = xpath.compile("//Labdarúgó[Szülidő = '" + szuletesiEv + "']/@Lkód");
	        String azonosito = (String) expr.evaluate(document, XPathConstants.STRING);

	        // Eredmény Kiíratása
	        System.out.println("Legidősebb labdarúgó születési éve: " + szuletesiEv);
	        System.out.println("Legidősebb labdarúgó neve: " + keresztnev + " " + vezeteknev);
	        System.out.println("Legidősebb labdarúgó azonosítója: " + azonosito);			 
		}catch(Exception e) {
            e.printStackTrace();
        }
	}
	
	private static void taskThreeQueryXMLDLWGQZDocument(String filePath) {
		try {
			//Új sor a konzolra
			System.out.println();
			 
			// Fájl beolvasása
			File xmlFile = new File(filePath);
			 
			//DocumentBuilder inicializálása
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	        Document document = dBuilder.parse(xmlFile);
	         
	        //3. Feladat kiiratása a konzolra
		    System.out.println("<3. Feladat: Írassa ki az összes Stadion nevét!>");
		     
		    // Az összes Stadion elem kinyerése
	        NodeList stadionokList = document.getElementsByTagName("Stadion");

	        // Az összes Stadion nevének kiírása a konzolra
	        for (int i = 0; i < stadionokList.getLength(); i++) {
	            Node stadionNode = stadionokList.item(i);
	            if (stadionNode.getNodeType() == Node.ELEMENT_NODE) {
	                Element stadionElem = (Element) stadionNode;
	                String stadionNev = stadionElem.getElementsByTagName("Név").item(0).getTextContent();
	                System.out.println("Stadion neve: " + stadionNev);
	            }
	        }
	    }catch (Exception e) {
	        e.printStackTrace();
	    }	
	}
	
	private static void taskTwoQueryXMLDLWGQZDocument(String filePath) {
		try {
			//Új sor a konzolra
			System.out.println();
			 
			// Fájl beolvasása
			File xmlFile = new File(filePath);
			 
			//DocumentBuilder inicializálása
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	        Document document = dBuilder.parse(xmlFile);
	         
	        //2. Feladat kiiratása a konzolra
	        System.out.println("<2. Feladat: Írassa ki a labdarúgók nevét és góljainak számát!>");

	        // XPath létrehozása
	        XPathFactory xPathFactory = XPathFactory.newInstance();
	        XPath xPath = xPathFactory.newXPath();

	        // XPath kifejezés a labdarúgók nevének és góljainak lekérdezéséhez
	        XPathExpression expr = xPath.compile("//Labdarúgó/Név/Keresztnév | //Labdarúgó/Név/Vezetéknév | //Labdarúgó/Gól");

	        // Kifejezés kiértékelése
	        NodeList nodeList = (NodeList) expr.evaluate(document, XPathConstants.NODESET);

	        // Eredmények kiírása
	        for (int i = 0; i < nodeList.getLength(); i = i + 3) {
	            String keresztnev = nodeList.item(i).getTextContent();
	            String vezeteknev = nodeList.item(i + 1).getTextContent();
	            String gol = nodeList.item(i + 2).getTextContent();
	            System.out.println("Labdarúgó: " + keresztnev + " " + vezeteknev + " || Gólok: " + gol);
	        }
	    }catch (Exception e) {
	        e.printStackTrace();
	    }
	 }
			
	private static void taskOneQueryXMLDLWGQZDocument(String filePath){	
		try {
			// Fájl beolvasása
			File xmlFile = new File(filePath);
			 
			//DocumentBuilder inicializálása
		    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		    DocumentBuilder dBuilder = factory.newDocumentBuilder();
		    Document document = dBuilder.parse(xmlFile);
		     
			// XPath inicializálása
		    XPathFactory xPathfactory = XPathFactory.newInstance();
		    XPath xpath = xPathfactory.newXPath();

	        // Csapatok lekérdezése
	        XPathExpression expr = xpath.compile("//Csapat");
	        NodeList csapatNodeList = (NodeList) expr.evaluate(document, XPathConstants.NODESET);
	            
	        //1. Feladat kiiratása a konzolra
	        System.out.println("<1. Feladat: Írja ki az összes csapat nevét és az adott csapathoz tartozó labdarúgók számát!>");

	        // Csapat elem feldolgozása
	        for (int i = 0; i < csapatNodeList.getLength(); i++) {
	            Node csapatNode = csapatNodeList.item(i);

	            if (csapatNode.getNodeType() == Node.ELEMENT_NODE) {
	                Element csapatElement = (Element) csapatNode;

	                // Csapat nevének lekérdezése
	                String csapatNev = csapatElement.getElementsByTagName("Név").item(0).getTextContent();

	                // Csapat kódjának lekérdezése
	                String csapatKod = csapatElement.getAttribute("CSkód");

	                // Csapat labdarúgóinak számának lekérdezése
	                XPathExpression labdarugokExpr = xpath.compile("count(//Labdarúgó[@CSkód='" + csapatKod + "'])");
	                Double labdarugokSzam = (Double) labdarugokExpr.evaluate(document, XPathConstants.NUMBER);

	                // Eredmény kiírása
	                System.out.println("Csapat neve: " + csapatNev);
	                System.out.println("Labdarúgók száma: " + labdarugokSzam.intValue());
	                System.out.println("========================");
	            }
	        }
		}catch(Exception e) {
            e.printStackTrace();
        }		
	}	
}
