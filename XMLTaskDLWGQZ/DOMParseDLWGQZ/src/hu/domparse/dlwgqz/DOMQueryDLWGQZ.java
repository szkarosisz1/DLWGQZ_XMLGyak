package hu.domparse.dlwgqz;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.*;

public class DOMQueryDLWGQZ {
	// A fő metódus, ami meghívja a queryXMLDocument metódust a megadott XML fájllal
	public static void main(String[] args) {
		// Metódus meghívása	
		queryXMLDocument("./XMLDLWGQZ.xml");			
	}

	// Metódus, amely az XML fájl beolvasására és feldolgozására szolgál
	private static void queryXMLDocument(String filePath) {
		try {
			File xmlFile = new File("XMLDLWGQZ.xml");
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = factory.newDocumentBuilder();
			Document document = dBuilder.parse(xmlFile);
			document.getDocumentElement().normalize();
			
			//1. Az összes labdarúgó adatainak kiírása
			queryXMLAllFootballersData(document,"Labdarúgó");
			//2. Az összes csapat neve
			queryXMLAllTeamsName(document,"Csapat");
			//3. Mérkőzés dátuma és játékvezető kiírása
			queryXMLAllMatchesDateAndReferee(document,"Csapat");
			//4. '21'-es ID-val rendelkező logó tervezőjének neve
			queryXMLDesignerNameWithID21(document,"Logó");
			//5. Az összes stadion nevének kiírása, amelynek a férőhelyeinek száma pontosan 40000 fő 
			queryXMLAllStadiumsNameEquals40000(document,"Helyszín");
	                  		     
		} catch(Exception e) {
            e.printStackTrace();
        }
	}

	private static void queryXMLAllStadiumsNameEquals40000(Document document, String elementName) {
		System.out.println("\n--5.-Az-összes-stadion-nevének-kiírása,-amelynek-a-férőhelyeinek-pontosan-40000-fő--");	
		NodeList helyszinNodeList = document.getElementsByTagName("Helyszín");
		  		    
		for (int i = 0; i < helyszinNodeList.getLength(); i++) {
            Node helyszinNode = helyszinNodeList.item(i);
            
            if (helyszinNode.getNodeType() == Node.ELEMENT_NODE) {
                Element helyszinElement = (Element) helyszinNode;
                String stadionNev = helyszinElement.getElementsByTagName("Név").item(0).getTextContent();
                int ferohely = Integer.parseInt(helyszinElement.getElementsByTagName("Férőhely").item(0).getTextContent());

                if (ferohely == 40000) {
                    System.out.println("Stadion neve: " + stadionNev);
                    System.out.println("--------------------------------");
                }
            }
        } 
	}

	private static void queryXMLDesignerNameWithID21(Document document, String elementName) {
		System.out.println("\n--4.-'21'-es-ID-val-rendelkező-logó-tervezőjének-neve--");
		String logokod = "21";
	    NodeList logoNodeList = document.getElementsByTagName(elementName);
	    for(int i = 0; i < logoNodeList.getLength(); i++) {
	    	Node logoNode = logoNodeList.item(i);
	      	
	    	if(logoNode.getNodeType() == Node.ELEMENT_NODE) {
	    		Element logoElem = (Element) logoNode;
	    		String logokodAttribute = logoElem.getAttribute("LOGÓkód");
	    		if(logokodAttribute.equals(logokod)) {
	    			String tervezo = logoElem.getElementsByTagName("Tervező").item(0).getTextContent();
	    			System.out.println("A '" + logokod +"'-es ID-val rendelkező logó tervezője: " + tervezo);
	    			System.out.println("-----------------------------------------------------------");
	    		}    			
	    	}
	    }
	}

	private static void queryXMLAllMatchesDateAndReferee(Document document, String string) {
		System.out.println("\n--3.-Mérkőzés-dátuma-és-játékvezető-kiírása--");
	    NodeList merkozikNodeList = document.getElementsByTagName("Mérkőzik");
	    
	    for(int i = 0; i < merkozikNodeList.getLength(); i++) {
	    	Node merkozikNode = merkozikNodeList.item(i);
	    	
	    	Element merkozikElem = (Element) merkozikNode;
	    	
	    	Node datumNode = merkozikElem.getElementsByTagName("Dátum").item(0);
    		String datum = datumNode.getTextContent();
	    	System.out.println("A mérkőzés dátuma: " + datum);  		
	    	Node jatekvezetoNode = merkozikElem.getElementsByTagName("Játékvezető").item(0);
    		String jatekvezeto = jatekvezetoNode.getTextContent();
	    	System.out.println("A játékvezető neve: " + jatekvezeto);
	    	System.out.println("---------------------------------------");
	    		
	    }    
	}
	    	
	private static void queryXMLAllTeamsName(Document document, String elementName) {
		System.out.println("\n--2.-Az-összes-csapat-neve--");	
	    NodeList csapatNodeList = document.getElementsByTagName(elementName);
	    
	    for(int i = 0; i < csapatNodeList.getLength(); i++) {
	    	Node csapatNode = csapatNodeList.item(i);
	    		    	
	    	if(csapatNode.getNodeType() == Node.ELEMENT_NODE && csapatNode.getNodeName().equals("Csapat")) {
	    		Element csapatElem = (Element) csapatNode;
	    		
	    		Node csapatnevNode = csapatElem.getElementsByTagName("Név").item(0);
	    		String csapatnev = csapatnevNode.getTextContent();
	    		System.out.println("Csapat neve: " + csapatnev);
	    		System.out.println("----------------------------------");
	    	}
	    }		
	}

	private static void queryXMLAllFootballersData(Document document, String elementName) {
		System.out.println("--1.-Az-összes-labdarúgó-adatainak-kiírása--");
	    NodeList labdarugoNodeList = document.getElementsByTagName(elementName);
	    
	    for(int i = 0; i < labdarugoNodeList.getLength(); i++) {
	    	Node labdarugoNode = labdarugoNodeList.item(i);
	    	
	    	if(labdarugoNode.getNodeType() == Node.ELEMENT_NODE && labdarugoNode.getNodeName().equals("Labdarúgó")) {
	    		// attribútum létrehozása és megadása
	    		Element labdarugoElem = (Element) labdarugoNode;
	    		String lkod = labdarugoElem.getAttribute("Lkód");
	    		System.out.println("A labdarúgó kódja: " + lkod);
	    		String cskod = labdarugoElem.getAttribute("CSkód");
	    		System.out.println("A csapat kódja: " + cskod);
	    		
	    		//Ellenőrizzük, hogy a többszörösen előforduló elemből több van-e, mint 3 darab
	    		if(labdarugoNodeList.item(i).getChildNodes().getLength() > 3) {
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
	    			    			    		
	    		//Ellenőrizzük, hogy a többszörösen előforduló elemből több van-e, mint 3 darab
	    		if(labdarugoNodeList.item(i).getChildNodes().getLength() > 3) {
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
	    		System.out.println("------------------------------------------");
	    	}
	    }
	}	
}
