package hu.domparse.dlwgqz;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.*;

public class DOMReadDLWGQZ {
	 // A fő metódus, ami meghívja a readXmlDocument metódust a megadott XML fájllal
	 public static void main(String[] args) {
		 readXMLDocument("./XMLDLWGQZ.xml");
	 }

	 // Metódus, amely az XML fájl beolvasására és feldolgozására szolgál
	 private static void readXMLDocument(String filePath) {	 
		try { 
			File xmlFile = new File("XMLDLWGQZ.xml");
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = factory.newDocumentBuilder();
			Document document = dBuilder.parse(xmlFile);
			document.getDocumentElement().normalize();
			
			// Kiírjuk a gyökérelem nevét a konzolra
			System.out.println("Gyökérelem: " + document.getDocumentElement().getNodeName());

			// XML elemek feldolgozása külön metódusokkal
			readXMLCsapatElement(document, "Csapat");
			readXMLLogoElement(document, "Logó");
			readXMLCsoportElement(document, "Csoport");
			readXMLLabdarugoElement(document, "Labdarúgó");
			readXMLMerkozesElement(document, "Mérkőzés");
			readXMLHelyszinElement(document, "Helyszín");
			readXMLTipusElement(document, "Típus");
			readXMLMerkozikElement(document, "Mérkőzik");							
		} catch(Exception e) {
			 e.printStackTrace();
		}		
	}
	
	// Csapat elemek feldolgozása 
	private static void readXMLCsapatElement(Document document, String elementName) {
		System.out.println("\n-----CSAPAT-ELEM-----");
	    NodeList csapatNodeList = document.getElementsByTagName(elementName);
	    
	    for(int i = 0; i < csapatNodeList.getLength(); i++) {
	    	Node csapatNode = csapatNodeList.item(i);
	    	System.out.println("\nJelenlegi elem: " + csapatNode.getNodeName());
	    	
	    	// Ellenőrzi, hogy a Node egy ELEMENT_NODE típusú-e
	    	if(csapatNode.getNodeType() == Node.ELEMENT_NODE) {
	    		Element csapatElem = (Element) csapatNode;
	    		
	    		// attribútumok létrehozása és megadása
	    		String cskod = csapatElem.getAttribute("CSkód");
	    		String csapatkapitany = csapatElem.getAttribute("Csapatkapitány");
	    		String csopkod = csapatElem.getAttribute("CSOPkód");
	    		String logokod = csapatElem.getAttribute("LOGÓkód");
	    		
	    		Node vezetoedzoNode = csapatElem.getElementsByTagName("Vezetőedző").item(0);
	    		String vezetoedzo = vezetoedzoNode.getTextContent();    		
	    		Node csapatnevNode = csapatElem.getElementsByTagName("Név").item(0);
	    		String csapatnev = csapatnevNode.getTextContent();
	    		
	    		// Kiíjuk a konzolra az attribútumokat és az elemeket
	    		System.out.println("Cskód: " + cskod);
	    		System.out.println("Csapatkapitány: " + csapatkapitany);
	    		System.out.println("CSOPkód: " + csopkod);
	    		System.out.println("LOGÓkód: " + logokod);
	    		System.out.println("Vezetőedző: " + vezetoedzo);
	    		System.out.println("Csapatnév: " + csapatnev);   		
	    		System.out.println("-----------------------------");
	    	}
	    }      
	}
	
	// Logó elemek feldolgozása
	private static void readXMLLogoElement(Document document, String elementName) {
		System.out.println("\n-----LOGÓ-ELEM-----");
	    NodeList logoNodeList = document.getElementsByTagName(elementName);
	    
	    for(int i = 0; i < logoNodeList.getLength(); i++) {
	    	Node logoNode = logoNodeList.item(i);
	    	System.out.println("\nJelenlegi elem: " + logoNode.getNodeName());
	    	
	    	// Ellenőrzi, hogy a Node egy ELEMENT_NODE típusú-e
	    	if(logoNode.getNodeType() == Node.ELEMENT_NODE) {
	    		Element logoElem = (Element) logoNode;
	    		
	    		// attribútum létrehozása és megadása
	    		String logokod = logoElem.getAttribute("LOGÓkód");
	    		System.out.println("LOGÓkód: " + logokod);
	    		
	    		//Ellenőrzi, hogy a gyerekelemek hossza nagyobb-e, mint 3
	    		if(logoNodeList.item(i).getChildNodes().getLength() >= 3) {
	    			int darab = 0;
	    			Node szinekNode = logoElem.getElementsByTagName("Színek").item(0);
	    			while(szinekNode != null) {
	    				szinekNode = logoElem.getElementsByTagName("Színek").item(darab);
	    				if(szinekNode != null) {
	    					String szinek = szinekNode.getTextContent();
	    					System.out.println("A logó színe: " + szinek);
	    				}
	    				darab++;
	    			}
	    		}
	    		
	    		Node tervezoNode = logoElem.getElementsByTagName("Tervező").item(0);
	    		String tervezo = tervezoNode.getTextContent();
	    		
	    		System.out.println("Tervező: " + tervezo);
	    		System.out.println("-----------------------------");
	    	}		       	
	    }        
	}
	
