package hu.domparse.dlwgqz;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;

import org.w3c.dom.*;

public class DOMWriteDLWGQZ {

	public static void main(String[] args) {
		// Metódus meghívása
		writeElementsOfXMLDocumentToFileAndConsole();
	}
	
	private static void writeElementsOfXMLDocumentToFileAndConsole() {
		try {
			// Előkészítjük a dokumentumot
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            // Ez a dokumentumépítő példányok létrehozására szolgál
            DocumentBuilder builder = factory.newDocumentBuilder();
            // Ez a dokumentum építésére szolgál
            Document document = builder.newDocument();
            // Gyökérelem létrehozása
            Element rootElement = document.createElement("Világbajnokság_DLWGQZ");
            rootElement.setAttribute("xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance");
            rootElement.setAttribute("xsi:noNamespaceSchemaLocation", "XMLSchemaU3ROFS.xsd");
            document.appendChild(rootElement);
            
            // Csapat elemek létrehozása
            addCsapat(document, rootElement, "1", "CR7", "11", "21", "Roberto Martínez", "Portugália");
            addCsapat(document, rootElement, "2", "TS1", "12", "22", "Hansi Flick", "Németország");
            addCsapat(document, rootElement, "3", "KM10", "13", "23", "Didier Deschamp", "Franciaország");
            addCsapat(document, rootElement, "4", "HK9", "14", "24", "Gareth Southgate", "Anglia");
            addCsapat(document, rootElement, "5", "LM10", "15", "25", "Lionel Scaloni", "Argentína");
                       
            // Logó elemek létrehozása
            addLogo(document, rootElement, "21", Arrays.asList("Piros", "Kék", "Zöld"), "António Modesto");
            addLogo(document, rootElement, "22", Arrays.asList("Fekete", "Piros", "Arany"), "Anton Stankowski");
            addLogo(document, rootElement, "23", Arrays.asList("Kék", "Fehér", "Piros"), "Raymond Savignac");
            addLogo(document, rootElement, "24", Arrays.asList("Fehér", "Kék", "Piros"), "William C. Gibbons");
            addLogo(document, rootElement, "25", Arrays.asList("Égkék", "Fehér", "Sárga"), "Salvador Dellutri");
            
            // Csoport elemek létrehozása
            addCsoport(document, rootElement, "11", "A");
            addCsoport(document, rootElement, "12", "B");
            addCsoport(document, rootElement, "13", "C");
            addCsoport(document, rootElement, "14", "D");
            addCsoport(document, rootElement, "15", "E");
            
            // Labdarúgó elemek létrehozása
            addLabdarugo(document, rootElement, "CR7", "1", Arrays.asList("Középcsatár"), "Cristiano", "Ronaldo", "10", "7", "1985.02.05");
            addLabdarugo(document, rootElement, "RN18", "1", Arrays.asList("Középpályás", "Védekező középpályás"), "Ruben", "Neves", "2", "18", "1997.03.13");
            addLabdarugo(document, rootElement, "TS1", "2", Arrays.asList("Kapus"), "Marc-André", "ter Stegen", "0", "22", "1992.04.05");
            addLabdarugo(document, rootElement, "JK6", "2", Arrays.asList("Középpályás", "Jobb szélső védő"), "Joshua", "Kimmich", "3", "6", "1995.02.08");
            addLabdarugo(document, rootElement, "KM10", "3", Arrays.asList("Középcsatár"), "Kylian", "Mbappé", "9", "10", "1998.12.20");
            addLabdarugo(document, rootElement, "EC25", "3", Arrays.asList("Középpályás", "Védekező középpályás", "Bal szélső védő"), "Eduardo", "Camavinga", "3", "25", "2002.11.10");
            addLabdarugo(document, rootElement, "HK9", "4", Arrays.asList("Középcsatár"), "Harry", "Kane", "7", "9", "1993.07.28");
            addLabdarugo(document, rootElement, "JB10", "4", Arrays.asList("Középpályás"), "Jude", "Belligham", "6", "10", "2003.06.29");
            addLabdarugo(document, rootElement, "LM10", "5", Arrays.asList("Középcsatár", "Jobb szélső csatár"), "Lionel", "Messi", "9", "10", "1987.07.24");
            addLabdarugo(document, rootElement, "EM23", "5", Arrays.asList("Kapus"), "Emiliano", "Martinez", "0", "23", "1992.09.02");
            
            // Mérkőzés elemek létrehozása
            addMerkozes(document, rootElement, "1", "101", "201", "5", "2");
            addMerkozes(document, rootElement, "2", "102", "202", "0", "1");
            addMerkozes(document, rootElement, "3", "103", "203", "3", "7");
            addMerkozes(document, rootElement, "4", "104", "204", "2", "1");
            addMerkozes(document, rootElement, "5", "104", "205", "2", "3");
            		  		
            // Helyszín elemek létrehozása
            addHelyszin(document, rootElement, "201", "Education City Stadion", "45000", "katari kormány", "al-Rajján");	
            addHelyszin(document, rootElement, "202", "Al Bayt Stadion", "60000", "katari kormány", "al-Hor");
            addHelyszin(document, rootElement, "203", "Loszaíli Nemzeti Stadion", "80000", "katari kormány", "Loszaíl");	
            addHelyszin(document, rootElement, "204", "al-Thumama Stadion", "40000", "katari kormány", "al-Thumama");	
            addHelyszin(document, rootElement, "205", "El-Dzsanúb Stadion", "40000", "katari kormány", "Al-Vakra");	
            
            // Típus elemek létrehozása		
            addTipus(document, rootElement, "101", "Csoportmérkőzés");
            addTipus(document, rootElement, "102", "Nyolcaddöntő");	
            addTipus(document, rootElement, "103", "Negyeddöntő");	
            addTipus(document, rootElement, "104", "Elődöntő");	
            addTipus(document, rootElement, "105", "Döntő");	
            		
            // Mérkőzik elemek létrehozása
            addMerkozik(document, rootElement, "1", "1", "2", "1", "2022.11.22", "Frank de Bleeckere");
            addMerkozik(document, rootElement, "2", "3", "5", "5", "2022.11.26", "Pedro Proenca");	
            addMerkozik(document, rootElement, "3", "2", "1", "2", "2022.11.30", "Kim Milton Nielsen");	
            addMerkozik(document, rootElement, "4", "4", "5", "4", "2022.12.01", "Michel Vautrot");	
            addMerkozik(document, rootElement, "5", "1", "5", "1", "2022.12.18", "Oscar Ruiz");	
                       		
            // Dokumentum mentése
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{https://xml.apache.org/xslt}indent-amount", "2");

            printDocument(document);   
		} catch (Exception e) {
            e.printStackTrace();
        }
	}
	
	//addMerkozik metódus (MÉRKŐZIK)
	private static void addMerkozik(Document document, Element rootElement, String mkod, String csapat1, String csapat2, 
			String gyoztes, String datum, String jatekvezeto) {
		Element merkozik = document.createElement("Mérkőzik");
		merkozik.setAttribute("Mkód", mkod);
		merkozik.setAttribute("Csapat1", csapat1);
		merkozik.setAttribute("Csapat2", csapat2);
		merkozik.setAttribute("Győztes", gyoztes);
		
		Element datumElement = createElement(document, "Dátum", datum);
		merkozik.appendChild(datumElement);
		
		Element jatekvezetoElement = createElement(document, "Játékvezető", jatekvezeto);
		merkozik.appendChild(jatekvezetoElement);
		
		rootElement.appendChild(merkozik);
	}
	
	//addTipus metódus (TÍPUS)
	private static void addTipus(Document document, Element rootElement, String tkod, String nev) {
		Element tipus = document.createElement("Típus");
		tipus.setAttribute("Tkód", tkod);
		
		Element nevElement = createElement(document, "Név", nev);
		tipus.appendChild(nevElement);
		
		rootElement.appendChild(tipus);
	}
	
	//addHelyszin metódus (HELYSZÍN)
	private static void addHelyszin(Document document, Element rootElement, String hkod, String nev, String ferohely, 
			String tulajdonos, String varos) {
		Element helyszin = document.createElement("Helyszín");
		helyszin.setAttribute("Hkód", hkod);
		
		Element stadionElement = document.createElement("Stadion");
		Element nevElement = createElement(document, "Név", nev);
		Element ferohelyElement = createElement(document, "Férőhely", ferohely);
		Element tulajdonosElement = createElement(document, "Tulajdonos", tulajdonos);
		stadionElement.appendChild(nevElement);
		stadionElement.appendChild(ferohelyElement);
		stadionElement.appendChild(tulajdonosElement);
		
		rootElement.appendChild(helyszin);
	}
		
	//addMerkozes metódus (MÉRKŐZÉS)
	private static void addMerkozes(Document document, Element rootElement, String mkod, String tkod, String hkod, 
			String csapat1golok, String csapat2golok) {
		Element merkozes = document.createElement("Mérkőzés");
		merkozes.setAttribute("Mkód", mkod);
		merkozes.setAttribute("Tkód", tkod);
		merkozes.setAttribute("Hkód", hkod);
		
		Element csapat1golokElement = createElement(document, "Csapat1Gólok", csapat1golok);
		merkozes.appendChild(csapat1golokElement);
		
		Element csapat2golokElement = createElement(document, "Csapat2Gólok", csapat2golok);
		merkozes.appendChild(csapat2golokElement);
		
		rootElement.appendChild(merkozes);
		
	}
	
	//addLabdarugo metódus (LABDARÚGÓ)
	private static void addLabdarugo(Document document, Element rootElement, String lkod, String cskod, List<String> posztok,
            String keresztnev, String vezeteknev, String gol, String mezszam, String szulido) {
		Element labdarugo = document.createElement("Labdarúgó");
		labdarugo.setAttribute("Lkód", lkod);
		labdarugo.setAttribute("CSkód", cskod);
		
		for (String poszt : posztok) {
            Element posztElement = createElement(document, "Poszt", poszt);
            labdarugo.appendChild(posztElement);
        }
		
		Element nevElement = document.createElement("Név");
		Element keresztnevElement = createElement(document, "Keresztnév", keresztnev);
		Element vezeteknevElement = createElement(document, "Vezetéknév", vezeteknev);
		nevElement.appendChild(keresztnevElement);
		nevElement.appendChild(vezeteknevElement);
	    
	    
	    labdarugo.appendChild(nevElement);
	    	    
	    Element golElement = createElement(document, "Gól", gol);
	    labdarugo.appendChild(golElement);
	    
	    Element mezszamElement = createElement(document, "Mezszám", mezszam);
	    labdarugo.appendChild(mezszamElement);
	    
	    Element szulidoElement = createElement(document, "Szülidő", szulido);
	    labdarugo.appendChild(szulidoElement);
	     		
		rootElement.appendChild(labdarugo);
	}
		
	//addCsoport metódus (CSOPORT)
	private static void addCsoport(Document document, Element rootElement, String csopkod, String jeloles) {
		Element csoport = document.createElement("Csoport");
	    csoport.setAttribute("CSOPkód", csopkod);
	    
	    Element jelolesElement = createElement(document, "Jelölés", jeloles);
        csoport.appendChild(jelolesElement);
        
        rootElement.appendChild(csoport);

	}
	
	//addLogo metódus (LOGÓ)
	private static void addLogo(Document document, Element rootElement, String logokod, List<String> szinek, String tervezo) {
        Element logo = document.createElement("Logó");
        logo.setAttribute("LOGÓKód", logokod);

        for (String szin : szinek) {
            Element szinElement = createElement(document, "Színek", szin);
            logo.appendChild(szinElement);
        }
        
        Element tervezoElement = createElement(document, "Tervező", tervezo);
        logo.appendChild(tervezoElement);

        rootElement.appendChild(logo);

	}
	
	//addCsapat metódus (CSAPAT)
	private static void addCsapat(Document document, Element rootElement, String cskod, String csapatkapitany, String csopkod,
            String logokod, String vezetoedzo, String nev ) {
        Element csapat = document.createElement("Csapat");
        csapat.setAttribute("CSkód", cskod);
        csapat.setAttribute("Csapatkapitány", csapatkapitany);
        csapat.setAttribute("CSOPkód", csopkod);
        csapat.setAttribute("LOGÓkód", logokod);

        Element vezetoedzoElement = createElement(document, "Vezetőedző", vezetoedzo);
        Element nevElement = createElement(document, "Név", nev);
        csapat.appendChild(vezetoedzoElement);
        csapat.appendChild(nevElement);

        rootElement.appendChild(csapat);

	}
	
	 private static Element createElement(Document document, String name, String value) {
	     Element element = document.createElement(name);
	     element.appendChild(document.createTextNode(value));
	     return element;
	 }
	 
	 private static void printNodeList(NodeList nodeList, PrintWriter writer) {
	      for (int i = 0; i < nodeList.getLength(); i++) {
	    	 Node node = nodeList.item(i);
	    	 printNode(node, 1, writer);
	         System.out.println("");
	         writer.println("");
	      }
	 }
	 
	 private static void printNode(Node node, int indent, PrintWriter writer) {
	        // Ha az elem típusa ELEMENT_NODE, akkor kiírjuk az elem nevét és attribútumait
	        if (node.getNodeType() == Node.ELEMENT_NODE) {
	            Element element = (Element) node;
	            String nodeName = element.getTagName();
	            StringJoiner attributes = new StringJoiner(" ");
	            NamedNodeMap attributeMap = element.getAttributes();
	            // Kiírjuk az elem nevét és attribútumait
	            for (int i = 0; i < attributeMap.getLength(); i++) {
	                Node attribute = attributeMap.item(i);
	                attributes.add(attribute.getNodeName() + "=\"" + attribute.getNodeValue() + "\"");
	            }

	            // Kiírjuk az elem nevét és attribútumait
	            System.out.print(getIndentString(indent));
	            System.out.print("<" + nodeName + " " + attributes.toString() + ">");

	            writer.print(getIndentString(indent));
	            writer.print("<" + nodeName + " " + attributes.toString() + ">");

	            NodeList children = element.getChildNodes();
	            if (children.getLength() == 1 && children.item(0).getNodeType() == Node.TEXT_NODE) {
	                System.out.print(children.item(0).getNodeValue());
	                writer.print(children.item(0).getNodeValue());
	            } else {
	                System.out.println();
	                writer.println();
	                for (int i = 0; i < children.getLength(); i++) {
	                    printNode(children.item(i), indent + 1, writer);
	                }
	                System.out.print(getIndentString(indent));
	                writer.print(getIndentString(indent));
	            }
	            System.out.println("</" + nodeName + ">");
	            writer.println("</" + nodeName + ">");
	        }
	    }
	 
	 private static void printDocument(Document document) {
	        try {
	            // Fájlba írás
	            File xmlFile = new File("XMLDLWGQZ1.xml");
	            
	            // Írás a konzolra
	            PrintWriter writer = new PrintWriter(new FileWriter(xmlFile, true));
	            
	            // Kiírja az XML főgyökér elemét a konzolra és fájlba
	            Element rootElement = document.getDocumentElement();
	            String rootName = rootElement.getTagName();
	            
	            // A gyökérelem attribútumainak kiírása
	            StringJoiner rootAttributes = new StringJoiner(" ");
	            
	            // Gyökérelem attribútumainak lekérése
	            NamedNodeMap rootAttributeMap = rootElement.getAttributes();

	            for (int i = 0; i < rootAttributeMap.getLength(); i++) {
	                Node attribute = rootAttributeMap.item(i);
	                rootAttributes.add(attribute.getNodeName() + "=\"" + attribute.getNodeValue() + "\"");
	            }

	            System.out.print("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
	            writer.print("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");

	            System.out.print("<" + rootName + " " + rootAttributes.toString() + ">\n");
	            writer.print("<" + rootName + " " + rootAttributes.toString() + ">\n");
	            // A gyökér elem alatti elemek lekérése
	            NodeList csapatList = document.getElementsByTagName("Csapat");
	            NodeList logoList = document.getElementsByTagName("Logó");
	            NodeList csoportList = document.getElementsByTagName("Csoport");
	            NodeList labdarugoList = document.getElementsByTagName("Labdarúgó");
	            NodeList merkozesList = document.getElementsByTagName("Mérkőzés");
	            NodeList helyszinList = document.getElementsByTagName("Helyszín");
	            NodeList tipusList = document.getElementsByTagName("Típus");
	            NodeList merkozikList = document.getElementsByTagName("Mérkőzik");

	            printNodeList(csapatList, writer);
	            System.out.println("");
	            writer.println("");
	            
	            printNodeList(logoList, writer);
	            System.out.println("");
	            writer.println("");
	            
	            printNodeList(csoportList, writer);
	            System.out.println("");
	            writer.println("");
	            
	            printNodeList(labdarugoList, writer);
	            System.out.println("");
	            writer.println("");
	            
	            printNodeList(merkozesList, writer);
	            System.out.println("");
	            writer.println("");
	            
	            printNodeList(helyszinList, writer);
	            System.out.println("");
	            writer.println("");
	            
	            printNodeList(tipusList, writer);
	            System.out.println("");
	            writer.println("");
	            
	            printNodeList(merkozikList, writer);
	            System.out.println("");
	            writer.println("");
	            

	            System.out.println("</" + rootName + ">");
	            writer.append("</" + rootName + ">");

	            writer.close();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	 
	 private static String getIndentString(int indent) {
		 StringBuilder sb = new StringBuilder();
	       for (int i = 0; i < indent; i++) {
	           sb.append(" ");
	       }
	       return sb.toString();
	 }
}
