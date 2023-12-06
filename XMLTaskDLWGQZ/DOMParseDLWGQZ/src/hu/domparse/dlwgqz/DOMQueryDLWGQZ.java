package hu.domparse.dlwgqz;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.*;

public class DOMQueryDLWGQZ {
	// A fő metódus, ami meghívja a queryXMLDocument metódust a megadott XML fájllal 
    public static void main(String[] args) {
        queryXMLDocument("./XMLDLWGQZ.xml");			
    }

	// Metódus, amely az XML fájl beolvasására és feldolgozására szolgál
    private static void queryXMLDocument(String filePath) {
        try {
            File xmlFile = new File(filePath);
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = factory.newDocumentBuilder();
            Document document = dBuilder.parse(xmlFile);
            document.getDocumentElement().normalize();
            
            //1. Az összes labdarúgó adatainak kiírása
            queryXMLAllFootballersData(document, "Labdarúgó");
            //2. Az összes csapat neve
            queryXMLAllTeamsName(document, "Csapat");
            //3. Az összes mérkőzés időpontja és játékvezető kiírása
            queryXMLAllMatchesDateAndReferee(document, "Mérkőzik");
            //4. '21'-es ID-val rendelkező logó tervezőjének neve
            queryXMLDesignerNameWithID21(document, "Logó");
            //5. Az összes stadion nevének kiírása, amelynek a férőhelyeinek száma pontosan 40000 fő 
            queryXMLAllStadiumsNameEquals40000(document, "Helyszín");
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

	private static void queryXMLAllStadiumsNameEquals40000(Document document, String elementName) {	
		System.out.println("\n5. Az összes stadion nevének kiírása, amelynek a férőhelyeinek száma pontosan 40000 fő");

		// XML dokumentumból az összes "Helyszín" elem kinyerése
		NodeList helyszinNodeList = document.getElementsByTagName("Helyszín");

		// Az összes "Helyszín" elemen végigiterálunk
		for (int i = 0; i < helyszinNodeList.getLength(); i++) {
			// Aktuális "Helyszín" elem kinyerése
			Node helyszinNode = helyszinNodeList.item(i);

			// Ellenőrzés: Csak ELEMENT_NODE típusú elemek kezelése
			if (helyszinNode.getNodeType() == Node.ELEMENT_NODE) {
				// Az aktuális "Helyszín" elem konvertálása "Element" objektummá
				Element helyszinElement = (Element) helyszinNode;

				// Az aktuális stadion nevének és férőhelyének kinyerése az XML-ből
				String stadionNev = helyszinElement.getElementsByTagName("Név").item(0).getTextContent();
				int ferohely = Integer.parseInt(helyszinElement.getElementsByTagName("Férőhely").item(0).getTextContent());

				// Ellenőrzés: Ha a stadion férőhelye pontosan 40000 fő, akkor kiírjuk az eredményt a konzolra
				if (ferohely == 40000) {
					System.out.println("<Stadion>");
					System.out.println("    <Név>" + stadionNev + "</Név>");
					System.out.println("    <Férőhely>" + ferohely + "</Férőhely>");
					System.out.println("</Stadion>");
				}
			}
		}
	}

	private static void queryXMLDesignerNameWithID21(Document document, String elementName) {
		System.out.println("\n4. '21'-es ID-val rendelkező logó tervezőjének neve");

		String logokod = "21";

		// Az összes "Logó" elem kinyerése a megadott elemnév alapján
		NodeList logoNodeList = document.getElementsByTagName(elementName);

		// Az összes "Logó" elemen végigiterálunk
		for (int i = 0; i < logoNodeList.getLength(); i++) {
			// Aktuális "Logó" elem kinyerése
			Node logoNode = logoNodeList.item(i);

			// Ellenőrzés, hogy a Node objektum egy ELEMENT_NODE típusú-e
			if (logoNode.getNodeType() == Node.ELEMENT_NODE && logoNode.getNodeName().equals("Logó")) {
				// Az aktuális "Logó" elem konvertálása "Element" objektummá
				Element logoElem = (Element) logoNode;

				// Az 'LOGÓkód' attribútum értékének lekérdezése
				String logokodAttribute = logoElem.getAttribute("LOGÓkód");

				// Ellenőrzés: Ha az 'LOGÓkód' értéke egyezik a keresett '21'-es kóddal
				if (logokodAttribute.equals(logokod)) {
					// A tervező nevének kinyerése és kiírása a konzolra
					String tervezo = logoElem.getElementsByTagName("Tervező").item(0).getTextContent();
					System.out.println("<Logó>");
					System.out.println("    <Tervező>" + tervezo + "</Tervező>");
					System.out.println("</Logó>");
				}
			}
		}
	}

	private static void queryXMLAllMatchesDateAndReferee(Document document, String string) {
		System.out.println("\n3. Az összes mérkőzés időpontja és játékvezető kiírása");

		// Az összes "Mérkőzik" elem kinyerése a megadott elemnév alapján
		NodeList merkozikNodeList = document.getElementsByTagName("Mérkőzik");

		// Az összes "Mérkőzik" elemen végigiterálunk
		for (int i = 0; i < merkozikNodeList.getLength(); i++) {
			// Aktuális "Mérkőzik" elem kinyerése
			Node merkozikNode = merkozikNodeList.item(i);

			// Ellenőrzés, hogy a Node objektum egy ELEMENT_NODE típusú-e
			if (merkozikNode.getNodeType() == Node.ELEMENT_NODE && merkozikNode.getNodeName().equals("Mérkőzik")) {
				// Az aktuális "Mérkőzik" elem konvertálása "Element" objektummá
				Element merkozikElem = (Element) merkozikNode;

				// Az aktuális mérkőzés dátumának kinyerése és kiírása a konzolra
				Node datumNode = merkozikElem.getElementsByTagName("Dátum").item(0);
				String datum = datumNode.getTextContent();
				System.out.println("<Mérkőzik>");
				System.out.println("    <Dátum>" + datum + "</Dátum>");

				// Az aktuális mérkőzés játékvezetőjének kinyerése és kiírása a konzolra
				Node jatekvezetoNode = merkozikElem.getElementsByTagName("Játékvezető").item(0);
				String jatekvezeto = jatekvezetoNode.getTextContent();
				System.out.println("    <Játékvezető>" + jatekvezeto + "</Játékvezető>");
				System.out.println("</Mérkőzik>");
			}
		}
	}
        
	private static void queryXMLAllTeamsName(Document document, String elementName) {
		System.out.println("\n2. Az összes csapat neve");	
		// Az összes "Csapat" elem kinyerése a megadott elemnév alapján
		NodeList csapatNodeList = document.getElementsByTagName(elementName);

		// Az összes "Csapat" elemen végigiterálunk
		for (int i = 0; i < csapatNodeList.getLength(); i++) {
			// Aktuális "Csapat" elem kinyerése.
			Node csapatNode = csapatNodeList.item(i);

			// Ellenőrzés, hogy a Node objektum egy ELEMENT_NODE típusú-e
			if (csapatNode.getNodeType() == Node.ELEMENT_NODE && csapatNode.getNodeName().equals("Csapat")) {
				// Az aktuális "Csapat" elem konvertálása "Element" objektummá
				Element csapatElem = (Element) csapatNode;

				// Az aktuális csapat nevének kinyerése és kiírása a konzolra
				Node csapatnevNode = csapatElem.getElementsByTagName("Név").item(0);
				String csapatnev = csapatnevNode.getTextContent();
				System.out.println("<Csapat>");
				System.out.println("    <Név>" + csapatnev + "</Név>");
				System.out.println("</Csapat>");
			}
		}
	}

	private static void queryXMLAllFootballersData(Document document, String elementName) {
		System.out.println("1. Az összes labdarúgó adatainak kiírása");

		// Az összes "Labdarúgó" elem kinyerése a megadott elemnév alapján
		NodeList labdarugoNodeList = document.getElementsByTagName(elementName);

		// Az összes "Labdarúgó" elem bejárása
		for (int i = 0; i < labdarugoNodeList.getLength(); i++) {
			// Aktuális "Labdarúgó" elem kinyerése
			Node labdarugoNode = labdarugoNodeList.item(i);

			// Ellenőrzés, hogy a Node objektum egy ELEMENT_NODE típusú-e
			if (labdarugoNode.getNodeType() == Node.ELEMENT_NODE && labdarugoNode.getNodeName().equals("Labdarúgó")) {
				// Az aktuális "Labdarúgó" elem konvertálása "Element" objektummá
				Element labdarugoElem = (Element) labdarugoNode;

				// Az aktuális labdarúgó Lkód attribútumának kiírása a konzolra
				String lkod = labdarugoElem.getAttribute("Lkód");
				System.out.println("<Labdarúgó Lkód=\"" + lkod + "\">");

				// Az aktuális labdarúgó CSkód elemének kiírása a konzolra
				String cskod = labdarugoElem.getAttribute("CSkód");
				System.out.println("    <CSkód>" + cskod + "</CSkód>");

				// Ellenőrzés: Ha a labdarúgó Poszt elemekkel rendelkezik, azok kiírása a konzolra
				if (labdarugoNodeList.item(i).getChildNodes().getLength() > 3) {
					int darab = 0;
					Node posztNode = labdarugoElem.getElementsByTagName("Poszt").item(0);
					while (posztNode != null) {
						posztNode = labdarugoElem.getElementsByTagName("Poszt").item(darab);
						if (posztNode != null) {
							String poszt = posztNode.getTextContent();
							System.out.println("    <Poszt>" + poszt + "</Poszt>");
						}
						darab++;
					}
				}

				// Ellenőrzés: Ha a labdarúgó Név elemekkel rendelkezik, azok kiírása a konzolra
				if (labdarugoNodeList.item(i).getChildNodes().getLength() > 3) {
					int darab = 0;
					Node nevNode = labdarugoElem.getElementsByTagName("Név").item(0);
					while (nevNode != null) {
						nevNode = labdarugoElem.getElementsByTagName("Név").item(darab);
						if (nevNode != null) {
							Node keresztnevNode = labdarugoElem.getElementsByTagName("Keresztnév").item(darab);
							String keresztnev = keresztnevNode.getTextContent();
							System.out.println("    <Keresztnév>" + keresztnev + "</Keresztnév>");
							Node vezeteknevNode = labdarugoElem.getElementsByTagName("Vezetéknév").item(darab);
							String vezeteknev = vezeteknevNode.getTextContent();
							System.out.println("    <Vezetéknév>" + vezeteknev + "</Vezetéknév>");
						}
						darab++;
					}
				}

				// Az aktuális labdarúgó Gól elemének kiírása a konzolra
				Node golNode = labdarugoElem.getElementsByTagName("Gól").item(0);
				String gol = golNode.getTextContent();
				System.out.println("    <Gól>" + gol + "</Gól>");

				// Az aktuális labdarúgó Mezszám elemének kiírása a konzolra
				Node mezszamNode = labdarugoElem.getElementsByTagName("Mezszám").item(0);
				String mezszam = mezszamNode.getTextContent();
				System.out.println("    <Mezszám>" + mezszam + "</Mezszám>");

				// Az aktuális labdarúgó Szülidő elemének kiírása a konzolra
				Node szulidoNode = labdarugoElem.getElementsByTagName("Szülidő").item(0);
				String szulido = szulidoNode.getTextContent();
				System.out.println("    <Szülidő>" + szulido + "</Szülidő>");

				// Az aktuális "Labdarúgó" elem lezárása
				System.out.println("</Labdarúgó>");
			}
		}
	}
}


	