	// Csoport elemek feldolgozása
	private static void readXMLCsoportElement(Document document, String elementName) {
		System.out.println("\n-----CSOPORT-ELEM-----");
	    NodeList csoportNodeList = document.getElementsByTagName(elementName);
	    
	    for(int i = 0; i < csoportNodeList.getLength(); i++) {
	    	Node csoportNode = csoportNodeList.item(i);
	    	System.out.println("\nJelenlegi elem: " + csoportNode.getNodeName());
	    	
	    	// Ellenőrzi, hogy a Node egy ELEMENT_NODE típusú-e
	    	if(csoportNode.getNodeType() == Node.ELEMENT_NODE) {
	    		// attribútum létrehozása és megadása
	    		Element csoportElem = (Element) csoportNode;
	    		String csopkod = csoportElem.getAttribute("CSOPkód");
	    		System.out.println("CSOPkód: " + csopkod);
	    		
	    		Node jelolesNode = csoportElem.getElementsByTagName("Jelölés").item(0);
	    		String jeloles = jelolesNode.getTextContent();
	    		
	    		System.out.println("A csoport jelölése: " + jeloles);
	    		System.out.println("-----------------------------");
	    	}
	    }
	}
	
	// Labdarúgó elemek feldolgozása
	private static void readXMLLabdarugoElement(Document document, String elementName) {
		System.out.println("\n-----LABDARÚGÓ-ELEM-----");
	    NodeList labdarugoNodeList = document.getElementsByTagName(elementName);
	    
	    for(int i = 0; i < labdarugoNodeList.getLength(); i++) {
	    	Node labdarugoNode = labdarugoNodeList.item(i);
	    	System.out.println("\nJelenlegi elem: " + labdarugoNode.getNodeName());
	    	
	    	// Ellenőrzi, hogy a Node egy ELEMENT_NODE típusú-e
	    	if(labdarugoNode.getNodeType() == Node.ELEMENT_NODE) {
	    		// attribútum létrehozása és megadása
	    		Element labdarugoElem = (Element) labdarugoNode;
	    		String lkod = labdarugoElem.getAttribute("Lkód");
	    		System.out.println("Lkód: " + lkod);
	    		String cskod = labdarugoElem.getAttribute("CSkód");
	    		System.out.println("CSkód: " + cskod);
	    		
	    		//Ellenőrzi, hogy a gyerekelemek hossza nagyobb-e, mint 3
	    		if(labdarugoNodeList.item(i).getChildNodes().getLength() >= 3) {
	    			int darab = 0;
	    			Node posztNode = labdarugoElem.getElementsByTagName("Poszt").item(0);
	    			while(posztNode != null) {
	    				posztNode = labdarugoElem.getElementsByTagName("Poszt").item(darab);
	    				if(posztNode != null) {
	    					String poszt = posztNode.getTextContent();
	    					System.out.println("A labdarúgó posztja: " + poszt);
	    				}
	    				darab++;
	    			}
	    		}
	    			    			    		
	    		//Ellenőrzi, hogy a gyerekelemek hossza nagyobb-e, mint 3
	    		if(labdarugoNodeList.item(i).getChildNodes().getLength() >= 3) {
	    			int darab = 0;
	    			Node nevNode = labdarugoElem.getElementsByTagName("Név").item(0);
	    			while(nevNode != null) {
	    				nevNode = labdarugoElem.getElementsByTagName("Név").item(darab);
	    				if(nevNode != null) {
	    					Node keresztnevNode = labdarugoElem.getElementsByTagName("Keresztnév").item(darab);
	    					String keresztnev = keresztnevNode.getTextContent();
	    					System.out.println("A labdarúgó keresztneve: " + keresztnev);
	    					Node vezeteknevNode = labdarugoElem.getElementsByTagName("Vezetéknév").item(darab);
	    					String vezeteknev = vezeteknevNode.getTextContent();
	    					System.out.println("A labdarúgó vezetékneve: " + vezeteknev);
	    				}
	    				darab++;
	    			}
	    		}
	    		 
	    		Node golNode = labdarugoElem.getElementsByTagName("Gól").item(0);
	    		String gol = golNode.getTextContent();
	    		System.out.println("Gólok száma: " + gol);
	    		Node mezszamNode = labdarugoElem.getElementsByTagName("Mezszám").item(0);
	    		String mezszam = mezszamNode.getTextContent();
	    		System.out.println("Mezszám: " + mezszam);
	    		Node szulidoNode = labdarugoElem.getElementsByTagName("Szülidő").item(0);
	    		String szulido = szulidoNode.getTextContent();
	    		System.out.println("Születési dátum: " + szulido);
	    		System.out.println("-----------------------------");
	    	}	    	
	    }        
	}
	
	// Mérkőzés elemek feldolgozása
	private static void readXMLMerkozesElement(Document document, String elementName) {
		System.out.println("\n-----MÉRKŐZÉS-ELEM-----");
	    NodeList merkozesNodeList = document.getElementsByTagName("Mérkőzés");
	    for(int i = 0; i < merkozesNodeList.getLength(); i++) {
	    	Node merkozesNode = merkozesNodeList.item(i);
	    	System.out.println("\nJelenlegi elem: " + merkozesNode.getNodeName());
	    	
	    	// Ellenőrzi, hogy a Node egy ELEMENT_NODE típusú-e
	    	if(merkozesNode.getNodeType() == Node.ELEMENT_NODE) {
	    		// attribútum létrehozása és megadása
	    		Element merkozesElem = (Element) merkozesNode;
	    		String mkod = merkozesElem.getAttribute("Mkód");
	    		System.out.println("Mkód: " + mkod);
	    		String tkod = merkozesElem.getAttribute("Tkód");
	    		System.out.println("Tkód: " + tkod);
	    		String hkod = merkozesElem.getAttribute("Hkód");
	    		System.out.println("Hkód: " + hkod);
	    		
	    		Node csapat1Node = merkozesElem.getElementsByTagName("Csapat1Gólok").item(0);
	    		String csapat1Gol = csapat1Node.getTextContent();
	    		System.out.println("Csapat1 góljainak száma: " + csapat1Gol);
	    		Node csapat2Node = merkozesElem.getElementsByTagName("Csapat2Gólok").item(0);
	    		String csapat2Gol = csapat2Node.getTextContent();
	    		System.out.println("Csapat2 góljainak száma: " + csapat2Gol);
	    		System.out.println("-----------------------------");
	    	}
	    }	        
	}
	
	// Helyszín elemek feldolgozása
	private static void readXMLHelyszinElement(Document document, String elementName) {
		System.out.println("\n-----HELYSZÍN-ELEM-----");
	    NodeList helyszinNodeList = document.getElementsByTagName("Helyszín");
	    
	    for(int i = 0; i < helyszinNodeList.getLength(); i++) {
	    	Node helyszinNode = helyszinNodeList.item(i);
	    	System.out.println("\nJelenlegi elem: " + helyszinNode.getNodeName());
	    	
	    	// Ellenőrzi, hogy a Node egy ELEMENT_NODE típusú-e
	    	if(helyszinNode.getNodeType() == Node.ELEMENT_NODE) {
	    		// attribútum létrehozása és megadása
	    		Element helyszinElem = (Element) helyszinNode;
	    		String hkod = helyszinElem.getAttribute("Hkód");
	    		System.out.println("Hkód: " + hkod);
	    		
	    		//Ellenőrzi, hogy a gyerekelemek hossza nagyobb-e, mint 3
	    		if(helyszinNodeList.item(i).getChildNodes().getLength() >= 3) {
	    			int darab = 0;
	    			Node stadionNode = helyszinElem.getElementsByTagName("Stadion").item(0);
	    			while(stadionNode != null) {
	    				stadionNode = helyszinElem.getElementsByTagName("Stadion").item(darab);
	    				if(stadionNode != null) {
	    					Node nevNode = helyszinElem.getElementsByTagName("Név").item(darab);
	    					String nev = nevNode.getTextContent();
	    					System.out.println("A stadion neve: " + nev);
	    					Node ferohelyNode = helyszinElem.getElementsByTagName("Férőhely").item(darab);
	    					String ferohely = ferohelyNode.getTextContent();
	    					System.out.println("Férőhely: " + ferohely + " fő");
	    					Node tulajdonosNode = helyszinElem.getElementsByTagName("Tulajdonos").item(darab);
	    					String tulajdonos = tulajdonosNode.getTextContent();
	    					System.out.println("Tulajdonos: " + tulajdonos);
	    				}
	    				darab++;
	    			}
	    		}
	    		
	    		Node varosNode = helyszinElem.getElementsByTagName("Város").item(0);
	    		String varos = varosNode.getTextContent();
	    		System.out.println("Város: " + varos);
	    		System.out.println("-----------------------------");
	    	}	    	
	    }    		        
	}
	
	// Típus elemek feldolgozása
	private static void readXMLTipusElement(Document document, String elementName) {
		System.out.println("\n-----TÍPUS-ELEM-----");
	    NodeList tipusNodeList = document.getElementsByTagName("Típus");
	    
	    for(int i = 0; i < tipusNodeList.getLength(); i++) {
	    	Node tipusNode = tipusNodeList.item(i);
	    	System.out.println("\nJelenlegi elem: " + tipusNode.getNodeName());
	    	
	    	// Ellenőrzi, hogy a Node egy ELEMENT_NODE típusú-e
	    	if(tipusNode.getNodeType() == Node.ELEMENT_NODE) {
	    		// attribútum létrehozása és megadása
	    		Element tipusElem = (Element) tipusNode;
	    		String tkod = tipusElem.getAttribute("Tkód");
	    		System.out.println("Tkód: " + tkod);
	    		
	    		Node nevNode = tipusElem.getElementsByTagName("Név").item(0);
	    		String nev = nevNode.getTextContent();
	    		System.out.println("Mérkőzés típusa: " + nev);
	    		System.out.println("-----------------------------");
	    	}
	    }      
	}
	
	// Mérkőzik elemek feldolgozása
	private static void readXMLMerkozikElement(Document document, String elementName) {
		System.out.println("\n-----MÉRKŐZIK-ELEM-----");
	    NodeList merkozikNodeList = document.getElementsByTagName("Mérkőzik");
	    
	    for(int i = 0; i < merkozikNodeList.getLength(); i++) {
	    	Node merkozikNode = merkozikNodeList.item(i);
	    	System.out.println("\nJelenlegi elem: " + merkozikNode.getNodeName());
	    	
	    	// Ellenőrzi, hogy a Node egy ELEMENT_NODE típusú-e
	    	if(merkozikNode.getNodeType() == Node.ELEMENT_NODE) {
	    		// attribútum létrehozása és megadása
	    		Element merkozikElem = (Element) merkozikNode;
	    		String mkod = merkozikElem.getAttribute("Mkód");
	    		System.out.println("Mkód: " + mkod);
	    		String csapat1 = merkozikElem.getAttribute("Csapat1");
	    		System.out.println("Csapat1 kódja: " + csapat1);
	    		String csapat2 = merkozikElem.getAttribute("Csapat2");
	    		System.out.println("Csapat2 kódja: " + csapat2);
	    		String gyoztes = merkozikElem.getAttribute("Győztes");
	    		System.out.println("Győztes csapat kódja: " + gyoztes);
	    		
	    		Node datumNode = merkozikElem.getElementsByTagName("Dátum").item(0);
	    		String datum = datumNode.getTextContent();
	    		System.out.println("Dátum: " + datum);
	    		Node jatekvezetoNode = merkozikElem.getElementsByTagName("Játékvezető").item(0);
	    		String jatekvezeto = jatekvezetoNode.getTextContent();
	    		System.out.println("Játékvezető: " + jatekvezeto);
	    		System.out.println("-----------------------------");
	    	}	    	
	    }
	}
}